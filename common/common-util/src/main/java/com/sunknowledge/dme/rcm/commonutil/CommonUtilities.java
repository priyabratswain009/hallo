package com.sunknowledge.dme.rcm.commonutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtilities {

	static UUID generateUUID() {
		return UUID.randomUUID();
	}

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

	public static boolean isStringNullOrBlank(String value) {

		if (value == null || value.isEmpty() || value.trim().isEmpty() || value.trim().equalsIgnoreCase("null")) {
			return false;
		}

		return true;
	}

	public static String datetoStringConverter(String date) {
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		String day = date.substring(8, 10);
		return (year + month + day);
	}

	public static LocalDate stringtodateConverter(String date) {
		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		String day = date.substring(6, 8);
		LocalDate localDate = LocalDate.parse(year + "-" + month + "-" + day);
		return localDate;
	}

	public static LocalDate stringwithhyphentodateConverter(String date) {
		LocalDate localDate = LocalDate.parse(date);
		return localDate;
	}

	public static LocalDate stringzonedtodateConverter(String date) {
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		String day = date.substring(8, 10);
		LocalDate localDate = LocalDate.parse(year + "-" + month + "-" + day);
		return localDate;
	}

	public static ZonedDateTime stringtozoneddateConverter(String date) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss VV");
		date = date.substring(0, 10);
		System.out.println("Parsed date:  " + date);
		ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);
		return zonedDateTime;
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

	public static String dateDifference(LocalDate date1, LocalDate date2) {

		long daysDifference = ChronoUnit.DAYS.between(date1, date2);
		long monthsDifference = ChronoUnit.MONTHS.between(date1, date2);
		long yearsDifference = ChronoUnit.YEARS.between(date1, date2);

		String diff = String.valueOf(daysDifference) + "days " + String.valueOf(monthsDifference) + "months "
				+ String.valueOf(yearsDifference) + "Year's";

		return diff;
	}

	public static ServiceOutcome<LocalDate> dateCompare(LocalDate date1, LocalDate date2)
			throws ParseException, java.text.ParseException {

		ServiceOutcome<LocalDate> outCome = new ServiceOutcome<LocalDate>();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdformat.parse(date1.toString());
		Date d2 = sdformat.parse(date2.toString());
		int val = d1.compareTo(d2);

		if (val > 0) {
			outCome.setData(date1);
			outCome.setOutcome(true);
			outCome.setMessage("Date1 is greater than Date2");
		} else if (val == 0) {
			outCome.setData(date1);
			outCome.setOutcome(true);
			outCome.setMessage("Equal");
		} else {
			outCome.setData(date2);
			outCome.setOutcome(true);
			outCome.setMessage("Date2 is greater than Date1");
		}

		return outCome;
	}

	// ---- For trimming the left and right spaces of a DTO strings ----
	public static void dtoTrimmer(Object classObj) {
		Field[] allFields = classObj.getClass().getDeclaredFields();
		for (Field field : allFields) {
			field.setAccessible(true);
			try {
				Object value = field.get(classObj);
				if (value != null && value instanceof String) {
					String data = (String) value;
					field.set(classObj, data.trim());
				}
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	// -- For reading the property file ------------
	public Properties readPropertiesFile(String fileName) throws IOException {
		InputStream fis = null;
		Properties prop = null;
		try {
			prop = new Properties();
			fis = this.getClass().getResourceAsStream(fileName);

			// create Properties class object
			if (fis != null) {
				// load properties file into it
				prop.load(fis);
			} else {
				throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			fis.close();
		}

		return prop;
	}

	// ===== Convert List To Json By Removing a Specific Value
	// ==========================
	public static JSONArray convertListToJsonRemovingSpecificValue(List list, String keyToRemove) {
		ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.registerModule(new JavaTimeModule());
		String newJsonData = null;
		try {
			newJsonData = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		JSONParser parser = new JSONParser();
		JSONArray jarr = null;
		try {
			jarr = (JSONArray) parser.parse(newJsonData);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		jarr.forEach(obj -> {
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject.containsKey(keyToRemove)) {
				jsonObject.remove(keyToRemove);
			}
		});
		return jarr;
	}

	//=========== Merge Name ==============================
	public static String mergeName(String firstName, String middleName, String lastName) {
		if (middleName == null && lastName == null) {
			return firstName.trim();
		} else if (firstName == null && lastName == null) {
			return middleName.trim();
		} else if (middleName == null && firstName == null) {
			return lastName.trim();
		} else if (firstName != null && lastName != null &&
				middleName != null && !middleName.trim().equals("")) {
			return firstName.trim() + " " + middleName.trim() + " " + lastName.trim();
		} else if (firstName != null && lastName != null &&
				(middleName == null || middleName.trim().equals(""))) {
			return firstName.trim() + " " + lastName.trim();
		} else {
			return "NA";
		}
	}
}
