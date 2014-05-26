package de.lbl.multifunctiontool.draganddropspot;

import java.io.IOException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import de.lbl.multifunctiontool.R;

public class DragAndDropSpotActivity extends ActionBarActivity
{

	@Override
	public void onCreate(Bundle state)
	{
		super.onCreate(state);
		setContentView(R.layout.activity_draganddropspot);
		
		if (state == null)
		{
			getSupportFragmentManager().beginTransaction().add(R.id.container_draganddropspot, new DragAndDropSpotFragment()).commit();
		}

		

//		gridview.setOnItemClickListener(new OnItemClickListener(){
//			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
//			{
//				
//				Toast.makeText(DragAndDropActivity.this, "" + position, Toast.LENGTH_SHORT).show();
//			}
//		});
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
			
			GridView gridview = (GridView) rootView.findViewById(R.id.dnd_gridview);
			DragAndDropSpotData.getIntance().initiateData(getActivity());
			
			BaseAdapter adapter = new DragAndDropSpotAdapter(getActivity());
			gridview.setAdapter(adapter);
			
			return rootView;
		}

	}
}
