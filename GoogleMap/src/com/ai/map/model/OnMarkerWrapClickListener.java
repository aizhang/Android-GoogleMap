package com.ai.map.model;

import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;

public interface OnMarkerWrapClickListener extends OnMarkerClickListener {
    public void onMarkerWrapClick(MarkerWrap mw);
}
