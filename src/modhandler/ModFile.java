package modhandler;

public class ModFile
{
	public final int ID;
	public final String name;
	public final String mirror;
	public final String url;
	
	public ModFile(int ID, String name, String mirror, String url)
	{
		this.ID = ID;
		this.name = name;
		this.mirror = mirror;
		this.url = url;
	}
	
	public ModFile(int ID, String name, String mirror)
	{
		this(ID, name, mirror, null);
	}
}