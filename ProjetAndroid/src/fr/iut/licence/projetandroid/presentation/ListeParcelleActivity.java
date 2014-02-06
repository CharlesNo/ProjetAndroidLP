package fr.iut.licence.projetandroid.presentation;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import fr.iut.licence.projetandroid.PlotArrayAdapter;
import fr.iut.licence.projetandroid.R;
import fr.iut.licence.projetandroid.entities.Plot;

/**
 * The Class MainActivity.
 */

public class ListeParcelleActivity extends Activity
{
	/** The list plot. */
	private List<Plot>	listPlot;
	private Context mContext;

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
		ListView list = (ListView) findViewById(R.id.liste);
		
		//TODO get plots for DB.
		//------------------------test------------------------
		listPlot = new ArrayList<Plot>();
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
		
		listPlot.add(plot);
		listPlot.add(plot1);
		

		//------------------------test------------------------
		PlotArrayAdapter arrayPlot = new PlotArrayAdapter(this, listPlot);
		list.setAdapter(arrayPlot);		
		
		list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                    long id) {
				final Bundle bundle = new Bundle();
				bundle.putSerializable("plot", listPlot.get(position));
				final Intent intent = new Intent(mContext, PlotActivty.class);
				intent.putExtra("bundle", bundle);
				startActivity(intent);
            }
        });
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

}
