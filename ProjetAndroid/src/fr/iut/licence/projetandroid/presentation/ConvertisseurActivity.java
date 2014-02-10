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

/**
 * The Class ConvertisseurActivity.
 */
public class ConvertisseurActivity extends Activity implements OnClickListener
{
	/** The m densité. */
	private EditText	mDensité;
	/** The m pgm. */
	private EditText	mPGM;
	/** The m result. */
	private TextView	mResult;
	/** The m button commande. */
	private Button		mButtonCommande;
	/** The m context. */
	private Context		mContext;

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
		actionBar.setTitle("Convertisseur");
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_convertisseur);
		mContext = this;
		mDensité = (EditText) findViewById(R.id.ed_densiteSemi);
		mPGM = (EditText) findViewById(R.id.ed_PGM);
		mResult = (TextView) findViewById(R.id.tv_resultPoidSemence);
		mButtonCommande = (Button) findViewById(R.id.b_convertisseur_commande);
		mButtonCommande.setOnClickListener(this);
		final TextWatcher tw = new TextWatcher()
		{
			/* _________________________________________________________ */
			/**
			 * Before text changed.
			 * 
			 * @param arg0
			 *            the arg0
			 * @param arg1
			 *            the arg1
			 * @param arg2
			 *            the arg2
			 * @param arg3
			 *            the arg3
			 * @see android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence,
			 *      int, int, int)
			 */
			@Override
			public void beforeTextChanged(final CharSequence arg0,
					final int arg1, final int arg2, final int arg3)
			{
			}

			/* _________________________________________________________ */
			/**
			 * On text changed.
			 * 
			 * @param arg0
			 *            the arg0
			 * @param arg1
			 *            the arg1
			 * @param arg2
			 *            the arg2
			 * @param arg3
			 *            the arg3
			 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence,
			 *      int, int, int)
			 */
			@Override
			public void onTextChanged(final CharSequence arg0, final int arg1,
					final int arg2, final int arg3)
			{
			}

			/* _________________________________________________________ */
			/**
			 * After text changed.
			 * 
			 * @param s
			 *            the s
			 * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
			 */
			@Override
			public void afterTextChanged(final Editable s)
			{
				long i = 0;
				long j = 0;
				int result = 0;
				try
				{
					if (!mDensité.getText().toString().equals(""))
					{
						i = Integer.valueOf(mDensité.getText().toString());
						++result;
					}
					if (!mPGM.getText().toString().equals(""))
					{
						j = Integer.valueOf(mPGM.getText().toString());
						++result;
					}
				}
				catch (final NumberFormatException ex)
				{
					i = 0;
					j = 0;
					mButtonCommande.setVisibility(View.GONE);
					Toast.makeText(mContext, "donne incalculable",
							Toast.LENGTH_SHORT).show();
				}
				if (result == 2)
				{
					mButtonCommande.setVisibility(View.VISIBLE);
				}
				else
				{
					mButtonCommande.setVisibility(View.GONE);
				}
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
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	/* _________________________________________________________ */
	/* _________________________________________________________ */
	/**
	 * On options item selected.
	 * 
	 * @param item
	 *            the item
	 * @return true, if successful
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(final MenuItem item)
	{
		Intent intent;
		switch (item.getItemId())
		{
			case android.R.id.home:
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
				return super.onOptionsItemSelected(item);
		}
		return true;
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
		if (v.getId() == R.id.b_convertisseur_commande)
		{
			final Intent intent = new Intent(this, CommandeActivity.class);
			intent.putExtra("pdtSemence", mResult.getText().toString());
			intent.putExtra("densiteSemi", mDensité.getText().toString());
			startActivity(intent);
		}
	}
}
