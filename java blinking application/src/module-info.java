module HelloFX {
	requires javafx.controls;
	requires javafx.media;
	requires javafx.graphics;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}
