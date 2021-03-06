package com.boardgamegeek.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.boardgamegeek.R;
import com.boardgamegeek.events.PlaySelectedEvent;
import com.boardgamegeek.tasks.ResetPlaysTask;
import com.boardgamegeek.util.ActivityUtils;
import com.boardgamegeek.util.DialogUtils;
import com.boardgamegeek.util.TaskUtils;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;

import org.greenrobot.eventbus.Subscribe;

import hugo.weaving.DebugLog;

public class PlaysSummaryActivity extends TopLevelSinglePaneActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			Answers.getInstance().logContentView(new ContentViewEvent().putContentType("PlaysSummary"));
		}
	}

	@Override
	protected Fragment onCreatePane() {
		return new PlaysSummaryFragment();
	}

	@Override
	protected int getDrawerResId() {
		return R.string.title_plays;
	}

	@Override
	protected int getOptionsMenuId() {
		return R.menu.plays_summary;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.re_sync) {
			DialogUtils.createConfirmationDialog(this, R.string.pref_sync_reset_plays_info_message, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					TaskUtils.executeAsyncTask(new ResetPlaysTask(PlaysSummaryActivity.this));
				}
			}).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("unused")
	@DebugLog
	@Subscribe
	public void onEvent(PlaySelectedEvent event) {
		ActivityUtils.startPlayActivity(this, event);
	}
}
