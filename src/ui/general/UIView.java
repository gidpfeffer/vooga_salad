// This entire file is part of my masterpiece.
// Talha Koc
/*
 * The purpose of this class is to create a fast and structured 
 * way to initialize a plain view and set its layout properties.
 * In the previous projects, I struggled to refactor code relating
 * to the layout properties. I would individually set each property.
 * With this class, I am able to not only position views very
 * precisely on screen, but also very quickly. I simply have to 
 * create a new Frame object and provide that in the constructor.
 * 
 * Additionally, UIView contains both a frame object and a bounds
 * object. The bounds object defines the layout properties locally.
 * This means that the xy-position will be set to 0 and the height
 * and width will remain the same as the frame object's. This allows
 * me to position sub-components within a UIView. 
 * 
 * Although neither UIView or Frame contain any advanced design 
 * concepts, these two classes together have allowed me to reduce 
 * my code size by a factor of 3-4, create better looking UI
 * components more easily, and keep my code structure more defined.
 * I do recognize that other frontend people may have differing 
 * opinions on how they like to setup their GUI components. I
 * like to be precise and these two classes help me do that. 
 * 
 * To see an example of how I use UIView, checkout 
 * the initGUIComponents method in UIMain.
 */
package ui.general;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * UIView is a Pane that can be initialized with a frame.
 * The frame will determine the layout properties of this class
 * like the xy-position, height, and width. Furthermore, GUI 
 * objects inside a UIView can access frame and bounds to determine
 * where to locate themselves.
 * @author TNK
 *
 */
public class UIView extends Pane implements Frameable{
	
	private Frame _frame;
	private Frame _bounds;
	
	public UIView(){
		super();
		_frame = new Frame(0,0,0,0);
		_bounds = new Frame(0,0,0,0);
	}
	
	public UIView(Frame frame){
		super();
		_bounds = frame.toLocalBounds();
		setFrame(frame);
	}
	
	public void setFrame(Frame frame){
		_frame = frame;
		this.setLayoutX(frame.getX());
		this.setLayoutY(frame.getY());
		this.setPrefWidth(frame.getWidth());
		this.setPrefHeight(frame.getHeight());
	}

	public Frame getFrame() {
		return _frame;
	}
	public Frame getBounds(){
		return _bounds;
	}
	
	public void setBackgroundColor(Color c){
		this.setBackground(new Background(new BackgroundFill[] { new BackgroundFill(c, new CornerRadii(3), Insets.EMPTY)}));
	}
	
}
