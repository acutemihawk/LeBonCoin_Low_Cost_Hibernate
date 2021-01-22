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
	
		MainController m = new MainController();
		
		m.createAccount("david", "test", "test@gmail.com");
		System.out.println(m.userConnect("david", "test"));
		

		System.out.println(m.addUserAdvertisment("blab", "blab", 50,"blab", "blab"));
		m.disconnect();
		
		m.createAccount("kaaris", "test", "mahoufal@gmail.com");
		System.out.println(m.userConnect("kaaris", "test"));
		m.addUserAdvertisment("blab", "blab", 50,"blab", "blab");
		m.disconnect();

		m.userConnect("david", "test");
		System.out.println(m.getMyUser().getIdUser());
		System.out.println(m.getMyUser().getMail());
		System.out.println(m.getMyUser().getPassword());
		System.out.println(m.getMyUser().getListAdvertisment());
		System.out.println(m.getMyUser().getListProposition());
		m.disconnect();

		m.createAccount("mahou", "azerty", "baloo@gmail.com");
		System.out.println(m.userConnect("mahou", "azerty"));
		m.addUserAdvertisment("blab", "blab", 50,"blab", "blab");
		m.addUserAdvertisment("blab", "blab", 50,"blab", "blab");
		m.addUserAdvertisment("blab", "blab", 50,"blab", "blab");
		m.addUserProposition(7, 50);
		m.addUserProposition(8, 700);
		m.disconnect();
		
		m.createAccount("lolola", "azerty", "pol@gmail.com");
		System.out.println(m.userConnect("lolola", "azerty"));
		m.addUserProposition(2, 50);
		m.addUserProposition(4, 700);
		m.addUserProposition(6, 900);

		m.disconnect();

		m.userConnect("david", "test");
		m.delUserAdvertisment(2);

	//	m.delUserAdvertisment(2);
		
		
		//System.out.println("HIHIHI");

		//System.out.println(m.getMyUser().getListAdvertisment().get(0).getIdAdvertisment());

		/*
		System.out.println(m.delUserAdvertisment(2));
		System.out.println(m.getMyUser().getListAdvertisment());

		m.addUserProposition(2, 50);
		m.addUserProposition(2, 700);
		m.addUserProposition(2, 900);
		//System.out.println(m.getMyUser().getListProposition());
		//System.out.println(m.getUserPropositions().get(0).getIdOffer());
		m.disconnect();

		m.userConnect("david", "test");
		System.out.println(m.getMyUser().getIdUser());
		System.out.println(m.getMyUser().getMail());
		System.out.println(m.getMyUser().getPassword());
		System.out.println(m.getMyUser().getListAdvertisment());
		System.out.println(m.getMyUser().getListProposition());
		
		System.out.println(m.addUserAdvertisment("blab", "blab", 50,"blab", "blab"));
		System.out.println(m.getMyUser().getListAdvertisment());*/


		
		
		
		//System.out.println(m.getUserReceivedOffer());
		//System.out.println(m.getUserReceivedOffer().get(0).getIdOffer());
		//System.out.println(m.delUserProposition(4));
		
		//System.out.println(m.acceptOffer(4));
		
		//System.out.println(m.refuseOffer(4));
		
	/*	Advertisment a = new Advertisment();
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
		a.addAdvertisment(d);*/
		
		
		//m.getMyUserDAO().insertUser(UserTest);
		//System.out.println(m.userConnect("test", "david"));
		
		//OfferDAO offerDAO = new OfferDAO();
		//System.out.println(c.getBuyer().getIdUser());
		//myDAO.getUserListOffer(UserTest);
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