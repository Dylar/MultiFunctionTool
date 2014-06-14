package de.lbl.multifunctiontool.tabhost;

import android.content.*;
import android.hardware.*;
import android.widget.*;

public class GestureDetector
{
	private Context con;

	private SensorManager mSensorManager;
	public float mAccel; // acceleration apart from gravity
	private float mAccelCurrent; // current acceleration including gravity
	private float mAccelLast; // last acceleration including gravity
	
	private Thread shakeThread;

	private final SensorEventListener mSensorListener = new SensorEventListener() {

		public void onSensorChanged(SensorEvent se) {
			float x = se.values[0];
			float y = se.values[1];
			float z = se.values[2];
			mAccelLast = mAccelCurrent;
			mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
			float delta = mAccelCurrent - mAccelLast;
			mAccel = mAccel * 0.9f + delta; // perform low-cut filter
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
	

	public GestureDetector(Context con){
		this.con = con;
		mSensorManager = (SensorManager) con.getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		mAccel = 0.00f;
		mAccelCurrent = SensorManager.GRAVITY_EARTH;
		mAccelLast = SensorManager.GRAVITY_EARTH;
	}
	
	protected void onResume() {
		mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	}


	protected void onPause() {
		mSensorManager.unregisterListener(mSensorListener);
	}
	
	public void m(String s){
		Toast.makeText(con,s,Toast.LENGTH_SHORT);
	}
	
	public void startShakeDetection(){
		shakeThread = new Thread(){
			public void run(){
				while(true){
					if(mAccel > 2){
						m("shake it");
					}
				}
			}
		};
	}
}
