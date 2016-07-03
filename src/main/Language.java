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
		
		bW.write("buttonOk:Ok");
		bW.newLine();
		
		bW.write("buttonCancel:Cancel");
		bW.newLine();
		
		bW.write("buttonYes:Yes");
		bW.newLine();
		
		bW.write("buttonNo:No");
		bW.newLine();
		
		bW.write("modPackTab:Modpacks");
		bW.newLine();
		
		bW.write("modsTab:Mods");
		bW.newLine();
		
		bW.close();
		fW.close();
	}
}

