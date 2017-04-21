package util.generator;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

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
	
	//TODO: Error classifications!
	
	public static void main (String [] args){
		Image pup = findPng("Puppy");
	}
	
	public static Image findPng(String qry){
		return findImage(qry, PNG);
	}
	
	public static Image findImage(String qry, String fileType){
		return findSearchItem(qry, fileType, IMAGE);
	}
	
	public static Image findSearchItem(String qry, String fileType, String searchType){
		Image toRet = null;
		try{
			HttpURLConnection google = constructSearchUrl(qry, fileType, searchType);
			String imagePath = popFilePath(google);
			google.disconnect();
			URL toRead = new URL(imagePath);
			toRet = java.awt.Toolkit.getDefaultToolkit().createImage(toRead);
			
		} catch(IOException e){
			printExceptionMessage(e);
		}
		return toRet;
	}
	
	private static String popFilePath(HttpURLConnection conn) throws IOException {
		InputStream stream = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader((stream)));
		  
		String output;
		String toRet = "";
		while ((output = br.readLine()) != null) {
		     if(output.contains(URL_START)){                
		         toRet = output.substring(output.indexOf(URL_START)+
		              (URL_START).length(), output.indexOf(URL_END));
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
	
	private static void printExceptionMessage(Exception e){
		System.out.println("Message: " + e.getMessage() + "\nCause: "+ e.getCause());
	}
}