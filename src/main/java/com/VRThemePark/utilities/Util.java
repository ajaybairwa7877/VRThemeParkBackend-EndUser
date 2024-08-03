package com.VRThemePark.utilities;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ParseException;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Util {

    private final static Logger LOGGER = LoggerFactory.getLogger(Util.class);

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getCurrentDate() {
        return new Date();

    }

    public static String getCurrentDateIncrementByOneYear() {
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        // manipulate date
        c.add(Calendar.YEAR, 1);
        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        return dateFormat.format(currentDatePlusOne);
    }

    public static String getCurrentDateDecrementBy1Day() {
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        // manipulate date
        c.add(Calendar.DAY_OF_MONTH, -30);
        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        return dateFormat.format(currentDatePlusOne);
    }

    public static Date getCurrentDateIncrementBy1Day(Date inputDate, int numberOfDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(inputDate);
        // manipulate date
        c.add(Calendar.DAY_OF_MONTH, numberOfDays);
        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        return currentDatePlusOne;
    }

    public static Date convertStringToDate(String stringDate) throws Exception {
        try {
            Date date = dateFormat.parse(stringDate);
            return date;
        } catch (ParseException e) {
            throw new Exception("Incorrect Date Formate", e);
        }
    }

    public static String getParameters(HttpServletRequest request) {
        StringBuffer posted = new StringBuffer();
        Enumeration<?> e = request.getParameterNames();
        if (e != null) {
            posted.append("?");
        }
        while (e.hasMoreElements()) {
            if (posted.length() > 1) {
                posted.append("&");
            }
            String curr = (String) e.nextElement();
            posted.append(curr + "=");
            if (curr.contains("password") || curr.contains("pass") || curr.contains("pwd")) {
                posted.append("*****");
            } else {
                posted.append(request.getParameter(curr));
            }
        }
        String ip = request.getHeader("X-FORWARDED-FOR");
        String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
        if (ipAddr != null && !ipAddr.equals("")) {
            posted.append("&_psip=" + ipAddr);
        }
        return posted.toString();
    }

    public static String getRemoteAddr(HttpServletRequest request) {
        String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            return ipFromHeader;
        }
        return request.getRemoteAddr();
    }

    public static String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public static String getFullName(String firstName, String lastName) {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);
        return sb.toString();
    }

    public static String getFullNameWithTitleAndQual(String title, String firstName, String lastName,
                                                     String qualification) {
        StringBuilder sb = new StringBuilder();
        if (title != null && title.length() > 0) {
            sb.append(title);
            sb.append(" ");
        }

        if (firstName != null && firstName.length() > 0) {
            sb.append(firstName);
        }

        if (lastName != null && lastName.length() > 0) {
            sb.append(" ");
            sb.append(lastName);
        }

        if (qualification != null && qualification.length() > 0) {
            sb.append(" ");
            sb.append(qualification);
        }

        return sb.toString();
    }

    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

//	public static LocationDetails fetchLocationDataWithIP(HttpServletRequest request) {
//		RestTemplate restTemplate = new RestTemplate();
//		return restTemplate.getForObject("http://ipwhois.app/json/" + Util.getRemoteAddr(request) + "?lang=en",
//				LocationDetails.class);
//	}

    public static boolean checkDouble(String value) {
        String decimalPattern = "^-?[0-9]\\d*(\\.\\d+)?$";
        return Pattern.matches(decimalPattern, value);
    }

    public static long numberOfDay(Date fromDate, LocalDate to) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = fromDate.toInstant();
        LocalDate from = instant.atZone(defaultZoneId).toLocalDate();
        return ChronoUnit.DAYS.between(from, to);
    }

    public static long numberOfDay(Date fromDate) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = fromDate.toInstant();
        LocalDate from = instant.atZone(defaultZoneId).toLocalDate();
        return ChronoUnit.DAYS.between(from, LocalDate.now());
    }

    public static boolean isNewUser(Date userProfileCreationDate, String value) {
        boolean flag = false;
        long noOfDays = numberOfDay(userProfileCreationDate, LocalDate.now());
        if (noOfDays <= Integer.parseInt(value)) {
            flag = true;
        }
        return flag;
    }

    public static String getDateAfterParticularDate(String value) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime then = now.minusDays(Integer.parseInt(value));
        return then.format(format);
    }



    public static boolean isStringValid(String stringValue, int length) {
        if (stringValue != null && stringValue.length() > length) {
            return true;
        }
        return false;
    }

    public static String returnEmailFromJson(String json) {
        Pattern p = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(json);
        while (matcher.find()) {
            return matcher.group();
        }
        return null;
    }


}
