package modhandler;

import java.util.ArrayList;

public class ModPack
{
	/*
	 * ToDo: Author, Beschreibung, etc...
	 */
	private ArrayList<Mod> mods;
	
	public ModPack(Mod[] m)
	{
		this.mods = new ArrayList<>();
		for(int i = 0; i < m.length; i++)
		{
			this.mods.add(m[i]);
		}
	}
	
	public ModPack()
	{
		this(new Mod[0]);
	}
}