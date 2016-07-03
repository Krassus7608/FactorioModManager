package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Configuration;
import main.Main;
import modhandler.Mod;
import modhandler.ModPack;

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
	private Pane infoBox;
	private VBox modsInfoBox;
	private VBox modPackBox, modPackInfoBox;
	private TableView<Mod> modPackModsTable;
	private ListView<Mod> modsTabList;
	private ListView<ModPack> modPackTabList;
	
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
		this.infoBox = new Pane();
		
		this.modPackList = new TabPane();
		this.modPackList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>()
		{
			        @Override
			        public void changed(ObservableValue<? extends Tab> ov, Tab oldTab, Tab newTab)
			        {
			        	if(newTab.getText().equals(Gui.this.modPackTab.getText())){ Gui.this.showModPackInfoBox(); }
			        	else{ Gui.this.showModsInfoBox(); }
			        }
		});
		
		this.modPackTab = new Tab(Main.lang.modPackTab);
		this.modPackTab.setClosable(false);
		this.modPackTabList = new ListView<>();
		//this.modPackTabList.setItems();
		this.modPackTab.setContent(this.modPackTabList);
		
		this.modsTab = new Tab(Main.lang.modsTab);
		this.modsTab.setClosable(false);
		this.modsTabList = new ListView<>();
		//this.modsTabList.setItems();
		this.modsTab.setContent(this.modsTabList);
		
		this.modPackList.getTabs().addAll(this.modPackTab, this.modsTab);
		
		this.modsInfoBox = new VBox();
		this.modsInfoBox.getChildren().addAll();
		
		this.modPackBox = new VBox();
		this.modPackInfoBox = new VBox();
		this.modPackModsTable = new TableView<>();
		this.modPackBox.getChildren().addAll(this.modPackInfoBox, this.modPackModsTable);
		
		this.infoBox.getChildren().add(this.modPackBox);
		this.mainBox.getChildren().addAll(this.modPackList, this.infoBox);
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
	 * 
	 */
	private void showModPackInfoBox()
	{
		this.infoBox.getChildren().clear();
		this.infoBox.getChildren().add(this.modPackBox);
	}
	
	private void showModsInfoBox()
	{
		this.infoBox.getChildren().clear();
		this.infoBox.getChildren().add(this.modsInfoBox);
	}
	
	/*
	 * Exit-Function
	 */
	private void exit()
	{
		
	}
}
