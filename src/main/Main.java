package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import gui.DownloadChooser;
import gui.Gui;
import gui.ModDownloadItem;
import javafx.application.Application;
import javafx.stage.Stage;
import modhandler.Mod;
import modhandler.ModHandler;
import modhandler.ModRelease;

public class Main extends Application
{
	public static final Configuration config = new Configuration();
	public static final Language lang = new Language(Main.config.langFile);
	public static final ModHandler modHandler = new ModHandler();
	
	public static void main(String[] args)
	{
		try
		{
			PrintStream out = new PrintStream(new FileOutputStream("log.txt"));
			System.setOut(out);
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		
		args = new String[]{"factoriomods://eyJpZCI6MzgzLCJ1cmwiOiJodHRwOi8vd3d3LmZhY3RvcmlvbW9kcy5jb20vbW9kcy9zLWEtbSIsImNhdGVnb3JpZXMiOlsibWFudWZhY3R1cmluZyIsImJhbGFuY2UiLCJnYW1lcGxheSJdLCJhdXRob3IiOiJCT0ZhbjgwIiwiY29udGFjdCI6IiIsInRpdGxlIjoiUy5BLk0uIiwibmFtZSI6IlN1cGVyX2Fzc2VtYmxpbmdfbWFjaGluZSIsImRlc2NyaXB0aW9uIjoiVGhlIG1haW4gY29tcG9uZW50IGlzIHRoZSBTdXBlciBBc3NlbWJsaW5nIE1hY2hpbmUuIEFsbCBuZXcgcmVjaXBlcyBjYW4gYmUgbWFkZSBvbmx5IHRoZXJlLiBTLkEuTS4gaGFzIDggbW9kdWxlIHNsb3RzIGFuZCB1cCB0byA4IGluZ3JlZGllbnRzLiBJdCBjYW4gcHJvY2VzcyBib3RoIHNvbGlkIGFuZCBsaXF1aWQgaW5ncmVkaWVudHMuIEZ1cnRoZXJtb3JlLCBJJ3ZlIHRob3VnaHQgb2YgdGhlIFNhbS1jaXJjdWl0LiBFYWNoIG5ldyByZWNpcGUgd2hpY2ggaXMgcHJlcGFyZWQgaW4gYSBTQU0sIG5lZWRzIHRoaXMgYXMgYSBjYXRhbHlzdC5cclxuXHJcblRoZSBtYWluIHJlY2lwZXMgZm9yIHJlc2VhcmNoIGFuZCBkZXZlbG9wbWVudCBoYXZlIGJlZW4gaW1wbGVtZW50ZWQgeWV0LlxyXG4zLiBQYXJ0eSBNb2RzIGFyZSBub3Qgc3VwcG9ydGVkIHlldCwgSSBoYXZlIHRvIGFkanVzdCBlYWNoIHJlY2lwZSBpbmRpdmlkdWFsbHkuXHJcblxyXG5Pbmx5IGFydGljbGVzIGFyZSBtYWRlLCB3aGljaCBjYW4gYmUgYWxzbyBwcm92aWRlZCBpbiB0aGUgbm9ybWFsIEFzc2VtYmxpbmcgTWFzY2hpbmVzLiBUaGUgY2hlbWljYWwgcGxhbnQgaXMgbm90IHlldCBzdXBwb3J0ZWQuIEhvd2V2ZXIsIHRoaXMgaXMgc3RpbGwgb24gbXkgdG8tZG8gbGlzdC5cclxuXHJcbkNhdXRpb24gaW4gVmVyc2lvbiAwLjIuMSB5b3UgY2FuIG5vdCByZXNlYXJjaCBsb2dpc3RpLXJvYm90aWNzIGFuZCBTLkEuTS4gYXQgdGhlIHNhbWUgdGltZS4gWW91IG11c3QgY2hvb3NlIGJldHdlZW4gdGhpcyB0ZWNobm9sb2d5cy4gXHJcblxyXG5WZXJzaW9uIDAuMy4wIG9ubHkgZm9yIEZhY3RvcmlvIDAuMTMuWFxyXG5hZGRlZCB0aGUgQ2hlbXBsYW50IHZlcnNpb24gb2YgUy5BLk0uXHJcblJlbW92ZSBEZWNpc2lvbmUgYmV0d2VlbiBTLkEuTS4gYW5kIExvZ2lzdGljIFJvYm90cy5cclxuVmVyc2lvbiAwLjMuMCBpc3QgZWFybHkgQmV0YSwgcGxlYXNlIHJlcG9ydCBhbnkgQnVncyIsImhvbWVwYWdlIjoiIiwicmVsZWFzZXMiOlt7ImlkIjo3MDEsInZlcnNpb24iOiIwLjMuMCIsInJlbGVhc2VkX2F0IjoiMjAxNi0wNy0wM1QwMDowMDowMC4wMDBaIiwiZ2FtZV92ZXJzaW9ucyI6WyIwLjEyLngiXSwiZGVwZW5kZW5jaWVzIjpbXSwiZmlsZXMiOlt7ImlkIjo3MjcsIm5hbWUiOiIiLCJtaXJyb3IiOiJodHRwOi8vczMuYW1hem9uYXdzLmNvbS9mYWN0b3Jpb21vZHMvbW9kX2ZpbGVzL2F0dGFjaG1lbnRzLzAwMC8wMDAvNzI3L29yaWdpbmFsL1N1cGVyX2Fzc2VtYmxpbmdfbWFjaGluZV8wLjMuMC56aXA/MTQ2NzU0NDQzOCIsInVybCI6bnVsbH1dfSx7ImlkIjo2NDIsInZlcnNpb24iOiIwLjIuMSIsInJlbGVhc2VkX2F0IjoiMjAxNi0wNi0wNVQwMDowMDowMC4wMDBaIiwiZ2FtZV92ZXJzaW9ucyI6WyIwLjEyLngiXSwiZGVwZW5kZW5jaWVzIjpbXSwiZmlsZXMiOlt7ImlkIjo2NjYsIm5hbWUiOiIiLCJtaXJyb3IiOiJodHRwOi8vczMuYW1hem9uYXdzLmNvbS9mYWN0b3Jpb21vZHMvbW9kX2ZpbGVzL2F0dGFjaG1lbnRzLzAwMC8wMDAvNjY2L29yaWdpbmFsL1N1cGVyX2Fzc2VtYmxpbmdfbWFjaGluZV8wLjIuMS56aXA/MTQ2NTE4ODU3MiIsInVybCI6bnVsbH1dfSx7ImlkIjo2MTIsInZlcnNpb24iOiIwLjIuMSIsInJlbGVhc2VkX2F0IjoiMjAxNi0wNS0yNFQwMDowMDowMC4wMDBaIiwiZ2FtZV92ZXJzaW9ucyI6WyIwLjEyLngiXSwiZGVwZW5kZW5jaWVzIjpbXSwiZmlsZXMiOlt7ImlkIjo2MzYsIm5hbWUiOiIiLCJtaXJyb3IiOiJodHRwOi8vczMuYW1hem9uYXdzLmNvbS9mYWN0b3Jpb21vZHMvbW9kX2ZpbGVzL2F0dGFjaG1lbnRzLzAwMC8wMDAvNjM2L29yaWdpbmFsL1N1cGVyX2Fzc2VtYmxpbmdfbWFjaGluZV8wLjIuMS56aXA/MTQ2NTE0MDI1OSIsInVybCI6bnVsbH1dfV19"};
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		if(!Main.isURIRegistered(Configuration.uriScheme))
		{
			System.out.println("URI-Scheme is not registered.");
			if(utils.Error.popUpWarning(Main.lang.warningWindowTitle, Main.lang.warningUriSchemeMessage, Main.lang.warningUriSchemeInfo))
			{
				Main.registerUriScheme(Configuration.uriScheme);
				
				if(!Main.isURIRegistered(Configuration.uriScheme))
				{
					System.out.println("Failed to register URI-Scheme.");
					if(utils.Error.popUpWarningDetailed(Main.lang.warningWindowTitle, Main.lang.warningUriSchemeMessage, Main.lang.warningUriSchemeFailedInfo, Main.lang.warningUriSchemeFailedDetail))
					{
						System.exit(0);
					}
				}
			}
			else
			{
				System.out.println("URI-Scheme is not registered.");
				utils.Error.popUpWarning(Main.lang.warningWindowTitle, Main.lang.warningUriSchemeMessage, Main.lang.warningUriSchemeCancleInfo);
			}
		}
		
		if(getParameters().getRaw().size() > 0)
		{
			this.getModFromArg(getParameters().getRaw());
		}
		
		new Gui(primaryStage, getHostServices());
	}
	
	public static void exit(Gui g)
	{
		g.close();
		/*
		 * perform save actions
		 */
		//Main.config.storeConfig();
		//Main.modHandler.storeMods();
	}
	
	/*
	 * Custom URI-Scheme
	 */
	private static boolean isURIRegistered(String uriScheme)
	{
		try
		{
			Process proc = Runtime.getRuntime().exec("cmd /c ftype " + uriScheme);
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s = inputStream.readLine();
			if(s == null){ return false; }
			return s.equals(uriScheme + "=\"" + System.getProperty("java.home") + "\\bin\\javaw.exe\"" + " -jar \"" + Configuration.jarPath + "\" \"%1\"");
		}
		catch (IOException e)
		{
			/*
			 * Log event
			 */
			e.printStackTrace();
		}
		return false;
	}
	
	private static void registerUriScheme(String uriScheme)
	{
		try
		{
			Process proc = Runtime.getRuntime().exec("cmd /c ftype " + uriScheme + "=\"" + System.getProperty("java.home") + "\\bin\\javaw.exe\"" + " -jar \"" + Configuration.jarPath + "\" \"%1\"");
			BufferedReader errorStream = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String s = errorStream.readLine();
			if(s != null)
			{
				s += "\n";
				String tmp = "";
				while ((tmp = errorStream.readLine()) != null)
				{
				    s += tmp + "\n";
				}
				System.out.println("Failed to register URI-Scheme: " + s);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void getModFromArg(List<String> args) throws MalformedURLException
	{
		ArrayList<ModDownloadItem> dl = new ArrayList<>();
		for(int i = 0; i < args.size(); i++)
		{
			if(args.get(i).startsWith("factoriomods://"))
			{
				Mod m = Main.modHandler.getMod(args.get(i).replace("factoriomods://", ""));
				for(ModRelease mR : m.releases)
				{
					dl.add(new ModDownloadItem(m.name ,getHostServices(), mR));
				}
			}
		}
		new DownloadChooser(dl).show();
	}
}