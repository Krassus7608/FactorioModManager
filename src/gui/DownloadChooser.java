package gui;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Configuration;

public class DownloadChooser
{
	private Stage stage;
	private Scene scene;
	private ScrollPane root;
	private VBox itemBox;
	
	public DownloadChooser(ArrayList<ModDownloadItem> m)
	{
		this.root = new ScrollPane();
		this.scene = new Scene(this.root, 450, 700);
		this.stage = new Stage();
		this.stage.setScene(this.scene);
		this.stage.setTitle(Configuration.name);
		this.stage.initModality(Modality.APPLICATION_MODAL);
		this.stage.getIcons().add(Configuration.icon);
		
		for(ModDownloadItem x : m){ x.setOnClose(new EventHandler<ActionEvent>(){ @Override public void handle(ActionEvent event){ DownloadChooser.this.stage.close(); } }); }
		
		this.itemBox = new VBox();
		this.itemBox.getChildren().addAll(m);
		this.root.setContent(this.itemBox);
	}
	
	public void show()
	{
		this.stage.showAndWait();
	}
}