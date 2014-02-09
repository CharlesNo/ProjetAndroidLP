package fr.iut.licence.projetandroid;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;

public class ReverseGeocodingTask extends AsyncTask<String, String, String> {
	private Context mContext;
	private Location mlocation;
	private TaskFinishedListener mListener;

	
	public ReverseGeocodingTask(Context context, TaskFinishedListener listener, Location location) {
		super();
		mContext = context;
		mListener = listener;
		mlocation = location;
	}

	@Override
	protected String doInBackground(String... arg0) {
		Geocoder  geo = new Geocoder(mContext, Locale.getDefault());
		Location loc = mlocation;
		List<Address> addrs = null;
		try {
			// geocoding
			if(arg0[0] == null)
				addrs = geo.getFromLocationName(arg0[0],1);
			//reverse Geocoding
			else
				addrs = geo.getFromLocation(loc.getAltitude(), loc.getLongitude(), 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(addrs != null && addrs.size()>0){
			Address address = addrs.get(0);
			mListener.onTaskCompleted(address);
		}
		else
			mListener.onTaskCompleted(null);
		return "";
	}
}
