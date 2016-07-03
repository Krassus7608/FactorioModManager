package modhandler;

public class Mod
{
	public final String name;
	public final String url;
	public final String author;
	public final String title;
	public final String description;
	public final String hompage;
	private boolean status;

	public Mod(String name, String url, String author, String title, String description, String hompage, boolean status) {
		this.name = name;
		this.url = url;
		this.author = author;
		this.title = title;
		this.description = description;
		this.hompage = hompage;
		this.status = status;
	}

	public Mod(String name, String url, String author, String title, String description, String homepage)
	{
		this(name, url, author, title, description, homepage, false);
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