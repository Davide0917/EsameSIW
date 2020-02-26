package searchouse.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import searchouse.bean.AdsBean;
import searchouse.bean.Bean;
import searchouse.bean.SearchAdsBean;
import searchouse.utils.AppUtil;

public class AdsDAO implements DAO {

	@Override
	public void Insert(Bean newBean) throws SQLException {
		Statement stmt = null;
		Connection conn = null;

		try {
			conn = AppUtil.getDBConnection();
			AdsBean ad = (AdsBean) newBean;
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			String insertAd = "INSERT INTO ads (id,id_account,title,address,provincia,description,dimension,type,price,lan,lng,date_ins)";
			insertAd += " VALUES ('" + ad.getId() + "','" + ad.getId_account() + "','" + ad.getTitle() + "','"
					+ ad.getAddress() + "','" + ad.getProvincia() + "','"+ ad.getDescription() + "','" + ad.getDimension()+ "','" + ad.getType()
					+ "','" + ad.getPrice()+ "','" + ad.getX() + "','" + ad.getY() + "'," + " SYSDATE())";
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

	public void Delete(Bean beanToRemove) throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt = null;
		Connection conn = null;

		try {
			
			conn = AppUtil.getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			AdsBean ad = (AdsBean) beanToRemove;
			DeleteFile(ad.getId());
			String delete = "DELETE FROM ads WHERE id='" +ad.getId()+ "'";
			String deleteimg = "DELETE FROM files WHERE id_ad='"+ad.getId()+"'";
			stmt.executeUpdate(delete);
			stmt.executeUpdate(deleteimg);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bean GetOne(Bean beanToGet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void DeleteFile(UUID id_ad) throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt = null;
		Connection conn = null;

		try {
			
			conn = AppUtil.getDBConnection();
			
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String delete = "DELETE FROM files WHERE id_ad='" +id_ad+ "'";
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

	public ArrayList<AdsBean> getAllFromUser(String user) throws SQLException {
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
				String id_ad = adsList.getString("id");
				String title = adsList.getString("title");
				String address = adsList.getString("address");
				String provincia = adsList.getString("provincia");
				String description = adsList.getString("description");
				int dimension = Integer.parseInt(adsList.getString("dimension"));
				String type = adsList.getString("type");
				int price = Integer.parseInt(adsList.getString("price"));
				float x = Float.parseFloat(adsList.getString("lan"));
				float y = Float.parseFloat(adsList.getString("lng"));

				AdsBean ad = new AdsBean();
				UUID id = UUID.fromString(id_ad);
				ad.setId(id);
				ad.setTitle(title);
				ad.setAddress(address);
				ad.setProvincia(provincia);
				ad.setDescription(description);
				ad.setDimension(dimension);
				ad.setType(type);
				ad.setPrice(price);
				ad.setImagesName(getFilePath(id));
				ad.setX(x);
				ad.setY(y);
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

	public ArrayList<String> getFilePath(UUID id) throws SQLException {
		Statement stmt = null;
		Connection conn = null;
		ArrayList<String> filePath = new ArrayList<String>();
		try {
			conn = AppUtil.getDBConnection();
			stmt = conn.createStatement();
			String searchFile = " SELECT * from files WHERE id_ad = '" + id + "'";
			System.out.println("QUERY ACCOUNT: " + searchFile);
			ResultSet adsList = stmt.executeQuery(searchFile);
			while (adsList.next()) {
				String path = adsList.getString("path");
				filePath.add(path);
			}
			return filePath;
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
	
	public ArrayList<AdsBean> getAll() throws SQLException{
		Statement stmt = null;
		Connection conn = null;
		ArrayList<AdsBean> ads = new ArrayList<AdsBean>();
		try {
			conn = AppUtil.getDBConnection();
			stmt = conn.createStatement();
			String searchAds = " SELECT * from ads ";
			System.out.println("QUERY ACCOUNT: " + searchAds);
			ResultSet adsList = stmt.executeQuery(searchAds);
			while (adsList.next()) {
				String id_ad = adsList.getString("id");
				String title = adsList.getString("title");
				String address = adsList.getString("address");
				String provincia = adsList.getString("provincia");
				String description = adsList.getString("description");
				int dimension = Integer.parseInt(adsList.getString("dimension"));
				String type = adsList.getString("type");
				int price = Integer.parseInt(adsList.getString("price"));
				float x = Float.parseFloat(adsList.getString("lan"));
				float y = Float.parseFloat(adsList.getString("lng"));
				UUID id = UUID.fromString(id_ad);
				
				AdsBean ad = new AdsBean();
				
				ad.setId(id);
				ad.setTitle(title);
				ad.setAddress(address);
				ad.setProvincia(provincia);
				ad.setDescription(description);
				ad.setDimension(dimension);
				ad.setType(type);
				ad.setPrice(price);
				ad.setImagesName(getFilePath(id));
				ad.setX(x);
				ad.setY(y);
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
	
	public int CalcolaDistanza(double LanRicerca, double LngRicerca, double LanAD, double LngAD) {
			final int R = 6371; // Radius of the earth

		    double latDistance = Math.toRadians(LanAD- LanRicerca);
		    double lonDistance = Math.toRadians(LngAD - LngRicerca);
		    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
		            + Math.cos(Math.toRadians(LanRicerca)) * Math.cos(Math.toRadians(LanAD))
		            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		    double distance = R * c ; // convert to meters

		    System.out.println("LA DISTANZA DAL PORCO DI DIO ALLA MADONNA è ----> "+(int)distance) ;
		    return (int)distance;
	}
	
	public ArrayList<AdsBean> SearchAds(SearchAdsBean SAD) throws SQLException {
		Statement stmt = null;
		Connection conn = null;
		ArrayList<AdsBean> ads = new ArrayList<AdsBean>();
		try {
			conn = AppUtil.getDBConnection();
			stmt = conn.createStatement();
			String searchAds;
			System.out.println("Valori di ricerca "+SAD.getX()+SAD.getY());
			if(SAD.getType().equals("NessunCampo")) {
				if (!SAD.getProvincia().equals("Seleziona Provincia")) {
						searchAds = " SELECT * from ads WHERE (provincia  ='" + SAD.getProvincia()
							+ "') AND (dimension >= '" + SAD.getMinSuperficie() + "' AND dimension <= '"
							+ SAD.getMaxSuperficie() + "') AND ( price >= '" + SAD.getMinPrezzo() + "'AND price <= '"
							+ SAD.getMaxPrezzo() + "')";
				} else {
						searchAds = " SELECT * from ads WHERE (dimension >= '" + SAD.getMinSuperficie()
							+ "' AND dimension <= '" + SAD.getMaxSuperficie() + "') AND ( price >= '" + SAD.getMinPrezzo()
							+ "'AND price <= '" + SAD.getMaxPrezzo() + "')";
				}
			} else {
				if (!SAD.getProvincia().equals("Seleziona Provincia")) {
					searchAds = " SELECT * from ads WHERE (provincia  ='" + SAD.getProvincia()
						+ "') AND (dimension >= '" + SAD.getMinSuperficie() + "' AND dimension <= '"
						+ SAD.getMaxSuperficie() + "') AND ( price >= '" + SAD.getMinPrezzo() + "'AND price <= '"
						+ SAD.getMaxPrezzo() + "') AND (type = '" + SAD.getType() + "')";
				} else {
						searchAds = " SELECT * from ads WHERE (dimension >= '" + SAD.getMinSuperficie()
							+ "' AND dimension <= '" + SAD.getMaxSuperficie() + "') AND ( price >= '" + SAD.getMinPrezzo()
							+ "'AND price <= '" + SAD.getMaxPrezzo() + "') AND (type = '" + SAD.getType() + "')";
				}
			}
			System.out.println("QUERY ADS: " + searchAds);
			ResultSet adsList = stmt.executeQuery(searchAds);
			while (adsList.next()) {
				UUID id = UUID.fromString(adsList.getString("id"));
				String id_account = adsList.getString("id_account");
				String title = adsList.getString("title");
				String address = adsList.getString("address");
				String provincia = adsList.getString("provincia");
				String description = adsList.getString("description");
				String dimension =adsList.getString("dimension");
				String type = adsList.getString("type");
				String price = adsList.getString("price");
				String x = adsList.getString("lan");
				String y = adsList.getString("lng");

				AdsBean ad = new AdsBean();
				ad.setId(id);
				ad.setId_account(id_account);
				ad.setTitle(title);
				ad.setAddress(address);
				ad.setProvincia(provincia);
				ad.setDescription(description);
				if(dimension!="")
					ad.setDimension(Integer.parseInt(dimension));
				ad.setType(type);
				if(price!= "")
					ad.setPrice(Integer.parseInt(price));
				ad.setImagesName(getFilePath(id));
				if(x!="")
					ad.setX(Float.parseFloat(x));
				if(y!="")
					ad.setY(Float.parseFloat(y));
				if(!SAD.getDistance().equals("Seleziona Distanza")) {
					if(CalcolaDistanza((double)SAD.getX(),(double)SAD.getY(), Double.parseDouble(x), Double.parseDouble(y)) < Integer.parseInt(SAD.getDistance())) {
						ads.add(ad);
					}
				}else {
					ads.add(ad);
				}
				System.out.println("RICERCA DB " + title + " ---> " + address + " ---> " + dimension);
				
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
