package utils;

public class PrintByte
{
	private PrintByte(){}
	
	public static void printByte(byte[] b)
	{
		for(int i = 0; i < b.length; i++)
		{
			System.out.print(b[i] + " ");
		}
		System.out.println("");
	}
	
	public static void printByte(byte[][] b)
	{
		for(int i = 0; i < b.length; i++)
		{
			for(int j = 0; j < b[i].length; j++)
			{
				System.out.print(b[i][j] + " ");
			}
		}
		System.out.println("");
	}
}
