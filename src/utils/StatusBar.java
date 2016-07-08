package utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StatusBar extends StackPane
{
	private Rectangle innerBar;
	private Rectangle outterBar;
	
	public StatusBar()
	{
		this.innerBar = new Rectangle();
		this.outterBar = new Rectangle();
		
		this.innerBar.setFill(Color.DODGERBLUE);
		this.outterBar.setFill(Color.TRANSPARENT);
		this.outterBar.setStroke(Color.GREY);
		this.outterBar.setStrokeWidth(3);
		
		this.innerBar.setWidth(75);
		this.innerBar.setHeight(20);
		this.innerBar.yProperty().add(3);
		
		this.outterBar.setWidth(300);
		this.outterBar.setHeight(20);
		
		StackPane.setMargin(this.innerBar, new Insets(3));
		this.getChildren().addAll(this.innerBar, this.outterBar);
		this.setAlignment(Pos.BASELINE_LEFT);
		this.setPadding(new Insets(30));
	}
	
	public void setStatus(int status)
	{
		if(status > 100)
		{
			status %= 100;
		}
		this.innerBar.setWidth(this.outterBar.getWidth()/100*status);
	}
	
	public double getStatus()
	{
		return (this.innerBar.getWidth()/(this.outterBar.getWidth()/100));
	}
}