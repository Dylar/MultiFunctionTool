package de.lbl.multifunctiontool.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import de.lbl.multifunctiontool.main.Helper;

public class MyService extends IntentService
{

	private int						result			= Activity.RESULT_CANCELED;
	public static final String	URL				= "urlpath";
	public static final String	FILENAME			= "filename";
	public static final String	FILEPATH			= "filepath";
	public static final String	RESULT			= "result";
	public static final String	NOTIFICATION	= "Notification";


	public MyService()
	{
		super("MyService");
	}

	@Override
   public int onStartCommand(Intent intent, int flags, int startId) {
       super.onStartCommand(intent, startId, startId);
       Log.d("Service", "Received start id " + startId + ": " + intent);

       return START_STICKY;
   }
	

	// will be called asynchronously by Android
	@Override
	protected void onHandleIntent(Intent intent)
	{
		String urlPath = intent.getStringExtra(URL);
		String fileName = intent.getStringExtra(FILENAME);

		try
		{


         URL url = new URL(urlPath);
         URLConnection conn = url.openConnection();
         Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
         
         Helper.saveToSdCard(bitmap, fileName);
         
			// successfully finished
			result = Activity.RESULT_OK;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
			
		
		publishResults(Environment.getExternalStorageDirectory().getPath(), result);
	}

	
	

	private void publishResults(String outputPath, int result)
	{
		Intent intent = new Intent(NOTIFICATION);
		intent.putExtra(FILEPATH, outputPath);
		intent.putExtra(RESULT, result);
		sendBroadcast(intent);
	}
}