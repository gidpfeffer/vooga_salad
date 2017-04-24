package ui.player.inGame;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import gamedata.ActorData;
import gameengine.grid.interfaces.frontendinfo.FrontEndInformation;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ui.general.ImageViewPane;
import ui.handlers.LoginHandler;
import ui.handlers.UIHandler;
import ui.player.login.LoginElement;
import util.observerobservable.VoogaObserver;


public class GameScreen extends GenericGameScreen 
	implements VoogaObserver<Map<Integer,FrontEndInformation>>, LoginElement{
	
	private ImageViewPane ivp;
	private UIHandler uihandler;
	private SimpleHUD hud;
	private Map<Integer, Actor> actorsMap;
	private GameScreen gs = this;
	private ScreenHandler screenHandler;
	private LoginHandler loginhandler;
	
	private void initializeScreenHandler() {
		screenHandler = new ScreenHandler(){
			@Override
			public void createActor(double x, double y, int option, ActorData actorData ) {
				Actor actor = new Actor(uihandler, screenHandler, option,actorData,ivp, actorsMap);
				actor.getPane().setLayoutX(getWidth() - x);
				actor.getPane().setLayoutY(y);
				getChildren().add(actor.getPane());
			}
			@Override
			public void showError(String msg) {
				Text error = new Text(msg);
				error.setStyle("-fx-font-size: 50; -fx-fill: red");
				HBox holder = new HBox(error);
				holder.setAlignment(Pos.CENTER);
				getChildren().add(holder);
				FadeTransition ft = (FadeTransition) fadeTransition(holder, 1.0, 0.);
				ft.setOnFinished(e -> getChildren().remove(holder));
				AnchorPane.setTopAnchor(holder, 20.);
				AnchorPane.setLeftAnchor(holder, 20.);
				AnchorPane.setRightAnchor(holder, 20.);
				AnchorPane.setBottomAnchor(holder, 20.);
			}
		};
	}
	
	public EventHandler<ActionEvent> getAction() {
		EventHandler<ActionEvent> backToLogin = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				uihandler.stop();
				loginhandler.returnToMain();
			}
		};
		System.out.println(gs.getMediaPlayer().getStatus());
		if(gs.getMediaPlayer().getStatus().equals(Status.PLAYING)) gs.getMediaPlayer().stop();
		return backToLogin;
	}
	
	public GameScreen(UIHandler uihandler, LoginHandler loginhandler) {
		super(uihandler, Optional.ofNullable(null), Optional.ofNullable(null), Optional.ofNullable(null));
		this.uihandler = uihandler;
		this.actorsMap = new HashMap<Integer, Actor>();
		this.ivp = this.getIVP();
		this.loginhandler = loginhandler;
		initializeScreenHandler();
		hud = uihandler.getSimpleHUD().get();
		setup();
		fadeTransition(this, .0, 1.);
	}
	
	private void setup() {
		setupPanels();
		setupHUD();
	}
	
	private void setupPanels() {
		SidePanel sidePanel = new SidePanel(screenHandler, uihandler.getOptions());
		AnchorPane.setRightAnchor(sidePanel.getSidePane(), 10.0);
		this.getChildren().add(sidePanel.getSidePane());
		addInternalPanesToRoot(sidePanel.getListOfPanes());
	}
	
	public void addInternalPanesToRoot(Collection<OptionsPane> listOfPanes) {
		listOfPanes.forEach(op -> {
			this.getChildren().add(op);
			AnchorPane.setRightAnchor(op, -op.getPrefWidth());
			op.setStyle(("-fx-background-color: MediumAquamarine;" + " -fx-border-radius: 10 0 0 10;"
					+ "-fx-background-radius: 10 0 0 10;"));
		});
	}
	
	private void setupHUD() {
		AnchorPane.setBottomAnchor(hud.getGrid(), 10.);
		AnchorPane.setLeftAnchor(hud.getGrid(), 10.);
		this.getChildren().add(hud.getGrid());
	}
	
	private Transition fadeTransition(Node n, double from, double to) {
		FadeTransition ft = new FadeTransition(Duration.millis(1000), n);
		ft.setFromValue(from);
		ft.setToValue(to);
		ft.play();
		return ft;
	}

	@Override
	public void update(Map<Integer, FrontEndInformation> arg) {
		actorsMap.keySet().removeIf(id -> {
			if(arg.containsKey(id)) {
				return false;
			}
			this.getChildren().remove(actorsMap.get(id).getPane());
			//actorsMap.get(id).deleteActor();
			return true;
		});
		
		arg.keySet().stream().forEach(id -> {
			Integer actorOption = arg.get(id).getActorOption();
			if(!actorsMap.containsKey(id)) {
				Actor newActor = new Actor(uihandler, screenHandler, actorOption, uihandler.getOptions().get(actorOption), ivp, actorsMap);
				actorsMap.put(id, newActor);
				this.getChildren().add(newActor.getPane());
			}
			Actor actor = actorsMap.get(id);
			double xCoor = util.Transformer.ratioToCoordinate(arg.get(id).getActorLocation().getX(), (ivp.getWidth() - ivp.getImageInsets().x));
			double yCoor = util.Transformer.ratioToCoordinate(arg.get(id).getActorLocation().getY(), (ivp.getHeight() - ivp.getImageInsets().y));
			actor.getPane().setLayoutX(xCoor);
			actor.getPane().setLayoutY(yCoor);
			//System.out.println("Layout: " + actor.getActor().getLayoutX() + " " + xCoor + " " + actor.getActor().getLayoutY() + " " + yCoor);
		});
	}
}