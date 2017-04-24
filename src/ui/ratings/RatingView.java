/**
 * 
 */
package ui.ratings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author harirajan
 *
 */
public class RatingView extends ScrollPane {
	
	
	public RatingView(Stage s) {
		
		setContent(new RatingDisplay("reviews.xml"));
				
		s.setScene(new Scene(this));
		s.setWidth(800);
		s.setHeight(800);
		s.setResizable(false);
		s.show();
	}
}
