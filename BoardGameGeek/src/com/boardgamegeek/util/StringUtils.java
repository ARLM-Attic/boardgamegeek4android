package com.boardgamegeek.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;
import android.util.Log;

/**
 * Provides utility methods for dealing with strings.
 */
public class StringUtils {
	private final static String TAG = "StringUtils";

	private static Pattern mVertialWhiteSpacePattern;

	static {
		mVertialWhiteSpacePattern = Pattern.compile("[\n]{3,}");
	}

	public static String formatDescription(String input) {
		if (TextUtils.isEmpty(input)) {
			return null;
		}

		final long startLocal = System.currentTimeMillis();

		String output = input;
		output = convertBreaks(output);
		output = unescapeHtml(output);
		output = trimLineEndings(output);
		output = removeVerticalWhiteSpace(output);

		Log.d(TAG, "Formatting description took " + (System.currentTimeMillis() - startLocal) + "ms");

		return output;
	}

	private static String convertBreaks(String input) {
		return input.replace("<br/>", "\n");
	}

	private static String trimLineEndings(String input) {
		return input.replaceAll("[ \t]+$", "");
	}

	private static String removeVerticalWhiteSpace(String input) {
		Matcher matcher = mVertialWhiteSpacePattern.matcher(input);
		return matcher.replaceAll("\n\n").trim();
	}

	/**
	 * Replaces escaped HTML with the unescaped equivalent.
	 */
	private static String unescapeHtml(String escapedHtml) {
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
		unescapedText = unescapedText.replace("&acute;", "'");
		unescapedText = unescapedText.replace("&trade;", "�");
		unescapedText = unescapedText.replace("&mdash;", "�");
		unescapedText = unescapedText.replace("&ndash;", "-");
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
		return unescapedText;
	}

	public static String createSortName(String name, int sortIndex) {
		if (sortIndex <= 1 || sortIndex > name.length()) {
			return name;
		}
		int i = sortIndex - 1;
		return name.substring(i) + ", " + name.substring(0, i).trim();
	}

	public static int parseInt(String text) {
		return parseInt(text, 0);
	}

	public static int parseInt(String text, int defaultValue) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException ex) {
			return defaultValue;
		}
	}

	public static double parseDouble(String text) {
		return parseDouble(text, 0);
	}

	public static double parseDouble(String text, double defaultValue) {
		try {
			return Double.parseDouble(text);
		} catch (NumberFormatException ex) {
			return defaultValue;
		}
	}

	/**
	 * Gets the ordinal (1st) for the given cardinal (1)
	 * 
	 * @param cardinal
	 * @return
	 */
	public static String getOrdinal(int cardinal) {

		if (cardinal < 0) {
			return "";
		}

		String c = String.valueOf(cardinal);
		String n = "0";
		if (c.length() > 1) {
			n = c.substring(c.length() - 2, c.length() - 1);
		}
		String l = c.substring(c.length() - 1);
		if (!n.equals("1")) {
			if (l.equals("1")) {
				return c + "st";
			} else if (l.equals("2")) {
				return c + "nd";
			} else if (l.equals("3")) {
				return c + "rd";
			}
		}
		return c + "th";
	}

	public static String[] concat(String[] first, String[] second) {
		String[] result = new String[first.length + second.length];
		System.arraycopy(first, 0, result, 0, first.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}
}
