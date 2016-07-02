package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Configuration
{
	/*
	 * Static attributes
	 */
	public static final String name = "FactorioModOrganizer";
	
	/*
	 * Configurable attributes
	 * These attributes will be loaded from fmc.conf
	 */
	public final String path;
	public final File langFile;
	
	/*
	 * Other attributes
	 */
	private File confFile;
	private String[] confArray;
	
	public Configuration()
	{
		//get path to fmo.jar
		this.path = System.getProperty("user.dir").replace('\\', '/') + "/";
		
		//setup confFile
		this.confFile = new File(this.path + "fmo.conf");
		
		//load from fmo.conf
		String line;
		this.confArray = new String[6];
		String[] splitLine;
		try
		{
			int lineNo = 1;
			FileReader fL = new FileReader(this.confFile);
			BufferedReader bR = new BufferedReader(fL);
			while( (line = bR.readLine()) != null )
			{
				
			}
			bR.close();
		}
		catch (FileNotFoundException e)
		{
			this.createDefaultConfig();
			this.loadDefaultConfig();
		}
		catch (IOException e)
		{
			//Log event
		}
		
		this.langFile = new File("EN_en.lang");
	}
	
	/*
	 * Creates a default configuration file
	 */
	private void createDefaultConfig()
	{
		
	}
	
	private void loadDefaultConfig()
	{
		
	}
}
