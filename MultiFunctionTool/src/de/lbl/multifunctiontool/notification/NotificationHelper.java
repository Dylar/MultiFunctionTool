package de.lbl.multifunctiontool.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import de.lbl.multifunctiontool.R;
import de.lbl.multifunctiontool.draganddropcontainer.DragAndDropContainerActivity;
import de.lbl.multifunctiontool.draganddropspot.DragAndDropSpotActivity;

public class NotificationHelper
{

	public enum LEDColor
	{
		RED, BLUE, GREEN, WHITE;

		public int getColor(LEDColor color)
		{
			int result = -1;
			switch (color)
			{
			// case BLUE:
			// result =
			// break;
			// case GREEN:
			// result =
			// break;
			// case RED:
			// result =
			// break;
			// case WHITE:
			// result =
			// break;
			// default:
			// break;
			}
			return result;
		}
	}

	private static NotificationHelper	nh							= new NotificationHelper();
	private final int							LED_NOTIFICATION_ID	= 0;


	private NotificationHelper()
	{}


	public static NotificationHelper getInstance()
	{
		return nh;
	}


	public void RedFlashLight(Context con)
	{
		NotificationManager nm = (NotificationManager) con.getSystemService(Activity.NOTIFICATION_SERVICE);
		Notification notif = new Notification();
		notif.ledARGB = 0xFFff0000;
		notif.flags = Notification.FLAG_SHOW_LIGHTS;
		notif.ledOnMS = 10;
		notif.ledOffMS = 10;
		nm.notify(LED_NOTIFICATION_ID, notif);
	}


	public void setTestNotification(Context con)
	{
		// prepare intent which is triggered if the
		// notification is selected

		Intent intent1 = new Intent(con, DragAndDropSpotActivity.class);
		intent1.putExtra(NotificationActivity.NOTIFICATION_DATA, "Muuuuh");

		Intent intent2 = new Intent(con, DragAndDropSpotActivity.class);
		intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

		Intent intent3 = new Intent(con, NotificationActivity.class);

		PendingIntent pIntent1 = PendingIntent.getActivity(con, 0, intent1, 0);
		PendingIntent pIntent2 = PendingIntent.getActivity(con, 0, intent2, 0);
		PendingIntent pIntent3 = PendingIntent.getActivity(con, 0, intent3, 0);

		// build notification
		Notification n = new Notification.Builder(con)
		.setContentTitle("Just Testing")
		.setContentText("Subject")
		.setSmallIcon(R.drawable.blackspot)
		.setContentIntent(pIntent3)
		.setAutoCancel(true)
		.addAction(R.drawable.blackspot, "Call", pIntent1)
		.addAction(R.drawable.redspot, "More", pIntent2)
		.addAction(R.drawable.redspot, "And more", pIntent3)
		.setStyle(new Notification.BigTextStyle().bigText("hi was geht das sollte hier eigemntlich län ger sein aber is ja auch shceiss egal was hier steht nur dass der text eben länger ist als man denkt blubb bla ble"))
		.build();
		
		n.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;

		NotificationManager notificationManager = (NotificationManager) con.getSystemService(Context.NOTIFICATION_SERVICE);

		notificationManager.notify(0, n);
	}

}
