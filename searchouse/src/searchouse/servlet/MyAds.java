package searchouse.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import searchouse.bean.AccountBean;
import searchouse.bean.AdsBean;
import searchouse.db.AdsDAO;
import searchouse.db.SaveMySQL;
import searchouse.utils.AppUtil;

/**
 * Servlet implementation class MyAds
 */
@WebServlet("/MyAds")
public class MyAds extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyAds() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		
		String index = request.getParameter("AdIndex");
		String whatsend = request.getParameter("whatsend");
		if(whatsend == null) {
			whatsend = "myAds";
		}
		int a = 0;
		if(index!=null)
			a = Integer.parseInt(index);
		if(whatsend.equals("openAd")) {
			ArrayList<AdsBean> adArray = new ArrayList<AdsBean>();
			adArray = AppUtil.getAllAds(session);
			if(a>=adArray.size()) {
				ServletContext sc = request.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/myAds.jsp");
				rd.forward(request, response);
			}else {
			AppUtil.removeData(session, "AD");
			AppUtil.storeData(session, "AD", adArray.get(a));
			ServletContext sc = request.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/loggedAd.jsp");
			rd.forward(request, response);
			}
		}
		else if(whatsend.equals("myAds")) {
			AppUtil.removeData(session, "AD");
			AdsDAO db = new AdsDAO();
		
			if(AppUtil.getLoginedUser(session)!=null) {
				AccountBean user = (AccountBean) AppUtil.getLoginedUser(session);
				System.out.println(user.getUsername());
				AppUtil.removeData(session, "ADLIST");
				try {
					ArrayList<AdsBean> ads = db.getAllFromUser(user.getUsername());
					for(AdsBean ad: ads) {
						ad.setImagesName(db.getFilePath(ad.getId()));
					}
					user.setAds(ads);
					//user.setAds(db.getAllFromUser(user.getUsername()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				AppUtil.storeData(session, "ADLIST", user.getAds());
				AppUtil.removeLoginedUser(session);
				AppUtil.storeLoginedUser(session, user);
			}
		AppUtil.forward(request, response, "/myAds.jsp");
		}
		else {
			ServletContext sc = request.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/myAds.jsp");
			rd.forward(request, response);
		}
//		AppUtil.storeData(session,"ADLIST", AppUtil.getLoginedUser(session).getAds());
//		AppUtil.forward(request, response, "/AdPreview.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//doGet(request, response);
		// Aggiungere codice per gestione eliminazione e modifica degli annunci
		
	
		HttpSession session = request.getSession();
		
		UUID id = UUID.fromString(request.getParameter("Delete").trim());
		
		AdsDAO db = new AdsDAO();
		AdsBean adDelete = new AdsBean();
		adDelete.setId(id);
		
		if(request.getSession()!= null) {
			try {
				db.Delete(adDelete);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("/searchouse/myAds");
		} else {
			doGet(request, response);
		}
		
	
	
		
	}

}
