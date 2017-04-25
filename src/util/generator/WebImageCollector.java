package util.generator;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * Class for collecting images (and other file types, if
 * necessary) from the internet. 
 * 
 * FOR IMAGES:
 * Contains findImage method that take in a "query" (search topic) and a file
 * type (e.g., "png"), and returns a BufferedImage.
 * 
 * Resources:
 * http://codigogenerativo.com/code/google-custom-search-api/
 * https://developers.google.com/custom-search/json-api/v1/reference/cse/list
 * https://www.google.com/search?client=safari&rls=en&q=convert+from+image+url+to+image+java&ie=UTF-8&oe=UTF-8
 * @author maddiebriere
 *
 */

public class WebImageCollector {
	//API key, generated on Google Custom Search website
	private final static String KEY = "AIzaSyA5cXZKmvGI_SSj0KDfOyVtNTCXNO5o_64";
	//Personal Google web-engine ID
	private final static String CX = "010345643380297177901:4s0abli8aki";
	
	//Delimeters used for search precision
	private final static String IMAGE = "image";
	private final static String PNG = "png";
	private final static String URL_START = "\"link\": \"";
	private final static String URL_END = "\",";
	private final static String API_ADDRESS = "https://www.googleapis.com/customsearch/v1?";
	private final static String IMAGE_FOLDER = "images/internet/";
	
	public static ImageInfo findAndSaveRandomIcon(Random randy, String qry){
		BufferedImage image = findRandomIcon(randy, qry);
		String savePath = IMAGE_FOLDER + qry;
		String s = savePng(image, savePath);
		return new ImageInfo(image, s);
		
		
	}
	
	/**
	 * Saved png to the internet images folder in the workspace
	 * @param image Image to be saved
	 * @param fileName Folder + name of file (e.g., images/button)
	 */
	private static String savePng(BufferedImage image, String fileName) {
		String toRet = "";
        try {
        	toRet = fileName + "." + PNG;
            ImageIO.write(image, PNG,
                    new File(toRet));
        } catch (IOException e) {
        	//TODO
            e.printStackTrace();
        }
        return toRet;
    }
	
	
	
	/**
	 * For use in random Actor generation.
	 * 
	 * @param Random Object used to choose iteration for image
	 * @param qry The String topic to search
	 * @return BufferedImage found on the internet
	 */
	public static BufferedImage findRandomIcon(Random randy, String qry){
		int index = randy.nextInt(20) + 1;
		return findImage(qry+"+cartoon", PNG, index);
	}
	
	/**
	 * For use in random Actor generation.
	 * @param qry String topic to search
	 * @param iter How many layers of search to go through before choosing
	 * the final image
	 * @return BufferedImage found on the internet
	 */
	public static BufferedImage findIcon(String qry, int iter){
		return findImage(qry+"+cartoon", PNG, iter);
	}
	
	public static BufferedImage findImage(String qry, String fileType, int iter){
		return findSearchItem(qry, fileType, IMAGE, iter);
	}
	
	public static BufferedImage findSearchItem(String qry, String fileType, String searchType, int iter){
		BufferedImage toRet = null;
		String search = stringToSearch(qry);
		try{
			HttpURLConnection google = constructSearchUrl(search, fileType, searchType);
			String imagePath = popFilePath(google, iter);
			google.disconnect();
			URL toRead = new URL(imagePath);
			toRet = ImageIO.read(toRead);
			
		} catch(IOException e){
			//printExceptionMessage(e);
			toRet = findSearchItem(qry, fileType, searchType, ++iter);
			if(toRet == null && iter-1>0){
				toRet = findSearchItem(qry, fileType, searchType, --iter);
			}
			//toRet = ImageIO.read(new File('DEFAULT_PATH'));
			// Replace null with default image
		}
		return toRet;
	}
	
	private static String stringToSearch(String qry){
		return qry.replaceAll(" ", "+");
	}
	
	private static String popFilePath(HttpURLConnection conn, int iter) throws IOException {
		InputStream stream = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader((stream)));
		  
		String output;
		String toRet = "";
		int counter = 0;
		while ((output = br.readLine()) != null) {
		     if(output.contains(URL_START)){                
		         toRet = output.substring(output.indexOf(URL_START)+
		              (URL_START).length(), output.indexOf(URL_END));
		         if(counter++>=iter)
		        	 break;
		     }     
		}
		return toRet;
	}
	
	private static HttpURLConnection constructSearchUrl(String qry, String fileType, String searchType) throws IOException{
		URL url = new URL (API_ADDRESS + 
				"key=" +KEY+ 
				"&cx=" +CX+ 
				"&q=" +qry+
				"&fileType="+fileType+
				"&searchType="+searchType+
				"&alt=json");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    configureConnection(conn);
	    return conn;
	}
	
	private static void configureConnection(HttpURLConnection conn) throws ProtocolException{
		conn.setRequestMethod("GET");
	    conn.setRequestProperty("Accept", "application/json");
	}
	
/*	private static void printExceptionMessage(Exception e){
		JOptionPane.showMessageDialog(null, "Message: " + e.getMessage() 
			+ "\nCause: "+ e.getCause());
		
	}*/
}
