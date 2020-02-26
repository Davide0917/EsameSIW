package searchouse.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import searchouse.bean.AccountBean;
import searchouse.bean.AdsBean;
import searchouse.bean.Bean;
import searchouse.utils.AppUtil;

public class AccountDAO implements DAO {

	@Override
	public void Insert(Bean newBean) throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt = null;
		Connection conn = null;

		try {
			conn = AppUtil.getDBConnection();

			AccountBean account = (AccountBean) newBean;
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String insertAccount = "INSERT INTO account (name,surname,username,password,phone,email,date_ins)";
			insertAccount += " VALUES ('" + account.getName() + "','" + account.getSurname() + "','"
					+ account.getUsername() + "','" + account.getPassword() + "','" + account.getPhone() + "','"
					+ account.getEmail() + "'," + " SYSDATE())";
			System.out.println("INSERT QUERY:" + insertAccount);
			stmt.executeUpdate(insertAccount);
			conn.commit();
		} catch (SQLException sqle) {
			if (conn != null)
				conn.rollback();
			System.out.println("INSERT ERROR: Transaction is being rolled back");
			throw new SQLException(sqle.getErrorCode() + ":" + sqle.getMessage());
		} catch (Exception err) {
			if (conn != null)
				conn.rollback();
			System.out.println("GENERIC ERROR: Transaction is being rolled back");
			throw new SQLException(err.getMessage());
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}

	}

	@Override
	public void Delete(Bean beanToRemove) throws SQLException {
		Statement stmt = null;
		Connection conn = null;

		try {
			AdsDAO dao = new AdsDAO();
			conn = AppUtil.getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			AccountBean account = (AccountBean) beanToRemove;
			ArrayList<AdsBean> ads = dao.getAllFromUser(account.getUsername());
			for(AdsBean ad:ads) {
				dao.Delete(ad);
			}
			String delete = "DELETE FROM account WHERE username='" +account.getUsername()+ "'";
			stmt.executeUpdate(delete);
			conn.commit();
			
		}catch (SQLException e) {
			if (conn != null)
				conn.rollback();
			System.out.println("ERROR: Transaction is being rolled back");
			
		}catch (Exception err) {
			if (conn != null)
				conn.rollback();
			System.out.println("GENERIC ERROR: Transaction is being rolled back");
			throw new SQLException(err.getMessage());
		}finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}

	}

	@Override
	public boolean Update(Bean beanToUpdate) throws SQLException {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = AppUtil.getDBConnection();
			AccountBean account = (AccountBean) beanToUpdate;
			stmt = conn.createStatement();
			String updateAccount = "UPDATE account SET " + " email = '" + account.getEmail() + "', phone ='"
					+ account.getPhone() + "', password ='" + account.getPassword() + "' WHERE username = '"
					+ account.getUsername() + "'";
			stmt.executeUpdate(updateAccount);
			return true;
		} catch (SQLException sqle) {
			throw new SQLException(sqle.getErrorCode() + ":" + sqle.getMessage());
		} catch (Exception err) {
			throw new SQLException(err.getMessage());
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}

	@Override
	public Bean GetOne(Bean beanToGet) throws SQLException {
		Statement stmt = null;
		Connection conn = null;

		try {
			conn = AppUtil.getDBConnection();
			AccountBean accountToGet = (AccountBean) beanToGet;
			String user = accountToGet.getUsername();
			stmt = conn.createStatement();
			String searchAccount = " SELECT * from account WHERE username = '" + user + "'";
			System.out.println("QUERY ACCOUNT: " + searchAccount);
			ResultSet accountList = stmt.executeQuery(searchAccount);
			if (!accountList.next())
				return null;
			String name = accountList.getString("name");
			String surname = accountList.getString("surname");
			String username = accountList.getString("username");
			String password = accountList.getString("password");
			String email = accountList.getString("email");
			String phone = accountList.getString("phone");

			AccountBean account = new AccountBean();
			account.setName(name);
			account.setSurname(surname);
			account.setUsername(username);
			account.setPassword(password);
			account.setPhone(phone);
			account.setEmail(email);

			return account;
		} catch (SQLException sqle) {
			throw new SQLException(sqle.getErrorCode() + ":" + sqle.getMessage());
		} catch (Exception err) {
			throw new SQLException(err.getMessage());
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}

	// Metodo che verifica se l'username � gi� in uso
	public boolean isPresent(String username) throws SQLException {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = AppUtil.getDBConnection();
			stmt = conn.createStatement();
			String searchAccount = " SELECT * from account WHERE username = '" + username + "'";
			System.out.println("QUERY ACCOUNT: " + searchAccount);
			ResultSet accountList = stmt.executeQuery(searchAccount);
			while (accountList.next()) {
				return true;
			}
			return false;
		} catch (SQLException sqle) {
			throw new SQLException(sqle.getErrorCode() + ":" + sqle.getMessage());
		} catch (Exception err) {
			throw new SQLException(err.getMessage());
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}

	// Metodo che verifica se i dati di login sono corretti
	public boolean isCorrect(String username, String password) throws SQLException {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = AppUtil.getDBConnection();
			stmt = conn.createStatement();
			String searchAccount = " SELECT * from account WHERE (username = '" + username + "') AND (password = '"
					+ password + "')";
			System.out.println("QUERY ACCOUNT: " + searchAccount);
			ResultSet accountList = stmt.executeQuery(searchAccount);
			boolean logged = (boolean) accountList.next();
			return logged;
		} catch (SQLException sqle) {
			throw new SQLException(sqle.getErrorCode() + ":" + sqle.getMessage());
		} catch (Exception err) {
			throw new SQLException(err.getMessage());
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}

	}
}
