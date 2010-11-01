package com.boardgamegeek.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * Provides utility methods for dealing with strings.
 */
public class StringUtils {
	private final static String TAG = "StringUtils";

	/**
	 * Replaces escaped HTML with the unescaped equivalent.
	 */
	public static String unescapeHtml(String escapedHtml) {
		if (TextUtils.isEmpty(escapedHtml)) {
			return null;
		}
		
		final long startLocal = System.currentTimeMillis();

		String unescapedText = escapedHtml;
		unescapedText = unescapedText.replace("&nbsp;", " ");
		unescapedText = unescapedText.replace("&lt;", "<");
		unescapedText = unescapedText.replace("&gt;", ">");
		unescapedText = unescapedText.replace("&amp;", "&");
		unescapedText = unescapedText.replace("&quot;", "\"");
		unescapedText = unescapedText.replace("&ldquo;", "\"");
		unescapedText = unescapedText.replace("&rdquo;", "\"");
		unescapedText = unescapedText.replace("&apos;", "'");
		unescapedText = unescapedText.replace("&lsquo;", "'");
		unescapedText = unescapedText.replace("&rsquo;", "'");
		unescapedText = unescapedText.replace("&trade;", "�");
		unescapedText = unescapedText.replace("&ndash;", "�");
		unescapedText = unescapedText.replace("&agrave;", "�");
		unescapedText = unescapedText.replace("&Agrave;", "�");
		unescapedText = unescapedText.replace("&acirc;", "�");
		unescapedText = unescapedText.replace("&auml;", "�");
		unescapedText = unescapedText.replace("&Auml;", "�");
		unescapedText = unescapedText.replace("&Acirc;", "�");
		unescapedText = unescapedText.replace("&aring;", "�");
		unescapedText = unescapedText.replace("&Aring;", "�");
		unescapedText = unescapedText.replace("&aelig;", "�");
		unescapedText = unescapedText.replace("&AElig;", "�");
		unescapedText = unescapedText.replace("&ccedil;", "�");
		unescapedText = unescapedText.replace("&Ccedil;", "�");
		unescapedText = unescapedText.replace("&eacute;", "�");
		unescapedText = unescapedText.replace("&Eacute;", "�");
		unescapedText = unescapedText.replace("&egrave;", "�");
		unescapedText = unescapedText.replace("&Egrave;", "�");
		unescapedText = unescapedText.replace("&ecirc;", "�");
		unescapedText = unescapedText.replace("&Ecirc;", "�");
		unescapedText = unescapedText.replace("&euml;", "�");
		unescapedText = unescapedText.replace("&Euml;", "�");
		unescapedText = unescapedText.replace("&iuml;", "�");
		unescapedText = unescapedText.replace("&Iuml;", "�");
		unescapedText = unescapedText.replace("&ocirc;", "�");
		unescapedText = unescapedText.replace("&Ocirc;", "�");
		unescapedText = unescapedText.replace("&ouml;", "�");
		unescapedText = unescapedText.replace("&Ouml;", "�");
		unescapedText = unescapedText.replace("&oslash;", "�");
		unescapedText = unescapedText.replace("&Oslash;", "�");
		unescapedText = unescapedText.replace("&szlig;", "�");
		unescapedText = unescapedText.replace("&ugrave;", "�");
		unescapedText = unescapedText.replace("&Ugrave;", "�");
		unescapedText = unescapedText.replace("&ucirc;", "�");
		unescapedText = unescapedText.replace("&Ucirc;", "�");
		unescapedText = unescapedText.replace("&uuml;", "�");
		unescapedText = unescapedText.replace("&Uuml;", "�");
		unescapedText = unescapedText.replace("&copy;", "\u00a9");
		unescapedText = unescapedText.replace("&reg;", "\u00ae");
		unescapedText = unescapedText.replace("&euro;", "\u20a0");
		unescapedText = unescapedText.replace("&bull;", "\u0095");
		unescapedText = unescapedText.replace("&#10;", "\n");
		unescapedText = unescapedText.replace("<br/>", "\n");
		
		Log.d(TAG, "unescaping HTML took " + (System.currentTimeMillis() - startLocal) + "ms");
		
		return unescapedText.trim();
	}
}
