package fr.iut.licence.projetandroid.presentation;

import java.util.ArrayList;
import java.util.List;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import fr.iut.licence.projetandroid.PlotArrayAdapter;
import fr.iut.licence.projetandroid.R;
import fr.iut.licence.projetandroid.entities.Constantes;
import fr.iut.licence.projetandroid.entities.Plot;

/**
 * The Class MainActivity.
 */
public class ListeParcelleActivity extends Activity
{
	/** The list plot. */
	private List<Plot>	listPlot;
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
		setContentView(R.layout.activity_listeparcelle);
		mContext = this;
		final ListView list = (ListView) findViewById(R.id.liste);
		// TODO get plots for DB.
		// ------------------------test------------------------
		listPlot = new ArrayList<Plot>();
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
		listPlot.add(plot);
		listPlot.add(plot1);
		// ------------------------test------------------------
		final PlotArrayAdapter arrayPlot = new PlotArrayAdapter(this, listPlot,
				Constantes.TYPE_SEMANCE);
		list.setAdapter(arrayPlot);
		list.setOnItemClickListener(new OnItemClickListener()
		{
			/* _________________________________________________________ */
			/**
			 * On item click.
			 * 
			 * @param arg0
			 *            the arg0
			 * @param view
			 *            the view
			 * @param position
			 *            the position
			 * @param id
			 *            the id
			 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
			 *      android.view.View, int, long)
			 */
			@Override
			public void onItemClick(final AdapterView<?> arg0, final View view,
					final int position, final long id)
			{
				final Bundle bundle = new Bundle();
				bundle.putSerializable("plot", listPlot.get(position));
				final Intent intent = new Intent(mContext, PlotActivty.class);
				intent.putExtra("bundle", bundle);
				startActivity(intent);
			}
		});
		final ActionBar ac = getActionBar();
		ac.show();
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
	/* _________________________________________________________ */
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
			case R.id.menu_ajoutParcelle:
				intent = new Intent(this, AjoutParcelleActivity.class);
				startActivity(intent);
				break;
			case R.id.menu_convertisseur:
				intent = new Intent(this, ConvertisseurActivity.class);
				startActivity(intent);
				break;
			case R.id.menu_commande:
				intent = new Intent(this, CommandeActivity.class);
				startActivity(intent);
				break;
			case R.id.menu_controle:
				intent = new Intent(this, ControleActivity.class);
				startActivity(intent);
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		return true;
	}
}
