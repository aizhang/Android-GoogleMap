package com.ai.map.model;

public interface OnInfoWindowWrapClickListener extends
		com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener {

	public void onWebInfoWindowClicked(MarkerWrap mw);
}
