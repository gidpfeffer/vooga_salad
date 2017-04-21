package ui.player.inGame;

import java.io.File;
import java.util.Optional;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import ui.general.ToggleSwitch;

public class MusicPlayer {

	private Media song;
	private MediaPlayer mediaPlayer;
	static final String heroSong = "data/resource/hero_song.mp3";
	
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	
	public MusicPlayer(ToggleSwitch musicToggle, Optional<String> audioString) {
		song = new Media(new File(audioString.orElse(heroSong)).toURI().toString());
		mediaPlayer = new MediaPlayer(song);
		setupAudio(musicToggle);
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
}
