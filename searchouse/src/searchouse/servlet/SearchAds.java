package searchouse.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import searchouse.bean.AccountBean;
import searchouse.bean.AdsBean;
import searchouse.bean.ErrorBean;
import searchouse.bean.SearchAdsBean;
import searchouse.db.AccountDAO;
import searchouse.db.AdsDAO;
import searchouse.utils.AppUtil;

/**
 * Servlet implementation class MyAds
 */
@WebServlet("/SearchAds")
public class SearchAds extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public SearchAds() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

	}

	public void destroy() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext sc = request.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		String provincia = request.getParameter("provincia").trim();
		String district = request.getParameter("district").trim();
		String distance = request.getParameter("distance").trim();
		String minSuperficie = request.getParameter("minSuperficie").trim();
		String maxSuperficie = request.getParameter("maxSuperficie").trim();
		String minPrezzo = request.getParameter("minPrezzo").trim();
		String maxPrezzo = request.getParameter("maxPrezzo").trim();
		String type = request.getParameter("type").trim();
		String x = request.getParameter("lan").trim();
		String y = request.getParameter("lng").trim();

		System.out.println("Provincia->" + provincia);
		System.out.println("District->" + district);
		System.out.println("Distance->" + distance);
		System.out.println("MinSuperficie->" + minSuperficie);
		System.out.println("MaxSuperficie->" + maxSuperficie);
		System.out.println("MinPrezzo->" + minPrezzo);
		System.out.println("MaxPrezzo->" + maxPrezzo);
		System.out.println("Type->" + type);
		System.out.println("Lan->" + x);
		System.out.println("Lng->"+ y);

		
		ArrayList<AdsBean> adsList = new ArrayList<AdsBean>();
		SearchAdsBean searchAds = new SearchAdsBean();
		// SaveMySQL saveAccount = new SaveMySQL();
		AdsDAO db = new AdsDAO();
		ErrorBean error = new ErrorBean();

		if(provincia != "") 
			searchAds.setProvincia(provincia);
		if(district != "") {
			searchAds.setDistrict(district);
		}
		if(distance != "")
			searchAds.setDistance(distance);
		if(minSuperficie != "")
			searchAds.setMinSuperficie(Integer.parseInt(minSuperficie));
		if(maxSuperficie != "")
			searchAds.setMaxSuperficie(Integer.parseInt(maxSuperficie));
		if(minPrezzo != "")
			searchAds.setMinPrezzo(Integer.parseInt(minPrezzo));
		if(maxPrezzo != "")
			searchAds.setMaxPrezzo(Integer.parseInt(maxPrezzo));
		if(x != "")
			searchAds.setX(Float.parseFloat(x));
		if(y != "")
			searchAds.setY(Float.parseFloat(y));
		searchAds.setType(type);
		
		AppUtil.storeData(session, "QUERY", searchAds);
		
		try {
			if (!db.SearchAds(searchAds).isEmpty()) {
				adsList = db.SearchAds(searchAds);
				AppUtil.removeData(session, "ADLIST");
				AppUtil.storeData(session, "ADLIST", adsList);
				System.out.println("ADS TROVATI");
				ServletContext sc = request.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			} else {
				error.setMessage("Nessun annuncio trovato con questi parametri");
				error.setComeBack("SearchAds");
				// request.getSession().removeAttribute("ACCOUNT");
				AppUtil.storeData(session, "ERROR", error);
				ServletContext sc = request.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
