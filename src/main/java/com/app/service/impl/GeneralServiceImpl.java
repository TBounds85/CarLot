package com.app.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.app.dao.dbutil.PostgresqlConnection;
import com.app.exception.BusinessException;
import com.app.service.GeneralService;

public class GeneralServiceImpl implements GeneralService {

	// initiating objects needed globally
	SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMM-dd-yyyy z  HH:mm:ss");
	public Date d = new Date();
	Scanner sc = new Scanner(System.in);

	@Override
	public boolean CheckForCarIdInUse(int carId) {
		boolean checker = false;
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "select carid from carlot;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int DBCarId = resultSet.getInt("carid");
				checker = carId == DBCarId;
				if (checker == true) {
					return checker;
				}
			}
			return checker;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return checker;

	}

	@Override
	public boolean CheckUsernameAvailability(String username) throws BusinessException {

		
		try (Connection connection = PostgresqlConnection.getConnection()) {
			boolean b = false;
			String sql = "select username from loginverification";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String DBUsername = resultSet.getString("username").toLowerCase();
				
				b = username.toLowerCase().equals(DBUsername);
				if (b == true) {
					return b;
				}
			}
			return b;
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Error Connecting to Database.");
		}
	} // End of CheckUsernameAvailability

	@Override
	public void CreateNewUser(String firstName, String lastName, String address, long contact, String username,String password) throws BusinessException {
		try (Connection connection = PostgresqlConnection.getConnection()) {

			// generates next available userId available from database
			int userId = 100; // client IDs Start at 100;

			String sql = "select userid from customer";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				userId++; // ++1 for each customer found
			}
			String sql3 = "insert into loginverification(userid,username,password) values(?,?,?)";
			preparedStatement = connection.prepareStatement(sql3);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);
			preparedStatement.executeUpdate();
			
			String sql2 = "insert into customer(userid,firstname,lastname,address,contact) values(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, firstName);
			preparedStatement.setString(4, address);
			preparedStatement.setLong(5, contact);
			preparedStatement.executeUpdate();

			
		
		} catch (SQLException | ClassNotFoundException e) {
			throw new BusinessException("Error Connecting to Database. "+e);
		}
	}// End of CreateNewUser()

	public int getNextTransactionId() throws BusinessException {

		try (Connection connection = PostgresqlConnection.getConnection()) {
			int transid = 1000; // initial transid
			String sql = "select transid from transaction";
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement1.executeQuery();
			while (resultSet.next()) {
				transid++;// increments to next transId number
			}
			return transid;
		} catch (SQLException | ClassNotFoundException e) {
			throw new BusinessException("Error Connecting to Database thru General Service " + e);
		}
	}

	@Override
	public void GenerateTransactionRecord(double oldBalance, double paymentAmount, double newBalance, int carId)
			throws BusinessException {
		GeneralService dao = new GeneralServiceImpl();
		try (Connection connection = PostgresqlConnection.getConnection()) {

			// generates next transId
			int transId = dao.getNextTransactionId();

			// inserting transaction record
			String sql = "insert into transaction(transid,oldbalance,paymentamount,newbalance,carid,timestamp) values(?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, transId);
			preparedStatement.setDouble(2, oldBalance);
			preparedStatement.setDouble(3, paymentAmount);
			preparedStatement.setDouble(4, newBalance);
			preparedStatement.setInt(5, carId);
			preparedStatement.setString(6, sdf.format(d));
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Error Connecting to Database thru Services " + e);
		}
	}

	
}/******************** End of Class ****************/