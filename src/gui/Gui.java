package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.util.Callback;
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
		this.fileMenuExit.setOnAction(e -> { Main.exit(Gui.this); });
		
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
		this.mainBox.minHeightProperty().bind(this.mainStage.heightProperty());

		this.infoBox = new Pane();
		this.infoBox.prefWidthProperty().bind(this.mainBox.widthProperty().divide(3).multiply(2));
		
		this.modPackList = new TabPane();
		this.modPackList.prefWidthProperty().bind(this.mainBox.widthProperty().divide(3));
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
		this.modPackTabList.setItems(Main.modHandler.getModPacks());
		this.modPackTab.setContent(this.modPackTabList);
		
		this.modsTab = new Tab(Main.lang.modsTab);
		this.modsTab.setClosable(false);
		this.modsTabList = new ListView<>();
		this.modsTabList.setItems(Main.modHandler.getMods());
		this.modsTabList.setCellFactory(new Callback<ListView<Mod>, ListCell<Mod>>(){
            @Override
            public ListCell<Mod> call(ListView<Mod> p){
                ListCell<Mod> cell = new ListCell<Mod>(){
                    @Override
                    protected void updateItem(Mod t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null){ setText(t.name); }
                    }
                };
                return cell;
        }});
		this.modsTab.setContent(this.modsTabList);
		this.modPackList.getTabs().addAll(this.modPackTab, this.modsTab);
		
		//ModInfo
		this.modsInfoBox = new VBox();
		this.modsInfoBox.prefWidthProperty().bind(this.infoBox.widthProperty());
		this.modsInfoBox.prefHeightProperty().bind(this.infoBox.heightProperty());
		this.modsInfoBox.getChildren().addAll(new Label("Test: 123BlaBla"));
		
		//ModPack Info
		this.modPackBox = new VBox();
		this.modPackBox.prefWidthProperty().bind(this.infoBox.widthProperty());
		this.modPackBox.prefHeightProperty().bind(this.infoBox.heightProperty());
		
		this.modPackInfoBox = new VBox();
		this.modPackInfoBox.prefWidthProperty().bind(this.modPackBox.widthProperty());
		this.modPackInfoBox.prefHeightProperty().bind(this.modPackBox.heightProperty().divide(2));
		this.modPackInfoBox.getChildren().addAll(new Label("Test: 12345"));
		
		this.modPackModsTable = new TableView<>();
		this.modPackModsTable.prefWidthProperty().bind(this.modPackBox.widthProperty());
		this.modPackModsTable.prefHeightProperty().bind(this.modPackBox.heightProperty().divide(2));
		this.modPackModsTable.prefWidthProperty().bind(this.infoBox.widthProperty());
		
		this.modPackBox.getChildren().addAll(this.modPackInfoBox, this.modPackModsTable);
		
		this.infoBox.getChildren().add(this.modPackBox);
		Line splitter = new Line();
		splitter.startXProperty().bind(this.modPackList.widthProperty());
		splitter.setStartY(0.0);
		splitter.endXProperty().bind(this.modPackList.widthProperty());
		splitter.endYProperty().bind(this.mainStage.heightProperty());
		splitter.setStrokeWidth(5);
		splitter.setStroke(Color.GREY.deriveColor(0, 1, 1, 0.5));
		splitter.setStrokeLineCap(StrokeLineCap.SQUARE);
		this.mainBox.getChildren().addAll(this.modPackList, splitter, this.infoBox);
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
		try
		{
			Desktop.getDesktop().open(new File(Main.config.factorioModFolder + "xyz"));
		}
		catch (IOException | IllegalArgumentException e)
		{
			/*
			 * Log event
			 */
			utils.Error.popUpErrorDetailed(Main.lang.errorWindowTitle, Main.lang.failedToOpenDirMessage.replace("%dir%", Main.config.factorioModFolder + "xyz"), Main.lang.failedToOpenDirInfo.replace("%dir%", Main.config.factorioModFolder + "xyz"), e.getMessage(), x -> {});
		}
	}
	
	/*
	 * 
	 */
	private void showModPackInfoBox()
	{
		if(this.infoBox != null && this.modPackBox != null)
		{
			this.infoBox.getChildren().clear();
			this.infoBox.getChildren().add(this.modPackBox);
		}
	}
	
	private void showModsInfoBox()
	{
		if(this.infoBox != null && this.modPackBox != null)
		{
			this.infoBox.getChildren().clear();
			this.infoBox.getChildren().add(this.modsInfoBox);
		}
	}
	
	/*
	 * Close-Method
	 */
	public void close()
	{
		this.mainStage.close();
	}
}
