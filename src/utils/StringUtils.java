package utils;

import java.util.ArrayList;

public class StringUtils
{
	private StringUtils(){}
	
	public static String[] splitString(String split, String str, boolean removeSplit)
	{
		int start = 0;
		int splitLen = split.length();
		int strLen = str.length();
		
		String tmp = "";
		ArrayList<String> splitedStringList = new ArrayList<>();
		
		int i;
		for(i = 1; i <= strLen; i++)
		{
			if(i >= splitLen)
			{
				tmp = str.substring(i-splitLen, i);
				if(tmp.equals(split))
				{
					if(removeSplit)
					{
						splitedStringList.add(str.substring(start, i-splitLen));
					}
					else
					{
						splitedStringList.add(str.substring(start, i));
					}
					start = i;
				}
			}
			else
			{
				tmp = str.substring(0, i);
			}
		}
		
		i--;
		if(start != i)
		{
			if(removeSplit && str.substring(i-splitLen, i).equals(split))
			{
				splitedStringList.add(str.substring(start, i-splitLen));
			}
			else
			{
				splitedStringList.add(str.substring(start, i));
			}
		}
		
		String[] splitedString = new String[splitedStringList.size()];
		for(i = 0; i < splitedString.length; i++)
		{
			splitedString[i] = splitedStringList.get(i);
		}
		return splitedString;
	}
	
	public static String arrToString(String[] str, String delimiter)
	{
		String s = "";
		for(int i = 0; i < str.length; i++)
		{
			s += str[i];
			if(i+1 < str.length)
			{
				s += delimiter + " ";
			}
		}
		return s;
	}
}
