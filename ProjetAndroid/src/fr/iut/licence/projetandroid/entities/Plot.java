/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : Plot.java
 * 
 * Créé le 29 janv. 2014 à 08:25:52
 * 
 * Auteur : Charles NEAU
 */
package fr.iut.licence.projetandroid.entities;

import java.io.Serializable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/* _________________________________________________________ */
/**
 * The Class Plot.
 * 
 * @author Charles NEAU
 */
@DatabaseTable(tableName = "plot")
public class Plot implements Serializable
{
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -6091604027434736676L;
	/** The growing. */
	@DatabaseField
	private String				growing;
	/** The id. */
	@DatabaseField(generatedId = true,allowGeneratedIdInsert =  true)
	private String				id;
	/** The last_growing. */
	@DatabaseField
	private String				last_growing;
	/** The name. */
	@DatabaseField
	private String				name;
	/** The surface. */
	@DatabaseField
	private int					surface;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new plot.
	 */
	public Plot()
	{
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ growing.
	 * 
	 * @return la valeur du champ growing.
	 */
	public final String getGrowing()
	{
		return growing;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ id.
	 * 
	 * @return la valeur du champ id.
	 */
	public final String getId()
	{
		return id;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ last_growing.
	 * 
	 * @return la valeur du champ last_growing.
	 */
	public final String getLast_growing()
	{
		return last_growing;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ name.
	 * 
	 * @return la valeur du champ name.
	 */
	public final String getName()
	{
		return name;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ surface.
	 * 
	 * @return la valeur du champ surface.
	 */
	public final int getSurface()
	{
		return surface;
	}

	/* _________________________________________________________ */
	/**
	 * Modifie la valeur du cmap growing.
	 * 
	 * @param growing
	 *            la valeur à placer dans le champ growing.
	 */
	public final void setGrowing(final String growing)
	{
		this.growing = growing;
	}

	/* _________________________________________________________ */
	/**
	 * Modifie la valeur du cmap id.
	 * 
	 * @param id
	 *            la valeur à placer dans le champ id.
	 */
	public final void setId(final String id)
	{
		this.id = id;
	}

	/* _________________________________________________________ */
	/**
	 * Modifie la valeur du cmap last_growing.
	 * 
	 * @param last_growing
	 *            la valeur à placer dans le champ last_growing.
	 */
	public final void setLast_growing(final String last_growing)
	{
		this.last_growing = last_growing;
	}

	/* _________________________________________________________ */
	/**
	 * Modifie la valeur du cmap name.
	 * 
	 * @param name
	 *            la valeur à placer dans le champ name.
	 */
	public final void setName(final String name)
	{
		this.name = name;
	}

	/* _________________________________________________________ */
	/**
	 * Modifie la valeur du cmap surface.
	 * 
	 * @param surface
	 *            la valeur à placer dans le champ surface.
	 */
	public final void setSurface(final int surface)
	{
		this.surface = surface;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Plot.java.
 * /*_________________________________________________________
 */
