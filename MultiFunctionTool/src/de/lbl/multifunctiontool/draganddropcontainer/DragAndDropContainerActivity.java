package de.lbl.multifunctiontool.draganddropcontainer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import de.lbl.multifunctiontool.R;

public class DragAndDropContainerActivity extends ActionBarActivity
{

	@Override
	public void onCreate(Bundle state)
	{
		super.onCreate(state);
		setContentView(R.layout.activity_draganddropcontainer);
		
		if (state == null)
		{
			getSupportFragmentManager().beginTransaction().add(R.id.container_draganddropcontainer, new DragAndDropContainerFragment()).commit();
		}

		
	}
	
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class DragAndDropContainerFragment extends Fragment
	{

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_draganddropcontainer, container, false);

			LinearLayout topleft = (LinearLayout) rootView.findViewById(R.id.dndc_topleft_container);
			LinearLayout topright = (LinearLayout) rootView.findViewById(R.id.dndc_topright_container);
			LinearLayout bottomleft = (LinearLayout) rootView.findViewById(R.id.dndc_bottomleft_container);
			LinearLayout bottomright = (LinearLayout) rootView.findViewById(R.id.dndc_bottomright_container);

			topleft.setOnDragListener(new DragAndDropDragListener());
			topright.setOnDragListener(new DragAndDropDragListener());
			bottomleft.setOnDragListener(new DragAndDropDragListener());
			bottomright.setOnDragListener(new DragAndDropDragListener());
			

			ImageView img1 = (ImageView) rootView.findViewById(R.id.myimage1);
			ImageView img2 = (ImageView) rootView.findViewById(R.id.myimage2);
			ImageView img3 = (ImageView) rootView.findViewById(R.id.myimage3);
			ImageView img4 = (ImageView) rootView.findViewById(R.id.myimage4);
			ImageView img5 = (ImageView) rootView.findViewById(R.id.myimage5);
			ImageView img6 = (ImageView) rootView.findViewById(R.id.myimage6);

			img1.setOnTouchListener(new DragAndDropTouchListener());
			img2.setOnTouchListener(new DragAndDropTouchListener());
			img3.setOnTouchListener(new DragAndDropTouchListener());
			img4.setOnTouchListener(new DragAndDropTouchListener());
			img5.setOnTouchListener(new DragAndDropTouchListener());
			img6.setOnTouchListener(new DragAndDropTouchListener());
			
//			DragAndDropContainerData.getIntance().initiateData(getActivity());
//			
//			BaseAdapter adapter = new DragAndDropContainerAdapter(getActivity());
//			gridview.setAdapter(adapter);
//			
			return rootView;
		}

	}
	
//	java.lang.RuntimeException: Unable to start activity ComponentInfo{de.lbl.multifunctiontool/de.lbl.multifunctiontool.draganddropcontainer.DragAndDropContainerActivity}: java.lang.IllegalArgumentException: No view found for id 0x7f05003c (de.lbl.multifunctiontool:id/container_draganddropcontainer) for fragment DragAndDropContainerFragment{42e5f808 #0 id=0x7f05003c}
}
