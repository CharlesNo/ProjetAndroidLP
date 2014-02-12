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

/**
 * The Class CommandeActivity.
 */
public class CommandeActivity extends Activity implements OnClickListener
{
	/** The m activity. */
	private Activity	mActivity;
	/** The m but. */
	private Button		mBut;
	/** The m but parcelles. */
	private ImageButton	mButParcelles;
	/** The m condi. */
	private EditText	mCondi;
	/** The m context. */
	private Context		mContext;
	/** The m densitee semi. */
	private String		mDensiteeSemi;
	/** The m pd sem nec. */
	private EditText	mPdSemNec;
	/** The m resultat. */
	private TextView	mResultat;
	/** The m surface. */
	private EditText	mSurface;

	/**
	 * Inits the components.
	 */
	private void initComponents()
	{
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
				long i = 0;
				long j = 0;
				long x = 0;
				int result = 0;
				try
				{
					if (!mPdSemNec.getText().toString().equals(""))
					{
						i = Integer.valueOf(mPdSemNec.getText().toString());
						++result;
					}
					if (!mSurface.getText().toString().equals(""))
					{
						j = Integer.valueOf(mSurface.getText().toString());
						++result;
					}
					if (!mCondi.getText().toString().equals(""))
					{
						x = Integer.valueOf(mCondi.getText().toString());
						++result;
					}
					if (x == 0)
					{
						throw new NumberFormatException();
					}
					if (result == 3)
					{
						mBut.setVisibility(View.VISIBLE);
					}
					else
					{
						mBut.setVisibility(View.GONE);
					}
				}
				catch (final NumberFormatException ex)
				{
					i = 0;
					j = 0;
					x = 1;
					Toast.makeText(mContext, "Donnée incalculable",
							Toast.LENGTH_SHORT).show();
				}
				mResultat.setText(String.valueOf((i * j) / x));
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
		mPdSemNec.addTextChangedListener(tw);
		mSurface.addTextChangedListener(tw);
		mCondi.addTextChangedListener(tw);
	}

	/**
	 * Load extra.
	 */
	private void loadExtra()
	{
		final Intent intent = getIntent();
		String result = intent.getStringExtra("pdtSemence");
		if (result != null)
		{
			mPdSemNec.setText(result);
		}
		result = intent.getStringExtra("densiteSemi");
		if (result != null)
		{
			mDensiteeSemi = result;
		}
	}

	/* _________________________________________________________ */
	/**
	 * On click.
	 * 
	 * @param arg0
	 *            the arg0
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(final View arg0)
	{
		switch (arg0.getId())
		{
			case R.id.b_commande_ajouter:
				final Intent intent = new Intent(this, ControleActivity.class);
				intent.putExtra("densiteSemi", mDensiteeSemi);
				startActivity(intent);
				break;
			case R.id.b_commande_infoParcelles:
				final List<Plot> plots = new ArrayList<Plot>();
				final Plot plot = new Plot();
				plot.setGrowing("blé");
				plot.setId(0L);
				plot.setLast_growing("maïs");
				plot.setName("ici");
				plot.setSurface(150);
				final Plot plot1 = new Plot();
				plot1.setGrowing("blé");
				plot1.setId(0L);
				plot1.setLast_growing("maïs");
				plot1.setName("ici");
				plot1.setSurface(150);
				plots.add(plot);
				plots.add(plot1);
				final PlotArrayAdapter plotArray = new PlotArrayAdapter(
						mContext, plots, Constantes.TYPE_SURFACE);
				final AlertDialog.Builder builder = new AlertDialog.Builder(
						mActivity);
				builder.setTitle(R.string.parcelles);
				builder.setAdapter(plotArray,
						new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(final DialogInterface dialog,
									final int which)
							{
								mSurface.setText(String.valueOf(plots
										.get(which).getSurface()));
								dialog.cancel();
							}
						});
				final AlertDialog alert = builder.create();
				alert.show();
				break;
			default:
				break;
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
		setContentView(R.layout.activity_commande);
		initComponents();
		loadExtra();
		final ActionBar actionBar = getActionBar();
		actionBar.setTitle("Commande");
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
}
