package main;

import gui.Gui;
import javafx.application.Application;
import javafx.stage.Stage;
import modhandler.ModHandler;

public class Main extends Application
{
	public static final Configuration config = new Configuration();
	public static final Language lang = new Language(Main.config.langFile);
	public static final ModHandler modHandler = new ModHandler();
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		new Gui(primaryStage, getHostServices());
	}
	
	public static void exit(Gui g)
	{
		g.close();
		/*
		 * perform save actions
		 */
		//Main.config.storeConfig();
		//Main.modHandler.storeMods();
	}
}