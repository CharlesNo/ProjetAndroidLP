package fr.iut.licence.projetandroid;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;

/**
 * The Class ReverseGeocodingTask.
 */
public class ReverseGeocodingTask extends AsyncTask<String, String, String>
{
	/** The m context. */
	private final Context				mContext;
	/** The mlocation. */
	private final Location				mlocation;
	/** The m listener. */
	private final TaskFinishedListener	mListener;

	/**
	 * Instantiates a new reverse geocoding task.
	 * 
	 * @param context
	 *            the context
	 * @param listener
	 *            the listener
	 * @param location
	 *            the location
	 */
	public ReverseGeocodingTask(final Context context,
			final TaskFinishedListener listener, final Location location)
	{
		super();
		mContext = context;
		mListener = listener;
		mlocation = location;
	}

	/* _________________________________________________________ */
	/**
	 * Do in background.
	 * 
	 * @param arg0
	 *            the arg0
	 * @return the string
	 * @see android.os.AsyncTask#doInBackground
	 */
	@Override
	protected String doInBackground(final String... arg0)
	{
		final Geocoder geo = new Geocoder(mContext, Locale.getDefault());
		final Location loc = mlocation;
		List<Address> addrs = null;
		try
		{
			// geocoding
			if (arg0 != null)
			{
				if (arg0.length != 0)
				{
					addrs = geo.getFromLocationName(arg0[0], 1);
					// reverse Geocoding
				}
			}
			else
			{
				addrs = geo.getFromLocation(loc.getAltitude(),
						loc.getLongitude(), 1);
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		if ((addrs != null) && (addrs.size() > 0))
		{
			final Address address = addrs.get(0);
			mListener.onTaskCompleted(address);
		}
		else
		{
			mListener.onTaskCompleted(null);
		}
		return "";
	}
}
