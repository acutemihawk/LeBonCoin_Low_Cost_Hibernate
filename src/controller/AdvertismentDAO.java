package controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


import model.Advertisment;
import model.Offer;
import model.User;


// TODO: Auto-generated Javadoc
/**
 * The Class AdvertismentDAO.
 */
public class AdvertismentDAO
{
	
	private EntityManager em;
	private EntityManagerFactory emf;
	
	/**
	 * Instantiates a new Advertisment DAO.
	 */
	public AdvertismentDAO()
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
	
	/**
	 * Insert an advertisment into the database.
	 *
	 * @param ad the advertisment
	 * @return true, if successful
	 */
	public boolean insertAd(Advertisment ad)
	{
		try
		{
			em.getTransaction().begin();
			Advertisment mergedAdv = em.merge(ad);
			em.persist(mergedAdv);
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
	 * Delete an advertisment in the database.
	 *
	 * @param ad the advertisment
	 * @return true, if successful
	 */
	public boolean deleteAd(long id)
	{
		try
		{
			/*
			em.getTransaction().begin();
			
			Advertisment advDeleted = em.find(Advertisment.class, id);
			Offer of = em.find(Offer.class, advDeleted.getListMyOffer().get(0).getIdOffer());
			advDeleted.removeOffer(of);
		
			Advertisment mergedAdv = em.merge(advDeleted);
			
			em.remove(mergedAdv);
			
			em.getTransaction().commit();*/
		
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
	 * Get an advertisment from the database.
	 *
	 * @param id the id of the Advertisment to get
	 * @return the advertisment
	 */
	public Advertisment getAdvertismentById(long idAdv)
	{
		try
		{
			Advertisment advToFind = em.find(Advertisment.class,idAdv);
			if(advToFind != null)
				return advToFind;
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
	 * Search for the advertisments that correspond to the criterias given in parameters.
	 *
	 * @param category the category
	 * @param minPrice the minimum price
	 * @param maxPrice the maximum price
	 * @param localisation the localisation
	 * @return the array list of advertisment
	 */
	public List<Advertisment> search(String category, float minPrice, float maxPrice,String localisation)
	{
		try
		{
			String Command = "SELECT idAdvertisment FROM Advertisment WHERE "
				+ "LOWER(category) LIKE '" + "%"+category.toLowerCase()+"%" + "' AND "
				+ "price > " + minPrice + " and price < " + maxPrice + " AND "
				+ "LOWER(localisation) LIKE '" + "%"+localisation.toLowerCase()+"%'";
			
			List<BigInteger> myList = new ArrayList<BigInteger>();
			List<Advertisment> myAdvList = new ArrayList<Advertisment>();
			
			Query query = em.createNativeQuery(Command);
			myList = query.getResultList();
			
			if(myList.size() != 0)
			{
				for(BigInteger id : myList)
				{
					myAdvList.add(em.find(Advertisment.class, id.longValue()));
				}
				
				return myAdvList;
			}
			
			return null;
		}
		catch (PersistenceException e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Gets the list of category's names.
	 *
	 * @return the list of names
	 */
	public List<String> getCategories()
	{
		List<String> myList = new ArrayList<String>();
		
		try
		{
			String sqlCommand = "SELECT DISTINCT category FROM Advertisment";
			Query query = em.createQuery(sqlCommand);
			myList = query.getResultList();
			
			if(myList.size() != 0)
        	{
        		return myList;
        	}
			
			System.out.println("could not get the list of categories");
			return null;
		}
		catch (PersistenceException e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	/**
	 * Gets the list of advertisments in a given category.
	 *
	 * @param category the name of the category
	 * @return the list of advertisments
	 */
	public List<Advertisment> getAdvertismentsFromCategory(String category)
	{
		try
		{
			String command = "SELECT idAdvertisment FROM Advertisment WHERE LOWER(category) LIKE ?";
			
			List<Long> myList = new ArrayList<Long>();
			List<Advertisment> myAdvList = new ArrayList<Advertisment>();
			
			Query query = em.createQuery(command).setParameter(0, category);
			myList = query.getResultList();
			
			if(myList.size() != 0)
			{
				for(long id : myList)
				{
					myAdvList.add(em.find(Advertisment.class, id));
				}
				
				return myAdvList;
			}
			
			return null;
		}
		catch (PersistenceException e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	/**
	 * Gets an user's advertisments.
	 *
	 * @param idUser the user id
	 * @return the user advertisments
	 */
	public List<Advertisment> getUserAdvertisments(long idUser)
	{
		try
		{
			String command = "SELECT idAdvertisment FROM Advertisment WHERE iduser=?";
			
			List<Long> myList = new ArrayList<Long>();
			List<Advertisment> myAdvList = new ArrayList<Advertisment>();
			
			Query query = em.createQuery(command).setParameter(0, idUser);
			myList = query.getResultList();
			
			if(myList.size() != 0)
			{
				for(long id : myList)
				{
					myAdvList.add(em.find(Advertisment.class, id));
				}
				
				return myAdvList;
			}
			
			return null;
		}
		catch (PersistenceException e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
}