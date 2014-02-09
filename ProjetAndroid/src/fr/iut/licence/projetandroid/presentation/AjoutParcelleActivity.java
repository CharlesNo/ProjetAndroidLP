package fr.iut.licence.projetandroid.presentation;

import fr.iut.licence.projetandroid.R;
import fr.iut.licence.projetandroid.ReverseGeocodingTask;
import fr.iut.licence.projetandroid.TaskFinishedListener;
import fr.iut.licence.projetandroid.entities.Plot;
import fr.iut.licence.projetandroid.persistence.DaoUtils;
import android.app.ActionBar;
import android.app.Activity;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class AjoutParcelleActivity extends Activity implements OnClickListener,LocationListener, TaskFinishedListener{

	private Spinner mSpinner;
	private Spinner mSpinnerPrec;
	private EditText  mName;
	private NumberPicker mSurface;
	private EditText mAdresse;
	
	//GPS
	private LocationManager mLocationManager;
	private Location mLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Ajout Parcelle");
		actionBar.setDisplayHomeAsUpEnabled(true);		
		setContentView(R.layout.activity_ajoutparcelle);

		mName = (EditText) findViewById(R.id.ed_nom);
		mSurface = (NumberPicker) findViewById(R.id.np_surface);
		mSpinner = (Spinner) findViewById(R.id.sp_culture);
		mSpinnerPrec = (Spinner) findViewById(R.id.sp_culturePrec);
		mAdresse = (EditText) findViewById(R.id.ed_adresse);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.culture_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSpinner.setAdapter(adapter);
		mSpinnerPrec.setAdapter(adapter);
		mSurface.setMaxValue(1000);
		mSurface.setMinValue(0);

		Button b_ajouter = (Button) findViewById(R.id.b_ajouter);
		b_ajouter.setOnClickListener(this);
		
		Button b_map = (Button) findViewById(R.id.ib_map);
		b_map.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.b_ajouter :
			if(mAdresse.getText().toString().equals(""))
				Toast.makeText(this, "Adresse non renseignée", Toast.LENGTH_SHORT).show();
			else {
				ReverseGeocodingTask reverseGeocodingTask = new ReverseGeocodingTask(this, this, mLocation);
				reverseGeocodingTask.execute(mAdresse.getText().toString());
			}
			if(mLocation.getLatitude() != 0 && mLocation.getLongitude() !=0)
				savePlot();				
			break;
		case R.id.ib_map :
			mLocationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
			if( mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
				mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, this);
			}
			mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0, this);
			
			ReverseGeocodingTask reverseGeocodingTask = new ReverseGeocodingTask(this, this, mLocation);
			reverseGeocodingTask.execute();
		}
	}

	/**
	 * Sauvegarde une pacelle 
	 */
	private void savePlot() {
		Plot plot = new Plot();
		plot.setName(mName.getText().toString());
		plot.setSurface(mSurface.getValue());
		plot.setGrowing(mSpinner.getSelectedItem().toString());
		plot.setLast_growing(mSpinnerPrec.getSelectedItem().toString());
		DaoUtils.storeSingleData(this,plot);
		Toast.makeText(this, "Parcelle ajoutée", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mLocationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		mLocation.setLatitude(location.getLatitude());
		mLocation.setLongitude(location.getLongitude());
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

	@Override
	public void onTaskCompleted(Address adresse) {
		if(adresse != null ){
			mAdresse.setText( String.format("%s, %s, %s", 
					adresse.getMaxAddressLineIndex() >0 ? adresse.getAddressLine(0): " ",
					adresse.getLocality(),
					adresse.getCountryName()));
			mLocation.setLatitude(adresse.getLatitude());
			mLocation.setLongitude(adresse.getLongitude());
		}
		else
			Toast.makeText(this, "Echec lors du chargement de l'adresse", Toast.LENGTH_SHORT).show();
	}
}
