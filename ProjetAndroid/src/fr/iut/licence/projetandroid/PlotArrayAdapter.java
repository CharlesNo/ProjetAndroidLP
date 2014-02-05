package fr.iut.licence.projetandroid;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.iut.licence.projetandroid.entities.Plot;
import fr.iut.licence.projetandroid.entities.ViewHolder;

public class PlotArrayAdapter extends ArrayAdapter<String>  {
	private final Context mContext;
	private final List<Plot> mValues;
	
	public PlotArrayAdapter(Context context,List<Plot> plots) {
		super(context, R.layout.list_plot);
		mContext = context;
		mValues = plots;	
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context
							.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_plot, parent, false);

			holder = new ViewHolder ();
			
			holder.plotName= (TextView) convertView.findViewById(R.id.tv_plot);
			holder.plotType = (TextView) convertView.findViewById(R.id.tv_type);
			
			//Store the holder with the view.
			convertView.setTag(holder);
		} else {
			// Else re-assign
			holder = ( ViewHolder ) convertView.getTag();
		}

		holder.plotName.setText(mValues.get(position).getName());
		holder.plotType.setText(mValues.get(position).getGrowing());
		
		return convertView;
	}


}
