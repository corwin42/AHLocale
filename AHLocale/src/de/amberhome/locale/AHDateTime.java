package de.amberhome.locale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.text.format.DateFormat;
import anywheresoftware.b4a.BA;
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

	public final int DEFAULT = SimpleDateFormat.DEFAULT;
	public final int SHORT = SimpleDateFormat.SHORT;
	public final int MEDIUM = SimpleDateFormat.MEDIUM;
	public final int LONG = SimpleDateFormat.LONG;
	public final int FULL = SimpleDateFormat.FULL;
	
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
	 */
	public void setTimeZone(AHTimezone TimeZone) {
		sdf.setTimeZone(TimeZone.tz);
	}

	public AHTimezone getTimeZone() {
		AHTimezone tz = new AHTimezone();
		tz.Initialize_internal(sdf.getTimeZone());
		return tz;
	}
	
	/**
	 * Gets the default TimePattern of the device default locale
	 *  
	 * Style - Use constants SHORT, MEDIUM, LONG, FULL, DEFAULT
	 */
	public String GetDefaultTimePattern(BA ba, int Style) {
		SimpleDateFormat sf = (SimpleDateFormat) SimpleDateFormat.getTimeInstance(Style);
		return sf.toPattern();
	}
	
	/**
	 * Gets the default TimePattern of given locale
	 *  
	 * Style - Use constants SHORT, MEDIUM, LONG, FULL, DEFAULT
	 * Locale - Reference to an AHLocale object
	 */
	public String GetDefaultTimePattern2(int Style, AHLocale Locale) {
		SimpleDateFormat sf = (SimpleDateFormat) SimpleDateFormat.getTimeInstance(Style, Locale.myLocale);
		return sf.toPattern();
	}
	
	/**
	 * Return a short date pattern without year information
	 */
	public String GetShortDatePatternWithoutYear() {
		SimpleDateFormat sf = (SimpleDateFormat) SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
		return sf.toPattern().replaceAll("\\W?[Yy]+\\W?", "");
	}
	
	
	/**
	 * Gets the default DatePattern of the device default locale
	 *  
	 * Style - Use constants SHORT, MEDIUM, LONG, FULL, DEFAULT
	 */
	public String GetDefaultDatePattern(int Style) {
		SimpleDateFormat sf = (SimpleDateFormat) SimpleDateFormat.getDateInstance(Style);
		return sf.toPattern();
	}

	/**
	 * Gets the default DatePattern of given locale
	 *  
	 * Style - Use constants SHORT, MEDIUM, LONG, FULL, DEFAULT
	 * Locale - Reference to an AHLocale object
	 */
	public String GetDefaultDatePattern2(int Style, AHLocale Locale) {
		SimpleDateFormat sf = (SimpleDateFormat) SimpleDateFormat.getDateInstance(Style, Locale.myLocale);
		return sf.toPattern();
	}
	
	/**
	 * Gets the device default DateTimePattern
	 * 
	 * DateStyle - Style for Date
	 * TimeStyle - Style for Time
	 */
	public String GetDefaultDateTimePattern(int DateStyle, int TimeStyle) {
		SimpleDateFormat sf = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance(DateStyle, TimeStyle);
		return sf.toPattern();		
	}

	/**
	 * Gets the DateTimePattern for the given Locale
	 * 
	 * DateStyle - Style for Date
	 * TimeStyle - Style for Time
	 */
	public String GetDefaultDateTimePattern2(int DateStyle, int TimeStyle, AHLocale Locale) {
		SimpleDateFormat sf = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance(DateStyle, TimeStyle, Locale.myLocale);
		return sf.toPattern();		
	}

	/**
	 * Returns true if the user preference settings is 24 hour format.
	 */
	public boolean is24HourFormat(BA ba) {
		return DateFormat.is24HourFormat(ba.context);
	}
}
