package controller;

import model.*;

import java.math.BigInteger;
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
		try
		{
			emf  = Persistence.createEntityManagerFactory("Test");
	        em = emf.createEntityManager() ;
		}
		catch (PersistenceException e) 
		{
            System.out.println("Could not connect to Database");
            System.exit(1);
        }
	}

	
	public long authentificate(String username, String password)
	{
		long iduser = -1;
		try
		{
			String hql = "SELECT idUser from User WHERE username = ?1 AND password = ?2";
			Query query = em.createQuery(hql);
			query.setParameter(1, username);
			query.setParameter(2, password);
			iduser = (long)query.getSingleResult();
			
			if(iduser != -1)
				return iduser;
			
			return iduser;
		}
		catch (PersistenceException e) 
		{
            System.out.println(e.getMessage());
            return iduser;
        }
	}
	
	
	/**
	 * Gets the list of offers received by the user in parameter
	 *
	 * @param myUser 
	 * @return the user list offer
	 */
	@SuppressWarnings("unchecked")
	public List<Offer> getUserListOffer(User myUser)
	{	
		try
		{
			String command = "SELECT offer.idoffer FROM advertisment INNER JOIN OFFER ON advertisment.idAdvertisment= offer.idAdvertisment WHERE advertisment.iduser= ?";
			
			List<BigInteger> resultList = new ArrayList<BigInteger>();
			List<Offer> myOfferList = new ArrayList<Offer>();
			
			
			Query query = em.createNativeQuery(command).setParameter(1, myUser.getIdUser());
			resultList = query.getResultList();
			
			if(resultList.size() != 0)
			{
				for(BigInteger id : resultList)
				{
					myOfferList.add(em.find(Offer.class, id.longValue()));
				}
				
				return myOfferList;
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
	 * Gets the list of propositions of the user in parameter
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
        		if ( userTmp.getListProposition().size() != 0)
        		{
            		return userTmp.getListProposition();
        		}
        		else
        			return null;
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
			System.out.println("avant persist");

			em.persist(userToCreate);
			System.out.println("apres persist");

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
	
	
	/**
	 * Gets the user corresponding to the User id given in parameter
	 *
	 * @param idUser the id of the user
	 * @return the user
	 */
	public User getUserById(long idUser)
	{
		try
		{
			User userToFind = em.find(User.class,idUser);
			if(userToFind != null)
			{
				return userToFind;
			}
			else
				return null;
		}
		catch (PersistenceException e)
		{
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Gets the user corresponding to the User id given in parameter
	 *
	 * @param idUser the id of the user
	 * @return the user
	 */
	public User mergeUser(User userToMerge)
	{
		try
		{
			User mergedUser = em.merge(userToMerge);
			return mergedUser;
		}
		catch (PersistenceException e)
		{
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return null;
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