//note to self: this is broken as shit... something in on touch .

package com.colin.wielga;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class Maps2Activity extends MapActivity implements LocationListener {
	/** Called when the activity is first created. */

	MapView map;
	Long start;
	Long stop = (long) 0;
	MyLocationOverlay compass;
	MapController controller;
	int x, y;
	Drawable d;
	List<Overlay> overlaylist;
	LocationManager lm;
	String towers;
	int lat = 0;
	int lon = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		map = (MapView) findViewById(R.id.mvMain);
		map.setBuiltInZoomControls(true);

		Touchy t = new Touchy();
		overlaylist = map.getOverlays();
		overlaylist.add(t);

		compass = new MyLocationOverlay(Maps2Activity.this, map);
		overlaylist.add(compass);

		compass.disableCompass();
		// compass.enableCompass();

		controller = map.getController();
		GeoPoint point = new GeoPoint(51634579, 78233456);
		controller.animateTo(point);
		controller.setZoom(6);

		d = getResources().getDrawable(R.drawable.ic_launcher);

		// makes a pinpoint at our location
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Criteria crit = new Criteria();

		towers = lm.getBestProvider(crit, false);
		Location location = lm.getLastKnownLocation(towers);

		if (location != null) {
			lat = (int) (location.getLatitude()*1E6);
			lon = (int) (location.getLongitude()*1E6);
			GeoPoint ourLoc = new GeoPoint(lat, lon);
			OverlayItem overlayItem = new OverlayItem(ourLoc, "whats up",
					"second str");
			CustomPinpoint custom = new CustomPinpoint(d, Maps2Activity.this);
			custom.insertPinpoint(overlayItem);
			overlaylist.add(custom);
		} else {
			Toast.makeText(getBaseContext(), "cound not get provider",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		compass.disableCompass();
		super.onPause();
		lm.removeUpdates(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		// compass.enableCompass();
		super.onResume();
		lm.requestLocationUpdates(towers, 500, 1, this);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	class Touchy extends Overlay {
		GeoPoint touchpoint;

		public boolean onTouchEvent(MotionEvent e, MapView m) {
			if (e.getAction() == MotionEvent.ACTION_DOWN) {
				start = e.getEventTime();
				x = (int) e.getX();
				y = (int) e.getY();
				touchpoint = map.getProjection().fromPixels(x, y);

			}
			if (e.getAction() == MotionEvent.ACTION_UP) {
				stop = e.getEventTime();
			}
			if (stop - start > 1500) {
				AlertDialog alert = new AlertDialog.Builder(Maps2Activity.this)
						.create();
				alert.setTitle("Pick an Option");
				alert.setMessage("do it");
				alert.setButton("place a pin",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								OverlayItem overlayItem = new OverlayItem(
										touchpoint, "whats up", "second str");
								CustomPinpoint custom = new CustomPinpoint(d,
										Maps2Activity.this);
								custom.insertPinpoint(overlayItem);
								overlaylist.add(custom);
							}
						});
				alert.setButton2("get address",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								String display = "no address found";
								Geocoder geocoder = new Geocoder(
										getBaseContext(), Locale.getDefault());
								try {
									List<Address> address = geocoder.getFromLocation(
											touchpoint.getLatitudeE6() / 1E6,
											touchpoint.getLongitudeE6() / 1E6,
											1);
									if (address.size() > 0) {
										for (int i = 0; i < address.get(0)
												.getMaxAddressLineIndex(); i++) {
											display = "";
											display += address.get(0)
													.getAddressLine(i) + "\n";
										}
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
								Toast t = Toast.makeText(getBaseContext(),
										display, Toast.LENGTH_LONG);
								t.show();
							}
						});
				alert.setButton3("toggle view",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								if (map.isSatellite()) {
									map.setSatellite(false);
									map.setStreetView(true);
								} else {
									map.setSatellite(true);
									map.setStreetView(false);

								}

							}
						});
				alert.show();
				return true;
			}
			return false;
		}
	}

	@Override
	public void onLocationChanged(Location l) {
		lat = (int) (l.getLatitude()*1E6);
		lon = (int) (l.getLongitude()*1E6);
		GeoPoint ourLoc = new GeoPoint(lat, lon);
		OverlayItem overlayItem = new OverlayItem(ourLoc, "whats up",
				"second str");
		CustomPinpoint custom = new CustomPinpoint(d, Maps2Activity.this);
		custom.insertPinpoint(overlayItem);
		overlaylist.add(custom);
	}

	@Override
	public void onProviderDisabled(String arg0) {

	}

	@Override
	public void onProviderEnabled(String arg0) {

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

	}
}