package fr.iut.licence.projetandroid.presentation;

import android.app.ActionBar;
import android.app.Activity;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;
import fr.iut.licence.projetandroid.R;
import fr.iut.licence.projetandroid.ReverseGeocodingTask;
import fr.iut.licence.projetandroid.TaskFinishedListener;
import fr.iut.licence.projetandroid.entities.Plot;
import fr.iut.licence.projetandroid.persistence.DaoUtils;

/**
 * The Class AjoutParcelleActivity.
 */
public class AjoutParcelleActivity extends Activity implements OnClickListener,
		LocationListener, TaskFinishedListener
{
	/** The m spinner. */
	private Spinner			mSpinner;
	/** The m spinner prec. */
	private Spinner			mSpinnerPrec;
	/** The m name. */
	private EditText		mName;
	/** The m surface. */
	private NumberPicker	mSurface;
	/** The m adresse. */
	private EditText		mAdresse;
	// GPS
	/** The m location manager. */
	private LocationManager	mLocationManager;
	/** The m location. */
	private Location		mLocation;

	/* _________________________________________________________ */
	/**
	 * On create.
	 * 
	 * @param savedInstanceState
	 *            the saved instance state
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		final ActionBar actionBar = getActionBar();
		actionBar.setTitle("Ajout Parcelle");
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_ajoutparcelle);
		mName = (EditText) findViewById(R.id.ed_nom);
		mSurface = (NumberPicker) findViewById(R.id.np_surface);
		mSpinner = (Spinner) findViewById(R.id.sp_culture);
		mSpinnerPrec = (Spinner) findViewById(R.id.sp_culturePrec);
		mAdresse = (EditText) findViewById(R.id.ed_adresse);
		final ArrayAdapter<CharSequence> adapter = ArrayAdapter
				.createFromResource(this, R.array.culture_array,
						android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinner.setAdapter(adapter);
		mSpinnerPrec.setAdapter(adapter);
		mSurface.setMaxValue(1000);
		mSurface.setMinValue(0);
		final Button b_ajouter = (Button) findViewById(R.id.b_ajouter);
		b_ajouter.setOnClickListener(this);
		final ImageButton b_map = (ImageButton) findViewById(R.id.ib_map);
		b_map.setOnClickListener(this);
		mLocation = new Location("prvider");
	}

	/* _________________________________________________________ */
	/**
	 * On click.
	 * 
	 * @param v
	 *            the v
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(final View v)
	{
		switch (v.getId())
		{
			case R.id.b_ajouter:
				if (mAdresse.getText().toString().equals(""))
				{
					Toast.makeText(this, "Adresse non renseignée",
							Toast.LENGTH_SHORT).show();
				}
				else
				{
					final ReverseGeocodingTask reverseGeocodingTask = new ReverseGeocodingTask(
							this, this, mLocation);
					reverseGeocodingTask.execute(mAdresse.getText().toString());
				}
				if ((mLocation.getLatitude() != 0)
						&& (mLocation.getLongitude() != 0))
				{
					savePlot();
				}
				break;
			case R.id.ib_map:
				mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
				if (mLocationManager
						.isProviderEnabled(LocationManager.GPS_PROVIDER))
				{
					mLocationManager.requestLocationUpdates(
							LocationManager.GPS_PROVIDER, 3000, 0, this);
					final ReverseGeocodingTask reverseGeocodingTask = new ReverseGeocodingTask(
							this, this, mLocation);
					reverseGeocodingTask.execute();
				}
				else
				{
					Toast.makeText(this, "GPS non disponible.",
							Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
		}
	}

	/**
	 * Sauvegarde une pacelle.
	 */
	private void savePlot()
	{
		final Plot plot = new Plot();
		plot.setName(mName.getText().toString());
		plot.setSurface(mSurface.getValue());
		plot.setGrowing(mSpinner.getSelectedItem().toString());
		plot.setLast_growing(mSpinnerPrec.getSelectedItem().toString());
		DaoUtils.storeSingleData(this, plot);
		Toast.makeText(this, "Parcelle ajoutée", Toast.LENGTH_SHORT).show();
	}

	/* _________________________________________________________ */
	/**
	 * On pause.
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause()
	{
		super.onPause();
		mLocationManager.removeUpdates(this);
	}

	/* _________________________________________________________ */
	/**
	 * On location changed.
	 * 
	 * @param location
	 *            the location
	 * @see android.location.LocationListener#onLocationChanged(android.location.Location)
	 */
	@Override
	public void onLocationChanged(final Location location)
	{
		if (location != null)
		{
			mLocation.setLatitude(location.getLatitude());
			mLocation.setLongitude(location.getLongitude());
		}
		else
		{
			Toast.makeText(this, "Location non définie.", Toast.LENGTH_SHORT)
					.show();
		}
	}

	/* _________________________________________________________ */
	/**
	 * On provider disabled.
	 * 
	 * @param arg0
	 *            the arg0
	 * @see android.location.LocationListener#onProviderDisabled(java.lang.String)
	 */
	@Override
	public void onProviderDisabled(final String arg0)
	{
	}

	/* _________________________________________________________ */
	/**
	 * On provider enabled.
	 * 
	 * @param arg0
	 *            the arg0
	 * @see android.location.LocationListener#onProviderEnabled(java.lang.String)
	 */
	@Override
	public void onProviderEnabled(final String arg0)
	{
	}

	/* _________________________________________________________ */
	/**
	 * On status changed.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 * @param arg2
	 *            the arg2
	 * @see android.location.LocationListener#onStatusChanged(java.lang.String,
	 *      int, android.os.Bundle)
	 */
	@Override
	public void onStatusChanged(final String arg0, final int arg1,
			final Bundle arg2)
	{
	}

	/* _________________________________________________________ */
	/**
	 * On task completed.
	 * 
	 * @param adresse
	 *            the adresse
	 * @see fr.iut.licence.projetandroid.TaskFinishedListener#onTaskCompleted(android.location.Address)
	 */
	@Override
	public void onTaskCompleted(final Address adresse)
	{
		if (adresse != null)
		{
			mAdresse.setText(String.format("%s, %s, %s", adresse
					.getMaxAddressLineIndex() > 0 ? adresse.getAddressLine(0)
					: " ", adresse.getLocality(), adresse.getCountryName()));
			mLocation.setLatitude(adresse.getLatitude());
			mLocation.setLongitude(adresse.getLongitude());
		}
		else
		{
			Looper.prepare();
			Toast.makeText(this, "Echec lors du chargement de l'adresse",
					Toast.LENGTH_SHORT).show();
		}
	}
}
