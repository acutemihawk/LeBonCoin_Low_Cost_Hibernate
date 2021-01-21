package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.*;
import model.*;

// TODO: Auto-generated Javadoc
/**
 * The Class View.
 */
public class View
{
	
	/** The main controller. */
	private MainController mainController;
	
	/** The my scanner. */
	private Scanner myScanner ;
	
	/** The given str. */
	private String given_Str;
	
	/** The option number. */
	private int option_number;
	
	/**
	 * Instantiates a new view.
	 */
	public View()
	{
		mainController = new MainController();
		myScanner = new Scanner(System.in);
		given_Str = "";
		option_number = 0;
		
	}
	
	/**
	 * Main menu.
	 */
	//fonction du démarrage de l'application
	public void mainMenu()
	{
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Choose one option from below and press Enter to navigate :");
		System.out.println("1 - Sign in");
		System.out.println("2 - Continue without signing in");
		System.out.println("3 - Create account");
		System.out.println("4 - Exit");
		System.out.println("----------------------------------------------------------------------");
		
		try
		{
			option_number = myScanner.nextInt();

			if(option_number == 1)
			{
				System.out.println("username:");
				given_Str = myScanner.next();
				String username = given_Str;
				
				System.out.println("password:");
				given_Str = myScanner.next();
				String password = given_Str;
				
				if(mainController.userConnect(username, password))
				{
					mainController.getMyUser().setConnected(true);
					connectedUser();
				}
				else
				{
					System.out.println("Error: Could not connect, please verify your username or password.");
					System.out.println("You can also create your account.");
					
					mainMenu();
				}
			}
			else if(option_number == 2)
			{
				System.out.println("Without signing in, you can only browse for advertisments:");
				browse();
			}
			else if(option_number == 3)
			{
				createAccount();
			}
			else if(option_number == 4)
			{
				System.exit(0);
			}
			else
			{
				System.out.println("You have chosen the ultimate choice, now go back to the main menu!");
				mainMenu();
			}
		}
		catch(NumberFormatException myException)
		{
			System.out.println("Please enter a number !");
		}
		catch(InputMismatchException myException) //à voir si on doit le garder
		{
			System.out.println("The argument you entered is invalid");
		}
	}
	
	/**
	 * Connected user.
	 */
	//fonction qui affiche le nouveau menu une fois que l'utilisateur s'est co
	public void connectedUser()
	{
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Choose one option from below and press Enter to navigate :");
		System.out.println("1 - Browse");
		System.out.println("2 - Consult my advertisments");
		System.out.println("3 - Consult my propositions");
		System.out.println("4 - Consult my received offers");
		System.out.println("5 - Return");
		System.out.println("----------------------------------------------------------------------");
		
		try
		{
			option_number = myScanner.nextInt();

			if(option_number == 1)
			{
				browse();
			}
			else if(option_number == 2)
			{
				displayUserAdvertisments();
			}
			else if(option_number == 3)
			{
				displayUserPropositions();
			}
			else if(option_number == 4)
			{
				displayUserReceivedOffer();
			}
			else if(option_number == 5)
			{
				mainMenu();
			}
			else
			{
				System.out.println("You have chosen the ultimate choice, now go back to the main menu!");
				mainMenu();
			}
		}
		catch(NumberFormatException myException)
		{
			System.out.println("Please enter a number !");
		}
		catch(InputMismatchException myException)
		{
			System.out.println("The argument you entered is invalid");
		}
	}
	
	/**
	 * Browse.
	 */
	//fonction parcourir
	public void browse()
	{
		ArrayList<String> categoriesArray = new ArrayList<String>();
		int numberToDisplay = 0;
		
		categoriesArray = mainController.getMyAdvDAO().getCategories();
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Choose one option from below and press Enter to navigate :");
		System.out.println("1 - Search with parameters");
		System.out.println("2 - Return");
		
		for (int categoryLoop=0 ; categoryLoop < categoriesArray.size(); categoryLoop++)
		{
			numberToDisplay = categoryLoop + 3;
			System.out.println(numberToDisplay + " - " + categoriesArray.get(categoryLoop));
		}
		System.out.println("----------------------------------------------------------------------");	
		
		try
		{
			option_number = myScanner.nextInt();
			
			if(option_number == 1)
			{
				search();
			}
			else if(option_number == 2)
			{
				if(mainController.getMyUser().isConnected())
					connectedUser();
				else
					mainMenu();
			}
			else if(option_number > 2 && option_number <= categoriesArray.size()+2)
			{
				displayCategory(categoriesArray.get(option_number-3));
			}
			else
			{
				System.out.println("You have chosen the ultimate choice, now go back to the main menu!");
				mainMenu();
			}
		}
		catch(NumberFormatException myException)
		{
			System.out.println("Please enter a number !");
		}
		catch(InputMismatchException myException)
		{
			System.out.println("The argument you entered is invalid");
		}
	}
	
