package de.lbl.multifunctiontool.tabhost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import de.lbl.multifunctiontool.R;

public class Tab1 extends Fragment implements OnClickListener
{
	View rootView;

	public void onClick(View p1)
	{
		test(p1);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }
		rootView = inflater.inflate(R.layout.tab1, container, false);

        rootView.findViewById(R.id.tab1ButtonTest).setOnClickListener(this);
        return (LinearLayout) rootView;
		
    }
	public void test(View v){
		((Button)v).setText("muh");
	}
}
