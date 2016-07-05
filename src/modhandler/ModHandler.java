package modhandler;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.*;
import org.json.simple.parser.*;

import com.sun.xml.internal.messaging.saaj.util.Base64;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModHandler
{
	/*private ArrayList<Mod> modList;
	private ArrayList<ModPack> modPacks;*/
	private ObservableList<Mod> modList;
	private ObservableList<ModPack> modPacks;
	private JSONParser parser;
	
	public ModHandler()
	{
		this.modList = FXCollections.observableList(new ArrayList<Mod>());
		this.modPacks = FXCollections.observableList(new ArrayList<ModPack>());
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
		this.parser = new JSONParser();
		
		/*
		 * temporärer FileReader
		 */
		this.modList.add(this.getMod("eyJpZCI6MzgzLCJ1cmwiOiJodHRwOi8vd3d3LmZhY3RvcmlvbW9kcy5jb20vbW9kcy9zLWEtbSIsImNhdGVnb3JpZXMiOlsibWFudWZhY3R1cmluZyIsImJhbGFuY2UiLCJnYW1lcGxheSJdLCJhdXRob3IiOiJCT0ZhbjgwIiwiY29udGFjdCI6IiIsInRpdGxlIjoiUy5BLk0uIiwibmFtZSI6IlN1cGVyX2Fzc2VtYmxpbmdfbWFjaGluZSIsImRlc2NyaXB0aW9uIjoiVGhlIG1haW4gY29tcG9uZW50IGlzIHRoZSBTdXBlciBBc3NlbWJsaW5nIE1hY2hpbmUuIEFsbCBuZXcgcmVjaXBlcyBjYW4gYmUgbWFkZSBvbmx5IHRoZXJlLiBTLkEuTS4gaGFzIDggbW9kdWxlIHNsb3RzIGFuZCB1cCB0byA4IGluZ3JlZGllbnRzLiBJdCBjYW4gcHJvY2VzcyBib3RoIHNvbGlkIGFuZCBsaXF1aWQgaW5ncmVkaWVudHMuIEZ1cnRoZXJtb3JlLCBJJ3ZlIHRob3VnaHQgb2YgdGhlIFNhbS1jaXJjdWl0LiBFYWNoIG5ldyByZWNpcGUgd2hpY2ggaXMgcHJlcGFyZWQgaW4gYSBTQU0sIG5lZWRzIHRoaXMgYXMgYSBjYXRhbHlzdC5cclxuXHJcblRoZSBtYWluIHJlY2lwZXMgZm9yIHJlc2VhcmNoIGFuZCBkZXZlbG9wbWVudCBoYXZlIGJlZW4gaW1wbGVtZW50ZWQgeWV0LlxyXG4zLiBQYXJ0eSBNb2RzIGFyZSBub3Qgc3VwcG9ydGVkIHlldCwgSSBoYXZlIHRvIGFkanVzdCBlYWNoIHJlY2lwZSBpbmRpdmlkdWFsbHkuXHJcblxyXG5Pbmx5IGFydGljbGVzIGFyZSBtYWRlLCB3aGljaCBjYW4gYmUgYWxzbyBwcm92aWRlZCBpbiB0aGUgbm9ybWFsIEFzc2VtYmxpbmcgTWFzY2hpbmVzLiBUaGUgY2hlbWljYWwgcGxhbnQgaXMgbm90IHlldCBzdXBwb3J0ZWQuIEhvd2V2ZXIsIHRoaXMgaXMgc3RpbGwgb24gbXkgdG8tZG8gbGlzdC5cclxuXHJcbkNhdXRpb24gaW4gVmVyc2lvbiAwLjIuMSB5b3UgY2FuIG5vdCByZXNlYXJjaCBsb2dpc3RpLXJvYm90aWNzIGFuZCBTLkEuTS4gYXQgdGhlIHNhbWUgdGltZS4gWW91IG11c3QgY2hvb3NlIGJldHdlZW4gdGhpcyB0ZWNobm9sb2d5cy4gXHJcblxyXG5WZXJzaW9uIDAuMy4wIG9ubHkgZm9yIEZhY3RvcmlvIDAuMTMuWFxyXG5hZGRlZCB0aGUgQ2hlbXBsYW50IHZlcnNpb24gb2YgUy5BLk0uXHJcblJlbW92ZSBEZWNpc2lvbmUgYmV0d2VlbiBTLkEuTS4gYW5kIExvZ2lzdGljIFJvYm90cy5cclxuVmVyc2lvbiAwLjMuMCBpc3QgZWFybHkgQmV0YSwgcGxlYXNlIHJlcG9ydCBhbnkgQnVncyIsImhvbWVwYWdlIjoiIiwicmVsZWFzZXMiOlt7ImlkIjo3MDEsInZlcnNpb24iOiIwLjMuMCIsInJlbGVhc2VkX2F0IjoiMjAxNi0wNy0wM1QwMDowMDowMC4wMDBaIiwiZ2FtZV92ZXJzaW9ucyI6WyIwLjEyLngiXSwiZGVwZW5kZW5jaWVzIjpbXSwiZmlsZXMiOlt7ImlkIjo3MjcsIm5hbWUiOiIiLCJtaXJyb3IiOiJodHRwOi8vczMuYW1hem9uYXdzLmNvbS9mYWN0b3Jpb21vZHMvbW9kX2ZpbGVzL2F0dGFjaG1lbnRzLzAwMC8wMDAvNzI3L29yaWdpbmFsL1N1cGVyX2Fzc2VtYmxpbmdfbWFjaGluZV8wLjMuMC56aXA/MTQ2NzU0NDQzOCIsInVybCI6bnVsbH1dfSx7ImlkIjo2NDIsInZlcnNpb24iOiIwLjIuMSIsInJlbGVhc2VkX2F0IjoiMjAxNi0wNi0wNVQwMDowMDowMC4wMDBaIiwiZ2FtZV92ZXJzaW9ucyI6WyIwLjEyLngiXSwiZGVwZW5kZW5jaWVzIjpbXSwiZmlsZXMiOlt7ImlkIjo2NjYsIm5hbWUiOiIiLCJtaXJyb3IiOiJodHRwOi8vczMuYW1hem9uYXdzLmNvbS9mYWN0b3Jpb21vZHMvbW9kX2ZpbGVzL2F0dGFjaG1lbnRzLzAwMC8wMDAvNjY2L29yaWdpbmFsL1N1cGVyX2Fzc2VtYmxpbmdfbWFjaGluZV8wLjIuMS56aXA/MTQ2NTE4ODU3MiIsInVybCI6bnVsbH1dfSx7ImlkIjo2MTIsInZlcnNpb24iOiIwLjIuMSIsInJlbGVhc2VkX2F0IjoiMjAxNi0wNS0yNFQwMDowMDowMC4wMDBaIiwiZ2FtZV92ZXJzaW9ucyI6WyIwLjEyLngiXSwiZGVwZW5kZW5jaWVzIjpbXSwiZmlsZXMiOlt7ImlkIjo2MzYsIm5hbWUiOiIiLCJtaXJyb3IiOiJodHRwOi8vczMuYW1hem9uYXdzLmNvbS9mYWN0b3Jpb21vZHMvbW9kX2ZpbGVzL2F0dGFjaG1lbnRzLzAwMC8wMDAvNjM2L29yaWdpbmFsL1N1cGVyX2Fzc2VtYmxpbmdfbWFjaGluZV8wLjIuMS56aXA/MTQ2NTE0MDI1OSIsInVybCI6bnVsbH1dfV19"));
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
	
	public Mod getMod(String json)
	{
		json = Base64.base64Decode(json);
		try
		{
			Object obj = parser.parse(json);
			JSONObject jsonObject = (JSONObject) obj;

			//Get categories
			JSONArray jA = ((JSONArray) jsonObject.get("categories"));
			String[] categories = new String[jA.size()];
			for(int i = 0; i < jA.size(); i++){ categories[i] = (String) jA.get(i); }
			
			//Get releases
			jA = ((JSONArray) jsonObject.get("releases"));
			ModRelease[] mR = new ModRelease[jA.size()];
			for(int i = 0; i < jA.size(); i++)
			{
				JSONObject jO = (JSONObject) jA.get(i);
				Date date = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss.SSS").parse(((String)jO.get("released_at")).replace("T", " ").replace("Z", ""));
				
				JSONArray vJA = (JSONArray) jO.get("game_versions");
				String[] versions = new String[vJA.size()];
				for(int j = 0; j < vJA.size(); j++){ versions[j] = (String)vJA.get(j); }
				
				vJA = (JSONArray) jO.get("dependencies");
				String[] dependencies = new String[vJA.size()];
				for(int j = 0; j < vJA.size(); j++){ dependencies[j] = (String)vJA.get(j); }
				
				JSONArray fJA = (JSONArray) jO.get("files");
				ModFile[] mF = new ModFile[fJA.size()];
				for(int j = 0; j < fJA.size(); j++)
				{
					JSONObject fJO = (JSONObject) fJA.get(j);
					mF[j] = new ModFile( Math.toIntExact(((long)fJO.get("id"))), (String)fJO.get("name"), (String)fJO.get("mirror"), (String)fJO.get("url"));
				} 
				
				mR[i] = new ModRelease(Math.toIntExact((long)jO.get("id")), (String)jO.get("version"), date, versions, dependencies, mF);
			}
			
			return new Mod(Math.toIntExact((long) jsonObject.get("id")), (String) jsonObject.get("url"), categories, (String) jsonObject.get("author"), (String) jsonObject.get("contact"), (String) jsonObject.get("title"), (String) jsonObject.get("name"), (String) jsonObject.get("description"), (String) jsonObject.get("homepage"), mR);
		}
		catch (ParseException e)
		{
			/*
			 * ExceptionHandling
			 */
			e.printStackTrace();
		}
		catch (java.text.ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	public ObservableList<Mod> getMods()
	{
		return this.modList;
	}
	
	public ObservableList<ModPack> getModPacks()
	{
		return this.modPacks;
	}
}