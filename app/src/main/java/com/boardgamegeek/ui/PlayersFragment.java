package com.boardgamegeek.ui;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boardgamegeek.R;
import com.boardgamegeek.provider.BggContract.PlayPlayers;
import com.boardgamegeek.provider.BggContract.Plays;
import com.boardgamegeek.util.UIUtils;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import timber.log.Timber;

public class PlayersFragment extends StickyHeaderListFragment implements LoaderManager.LoaderCallbacks<Cursor> {
	private static final String STATE_SELECTED_NAME = "selectedName";
	private static final String STATE_SELECTED_USERNAME = "selectedUsername";

	private PlayersAdapter mAdapter;
	private String mSelectedName;
	private String mSelectedUsername;

	public interface Callbacks {
		public boolean onPlayerSelected(String name, String username);

		public void onPlayerCountChanged(int count);
	}

	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public boolean onPlayerSelected(String name, String username) {
			return true;
		}

		@Override
		public void onPlayerCountChanged(int count) {
		}
	};

	private Callbacks mCallbacks = sDummyCallbacks;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof Callbacks)) {
			throw new ClassCastException("Activity must implement fragment's callbacks.");
		}
		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			mSelectedName = savedInstanceState.getString(STATE_SELECTED_NAME);
			mSelectedUsername = savedInstanceState.getString(STATE_SELECTED_USERNAME);
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setEmptyText(getString(R.string.empty_players));
		getLoaderManager().restartLoader(PlayersQuery._TOKEN, getArguments(), this);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (!TextUtils.isEmpty(mSelectedName) || !TextUtils.isEmpty(mSelectedUsername)) {
			outState.putString(STATE_SELECTED_NAME, mSelectedName);
			outState.putString(STATE_SELECTED_USERNAME, mSelectedUsername);
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(View v, int position, long id) {
		final Cursor cursor = (Cursor) mAdapter.getItem(position);
		final String name = cursor.getString(PlayersQuery.NAME);
		final String username = cursor.getString(PlayersQuery.USER_NAME);
		if (mCallbacks.onPlayerSelected(name, username)) {
			setSelectedPlayer(name, username);
		}
	}

	public void setSelectedPlayer(String name, String username) {
		mSelectedName = name;
		mSelectedUsername = username;
		if (mAdapter != null) {
			mAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle data) {
		CursorLoader loader = new CursorLoader(getActivity(), Plays.buildPlayersByUniquePlayerUri(),
			PlayersQuery.PROJECTION, null, null, PlayPlayers.NAME);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		if (getActivity() == null) {
			return;
		}

		int token = loader.getId();
		if (token == PlayersQuery._TOKEN) {
			if (mAdapter == null) {
				mAdapter = new PlayersAdapter(getActivity());
				setListAdapter(mAdapter);
			}
			mAdapter.changeCursor(cursor);
			mCallbacks.onPlayerCountChanged(cursor.getCount());
			restoreScrollState();
		} else {
			Timber.d("Query complete, Not Actionable: " + token);
			cursor.close();
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mAdapter.changeCursor(null);
	}

	public class PlayersAdapter extends CursorAdapter implements StickyListHeadersAdapter {
		private LayoutInflater mInflater;

		public PlayersAdapter(Context context) {
			super(context, null, false);
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			View row = mInflater.inflate(R.layout.row_text_3, parent, false);
			ViewHolder holder = new ViewHolder(row);
			row.setTag(holder);
			return row;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			ViewHolder holder = (ViewHolder) view.getTag();

			String name = cursor.getString(PlayersQuery.NAME);
			String userName = cursor.getString(PlayersQuery.USER_NAME);
			int quantity = cursor.getInt(PlayersQuery.SUM_QUANTITY);

			UIUtils.setActivatedCompat(view, name.equals(mSelectedName) && userName.equals(mSelectedUsername));

			holder.name.setText(name);
			holder.username.setText(userName);
			holder.username.setVisibility(TextUtils.isEmpty(userName) ? View.GONE : View.VISIBLE);
			holder.quantity.setText(getResources().getQuantityString(R.plurals.plays, quantity, quantity));
		}

		@Override
		public long getHeaderId(int position) {
			if (position < 0) {
				return 0;
			}
			return getHeaderText(position).charAt(0);
		}

		@Override
		public View getHeaderView(int position, View convertView, ViewGroup parent) {
			HeaderViewHolder holder;
			if (convertView == null) {
				holder = new HeaderViewHolder();
				convertView = mInflater.inflate(R.layout.row_header, parent, false);
				holder.text = (TextView) convertView.findViewById(android.R.id.title);
				convertView.setTag(holder);
			} else {
				holder = (HeaderViewHolder) convertView.getTag();
			}
			holder.text.setText(getHeaderText(position));
			return convertView;
		}

		private String getHeaderText(int position) {
			String missingLetter = "-";
			int cur = getCursor().getPosition();
			getCursor().moveToPosition(position);
			String name = getCursor().getString(PlayersQuery.NAME);
			getCursor().moveToPosition(cur);
			String targetLetter = TextUtils.isEmpty(name) ? missingLetter : name.substring(0, 1).toUpperCase(
				Locale.getDefault());
			return targetLetter;
		}

		class ViewHolder {
			@InjectView(android.R.id.title) TextView name;
			@InjectView(android.R.id.text1) TextView username;
			@InjectView(android.R.id.text2) TextView quantity;

			public ViewHolder(View view) {
				ButterKnife.inject(this, view);
			}
		}

		class HeaderViewHolder {
			TextView text;
		}
	}

	private interface PlayersQuery {
		int _TOKEN = 0x1;

		String[] PROJECTION = { BaseColumns._ID, PlayPlayers.NAME, PlayPlayers.USER_NAME, PlayPlayers.SUM_QUANTITY };

		int NAME = 1;
		int USER_NAME = 2;
		int SUM_QUANTITY = 3;
	}
}
