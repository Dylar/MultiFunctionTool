package de.lbl.multifunctiontool.asynctask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.lbl.multifunctiontool.R;

public class AsynctaskActivity extends ActionBarActivity
{
	@Override
	public void onCreate(Bundle state)
	{
		super.onCreate(state);
		setContentView(R.layout.activity_asynctask);
		
		if (state == null)
		{
			getSupportFragmentManager().beginTransaction().add(R.id.container_asynctask, new AsynctaskFragment()).commit();
		}
	}
	
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class AsynctaskFragment extends Fragment
	{

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_asynctask, container, false);

			return rootView;
		}

	}
	
}

