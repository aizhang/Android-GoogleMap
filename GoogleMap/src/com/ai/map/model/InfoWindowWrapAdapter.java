package com.ai.map.model;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

public interface InfoWindowWrapAdapter extends InfoWindowAdapter {
	public MarkerWrap getMarkerWrap(Marker marker);

}
