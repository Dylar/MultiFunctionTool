package de.lbl.multifunctiontool.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import de.lbl.multifunctiontool.main.MainActivity.Looks;

public class Helper
{
	public static String	rootPackagePath	= "de.lbl.multifunctiontool.";
	public static String	list[]				= { "Main", "DragAndDropSpot", "DragAndDropContainer", "Asynctask", "Service", "Notification", "Intent" };


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


	public static Drawable getDrawableFromPath(String path)
	{
		return Drawable.createFromPath(path);
	}


	public static void saveToSdCard(Bitmap bitmap, String filename)
	{

		File sdcard = Environment.getExternalStorageDirectory();
		
		Log.d("helper", sdcard.getAbsolutePath());

		File folder = new File(sdcard.getAbsoluteFile(), "your_specific_directory");
		folder.mkdir();

		Log.d("helper", folder.getAbsolutePath());
		File file = new File(folder.getAbsoluteFile(), filename);

		try
		{
			FileOutputStream out = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public static void openLook(Context con, Looks look)
	{
		Intent i = null;
		switch (look)
		{
			case FRAGMENT:
				break;
			case LISTVIEW:
				i = new Intent(con, ListViewActivity.class);
				con.startActivity(i);
				break;
			case TABHOST:
				break;
			default:
				break;
		}
	}

}
