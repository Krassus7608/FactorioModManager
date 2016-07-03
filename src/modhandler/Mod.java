package modhandler;

public class Mod
{
	/*
	 * ToDo: Author, Beschreibung, etc...
	 */
	public final String name;
	private boolean status;
	
	public Mod(String name, boolean status)
	{
		this.name = name;
		this.status = status;
	}
	
	public Mod(String name)
	{
		this(name, false);
	}
	
	/*
	 * Getter and Setter
	 */
	public boolean getStatus()
	{
		return this.status;
	}
	
	public void setStatus(boolean s)
	{
		this.status = s;
	}
}
