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
		
		Window myWindow = new Window();
		myWindow.mainMenu();
	
		/*MainController m = new MainController();
		
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

		m.userConnect("david", "test");*/

		
	}	
}