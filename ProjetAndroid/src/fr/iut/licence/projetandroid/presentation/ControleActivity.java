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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.iut.licence.projetandroid.R;

/**
 * The Class ControleActivity.
 */
public class ControleActivity extends Activity
{
	/** The m context. */
	private Context		mContext;
	/** The m densité semi. */
	private EditText	mDensitéSemi;
	/** The m ecartement. */
	private EditText	mEcartement;
	/** The m nb grains. */
	private TextView	mNbGrains;
	/** The m nb rangs. */
	private TextView	mNbRangs;

	/**
	 * Inits the components.
	 */
	private void initComponents()
	{
		mContext = this;
		mDensitéSemi = (EditText) findViewById(R.id.ed_controle_densite);
		mEcartement = (EditText) findViewById(R.id.ed_controle_ecartement);
		mNbGrains = (TextView) findViewById(R.id.tv_controle_nbGrains);
		mNbRangs = (TextView) findViewById(R.id.tv_controle_nbRangs);
		final TextWatcher tw = new TextWatcher()
		{
			/* _________________________________________________________ */
			/**
			 * After text changed.
			 * 
			 * @param s
			 *            the s
			 * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
			 */
			@Override
			public void afterTextChanged(
					@SuppressWarnings("unused") final Editable s)
			{
				long densité = 0;
				long ecartement = 0;
				try
				{
					if (!mDensitéSemi.getText().toString().equals(""))
					{
						densité = Integer.valueOf(mDensitéSemi.getText()
								.toString());
					}
					if (!mEcartement.getText().toString().equals(""))
					{
						ecartement = Integer.valueOf(mEcartement.getText()
								.toString());
					}
					if (ecartement == 0)
					{
						throw new NumberFormatException();
					}
				}
				catch (final NumberFormatException ex)
				{
					densité = 0;
					ecartement = 1;
					Toast.makeText(mContext, "donne incalculable",
							Toast.LENGTH_SHORT).show();
				}
				mNbRangs.setText(String.valueOf(densité / ecartement));
				final int result = Integer.valueOf(mNbRangs.getText()
						.toString());
				if (result != 0)
				{
					mNbGrains.setText(String.valueOf(densité / result));
				}
				else
				{
					Toast.makeText(mContext,
							"Nombre de grains/m2 incalculable",
							Toast.LENGTH_SHORT).show();
					mNbGrains.setText("0");
				}
			}

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
			@SuppressWarnings("unused")
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
			@SuppressWarnings("unused")
			@Override
			public void onTextChanged(final CharSequence arg0, final int arg1,
					final int arg2, final int arg3)
			{
			}
		};
		mDensitéSemi.addTextChangedListener(tw);
		mEcartement.addTextChangedListener(tw);
	}

	/**
	 * Load extra.
	 */
	private void loadExtra()
	{
		final Intent intent = getIntent();
		final String result = intent.getStringExtra("densiteSemi");
		if (result != null)
		{
			mDensitéSemi.setText(result);
		}
	}

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
		setContentView(R.layout.activity_controle);
		initComponents();
		loadExtra();
		final ActionBar actionBar = getActionBar();
		actionBar.setTitle("Controle");
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	/* _________________________________________________________ */
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
				return super.onOptionsItemSelected(item);
		}
		return true;
	}
}
