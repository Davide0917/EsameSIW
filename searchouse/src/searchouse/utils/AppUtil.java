package searchouse.utils;

import searchouse.bean.AccountBean;
import searchouse.bean.AdsBean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AppUtil {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	//	Da utilizzare per db in locale
	//private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/searchouse?useSSL=false";
	//private static final String DB_USER = "root";
	//private static final String DB_PASSWORD = "root";
	private static final String DB_CONNECTION = "jdbc:mysql://remotemysql.com:3306/M2jeYbDPbl?useSSL=false";
	private static final String DB_USER = "M2jeYbDPbl";
	private static final String DB_PASSWORD = "I163oKiQrQ";
	
	public static Connection getDBConnection() throws Exception {
		System.out.println("-------- MySQL JDBC Connection --------");
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR : MySQL JDBC Driver not found!!");
			throw new Exception(e.getMessage());
			// TODO: handle exception
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			System.out.println("SQL Connection to database established!");
		} catch (SQLException e) {
			System.out.println("Connection to database failed!");
			throw new SQLException(e.getErrorCode() + ":" + e.getMessage());
		}
		return dbConnection;
	}
	
	public static void storeLoginedUser(HttpSession session, AccountBean loginedUtente) {
		session.setAttribute("LOGINED_USER", loginedUtente);
	}
	
	public static AccountBean getLoginedUser(HttpSession session) {
		AccountBean utente = (AccountBean) session.getAttribute("LOGINED_USER");
		return utente;
	}
	
	public static void removeLoginedUser(HttpSession session) {
		session.removeAttribute("LOGINED_USER");
	}
	
	public static void storeData(HttpSession session, String attribute_name, Object value) {
		if (session.getAttribute(attribute_name) != null)
			session.removeAttribute(attribute_name);
		session.setAttribute(attribute_name, value);
	}
	
	public static Object getData(HttpSession session,String attribute_name) {
		Object obj = (Object) session.getAttribute(attribute_name);
		return obj;
	}
	
	// QUESTO METODO RESTITUISCE LA LISTA DEGLI ANNUNCI SALVATO IN SESSION 
	// CHE CONTERRA GLI ANNUNCI IN BASE ALLE QUERY O LA LISTA DI TUTTI GLI ANNUNCI 
	// SE NON C'E' NESSUNA QUERY
	public static ArrayList<AdsBean> getAllAds(HttpSession session){
		@SuppressWarnings("unchecked")
		ArrayList<AdsBean> ads = (ArrayList<AdsBean>)session.getAttribute("ADLIST");
		return ads;
	}
	
	public static void removeData(HttpSession session, String attribute_name) {
		session.removeAttribute(attribute_name);
	}
	
	public static void forward(HttpServletRequest request,HttpServletResponse response, String url) throws ServletException, IOException {
		ServletContext sc = request.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	public static String getExtension(String name) {
		int posPoint = name.lastIndexOf(".");
		return name.substring(posPoint);
	}
}
