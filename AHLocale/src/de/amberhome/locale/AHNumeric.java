package de.amberhome.locale;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.keywords.Common;

/**
 * With the AHNumeric object you can format and parse numbers, currency values
 * and percent values.
 * 
 * There are several Initialize methods to initialize different types of the
 * object. These differ normally in the pattern that is applied to the
 * NumberFormat object.
 * 
 * For further details see the <link>java.text.DecimalFormat|http://developer.android.com/reference/java/text/DecimalFormat.html</link>
 * and <link>java.text.NumberFormat|http://developer.android.com/reference/java/text/NumberFormat.html</link> documentation.
 * 
 */
@ShortName("AHNumeric")
public class AHNumeric {
	NumberFormat mNumberFormat;

	public AHNumeric() {

	}

	/**
	 * Initializes a standard NumberFormat object with the standard locale
	 */
	public void Initialize() {
		mNumberFormat = NumberFormat.getInstance();
	}

	/**
	 * Initializes a standard NumberFormat object with the given locale.
	 */
	public void Initialize2(AHLocale Locale) {
		mNumberFormat = NumberFormat.getInstance(Locale.myLocale);
	}

	/**
	 * Initializes a standard NumberFormat object with the standard locale
	 */
	public void InitializeNumber() {
		mNumberFormat = NumberFormat.getNumberInstance();
	}

	/**
	 * Initializes a standard NumberFormat object with the given locale.
	 */
	public void InitializeNumber2(AHLocale Locale) {
		mNumberFormat = NumberFormat.getNumberInstance(Locale.myLocale);
	}

	/**
	 * Initializes an integer NumberFormat object with the standard locale
	 */
	public void InitializeInteger() {
		mNumberFormat = NumberFormat.getIntegerInstance();
	}

	/**
	 * Initializes an integer NumberFormat object with the given locale.
	 */
	public void InitializeInteger2(AHLocale Locale) {
		mNumberFormat = NumberFormat.getIntegerInstance(Locale.myLocale);
	}

	/**
	 * Initializes a currency NumberFormat object with the standard locale
	 */
	public void InitializeCurrency() {
		mNumberFormat = NumberFormat.getCurrencyInstance();
	}

	/**
	 * Initializes a currency NumberFormat object with the given locale.
	 */
	public void InitializeCurrency2(AHLocale Locale) {
		mNumberFormat = NumberFormat.getCurrencyInstance(Locale.myLocale);
	}

	/**
	 * Initializes a percent NumberFormat object with the standard locale
	 */
	public void InitializePercent() {
		mNumberFormat = NumberFormat.getPercentInstance();
	}

	/**
	 * Initializes a percent NumberFormat object with the given locale.
	 */
	public void InitializePercent2(AHLocale Locale) {
		mNumberFormat = NumberFormat.getPercentInstance(Locale.myLocale);
	}

	/**
	 * Set or get the maximum fraction digits.
	 */
	public void setMaximumFractionDigits(int Value) {
		mNumberFormat.setMaximumFractionDigits(Value);
	}

	public int getMaximumFractionDigits() {
		return mNumberFormat.getMaximumFractionDigits();
	}

	/**
	 * Set or get the maximum integer digits.
	 */
	public void setMaximumIntegerDigits(int Value) {
		mNumberFormat.setMaximumIntegerDigits(Value);
	}

	public int getMaximumIntegerDigits() {
		return mNumberFormat.getMaximumIntegerDigits();
	}

	/**
	 * Set or get the minimum fraction digits.
	 */
	public void setMinimumFractionDigits(int Value) {
		mNumberFormat.setMinimumFractionDigits(Value);
	}

	public int getMinimumFractionDigits() {
		return mNumberFormat.getMinimumFractionDigits();
	}

	/**
	 * Set or get the minimum integer digits
	 */
	public void setMinimumIntegerDigits(int Value) {
		mNumberFormat.setMinimumIntegerDigits(Value);
	}

	public int getMinimumIntegerDigits() {
		return mNumberFormat.getMinimumIntegerDigits();
	}

	/**
	 * Set or get the pattern that is used for formatting and parsing.
	 */
	public void setPattern(String Pattern) {
		if (mNumberFormat instanceof DecimalFormat) {
			((DecimalFormat) mNumberFormat).applyPattern(Pattern);
		} else
			Common.Log("Not a DecimalFormat");
	}

	public String getPattern() {
		if (mNumberFormat instanceof DecimalFormat) {
			return ((DecimalFormat) mNumberFormat).toPattern();
		} else {
			Common.Log("Not a DecimalFormat");
			return "";
		}
	}

	/**
	 * Parse the given string and convert it to a number. This may throw a
	 * ParseException if the format of the string is not correct.
	 */
	public Number Parse(String Value) throws ParseException {
		return mNumberFormat.parse(Value);
	}

	/**
	 * Format the number to a string using the pattern and rules of this object.
	 */
	public String Format(double Value) {
		return mNumberFormat.format(Value);
	}

}
