package de.lbl.multifunctiontool.notification;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import de.lbl.multifunctiontool.R;

public class NotificationActivity extends ActionBarActivity
{
	
	public static final String NOTIFICATION_DATA = "muh";

	@Override
	public void onCreate(Bundle state)
	{
		super.onCreate(state);
		setContentView(R.layout.activity_notification);

		if (state == null)
		{
			getSupportFragmentManager().beginTransaction().add(R.id.container_notification, new NotificationFragment()).commit();
		}
		else
		{
			Toast.makeText(this, getIntent().getExtras().getString(NOTIFICATION_SERVICE), Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class NotificationFragment extends Fragment
	{

		private TextView	finalResult;


		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_notification, container, false);

			finalResult = (TextView) rootView.findViewById(R.id.tv_result);

			AsyncTaskRunner runner = new AsyncTaskRunner();
			String sleepTime = "10000";
			runner.execute(sleepTime);

			return rootView;
		}


		public void setNotification()
		{
			NotificationHelper.getInstance().setTestNotification(getActivity());
		}

		private class AsyncTaskRunner extends AsyncTask<String, String, String>
		{

			private String	resp;


			@Override
			protected String doInBackground(String... params)
			{
				publishProgress("Sleeping..."); // Calls onProgressUpdate()
				try
				{
					// Do your long operations here and return the result
					int time = Integer.parseInt(params[0]);
					// Sleeping for given time period
					Thread.sleep(time);
					resp = "Slept for " + time + " milliseconds";

					setNotification();

				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
					resp = e.getMessage();
				}
				catch (Exception e)
				{
					e.printStackTrace();
					resp = e.getMessage();
				}
				return resp;
			}


			@Override
			protected void onPostExecute(String result)
			{
				// execution of result of Long time consuming operation
				finalResult.setText(result);
			}


			@Override
			protected void onPreExecute()
			{
				// Things to be done before execution of long running operation. For
				// example showing ProgessDialog
			}


			@Override
			protected void onProgressUpdate(String... text)
			{
				// finalResult.setText(text[0]);
				// // Things to be done while execution of long running operation is
				// in
				// // progress. For example updating ProgessDialog
			}
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
