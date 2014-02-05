package fr.iut.licence.projetandroid;

import fr.iut.licence.projetandroid.entities.Plot;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PlotActivty extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plot);
		
		//Load plot from intent
		Intent intent = getIntent();
		Bundle  bundle = intent.getBundleExtra("bundle");
		Plot plot = (Plot)bundle.getSerializable("plot");
		
		//Load content
		TextView tv_cult = (TextView)findViewById(R.id.tv_plotCul);
		TextView tv_cultPrev = (TextView)findViewById(R.id.tv_plotCulPrev);
		TextView tv_surface = (TextView)findViewById(R.id.tv_plotSurf);
		
		//set info
		tv_cult.setText(plot.getGrowing());
		tv_cultPrev.setText(plot.getLast_growing());
		tv_surface.setText(plot.getSurface());
	}
	
}
