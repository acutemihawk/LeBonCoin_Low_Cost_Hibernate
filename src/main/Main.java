package main;

import javax.persistence.*;
import javax.persistence.Persistence;

import controller.MainController;
import view.*;

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
	}	
}