package utils;

public class CharUtils
{
	private CharUtils(){}
	
	public static String charArrayToString(char[] c)
	{
		String string = "";
		for(int i = 0; i < c.length; i++)
		{
			string += c[i];
		}
		return string;
	}
}
