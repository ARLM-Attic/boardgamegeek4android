package com.boardgamegeek;

public final class Utility {

	private Utility() {
		// prevents instantiation
	}

	public static String unescapeText(String escapedText) {
		String unescapedText = escapedText;
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
		unescapedText = unescapedText.replace("\n\n\n", "\n\n");
		return unescapedText.trim();
	}
}
