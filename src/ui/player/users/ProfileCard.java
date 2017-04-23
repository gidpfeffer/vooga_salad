package ui.player.users;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProfileCard {

	private HBox card;
	private User user;
	private ResourceBundle profileRB;
	private ExperienceGrid expGrid;
	private GameStatsGrid gsGrid;

	
	public User getUser() {
		return user;
	}
	
	public HBox getCard() {
		return card;
	}
	public ProfileCard(String rb, User user, String css) {
		card = new HBox(20);
		card.setPadding(new Insets(20, 20, 20, 20));
		card.getStylesheets().add(css);
		card.setId("hbox");
		this.user = user;
		this.profileRB = ResourceBundle.getBundle(rb);
		setup();
	}

	private void setup() {
		imageAndExperince();
		gameStats();
	}

	private void imageAndExperince() {
		VBox vb = new VBox(20);
		ImageView profilePicture = new ImageView(new Image(user.getProfilePicture(), 150, 150, false, true));
		expGrid = new ExperienceGrid(profileRB);
		setupExpGridValues();
		vb.getChildren().addAll(profilePicture, expGrid.getGrid());
		vb.setAlignment(Pos.CENTER);
		card.getChildren().add(vb);
	}

	private void setupExpGridValues() {
		expGrid.getRank().setText(user.getRank());
		expGrid.getExperience().setText(user.getExperience().toString());
		expGrid.getLevel().setText(user.getLevel().toString());
		expGrid.getEntryMap().keySet().forEach(entry -> {
			expGrid.getEntryMap().get(entry).setEditable(false);
			expGrid.getEntryMap().get(entry).setPrefWidth(100);
		});
	}

	private void gameStats() {
		gsGrid = new GameStatsGrid(profileRB);
		setupGSGridValues();
		gsGrid.getGrid().setAlignment(Pos.BOTTOM_CENTER);
		card.getChildren().add(gsGrid.getGrid());
		card.setAlignment(Pos.BOTTOM_CENTER);
	}

	private void setupGSGridValues() {
		gsGrid.getLastPlayed().setText(user.getLastPlayed());
		gsGrid.getMostPlayed().setText(user.getMostPlayed());
		gsGrid.getScore().setText(user.getHighScore().toString());
		gsGrid.getUsername().setText(user.getUsername());
		gsGrid.getEntryMap().keySet().forEach(entry -> {
			gsGrid.getEntryMap().get(entry).setEditable(false);
			gsGrid.getEntryMap().get(entry).setPrefWidth(100);
		});
	}

}
