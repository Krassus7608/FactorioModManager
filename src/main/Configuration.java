package main;

import java.io.File;

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
	public final File langFile;
	
	/*
	 * Other attributes
	 */
	
	public Configuration()
	{
		this.langFile = new File("EN_en.lang");
	}
}
