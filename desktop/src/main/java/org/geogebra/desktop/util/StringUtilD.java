package org.geogebra.desktop.util;

import java.util.Locale;

import org.geogebra.common.util.StringUtil;

public class StringUtilD extends StringUtil {
	@Override
	protected boolean isRightToLeftChar(char c) {
		// CharTableImpl c;
		return (Character.getDirectionality(c) == Character.DIRECTIONALITY_RIGHT_TO_LEFT);
	}

	@Override
	public String toLower(String s) {
		return s.toLowerCase(Locale.US);
	}

	@Override
	public String toUpper(String s) {
		return s.toUpperCase(Locale.US);
	}
}
