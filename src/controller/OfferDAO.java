package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
	 * Instantiates a new user DAO.
	 */
	public OfferDAO()
	{
		emf  = Persistence.createEntityManagerFactory("Test");
        em = emf.createEntityManager() ;
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
	 * Delete of.
	 *
	 * @param Of the of
	 * @return true, if successful
	 */
	/* delete de la table offre l'offre passée en parametre  */
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
	 * Delete all offer.
	 *
	 * @param Of the of
	 * @return true, if successful
	 */
	/* supprimes toutes les propositions sur l'id de l'annonce passé en parametre */
	public boolean deleteAllOffer(Offer Of)
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