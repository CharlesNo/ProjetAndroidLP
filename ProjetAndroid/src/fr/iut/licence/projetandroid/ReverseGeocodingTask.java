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
public class ReverseGeocodingTask extends AsyncTask<String, Void, Void>
{
	/** The addrs. */
	List<Address>						addrs	= null;
	/** The m context. */
	private final Context				mContext;
	/** The m listener. */
	private final TaskFinishedListener	mListener;
	/** The mlocation. */
	private final Location				mlocation;

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
	protected Void doInBackground(final String... arg0)
	{
		final Geocoder geo = new Geocoder(mContext, Locale.getDefault());
		final Location loc = mlocation;
		try
		{
			// geocoding
			if (arg0 != null)
			{
				if (arg0.length != 0)
				{
					// reverse Geocoding
					addrs = geo.getFromLocationName(arg0[0], 1);
				}
				else
				{
					addrs = geo.getFromLocation(loc.getAltitude(),
							loc.getLongitude(), 1);
				}
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/* _________________________________________________________ */
	/**
	 * On post execute.
	 * 
	 * @param result
	 *            the result
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(@SuppressWarnings("unused") final Void result)
	{
		// on trouve une adresse
		if ((addrs != null) && (addrs.size() > 0))
		{
			final Address address = addrs.get(0);
			mListener.onTaskCompleted(address);
		}
		// sinon
		else
		{
			mListener.onTaskCompleted(null);
		}
	}
}
