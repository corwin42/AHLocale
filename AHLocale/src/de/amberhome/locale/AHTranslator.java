package de.amberhome.locale;

import java.io.IOException;

import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.objects.collections.List;
import anywheresoftware.b4a.objects.collections.Map;
import anywheresoftware.b4a.objects.streams.File;

/**
 * With the Translator object you can make your programs support multiple
 * languages. It is very easy to create translations for your program and use
 * them.
 * 
 * Translation files have the following name convention:
 * [basename]_[language].lng
 * 
 * The format is a standard Java properties file. Special characters should be
 * converted to unicode sequences.
 * 
 * The translator object holds two Map Objects for the translations. One for the
 * existing translations in the language file and one for missing translations
 * in the language file. When you call GetText() with a string that is not in
 * the translation file then it will be added to the missing translations Map.
 * 
 */
@ShortName("AHTranslator")
public class AHTranslator {

	private Map translation = new Map();
	private Map missingTranslation = new Map();

	private String currentLanguage;
	private String currentCountry;
	private String currentFile;
	private String currentExtension = ".lng";
	private String defaultLanguage = "";
	//private String currentDir;

	public AHTranslator() {

	}

	/**
	 * Initializes a translation object with the device language. Tries to read a
	 * translation file with the following name convention: basename_language.lng 
	 */
	public void Initialize(String dir, String basename) throws IOException {

		currentLanguage = java.util.Locale.getDefault().getLanguage();
		currentCountry = java.util.Locale.getDefault().getCountry();

		Initialize3(dir, basename, currentLanguage, currentCountry);
	}

	/**
	 * Initializes a translation object with the given language. Tries to read a
	 * translation file with the following name convention: basename_language.lng 
	 */
	public void Initialize2(String dir, String basename, String language)
			throws IOException {

		Initialize3(dir, basename, language, null);
	}

	/**
	 * Initializes a translation object with the given language and country code.
	 * Tries to read a translation file with the following name
	 * convention: basename_language_country.lng 
	 */
	public void Initialize3(String dir, String basename, String language, String country)
			throws IOException {

		translation.Initialize();
		missingTranslation.Initialize();
		translation.Clear();
		missingTranslation.Clear();

		if (defaultLanguage != null & defaultLanguage != "") {
			LoadDefaultTranslations(dir, basename);
		}
		LoadTranslation(dir, basename, language, country);
	}

	private void LoadDefaultTranslations(String dir, String file) throws IOException {
		String filename;
		
		filename = file + "_" + defaultLanguage + currentExtension;

		if (File.Exists(dir, filename)) {
			translation = File.ReadMap(dir, filename);
		}
		else if (File.Exists(File.getDirAssets(), filename)) {
			translation = File.ReadMap(dir, filename);
		}
}
	
	private void LoadTranslation(String dir, String file, String language, String country)
			throws IOException {

		String filename;
		
		currentLanguage = language;
		currentCountry = country;
		if (country != null & country != "") {
			filename = file + "_" + language + "_" + country + currentExtension;
			
			if (!File.Exists(dir, filename)) {
				filename = file + "_" + language + currentExtension;
				currentCountry = "";
			}
		}
		else {
			filename = file + "_" + language + currentExtension;
		}
		currentFile = filename;

		Map localTranslation = new Map();
		localTranslation.Initialize();
		
		if (File.Exists(dir, filename)) {
			localTranslation = File.ReadMap(dir, filename);
		}
		else if (File.Exists(File.getDirAssets(), filename)) {
			localTranslation = File.ReadMap(dir, filename);
		}
		
		for (int i=0; i<localTranslation.getSize(); i++) {
			translation.Put(localTranslation.GetKeyAt(i), localTranslation.GetValueAt(i));
		}
	}

	/**
	 * Writes two files to the specified dir. The current translation Map is
	 * written to [filename]_[language].lng and the missing translation Map is
	 * written to [filename]_miss_[language].lng 
	 */
	public void WriteTranslation(String dir, String filename)
			throws IOException {
		if (translation.getSize() > 0) {
			File.WriteMap(dir, filename + "_" + currentLanguage + currentExtension,
					translation);
		}

		if (missingTranslation.getSize() > 0) {
			File.WriteMap(dir, filename + "_miss_" + currentLanguage + currentExtension,
					missingTranslation);
		}
	}

	/**
	 * Searches for the text in the translation table. If a translation is
	 * available it will be returned. If no translation is available the text is
	 * added to the missing translation table and the original text is returned.
	 * So you can write your whole program in a default language. If a
	 * translation is available it will be used, if not, the text will be
	 * returned in the default language.
	 */
	public String GetText(String text) {
		// If the text is in our translation table then return it.
		if (translation.ContainsKey(text)) {
			return (String) translation.Get(text);
		} else {
			// If the text is not in our translation table then check
			// if it is in the missing translations table. If not, add it.
			if (!missingTranslation.ContainsKey(text)) {
				missingTranslation.Put(text, text);
			}

			return text;
		}
	}

	/**
	 * Searches for the text in the translation table and returns the translated
	 * text.
	 * 
	 * You can use Placeholders in the form of {1} {2} etc. which will be
	 * replaced with the content of the second parameter. {1} will be replaced
	 * with the first item in the Array/List, {2} with the second one and so on.
	 * 
	 */
	public String GetText2(String text, List params) {
		String myText = GetText(text);

		if (params.getSize() > 0) {
			String retString = myText;
			for (int i = 0; i < params.getSize(); i++) {
				retString = ((String) retString).replace("{" + (i + 1) + "}", params
						.Get(i).toString());
			}
			myText = retString;
		}

		return myText;
	}

	/**
	 * Returns a Map object with the current translations
	 */
	public Map getTranslationMap() {
		return translation;
	}

	/**
	 * Sets the translation Map object
	 */
	public void setTranslationMap(Map transmap) {
		translation = transmap;
	}

	/**
	 * Returns a Map object with all text phrases which are missing in the 
	 * translation file.
	 */
	public Map getMissingTranslationMap() {
		return missingTranslation;
	}

	/**
	 * Returns current Language code
	 */
	public String getCurrentLanguage() {
		return currentLanguage;
	}
	
	/**
	 * Returns the current County code
	 */
	public String getCurrentCountry() {
		return currentCountry;
	}
	
	/**
	 * Returns current file name of language file
	 */
	public String getCurrentFile() {
		return currentFile;
	}
	
	/**
	 * Sets or gets the current file extension
	 */
	public String getExtension() {
		return currentExtension;
	}
	
	public void setExtension(String Extension) {
		currentExtension = Extension;
	}
	
	/**
	 * Sets or gets the default language. This is the language used when no language file is found.
	 * If you don't set it the default language will be empty and is not used.
	 */
	public String getDefaultLanguage() {
		return defaultLanguage;
	}
	
	public void setDefaultLanguage(String Language) {
		defaultLanguage = Language;
	}
}
