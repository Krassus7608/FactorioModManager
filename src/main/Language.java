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
	public final String menuEdit;
	public final String menuHelp;
	
	/*
	 * Other attributes
	 */
	private File langFile;
	private String[] langArray;
	private final int langItems = 1000;
	
	public Language(File langFile)
	{
		this.langFile = langFile;
		
		//prepare langArray
		this.langArray = new String[this.langItems];
		
		//Load langFile
		this.loadLangFile();
		
		//set final attributes
		this.menuFile = this.langArray[0];
		this.menuEdit = this.langArray[1];
		this.menuHelp = this.langArray[2];
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
		String[] lArray = new String[this.langItems];
		
		//MenuItems
		lArray[0] = "File";
		lArray[1] = "Edit";
		lArray[2] = "Help";
		
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
