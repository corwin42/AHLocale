package de.amberhome.locale;

import android.text.format.DateUtils;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.ShortName;

/**
 * With the AHDateUtils object you can format date and time strings with
 * standard device settings.
 * 
 * For more information have a look at the original <link>android.text.format.DateUtils|http://developer.android.com/reference/android/text/format/DateUtils.html</link>documentation.
 */
@ShortName("AHDateUtils")
public class AHDateUtils {

	public AHDateUtils() {
	}

	/**
	 * Formats a date or a time according to the local conventions. There are
	 * lots of options that allow the caller to control, for example, if the
	 * time is shown, if the day of the week is shown, if the month name is
	 * abbreviated, if noon is shown instead of 12pm, and so on.
	 * 
	 * Ticks - time/date to format
	 * Flags - a bit mask of options
	 */
	public String FormatDateTime(BA ba, long Ticks, int Flags) {
		return DateUtils.formatDateTime(ba.context, Ticks, Flags);
	}

	/**
	 * Formats a date or a time range according to the local conventions.
	 * 
	 * Note that this is a convenience method. Using it involves creating an
	 * internal Formatter instance on-the-fly, which is somewhat costly in terms
	 * of memory and time.
	 * 
	 * StartTicks - start time 
	 * EndTicks - end time 
	 * Flags - a bit mask of options
	 */
	public String FormatDateRange(BA ba, long StartTicks, long EndTicks,
			int Flags) {
		return DateUtils.formatDateRange(ba.context, StartTicks, EndTicks,
				Flags);
	}

	/**
	 * Formats an elapsed time in the form "MM:SS" or "H:MM:SS" for display on
	 * the call-in-progress screen.
	 * 
	 * Seconds - the elapsed time in seconds.
	 */
	public String FormatElapsedTime(long Seconds) {
		return DateUtils.formatElapsedTime(Seconds);
	}

	/**
	 * Return string describing the elapsed time since startTime formatted like
	 * "[relative time/date], [time]".
	 * 
	 * <b>Ticks</b> - some time in the past.
	 * 
	 * <b>MinResolution</b> - the minimum elapsed time (in milliseconds) to
	 * report when showing relative times. For example, a time 3 seconds in the
	 * past will be reported as "0 minutes ago" if this is set to
	 * MINUTE_IN_MILLIS.
	 * 
	 * <b>TransitionResolution</b> - the elapsed time (in milliseconds) at which
	 * to stop reporting relative measurements. Elapsed times greater than this
	 * resolution will default to normal date formatting. For example, will
	 * transition from "6 days ago" to "Dec 12" when using WEEK_IN_MILLIS.
	 * 
	 * <b>Flags</b> - a bit mask of options
	 */
	public CharSequence GetRelativeDateTimeString(BA ba, long Ticks,
			long MinResolution, long TransitionResolution, int Flags) {
		return DateUtils.getRelativeDateTimeString(ba.context, Ticks,
				MinResolution, TransitionResolution, Flags);
	}

	/**
	 * Returns a string describing 'time' as a time relative to 'now'.
	 * 
	 * Time spans in the past are formatted like "42 minutes ago". Time spans in
	 * the future are formatted like "in 42 minutes". Can use
	 * FORMAT_ABBREV_RELATIVE flag to use abbreviated relative times, like
	 * "42 mins ago".
	 * 
	 * <b>Ticks</b> - the time to describe, in milliseconds

	 * <b>Now</b> - the current time in
	 * milliseconds
	 * 
	 * <b>MinResolution</b> - the minimum timespan to report. For example,
	 * a time 3 seconds in the past will be reported as "0 minutes ago" if this
	 * is set to MINUTE_IN_MILLIS. Pass one of 0, MINUTE_IN_MILLIS,
	 * HOUR_IN_MILLIS, DAY_IN_MILLIS, WEEK_IN_MILLIS
	 * 
	 * <b>Flags</b> - a bit mask of
	 * formatting options, such as FORMAT_NUMERIC_DATE or FORMAT_ABBREV_RELATIVE
	 */
	public CharSequence GetRelativeTimeSpanString(BA ba, long Ticks, long Now,
			long MinResolution, int Flags) {
		return DateUtils.getRelativeTimeSpanString(Ticks, Now, MinResolution,
				Flags);
	}

	/**
	 * Returns true if the supplied Ticks value is today else false
	 * 
	 */
	public boolean IsToday(long Ticks) {
		return DateUtils.isToday(Ticks);
	}

	public static final int FORMAT_12HOUR = DateUtils.FORMAT_12HOUR;
	public static final int FORMAT_24HOUR = DateUtils.FORMAT_24HOUR;
	public static final int FORMAT_ABBREV_ALL = DateUtils.FORMAT_ABBREV_ALL;
	public static final int FORMAT_ABBREV_MONTH = DateUtils.FORMAT_ABBREV_MONTH;
	public static final int FORMAT_ABBREV_RELIVE = DateUtils.FORMAT_ABBREV_RELATIVE;
	public static final int FORMAT_ABBREV_TIME = DateUtils.FORMAT_ABBREV_TIME;
	public static final int FORMAT_ABBREV_WEEKDAY = DateUtils.FORMAT_ABBREV_WEEKDAY;
	public static final int FORMAT_CAP_AMPM = DateUtils.FORMAT_CAP_AMPM;
	public static final int FORMAT_CAP_MIDNIGHT = DateUtils.FORMAT_CAP_MIDNIGHT;
	public static final int FORMAT_CAP_NOON = DateUtils.FORMAT_CAP_NOON;
	public static final int FORMAT_CAP_NOON_MIDNIGHT = DateUtils.FORMAT_CAP_NOON_MIDNIGHT;
	public static final int FORMAT_NO_MIDNIGHT = DateUtils.FORMAT_NO_MIDNIGHT;
	public static final int FORMAT_NO_MONTH_DAY = DateUtils.FORMAT_NO_MONTH_DAY;
	public static final int FORMAT_NO_NOON = DateUtils.FORMAT_NO_NOON;
	public static final int FORMAT_NO_NOON_MIDNIGHT = DateUtils.FORMAT_NO_NOON_MIDNIGHT;
	public static final int FORMAT_NO_YEAR = DateUtils.FORMAT_NO_YEAR;
	public static final int FORMAT_NUMERIC_DATE = DateUtils.FORMAT_NUMERIC_DATE;
	public static final int FORMAT_SHOW_DATE = DateUtils.FORMAT_SHOW_DATE;
	public static final int FORMAT_SHOW_TIME = DateUtils.FORMAT_SHOW_TIME;
	public static final int FORMAT_SHOW_WEEKDAY = DateUtils.FORMAT_SHOW_WEEKDAY;
	public static final int FORMAT_SHOW_YEAR = DateUtils.FORMAT_SHOW_YEAR;
	public static final int FORMAT_UTC = DateUtils.FORMAT_UTC;
	public static final int LENGTH_LONG = DateUtils.LENGTH_LONG;
	public static final int LENGTH_MEDIUM = DateUtils.LENGTH_MEDIUM;
	public static final int LENGTH_SHORT = DateUtils.LENGTH_SHORT;
	public static final int LENGTH_SHORTER = DateUtils.LENGTH_SHORTER;
	public static final int LENGTH_SHORTEST = DateUtils.LENGTH_SHORTEST;

}
