package de.lbl.multifunctiontool;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.GridView;
import de.lbl.multifunctiontool.dragandrop.DragAndDropAdapter;
import de.lbl.multifunctiontool.dragandrop.DragAndDropData;

public class DragAndDropActivity extends Activity
{

	@Override
	public void onCreate(Bundle state)
	{
		super.onCreate(state);
		setContentView(R.layout.activity_draganddrop);

		GridView gridview = (GridView) findViewById(R.id.dnd_gridview);
		DragAndDropData.getIntance().initiateData(this);
		
		BaseAdapter adapter = new DragAndDropAdapter(this);
		gridview.setAdapter(adapter);

//		gridview.setOnItemClickListener(new OnItemClickListener(){
//			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
//			{
//				
//				Toast.makeText(DragAndDropActivity.this, "" + position, Toast.LENGTH_SHORT).show();
//			}
//		});
	}
}