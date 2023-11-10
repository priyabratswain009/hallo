package com.sunknowledge.changehealthcare.validation;

import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericComponentValidations {

	static boolean isemail(String email) {

		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches())
			return false;
		return true;
	}

	static Boolean isIndustryCode(String value) {

		if (value.matches(
				"01|03|04|05|06|07|08|11|12|13|14|15|20|21|22|23|24|25|26|31|32|33|34|41|42|49|50|51|52|53|54|55|56|57|60|61|62|65|71|72|81|99"))
			return false;

		return true;

	}

	static Boolean isdiagnosisTypeCode(String value) {

		if (value.matches("BK|ABK|BF|ABF"))
			return false;

		return true;

	}

	static boolean issequenceNo(String value) {
		boolean val = isdigit(value);
		if (val || value.length() > 9)
			return false;

		return true;
	}

	static Boolean isPostalCode(String value) {

		if (value.length() < 1 || value.length() > 15)
			return true;

		return false;

	}

	static Boolean isState(String value) {

		if (value.length() < 1 || value.length() > 2)
			return true;

		return false;

	}

	static Boolean isCity(String value) {

		if (value.length() < 1 || value.length() > 30)
			return true;

		return false;

	}

	static Boolean islength55(String value) {

		if (value.length() < 1 || value.length() > 55)
			return true;

		return false;

	}

	static Boolean islength50(String value) {

		if (value.length() < 1 || value.length() > 50)
			return true;

		return false;

	}

	static Boolean islength80(String value) {

		if (value.length() < 1 || value.length() > 80)
			return true;

		return false;

	}

	static Boolean islength35(String value) {

		if (value.length() < 1 || value.length() > 35)
			return true;

		return false;

	}

	static Boolean islength60(String value) {

		if (value.length() < 1 || value.length() > 60)
			return true;

		return false;

	}

	static String noOfDigits(String value) {

		StringTokenizer t = new StringTokenizer(value, ".");
		String s1 = t.nextToken();
		String s2 = t.nextToken();
		int n1 = s1.length();
		int n2 = s2.length();
		if (s1.charAt(0) == '0')
			n1 = s1.length() - 1;
		if (n2 != 1)
			if (s2.charAt(s2.length() - 1) == '0')
				n2 = s2.length() - 1;
		String s3 = String.valueOf(n1) + ":" + String.valueOf(n2);
		return s3;
	}

	static boolean isalphanumeric(String value) {
		if (value.matches("[a-zA-Z0-9]+")) {
			return true;
		}

		return false;
	}

	static boolean isalphanumericspace(String value) {
		if (value.matches("^[A-Za-z0-9? ]+$")) {
			return true;
		}

		return false;
	}

	static boolean isdigit(String value) {
		if (value.chars().allMatch(Character::isDigit)) {
			return true;
		}

		return false;
	}

	static boolean isalphabets(String value) {
		int n = value.length();
		for (int i = 0; i < n; i++) {
			if (!Character.isLetter(value.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	public static boolean isName(String name) {

		Pattern pattern = Pattern.compile(new String("^[a-zA-Z\\s]*$"));
		Matcher matcher = pattern.matcher(name);

		return matcher.matches();

	}

	static boolean isStringNullOrBlank(String value) {

		if (value == null || value.isEmpty() || value.trim().isEmpty()) {
			return false;
		}

		return true;
	}

	static boolean isValidDate(String value) {

		Calendar cal = Calendar.getInstance();

		String year = value.substring(0, 4);
		String month = value.substring(4, 6);
		String day = value.substring(6, 8);

		if (cal.get(Calendar.YEAR) <= Integer.parseInt(year)) {
			return false;
		}

		if (Integer.parseInt(month) > 12) {
			return false;
		}

		if ((Integer.parseInt(month) == 1 || Integer.parseInt(month) == 3 || Integer.parseInt(month) == 5
				|| Integer.parseInt(month) == 7 || Integer.parseInt(month) == 8 || Integer.parseInt(month) == 10
				|| Integer.parseInt(month) == 12) && Integer.parseInt(day) > 31) {
			return false;
		}
		if ((Integer.parseInt(month) == 4 || Integer.parseInt(month) == 6 || Integer.parseInt(month) == 9
				|| Integer.parseInt(month) == 11) && Integer.parseInt(day) > 30) {
			return false;
		}

		if (Integer.parseInt(month) == 2 && Integer.parseInt(day) > 28 && Integer.parseInt(year) % 4 > 0) {
			return false;
		}
		if (Integer.parseInt(month) == 2 && Integer.parseInt(day) > 29 && Integer.parseInt(year) % 4 == 0) {
			return false;
		}

		return true;
	}
}
