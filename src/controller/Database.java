package controller;
import java.sql.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Database.
 */
public class Database 
{
	
	/** The my con. */
	private Connection myCon;
	
	/**
	 * Instantiates a new database.
	 */
	public Database()
	{
		
	}
	
	/**
	 * Connect to the database.
	 *
	 * @return the connection object
	 */
	public Connection connect()
	{
		try 
		{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
		catch (ClassNotFoundException e) 
		{
            System.out.println(e);
            System.exit(1);
            return null;
		}
		try
		{
			myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/bddsgbd?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","root", "");
			//System.out.println("Succesful connection !");
			return myCon;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Disconnect from the database.
	 *
	 * @return true, if successful
	 */
	public boolean disconnect()
	{
		try
		{
			myCon.close();
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Authentificate the user by its username and password given in parameters.
	 *
	 * @param username the username of the user
	 * @param password the password of the user
	 * @return true, if successful
	 */
	public boolean authentificate(String username, String password)
	{
		if(myCon == null)
		{
			System.out.println("Error, could not reach to database");	
			System.exit(-1);
		}
		try
		{	
			String SQL = " SELECT username,password FROM USER WHERE username = ? AND password = ?";
			
			PreparedStatement myStatement = myCon.prepareStatement(SQL);
			myStatement.setString(1, username );
			myStatement.setString(2, password );
			ResultSet myResult = myStatement.executeQuery();
			
			if(myResult.next() == false)
			{
				System.out.println("Username or/and password incorrect, please check if you have an existing account !");
				return false;
			}
			else
			{
				System.out.println("Logged in !");
				return true;
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	
	}

}