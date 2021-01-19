package main;

import javax.persistence.*;
import javax.persistence.Persistence;


import controller.MainController;
import view.*;




// has to be removed it is just for testing
import model.*;
import controller.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main 
{

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{
		/*
		Window myWindow = new Window();
		myWindow.mainMenu();*/

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Test");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		
		/*Advertisment a = new Advertisment();
		Advertisment b = new Advertisment();
		User UserTest = new User();
		UserTest.setPassword("test");
		UserTest.setUsername("david");
		UserTest.setMail("test@gmail.com");
		
		
		a.setOwner(UserTest);
		b.setOwner(UserTest);
		UserTest.addAdvertisment(a);
		UserTest.addAdvertisment(b);

		//UserTest.addAdvertisment();
		
		UserDAO myDAO = new UserDAO();*/
		
		//myDAO.insertUser(UserTest);
		//myDAO.getUserListOffer(UserTest);
		
		//System.out.println(myDAO.getUserListAdv(UserTest).get(0).getIdAdvertisment());
		//System.out.println(myDAO.getUserListAdv(UserTest).get(1).getIdAdvertisment());
		/*UserTest.setIdUser(myDAO.getUserId(UserTest));
		
		System.out.println(myDAO.getUserName(UserTest));*/
	}	
}