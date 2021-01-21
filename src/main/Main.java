package main;



import javax.persistence.Persistence;

import controller.MainController;
import view.*;




// has to be removed it is just for testing
import model.*;
import controller.*;
import java.util.List;

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

		Advertisment a = new Advertisment();
		Advertisment b = new Advertisment();

		Offer c = new Offer();
		Offer d = new Offer();
		User UserTest = new User();
		UserTest.setPassword("test");
		UserTest.setUsername("david");
		UserTest.setMail("test@gmail.com");
		
		
		a.setOwner(UserTest);
		b.setOwner(UserTest);
		
		UserTest.addAdvertisment(a);
		UserTest.addAdvertisment(b);
		
		c.setBuyer(UserTest);
		d.setBuyer(UserTest);
		
		UserTest.addProposition(d);
		UserTest.addProposition(c);
		
		c.setAdv(a);
		d.setAdv(a);
		a.addAdvertisment(c);
		a.addAdvertisment(d);
		
		UserDAO myDAO = new UserDAO();
		myDAO.insertUser(UserTest);
		OfferDAO offerDAO = new OfferDAO();
		System.out.println(c.getBuyer().getIdUser());
		
		//System.out.println(c.getIdOffer());
		//offerDAO.deleteAllOffer(c);
		
	/*	Offer Of = new Offer();
		Of.setBuyer(UserTest);
		UserTest.addProposition(Of);
		Of.getBuyer().setIdUser(myDAO.getUserId(UserTest));
		Of.setAdv(a);
		a.addAdvertisment(Of);
		Of.setNewPrice(500);

		offerDAO.insertOf(Of);
*/	
		//myDAO.getUserListAdv(UserTest);
		//myDAO.getUserListAdv(UserTest);

		//myDAO.getUserListOffer(UserTest);
		
		//System.out.println(myDAO.getUserListAdv(UserTest).get(0).getIdAdvertisment());
		//System.out.println(myDAO.getUserListAdv(UserTest).get(1).getIdAdvertisment());
		/*UserTest.setIdUser(myDAO.getUserId(UserTest));
		
		System.out.println(myDAO.getUserName(UserTest));*/
		
	}	
}