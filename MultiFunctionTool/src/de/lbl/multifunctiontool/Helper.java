package de.lbl.multifunctiontool;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Helper
{

	
	public static Bitmap getBitmapFromAsset(Context con, String strName) throws IOException
	{
		AssetManager assetManager = con.getAssets();
		InputStream istr = assetManager.open(strName);
		Bitmap bitmap = BitmapFactory.decodeStream(istr);
		return bitmap;
	}
	
}
