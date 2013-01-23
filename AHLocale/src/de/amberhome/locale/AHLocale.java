// Version History
//
// 1.11:
//   - Fix problem with Gregoriancalendar not available on all devices for all locales.
// 1.12:
//   - Add AHTimeZone object
// 1.13:
//   - Add TimeZone property to AHDateTime object
// 1.14:
//	 - AHTranslator.java - Removed log output
//	 - Timezone.java - fixed typo
// 1.15:
//   - AHNumeric - new
//   - AHDateUtils - new
// 1.16:
//   - Rename all classes to AHxxx notation
//   - Prepare for GitHub upload

package de.amberhome.locale;

import java.text.DateFormatSymbols;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Locale;

import anywheresoftware.b4a.BA.Author;
import anywheresoftware.b4a.BA.Hide;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.BA.Version;
import anywheresoftware.b4a.objects.collections.List;

/**
 * The AHLocale object allows you to access many locale aware values such as
 * localized month names, day names or currency symbols.
 * 
 * There are also methods to find out which locales are available on your device
 * or which languages and countries can be used.
 */
@ShortName("AHLocale")
@Version(1.15f)
@Author("Markus Stipp")
public class AHLocale {

	@Hide
	public Locale myLocale;
	//private Calendar myCalendar;

	private boolean isInitialized = false;

	public AHLocale() {
	}

	private void init(Locale loc) {
		myLocale = loc;
		isInitialized = true;
	}

	/**
	 * Initializes the AHLocale object with the default locale.
	 */
	public void Initialize() {
		init(Locale.getDefault());
	}

	/**
	 * Initializes the AHLocale object using the specified language.
	 */
	public void Initialize2(String language) {
		init(new Locale(language, Locale.getDefault().getCountry()));
	}

	/**
	 * Initializes the AHLocale object using the specified language and country.
	 */
	public void Initialize3(String language, String country) {
		init(new Locale(language, country));
	}

	/**
	 * Initializes the AHLocale Object with <strong>en_US</strong> locale. This locale is
	 * available on every device.
	 */
	public void InitializeUS() {
		init(Locale.US);
	}

	/**
	 * Returns the system's installed locales. These are returned as a B4A List of AHLocale Objects.
	 * There are only locales in this list which have a non empty country and language.
	 */
	static public List getAvailableLocales() {
		List ret = new List();
		ret.Initialize();

		for (Locale loc : Locale.getAvailableLocales()) {
			if (loc.getCountry() != "" && loc.getLanguage() != "")
			{
				AHLocale ahloc = new AHLocale();
				ahloc.init(loc);

				ret.Add(ahloc);
			}
		}

		return ret;
	}

	//
	// Locale related methods and properties
	//


	/**
	 * Returns if the object is initialized
	 */
	public Boolean getInitialized() {
		return isInitialized;
	}

	/**
	 * Returns the ISO Code for this Locale such as <strong>en_US</strong> or <strong>de_DE</strong>
	 */
	public String getISOCode() {
		return myLocale.toString();
	}

	/**
	 * Returns the country/region code for this locale, which will either be the
	 * empty string or an uppercase ISO 3166 2-letter code.
	 */
	public String getCountry() {
		return myLocale.getCountry();
	}

	/**
	 * Returns the name of this locale's country, localized to locale. Returns
	 * the empty string if this locale does not correspond to a specific country
	 */
	public String getDisplayCountry() {
		return myLocale.getDisplayCountry(myLocale);
	}

	/**
	 * Returns the name of this locale's country in english. Returns
	 * the empty string if this locale does not correspond to a specific country
	 */
	public String getEnglishCountry() {
		return myLocale.getDisplayCountry(Locale.US);
	}

	/**
	 * Returns this locale's language name, country name, and variant, localized
	 * to locale. The exact output form depends on whether this locale
	 * corresponds to a specific language, country and variant, such as:
	 * English, English (United States), English (United States,Computer),
	 * anglais (\u0419tats-Unis), anglais (\u0419tats-Unis,informatique).
	 * 
	 */
	public String getDisplayName() {
		return myLocale.getDisplayName(myLocale);
	}

	/**
	 * Returns this locale's language name, country name, and variant in english.
	 * The exact output form depends on whether this locale
	 * corresponds to a specific language, country and variant, such as:
	 * English, English (United States), English (United States,Computer),
	 * anglais (\u0419tats-Unis), anglais (\u0419tats-Unis,informatique).
	 * 
	 */
	public String getEnglishName() {
		return myLocale.getDisplayName(Locale.US);
	}

	/**
	 * Gets the language code for this Locale or the empty string if no language
	 * was set.
	 */
	public String getLanguage() {
		return myLocale.getLanguage();
	}

	/**
	 * Returns the name of this locale's language, localized to locale. If the
	 * language name is unknown, the language code is returned.
	 */
	public String getDisplayLanguage() {
		return myLocale.getDisplayLanguage(myLocale);
	}

	/**
	 * Returns the name of this locale's language in english. If the
	 * language name is unknown, the language code is returned.
	 */
	public String getEnglishLanguage() {
		return myLocale.getDisplayLanguage(Locale.US);
	}

	/**
	 * Gets the three letter ISO country code which corresponds to the country
	 * code for this Locale.
	 */
	public String getISO3Country() {
		return myLocale.getISO3Country();
	}

	/**
	 * Gets the three letter ISO language code which corresponds to the language
	 * code for this Locale.
	 * 
	 */
	public String getISO3Language() {
		return myLocale.getISO3Language();
	}

	/**
	 * Gets the list of two letter ISO country codes which can be used as the
	 * country code for a Locale.
	 */
	public String[] getISOCountries() {
		return Locale.getISOCountries();
	}

	/**
	 * Gets the list of two letter ISO language codes which can be used as the
	 * language code for a Locale.
	 */
	public String[] getISOLanguages() {
		return Locale.getISOLanguages();
	}

	//
	// Currency Symbol Methods
	//

	/**
	 * Returns the localized currency symbol for this currency in locale.
	 */
	public String getCurrencySymbol() {
		return Currency.getInstance(myLocale).getSymbol();
	}

	/**
	 * Returns this currency's ISO 4217 currency code.
	 */
	public String getCurrencyCode() {
		return Currency.getInstance(myLocale).getCurrencyCode();
	}

	/**
	 * Returns the default number of fraction digits for this currency.
	 */
	public int getCurrencyFractionDigits(){
		return Currency.getInstance(myLocale).getDefaultFractionDigits();
	}

	//
	// Calendar related Methods and properties
	//

	/**
	 * Returns the array of strings which represent AM and PM.
	 */
	public String[] getAmPmStrings(){
		return new DateFormatSymbols(myLocale).getAmPmStrings();
	}

	/**
	 * Returns the array of strings containing the full names of the months.
	 */
	public String[] getMonths() {
		return new DateFormatSymbols(myLocale).getMonths();
	}

	/**
	 * Returns the array of strings containing the abbreviated names of the months.
	 */
	public String[] getShortMonths(){
		return new DateFormatSymbols(myLocale).getShortMonths();
	}

	/**
	 * Returns the array of strings containing the full names of the days of the week.
	 */
	public String[] getWeekDays(){
		return new DateFormatSymbols(myLocale).getWeekdays();
	}

	/**
	 * Returns the array of strings containing the abbreviated names of the days of the week.
	 */
	public String[] getShortWeekDays(){
		return new DateFormatSymbols(myLocale).getShortWeekdays();
	}

	/**
	 * Gets the first day of the week
	 */
	public int getFirstDayOfWeek() {
		return new GregorianCalendar(myLocale).getFirstDayOfWeek();
	}


}
