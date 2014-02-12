package fr.iut.licence.projetandroid.presentation;

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
import fr.iut.licence.projetandroid.persistence.DaoUtils;

/**
 * The Class MainActivity.
 */
public class ListeParcelleActivity extends Activity
{
	/** The m context. */
	private Context		mContext;
	private PlotArrayAdapter mArrayPlot;

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
		mArrayPlot = new PlotArrayAdapter(this,(List<Plot>) DaoUtils.getAllData(this, Plot.class),
				Constantes.TYPE_SEMANCE);
		list.setAdapter(mArrayPlot);
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
				bundle.putSerializable("plot", mArrayPlot.getListPlot().get(position));
				final Intent intent = new Intent(mContext, PlotActivty.class);
				intent.putExtra("bundle", bundle);
				startActivity(intent);
			}
		});
		final ActionBar ac = getActionBar();
		ac.show();
	}

	public void addPlot(Plot plot){
		mArrayPlot.addPlot(plot);
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
	
	@Override
	protected void onResume() {
		mArrayPlot.setListPlot((List<Plot>) DaoUtils.getAllData(this, Plot.class));
		super.onResume();
	}
}
