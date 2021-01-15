package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
@Entity
public class User 
{
	
	/** The id user. */
	@Id 
	@GeneratedValue
	@Column(name = "iduser")
	private long idUser;
	
	/** The mail. */
	private String mail;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The list advertisment. */
	@OneToMany(mappedBy ="owner",cascade = CascadeType.ALL)
	private List<Advertisment> listAdvertisment;
	
	/** The list offer. */
	@OneToMany(mappedBy ="buyer",cascade = CascadeType.ALL)
	private List<Offer> listOffer;
	
	@Transient
	/** The is connected. */
	private boolean isConnected = false;
	
	
	/**
	 * Instantiates a new user.
	 *
	 * @param idUser the id user
	 * @param username the username
	 * @param mail the mail
	 */
	public User(long idUser,String username,String mail)
	{
		this.idUser = idUser;
		this.username = username;
		this.mail = mail;
		listAdvertisment = new ArrayList<Advertisment>();
		listOffer = new ArrayList<Offer>();
	}
	
	/**
	 * Instantiates a new user.
	 */
	public User()
	{
		listAdvertisment = new ArrayList<Advertisment>();
		listOffer = new ArrayList<Offer>();
	}
	
	/**
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	public long getIdUser() 
	{
		return idUser;
	}
	
	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(long idUser) 
	{
		this.idUser = idUser;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() 
	{
		return username;
	}
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() 
	{
		return mail;
	}
	
	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail) 
	{
		this.mail = mail;
	}
	
	/**
	 * Checks if is connected.
	 *
	 * @return true, if is connected
	 */
	public boolean isConnected() 
	{
		return isConnected;
	}
	
	/**
	 * Sets the connected.
	 *
	 * @param isConnected the new connected
	 */
	public void setConnected(boolean isConnected) 
	{
		this.isConnected = isConnected;
	}
	
	/**
	 * Gets the list advertisment.
	 *
	 * @return the list advertisment
	 */
	public List<Advertisment> getListAdvertisment() 
	{
		return listAdvertisment;
	}
	
	/**
	 * Sets the list advertisment.
	 *
	 * @param listAdvertisment the new list advertisment
	 */
	public void setListAdvertisment(List<Advertisment> listAdvertisment) 
	{
		this.listAdvertisment = listAdvertisment;
	}
	
	/**
	 * Gets the list offer.
	 *
	 * @return the list offer
	 */
	public List<Offer> getListOffer() 
	{
		return listOffer;
	}
	
	/**
	 * Sets the list offer.
	 *
	 * @param listOffer the new list offer
	 */
	public void setListOffer(ArrayList<Offer> listOffer) 
	{
		this.listOffer = listOffer;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}