package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dao.EmployeeCrudDAO;
import com.app.dao.dbutil.PostgresqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Account;
import com.app.model.Car;
import com.app.model.Customer;
import com.app.model.Offer;
import com.app.model.Transaction;
import com.app.service.GeneralService;
import com.app.service.impl.GeneralServiceImpl;

public class EmployeeCrudDAOImpl implements EmployeeCrudDAO {

	GeneralService dao = new GeneralServiceImpl();

	@Override
	public void getCustomerByName(String customerName) throws BusinessException {

		try (Connection connection = PostgresqlConnection.getConnection()) {
			String sql = "select userid, firstname, lastname, contact, address from customer where lastname like ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + customerName + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setUserId(resultSet.getInt("userid"));
				customer.setFirstName(resultSet.getString("firstname"));
				customer.setLastName(resultSet.getString("lastname"));
				customer.setAddress(resultSet.getString("address"));
				customer.setContact(resultSet.getLong("contact"));
				System.out.println(customer.toString());
			}
			System.out.println("\nEnd Of List");
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Error Interacting with Database");
		}
	}// End of getCustomerByName()

	@Override
	public void showAllAccounts(int UserId) {
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "select * from accounts where userid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, UserId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Account account = new Account();
				account.setUserId(UserId);
				account.setCarId(resultSet.getInt("carid"));
				account.setBalance(resultSet.getDouble("balance"));
				account.setRemainingMonths(resultSet.getInt("remainingmonths"));
				account.setMonthlyPayment(resultSet.getDouble("monthlypayment"));
				System.out.println(account.toString());
			}
			return;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// End of showAllAccounts()

	@Override
	public void getAllTransactions(int carId) {
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "select * from transaction where carid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, carId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransid(resultSet.getInt("transid"));
				transaction.setOldBalance(resultSet.getDouble("oldbalance"));
				transaction.setPaymentAmount(resultSet.getDouble("paymentamount"));
				transaction.setNewBalance(resultSet.getDouble("newbalance"));
				transaction.setCarId(resultSet.getInt("carid"));
				transaction.setTimeStamp(resultSet.getString("timestamp"));
				System.out.println(transaction.toString());
			}
			System.out.println("\nEnd Of Transaction Records for Vehicle");
			return;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void addCarToLot(int carId, int year, String make, String model, String color, double stickerPrice,
			String status) {
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "insert into carlot(carid, year,make,model,color,stickerprice,status) values(?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, carId);
			preparedStatement.setInt(2, year);
			preparedStatement.setString(3, make);
			preparedStatement.setString(4, model);
			preparedStatement.setString(5, color);
			preparedStatement.setDouble(6, stickerPrice);
			preparedStatement.setString(7, status);
			preparedStatement.executeUpdate();
			System.out.println("***Vehicle Successfuly Added to Car Lot***\n");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void removeCarFromLot(int carId) {
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "delete from carlot where carid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, carId);
			preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void processPayment(int carId, double paymentAmount) throws BusinessException {
		double oldBalance;
		double newBalance;
		double monthlyPayment;
		int remainingMonths;
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "select * from accounts where carid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, carId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				monthlyPayment = resultSet.getDouble("monthlypayment");
				oldBalance = resultSet.getDouble("balance");
				remainingMonths = resultSet.getInt("remainingmonths");
				newBalance = oldBalance - paymentAmount;
			} else {
				System.out.println("No Account Found For Vehicle");
				return;
			}

			if (paymentAmount < monthlyPayment) {
				System.out.println("This Amount Doesnt Equal or Exceed Monthly Payment Amount, and");
				System.out.println("We Can not Accept Partial Payments.");
				return;
			} else {
				remainingMonths--;

				String sql1 = "update accounts set balance=?, remainingmonths=? where carid=?";
				PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
				preparedStatement1.setDouble(1, newBalance);
				preparedStatement1.setInt(2, remainingMonths);
				preparedStatement1.setInt(3, carId);
				preparedStatement1.executeUpdate();
			}

			dao.GenerateTransactionRecord(oldBalance, paymentAmount, newBalance, carId);
			System.out.println("Payment Made Successfully! Thanks For Your Business.");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void viewCarLot() {
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "select * from carlot";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
	public void viewPendingOffers() {
		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "select * from pendingoffers order by carid";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Offer offer = new Offer();
				offer.setOfferId(resultSet.getInt("offerid"));
				offer.setCarId(resultSet.getInt("carid"));
				offer.setUserId(resultSet.getInt("userid"));
				offer.setAskingPrice(resultSet.getDouble("askingprice"));
				offer.setOfferMade(resultSet.getDouble("offermade"));
				offer.setYears(resultSet.getInt("years"));
				offer.setMonthlyPayment(resultSet.getDouble("monthlypayment"));
				offer.setTotalInterest(resultSet.getDouble("totalinterest"));
				offer.setTotalProfit(resultSet.getDouble("totalprofit"));
				System.out.println(offer.toString());
			}
			System.out.println("***End Of Offers***");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void approveOffer(int offerId) {

		

		try {
			// Select offer details
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "select * from pendingoffers where offerid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, offerId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				
				int carId = resultSet.getInt("carid");
				int userId = resultSet.getInt("userid");
				int years = resultSet.getInt("years");
				int remainingMonths = years * 12;
				double monthlyPayment = resultSet.getDouble("monthlypayment");
				double balance = resultSet.getDouble("totalprofit");
			
			
			// Add to accounts table
			String sql1 = "insert into accounts(userid,carid,balance,remainingmonths,monthlypayment) values(?,?,?,?,?)";
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement1.setInt(1, userId);
			preparedStatement1.setInt(2, carId);
			preparedStatement1.setDouble(3, balance);
			preparedStatement1.setInt(4, remainingMonths);
			preparedStatement1.setDouble(5, monthlyPayment);
			preparedStatement1.executeUpdate();

			// alter carlot table
			String sql2 = "update carlot set owner=?, stickerprice=0, status=? where carid=?";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			preparedStatement2.setInt(1, userId);
			preparedStatement2.setString(2, "Sold");
			preparedStatement2.setInt(3, carId);
			preparedStatement2.executeUpdate();

			// remove all offers with carid
			String sql3 = "delete from pendingoffers where carid=?";
			PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
			preparedStatement3.setInt(1, carId);
			preparedStatement3.executeUpdate();

			System.out.println("Offer Accepted and Account Created for Customer.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void rejectOffer(int offerId) {

		try {
			Connection connection = PostgresqlConnection.getConnection();
			String sql = "delete from pendingoffers where offerid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, offerId);
			preparedStatement.executeUpdate();
			System.out.println("Offer Rejected");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}/* End Of Class */
