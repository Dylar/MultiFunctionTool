package de.lbl.multifunctiontool.dragandrop;

import de.lbl.multifunctiontool.dragandrop.DragAndDropData.SpotColor;
import android.content.Context;
import android.graphics.drawable.Drawable;

public class DragAndDropSpotData
{

	
	private SpotColor	color;

	
	public DragAndDropSpotData(SpotColor color)
	{
		this.color = color;
	}


	public SpotColor getColor()
	{
		return color;
	}
	
	public void setColor(SpotColor color)
	{
		this.color = color;
	}
	
	
	
}
