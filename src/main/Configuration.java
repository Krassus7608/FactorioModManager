package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.image.Image;

public class Configuration
{
	/*
	 * Static attributes
	 */
	public static final String name = "FactorioModOrganizer";
	public static final Image icon = new Image(Configuration.class.getResourceAsStream("factorio.png"));
	
	
	/*
	 * Configurable attributes
	 * These attributes will be loaded from fmc.conf
	 */
	//public final String os;
	public final String path;
	
	public final String langFolder;
	public final File langFile;
	
	public final String factorioBaseFolder;
	public final String factorioAppFolder;
	
	
	/*
	 * Other attributes
	 */
	private static final int configItems = 1000;
	private File confFile;
	private String[] confArray;
	
	public Configuration()
	{
		//get path to fmo.jar
		this.path = System.getProperty("user.dir").replace('\\', '/') + "/";
		
		//setup confFile and confArray
		this.confArray = new String[Configuration.configItems];
		this.confFile = new File(this.path + "fmo.conf");
		
		//load from fmo.conf
		this.loadConfig();
		
		//set final attributes
		this.langFolder = this.path + "lang/";
		this.langFile = new File(this.langFolder + this.confArray[0] + ".lang");
		this.factorioBaseFolder = this.confArray[1];
		
	}
	
	/*
	 * Loads the configuration
	 */
	private void loadConfig()
	{
		String line;
		String[] splitLine;
		try
		{
			int lineNo = 1;
			FileReader fL = new FileReader(this.confFile);
			BufferedReader bR = new BufferedReader(fL);
			while( (line = bR.readLine()) != null )
			{
				if(line != "")
				{
					splitLine = utils.StringUtils.splitString("=", line, true);
					if(splitLine.length != 2)
					{
						//ToDo
						bR.close();
						throw new IllegalArgumentException("Wrong syntax in configuration file on line " + lineNo + " at '" + splitLine[splitLine.length-1] + "'");
					}
					else
					{
						switch(splitLine[0])
						{
							/*
							 *       0: Language
							 *       1: Factorio main directory
							 * 100-199: MenuItems
							 * 200-299: Buttons
							 */
							case "lang":
								this.confArray[0] = splitLine[1];
								break;
							case "factorioDir":
								this.confArray[1] = splitLine[1];
								break;
							default:
								//ToDo
								break;
						}
					}
				}
				lineNo++;
			}
			bR.close();
		}
		catch (FileNotFoundException e)
		{
			try
			{
				this.createDefaultConfig();
			}
			catch (IOException e1)
			{
				//ToDo: Log event
			}
			finally
			{
				this.loadDefaultConfig();
			}
		}
		catch (IOException e)
		{
			//ToDo: Log event
			this.loadDefaultConfig();
		}
	}
	
	/*
	 * Creates a default configuration file
	 */
	private void createDefaultConfig() throws IOException
	{
		FileWriter fW = new FileWriter(this.path + "fmo.conf");
		BufferedWriter bW = new BufferedWriter(fW);
		
		bW.write("lang=EN_en");
		bW.newLine();
		
		/*bW.write("factorioDir=");
		bW.newLine();*/
		
		bW.close();
		fW.close();
	}
	
	/*
	 * Loads a default configuration
	 */
	private void loadDefaultConfig()
	{
		this.confArray[0] = "En_en";
		this.confArray[1] = null;
	}
}
