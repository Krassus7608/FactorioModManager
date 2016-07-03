package gui;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Configuration;
import main.Main;
import modhandler.Mod;

public class Gui
{
	/*
	 * MainStage
	 */
	private Stage mainStage;
	private Scene scene;
	private VBox root;
	
	/*
	 * AboutWindow
	 */
	private Stage aboutStage;
	private Scene aboutScene;
	private HBox aboutRoot;
	
	/*
	 * MenuItems
	 */
	private MenuBar menu;
	private Menu fileMenu, editMenu, helpMenu;
	private MenuItem fileMenuOpen, fileMenuExit;
	private MenuItem editMenuUndo, editMenuRedo, editMenuView, editMenuSettings;
	private MenuItem helpMenuAbout;
	
	/*
	 * ModPack-/Mod-List
	 */
	private HBox mainBox;
	private TabPane modPackList;
	private Tab modPackTab, modsTab;
	private VBox modInfoBox;
	private VBox modPackInfoBox;
	private TableView<Mod> modInfoTable;
	
	/*
	 * Description
	 */
	
	public Gui(Stage mainStage)
	{
		//---------- MainStage ----------//
		this.mainStage = mainStage;
		this.root = new VBox();
		this.scene = new Scene(this.root, 720,548);
		this.mainStage.setScene(this.scene);
		//-------------------------------//
		
		
		//---------- AboutStage ----------//
		this.aboutStage = new Stage();
		this.aboutRoot = new HBox();
		this.aboutScene = new Scene(this.aboutRoot);
		this.aboutStage.setTitle(Main.lang.menuHelpAbout);
		this.aboutStage.setScene(this.aboutScene);
		this.aboutStage.getIcons().add(Configuration.icon);
		//-------------------------------//
		
		
		//---------- Menu ----------//
		this.menu = new MenuBar();
		this.fileMenu = new Menu(Main.lang.menuFile);
		this.editMenu = new Menu(Main.lang.menuEdit);
		this.helpMenu = new Menu(Main.lang.menuHelp);
		
		this.fileMenuOpen = new MenuItem(Main.lang.menuFileOpen);
		this.fileMenuOpen.setOnAction(e -> { Gui.this.open(); });
		this.fileMenuExit = new MenuItem(Main.lang.menuFileExit);
		this.fileMenuExit.setOnAction(e -> { Gui.this.exit(); });
		
		this.editMenuUndo = new MenuItem(Main.lang.menuEditUndo);
		this.editMenuRedo = new MenuItem(Main.lang.menuEditRedo);
		this.editMenuView = new MenuItem(Main.lang.menuEditView);
		this.editMenuSettings = new MenuItem(Main.lang.menuEditSettings);
		
		this.helpMenuAbout = new MenuItem(Main.lang.menuHelpAbout);
		this.helpMenuAbout.setOnAction(e -> { Gui.this.aboutStage.showAndWait(); });
		
		this.fileMenu.getItems().addAll(this.fileMenuOpen, this.fileMenuExit);
		this.editMenu.getItems().addAll(this.editMenuUndo, this.editMenuRedo, this.editMenuView, this.editMenuSettings);
		this.helpMenu.getItems().addAll(this.helpMenuAbout);
		this.menu.getMenus().addAll(this.fileMenu, this.editMenu, this.helpMenu);
		//--------------------------//
		
		
		//---------- ModPack-/Mod-List ----------//
		this.mainBox = new HBox();
		
		this.modPackList = new TabPane();
		this.modPackTab = new Tab(Main.lang.modPackTab);
		this.modPackTab.setClosable(false);
		this.modsTab = new Tab(Main.lang.modsTab);
		this.modsTab.setClosable(false);
		this.modPackList.getTabs().addAll(this.modPackTab, this.modsTab);
		
		this.mainBox.getChildren().addAll(this.modPackList);
		//---------------------------------------//


		this.root.getChildren().addAll(this.menu, this.mainBox);
		this.mainStage.setTitle(Configuration.name);
		this.mainStage.getIcons().add(Configuration.icon);
		this.mainStage.show();
	}
	
	/*
	 * Open mod directory in Explorer
	 */
	private void open()
	{
		
	}
	
	/*
	 * Exit-Function
	 */
	private void exit()
	{
		
	}
}