	/**
	 * Creates the account.
	 */
	//fonction creation d'un compte
	public void createAccount()
	{
		String username;
		String password;
		String mail;
		
		System.out.println("username:");
		given_Str = myScanner.next();
		username = given_Str;
		
		System.out.println("password:");
		given_Str = myScanner.next();
		password = given_Str;

		System.out.println("email address:");
		given_Str = myScanner.next();
		mail = given_Str;
		
		mainController.createAccount(username, password, mail);
		
		if(mainController.userConnect(username, password))
		{
			mainController.getMyUser().setConnected(true);
			connectedUser();
		}
		else
		{
			System.out.println("Error: Could not connect, please verify your username or password.");
			mainMenu();
		}
	}
	
	/**
	 * Search.
	 */
	//fonction search avec paramètres
	public void search()
    {
		int numberToDisplay = 3;
        String category = "";
        String localisation = "";
        float minPrice = 0;
        float maxPrice = 0;
        
        ArrayList<Advertisment> advertismentList = new ArrayList<Advertisment>();
        
        try
        {
            System.out.println("Please, enter some information:");
            
            System.out.println("Category:");
            category = myScanner.next();
            
            System.out.println("Localisation:");
            localisation = myScanner.next();
            
            System.out.println("Minimum price:");
            minPrice = myScanner.nextFloat();

            System.out.println("Maximum price:");
            maxPrice = myScanner.nextFloat();
            
            advertismentList = mainController.getMyAdvDAO().search(category, minPrice, maxPrice, localisation);
            
            System.out.println("----------------------------------------------------------------------");
    		System.out.println("Choose one option from below and press Enter to navigate :");
    		System.out.println("1 - Make an offer");
    		System.out.println("2 - Return");
    		
            for( Advertisment advTmp : advertismentList)
            {
            	System.out.println(numberToDisplay+" - "+advTmp.getTitre()+" "+advTmp.getPrice()+" ("+advTmp.getIdAdvertisment()+") ");
            	numberToDisplay++;
            }
            System.out.println("----------------------------------------------------------------------");
            
            option_number = myScanner.nextInt();

			if(option_number == 1)
			{
				
				if(mainController.getMyUser().isConnected())
				{
					makeOffer();
				}
				else
				{
					System.out.println("Error: You need to be connected to make an offer.");
					mainMenu();
				}
			}
			else if(option_number == 2)
			{
				browse();
			}
			else if(option_number > 2 && option_number < advertismentList.size()+2)
			{
				displayAdvertisment(advertismentList.get(option_number-3));
			}
			else
			{
				System.out.println("You have chosen the ultimate choice, now go back to the main menu!");
				mainMenu();
			}
        }
		catch(NumberFormatException myException)
		{
			System.out.println("Please enter a number !");
		}
		catch(InputMismatchException myException)
		{
			System.out.println("The argument you entered is invalid");
		}
    }
	
	/**
	 * Display category.
	 *
	 * @param category_name the category name
	 */
	//fonction qui affiche les categories
	public void displayCategory(String category_name)
	{
		int numberToDisplay = 3;
        
        ArrayList<Advertisment> advertismentList = new ArrayList<Advertisment>();
        
        try
        {
            advertismentList = mainController.getMyAdvDAO().getAdvertismentsFromCategory(category_name);
            
            System.out.println("----------------------------------------------------------------------");
    		System.out.println("Choose one option from below and press Enter to navigate :");
    		System.out.println("1 - Make an offer");
    		System.out.println("2 - Return");
    		
            for( Advertisment advTmp : advertismentList)
            {
            	System.out.println(numberToDisplay+" - "+advTmp.getTitre()+" "+advTmp.getPrice()+" ("+advTmp.getIdAdvertisment()+")");
            	numberToDisplay++;
            }
            System.out.println("----------------------------------------------------------------------");
            
            option_number = myScanner.nextInt();

			if(option_number == 1)
			{
				
				if(mainController.getMyUser().isConnected())
				{
					makeOffer();
				}
				else
				{
					System.out.println("Error: You need to be connected to make an offer.");
					mainMenu();
				}
			}
			else if(option_number == 2)
			{
				browse();
			}
			else if(option_number > 2 && option_number < advertismentList.size()+2)
			{
				displayAdvertisment(advertismentList.get(option_number-3));
			}
			else
			{
				System.out.println("You have chosen the ultimate choice, now go back to the main menu!");
				mainMenu();
			}
        }
		catch(NumberFormatException myException)
		{
			System.out.println("Please enter a number !");
		}
		catch(InputMismatchException myException)
		{
			System.out.println("The argument you entered is invalid");
		}
	}
	
