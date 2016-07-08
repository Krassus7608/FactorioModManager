package gui;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

public class Downloader
{
	//JavaFX
	private Stage stage;
	private Scene scene;
	private BorderPane root;
	private VBox centerBox;
	private ProgressBar progressBar;
	private Label label;

	private URL url;
	private HttpURLConnection http;
	private String save;
	private long fileSize;
	private long dlSize;
	
	private Runnable dlThread;
	
	public Downloader(String url, String save) throws MalformedURLException
	{
		this.root = new BorderPane();
		this.root.setPadding(new Insets(30));
		
		this.centerBox = new VBox();
		this.root.setCenter(this.centerBox);
		this.scene = new Scene(this.root);
		this.stage = new Stage();
		this.stage.setScene(this.scene);
		this.stage.initModality(Modality.APPLICATION_MODAL);
		
		this.progressBar = new ProgressBar(-1);
		this.progressBar.setPrefHeight(30);
		this.progressBar.setPrefWidth(400);
		this.label = new Label("Downloading...");
		this.label.setFont(Font.font("Lucida Sans Unicode", 10));
		this.centerBox.getChildren().addAll(this.progressBar, this.label);
		
		this.url = new URL(url);
		this.save = save;
		
		this.dlThread = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Downloader.this.http = (HttpURLConnection) Downloader.this.url.openConnection();
					BufferedInputStream inputStream = new BufferedInputStream(Downloader.this.http.getInputStream());
					FileOutputStream outputStream = new FileOutputStream(Downloader.this.save);
					
					byte[] part = new byte[1024];
					Downloader.this.dlSize = Downloader.this.http.getContentLength();
					Downloader.this.fileSize = 0;
					int pos = 0;
					while((pos = inputStream.read(part, 0, 1024)) >= 0)
					{
						Downloader.this.fileSize += pos;
						Platform.runLater(new Runnable(){
							@Override
							public void run() {
								Downloader.this.label.setText("Downloading... " + ((int)((double)Downloader.this.fileSize)/1000) + "kB / " + ((int)((double)Downloader.this.dlSize)/1000 + "kB") );
								Downloader.this.progressBar.setProgress(((double)Downloader.this.fileSize)/((double)Downloader.this.dlSize));
							}});
						outputStream.write(part);
					}
					Platform.runLater(new Runnable(){
						@Override
						public void run() {
							Downloader.this.label.setText("Downloading finished: " + ((int)((double)Downloader.this.fileSize)/1000) + "kB / " + ((int)((double)Downloader.this.dlSize)/1000 + "kB"));
							Downloader.this.progressBar.setProgress(1);
						}});
					inputStream.close();
					outputStream.close();
				}
				catch (IOException e)
				{
					Platform.runLater(new Runnable()
					{
						@Override
						public void run()
						{
							utils.Error.popUpErrorDetailed(Main.lang.errorWindowTitle, Main.lang.errorDownloadMessage, Main.lang.errorDownloadInfo, e.getMessage(), f -> {});
							Downloader.this.stage.close();
						}
					});
				}
			}
			
		};
	}
	
	public void setTitle(String title)
	{
		this.stage.setTitle(title);
	}
	
	public void setIcon(Image i)
	{
		this.stage.getIcons().add(i);
	}
	
	public void download()
	{
		new Thread(this.dlThread).start();
		this.stage.showAndWait();
	}
}