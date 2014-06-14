package de.lbl.multifunctiontool.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import de.lbl.multifunctiontool.R;

public class IntentActivity extends ActionBarActivity
{

	public static final String	EXTRA_KEY_SEND		= "sendKey";
	public static final String	EXTRA_KEY_RETURN	= "returnKey";
	public static final String	EXTRA_KEY_START_IMPLICIT	= "Start";

	private static final int	REQUEST_CODE		= 10;


	@Override
	public void onCreate(Bundle state)
	{
		super.onCreate(state);
		setContentView(R.layout.activity_intent);

		if (state == null)
		{
			getSupportFragmentManager().beginTransaction().add(R.id.container_intent, new IntentFragment()).commit();
		}
	}


	public void onClick(View view)
	{
		EditText text = (EditText) findViewById(R.id.inputforintent);
		String string = text.getText().toString();
		if(string.equals(EXTRA_KEY_START_IMPLICIT))
			startActivity(new Intent(this, CallIntentsActivity.class));
		else{
		Intent i = new Intent(this, ResultActivity.class);
		i.putExtra(EXTRA_KEY_SEND, string);
		startActivityForResult(i, REQUEST_CODE);}
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE)
		{
			if (data.hasExtra(EXTRA_KEY_RETURN))
			{
				String result = data.getExtras().getString(EXTRA_KEY_RETURN);
				if (result != null && result.length() > 0)
				{
					Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class IntentFragment extends Fragment
	{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_intent, container, false);

			return rootView;
		}

	}
}
