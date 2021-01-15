package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The Class Advertisment.
 */
@Entity
public class Advertisment
{
	
	/** The id advertisment. */
	@Id 
	@GeneratedValue
	private	long idAdvertisment;
	
	/** The price. */
	private float price;
	
	/** The titre. */
	private String titre;
	
	/** The category. */
	private String category;
	
	/** The localisation. */
	private String localisation;
	
	/** The description. */
	private String description;
	
	/** The list my offer. */
	@OneToMany(mappedBy ="Adv",cascade = CascadeType.ALL)
	private List<Offer> listMyOffer;
	
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User owner;
	
	/** The id owner. */
	@Transient
	private long idOwner;
	
	/**
	 * Instantiates a new advertisment.
	 *
	 * @param idOwn the id own
	 * @param Ad_titre the ad titre
	 * @param Ad_category the ad category
	 * @param Ad_localisation the ad localisation
	 * @param Ad_price the ad price
	 * @param Ad_desc the ad desc
	 */
	public Advertisment(long idOwn, String Ad_titre, String Ad_category, String Ad_localisation, float Ad_price, String Ad_desc)
	{
		idOwner = idOwn;
		titre = Ad_titre;
		category = Ad_category;
		localisation = Ad_localisation;
		price = Ad_price;
		description = Ad_desc;
		listMyOffer = new ArrayList<Offer>();
	}
	
	/**
	 * Instantiates a new advertisment.
	 */
	public Advertisment() 
    {
        listMyOffer = new ArrayList<Offer>();
    }
	
	/**
	 * Gets the id advertisment.
	 *
	 * @return the id advertisment
	 */
	public long getIdAdvertisment()
	{
		return idAdvertisment;
	}
	
	/**
	 * Sets the id advertisment.
	 *
	 * @param idAdvertisment the new id advertisment
	 */
	public void setIdAdvertisment(long idAdvertisment)
	{
		this.idAdvertisment = idAdvertisment;
	}
	
	/**
	 * Gets the id owner.
	 *
	 * @return the id owner
	 */
	public long getIdOwner()
	{
		return idOwner;
	}
	
	/**
	 * Sets the id owner.
	 *
	 * @param idOwner the new id owner
	 */
	public void setIdOwner(long idOwner)
	{
		this.idOwner = idOwner;
	}
	
	/**
	 * Gets the titre.
	 *
	 * @return the titre
	 */
	public String getTitre()
	{
		return titre;
	}
	
	/**
	 * Sets the titre.
	 *
	 * @param titre the new titre
	 */
	public void setTitre(String titre)
	{
		this.titre = titre;
	}
	
	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory()
	{
		return category;
	}
	
	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public float getPrice()
	{
		return price;
	}
	
	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(float price)
	{
		this.price = price;
	}
	
	/**
	 * Gets the localisation.
	 *
	 * @return the localisation
	 */
	public String getLocalisation()
	{
		return localisation;
	}
	
	/**
	 * Sets the localisation.
	 *
	 * @param localisation the new localisation
	 */
	public void setLocalisation(String localisation)
	{
		this.localisation = localisation;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * Gets the list my offer.
	 *
	 * @return the list my offer
	 */
	public List<Offer> getListMyOffer()
	{
		return listMyOffer;
	}
	
	/**
	 * Sets the list my offer.
	 *
	 * @param listMyOffer the new list my offer
	 */
	public void setListMyOffer(ArrayList<Offer> listMyOffer)
	{
		this.listMyOffer = listMyOffer;
	}
	

	
	
	
}