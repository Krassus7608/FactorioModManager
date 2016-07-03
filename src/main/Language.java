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
	public final String menuFile;
	public final String menuFileLoad;
	public final String menuFileSave;
	public final String menuFileExit;
	public final String menuEdit;
	public final String menuEditUndo;
	public final String menuEditRedo;
	public final String menuEditView;
	public final String menuEditSettings;
	public final String menuHelp;
	public final String menuHelpAbout;
	
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
		this.menuFileLoad = this.langArray[3];
		this.menuFileSave = this.langArray[4];
		this.menuFileExit = this.langArray[5];
		this.menuEditUndo = this.langArray[6];
		this.menuEditRedo = this.langArray[7];
		this.menuEditView = this.langArray[8];
		this.menuEditSettings = this.langArray[9];
		this.menuHelpAbout = this.langArray[10];
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
						case "menuFileLoad":
							langArray[3] = splitLine[1];
							break;
						case "menuFileSave":
							langArray[4] = splitLine[1];
							break;
						case "menuFileExit":
							langArray[5] = splitLine[1];
							break;
						case "menuEditUndo":
							langArray[6] = splitLine[1];
							break;
						case "menuEditRedo":
							langArray[7] = splitLine[1];
							break;
						case "menuEditView":
							langArray[8] = splitLine[1];
							break;
						case "menuEditSettings":
							langArray[9] = splitLine[1];
							break;
						case "menuHelpAbout":
							langArray[10] = splitLine[1].replace("%name%",Configuration.name);
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
		lArray[3] = "Load from...";
		lArray[4] = "Save to...";
		lArray[5] = "Exit";
		lArray[6] = "Undo";
		lArray[7] = "Redo";
		lArray[8] = "Appearance";
		lArray[9] = "Settings";
		lArray[10] = "About %name%";
		
		//Buttons
		lArray[100] = "Ok";
		lArray[101] = "Cancel";
		lArray[102] = "Yes";
		lArray[103] = "No";
		
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
		
		bW.write("lang=EN_en");
		bW.newLine();
		
		bW.close();
		fW.close();
	}
}

