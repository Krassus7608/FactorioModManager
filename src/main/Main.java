package main;

import gui.Gui;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
	public static final Configuration config = new Configuration();
	public static final Language lang = new Language(Main.config.langFile);
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Gui gui = new Gui(primaryStage);
	}

}