	/**
	 * Display advertisment.
	 *
	 * @param ad the ad
	 */
	//fonction qui affiche une annonce
	public void displayAdvertisment(Advertisment ad)
	{
		System.out.println("----------------------------------------------------------------------");
		System.out.println("1 - Return");
		System.out.println("Id : " + ad.getIdAdvertisment());
		System.out.println("Title : " + ad.getTitre());
		System.out.println("Price : " + ad.getPrice() + "$");
		System.out.println("Category : " + ad.getCategory());
		System.out.println("Description : " + ad.getDescription());
        System.out.println("----------------------------------------------------------------------");
        
        
        try
        {
        	option_number = myScanner.nextInt();
        	
			if(option_number == 1)
			{
				browse();
			}
			else
			{
				System.out.println("You have chosen the ultimate choice, now go back to the main menu!");
				mainMenu();
			}
        }
		catch(NumberFormatException myException)
		{
			System.out.println("Please enter a number !");
		}
		catch(InputMismatchException myException)
		{
			System.out.println("The argument you entered is invalid");
		}
	}
	
	/**
	 * Creates the advertisment.
	 */
	public void createAdvertisment()
	{
		try
		{
	        String localisation = "";
	        float price = 0;
	        String description="";
	        String category ="";
	        String titre ="";
	        
	        System.out.println("Please, enter some information about the advertisment:");
	        
	        System.out.println("Category : ");
	        category = myScanner.next();
	        // Consuming the leftover new line 
	        myScanner.nextLine();
	        
	        System.out.println("Localisation : ");
	        localisation = myScanner.next();
	        // Consuming the leftover new line 
	        myScanner.nextLine();
	        
	        System.out.println("Title : ");
	        titre = myScanner.nextLine();
	
	        System.out.println("Description : ");
	        description = myScanner.nextLine();
	        
	        System.out.println("Price :");
	        price = myScanner.nextFloat();
	        // Consuming the leftover new line 
	        myScanner.nextLine();
	        
	        mainController.addUserAdvertisment(titre, localisation, price, description, category);
	        
	        connectedUser();
		}
		catch(NumberFormatException myException)
		{
			System.out.println("Please enter a number !");
		}
		catch(InputMismatchException myException)
		{
			System.out.println(myException.getMessage());
		}
	}
	
	/**
	 * Display user propositions.
	 */
	public void displayUserPropositions()
	{
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Choose one option from below and press Enter to navigate");
		System.out.println("These are your currents propositions :");
		System.out.println("1 - Cancel a proposition");
		System.out.println("2 - Return");
		
		try
		{
	        ArrayList<Offer> myArrayList = new ArrayList<Offer>();
	        myArrayList = mainController.getUserPropositions();
	        
	        for ( Offer propositionTmp : myArrayList )
	        {
	        	System.out.println("- You made a proposition for the advertisment : "+propositionTmp.getIdAdvertisment()+" at the price of : "+propositionTmp.getNewPrice()+"$ ("+propositionTmp.getIdOffer()+")");;
	        }
	        System.out.println("----------------------------------------------------------------------");
		}
		catch( NullPointerException myException)
		{
			System.out.println("You need to register to use this functionnality");
		}
		
		try
		{
			option_number = myScanner.nextInt();
			
			if(option_number == 1)
			{
				System.out.println("Please enter the id (number on the right) of the proposition you want to delete :");
				long idOffer = myScanner.nextInt();
				if(mainController.delUserProposition(idOffer) == true)
				{
					System.out.println("Your proposition was successfully deleted.");
					displayUserPropositions();
				}
				else
				{
					System.out.println("Your proposition was not deleted, looser!");
					displayUserPropositions();
				}
			}
			else if(option_number == 2)
			{
				connectedUser();
			}
			else
			{
				System.out.println("You have chosen the ultimate choice, now go back to the main menu!");
				mainMenu();
			}
		}
		catch(InputMismatchException myException)
		{
			System.out.println("The argument you entered is invalid");
		}
	}
	
