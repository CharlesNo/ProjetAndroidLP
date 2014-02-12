package fr.iut.licence.projetandroid;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import fr.iut.licence.projetandroid.entities.Constantes;
import fr.iut.licence.projetandroid.entities.Plot;
import fr.iut.licence.projetandroid.entities.ViewHolder;

/**
 * The Class PlotArrayAdapter.
 */
public class PlotArrayAdapter extends BaseAdapter
{
	/** The m context. */
	private final Context	mContext;
	/** The m values. */
	private List<Plot>		mValues;
	/** The type. */
	private final int		type;

	/**
	 * Instantiates a new plot array adapter.
	 * 
	 * @param context
	 *            the context
	 * @param plots
	 *            the plots
	 * @param typeAffich
	 *            the type affich
	 */
	public PlotArrayAdapter(final Context context, final List<Plot> plots,
			final int typeAffich)
	{
		mContext = context;
		mValues = plots;
		type = typeAffich;
	}

	/**
	 * Adds the plot.
	 * 
	 * @param plot
	 *            the plot
	 */
	public void addPlot(final Plot plot)
	{
		mValues.add(plot);
		notifyDataSetChanged();
	}

	/* _________________________________________________________ */
	/**
	 * Gets the count.
	 * 
	 * @return the count
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return mValues.size();
	}

	/* _________________________________________________________ */
	/**
	 * Gets the item.
	 * 
	 * @param arg0
	 *            the arg0
	 * @return the item
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(final int arg0)
	{
		return mValues.get(arg0);
	}

	/* _________________________________________________________ */
	/**
	 * Gets the item id.
	 * 
	 * @param arg0
	 *            the arg0
	 * @return the item id
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(final int arg0)
	{
		return mValues.get(arg0).getId();
	}

	/**
	 * Gets the list plot.
	 * 
	 * @return the list plot
	 */
	public List<Plot> getListPlot()
	{
		return mValues;
	}

	/* _________________________________________________________ */
	/**
	 * Gets the view.
	 * 
	 * @param position
	 *            the position
	 * @param convertView
	 *            the convert view
	 * @param parent
	 *            the parent
	 * @return the view
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 *      android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View convertView,
			final ViewGroup parent)
	{
		ViewHolder holder;
		if (convertView == null)
		{
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.list_plot, parent, false);
			holder = new ViewHolder();
			holder.plotName = (TextView) convertView.findViewById(R.id.tv_plot);
			holder.plotType = (TextView) convertView.findViewById(R.id.tv_type);
			// Store the holder with the view.
			convertView.setTag(holder);
		}
		else
		{
			// Else re-assign
			holder = (ViewHolder) convertView.getTag();
		}
		final Plot p = mValues.get(position);
		holder.plotName.setText(p.getName());
		switch (type)
		{
			case Constantes.TYPE_SURFACE:
				holder.plotType.setText(String.valueOf(p.getSurface()));
				break;
			case Constantes.TYPE_SEMANCE:
				holder.plotType.setText(p.getGrowing());
				break;
			default:
				holder.plotType.setText(p.getGrowing());
				break;
		}
		return convertView;
	}

	/**
	 * Removes the.
	 * 
	 * @param object
	 *            the object
	 * @return true, if successful
	 */
	public boolean remove(final Object object)
	{
		mValues.remove(object);
		notifyDataSetChanged();
		return true;
	}

	/**
	 * Sets the list plot.
	 * 
	 * @param list
	 *            the new list plot
	 */
	public void setListPlot(final List<Plot> list)
	{
		mValues = list;
		notifyDataSetChanged();
	}
}
