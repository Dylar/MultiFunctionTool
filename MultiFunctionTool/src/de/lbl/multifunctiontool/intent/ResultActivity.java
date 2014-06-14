package de.lbl.multifunctiontool.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import de.lbl.multifunctiontool.R;

public class ResultActivity extends Activity
{

	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.activity_result);
		Bundle extras = getIntent().getExtras();
		String inputString = extras.getString(IntentActivity.EXTRA_KEY_SEND);
		TextView view = (TextView) findViewById(R.id.displayintentextra);
		view.setText(inputString);
	}


	@Override
	public void finish()
	{
		Intent intent = new Intent();
		EditText editText = (EditText) findViewById(R.id.returnValue);
		String string = editText.getText().toString();
		intent.putExtra(IntentActivity.EXTRA_KEY_RETURN, string);
		setResult(RESULT_OK, intent);
		super.finish();
	}
}