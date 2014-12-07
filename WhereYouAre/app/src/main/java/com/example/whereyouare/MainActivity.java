package com.example.whereyouare;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener{
	public LocationManager myManager;
	String SendLocation;
	static TextView messageBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		myManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		messageBox=(TextView)findViewById(R.id.messageBox);
		}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			TextView tx = (TextView)findViewById(R.id.tv1);
			SendLocation=" "+location.getLatitude()+"$"+location.getLongitude()+"$";
			tx.setText("Широта: "   + location.getLatitude()  + "\nДолгота: " + location.getLongitude());
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	public void sendLocation(View V)
	{
		Intent smsIntent = new Intent(Intent.ACTION_VIEW);
		//smsIntent.putExtra("address", "1231231");
		smsIntent.putExtra("sms_body", SendLocation);
		smsIntent.setType("vnd.android-dir/mms-sms");
		startActivity(smsIntent);
	}
	
	  public static void updateMessageBox(String msg)
	    {
	    	messageBox.append(msg);
	    }
}
