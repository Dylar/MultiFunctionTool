package de.lbl.multifunctiontool.dragandrop;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.os.Build;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import de.lbl.multifunctiontool.dragandrop.DragAndDropSpotData.SpotColor;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DragAndDropSpotListener implements OnTouchListener,OnDragListener
{

	@Override
	public boolean onTouch(View v, MotionEvent me)
	{
		if (me.getAction() == MotionEvent.ACTION_DOWN)
		{
			ClipData data = ClipData.newPlainText("", "");
			DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
			v.startDrag(data, shadowBuilder, v, 0);
			// v.setVisibility(View.INVISIBLE);
			return true;
		}
		else
		{
			return false;
		}
	}


	@Override
	public boolean onDrag(View v, DragEvent event)
	{
		int action = event.getAction();
		ImageView thisView = (ImageView) v;
		ImageView draggedView = (ImageView) event.getLocalState();
		SpotColor thisColor = DragAndDropSpotData.getIntance().dndsd[thisView.getId()];
		SpotColor draggedColor = DragAndDropSpotData.getIntance().dndsd[draggedView.getId()];

		switch (action)
		{

			case DragEvent.ACTION_DRAG_ENTERED:
				if (thisColor.equals(draggedColor))
					thisView.setImageDrawable(SpotColor.getDrawable(SpotColor.RED));
				else
					thisView.setImageDrawable(SpotColor.getDrawable(SpotColor.GREEN));
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				thisView.setImageDrawable(SpotColor.getDrawable(thisColor));
				break;
			case DragEvent.ACTION_DROP:

				if (!thisColor.equals(draggedColor))
				{
					DragAndDropSpotData.getIntance().setColor(thisView.getId(), draggedColor);
					thisView.setImageDrawable(SpotColor.getDrawable(draggedColor));
				}
				else
					thisView.setImageDrawable(SpotColor.getDrawable(thisColor));
				break;
			case DragEvent.ACTION_DRAG_ENDED:
			case DragEvent.ACTION_DRAG_STARTED:
			default:
				break;
		}
		return true;
	}


	private void turnRed()
	{

	}
}
