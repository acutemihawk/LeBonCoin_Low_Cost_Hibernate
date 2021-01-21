package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDAO.
 */
public class UserDAO 
{
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager em;
	private EntityManagerFactory emf;
	/**
	 * Instantiates a new user DAO.
	 */
	public UserDAO()
	{
		emf  = Persistence.createEntityManagerFactory("Test");
        em = emf.createEntityManager() ;
	}

	/**
	 * Gets the id of the User in parameters
	 *
	 * @param myUser 
	 * @return user id
	 */
	public long getUserId(User myUser)
	{
		long id = -1;		
		
		try
		{
			id = (long) emf.getPersistenceUnitUtil().getIdentifier(myUser);
			return id;
		}
		catch (PersistenceException e) 
		{
			System.out.println(e.getMessage());
			return -1;
		}
	}
	

	
	/**
	 * Gets the username of the User in parameters
	 *
	 * @param myUser 
	 * @return username
	 * @throws Exception 
	 */
	public String getUserName(User myUser)
    {
        String name = "";
        
        try
        {
        	User userTmp = em.find(User.class, myUser.getIdUser());
        	if(userTmp != null)
        	{
        		return userTmp.getUsername();
        	}
    		System.out.println("could not get Username");
    		return name;      
        }
		catch (PersistenceException e) 
		{
            System.out.println(e.getMessage());
            return name;
        }
    }
	
	/**
	 * Gets the list of Advertisment of the user in parameters
	 *
	 * @param myUser 
	 * @return a list of Advertisment
	 */
	public List<Advertisment> getUserListAdv(User myUser)
	{
		try
		{
			User userTmp = em.find(User.class, myUser.getIdUser());
        	if(userTmp != null)
        	{
        		return userTmp.getListAdvertisment();
        	}
     		System.out.println("could not get User List Propositions");
     		return null;
		}
		catch (PersistenceException e) 
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Gets the list of offer received of the user in parameters
	 *
	 * @param myUser 
	 * @return the user list offer
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getUserListOffer(User myUser)
	{	
		
		List<Integer> resultList = new ArrayList<Integer>();
		
		String SQL = "SELECT offer.idoffer FROM advertisment INNER JOIN OFFER ON advertisment.idAdvertisment= offer.idAdvertisment WHERE advertisment.iduser= ?";
		Query query = em.createNativeQuery(SQL);
		resultList = (List<Integer>)query.setParameter(1, myUser.getIdUser()).getResultList();
		return resultList;
	}
	
	/**
	 * Gets the list of propositions of the user in parametres
	 *
	 * @param myUser the my user
	 * @return the user list propositions
	 */
	/* renvoie la liste des propositions faites par l'utilisateur passé en parametre */
	public List<Offer> getUserListPropositions(User myUser)
	{
		try
		{
			User userTmp = em.find(User.class, myUser.getIdUser());
        	if(userTmp != null)
        	{
        		return userTmp.getListOffer();
        	}
     		System.out.println("could not get User List Propositions");
     		return null;
		}
		catch (PersistenceException e) 
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Gets the user mail.
	 *
	 * @param myUser the my user
	 * @return the user mail
	 */
	public String getUserMail(User myUser)
	{
        String mail = "";
        
        try
        {
        	User userTmp = em.find(User.class, myUser.getIdUser());
        	if(userTmp != null)
        	{
        		return userTmp.getMail();
        	}
    		System.out.println("could not get Mail");
    		return mail;      
        }
		catch (PersistenceException e) 
		{
            System.out.println(e.getMessage());
            return mail;
        }	
	}
	
	/**
	 * Insert the user in parameters into the database
	 *
	 * @param userToCreate the user to create
	 * @return true, if successful
	 */
	public boolean insertUser(User userToCreate)
	{
		try
		{
			em.getTransaction().begin();
			em.persist(userToCreate);
			em.getTransaction().commit();
			return true;
		}
		catch (PersistenceException e) 
		{
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	/**
	 * Checks if the username in parameters is not already taken by someone
	 *
	 * @param username the username
	 * @return true if the username is not used
	 */
	public boolean usernameInputChecker(String username)
	{
		try
		{
			String sql = "SELECT COUNT(u.idUser) from User u WHERE username = ?1 ";
			Query query = em.createQuery(sql);
			long count = (long)query.setParameter(1, username).getSingleResult();

			if(count == 1)
				return false;
			
			return true;
		}
		catch (PersistenceException e) 
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Checks if the mail in parameters is not already taken by someone
	 *
	 * @param mail the mail
	 * @return true if the mail is not used
	 */
	/* renvoie false si le nom username est deja utilisé, sinon vrai*/
	public boolean mailInputChecker(String mail)
	{
		try
		{
			String sql = "SELECT COUNT(u.idUser) from User u WHERE u.mail = ?1 ";
			Query query = em.createQuery(sql);
			long count = (long)query.setParameter(1, mail).getSingleResult();

			if(count == 1)
				return false;
			
			return true;
		}
		catch (PersistenceException e) 
		{
			System.out.println(e.getMessage());
			return false;
		}
	}

	public EntityManager getMyEntityManager() 
	{
		return em;
	}

	public void setMyEntityManager(EntityManager myEntityManager) 
	{
		this.em = myEntityManager;
	}
	
	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
}