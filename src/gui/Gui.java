package gui;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Configuration;
import main.Main;

public class Gui
{
	/*
	 * MainStage
	 */
	private Stage mainStage;
	private Scene scene;
	private VBox root;
	
	/*
	 * MenuItems
	 */
	private MenuBar menu;
	private Menu fileMenu, editMenu, helpMenu;
	private MenuItem fileMenuLoad, fileMenuSave, fileMenuExit;
	private MenuItem editMenuUndo, editMenuRedo, editMenuView, editMenuSettings;
	private MenuItem helpMenuAbout;
	
	/*
	 * ModPack-/Mod-List
	 */
	
	/*
	 * Description
	 */
	
	public Gui(Stage mainStage)
	{
		//MainStage
		this.mainStage = mainStage;
		this.root = new VBox();
		this.scene = new Scene(this.root, 720,548);
		this.mainStage.setScene(this.scene);
		
		
		//---------- Menu ----------//
		this.menu = new MenuBar();
		this.fileMenu = new Menu(Main.lang.menuFile);
		this.editMenu = new Menu(Main.lang.menuEdit);
		this.helpMenu = new Menu(Main.lang.menuHelp);
		
		this.fileMenuLoad = new MenuItem(Main.lang.menuFileLoad);
		this.fileMenuLoad.setOnAction(e -> { Gui.this.load(); });
		this.fileMenuSave = new MenuItem(Main.lang.menuFileSave);
		this.fileMenuLoad.setOnAction(e -> { Gui.this.save(); });
		this.fileMenuExit = new MenuItem(Main.lang.menuFileExit);
		this.fileMenuLoad.setOnAction(e -> { Gui.this.exit(); });
		
		this.editMenuUndo = new MenuItem(Main.lang.menuEditUndo);
		this.editMenuRedo = new MenuItem(Main.lang.menuEditRedo);
		this.editMenuView = new MenuItem(Main.lang.menuEditView);
		this.editMenuSettings = new MenuItem(Main.lang.menuEditSettings);
		
		this.helpMenuAbout = new MenuItem(Main.lang.menuHelpAbout);
		
		this.fileMenu.getItems().addAll(this.fileMenuLoad, this.fileMenuSave, this.fileMenuExit);
		this.editMenu.getItems().addAll(this.editMenuUndo, this.editMenuRedo, this.editMenuView, this.editMenuSettings);
		this.helpMenu.getItems().addAll(this.helpMenuAbout);
		this.menu.getMenus().addAll(this.fileMenu, this.editMenu, this.helpMenu);
		//--------------------------//


		this.root.getChildren().addAll(this.menu);
		this.mainStage.setTitle(Configuration.name);
		//this.mainStage.getIcons().add(Configuration.taskIcon);
		this.mainStage.show();
	}
	
	/*
	 * Load-/Save-Dialogue
	 */
	private void load()
	{
		//Main.modHandler.refresh();
	}
	
	private void save()
	{
		//Main.modHandler.save();
	}
	
	/*
	 * Exit-Function
	 */
	private void exit()
	{
		
	}
}
