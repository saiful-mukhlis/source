package com.bmb.app.lang;


import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.google.common.base.CharMatcher;

public class Lang {
	private Locale locale;
	private Locale resourceBundleLocale = null; // has to be null
	private ResourceBundle resourceBundle = null;
	static final String I18N_PREFIX = "";
//	private  String bundleName;

//	public Lang(final String bundleName) {
//		this.bundleName = bundleName;
//	}

	public Locale getLocale() {
		if (this.locale == null) {
			return Locale.getDefault();
		}
		return this.locale;
	}

	public void setLocale(final Locale locale) {
		this.locale = locale;
	}

//	private synchronized final ResourceBundle getLocaleBundle() {
//
//		Locale currentLocale = getLocale();
//		if (!currentLocale.equals(resourceBundleLocale)) {
//			resourceBundleLocale = currentLocale;
//			resourceBundle = ResourceBundle.getBundle(bundleName,
//					resourceBundleLocale, getClass().getClassLoader());
//		}
//		return resourceBundle;
//
//	}
	
	private synchronized final ResourceBundle getLocaleBundle(String bundleName) {

		Locale currentLocale = getLocale();
		if (!currentLocale.equals(resourceBundleLocale)) {
			resourceBundleLocale = currentLocale;
			resourceBundle = ResourceBundle.getBundle(bundleName,
					resourceBundleLocale, getClass().getClassLoader());
		}
		return resourceBundle;

	}

//	public String getLocalizedString(String key) {
//		try {
//			return getLocaleBundle().getString(CharMatcher.is(' ').replaceFrom(key, "_"));
//		} catch (MissingResourceException ex) {
//			return String.format("%s", key);
//		}
//	}
	
	public String getLocalizedString(String bundleName, String key) {
		try {
			return getLocaleBundle(bundleName).getString(CharMatcher.is(' ').replaceFrom(key, "_"));
		} catch (MissingResourceException ex) {
			return String.format("%s", key);
		}
	}

	public String getText(String bundleName,String text) {
		return text.startsWith(I18N_PREFIX) ? getLocalizedString(bundleName, text
				.substring(I18N_PREFIX.length())) : text;
	}

}
