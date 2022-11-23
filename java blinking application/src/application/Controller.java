package application;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView; 
public class Controller implements Initializable{

	@FXML
	private MediaView mediaview8, mediaview9, mediaview10, 
	mediaview11, mediaview12, mediaview13, mediaview14, mediaview15 ;
	@FXML 
	private File file ;
	private Media media8, media9, media10,media11, media12, media13, media14, media15 ;
	private MediaPlayer mediaplayer8, mediaplayer9, mediaplayer10 ,
			mediaplayer11, mediaplayer12, mediaplayer13, mediaplayer14,
			mediaplayer15 ;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		String path8 = "D:/ML PROJ/8Hz.mp4" ;
		String path9 = "D:/ML PROJ/9Hz.mp4" ;
		String path10 = "D:/ML PROJ/10Hz.mp4" ; 
		String path11 = "D:/ML PROJ/11Hz.mp4" ;
		String path12 = "D:/ML PROJ/12Hz.mp4" ;
		String path13= "D:/ML PROJ/13Hz.mp4" ;
		String path14 = "D:/ML PROJ/14Hz.mp4" ; 
		String path15 = "D:/ML PROJ/15Hz.mp4" ;
		
		 media8 = new Media(new File(path8).toURI().toString());  
		 media9 = new Media(new File(path9).toURI().toString()); 
		 media10 = new Media(new File(path10).toURI().toString()); 
		 media11 = new Media(new File(path11).toURI().toString()); 
		 media12 = new Media(new File(path12).toURI().toString()); 
		 media13 = new Media(new File(path13).toURI().toString()); 
		 media14= new Media(new File(path14).toURI().toString()); 
		 media15 = new Media(new File(path15).toURI().toString()); 
		
		 mediaplayer8 = new MediaPlayer(media8) ;
		 mediaplayer9 = new MediaPlayer(media9) ;
		 mediaplayer10 = new MediaPlayer(media10) ;
		 mediaplayer11 = new MediaPlayer(media11) ;
		 mediaplayer12 = new MediaPlayer(media12) ;
		 mediaplayer13 = new MediaPlayer(media13) ;
		 mediaplayer14 = new MediaPlayer(media14) ;
		 mediaplayer15 = new MediaPlayer(media15) ;
		
		mediaview8.setMediaPlayer(mediaplayer8);
		mediaview9.setMediaPlayer(mediaplayer9);
		mediaview10.setMediaPlayer(mediaplayer10);
		mediaview11.setMediaPlayer(mediaplayer11);
		mediaview12.setMediaPlayer(mediaplayer12);
		mediaview13.setMediaPlayer(mediaplayer13);
		mediaview14.setMediaPlayer(mediaplayer14);
		mediaview15.setMediaPlayer(mediaplayer15);
	
		mediaplayer8.setAutoPlay(true) ;
		mediaplayer9.setAutoPlay(true) ;
		mediaplayer10.setAutoPlay(true) ;
		mediaplayer11.setAutoPlay(true) ;
		mediaplayer12.setAutoPlay(true) ;
		mediaplayer13.setAutoPlay(true) ;
		mediaplayer14.setAutoPlay(true) ;
		mediaplayer15.setAutoPlay(true) ;
		
	}

}
