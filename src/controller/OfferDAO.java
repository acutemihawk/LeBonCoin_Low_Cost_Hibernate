package controller;


import javax.persistence.*;

import model.Offer;

// TODO: Auto-generated Javadoc
/**
 * The Class OfferDAO.
 */
public class OfferDAO
{
	
	private EntityManager em;
	private EntityManagerFactory emf;
	
	/**
	 * Instantiates a new Offer DAO.
	 */
	public OfferDAO()
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
	 * Insert an offer .
	 *
	 * @param Of the of
	 * @return true, if successful
	 */
	/* insere dans la table offer l'offre passée en parametre */
	public boolean insertOf(Offer Of)
	{
		try
		{
			em.getTransaction().begin();
			em.persist(Of);
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
	 * Delete offer.
	 *
	 * @param Of the offer
	 * @return true, if successful
	 */
	public boolean deleteOf(Offer Of)
	{
        try
        {
            em.getTransaction().begin();
            Offer offerToRemove = (Offer) em.find(Offer.class, Of.getIdOffer());
            em.remove(offerToRemove);
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
	 * Delete all offers from the advertisment of the offer given in parameter.
	 *
	 * @param Of the of
	 * @return true, if successful
	 */
	public boolean deleteAllProposition(Offer Of)
	{
		try
		{
			em.getTransaction().begin();
            String hql = " DELETE from Offer o WHERE o.Adv.idAdvertisment = ?1";
            Query query = em.createQuery(hql);
            query.setParameter(1, Of.getAdv().getIdAdvertisment());
            query.executeUpdate();
			em.getTransaction().commit();
			em.close();
	
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
	 * Get an offer by its id from the database
	 *
	 * @param idOffer the id of the offer
	 * @return the offer
	 */
	public Offer getOfferById(long idOffer)
	{
		try
		{
			Offer offerToFind = em.find(Offer.class,idOffer);
			
			if(offerToFind != null)
				return offerToFind;
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