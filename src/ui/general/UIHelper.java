package ui.general;

import java.util.Optional;

import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * A class that provides static methods for general purpose UI components
 * 
 * @author TNK
 *
 */
public class UIHelper {
	
	/**
	 * Sets the background color of a pane to the given color
	 * 
	 * @param pane Pane that the color is gonna be applied to
	 * @param c Color
	 */
	public static void setBackgroundColor(Pane pane, Color c){
		pane.setBackground(new Background(new BackgroundFill[] { new BackgroundFill(c, new CornerRadii(3.5), null)}));
	}
	
	/**
	 * Adds drop shadow to a node.
	 * @param node
	 */
	public static void setDropShadow(Node node){
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
		node.setEffect(dropShadow);
		
	}
	
	/**
	 * Adds click animation to a node.
	 * Node grows on mouse press and 
	 * shrinks on mouse release.
	 * 
	 * @param node
	 */
	public static void addClickAnimation(Node node){
		ScaleTransition s = new ScaleTransition();
		node.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			s.stop();
			s.setToX(1.2);
			s.setToY(1.2);
			s.play();
		});
		node.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
			s.stop();
			s.setToX(1);
			s.setToY(1);
			s.play();
		});
		s.setDuration(Duration.seconds(0.2));
		s.setNode(node);
		s.setAutoReverse(true);

	}
	
	/**
	 * This is a general purpose purpose alternative to the Button object.
	 * It allows for an icon and text to be displayed next to each other
	 * @param event: The action that is executed when the button is clicked
	 * @param optionalIcon: if you include an imageview,  make sure to set the layout properties
	 * @param optionalLabel: also make sure to set the layout for this. 
	 * @return a StackPane that is substituted for a button in order to allow an icon and text
	 * the StackPane also has a default animation when clicked
	 */
	public static StackPane buttonStack(EventHandler<MouseEvent> event, 
			Optional<Label> optionalLabel,Optional<ImageView> optionalIcon, Pos iconPos, boolean addDropShadow){
		StackPane view = new StackPane();
		UIHelper.setBackgroundColor(view, CustomColors.INDIGO); //default color
		view.addEventHandler(MouseEvent.MOUSE_CLICKED, event);
		UIHelper.addClickAnimation(view);
		optionalIcon.ifPresent(img -> {
			view.getChildren().add(img);
			StackPane.setAlignment(img, iconPos);
			StackPane.setMargin(img, new Insets(8));
		});
		optionalLabel.ifPresent(lbl -> {
			if(iconPos.equals(Pos.CENTER_RIGHT))
				StackPane.setMargin(lbl, new Insets(6,40,6,6));
			else if(iconPos.equals(Pos.CENTER_LEFT))
				StackPane.setMargin(lbl, new Insets(6,6,6,40));
			lbl.setAlignment(Pos.CENTER);
			view.getChildren().add(lbl);
			});
		if(addDropShadow)
			setDropShadow(view);
		return view;
	}
	
	
}
