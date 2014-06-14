package de.lbl.multifunctiontool.draganddropspot;

import java.io.IOException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;
import de.lbl.multifunctiontool.R;
import de.lbl.multifunctiontool.notification.NotificationActivity;

public class DragAndDropSpotActivity extends ActionBarActivity
{

	public static int id = 0;
	@Override
	public void onCreate(Bundle state)
	{
		super.onCreate(state);
		setContentView(R.layout.activity_draganddropspot);
		
		if (state == null)
		{
			getSupportFragmentManager().beginTransaction().add(R.id.container_draganddropspot, new DragAndDropSpotFragment()).commit();
		}
		
		id++;
	}
	
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class DragAndDropSpotFragment extends Fragment
	{

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_draganddropspot, container, false);
			
			GridView gridview = (GridView) rootView.findViewById(R.id.dnds_gridview);
			DragAndDropSpotData.getIntance().initiateData(getActivity());
			
			BaseAdapter adapter = new DragAndDropSpotAdapter(getActivity());
			gridview.setAdapter(adapter);
			
			return rootView;
		}

	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
		    String notificationData = bundle.getString(NotificationActivity.NOTIFICATION_DATA);
		    if (notificationData != null) {
		        Log.d("detectPushNotificationMessage", "notificationData =" + notificationData);
		        handlePushNotificationMessage(notificationData);
		    }

		}
	}


	private void handlePushNotificationMessage(String notificationData)
	{
		Toast.makeText(this, notificationData, Toast.LENGTH_LONG).show();
	}
}
