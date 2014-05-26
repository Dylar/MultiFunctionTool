package de.lbl.multifunctiontool;

import de.lbl.multifunctiontool.basics.Helper;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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
		String testName = Helper.list[position];
		try
		{
			Class clazz = Class.forName(Helper.rootPackagePath + testName + "Activity");
			Intent intent = new Intent(this, clazz);
			startActivity(intent);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
