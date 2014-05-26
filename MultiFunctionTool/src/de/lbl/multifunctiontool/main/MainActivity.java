package de.lbl.multifunctiontool.main;

import java.io.IOException;

import de.lbl.multifunctiontool.R;
import de.lbl.multifunctiontool.R.color;
import de.lbl.multifunctiontool.R.id;
import de.lbl.multifunctiontool.R.layout;
import de.lbl.multifunctiontool.R.menu;
import de.lbl.multifunctiontool.R.string;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends ActionBarActivity implements OnMenuItemClickListener
{
	
	public enum Looks
	{
		LISTVIEW,TABHOST,FRAGMENT
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null)
		{
			getSupportFragmentManager().beginTransaction().add(R.id.container_main, new PlaceholderFragment()).commit();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	public void openAlertDialog(View v)
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		String[] items = {getResources().getString(R.string.action_listviewact),getResources().getString(R.string.action_hosttabact),getResources().getString(R.string.action_fragmentact)};
		
		// set title
		alertDialogBuilder.setTitle("Options")
		.setCancelable(true)
		.setItems(items, new DialogInterface.OnClickListener(){
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				switch(which)
				{
					case 0:
						Helper.openLook(MainActivity.this, Looks.LISTVIEW);
						break;
					case 1:
						Helper.openLook(MainActivity.this, Looks.TABHOST);
						break;
					case 2:
						Helper.openLook(MainActivity.this, Looks.FRAGMENT);
						break;
				}
				Toast.makeText(MainActivity.this, ""+which, Toast.LENGTH_SHORT).show();
				dialog.cancel();
			}
		})
		.show();
	}


	public void openPopMenu(View v)
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) // TODO api lvl check
		{
			PopupMenu popMenu = new PopupMenu(this, v);
			MenuInflater inflater = popMenu.getMenuInflater();
			inflater.inflate(R.menu.main, popMenu.getMenu());
			popMenu.show();
			popMenu.setOnMenuItemClickListener(this);
		}
		else
			Toast.makeText(this, "Sorry low API", Toast.LENGTH_SHORT).show();
	}


	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
	}


	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		return onOptionsItemSelected(item);
	}


	@Override
	public boolean onMenuItemClick(MenuItem item)
	{
		return onOptionsItemSelected(item);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();

		switch (id)
		{
			case R.id.action_settings:
				return true;
			case R.id.action_listviewact:
				Helper.openLook(this, Looks.LISTVIEW);
				return true;
			case R.id.action_hosttabact:
				Helper.openLook(this, Looks.TABHOST);
				return true;
			case R.id.action_fragementact:
				Helper.openLook(this, Looks.FRAGMENT);
				return true;
		}

		return super.onOptionsItemSelected(item);
	}
	
	


	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment
	{

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			Bitmap bm;
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			ImageView i = (ImageView) rootView.findViewById(R.id.main_background_image);
			TextView greetingstv = (TextView) rootView.findViewById(R.id.main_greetings_textview);

			try
			{

				bm = Helper.getBitmapFromAsset(getActivity(), "activity_main_background.png");

				i.setImageBitmap(bm);

				i.setScaleType(ScaleType.FIT_XY); // TODO ImageScale
				i.setBackgroundColor(getResources().getColor(R.color.sysBlack)); // TODO
																										// background
																										// Color

				Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Starcraft.ttf");
				greetingstv.setTypeface(font); // TODO custom font

				registerForContextMenu(greetingstv); // TODO contextMenu
																	// regestrieren

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			return rootView;
		}

	}

}
