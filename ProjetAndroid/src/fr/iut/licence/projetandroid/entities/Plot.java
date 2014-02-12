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
import java.net.URL;
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
	/** The adresse. */
	@DatabaseField
	private String				adresse;
	/** The growing. */
	@DatabaseField
	private String				growing;
	/** The id. */
	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
	private Long				id;
	/** The image. */
	@DatabaseField
	private String				image;
	/** The last_growing. */
	@DatabaseField
	private String				last_growing;
	/** The latitude. */
	@DatabaseField
	private Double				latitude;
	/** The longitude. */
	@DatabaseField
	private Double				longitude;
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

	/**
	 * Gets the adresse.
	 * 
	 * @return the adresse
	 */
	public String getAdresse()
	{
		return adresse;
	}

	/**
	 * Gets the growing.
	 * 
	 * @return the growing
	 */
	public String getGrowing()
	{
		return growing;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * Gets the image.
	 * 
	 * @return the image
	 */
	public String getImage()
	{
		return image;
	}

	/**
	 * Gets the last_growing.
	 * 
	 * @return the last_growing
	 */
	public String getLast_growing()
	{
		return last_growing;
	}

	/**
	 * Gets the latitude.
	 * 
	 * @return the latitude
	 */
	public Double getLatitude()
	{
		return latitude;
	}

	/**
	 * Gets the longitude.
	 * 
	 * @return the longitude
	 */
	public Double getLongitude()
	{
		return longitude;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Gets the surface.
	 * 
	 * @return the surface
	 */
	public int getSurface()
	{
		return surface;
	}

	/**
	 * Sets the adresse.
	 * 
	 * @param adresse
	 *            the new adresse
	 */
	public void setAdresse(final String adresse)
	{
		this.adresse = adresse;
	}

	/**
	 * Sets the growing.
	 * 
	 * @param growing
	 *            the new growing
	 */
	public void setGrowing(final String growing)
	{
		this.growing = growing;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(final Long id)
	{
		this.id = id;
	}

	/**
	 * Sets the image.
	 * 
	 * @param image
	 *            the new image
	 */
	public void setImage(final String image)
	{
		this.image = image;
	}

	/**
	 * Sets the last_growing.
	 * 
	 * @param last_growing
	 *            the new last_growing
	 */
	public void setLast_growing(final String last_growing)
	{
		this.last_growing = last_growing;
	}

	/**
	 * Sets the latitude.
	 * 
	 * @param latitude
	 *            the new latitude
	 */
	public void setLatitude(final Double latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * Sets the longitude.
	 * 
	 * @param longitude
	 *            the new longitude
	 */
	public void setLongitude(final Double longitude)
	{
		this.longitude = longitude;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * Sets the surface.
	 * 
	 * @param surface
	 *            the new surface
	 */
	public void setSurface(final int surface)
	{
		this.surface = surface;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Plot.java.
 * /*_________________________________________________________
 */
