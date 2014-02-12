/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : DaoUtils.java
 * 
 * Créé le 5 févr. 2014 à 08:24:48
 * 
 * Auteur : Charles NEAU
 */
package fr.iut.licence.projetandroid.persistence;

import java.sql.SQLException;
import java.util.List;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

/* _________________________________________________________ */
/**
 * The Class DaoUtils.
 * 
 * @author Charles NEAU
 */
public class DaoUtils
{
	/**
	 * Gets the all data.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param pContext
	 *            the context
	 * @param pClazz
	 *            the clazz
	 * @return the all data
	 */
	public static <T> List<T> getAllData(final Context pContext,
			final Class<T> pClazz)
	{
		List<T> ret = null;
		final SQLiteOpenHelper db = DatabaseHelper.getInstance(pContext);
		final ConnectionSource connectionSource = new AndroidConnectionSource(
				db);
		try
		{
			@SuppressWarnings("unchecked")
			final Dao<T, Integer> dao = (Dao<T, Integer>) DaoManager.createDao(
					connectionSource, pClazz);
			if (dao != null)
			{
				ret = dao.queryForAll();
			}
		}
		catch (final SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			connectionSource.closeQuietly();
		}
		return ret;
	}

	/**
	 * Gets the eq datas.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param pContext
	 *            the context
	 * @param pClazz
	 *            the clazz
	 * @param pField
	 *            the field
	 * @param pValue
	 *            the value
	 * @param pOrder
	 *            the order
	 * @param pLimit
	 *            the limit
	 * @return the eq datas
	 */
	public static <T> List<T> getEqDatas(final Context pContext,
			final Class<T> pClazz, final String pField, final Object pValue,
			final String pOrder, final long pLimit)
	{
		List<T> ret = null;
		final SQLiteOpenHelper db = DatabaseHelper.getInstance(pContext);
		final ConnectionSource connectionSource = new AndroidConnectionSource(
				db);
		try
		{
			@SuppressWarnings("unchecked")
			final Dao<T, Integer> dao = (Dao<T, Integer>) DaoManager.createDao(
					connectionSource, pClazz);
			if (dao != null)
			{
				final QueryBuilder<T, Integer> queryBuilder = dao
						.queryBuilder();
				if (pField != null)
				{
					queryBuilder.where().eq(pField, pValue);
				}
				if (pLimit != 0)
				{
					queryBuilder.limit(pLimit);
				}
				if (pOrder != null)
				{
					queryBuilder.orderBy(pOrder, false);
				}
				ret = queryBuilder.query();
			}
		}
		catch (final SQLException e)
		{
		}
		finally
		{
			connectionSource.closeQuietly();
		}
		return ret;
	}

	/**
	 * Store single data.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param pContext
	 *            the context
	 * @param pData
	 *            the data
	 */
	@SuppressWarnings("unchecked")
	public static <T> void storeSingleData(final Context pContext, final T pData)
	{
		final Class<?> type = pData.getClass();
		final SQLiteOpenHelper db = DatabaseHelper.getInstance(pContext);
		final ConnectionSource connectionSource = new AndroidConnectionSource(
				db);
		Dao<T, Integer> dao = null;
		try
		{
			dao = (Dao<T, Integer>) DaoManager
					.createDao(connectionSource, type);
			if (dao != null)
			{
				dao.createOrUpdate(pData);
			}
		}
		catch (final SQLException e)
		{
		}
		finally
		{
			connectionSource.closeQuietly();
		}
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier DaoUtils.java.
 * /*_________________________________________________________
 */
