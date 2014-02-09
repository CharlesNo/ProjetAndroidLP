package fr.iut.licence.projetandroid.presentation;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import fr.iut.licence.projetandroid.PlotArrayAdapter;
import fr.iut.licence.projetandroid.R;
import fr.iut.licence.projetandroid.entities.Constantes;
import fr.iut.licence.projetandroid.entities.Plot;

public class CommandeActivity extends Activity implements OnClickListener {

	private  EditText mPdSemNec;
	private  EditText mSurface;
	private  EditText mCondi;
	private  TextView mResultat;
	private  Context mContext;
	private Activity mActivity;
	private String mDensiteeSemi;
	private Button mBut;
	private ImageButton mButParcelles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commande);
		initComponents();
		loadExtra();
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Commande");
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	private void loadExtra() {
		Intent intent = getIntent();
		String result = intent.getStringExtra("pdtSemence");
		if (result != null){
			mPdSemNec.setText(result);
		}
		result =  intent.getStringExtra("densiteSemi");	
		if (result != null){
			mDensiteeSemi = result;
		}
	}

	private void initComponents() {
		mContext = this;
		mActivity = this;
		mPdSemNec = (EditText) findViewById(R.id.ed_commande_pdSemNec);
		mSurface = (EditText) findViewById(R.id.ed_commande_surface);
		mCondi = (EditText) findViewById(R.id.ed_commande_conditionnement);
		mResultat = (TextView) findViewById(R.id.tv_commande_nbACommander);

		mBut = (Button) findViewById(R.id.b_commande_ajouter);
		mBut.setOnClickListener(this);

		mButParcelles = (ImageButton) findViewById(R.id.b_commande_infoParcelles);
		mButParcelles.setOnClickListener(this);

		TextWatcher tw = new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {				
			}

			@Override
			public void afterTextChanged(Editable s) {
				long i = 0;
				long j =0;
				long x = 0;
				int result = 0;
				try{
					if(!mPdSemNec.getText().toString().equals(""))
					{i = Integer.valueOf(mPdSemNec.getText().toString()); ++result;}
					if(!mSurface.getText().toString().equals(""))
					{j = Integer.valueOf(mSurface.getText().toString());++result;}
					if(!mCondi.getText().toString().equals(""))
					{x = Integer.valueOf(mCondi.getText().toString());++result;}
					if(x == 0)
						throw new NumberFormatException();
					if(result == 3)
						mBut.setVisibility(View.VISIBLE);
					else
						mBut.setVisibility(View.GONE);
				}
				catch(NumberFormatException ex){
					i=0;
					j=0;
					x=1;
					Toast.makeText(mContext, "Donnée incalculable", Toast.LENGTH_SHORT).show();
				}
				mResultat.setText(String.valueOf(i * j/x));
			}
		};
		mPdSemNec.addTextChangedListener(tw);
		mSurface.addTextChangedListener(tw);
		mCondi.addTextChangedListener(tw);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.b_commande_ajouter:  
			Intent intent = new Intent(this,ControleActivity.class);
			intent.putExtra("densiteSemi",mDensiteeSemi);
			startActivity(intent);
			break;
		case R.id.b_commande_infoParcelles:

			final List<Plot> plots = new ArrayList<Plot>();
			Plot plot = new Plot();
			plot.setGrowing("blé");
			plot.setId("0");
			plot.setLast_growing("maïs");
			plot.setName("ici");
			plot.setSurface(150);

			Plot plot1 = new Plot();
			plot1.setGrowing("blé");
			plot1.setId("0");
			plot1.setLast_growing("maïs");
			plot1.setName("ici");
			plot1.setSurface(150);

			plots.add(plot);
			plots.add(plot1);			
			PlotArrayAdapter plotArray = new PlotArrayAdapter(mContext,plots,Constantes.TYPE_SURFACE);
			AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
			builder.setTitle(R.string.parcelles);
			builder.setAdapter(plotArray, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {			
					mSurface.setText(String.valueOf(plots.get(which).getSurface()));
					dialog.cancel();
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
			break;
		default:
			break;
		}
	}
	
	/* _________________________________________________________ */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	
	/* _________________________________________________________ */
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case android.R.id.home :
			finish();
			break;
		case R.id.menu_ajoutParcelle:
			intent = new Intent(this, AjoutParcelleActivity.class);
			startActivity(intent);
			break;
		case R.id.menu_convertisseur:
			intent = new Intent(this, ConvertisseurActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.menu_commande:
			break;
		case R.id.menu_controle:
			intent = new Intent(this, ControleActivity.class);
			startActivity(intent);
			finish();
			break;
		default:
			return super .onOptionsItemSelected(item);
		}
		return  true;
	}
}
