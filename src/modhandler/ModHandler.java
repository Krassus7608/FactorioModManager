package modhandler;

import java.io.*;
import java.util.ArrayList;
import org.json.simple.*;
import org.json.simple.parser.*;

public class ModHandler
{
	private ArrayList<Mod> modList;
	private ArrayList<ModPack> modPacks;
	private JSONParser parser;
	
	public ModHandler()
	{
		/*
		 * Alle Mods(zips) aus %AppData%/Roaming/Factorio/mods/ erfassen, info.json auslesen und in modList speichern.
		 * Der Status der einzelnen Mods soll anfangs auf false gesetzt werden.
		 */
		
		/*
		 * Alle ModPacks aus %AppData%/Roaming/Factorio/mods/modPacks.json erfassen,
		 * prüfen, ob die Mods noch existieren (vgl. mit modList). Falls eine oder mehrere Mod(s) nicht
		 * mehr vorhanden sind, soll das ModPack deaktiviert werden.
		 * Der Status der Mods der aktiven ModPacks soll nun auf true gesetzt werden.
		 */
		
		/*
		 * Alle Mods aus modList in %AppData%/Roaming/Factorio/mods/mod-list.json schreiben (mit Name und Status).
		 */
		parser = new JSONParser();
	}
	
	/*
	 * Mod-Functions
	 */
	public void addMod(Mod m)
	{
		this.modList.add(m);
	}
	
	public void deleteMod(Mod m)
	{
		this.modList.remove(m);
	}
	
	public void getMod(String url)
	{
		//...
		//this.addMod(m);
	}

	public void parseModJson() {
		try {
			Object obj = parser.parse(new FileReader("D:\\Projects\\FactorioModOrganizer\\mod.json"));
			JSONObject jsonObject = (JSONObject) obj;

			String title = (String) jsonObject.get("title");
			System.out.println(title);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * ModPack-Functions
	 */
	public void createModPack(Mod[] m)
	{
		this.modPacks.add(new ModPack(m));
	}
	
	public void createModPack()
	{
		this.createModPack(new Mod[0]);
	}
	
	public void addModPack(ModPack m)
	{
		this.modPacks.add(m);
	}
	
	public void deleteModPack(ModPack m)
	{
		this.modPacks.remove(m);
	}
	
	/*
	 * Getter and Setter
	 */
	public Mod[] getMods()
	{
		Mod[] m = new Mod[this.modList.size()];
		
		for(int i = 0; i < m.length; i++)
		{
			m[i] = this.modList.get(i);
		}
		
		return m;
	}
	
	public ModPack[] getModPacks()
	{
		ModPack[] mP = new ModPack[this.modPacks.size()];
		
		for(int i = 0; i < mP.length; i++)
		{
			mP[i] = this.modPacks.get(i);
		}
		
		return mP;
	}
}