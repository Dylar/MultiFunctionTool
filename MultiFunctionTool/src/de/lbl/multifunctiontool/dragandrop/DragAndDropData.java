package de.lbl.multifunctiontool.dragandrop;

import java.io.IOException;

import android.content.Context;
import android.graphics.drawable.Drawable;
import de.lbl.multifunctiontool.R;
import de.lbl.multifunctiontool.basics.Helper;
import de.lbl.multifunctiontool.dragandrop.DragAndDropData.SpotColor;

public class DragAndDropData
{
	public enum SpotColor
	{
		RED, BLACK, GREEN, YELLOW;

		@Override
		public String toString()
		{
			switch (this)
			{
				case BLACK:
					return "BLACK";
				case RED:
					return "RED";
				case GREEN:
					return "GREEN";
				case YELLOW:
					return "YELLOW";
				default:
					break;
			}
			return null;
		}


		public static SpotColor getColor(String color)
		{
			if (color.equals(RED.toString()))
				return RED;
			else
				if (color.equals(BLACK.toString()))
					return BLACK;
				else
					if (color.equals(GREEN.toString()))
						return GREEN;
					else
						return YELLOW;

		}


		public static Drawable getDrawable(SpotColor color)
		{
			Drawable result = null;
			try
			{
				switch (color)
				{
					case BLACK:
						result = DragAndDropData.getIntance().con.getResources().getDrawable(R.drawable.blackspot);
						break;
					case GREEN:
						result = Helper.getDrawableFromAsset(DragAndDropData.getIntance().con, "greenspot.jpg");
						break;
					case RED:
						result = DragAndDropData.getIntance().con.getResources().getDrawable(R.drawable.redspot);
						break;
					case YELLOW:
						result = Helper.getDrawableFromAsset(DragAndDropData.getIntance().con, "yellowspot.png");
						break;
					default:
						break;
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return result;
		}
	}
	
	

	private static DragAndDropData	instance	= new DragAndDropData();
	public Context							con;

	public DragAndDropSpotData[]		dndsd		= new DragAndDropSpotData[9];


	public static DragAndDropData getIntance()
	{
		return instance;
	}


	public void initiateData(Context con)
	{
		this.con = con;
		for (int i = 0; i < 8; i++)
		{
			dndsd[i] = new DragAndDropSpotData(SpotColor.BLACK);
		}
		dndsd[8] = new DragAndDropSpotData(SpotColor.YELLOW);

	}


	public void setColor(int id, SpotColor draggedColor)
	{
		dndsd[id].setColor(draggedColor);
	}
}
