package de.lbl.multifunctiontool.basics;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import de.lbl.multifunctiontool.ListViewActivity;
import de.lbl.multifunctiontool.MainActivity.Looks;

public class Helper
{
	public static String rootPackagePath = "de.lbl.multifunctiontool.";
	public static String list[] = {"Main", "DragAndDrop"};
	
	public static Bitmap getBitmapFromAsset(Context con, String strName) throws IOException
	{
		AssetManager assetManager = con.getAssets();
		InputStream istr = assetManager.open(strName);
		Bitmap bitmap = BitmapFactory.decodeStream(istr);
		return bitmap;
	}
	
	public static Drawable getDrawableFromAsset(Context con, String strName) throws IOException
	{
		AssetManager assetManager = con.getAssets();
		InputStream istr = assetManager.open(strName);
		Drawable drawable = Drawable.createFromStream(istr, strName);
		return drawable;
	}
	
	public static void openLook(Context con,Looks look)
	{
		Intent i = null;
		switch(look){
			case FRAGMENT:
				break;
			case LISTVIEW:
				i = new Intent(con, ListViewActivity.class);
				con.startActivity(i);
				break;
			case TABHOST:
				break;
			default:
				break;}
	}

}
