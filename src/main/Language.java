package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Language
{
	/*
	 * Final attributes
	 */
	//MenutItems
	public final String menuFile;
	public final String menuFileOpen;
	public final String menuFileExit;
	public final String menuEdit;
	public final String menuEditUndo;
	public final String menuEditRedo;
	public final String menuEditView;
	public final String menuEditSettings;
	public final String menuHelp;
	public final String menuHelpAbout;
	
	//Buttons
	public final String buttonOk;
	public final String buttonCancel;
	public final String buttonYes;
	public final String buttonNo;
	
	//TabPane
	public final String modPackTab;
	public final String modsTab;
	
	//Window titles
	public final String errorWindowTitle;
	public final String warningWindowTitle;
	public final String infoWindowTitle;
	
	//Error/Warning/Info messages
	public final String failedToOpenDirMessage;
	public final String failedToOpenDirInfo;
	public final String failedToOpenFileMessage;
	public final String failedToOpenFileInfo;
	
	//ModInfoItems
	public final String modID;
	public final String modTitle;
	public final String modURL;
	public final String modCategories;
	public final String modAuthor;
	public final String modContact;
	public final String modName;
	public final String modDescription;
	public final String modHomepage;
	
	/*
	 * Other attributes
	 */
	private static final int langItems = 1000;
	private File langFile;
	private String[] langArray;
	
	public Language(File langFile)
	{
		this.langFile = langFile;
		
		//prepare langArray
		this.langArray = new String[Language.langItems];
		
		//Load langFile
		this.loadLangFile();
		
		//set final attributes
		this.menuFile = this.langArray[0];
		this.menuEdit = this.langArray[1];
		this.menuHelp = this.langArray[2];
		this.menuFileOpen = this.langArray[3];
		this.menuFileExit = this.langArray[4];
		this.menuEditUndo = this.langArray[5];
		this.menuEditRedo = this.langArray[6];
		this.menuEditView = this.langArray[7];
		this.menuEditSettings = this.langArray[8];
		this.menuHelpAbout = this.langArray[9].replace("%name%",Configuration.name);;
		this.buttonOk = this.langArray[100];
		this.buttonCancel = this.langArray[101];
		this.buttonYes = this.langArray[102];
		this.buttonNo = this.langArray[103];
		this.modPackTab = this.langArray[200];
		this.modsTab = this.langArray[201];
		this.errorWindowTitle = this.langArray[202].replace("%name%", Configuration.name);
		this.warningWindowTitle = this.langArray[203].replace("%name%", Configuration.name);;
		this.infoWindowTitle = this.langArray[204].replace("%name%", Configuration.name);;
		this.failedToOpenDirMessage = this.langArray[300];
		this.failedToOpenDirInfo = this.langArray[301];
		this.failedToOpenFileMessage = this.langArray[302];
		this.failedToOpenFileInfo = this.langArray[303];
		this.modID = this.langArray[400];
		this.modTitle = this.langArray[401];
		this.modURL = this.langArray[402];
		this.modCategories = this.langArray[403];
		this.modAuthor = this.langArray[404];
		this.modContact = this.langArray[405];
		this.modName = this.langArray[406];
		this.modDescription = this.langArray[407];
		this.modHomepage = this.langArray[408];
	}
	
	/*
	 * LangFile-Loader
	 */
	private void loadLangFile()
	{
		this.langArray = this.defaultLang();
		try
		{
			if(!this.langFile.exists()){ this.createLangFile(); }
			else
			{
				String line;
				FileReader fL;
				fL = new FileReader(this.langFile);
				BufferedReader bR = new BufferedReader(fL);
				while( (line = bR.readLine()) != null )
				{
					String[] splitLine = utils.StringUtils.splitString(":", line, true);
					switch(splitLine[0])
					{
						/*
						 *    0-99: MenuItems
						 * 100-199: Buttons
						 * 200-201: TabPane
						 * 202-299: Window titles
						 * 300-399: Error/Warning/Info messages
						 * 400-499: ModInfoLabels
						 */
						case "menuFile":
							langArray[0] = splitLine[1];
							break;
						case "menuEdit":
							langArray[1] = splitLine[1];
							break;
						case "menuHelp":
							langArray[2] = splitLine[1];
							break;
						case "menuFileOpen":
							langArray[3] = splitLine[1];
							break;
						case "menuFileExit":
							langArray[4] = splitLine[1];
							break;
						case "menuEditUndo":
							langArray[5] = splitLine[1];
							break;
						case "menuEditRedo":
							langArray[6] = splitLine[1];
							break;
						case "menuEditView":
							langArray[7] = splitLine[1];
							break;
						case "menuEditSettings":
							langArray[8] = splitLine[1];
							break;
						case "menuHelpAbout":
							langArray[9] = splitLine[1];
							break;
						case "buttonOk":
							langArray[100] = splitLine[1];
							break;
						case "buttonCancel":
							langArray[101] = splitLine[1];
							break;
						case "buttonYes":
							langArray[102] = splitLine[1];
							break;
						case "buttonNo":
							langArray[103] = splitLine[1];
							break;
						case "modPackTab":
							langArray[200] = splitLine[1];
							break;
						case "modsTab":
							langArray[201] = splitLine[1];
							break;
						case "errorWindowTitle":
							langArray[202] = splitLine[1];
							break;
						case "warningWindowTitle":
							langArray[203] = splitLine[1];
							break;
						case "infoWindowTitle":
							langArray[204] = splitLine[1];
							break;
						case "failedToOpenDirMessage":
							langArray[300] = splitLine[1];
							break;
						case "failedToOpenDirInfo":
							langArray[301] = splitLine[1];
							break;
						case "failedToOpenFileMessage":
							langArray[302] = splitLine[1];
							break;
						case "failedToOpenFileInfo":
							langArray[303] = splitLine[1];
							break;
						case "modID":
							langArray[400] = splitLine[1];
							break;
						case "modTitle":
							langArray[401] = splitLine[1];
							break;
						case "modURL":
							langArray[402] = splitLine[1];
							break;
						case "modCategories":
							langArray[403] = splitLine[1];
							break;
						case "modAuthor":
							langArray[404] = splitLine[1];
							break;
						case "modContact":
							langArray[405] = splitLine[1];
							break;
						case "modName":
							langArray[406] = splitLine[1];
							break;
						case "modDescription":
							langArray[407] = splitLine[1];
							break;
						case "modHomepage":
							langArray[408] = splitLine[1];
							break;
						default:
							break;
					}
				}
				bR.close();
			}
		}
		catch (IOException e)
		{
			/*
			 * ToDo: Log Event
			 */
		}
	}
	
	/*
	 * ...
	 */
	private String[] defaultLang()
	{
		String[] lArray = new String[Language.langItems];
		
		//MenuItems
		lArray[0] = "File";
		lArray[1] = "Edit";
		lArray[2] = "Help";
		lArray[3] = "Open mod directory";
		lArray[4] = "Exit";
		lArray[5] = "Undo";
		lArray[6] = "Redo";
		lArray[7] = "Appearance";
		lArray[8] = "Settings";
		lArray[9] = "About %name%";
		
		//Buttons
		lArray[100] = "Ok";
		lArray[101] = "Cancel";
		lArray[102] = "Yes";
		lArray[103] = "No";
		
		//TabPane
		lArray[200] = "Modpacks";
		lArray[201] = "Mods";
		
		//Window titles
		lArray[202] = "%name% - Error";
		lArray[203] = "%name% - Warning";
		lArray[204] = "%name% - Information";
		
		//Error/Warning/Info messages
		lArray[300] = "Failed to open directory";
		lArray[301] = "Could not open the directory '%dir%' due to the following error:";
		lArray[302] = "Failed to open file";
		lArray[303] = "Could not open the file '%file%' due to the following error:";
		
		//ModInfoLabels
		lArray[400] = "Mod-ID";
		lArray[401] = "Title";
		lArray[402] = "URL";
		lArray[403] = "Categories";
		lArray[404] = "Author";
		lArray[405] = "Contact";
		lArray[406] = "Name";
		lArray[407] = "Description";
		lArray[408] = "Homepage";
		
		return lArray;
	}
	
	/*
	 * Default LangFile-Creator
	 * Creates a default langFile in english
	 */
	private void createLangFile() throws IOException
	{
		this.langFile = new File(Main.config.langFolder + "EN_en.lang");
		
		FileWriter fW = new FileWriter(this.langFile);
		BufferedWriter bW = new BufferedWriter(fW);
		
		//MenuItems
		bW.write("menuFile:File");
		bW.newLine();
		
		bW.write("menuEdit:Edit");
		bW.newLine();
		
		bW.write("menuHelp:Help");
		bW.newLine();
		
		bW.write("menuFileOpen:Open mod directory");
		bW.newLine();
		
		bW.write("menuFileExit:Exit");
		bW.newLine();
		
		bW.write("menuEditUndo:Undo");
		bW.newLine();
		
		bW.write("menuEditRedo:Redo");
		bW.newLine();
		
		bW.write("menuEditView:Appearance");
		bW.newLine();
		
		bW.write("menuEditSettings:Settings");
		bW.newLine();
		
		bW.write("menuViewAbout:About %name%");
		bW.newLine();
		
		//Buttons
		bW.write("buttonOk:Ok");
		bW.newLine();
		
		bW.write("buttonCancel:Cancel");
		bW.newLine();
		
		bW.write("buttonYes:Yes");
		bW.newLine();
		
		bW.write("buttonNo:No");
		bW.newLine();
		
		//TabPane
		bW.write("modPackTab:Modpacks");
		bW.newLine();
		
		bW.write("modsTab:Mods");
		bW.newLine();
		
		//WindowTitles
		bW.write("errorWindowTitle:%name% - Error");
		bW.newLine();
		
		bW.write("warningWindowTitle:%name% - Warning");
		bW.newLine();
		
		bW.write("infoWindowTitle:%name% - Information");
		bW.newLine();
		
		//Error/Warning/Info messages
		bW.write("failedToOpenDirMessage:Failed to open directory");
		bW.newLine();
		
		bW.write("failedToOpenDirInfo:Could not open the directory '%dir%' due to the following error:");
		bW.newLine();
		
		bW.write("failedToOpenFileMessage:Failed to open file");
		bW.newLine();
		
		bW.write("failedToOpenFileInfo:Could not open the file '%file%' due to the following error:");
		bW.newLine();
		
		//ModInfoLabels
		bW.write("modID:Mod-ID");
		bW.newLine();
		
		bW.write("modTitle:Title");
		bW.newLine();
		
		bW.write("modURL:URL");
		bW.newLine();
		
		bW.write("modCategories:Categories");
		bW.newLine();
		
		bW.write("modAuthor:Author");
		bW.newLine();
		
		bW.write("modContact:Contact");
		bW.newLine();
		
		bW.write("modName:Name");
		bW.newLine();
		
		bW.write("modDescription:Description");
		bW.newLine();
		
		bW.write("modHomepage:Homepage");
		bW.newLine();
		
		bW.close();
		fW.close();
	}
}

