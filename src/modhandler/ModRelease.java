package modhandler;

import java.util.Date;

public class ModRelease
{
	public final int ID;
	public final String version;
	public final Date release;
	public final String[] gameVersions;
	public final String[] dependencies;
	public final ModFile[] files;
	
	public ModRelease(int ID, String version, Date release, String[] gameVersions, String[] dependencies, ModFile[] files)
	{
		this.ID = ID;
		this.version = version;
		this.release = release;
		this.gameVersions = gameVersions;
		this.dependencies = dependencies;
		this.files = files;
	}
	
	@Override
	public String toString()
	{
		String str = "ID: " + this.ID + "\n[\n\tmodVersion: " + this.version + "\n\treleaseDate: " + this.release.toString() + "\n\tgameVersions: ";
		for(int i = 0; i < this.gameVersions.length; i++)
		{
			str += this.gameVersions[i];
			if(i+1 < this.gameVersions.length)
			{
				str += ", ";
			}
		}
		str += "\n\tdependencies: ";
		for(int i = 0; i < this.dependencies.length; i++)
		{
			str += this.dependencies[i];
			if(i+1 < this.dependencies.length)
			{
				str += ", ";
			}
		}
		str += "\n]";
		return str;
	}
}