/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : DatabaseHelper.java
 * 
 * Créé le 29 janv. 2014 à 08:29:04
 * 
 * Auteur : Charles NEAU
 */
package fr.iut.licence.projetandroid.persistence;

import java.sql.SQLException;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import fr.iut.licence.projetandroid.entities.Plot;

/* _________________________________________________________ */
/**
 * The Class DatabaseHelper.
 * 
 * @author Charles NEAU
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{
	/** The Constant DATABASE_NAME. */
	private static final String		DATABASE_NAME		= "DATABASE_ANDROID_PROJECT";
	/** The Constant DATABASE_VERSION. */
	private static final int		DATABASE_VERSION	= 1;
	/** The instance. */
	private static DatabaseHelper	instance;

	/**
	 * Instantiates a new database helper.
	 * 
	 * @param context
	 *            the context
	 */
	private DatabaseHelper(final Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Gets the single instance of DatabaseHelper.
	 * 
	 * @param context
	 *            the context
	 * @return single instance of DatabaseHelper
	 */
	public static DatabaseHelper getInstance(final Context context)
	{
		if (instance == null)
		{
			instance = new DatabaseHelper(context);
		}
		return instance;
	}

	/* _________________________________________________________ */
	/**
	 * On create.
	 * 
	 * @param sqLiteDatabase
	 *            the sq lite database
	 * @param connectionSource
	 *            the connection source
	 * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase,
	 *      com.j256.ormlite.support.ConnectionSource)
	 */
	@SuppressWarnings("unused")
	@Override
	public void onCreate(final SQLiteDatabase sqLiteDatabase,
			final ConnectionSource connectionSource)
	{
		try
		{
			TableUtils.createTable(connectionSource, Plot.class);
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
	}

	/* _________________________________________________________ */
	/**
	 * On upgrade.
	 * 
	 * @param sqLiteDatabase
	 *            the sq lite database
	 * @param connectionSource
	 *            the connection source
	 * @param oldVersion
	 *            the old version
	 * @param newVersion
	 *            the new version
	 * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase,
	 *      com.j256.ormlite.support.ConnectionSource, int, int)
	 */
	@SuppressWarnings("unused")
	@Override
	public void onUpgrade(final SQLiteDatabase sqLiteDatabase,
			final ConnectionSource connectionSource, final int oldVersion,
			final int newVersion)
	{
		try
		{
			TableUtils.dropTable(connectionSource, Plot.class, true);
			onCreate(sqLiteDatabase, connectionSource); // Create tables again
		}
		catch (final SQLException sqle)
		{
		}
	}
}
/* newVersion_______________________________________________ */
/*
 * Fin du fichier DatabaseHelper.java.
 * /*_________________________________________________________
 */