	/**
	 * Display user received offer.
	 */
	public void displayUserReceivedOffer()
	{
		try
		{
			System.out.println("----------------------------------------------------------------------");
			System.out.println("Choose one option from below and press Enter to navigate");
			System.out.println("These are your currents offer :");
			System.out.println("1 - Accept an offer");
			System.out.println("2 - Refuse an offer");
			System.out.println("3 - Return");

	        ArrayList<Offer> myArrayList = new ArrayList<Offer>();
	        myArrayList = mainController.getUserReceivedOffer();
	        
	        for (Offer receivedOffer : myArrayList)
	        {
	        	User buyer = new User();
	        	buyer.setIdUser(receivedOffer.getIdBuyer());
	        	System.out.println("- You received an offer from : "+mainController.getMyUserDAO().getUserName(buyer)+" on the advertisment : "+receivedOffer.getIdAdvertisment()+" at the price of : "+receivedOffer.getNewPrice()+"$"
	        			+ " ("+receivedOffer.getIdOffer()+") ");
	        }
	        
	        System.out.println("----------------------------------------------------------------------");

			option_number = myScanner.nextInt();
			
			if(option_number == 1)
			{
				System.out.println("Please enter the number in parentheses on the right of the offer you wish to accept ");
				long idOffer = myScanner.nextInt();
				if(mainController.acceptOffer(idOffer) == true)		
				{
					System.out.println("The offer was successfully accepted, as a consequence your annonce has been removed from the application as well as any current offer on it  ");
					displayUserReceivedOffer();
				}
				else
				{
					displayUserReceivedOffer();
				}
			}
			else if(option_number == 2)
			{
				System.out.println("Please enter the number in parentheses on the right of the offer you wish to refuse ");
				long idOffer = myScanner.nextInt();
				if(mainController.refuseOffer(idOffer) == true)
				{
					System.out.println("The offer was successfully refused");
					displayUserReceivedOffer();
				}	
				else
				{
					displayUserReceivedOffer();
				}
			}
			else
			{
				connectedUser();
			}
		}
		catch(InputMismatchException myException)
		{
			System.out.println("The argument you entered is invalid");
			
		}
		catch(Exception myException)
		{
			myException.getMessage();
		}
	}
	
	/**
	 * Make offer.
	 */
	//fonction creation d'une offre
	public void makeOffer()
	{
		try
        {
			System.out.println("Please enter the id (number on the right) of the proposition you want to purchase :");
            long idAdvertisment = myScanner.nextLong();
            
            System.out.println("Please enter the price of the item you wish purchase :");
            float newPrice = myScanner.nextFloat();
			
            if(mainController.addUserProposition(idAdvertisment, newPrice))
            	System.out.println("Your proposition has been successfully added!");
            else
            	System.out.println("An error has occured, please try again.");
            
            connectedUser();
            
        }
		catch(NumberFormatException myException)
		{
			System.out.println("Please enter a number !");
		}
		catch(InputMismatchException myException)
		{
			System.out.println("The argument you entered is invalid");
		}
	}
	
	/**
	 * Display user advertisments.
	 */
	public void displayUserAdvertisments()
	{
		int numberToDisplay = 3;
        
        ArrayList<Advertisment> advertismentList = new ArrayList<Advertisment>();
        
        try
        {
            advertismentList = mainController.getMyAdvDAO().getUserAdvertisments(mainController.getMyUser().getIdUser());
            
            System.out.println("----------------------------------------------------------------------");
    		System.out.println("Choose one option from below and press Enter to navigate :");
    		System.out.println("1 - Create an advertisment");
    		System.out.println("2 - Delete an advertisment");
    		System.out.println("3 - Return");
    		
            for( Advertisment advTmp : advertismentList)
            {
            	System.out.println(numberToDisplay+" - "+advTmp.getTitre()+" "+advTmp.getPrice()+" ("+advTmp.getIdAdvertisment()+")");
            	numberToDisplay++;
            }
            System.out.println("----------------------------------------------------------------------");
            
            option_number = myScanner.nextInt();
            
            if(option_number == 1)
			{
				createAdvertisment();
			}
            else if(option_number == 2)
			{
            	System.out.println("Please enter the id (number on the right) of the advertisment you want to delete :");
				long idAdvToDel = myScanner.nextInt();
				
				mainController.delUserAdvertisment(idAdvToDel);
				
				connectedUser();
			}
            else if(option_number == 3)
			{
				connectedUser();
			}
			else if(option_number > 3 && option_number < advertismentList.size()+3)
			{
				displayAdvertisment(advertismentList.get(option_number-4));
			}
			else
			{
				System.out.println("You have chosen the ultimate choice, now go back to the main menu!");
				mainMenu();
			}
        }
		catch(NumberFormatException myException)
		{
			System.out.println("Please enter a number !");
		}
		catch(InputMismatchException myException)
		{
			System.out.println("The argument you entered is invalid");
		}
	}
	
	
	
}