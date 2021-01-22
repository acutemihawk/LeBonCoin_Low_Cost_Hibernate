package controller;

import java.util.ArrayList;
import java.util.List;

import model.*;

// TODO: Auto-generated Javadoc
/**
 * The Class MainController.
 */ 
public class MainController 
{
	
	/** The my user. */
	private User myUser;
	
	/** The my user DAO. */
	private UserDAO myUserDAO;
	
	/** The my adv. */
	private Advertisment myAdv;
	
	/** The my adv DAO. */
	private AdvertismentDAO myAdvDAO;
	
	/** The my offer. */
	private Offer myOffer;
	
	/** The my of DAO. */
	private OfferDAO myOfDAO;

	
	/**
	 * Instantiates a new main controller.
	 */
	public MainController() 
	{
		myUser = new User();
		myOffer = new Offer();
		myAdv = new Advertisment();
		myAdvDAO = new AdvertismentDAO();
		myUserDAO = new UserDAO();
		myOfDAO = new OfferDAO();
	}
	
	/**
	 * Connects the user to the data base with the given entries
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean userConnect(String username, String password)
	{
		long id = myUserDAO.authentificate(username, password);

		if(id != 0)
		{
			if(myUserDAO.getUserById(id) != null)
			{
				myUser = myUserDAO.getUserById(id);
				if(myAdvDAO.getUserAdvertisments(myUser.getIdUser()) != null )
				{
					System.out.println(myAdvDAO.getUserAdvertisments(myUser.getIdUser()).get(0).getIdAdvertisment());
					myUser.setListAdvertisment(myAdvDAO.getUserAdvertisments(myUser.getIdUser()));
				}
				if(myUserDAO.getUserListPropositions(myUser) != null )
				{
					System.out.println(myUserDAO.getUserListPropositions(myUser).get(0).getIdOffer());
					myUser.setListProposition(myUserDAO.getUserListPropositions(myUser));
				}
				//MERGE
				myUser = myUserDAO.mergeUser(myUser);
				
			 	//myUser.setListAdvertisment(myAdvDAO.getUserAdvertisments(myUser.getIdUser()));
				myUser.setConnected(true);
				return true;
			}
			else
				return false;
		}
		return false;
	}
	
	/**
	 * Creates a new user account into the database with the given entries
	 *
	 * @param username the username
	 * @param password the password
	 * @param mail the mail
	 * @return true, if successful
	 */
	public boolean createAccount(String username,String password, String mail)
	{
		if(myUserDAO.usernameInputChecker(username) == true && myUserDAO.mailInputChecker(mail) == true)
		{
			User myUserTmp = new User();
			myUserTmp.setUsername(username);
			myUserTmp.setPassword(password);
			myUserTmp.setMail(mail);
			System.out.println("DANS CREATE");
			if(myUserDAO.insertUser(myUserTmp) == true)
			{
				System.out.println("User successfully created");
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}


	public boolean disconnect()
	{
		myUser.setConnected(false);
		myUser.getListAdvertisment().clear();
		myUser.getListProposition().clear();
		return true;
	}
	
	/**
	 * Creates and adds an advertisment on the website, the owner is the user calling the method
	 *
	 * @param titre the titre
	 * @param localisation the localisation
	 * @param price the price
	 * @param description the description
	 * @param category the category
	 * @return true, if successful
	 */
	public boolean addUserAdvertisment(String titre, String localisation, float price, String description,String category)
	{
		if (testConnection() == false)
			return false;
		
		if(price <0)
        {
            System.out.println("Please put a price above 0...");
            return false;
        }
		
		Advertisment myAdvTmp = new Advertisment();

		myAdvTmp.setTitre(titre);
		myAdvTmp.setCategory(category);
		myAdvTmp.setDescription(description);
		myAdvTmp.setPrice(price);
		myAdvTmp.setLocalisation(localisation);
		
		myUser.addAdvertisment(myAdvTmp);
		myAdvTmp.setOwner(myUser);
		//return true;
		if(myAdvDAO.insertAd(myAdvTmp) == true)
			return true;
		else
			return false;
	}
	
	/**
	 * Delete an advertisment from the user calling the method on the website
	 *
	 * @param idAdvToDel the id adv to del
	 * @return true, if successful
	 */
	public boolean delUserAdvertisment(long idAdv)
	{
		if (testConnection() == false)
			return false;
		
		
		if(myAdvDAO.deleteAd(idAdv) == true)
			return true;
		else 
			return false;

	}
	
	/**
	 * Makes a proposition to an advertisment with a price
	 *
	 * @param idAdv the id adv
	 * @param newPrice the new price
	 * @return true, if successful
	 */
	public boolean addUserProposition(long idAdv,float newPrice)
	{
		if (testConnection() == false)
			return false;
		
		if(newPrice <0)
		{
			System.out.println("Please put a price that is above 0$");
			return false;
		}
		
		Advertisment advProposedTo = myAdvDAO.getAdvertismentById(idAdv);
		Offer propositionFromUser = new Offer();
		propositionFromUser.setNewPrice(newPrice);
		
		myUser.addProposition(propositionFromUser);
		propositionFromUser.setBuyer(myUser);
		
		
		advProposedTo.addOffer(propositionFromUser);
		propositionFromUser.setAdv(advProposedTo);
		
		
		if(myOfDAO.insertOf(propositionFromUser) == true) 
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * Deletes a proposition to an advertisment
	 *
	 * @param idOffer the id offer deleted
	 * @return true, if successful
	 */
	public boolean delUserProposition(long idOffer)
    {
        if (testConnection() == false)
            return false;
        
        Offer offerToDel = myOfDAO.getOfferById(idOffer);
        Advertisment advProposedTo = myAdvDAO.getAdvertismentById(offerToDel.getAdv().getIdAdvertisment());
        
		myUser.removeProposition(offerToDel);
		offerToDel.setBuyer(null);
		
		advProposedTo.removeOffer(offerToDel);
		offerToDel.setAdv(null);
        
        if(myOfDAO.deleteOf(offerToDel) == true)
        {
        	return true;
        }
        return false;
    }
	
	/**
	 * Accept an offer from another user, deletes all other propositions on 
	 * the sold advertisment and deletes the advertisment from the website
	 *
	 * @param idOffer the id offer accepted
	 * @return true, if successful
	 */
	public boolean acceptOffer(long idOffer)
	{
		if (testConnection() == false)
			return false;
		
		Offer offerAccepted = myOfDAO.getOfferById(idOffer);
		
        Advertisment advSold = myAdvDAO.getAdvertismentById(offerAccepted.getAdv().getIdAdvertisment());
        
        if(offerAccepted.getAdv().getOwner().getIdUser() == myUser.getIdUser())		
        {
			advSold.removeOffer(offerAccepted);
			//return ( myAdvDAO.deleteAd(advSold) == true && myOfDAO.deleteAllProposition(offerAccepted) == true );
			return false;
	
		}
		else 
		{
			System.out.println("Please accept an offer that is addressed to you ");
			return false;	
		}
	}

	
	/**
	 * Refuse an offer made by a user on an advertisment
	 *
	 * @param idOffer the id offer refused
	 * @return true, if successful
	 */
	public boolean refuseOffer(long idOffer)
	{
		if (testConnection() == false)
			return false;
		
		Offer offerRefused = myOfDAO.getOfferById(idOffer);
		Advertisment adRefusingOffer = myAdvDAO.getAdvertismentById(offerRefused.getAdv().getIdAdvertisment());
		User userDeniedOffer = myUserDAO.getUserById(offerRefused.getBuyer().getIdUser());
		
		 if(offerRefused.getAdv().getOwner().getIdUser() == myUser.getIdUser())		
		 {
			adRefusingOffer.removeOffer(offerRefused);
			userDeniedOffer.removeProposition(offerRefused);
			offerRefused.setAdv(null);
			
			if(myOfDAO.deleteOf(offerRefused) == true)
				return true;
			else
				return false;
			
		}
		else
		{
			System.out.println("Please accept an offer that is addressed to you ");
			return false;
		}

	}
	
	/**
	 * Gets an arrayList of offer that represents all of the proposition made by the user to different advertisement 
	 *
	 * @return the user propositions
	 */
	public List<Offer> getUserPropositions()
	{
		if (testConnection() == false)
			return null;
		
		return myUserDAO.getUserListPropositions(myUser);

	}
	
	/**
	 * Gets all of the offer received on all advertisment sold by the user
	 *
	 * @return the user received offer
	 */
	/* renvoie une listes des offre recues par l'uitlisateurs */
	public List<Offer> getUserReceivedOffer()
	{
		if (testConnection() == false)
			return null;
		
		return myUserDAO.getUserListOffer(myUser);
	}
	
	
	/**
	 * Gets the my user.
	 *
	 * @return the my user
	 */
	public User getMyUser() 
	{
		return myUser;
	}

	/**
	 * Sets the my user.
	 *
	 * @param myUser the new my user
	 */
	public void setMyUser(User myUser) 
	{
		this.myUser = myUser;
	}
	
	/**
	 * Test connection to database.
	 *
	 * @return true, if successful
	 */
	private boolean testConnection()
	{
		if(myUser.isConnected() == false)
		{
			System.out.println("You need to register to use this functionnality");
			return false;
		}
		else
			return true;
	}

	/**
	 * Gets the my user DAO.
	 *
	 * @return the my user DAO
	 */
	public UserDAO getMyUserDAO() {
		return myUserDAO;
	}

	/**
	 * Sets the my user DAO.
	 *
	 * @param myUserDAO the new my user DAO
	 */
	public void setMyUserDAO(UserDAO myUserDAO) {
		this.myUserDAO = myUserDAO;
	}

	/**
	 * Gets the my of DAO.
	 *
	 * @return the my of DAO
	 */
	public OfferDAO getMyOfDAO() {
		return myOfDAO;
	}

	/**
	 * Sets the my of DAO.
	 *
	 * @param myOfDAO the new my of DAO
	 */
	public void setMyOfDAO(OfferDAO myOfDAO) {
		this.myOfDAO = myOfDAO;
	}

	/**
	 * Gets the my offer.
	 *
	 * @return the my offer
	 */
	public Offer getMyOffer() 
	{
		return myOffer;
	}

	/**
	 * Sets the my offer.
	 *
	 * @param myOffer the new my offer
	 */
	public void setMyOffer(Offer myOffer) 
	{
		this.myOffer = myOffer;
	}

	/**
	 * Gets the my adv DAO.
	 *
	 * @return the my adv DAO
	 */
	public AdvertismentDAO getMyAdvDAO()
	{
		return myAdvDAO;
	}

	/**
	 * Sets the my adv DAO.
	 *
	 * @param myAdvDAO the new my adv DAO
	 */
	public void setMyAdvDAO(AdvertismentDAO myAdvDAO) 
	{
		this.myAdvDAO = myAdvDAO;
	}

	/**
	 * Gets the my adv.
	 *
	 * @return the my adv
	 */
	public Advertisment getMyAdv()
	{
		return myAdv;
	}

	/**
	 * Sets the my adv.
	 *
	 * @param myAdv the new my adv
	 */
	public void setMyAdv(Advertisment myAdv)
	{
		this.myAdv = myAdv;
	}
}