package gui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Configuration;

public class Gui
{
	/*
	 * MainStage elements
	 */
	private Stage mainStage;
	private Scene scene;
	private BorderPane root;
	
	public Gui(Stage mainStage)
	{
		this.mainStage = mainStage;
		this.root = new BorderPane();
		this.scene = new Scene(this.root, 720,548);
		this.mainStage.setScene(this.scene);
		this.mainStage.setTitle(Configuration.name);
		//this.stage.getIcons().add(Configuration.windowIcon);
	}
}
