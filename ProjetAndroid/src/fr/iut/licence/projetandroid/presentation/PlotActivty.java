package fr.iut.licence.projetandroid.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import fr.iut.licence.projetandroid.R;
import fr.iut.licence.projetandroid.entities.Plot;

/**
 * The Class PlotActivty.
 */
public class PlotActivty extends Activity
{
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
		setContentView(R.layout.activity_plot);
		// Load plot from intent
		final Intent intent = getIntent();
		final Bundle bundle = intent.getBundleExtra("bundle");
		final Plot plot = (Plot) bundle.getSerializable("plot");
		// Load content
		final TextView tv_name = (TextView) findViewById(R.id.tv_plotName);
		final TextView tv_cult = (TextView) findViewById(R.id.tv_plotCul);
		final TextView tv_cultPrev = (TextView) findViewById(R.id.tv_plotCulPrev);
		// ImageView iv_plot = (ImageView) findViewById(R.id.iv_plotImg);
		final TextView tv_surface = (TextView) findViewById(R.id.tv_plotSurf);
		// set info
		tv_name.setText(plot.getName());
		tv_cult.setText(plot.getGrowing());
		tv_cultPrev.setText(plot.getLast_growing());
		tv_surface.setText(String.valueOf(plot.getSurface()));
	}
}
