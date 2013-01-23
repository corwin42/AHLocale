package de.amberhome.locale;

import java.util.Date;
import java.util.TimeZone;

import anywheresoftware.b4a.BA.Hide;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.objects.collections.List;

/**
 * With the AHTimezone object you can get information about device timezones.
 * 
 */
@ShortName("AHTimeZone")
public class AHTimezone {
	
	java.util.TimeZone tz;
	
	public int STYLE_SHORT = TimeZone.SHORT;
	public int STYLE_LONG = TimeZone.LONG;
	
	public AHTimezone() {
		
	}
	
	/**
	 * Initialize a AHTimeZone object with default timezone
	 */
	public void Initialize() {
		tz = TimeZone.getDefault();
	}
	
	/**
	 * Initializes a AHTimeZone object with the given TimeZone ID
	 * 
	 * ID - TimeZone ID like "Europe/Berlin"
	 */
	public void Initialize2(String ID) {
		tz = TimeZone.getTimeZone(ID);
	}
	
	@Hide
	public void Initialize_internal(TimeZone timeZone) {
		tz = timeZone;
	}
	
	/**
	 * Returns a list of available timezone IDs on this device.
	 * One of the results can be passed to the Initialize2() Method to reinitialize
	 * an AHTimeZone object with a different timezone
	 */
	public List getAvailableIds() {
		List ret = new List();
		ret.Initialize();

		for (String id : TimeZone.getAvailableIDs()) {
			ret.Add(id);
		}

		return ret;
	}
	
	/**
	 * Returns the daylight savings offset in milliseconds for this time zone.
	 * The base implementation returns 3600000 (1 hour) for time zones that use daylight savings
	 * time and 0 for timezones that do not. 
	 * Note that this method doesn't tell you whether or not to apply the offset: you need to call
	 * InDaylightTime() for the specific time you're interested in. If this method returns a non-zero
	 * offset, that only tells you that this TimeZone sometimes observes daylight savings.
	 */
	public long getDSTSavings() {
		return tz.getDSTSavings();
	}
	
	/**
	 * Returns the Display name of this timezone
	 * 
	 * DaylightTime - Set to true if you want to have DST included into the name
	 * Style - AHTimeZone.SHORT or AHTimeZone.LONG format
	 */
	public String GetDisplayName(boolean DaylightTime, int Style) {
		return tz.getDisplayName(DaylightTime, Style);
	}
	
	/**
	 * Returns the Display name of this timezone
	 * 
	 * DaylightTime - Set to true if you want to have DST included into the name
	 * Style - AHTimeZone.SHORT or AHTimeZone.LONG format
	 * Locale - a AHLocale object to set in which language you want the displayname 
	 */
	public String GetDisplayName2(boolean DaylightTime, int Style, AHLocale Locale) {
		return tz.getDisplayName(DaylightTime, Style, Locale.myLocale);
	}
	
	/**
	 * Gets or sets the timezone ID as String for this timezone
	 */
	public String getID() {
		return tz.getID();
	}
	
	public void setID(String ID) {
		tz.setID(ID);
	}
	
	/**
	 * Returns the offset in milliseconds from UTC for this time zone at time.
	 * The offset includes daylight savings time if the specified date is within the daylight savings time period.
	 * 
	 * Date - Date for which you want to have the info
	 */
	public long GetOffset(long Date) {
		return tz.getOffset(Date);
	}
	
	/**
	 * Sets or gets the Raw offset for this timezone
	 */
	public long getRawOffset() {
		return tz.getRawOffset();
	}
	
	public void setRawOffset(int Offset) {
		tz.setRawOffset(Offset);
	}
	
	/**
	 * Returns true if Date is in a daylight savings time period for this time zone.
	 * 
	 * Date - Date for which you want to get the info
	 */
	public boolean InDaylightTime(long Date) {
		Date dt = new Date(Date);
		
		return tz.inDaylightTime(dt);
	}
}
