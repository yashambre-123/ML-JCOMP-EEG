package application;
	
import javafx.application.Application ;
import javafx.scene.Parent;
import javafx.scene.Scene;  
import javafx.scene.media.Media;  
import javafx.scene.media.MediaPlayer;  
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;  
import javafx.event.* ;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.* ;
import javafx.scene.layout.* ;
import javafx.geometry.* ;
import java.io.* ;

public class Main extends Application {
	public static void main (String[] args) {
		launch(args) ;
	}
	public void start (Stage stage) throws Exception {
		try {

			String path8 = "D:/ML PROJ/8Hz.mp4" ;
			String path9 = "D:/ML PROJ/9Hz.mp4" ;
			String path10 = "D:/ML PROJ/10Hz.mp4" ; 
			String path11 = "D:/ML PROJ/11Hz.mp4" ;
			String path12 = "D:/ML PROJ/12Hz.mp4" ;
			String path13= "D:/ML PROJ/13Hz.mp4" ;
			String path14 = "D:/ML PROJ/14Hz.mp4" ; 
			String path15 = "D:/ML PROJ/15Hz.mp4" ;
	
			Media media8 = new Media(new File(path8).toURI().toString());  
			Media media9 = new Media(new File(path9).toURI().toString()); 
			Media media10 = new Media(new File(path10).toURI().toString()); 
			Media media11 = new Media(new File(path11).toURI().toString()); 
			Media media12 = new Media(new File(path12).toURI().toString()); 
			Media media13 = new Media(new File(path13).toURI().toString()); 
			Media media14= new Media(new File(path14).toURI().toString()); 
			Media media15 = new Media(new File(path15).toURI().toString()); 
			
			MediaPlayer mediaplayer8 = new MediaPlayer(media8) ;
			MediaPlayer mediaplayer9 = new MediaPlayer(media9) ;
			MediaPlayer mediaplayer10 = new MediaPlayer(media10) ;
			MediaPlayer mediaplayer11 = new MediaPlayer(media11) ;
			MediaPlayer mediaplayer12 = new MediaPlayer(media12) ;
			MediaPlayer mediaplayer13 = new MediaPlayer(media13) ;
			MediaPlayer mediaplayer14 = new MediaPlayer(media14) ;
			MediaPlayer mediaplayer15 = new MediaPlayer(media15) ;
		
			MediaView mediaview8  = new MediaView(mediaplayer8);  
			MediaView mediaview9  = new MediaView(mediaplayer9);  
			MediaView mediaview10 = new MediaView(mediaplayer10);  
			MediaView mediaview11 = new MediaView(mediaplayer11);  
			MediaView mediaview12 = new MediaView(mediaplayer12);  
			MediaView mediaview13 = new MediaView(mediaplayer13);  
			MediaView mediaview14 = new MediaView(mediaplayer14);  
			MediaView mediaview15 = new MediaView(mediaplayer15); 
			
			mediaplayer8.setAutoPlay(true) ;
			mediaplayer9.setAutoPlay(true) ;
			mediaplayer10.setAutoPlay(true) ;
			mediaplayer11.setAutoPlay(true) ;
			mediaplayer12.setAutoPlay(true) ;
			mediaplayer13.setAutoPlay(true) ;
			mediaplayer14.setAutoPlay(true) ;
			mediaplayer15.setAutoPlay(true) ;

			Text up = new Text("up") ;
			Text down = new Text("down") ;
			Text left = new Text("left") ;
			Text right = new Text("right") ;
			Text front = new Text("front"); 
			Text back = new Text("back") ;
			Text gripopen = new Text("gripper open") ;
			Text gripclose = new Text("gripper close") ;

			//GridPane gridpane = new GridPane () ;
			//gridpane.setAlignment(Pos.CENTER) ;
			//gridpane.addRow(0, mediaview8, mediaview9, mediaview10, mediaview11) ;
			//gridpane.addRow(1, up, down, left, right) ;
			//gridpane.addRow(2, mediaview12, mediaview13, mediaview14, mediaview15) ;
			//gridpane.addRow(3, front, back, gripopen, gripclose) ;
			
			Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml")) ;
			Scene scene = new Scene(root) ;
			stage.setScene(scene) ;
			stage.setTitle("ML PROJECT") ;
			stage.show() ;


	

		}catch(Exception e){
			System.out.println("Exception: " + e.getMessage()) ;
		}
	}

}                                                                                                                                                                                               