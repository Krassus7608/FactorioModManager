package gui;

import java.net.MalformedURLException;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.Configuration;
import main.Main;
import modhandler.ModRelease;

public class ModDownloadItem extends GridPane
{
	private Label version;
	private Label release;
	private Hyperlink[] dlLink;
	private Button[] dlButton;
	private Downloader[] downloader;
	private EventHandler<ActionEvent> onClose;
	
	public ModDownloadItem(String name, HostServices hS, ModRelease mR) throws MalformedURLException
	{
		this.setPadding(new Insets(10));
		this.onClose = new EventHandler<ActionEvent>(){ @Override public void handle(ActionEvent event){} };
		
		this.version = new Label(Main.lang.modVersion + ": " + mR.version);
		this.add(this.version, 0, 0);
		
		this.release = new Label(Main.lang.modRelease + ": " + mR.release);
		this.add(this.release, 1, 0);
		
		this.dlLink = new Hyperlink[mR.files.length];
		this.dlButton = new Button[mR.files.length];
		this.downloader = new Downloader[mR.files.length];
		for(int i = 0; i < this.dlLink.length; i++)
		{
			final int x = i;
			this.dlLink[i] = new Hyperlink(Main.lang.otherDownloadFromWebsite);
			this.dlLink[i].setOnAction(e -> { hS.showDocument(mR.files[x].mirror); });
			
			this.downloader[i] = new Downloader(mR.files[i].mirror, Main.config.factorioModFolder + "/" + name + ".zip");
			this.downloader[i].setIcon(Configuration.icon);
			this.downloader[i].setTitle(Configuration.name + " - " + Main.lang.otherDownload);
			
			this.dlButton[i] = new Button(Main.lang.otherDownload);
			this.dlButton[i].setOnAction(e -> { ModDownloadItem.this.downloader[x].download(); ModDownloadItem.this.onClose.handle(new ActionEvent()); });
			this.add(this.dlLink[i], 0,i+1);
			this.add(this.dlButton[i], 1,i+1);
		}
	}
	
	public void setOnClose(EventHandler<ActionEvent> a)
	{
		this.onClose = a;
	}
}