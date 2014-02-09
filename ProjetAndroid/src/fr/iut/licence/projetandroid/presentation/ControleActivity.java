package fr.iut.licence.projetandroid.presentation;

import fr.iut.licence.projetandroid.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ControleActivity extends Activity{
	
	private EditText mDensitéSemi;
	private EditText mEcartement;
	private TextView mNbRangs;
	private TextView mNbGrains;
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_controle);
		initComponents();
		loadExtra();
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Controle");
		actionBar.setDisplayHomeAsUpEnabled(true);

	}
	private void loadExtra() {
		Intent intent = getIntent();
		String result = intent.getStringExtra("densiteSemi");
		if(result != null)
			mDensitéSemi.setText(result);
	}
	private void initComponents() {
		mContext = this;
		
		mDensitéSemi = (EditText) findViewById(R.id.ed_controle_densite);
		mEcartement= (EditText) findViewById(R.id.ed_controle_ecartement);
		mNbGrains = (TextView) findViewById(R.id.tv_controle_nbGrains);
		mNbRangs = (TextView) findViewById(R.id.tv_controle_nbRangs);
		
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
				long densité = 0;
				long ecartement =0;
				try{
				if(!mDensitéSemi.getText().toString().equals(""))
					densité = Integer.valueOf(mDensitéSemi.getText().toString());
				if(!mEcartement.getText().toString().equals(""))
					ecartement = Integer.valueOf(mEcartement.getText().toString());
				if( ecartement == 0)
					throw new NumberFormatException();
				}
				catch(NumberFormatException ex){
					densité=0;
					ecartement=1;
					Toast.makeText(mContext, "donne incalculable", Toast.LENGTH_SHORT).show();
				}
				mNbRangs.setText(String.valueOf(densité / ecartement));
				int result = Integer.valueOf(mNbRangs.getText().toString());
				if(result != 0)
					mNbGrains.setText(String.valueOf(densité /result));
				else
				{
					Toast.makeText(mContext, "Nombre de grains/m2 incalculable", Toast.LENGTH_SHORT).show();
					mNbGrains.setText("0");
				}
			}
	    };	
	    mDensitéSemi.addTextChangedListener(tw);
	    mEcartement.addTextChangedListener(tw);
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
			intent = new Intent(this, CommandeActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.menu_controle:
			break;
		default:
			return super .onOptionsItemSelected(item);
		}
		return  true;
	}

}
