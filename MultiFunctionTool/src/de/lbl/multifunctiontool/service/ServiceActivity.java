package de.lbl.multifunctiontool.service;

import java.io.File;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import de.lbl.multifunctiontool.R;

public class ServiceActivity extends ActionBarActivity
{

	private ServiceFragment	fragment;

	public Handler				handler;


	@Override
	public void onCreate(Bundle state)
	{
		super.onCreate(state);
		setContentView(R.layout.activity_service);

		handler = new Handler();

		if (state == null)
		{
			fragment = new ServiceFragment();
			getSupportFragmentManager().beginTransaction().add(R.id.container_service, fragment).commit();
		}
	}


	public Handler getHandler()
	{
		return handler;
	}


	public void onClick(View v)
	{
		Log.d("service", "onclick");
		fragment.onClick(v);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class ServiceFragment extends Fragment
	{
		private TextView				startView;
		private TextView				endView;
		private ProgressBar			spinner;

		private BroadcastReceiver	receiver	= new BroadcastReceiver(){

															@Override
															public void onReceive(Context context, Intent intent)
															{
																Bundle bundle = intent.getExtras();
																if (bundle != null)
																{
																	String filepath = bundle.getString(MyService.FILEPATH);

																	Log.d("Service", filepath);
																	int resultCode = bundle.getInt(MyService.RESULT);
																	if (resultCode == RESULT_OK)
																	{
																		Toast.makeText(ServiceFragment.this.getActivity(), "Download complete. Download URI: " + filepath, Toast.LENGTH_LONG).show();
																		endView.setText(DateFormat.format("yyyy-MM-dd HH:mm:ss.SSSZ", System.currentTimeMillis()));

																		LinearLayout container = (LinearLayout) ServiceFragment.this.getActivity().findViewById(R.id.service_container);
																		ImageView v = (ImageView) ServiceFragment.this.getActivity().findViewById(R.id.service_image);
																		// v.setImageDrawable(Drawable.createFromPath(filepath));

																		container.removeView(spinner);

																		File imgFile = new File(filepath + "/testbild.jpg");
																		Bitmap myBitmap = BitmapFactory.decodeFile(filepath + "/testbild.jpg");
//
																		v.setImageBitmap(myBitmap);

																	}
																	else
																	{
																		Toast.makeText(ServiceFragment.this.getActivity(), "Download failed", Toast.LENGTH_LONG).show();
																		endView.setText("Download failed");
																	}
																}
															}

														};


		public void onClick(View view)
		{

			Intent intent = new Intent(getActivity(), MyService.class);
			// add infos for the service which file to download and where to store
			intent.putExtra(MyService.FILENAME, "testbild.jpg");
			intent.putExtra(MyService.URL, "http://www.softpanorama.org/Security/Internet_privacy/Images/is_google_evil.jpg");
			getActivity().startService(intent);
			startView.setText(DateFormat.format("yyyy-MM-dd HH:mm:ss.SSSZ", System.currentTimeMillis()));

			spinner.setVisibility(View.VISIBLE);
			Log.d("service", "onclick");
		}


		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_service, container, false);

			startView = (TextView) rootView.findViewById(R.id.service_status_start);
			endView = (TextView) rootView.findViewById(R.id.service_status_end);

			spinner = (ProgressBar) rootView.findViewById(R.id.service_loadingspinner);
			spinner.setVisibility(View.GONE);

			return rootView;
		}


		@Override
		public void onResume()
		{
			super.onResume();
			getActivity().registerReceiver(receiver, new IntentFilter(MyService.NOTIFICATION));
		}


		@Override
		public void onPause()
		{
			super.onPause();
			getActivity().unregisterReceiver(receiver);
		}
	}

}
