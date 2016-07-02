package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Language
{
	private File langFile;
	
	public Language(File langFile)
	{
		this.langFile = langFile;
		
		//Load langFile
		String[] langArray = this.loadLangFile();
	}
	
	/*
	 * LangFile-Loader
	 */
	private String[] loadLangFile()
	{
		String[] langArray = this.defaultLang();
		try
		{
			if(!this.langFile.exists()){ this.createLangFile(); return langArray; }
			
			String line;
			FileReader fL;
			fL = new FileReader(this.langFile);
			BufferedReader bR = new BufferedReader(fL);
			while( (line = bR.readLine()) != null )
			{
				String[] splitLine = utils.StringUtils.splitString(":", line, true);
				switch(split[0])
				{
					case "menuFile":
						langArray[100] = splitLine[1];
						break;
					case "menuEdit":
						langArray[101] = splitLine[1];
						break;
					case "menuHelp":
						langArray[102] = splitLine[1];
						break;
					case "buttonOk":
						langArray[200] = splitLine[1];
						break;
					case "buttonCancle":
						langArray[201] = splitLine[1];
						break;
					case "buttonYes":
						langArray[202] = splitLine[1];
						break;
					case "buttonNo":
						langArray[203] = splitLine[1];
						break;
					default:
						break;
				}
			}
			bR.close();
		}
		catch (IOException e)
		{
			/*
			 * ToDo: Log Event
			 */
		}
		return langArray;
	}
	
	/*
	 * ...
	 */
	private String[] defaultLang()
	{
		String[] langArray = new String[0];
		
		//...
		
		return langArray;
	}
	
	/*
	 * Default LangFile-Creator
	 * Creates a default langFile in english
	 */
	private void createLangFile()
	{
	}
}
