package ui.player.inGame;




import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

/**
 * 
 * @author Anh
 * Reference: //docs.oracle.com/javafx/2/media/overview.htm
 */

public class MediaControl{
	private VBox layout = new VBox(); 
	private HBox component = new HBox();
	private HBox component1 = new HBox();
	private MediaPlayer mp; 
	private Slider timeSlider;
	private Slider volumeSlider; 
	private boolean atEndOfMedia = false;
	private Button playButton;
    private boolean repeat = false;
    private boolean stopRequested = false;
    private Duration duration;
    private Button muteButton; 
    
    
	public MediaControl(MediaPlayer mp){
		this.mp  = mp; 
		setupPlayButton();
		component.getChildren().add(playButton);
		Label spacer = new Label("   ");
		component.getChildren().add(spacer);
		Label timeLabel = new Label("Time: ");
		component.getChildren().add(timeLabel);
		setupTimeSlider();
		component.getChildren().add(timeSlider);
		layout.getChildren().add(component);
		
		
		//muteButton = new Button("Mute");
		//setupMuteButton();
		//component1.getChildren().add(muteButton);
		Label spacer1 = new Label("   ");
		component1.getChildren().add(spacer1);
		Label volumeLabel = new Label("Vol: ");
		component1.getChildren().add(volumeLabel);
		setupVolume();
		component1.getChildren().add(volumeSlider);
		layout.getChildren().add(component1);
		 
	}
	
//	private void setupMuteButton() {
//		// TODO Auto-generated method stub
//		muteButton.setOnAction(e -> {
//			volumeSlider.setValue(0);
//		});
//		
//	}

	private void setupVolume() {
		// TODO Auto-generated method stub
		volumeSlider = new Slider();        
		volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
		volumeSlider.setMinWidth(20);
		volumeSlider.valueProperty().addListener(new InvalidationListener() {
		    public void invalidated(Observable ov) {
		       if (volumeSlider.isValueChanging()) {
		           mp.setVolume(volumeSlider.getValue() / 100.0);
		       }
		    }
		});
		
	}

	private void setupTimeSlider(){
		timeSlider = new Slider();
		HBox.setHgrow(timeSlider,Priority.ALWAYS);
		timeSlider.setMinWidth(20);
		timeSlider.setMaxWidth(Double.MAX_VALUE);
		timeSlider.valueProperty().addListener(new InvalidationListener() {
		    public void invalidated(Observable ov) {
		       if (timeSlider.isValueChanging()) {
		       // multiply duration by percentage calculated by slider position
		          mp.seek(duration.multiply(timeSlider.getValue() / 100.0));
		       }
		    }
		});
	}
	private void setupPlayButton() {
		// TODO Auto-generated method stub
		playButton = new Button(">");
		playButton.setOnAction(e -> {doPlay();});
		mp.currentTimeProperty().addListener(new InvalidationListener() 
        {
            public void invalidated(Observable ov) {
                updateValues();
            }
        });
 
        mp.setOnPlaying(new Runnable() {
            public void run() {
                if (stopRequested) {
                    mp.pause();
                    stopRequested = false;
                } else {
                    playButton.setText("||");
                }
            }
        });
 
        mp.setOnPaused(new Runnable() {
            public void run() {
                System.out.println("onPaused");
                playButton.setText(">");
            }
        });
 
        mp.setOnReady(new Runnable() {
            public void run() {
                duration = mp.getMedia().getDuration();
                updateValues();
            }
        });
 
        mp.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        mp.setOnEndOfMedia(new Runnable() {
            public void run() {
                if (!repeat) {
                    playButton.setText(">");
                    stopRequested = true;
                    atEndOfMedia = true;
                }
            }
       });
        
       
	}
	private void doPlay() {
		// TODO Auto-generated method stub
		Status status = mp.getStatus();
		  if (status == Status.UNKNOWN  || status == Status.HALTED)
	        {
	           return;
	        }
		  if ( status == Status.PAUSED
	             || status == Status.READY
	             || status == Status.STOPPED)
	          {
			  if (atEndOfMedia){
	             mp.seek(mp.getStartTime());
	             atEndOfMedia = false;
			  	}
	          mp.play();
	          }
		  	else {
	             mp.pause();
	          }

	}
	
	protected void updateValues() {
		  if (timeSlider != null && volumeSlider != null) {
		     Platform.runLater(new Runnable() {
		        public void run() {
		          Duration currentTime = mp.getCurrentTime();
		          //playTime.setText(formatTime(currentTime, duration));
		          timeSlider.setDisable(duration.isUnknown());
		          if (!timeSlider.isDisabled() 
		            && duration.greaterThan(Duration.ZERO) 
		            && !timeSlider.isValueChanging()) {
		              timeSlider.setValue(currentTime.divide(duration.toMillis()).toMillis()
		                  * 100.0);
		          }
		          if (!volumeSlider.isValueChanging()) {
		            volumeSlider.setValue((int)Math.round(mp.getVolume() 
		                  * 100));
		          }
		        }
		     });
		  }
		}
	

	
	public Node getNode(){
		return layout;
	}

}
