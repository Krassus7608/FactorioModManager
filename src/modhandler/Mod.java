package modhandler;

public class Mod
{
	public final int ID;
	public final String url;
	public final String[] categories;
	public final String author;
	public final String contact;
	public final String title;
	public final String name;
	public final String description;
	public final String homepage;
	public final ModRelease[] releases;
	
	private boolean status;

	public Mod(int ID, String url, String[] categories, String author, String contact, String title, String name, String description, String homepage, ModRelease[] releases, boolean status)
	{
		this.ID = ID;
		this.url = url;
		this.categories = categories;
		this.author = author;
		this.contact = contact;
		this.title = title;
		this.name = name;
		this.description = description;
		this.homepage = homepage;
		this.releases = releases;
		
		this.status = status;
	}

	public Mod(int ID, String url, String[] categories, String author, String contact, String title, String name, String description, String homepage, ModRelease[] releases)
	{
		this(ID, url, categories, author, contact, title, name, description, homepage, releases, false);
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