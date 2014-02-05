package fr.iut.licence.projetandroid.presentation;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import fr.iut.licence.projetandroid.PlotArrayAdapter;
import fr.iut.licence.projetandroid.R;
import fr.iut.licence.projetandroid.entities.Plot;

/**
 * The Class MainActivity.
 */

public class ListeParcelleActivity extends ListActivity implements
		OnItemClickListener
{
	/** The list plot. */
	private List<Plot>	listPlot;

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
		
		//TODO get plots for DB.
		//------------------------test------------------------
		List<Plot> listPlot = new ArrayList<Plot>();
		Plot plot = new Plot();
		plot.setGrowing("blé");
		plot.setId("0");
		plot.setLast_growing("maïs");
		plot.setName("ici");
		plot.setSurface(150);
		
		listPlot.add(plot);
		
		PlotArrayAdapter arrayPlot = new PlotArrayAdapter(this, listPlot);
		setListAdapter(arrayPlot);
		
		//------------------------test------------------------

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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	/* _________________________________________________________ */
	/**
	 * On item click.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param v
	 *            the v
	 * @param position
	 *            the position
	 * @param id
	 *            the id
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	@Override
	public void onItemClick(final AdapterView<?> arg0, final View v,
			final int position, final long id)
	{
		final Bundle bundle = new Bundle();
		bundle.putSerializable("plot", listPlot.get(position));
		final Intent intent = new Intent(this, PlotActivty.class);
		intent.putExtra("bundle", bundle);
	}
}
