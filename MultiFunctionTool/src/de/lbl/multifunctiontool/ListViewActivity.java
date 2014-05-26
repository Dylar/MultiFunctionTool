package de.lbl.multifunctiontool;

import de.lbl.multifunctiontool.main.Helper;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends ListActivity
{

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Helper.list));
		
	}


	@Override
	protected void onListItemClick(ListView list, View view, int position, long id)
	{
		super.onListItemClick(list, view, position, id);
		String name = Helper.list[position];
		try
		{
			StringBuilder sb = new StringBuilder(Helper.rootPackagePath)
			.append(name.toLowerCase())
			.append(".")
			.append(name)
			.append("Activity");
			
			Log.d("MUH", sb.toString());
			
			Class clazz = Class.forName(sb.toString());
			Intent intent = new Intent(this, clazz);
			startActivity(intent);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
