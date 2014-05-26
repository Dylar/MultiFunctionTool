package de.lbl.multifunctiontool.draganddropcontainer;

import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DragAndDropDragListener implements OnDragListener
{

	@Override
	public boolean onDrag(View v, DragEvent event)
	{
		int action = event.getAction();

		switch (action)
		{

			case DragEvent.ACTION_DRAG_ENTERED:
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				break;
			case DragEvent.ACTION_DROP:
				View view = (View) event.getLocalState();
				ViewGroup owner = (ViewGroup) view.getParent();
				owner.removeView(view);
				LinearLayout container = (LinearLayout) v;
				container.addView(view);
				view.setVisibility(View.VISIBLE);
				break;
			case DragEvent.ACTION_DRAG_ENDED:
			case DragEvent.ACTION_DRAG_STARTED:
			default:
				break;
		}
		return true;
	}
}
