package util.general;

import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/**
 * Used for reading property files
 * 
 * @author maddiebriere
 *
 */

public class PropertyUtil {

	/**
	 * Add the terms in the resource file found in the bundle 'resource' to the
	 * given List of Entries
	 * 
	 * @param resource
	 *            Name of the resource file
	 * @param list
	 *            List to modify
	 */
	public static void addTerms(String resource, List<Entry<String, Pattern>> list) {
		ResourceBundle resources = ResourceBundle.getBundle(resource);
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			list.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
	}

	/**
	 * Return the item contained in this file specified by the String key. NOTE:
	 * Return this item as an int.
	 * 
	 * @param resource
	 *            The name of the file
	 * @param key
	 *            The key for which to search
	 * @return The numerical value held by that key in this resource file
	 */
	public static int getNumericalTerm(String resource, String key) {

		ResourceBundle resources = ResourceBundle.getBundle(resource);
		Object target;
		try {
			target = resources.getObject(key);
		} catch (Exception e) {
			return 0;
		}
		try {
			return Integer.parseInt((String) target);
		} catch (Exception e) {
			return 0;
		}
	}
}
