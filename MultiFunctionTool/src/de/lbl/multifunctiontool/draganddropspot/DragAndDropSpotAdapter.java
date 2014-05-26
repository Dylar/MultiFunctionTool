package de.lbl.multifunctiontool.draganddropspot;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import de.lbl.multifunctiontool.R;
import de.lbl.multifunctiontool.draganddropspot.DragAndDropSpotData.SpotColor;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DragAndDropSpotAdapter extends BaseAdapter
{
	private Context					mContext;



	public DragAndDropSpotAdapter(Context c)
	{
		mContext = c;
	}


	@Override
	public int getCount()
	{
		return DragAndDropSpotData.getIntance().dndsd.length;
	}


	@Override
	public Object getItem(int position)
	{
		return DragAndDropSpotData.getIntance().dndsd[position];
	}


	@Override
	public long getItemId(int position)
	{
		return 0;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView imageView = new ImageView(mContext);
		if (convertView == null)
		{ // if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(400, 620));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

			imageView.setImageDrawable(SpotColor.getDrawable(DragAndDropSpotData.getIntance().dndsd[position]));
			DragAndDropSpotListener dndl = new DragAndDropSpotListener();
			imageView.setOnTouchListener(dndl);
			imageView.setOnDragListener(dndl);
			imageView.setId(position);

		}
		else
		{
			imageView = (ImageView) convertView;
		}

		
		return imageView;
	}

}