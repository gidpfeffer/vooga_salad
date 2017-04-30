/**
 * 
 */
package util;

/**
 * @author harirajan
 *
 */
public class DateTimeUtil {
	
	public DateTimeUtil() {
		
	}
	
	public void test() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
	}
	
	public static void main(String[] args) {
		DateTimeUtil test = new DateTimeUtil();
		test.test();
	}

}
