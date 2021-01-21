package controller;

import java.sql.*;
import java.util.ArrayList;

import model.Advertisment;

// TODO: Auto-generated Javadoc
/**
 * The Class AdvertismentDAO.
 */
public class AdvertismentDAO
{
	
	/**
	 * Insert an advertisment into the database.
	 *
	 * @param ad the advertisment
	 * @return true, if successful
	 */
	public boolean insertAd(Advertisment ad)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "INSERT INTO advertisment (titre, localisation, price,description,category,iduser) VALUES (?,?,?,?,?,?)";
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			myStatement.setString(1, ad.getTitre());
			myStatement.setString(2, ad.getLocalisation());
			myStatement.setFloat(3, ad.getPrice());
			myStatement.setString(4, ad.getDescription());
			myStatement.setString(5, ad.getCategory());
			//myStatement.setLong(6, ad.getIdOwner());
			myStatement.executeUpdate();
			
			myStatement.close();
			myDB.disconnect();
			
			return true;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return false;
		}
	}
	
	/**
	 * Delete an advertisment in the database.
	 *
	 * @param ad the advertisment
	 * @return true, if successful
	 */
	public boolean deleteAd(Advertisment ad)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "DELETE FROM advertisment WHERE IdAdvertisment=?";
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			myStatement.setLong(1, ad.getIdAdvertisment());
			myStatement.executeUpdate();
			
			myStatement.close();
			myDB.disconnect();	
			
			return true;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return false;
		}
	}
	
	/**
	 * Gets the ID of the last advertisment created
	 *
	 * @return the last ID
	 */
	public long getLastID()
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		long id = 0;
		
		try
		{
			String sqlCommand = " SELECT idAdvertisment FROM advertisment ORDER BY idAdvertisment DESC ";
			Statement myStatement = myConnection.createStatement();
			
			ResultSet rs  = myStatement.executeQuery(sqlCommand);
			
			if(rs.next())
			{
				id = rs.getInt(1);
			}
			
			myStatement.close();
			myDB.disconnect();
			return id;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return 0;
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
	public ArrayList<Advertisment> search(String category, float minPrice, float maxPrice,String localisation)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		ArrayList<Advertisment> myArrayList = new ArrayList<Advertisment>();
		
		try
		{
			//l'id, le titre, le prix, et le titre
			String sqlCommand = "SELECT idAdvertisment,localisation,price,category,titre FROM advertisment WHERE LOWER(category) LIKE ? AND price > ? and price < ? AND LOWER(localisation) LIKE ? " ;
			
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			myStatement.setString(4, "%"+localisation.toLowerCase()+"%");
			myStatement.setString(1, "%"+category.toLowerCase()+"%");
			myStatement.setFloat(2, minPrice);
			myStatement.setFloat(3, maxPrice);
			ResultSet myResultSet = myStatement.executeQuery();
			
			while(myResultSet.next())
			{
				Advertisment myAdvToReturn = new Advertisment();
				myAdvToReturn.setIdAdvertisment(myResultSet.getLong(1));
				myAdvToReturn.setLocalisation(myResultSet.getString(2));	
				myAdvToReturn.setPrice(myResultSet.getFloat(3));
				myAdvToReturn.setCategory(myResultSet.getString(4));
				myAdvToReturn.setTitre(myResultSet.getString(5));
				myArrayList.add(myAdvToReturn);
			}
			
			myStatement.close();
			myDB.disconnect();

			return myArrayList;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return null;
		}
	}
	
	/**
	 * Gets the list of category's names.
	 *
	 * @return the list of names
	 */
	public ArrayList<String> getCategories()
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		ArrayList<String> arrayToReturn = new ArrayList<String>();
		
		try
		{
			String sqlCommand = "SELECT DISTINCT category FROM advertisment";
			Statement myStatement = myConnection.createStatement();
			ResultSet rs  = myStatement.executeQuery(sqlCommand);
			
			while(rs.next())
			{
				arrayToReturn.add(rs.getString(1));
			}				
			
			myStatement.close();
			myDB.disconnect();
			
			return arrayToReturn;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return null;
		}		
	}
	
	/**
	 * Gets the list of advertisments in a given category.
	 *
	 * @param category the name of the category
	 * @return the list of advertisments
	 */
	public ArrayList<Advertisment> getAdvertismentsFromCategory(String category)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		ArrayList<Advertisment> myArrayList = new ArrayList<Advertisment>();
		
		try
		{
			String sqlCommand = "SELECT idAdvertisment,localisation,price,category,titre FROM advertisment WHERE LOWER(category) LIKE ?" ;
			
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			myStatement.setString(1, "%"+category.toLowerCase()+"%");
			ResultSet myResultSet = myStatement.executeQuery();
			
			while(myResultSet.next())
			{
				Advertisment myAdvToReturn = new Advertisment();
				myAdvToReturn.setIdAdvertisment(myResultSet.getLong(1));
				myAdvToReturn.setLocalisation(myResultSet.getString(2));	
				myAdvToReturn.setPrice(myResultSet.getFloat(3));
				myAdvToReturn.setCategory(myResultSet.getString(4));
				myAdvToReturn.setTitre(myResultSet.getString(5));
				myArrayList.add(myAdvToReturn);
			}
			
			myStatement.close();
			myDB.disconnect();

			return myArrayList;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return null;
		}
	}
	
	/**
	 * Gets an user's advertisments.
	 *
	 * @param idUser the user id
	 * @return the user advertisments
	 */
	public ArrayList<Advertisment> getUserAdvertisments(long idUser)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		ArrayList<Advertisment> myArrayList = new ArrayList<Advertisment>();
		
		try
		{
			String sqlCommand = "SELECT idAdvertisment,localisation,price,category,titre FROM advertisment WHERE iduser=?";
			
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			myStatement.setLong(1, idUser);
			ResultSet myResultSet = myStatement.executeQuery();
			
			while(myResultSet.next())
			{
				Advertisment myAdvToReturn = new Advertisment();
				myAdvToReturn.setIdAdvertisment(myResultSet.getLong(1));
				myAdvToReturn.setLocalisation(myResultSet.getString(2));	
				myAdvToReturn.setPrice(myResultSet.getFloat(3));
				myAdvToReturn.setCategory(myResultSet.getString(4));
				myAdvToReturn.setTitre(myResultSet.getString(5));
				myArrayList.add(myAdvToReturn);
			}
			
			myStatement.close();
			myDB.disconnect();

			return myArrayList;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return null;
		}
	}
	
	/**
	 * Verify that the given advertisment exists in the database.
	 *
	 * @param ad the advertisment to check
	 * @return true, if true
	 */
	public boolean verify(Advertisment ad) 
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = " SELECT COUNT(*) FROM advertisment where idAdvertisment=?";
			
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand);
			myStatement.setLong(1, ad.getIdAdvertisment());
			ResultSet myResult  = myStatement.executeQuery();
			
			if(myResult.next() != false)
			{
				if(myResult.getInt(1) == 1)
				{	
					myDB.disconnect();
					myStatement.close();
					return true;
				}
				else
				{
					System.out.println("The advertisment does not exist");
					myDB.disconnect();
					myStatement.close();
					return false;	
				}
			}
			return false;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return false;
		}	
	}
}