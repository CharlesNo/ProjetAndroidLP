package fr.iut.licence.projetandroid;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.iut.licence.projetandroid.entities.Plot;

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
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context
						.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_plot, parent, false);
		
		TextView tv_plot = (TextView) rowView.findViewById(R.id.tv_plot);
		TextView tv_type = (TextView) rowView.findViewById(R.id.tv_type);

		
		tv_plot.setText(mValues.get(position).getName());
		tv_type.setText(mValues.get(position).getGrowing());
		
		return rowView;
	}


}
