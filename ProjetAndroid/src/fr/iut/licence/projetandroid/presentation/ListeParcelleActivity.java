package fr.iut.licence.projetandroid.presentation;

import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
<<<<<<< HEAD
=======
import fr.iut.licence.projetandroid.PlotActivty;
import fr.iut.licence.projetandroid.R;
import fr.iut.licence.projetandroid.entities.Plot;
>>>>>>> 808c85046d9deedf62f6e4c882d1857b5cb8d488

/**
 * The Class MainActivity.
 */
<<<<<<< HEAD
public class ListeParcelleActivity extends ListActivity implements OnItemClickListener
{
	private List<Plot> listPlot;
=======
public class ListeParcelleActivity extends ListActivity implements
		OnItemClickListener
{
	/** The list plot. */
	private List<Plot>	listPlot;

>>>>>>> 808c85046d9deedf62f6e4c882d1857b5cb8d488
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
<<<<<<< HEAD
		
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

=======
		// TODO get plots for DB.
		// setListAdapter(new PlotArrayAdapter()));
>>>>>>> 808c85046d9deedf62f6e4c882d1857b5cb8d488
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

<<<<<<< HEAD
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		Bundle bundle = new Bundle();
		bundle.putSerializable("plot",listPlot.get(position));
		Intent intent = new Intent (this, PlotActivty.class);
		intent.putExtra("bundle",bundle);
	}
	
	
	
=======
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
>>>>>>> 808c85046d9deedf62f6e4c882d1857b5cb8d488
}
