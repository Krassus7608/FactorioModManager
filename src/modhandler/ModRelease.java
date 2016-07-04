package modhandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}