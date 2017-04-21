package ui.player.inGame;

import java.io.File;
import java.util.Optional;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import ui.general.ToggleSwitch;

/**
 * @author Anngelyque
 * @author Anh
 *
 */
public class MusicPlayer {

	private VBox layout; 
	private CheckBox checkbox;
	private HBox component1; 
	private Media song;
	private MediaPlayer mediaPlayer;
	private ToggleSwitch mySwitch;
	private Slider volumeSlider;
	static final String heroSong = "data/resource/hero_song.mp3";
	
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	
	public MusicPlayer(Optional<String> audioString) {
		layout = new VBox();
		component1 = new HBox();
		
		mySwitch = new ToggleSwitch("Music");
		song = new Media(new File(audioString.orElse(heroSong)).toURI().toString());
		mediaPlayer = new MediaPlayer(song);
		mediaPlayer.play();
		
		
		checkbox = new CheckBox("Mute");
		checkbox.setOnAction(e -> mediaPlayer.setMute(!mediaPlayer.isMute()));
		
		setupAudio(mySwitch);
		
		
		Label volumeLabel = new Label("Vol: ");
		volumeSlider = new Slider();
		setupVolumeSlider();
		
		setupMusicPlayer();
		
		component1.getChildren().addAll(volumeLabel,volumeSlider);
		layout.getChildren().addAll(checkbox,component1);
	}
	
	private void setupMusicPlayer() {
		// TODO Auto-generated method stub
        mediaPlayer.setOnReady(new Runnable() {
            public void run() {
                if (!volumeSlider.isValueChanging()) {
		            volumeSlider.setValue((int)Math.round(mediaPlayer.getVolume() 
		                  * 100));
		          }
            }
        });
 
 
        
		
	}

	private void setupVolumeSlider() {
		// TODO Auto-generated method stub       
		volumeSlider.setPrefWidth(90);
		volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
		volumeSlider.setMinWidth(20);
		volumeSlider.valueProperty().addListener(new InvalidationListener() {
		    public void invalidated(Observable ov) {
		       if (volumeSlider.isValueChanging()) {
		           mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
		       }
		    }
		});
		
	}

	private void setupAudio(ToggleSwitch musicToggle) {
		mediaPlayer.setMute(true);
		mediaPlayer.play();
		Runnable repeat = () -> {
			mediaPlayer.play();
		};
		mediaPlayer.setOnRepeat(repeat);
		if (musicToggle.switchOnProperty()) playMusic();
		Runnable music = () -> {
			playMusic();
		};
		musicToggle.setListener(music);
	}
	
	private void playMusic() {
		mediaPlayer.setMute(!mediaPlayer.isMute());
	}
	
	public Node getNode(){
		return layout;
	}
}
