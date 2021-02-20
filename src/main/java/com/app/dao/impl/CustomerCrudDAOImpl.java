package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dao.CustomerCrudDAO;
import com.app.dao.dbutil.PostgresqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Account;
import com.app.model.Car;

public class CustomerCrudDAOImpl implements CustomerCrudDAO {

	@Override
	public void changePassword(int UserId, String newPassword) throws BusinessException {

		try (Connection connection = PostgresqlConnection.getConnection()) {

			String sql = "update loginverification set password=? where userid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newPassword);
			preparedStatement.setInt(2, UserId);
			preparedStatement.executeUpdate();
			System.out.println("Password successfully Changed");

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Error Connecting to Database ");
		}
		return;
	}

	@Override
	public void viewCarLot() {
		// pull from database and assign to list of objects
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "select * from carlot where status = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "For Sale");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Car car = new Car();
				car.setCarId(resultSet.getInt("carid"));
				car.setColor(resultSet.getString("color"));
				car.setMake(resultSet.getString("make"));
				car.setModel(resultSet.getString("model"));
				car.setYear(resultSet.getInt("year"));
				car.setStickerPrice(resultSet.getDouble("stickerprice"));
				car.setStatus(resultSet.getString("status"));
				System.out.println(car.toString());
			}
			System.out.println("End Of List\n");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}

	@Override
	public void makeOffer(int carId, int UserId, double offerMade, double apr, int years) {
		
		try {Connection connection = PostgresqlConnection.getConnection();
			double askingPrice;
			double totalProfit;
			double totalInterest;
							
			//get stickerprice 
			String sql = "select stickerprice from carlot where carid=? and status=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, carId);
			preparedStatement.setString(2, "For Sale");
			ResultSet resultSet1 = preparedStatement.executeQuery();
			if (resultSet1.next()) {
				askingPrice = resultSet1.getDouble("stickerprice");
			} else {
				System.out.println("No Vehicle Found With That ID #");
				return;
			}
			totalInterest = (offerMade * apr) * years;
			totalProfit = (offerMade - askingPrice) + totalInterest;
			double monthlyPayment = (offerMade + totalInterest) / (years * 12);

			// wont allow for 0 or less in profit prints message informing customer
			if (totalProfit <= 0) {
				System.out.println("We Apologize But We Can Not Accept this Offer");
				System.out.println("Because our Company Will take a loss.\n");
				return;
			}
			String sql1 = "insert into pendingoffers(carid,userid,askingprice,offermade,apr,years,totalprofit,totalinterest,monthlypayment) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement1.setInt(1, carId);
			preparedStatement1.setInt(2, UserId);
			preparedStatement1.setDouble(3, askingPrice);
			preparedStatement1.setDouble(4, offerMade);
			preparedStatement1.setDouble(5, apr);
			preparedStatement1.setInt(6, years);
			preparedStatement1.setDouble(7, totalProfit);
			preparedStatement1.setDouble(8, totalInterest);
			preparedStatement1.setDouble(9, monthlyPayment);
			preparedStatement1.executeUpdate();
			System.out.println("\nOffer Submitted Successfully!\n*Making offer does NOT save the Vehicle for you*\n");

			String sql2 = "Select monthlypayment from pendingoffers where offermade=? and userid=?";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			preparedStatement2.setDouble(1, offerMade);
			preparedStatement2.setInt(2, UserId);

			ResultSet resultSet2 = preparedStatement2.executeQuery();
			if (resultSet2.next()) {
				monthlyPayment = resultSet2.getDouble("monthlypayment");
			}
			System.out.println("If Accepted Your Monthly Payments will be: $" + monthlyPayment);
			
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void viewYourVehicles(int UserId) {
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "select * from carlot where owner=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, UserId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Car car = new Car();
				car.setCarId(resultSet.getInt("carid"));
				car.setColor(resultSet.getString("color"));
				car.setMake(resultSet.getString("make"));
				car.setModel(resultSet.getString("model"));
				car.setYear(resultSet.getInt("year"));
				car.setStickerPrice(resultSet.getDouble("stickerprice"));
				car.setStatus(resultSet.getString("status"));
				System.out.println(car.toString());
			}
			System.out.println("\nEnd Of Your Vehicles.\n");
			return;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void viewRemainingBalance(int UserId, int carId) {
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "select * from accounts where carid=? and userid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, carId);
			preparedStatement.setInt(2, UserId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Account account = new Account();
				account.setUserId(UserId);
				account.setCarId(resultSet.getInt("carid"));
				account.setBalance(resultSet.getDouble("balance"));
				account.setRemainingMonths(resultSet.getInt("remainingmonths"));
				account.setMonthlyPayment(resultSet.getDouble("monthlypayment"));
				System.out.println(account.toString());

			} else {
				System.out.println("No vehicle You Own was Found with that ID #");
			}
			return;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean viewCar(int carId) {
		// display vehicle by pulling carId
		boolean checker = false;
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "select * from carlot where carid = ? and status=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, carId);
			preparedStatement.setString(2, "For Sale");
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Car car = new Car();
				car.setCarId(resultSet.getInt("carid"));
				car.setColor(resultSet.getString("color"));
				car.setMake(resultSet.getString("make"));
				car.setModel(resultSet.getString("model"));
				car.setYear(resultSet.getInt("year"));
				car.setStickerPrice(resultSet.getDouble("stickerprice"));
				car.setStatus(resultSet.getString("status"));
				System.out.println(car.toString());
				checker = true;
				return checker;
			} else {
				System.out.println("No Vehicle Found With that ID #");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checker;
	}

}
