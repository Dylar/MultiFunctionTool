package de.lbl.multifunctiontool.draganddropspot;

import java.io.IOException;

import android.content.Context;
import android.graphics.drawable.Drawable;
import de.lbl.multifunctiontool.R;
import de.lbl.multifunctiontool.draganddropspot.DragAndDropSpotData.SpotColor;
import de.lbl.multifunctiontool.main.Helper;

public class DragAndDropSpotData
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
						result = DragAndDropSpotData.getIntance().con.getResources().getDrawable(R.drawable.blackspot);
						break;
					case GREEN:
						result = Helper.getDrawableFromAsset(DragAndDropSpotData.getIntance().con, "greenspot.jpg");
						break;
					case RED:
						result = DragAndDropSpotData.getIntance().con.getResources().getDrawable(R.drawable.redspot);
						break;
					case YELLOW:
						result = Helper.getDrawableFromAsset(DragAndDropSpotData.getIntance().con, "yellowspot.png");
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
	
	

	private static DragAndDropSpotData	instance	= new DragAndDropSpotData();
	public Context							con;

	public SpotColor[]		dndsd		= new SpotColor[9];


	public static DragAndDropSpotData getIntance()
	{
		return instance;
	}


	public void initiateData(Context con)
	{
		this.con = con;
		for (int i = 0; i < 8; i++)
		{
			dndsd[i] = SpotColor.BLACK;
		}
		dndsd[8] = SpotColor.YELLOW;

	}


	public void setColor(int id, SpotColor draggedColor)
	{
		dndsd[id] = draggedColor;
	}
}
