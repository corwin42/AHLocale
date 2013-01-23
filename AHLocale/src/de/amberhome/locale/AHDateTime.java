package de.amberhome.locale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import anywheresoftware.b4a.BA.ShortName;

/**
 * With the AHDateTime object you can format and parse date and time strings.
 * 
 * The B4A builtin DateTime object always uses localized format strings so it is
 * not possible to parse something like "2011/05/19 01:45 PM" on a german device
 * because "PM" is "nachm." there. You can Initialize the AHDateTime object with
 * any locale you want and so you are totally free on date formats.
 * 
 */
@ShortName("AHDateTime")
public class AHDateTime {
	private SimpleDateFormat sdf;
	private String pattern = "yyyy-MM-dd HH:mm:ss";

	public AHDateTime() {
	}

	/**
	 * Initializes the AHDateTime object with the default locale.
	 */
	public void Initialize() {
		sdf = new SimpleDateFormat();
		sdf.setTimeZone(TimeZone.getDefault());
	}

	/**
	 * Initializes the AHDateTime object with the given AHLocale object
	 */
	public void Initialize2(AHLocale locale) {
		sdf = new SimpleDateFormat(this.pattern, locale.myLocale);
		sdf.setTimeZone(TimeZone.getDefault());
	}

	/**
	 * Initializes the AHDateTime object with the <strong>en_US</strong> locale.
	 */
	public void InitializeUS() {
		sdf = new SimpleDateFormat(this.pattern, Locale.US);
		sdf.setTimeZone(TimeZone.getDefault());
	}

	/**
	 * Sets or gets the Date/Time pattern
	 * 
	 * Default pattern is: <strong>yyyy-MM-dd HH:mm:ss</strong>
	 * 
	 * For allowed placeholders look here: <link>SimpleDateFormat|http://developer.android.com/reference/java/text/SimpleDateFormat.html</link>
	 */
	public void setPattern(String pattern) {
		sdf.applyPattern(pattern);
	}

	public String getPattern() {
		return sdf.toPattern();
	}

	/**
	 * If you set this to true the parser is not very strict on his date format.
	 * 
	 */
	public void setLenient(boolean value) {
		sdf.setLenient(value);
	}

	public boolean getLenient() {
		return sdf.isLenient();
	}

	/**
	 * Formats the specified ticks value to a string with the specified pattern.
	 * 
	 */
	public String Format(long ticks) {
		Date mydate = new Date(ticks);

		return sdf.format(mydate);
	}

	/**
	 * Parses the specified string and returns a tick value.
	 * 
	 * Throws an exception if the string can not be parsed.
	 */
	public long Parse(String date) {
		try {
			return sdf.parse(date).getTime();
		} catch (ParseException e) {
			throw new RuntimeException("Unable to parse date string");
		}
	}
	
	/**
	 * Sets or gets the timezone for the AHDateTime object
	 * @param TimeZone
	 */
	public void setTimeZone(AHTimezone TimeZone) {
		sdf.setTimeZone(TimeZone.tz);
	}

	public AHTimezone getTimeZone() {
		AHTimezone tz = new AHTimezone();
		tz.Initialize_internal(sdf.getTimeZone());
		return tz;
	}
}
