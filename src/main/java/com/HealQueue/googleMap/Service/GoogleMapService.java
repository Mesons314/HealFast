package com.HealQueue.googleMap.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleMapService {

    private final GeoApiContext geoApiContext;

    public GoogleMapService(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }

    public GeocodingResult[] geocodeAddress(String address) throws IOException, InterruptedException, ApiException {
        return GeocodingApi.geocode(geoApiContext,address).await();
    }
}
