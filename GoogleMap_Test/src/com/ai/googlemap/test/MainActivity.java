package com.ai.googlemap.test;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.ai.map.Map;
import com.ai.map.MapFragment;
import com.ai.map.model.MarkerOptionsWrap;
import com.ai.map.model.MarkerWrap;
import com.ai.map.model.OnInfoWindowWrapClickListener;
import com.ai.map.model.OnMarkerWrapClickListener;
import com.ai.map.model.OnMarkerWrapDragListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends FragmentActivity {
	LatLng[] latLngs = { new LatLng(-34.397, 150.644), new LatLng(-20.397, 130.644), new LatLng(-30.4, 130) };

	Map map;
	List<MarkerWrap> markers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		markers = new ArrayList<MarkerWrap>();
		MapFragment mf = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.chanyouji_map);
		map = mf.getMap();

		map.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public void onMapClick(LatLng arg0) {
				Toast.makeText(getApplicationContext(), "Map Clicked: "+ arg0.toString()	,Toast.LENGTH_SHORT).show();
			}
		});

		map.setOnMarkerClickListener(new OnMarkerWrapClickListener() {

			@Override
			public boolean onMarkerClick(Marker arg0) {
				Toast.makeText(getApplicationContext(), arg0.getPosition().toString(), Toast.LENGTH_SHORT).show();
				return false;
			}

			@Override
			public void onMarkerWrapClick(MarkerWrap mw) {
				map.showInfoWindow(mw);
				Toast.makeText(getApplicationContext(), mw.getPosition().toString(), Toast.LENGTH_SHORT).show();
			}
		});

		map.setOnMarkerDragListener(new OnMarkerWrapDragListener() {

			@Override
			public void onMarkerDragStart(Marker arg0) {
				System.out.println(arg0.getPosition());
			}

			@Override
			public void onMarkerDragEnd(Marker arg0) {
				System.out.println(arg0.getPosition());
			}

			@Override
			public void onMarkerDrag(Marker arg0) {
				System.out.println(arg0.getPosition());
			}

			@Override
			public void onMarkerWrapDragStart(MarkerWrap mw) {
				System.out.println(mw.getPosition());
			}

			@Override
			public void onMarkerWrapDragEnd(MarkerWrap mw) {
				System.out.println(mw.getPosition());
			}

			@Override
			public void onMarkerWrapDrag(MarkerWrap mw) {
				System.out.println(mw.getPosition());
			}
		});

		
		map.setOnInfoWindowClickListener(new OnInfoWindowWrapClickListener() {
			
			@Override
			public void onInfoWindowClick(Marker arg0) {
				Toast.makeText(getApplicationContext(), "onInfoWindowClicked: "+arg0.getPosition().toString(), Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onWebInfoWindowClicked(MarkerWrap mw) {
				Toast.makeText(getApplicationContext(), "onWebInfoWindowClicked: "+mw.getPosition().toString(), Toast.LENGTH_SHORT).show();
			}
		});
		findViewById(R.id.test).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (markers.size() == 0) {
					PolylineOptions options = new PolylineOptions();
					options.color(0xfff1685e);
					options.width(TypedValue.applyDimension(map.isMapNative() ? TypedValue.COMPLEX_UNIT_DIP : TypedValue.COMPLEX_UNIT_PX, 2, getResources()
							.getDisplayMetrics()));
					options.visible(true);
					Builder bounds = LatLngBounds.builder();
					for (int i = 0; i < latLngs.length; i++) {
						LatLng latLng = latLngs[i];
						options.add(latLng);

						MarkerOptionsWrap mow = new MarkerOptionsWrap();
						MarkerOptions markerOptions = new MarkerOptions();
						markerOptions.position(latLng);
						markerOptions.title("Marker " + i);
						mow.setOptions(markerOptions);
						
						if (map.isMapNative()) {
							try {
								markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.custompoi_pingreen));
							} catch (Exception e) {
							}
						} else {
							mow.setWebIconPath("custompoi_pingreen_web.png");
						}
						
						MarkerWrap mw = map.addMarker(mow);
						mw.setTag("Marker " + i);
						markers.add(mw);
						bounds.include(latLng);
					}

					map.addPolyline(options);
					map.moveCameraPosition(bounds.build(), 8);
					
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
