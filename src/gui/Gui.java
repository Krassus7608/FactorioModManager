package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.application.HostServices;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.Configuration;
import main.Main;
import modhandler.Mod;
import modhandler.ModPack;
import modhandler.ModRelease;

public class Gui
{
	/*
	 * MainStage
	 */
	private Stage mainStage;
	private Scene scene;
	private VBox root;
	private HostServices hostServices;
	
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
	private VBox modsBox, modsInfoBox, modsReleaseTableBox;
	private TableView<ModRelease> modsReleaseTable;
	private VBox modPackBox, modPackInfoBox;
	private TableView<Mod> modPackModsTable;
	private ListView<Mod> modsTabList;
	private ListView<ModPack> modPackTabList;
	
	/*
	 * Labels/TextAreas for ModPack-/Mod-InfoBoxes
	 */
	private Label modInfoID;
	private HBox modInfoURLBox;
	private Label modInfoURL;
	private Hyperlink modInfoURLLink;
	private Label modInfoCategories;
	private HBox modInfoAuthorBox;
	private Label modInfoAuthor;
	private Hyperlink modInfoAuthorLink;
	private HBox modInfoContactBox;
	private Label modInfoContact;
	private Hyperlink modInfoContactLink;
	private Label modInfoTitle;
	private Label modInfoName;
	private Label modInfoDescriptionLabel;
	private TextArea modInfoDescription;
	private HBox modInfoHomepageBox;
	private Label modInfoHomepage;
	private Hyperlink modInfoHomepageLink;
	
	private Downloader dl;
	
