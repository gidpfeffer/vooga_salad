package ui.authoring.level;

import gamedata.ActorData;
import gamedata.WaveData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ui.authoring.delegates.PopViewDelegate;

public class EnemyChooserMenu extends AnchorPane{
	
	private PopViewDelegate myDelegate;
	private WaveData wave;
	private ActorData enemy;
	private StackPane view;
	
	public EnemyChooserMenu(PopViewDelegate delegate, WaveData wave, ActorData enemy){
		super();
		myDelegate = delegate;
		this.wave = wave;
		this.enemy = enemy;
		setupView();
	}
	
	public void setupView(){
		view = new StackPane();
		this.getChildren().add(view);
	}
	
}
