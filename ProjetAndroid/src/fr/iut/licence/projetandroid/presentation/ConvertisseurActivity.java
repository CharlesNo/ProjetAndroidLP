package fr.iut.licence.projetandroid.presentation;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.iut.licence.projetandroid.R;

public class ConvertisseurActivity extends Activity implements OnClickListener{

	private EditText mDensité ;
	private EditText mPGM;
	private TextView mResult;
	private Button mButtonCommande;
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Convertisseur");
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_convertisseur);

		mContext = this;
		mDensité = (EditText) findViewById(R.id.ed_densiteSemi);
		mPGM = (EditText) findViewById(R.id.ed_PGM);
		mResult = (TextView) findViewById(R.id.tv_resultPoidSemence);
		
		mButtonCommande = (Button) findViewById(R.id.b_convertisseur_commande);
		mButtonCommande.setOnClickListener(this);
		
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
				int result =0;
				try{
				if(!mDensité.getText().toString().equals(""))
					{i = Integer.valueOf(mDensité.getText().toString()); ++result;}
				if(!mPGM.getText().toString().equals(""))
					{j = Integer.valueOf(mPGM.getText().toString()); ++result;}
				}
				catch(NumberFormatException ex){
					i=0;
					j=0;
					mButtonCommande.setVisibility(View.GONE);
					Toast.makeText(mContext, "donne incalculable", Toast.LENGTH_SHORT).show();
				}
				if(result == 2)
					mButtonCommande.setVisibility(View.VISIBLE);
				else 
					mButtonCommande.setVisibility(View.GONE);
				mResult.setText(String.valueOf(i * j));
			}
	    };
		mDensité.addTextChangedListener(tw); 
		mPGM.addTextChangedListener(tw);
	}
	
	/* _________________________________________________________ */
	/**
	 * On create options menu.
	 * 
	 * @param menu
	 *            the menu
	 * @return true, if successful
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */

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
			break;
		case R.id.menu_commande:
			intent = new Intent(this, CommandeActivity.class);
			startActivity(intent);
			finish();
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

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.b_convertisseur_commande){
			Intent intent = new Intent(this,CommandeActivity.class);
			intent.putExtra("pdtSemence",mResult.getText().toString());
			intent.putExtra("densiteSemi",mDensité.getText().toString());
			startActivity(intent);
		}
	}
}
