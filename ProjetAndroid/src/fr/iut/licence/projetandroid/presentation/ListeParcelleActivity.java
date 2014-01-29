package fr.iut.licence.projetandroid.presentation;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import fr.iut.licence.projetandroid.R;

/**
 * The Class MainActivity.
 */
public class ListeParcelleActivity extends ListActivity
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
		setContentView(R.layout.activity_listeparcelle);

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
	
	/**
	 * View Holder Item
	 *
	 */
	static class ViewHolderItem {
	    public TextView tv_parcelle;
	    public TextView tv_typeParcelle;
	}

}