	@SuppressWarnings("unchecked")
	public Gui(Stage mainStage, HostServices hostServices)
	{	
		//---------- MainStage ----------//
		this.mainStage = mainStage;
		this.hostServices = hostServices;
		this.root = new VBox();
		this.scene = new Scene(this.root, 720,587);
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
		this.editMenuSettings.setOnAction(e -> { Gui.this.dl.download(); });
		
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
		this.infoBox.prefWidthProperty().bind(this.root.widthProperty().divide(3).multiply(2));
		this.infoBox.prefHeightProperty().bind(this.root.heightProperty().subtract(this.menu.heightProperty()));
		
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
		this.modsTabList.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent mE)
			{
				Mod selected = Gui.this.modsTabList.getSelectionModel().getSelectedItem();
				Gui.this.modInfoID.setText(Main.lang.modID + ": " + selected.ID);
				Gui.this.modInfoURLLink.setText(selected.url);
				Gui.this.modInfoCategories.setText(Main.lang.modCategories + ": " + selected.getCategories());
				Gui.this.modInfoAuthorLink.setText(selected.author);
				Gui.this.modInfoContactLink.setText(selected.contact);
				if(selected.contact.length() == 0){ Gui.this.modInfoContactLink.setDisable(true); }
				Gui.this.modInfoTitle.setText(Main.lang.modTitle + ": " + selected.title);
				Gui.this.modInfoName.setText(Main.lang.modName + ": " + selected.name);
				Gui.this.modInfoHomepageLink.setText(selected.homepage);
				if(selected.homepage.length() == 0){ Gui.this.modInfoHomepageLink.setDisable(true); }
				Gui.this.modInfoDescription.setText(selected.description);
				Gui.this.modsReleaseTable.setItems(FXCollections.observableArrayList(selected.releases));
			}
		});
		this.modsTab.setContent(this.modsTabList);
		this.modPackList.prefWidthProperty().bind(this.root.widthProperty().divide(3));
		this.modPackList.prefHeightProperty().bind(this.root.heightProperty().subtract(this.menu.heightProperty()));
		this.modPackList.getTabs().addAll(this.modPackTab, this.modsTab);
		//---------------------------------------//
		
		
		//---------- ModPack-/Mod-InfoBox ----------//
		//ModInfo
		this.modsBox = new VBox();
		this.modsBox.prefWidthProperty().bind(this.infoBox.widthProperty());
		this.modsBox.prefHeightProperty().bind(this.infoBox.heightProperty());
		
		this.modsInfoBox = new VBox();
		this.modsInfoBox.prefWidthProperty().bind(this.modsBox.widthProperty());
		this.modsInfoBox.prefHeightProperty().bind(this.modsBox.heightProperty().divide(4).multiply(3));
		this.modsInfoBox.setPadding(new Insets(0,10,0,10));
		this.modInfoID = new Label(Main.lang.modID + ": 1234");
		this.modInfoID.setFont(Font.font("Lucida Sans Unicode", 13));
		this.modInfoTitle = new Label(Main.lang.modTitle + ": TestMod");
		this.modInfoTitle.setFont(Font.font("Lucida Sans Unicode", 13));
		this.modInfoName = new Label(Main.lang.modName + ": test_mod");
		this.modInfoName.setFont(Font.font("Lucida Sans Unicode", 13));
		this.modInfoCategories = new Label(Main.lang.modCategories + ": gameplay, manufacturing, test, others");
		this.modInfoCategories.setFont(Font.font("Lucida Sans Unicode", 13));
		this.modInfoAuthorBox = new HBox();
		this.modInfoAuthor = new Label(Main.lang.modAuthor + ": ");
		this.modInfoAuthor.setFont(Font.font("Lucida Sans Unicode", 13));
		this.modInfoAuthorLink = new Hyperlink("Krassus");
		this.modInfoAuthorLink.setFont(Font.font("Lucida Sans Unicode", 13));
		this.modInfoAuthorLink.setOnAction(e -> { Gui.this.hostServices.showDocument("http://www.factoriomods.com/authors/" + Gui.this.modInfoAuthorLink.getText()); });
		this.modInfoAuthorBox.setAlignment(Pos.BASELINE_LEFT);
		this.modInfoAuthorBox.getChildren().addAll(this.modInfoAuthor, this.modInfoAuthorLink);
		this.modInfoContactBox = new HBox();
		this.modInfoContact = new Label(Main.lang.modContact + ": ");
		this.modInfoContact.setFont(Font.font("Lucida Sans Unicode", 13));
		this.modInfoContactLink = new Hyperlink("keinen@contact.de");
		this.modInfoContactLink.setFont(Font.font("Lucida Sans Unicode", 13));
		this.modInfoContactLink.setOnAction(e -> { Gui.this.hostServices.showDocument(Gui.this.modInfoContactLink.getText()); });
		this.modInfoContactBox.setAlignment(Pos.BASELINE_LEFT);
		this.modInfoContactBox.getChildren().addAll(this.modInfoContact, this.modInfoContactLink);
		this.modInfoURLBox = new HBox();
		this.modInfoURL = new Label(Main.lang.modURL + ": ");
		this.modInfoURL.setFont(Font.font("Lucida Sans Unicode", 13));
		this.modInfoURLLink = new Hyperlink("http://www.factoriomods.com/mods/testmod");
		this.modInfoURLLink.setFont(Font.font("Lucida Sans Unicode", 13));
		this.modInfoURLLink.setOnAction(e -> { Gui.this.hostServices.showDocument(Gui.this.modInfoURLLink.getText()); });
		this.modInfoURLBox.setAlignment(Pos.BASELINE_LEFT);
		this.modInfoURLBox.getChildren().addAll(this.modInfoURL, this.modInfoURLLink);
		this.modInfoHomepageBox = new HBox();
		this.modInfoHomepage = new Label(Main.lang.modHomepage + ": ");
		this.modInfoHomepage.setFont(Font.font("Lucida Sans Unicode", 13));
		this.modInfoHomepageLink = new Hyperlink("https://www.testmod.com");
		this.modInfoHomepageLink.setFont(Font.font("Lucida Sans Unicode", 13));
		this.modInfoHomepageLink.setOnAction(e -> { Gui.this.hostServices.showDocument(Gui.this.modInfoHomepageLink.getText()); });
		this.modInfoHomepageBox.setAlignment(Pos.BASELINE_LEFT);
		this.modInfoHomepageBox.getChildren().addAll(this.modInfoHomepage, this.modInfoHomepageLink);
		this.modInfoDescriptionLabel = new Label(Main.lang.modDescription + ":");
		this.modInfoDescriptionLabel.setFont(Font.font("Lucida Sans Unicode", 13));
		this.modInfoDescription = new TextArea("Hier steht die Beschreibung vom Testmod drin!");
		this.modInfoDescription.setFont(Font.font("Lucida Sans Unicode", 13));
		this.modInfoDescription.setEditable(false);
		
		this.modsInfoBox.getChildren().addAll(this.modInfoID, this.modInfoTitle, this.modInfoName, this.modInfoCategories, this.modInfoAuthorBox, this.modInfoContactBox, this.modInfoURLBox, this.modInfoHomepageBox, this.modInfoDescriptionLabel, this.modInfoDescription);
		
		this.modsReleaseTableBox = new VBox();
		this.modsReleaseTableBox.prefWidthProperty().bind(this.modsBox.widthProperty());
		this.modsReleaseTableBox.prefHeightProperty().bind(this.modsBox.heightProperty().divide(4));
		this.modsReleaseTableBox.setPadding(new Insets(10));
		
		this.modsReleaseTable = new TableView<>();
		TableColumn<ModRelease, String> mRID = new TableColumn<>(Main.lang.modID);
		mRID.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ModRelease, String>, ObservableValue<String>>(){ public ObservableValue<String> call(TableColumn.CellDataFeatures<ModRelease, String> arg0) {if(arg0.getValue() != null){ return new SimpleStringProperty(Integer.toString(arg0.getValue().ID)); }else{ return new SimpleStringProperty("-1"); } } });
		TableColumn<ModRelease, String> mRVersion = new TableColumn<>(Main.lang.modVersion);
		mRVersion.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ModRelease, String>, ObservableValue<String>>(){ public ObservableValue<String> call(TableColumn.CellDataFeatures<ModRelease, String> arg0) {if(arg0.getValue() != null){ return new SimpleStringProperty(arg0.getValue().version); }else{ return new SimpleStringProperty("n/A"); } } });
		TableColumn<ModRelease, String> mRDate = new TableColumn<>(Main.lang.otherDate);
		mRDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ModRelease, String>, ObservableValue<String>>(){ public ObservableValue<String> call(TableColumn.CellDataFeatures<ModRelease, String> arg0) {if(arg0.getValue() != null){ return new SimpleStringProperty(arg0.getValue().release.toString()); }else{ return new SimpleStringProperty("n/A"); } } });
		TableColumn<ModRelease, String> mRGameVersion = new TableColumn<>(Main.lang.modGameVersion);
		mRGameVersion.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ModRelease, String>, ObservableValue<String>>(){ public ObservableValue<String> call(TableColumn.CellDataFeatures<ModRelease, String> arg0) {if(arg0.getValue() != null){ return new SimpleStringProperty(utils.StringUtils.arrToString(arg0.getValue().gameVersions, ",")); }else{ return new SimpleStringProperty("n/A"); } } });
		TableColumn<ModRelease, String> mRDepend = new TableColumn<>(Main.lang.modDepend);
		mRDepend.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ModRelease, String>, ObservableValue<String>>(){ public ObservableValue<String> call(TableColumn.CellDataFeatures<ModRelease, String> arg0) {if(arg0.getValue() != null){ return new SimpleStringProperty(utils.StringUtils.arrToString(arg0.getValue().dependencies, ",")); }else{ return new SimpleStringProperty("n/A"); } } });
		TableColumn<ModRelease, String> mRFiles = new TableColumn<>(Main.lang.otherFiles);
		mRFiles.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ModRelease, String>, ObservableValue<String>>(){ public ObservableValue<String> call(TableColumn.CellDataFeatures<ModRelease, String> arg0) {if(arg0.getValue() != null){ return new SimpleStringProperty(Integer.toString(arg0.getValue().files.length)); }else{ return new SimpleStringProperty("-1"); } } });
        this.modsReleaseTable.getColumns().addAll(mRID,mRVersion,mRDate,mRGameVersion,mRDepend,mRFiles);
        this.modsReleaseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		this.modsReleaseTableBox.getChildren().add(this.modsReleaseTable);
		
		this.modsBox.getChildren().addAll(this.modsInfoBox, this.modsReleaseTableBox);
		
		//Modpack-Info
		this.modPackBox = new VBox();
		
		this.modPackInfoBox = new VBox();
		this.modPackInfoBox.getChildren().addAll();
		
		this.modPackModsTable = new TableView<>();
		
		this.modPackBox.getChildren().addAll(this.modPackInfoBox, this.modPackModsTable);
		
		
		this.infoBox.getChildren().add(this.modPackBox);
		Line splitter = new Line();
		splitter.setStrokeWidth(5);
		splitter.setStroke(Color.GREY.deriveColor(0, 1, 1, 0.5));
		splitter.setStrokeLineCap(StrokeLineCap.SQUARE);
		this.mainBox.getChildren().addAll(this.modPackList, this.infoBox);
		//------------------------------------------//


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
		if(this.infoBox != null && this.modsBox != null)
		{
			this.infoBox.getChildren().clear();
			this.infoBox.getChildren().add(this.modsBox);
		}
	}
	
	/*
	 * Close-Method
	 */
	public void close()
	{
		this.mainStage.close();
	}
	
	/*
	 * Download-GUI
	 */
	public static boolean dlGui(Mod m)
	{
		Stage stage = new Stage();
		VBox root = new VBox();
		root.setPadding(new Insets(10));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		Label header = new Label(Main.lang.choseFileDownload);
		root.getChildren().add(header);
		
		return true;
	}
}
