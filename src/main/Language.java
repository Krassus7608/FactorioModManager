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
	public final String errorFTypeRegistrationMessage;
	public final String errorFTypeRegistrationInfo;
	public final String choseFileDownload;
	public final String errorDownloadMessage;
	public final String errorDownloadInfo;
	public final String warningUriSchemeMessage;
	public final String warningUriSchemeInfo;
	public final String warningUriSchemeCancleInfo;
	public final String warningUriSchemeFailedInfo;
	public final String warningUriSchemeFailedDetail;
	
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
	public final String modMirror;
	public final String modVersion;
	public final String modGameVersion;
	public final String modDepend;
	public final String modRelease;
	
	//other
	public final String otherDate;
	public final String otherFiles;
	public final String otherDownload;
	public final String otherDownloadFromWebsite;
	
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
		this.errorFTypeRegistrationMessage = this.langArray[304];
		this.errorFTypeRegistrationInfo = this.langArray[305];
		this.choseFileDownload = this.langArray[306];
		this.errorDownloadMessage = this.langArray[307];
		this.errorDownloadInfo = this.langArray[308];
		this.warningUriSchemeMessage = this.langArray[309];
		this.warningUriSchemeInfo = this.langArray[310];
		this.warningUriSchemeCancleInfo = this.langArray[311];
		this.warningUriSchemeFailedInfo = this.langArray[312];
		this.warningUriSchemeFailedDetail = this.langArray[313].replace("%javaPath%", System.getProperty("java.home")).replace("%jarPath%", Configuration.jarPath);
		this.modID = this.langArray[400];
		this.modTitle = this.langArray[401];
		this.modURL = this.langArray[402];
		this.modCategories = this.langArray[403];
		this.modAuthor = this.langArray[404];
		this.modContact = this.langArray[405];
		this.modName = this.langArray[406];
		this.modDescription = this.langArray[407];
		this.modHomepage = this.langArray[408];
		this.modMirror = this.langArray[409];
		this.modVersion = this.langArray[410];
		this.modGameVersion = this.langArray[411];
		this.modDepend = this.langArray[412];
		this.modRelease = this.langArray[413];
		this.otherDate = this.langArray[900];
		this.otherFiles = this.langArray[901];
		this.otherDownload = this.langArray[902];
		this.otherDownloadFromWebsite = this.langArray[903];
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
						 * 900-999: Others
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
						case "errorFTypeRegistrationMessage":
							langArray[304] = splitLine[1];
							break;
						case "errorFTypeRegistrationInfo":
							langArray[305] = splitLine[1];
							break;
						case "choseFileDownload":
							langArray[306] = splitLine[1];
							break;
						case "errorDownloadMessage":
							langArray[307] = splitLine[1];
							break;
						case "errorDownloadInfo":
							langArray[308] = splitLine[1];
							break;
						case "warningUriSchemeMessage":
							langArray[309] = splitLine[1];
							break;
						case "warningUriSchemeInfo":
							langArray[310] = splitLine[1];
							break;
						case "warningUriSchemeCancleInfo":
							langArray[311] = splitLine[1];
							break;
						case "warningUriSchemeFailedInfo":
							langArray[312] = splitLine[1];
							break;
						case "warningUriSchemeFailedDetail":
							langArray[313] = splitLine[1];
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
						case "modMirror":
							langArray[409] = splitLine[1];
							break;
						case "modVersion":
							langArray[410] = splitLine[1];
							break;
						case "modGameVersion":
							langArray[411] = splitLine[1];
							break;
						case "modDepend":
							langArray[412] = splitLine[1];
							break;
						case "modRelease":
							langArray[413] = splitLine[1];
							break;
						case "otherDate":
							langArray[900] = splitLine[1];
							break;
						case "otherFiles":
							langArray[901] = splitLine[1];
							break;
						case "otherDownload":
							langArray[902] = splitLine[1];
							break;
						case "otherDownloadFromWebsite":
							langArray[903] = splitLine[1];
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
		lArray[304] = "Failed to register custom URI-Scheme";
		lArray[305] = "Could not register custom URI-Scheme due to the following error:";
		lArray[306] = "Witch version of the mod you want to download?";
		lArray[307] = "Downloaderror";
		lArray[308] = "Failed to download file due to the following error:";
		lArray[309] = "Unregistered URI-Scheme";
		lArray[310] = "The URI-Scheme 'factoriomods://' is not registered on your system. If you want to install mods via the Website you need to register this URI-Scheme.\nDo you want to register it now?";
		lArray[311] = "URI-Scheme is not registered. You will not be able to install mods directly from the website!";
		lArray[312] = "Failed to register URI-Scheme due to an error.\nIf you want to try it manually please execute the following steps:";
		lArray[313] = "Try to run the FactorioModOrganizer with administrator/root privileges.";
		
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
		lArray[409] = "Download";
		lArray[410] = "Mod version";
		lArray[411] = "Game version";
		lArray[412] = "Dependencies";
		lArray[413] = "Release";
		
		//Others
		lArray[900] = "Date";
		lArray[901] = "Files";
		lArray[902] = "Download";
		lArray[903] = "Download from website";
		
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
		
		bW.write("errorFTypeRegistrationMessage:Failed to register custom URI-Scheme");
		bW.newLine();
		
		bW.write("errorFTypeRegistrationInfo:Could not register custom URI-Scheme due to the following error:");
		bW.newLine();
		
		bW.write("choseFileDownload:Witch version of the mod you want to download?");
		bW.newLine();
		
		bW.write("errorDownloadMessage:Downloaderror");
		bW.newLine();
		
		bW.write("errorDownloadInfo:Failed to download file due to the following error:");
		bW.newLine();
		
		bW.write("warningUriSchemeMessage:Unregistered URI-Scheme");
		bW.newLine();
		
		bW.write("warningUriSchemeInfo:The URI-Scheme 'factoriomods://' is not registered on your system. If you want to install mods via the Website you need to register this URI-Scheme.\nDo you want to register it now?");
		bW.newLine();
		
		bW.write("warningUriSchemeCancleInfo:URI-Scheme is not registered. You will not be able to install mods directly from the website!");
		bW.newLine();
		
		bW.write("warningUriSchemeFailedInfo:Failed to register URI-Scheme due to an error.\nIf you want to try it manually please execute the following steps:");
		bW.newLine();
		
		bW.write("warningUriSchemeFailedDetail:Try to run the FactorioModOrganizer with administrator/root privileges.");
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
		
		bW.write("modMirror:Download");
		bW.newLine();
		
		bW.write("modVersion:Mod version");
		bW.newLine();
		
		bW.write("modGameVersion:Game version");
		bW.newLine();
		
		bW.write("modDepend:Dependencies");
		bW.newLine();
		
		bW.write("modRelease:Release");
		bW.newLine();
		
		//Other
		bW.write("otherDate:Date");
		bW.newLine();
		
		bW.write("otherFiles:Files");
		bW.newLine();
		
		bW.write("otherDownload:Download");
		bW.newLine();
		
		bW.write("otherDownloadFromWebsite:Download from website");
		bW.newLine();
		
		bW.close();
		fW.close();
	}
}

