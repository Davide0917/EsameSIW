package searchouse.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import searchouse.bean.AccountBean;
import searchouse.bean.AdsBean;
import searchouse.utils.AppUtil;

// 	QUESTA CLASSE ORMAI NON VIENE PIU UTILIZZATA 
public class SaveMySQL {

	public void insertAccount(AccountBean account) throws SQLException {
		Statement stmt = null;
		Connection conn = null;

		try {
			conn = AppUtil.getDBConnection();
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

	public AccountBean getAccount(String user) throws SQLException {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = AppUtil.getDBConnection();
			stmt = conn.createStatement();
			String searchAccount = " SELECT * from account WHERE username = '" + user + "'";
			System.out.println("QUERY ACCOUNT: " + searchAccount);
			ResultSet accountList = stmt.executeQuery(searchAccount);
			accountList.next();
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

	public boolean updateAccount(AccountBean account) throws SQLException {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = AppUtil.getDBConnection();
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

	// Modificare adattondolo per l'inserimento per le immagini
	public void insertAd(AdsBean ad) throws SQLException {
		Statement stmt = null;
		Connection conn = null;

		try {
			conn = AppUtil.getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			String insertAd = "INSERT INTO ads (id,id_account,title,address,provincia,description,dimension,type,price,date_ins)";
			insertAd += " VALUES ('" + ad.getId() + "','" + ad.getId_account() + "','" + ad.getTitle() + "','"
					+ ad.getAddress() + "','" + ad.getProvincia() + "','" + ad.getDescription() + "','" + ad.getDimension() + "','" + ad.getType()
					+ "','" + ad.getPrice() + "'," + " SYSDATE())";
			System.out.println("INSERT QUERY:" + insertAd);
			stmt.executeUpdate(insertAd);
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

	public void insertFile(UUID id_ad, String path) throws SQLException {
		Statement stmt = null;
		Connection conn = null;

		try {
			conn = AppUtil.getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			String insertFile = " INSERT INTO files (id_ad,path)";
			insertFile += " VALUES ('" + id_ad + "','" + path + "')";
			stmt.executeUpdate(insertFile);
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

	public ArrayList<AdsBean> getAds(String user) throws SQLException {
		Statement stmt = null;
		Connection conn = null;
		ArrayList<AdsBean> ads = new ArrayList<AdsBean>();
		try {
			conn = AppUtil.getDBConnection();
			stmt = conn.createStatement();
			String searchAds = " SELECT * from ads WHERE id_account = '" + user + "'";
			System.out.println("QUERY ACCOUNT: " + searchAds);
			ResultSet adsList = stmt.executeQuery(searchAds);
			while (adsList.next()) {
				String title = adsList.getString("title");
				String address = adsList.getString("address");
				String provincia = adsList.getString("provincia");
				String description = adsList.getString("description");
				int dimension = Integer.parseInt(adsList.getString("dimension"));
				String type = adsList.getString("type");
				int price = Integer.parseInt(adsList.getString("price"));

				AdsBean ad = new AdsBean();
				ad.setTitle(title);
				ad.setAddress(address);
				ad.setProvincia(provincia);
				ad.setDescription(description);
				ad.setDimension(dimension);
				ad.setType(type);
				ad.setPrice(price);

				ads.add(ad);
			}
			return ads;
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
