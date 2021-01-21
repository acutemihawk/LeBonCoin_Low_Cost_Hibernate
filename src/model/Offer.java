package model;

import javax.persistence.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Offer.
 */
@Entity
public class Offer
{
	
	/** The id offer. */
	@Id 
	@GeneratedValue
	@Column(name = "idoffer")
	private long idOffer;
	
	@ManyToOne
	@JoinColumn(name = "idbuyer")
	private User buyer;
	
	@ManyToOne
	@JoinColumn(name = "idAdvertisment")
	private Advertisment Adv;
	
	@Column(name = "price_offer ")
	/** The new price. */
	private float newPrice;
	
	
	/**
	 * Instantiates a new offer.
	 *
	 * @param idAd the id ad
	 * @param idBuy the id buy
	 * @param Offer_newPrice the offer new price
	 */
	public Offer(float Offer_newPrice)
	{
		newPrice = Offer_newPrice;
	}
	
	/**
	 * Instantiates a new offer.
	 */
	public Offer()
	{
	}
	
	/**
	 * Gets the id offer.
	 *
	 * @return the id offer
	 */
	public long getIdOffer()
	{
		return idOffer;
	}
	
	/**
	 * Sets the id offer.
	 *
	 * @param idOffer the new id offer
	 */
	public void setIdOffer(long idOffer)
	{
		this.idOffer = idOffer;
	}
	
	/**
	 * Gets the new price.
	 *
	 * @return the new price
	 */
	public float getNewPrice()
	{
		return newPrice;
	}
	
	/**
	 * Sets the new price.
	 *
	 * @param newPrice the new new price
	 */
	public void setNewPrice(float newPrice)
	{
		this.newPrice = newPrice;
	}
	
	public User getBuyer() 
	{
		return buyer;
	}

	public void setBuyer(User buyer)
	{
		this.buyer = buyer;
	}
	
	public Advertisment getAdv() 
	{
		return Adv;
	}

	public void setAdv(Advertisment adv)
	{
		Adv = adv;
	}
}