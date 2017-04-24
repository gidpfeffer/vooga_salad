/**
 * 
 */
package ui.ratings;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author harirajan
 *
 */
public class RatingMain extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		RatingView view = new RatingView(primaryStage);
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
