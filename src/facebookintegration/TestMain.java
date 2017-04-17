package facebookintegration;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestMain extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Get FB Set Up!");
        Button btn = new Button();
        btn.setText("Get FB Set Up!");
        StackPane root = new StackPane();
        Authenticator a = null;
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	
            @Override
            public void handle(ActionEvent event) {
	            Authenticator a = new Authenticator();
	            a.setUp();
                ImageView pic = a.getProfilePicFromClient();
	            pic.setFitHeight(50);
	            pic.setFitWidth(50);
	            StackPane.setAlignment(pic, Pos.TOP_CENTER);
	            root.getChildren().add(a.getProfilePicFromClient());
	            btn.setText("Post What's Up to FB");
            }
        });
       
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
	}

}
