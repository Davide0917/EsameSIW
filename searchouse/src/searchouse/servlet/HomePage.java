package searchouse.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.TODO;

import searchouse.bean.AccountBean;
import searchouse.bean.AdsBean;
import searchouse.db.AccountDAO;
import searchouse.db.AdsDAO;
import searchouse.utils.AppUtil;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomePage() {
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

		HttpSession session = request.getSession();
		
		// QUESTO CODICE SERVE PER APRIRE DA UN ADPREVIEW LA JSP ESTESA CON L'AD CLICCATA
		// INIZIO
		//TODO
		// VA IMPLEMENTATA LA PAGINA JSP PER LA VISUALIZZAZIONE DELL'AD
		String index = request.getParameter("AdIndex");
		String whatsend = request.getParameter("whatsend");
		if(whatsend == null) {
			whatsend = "homepage";
		}
		int a = 0;
		if(index!=null)
			a = Integer.parseInt(index);
		
		if(whatsend.equals("openAd")) {
			ArrayList<AdsBean> adArray = new ArrayList<AdsBean>();
			adArray = AppUtil.getAllAds(session);
			if(a>=adArray.size()) {
				ServletContext sc = request.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}else {
			AppUtil.removeData(session, "AD");
			AppUtil.storeData(session, "AD", adArray.get(a));
			ServletContext sc = request.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/Ad.jsp");
			rd.forward(request, response);
			}
		}
		else if(whatsend.equals("homepage")) {
			AppUtil.removeData(session, "AD");
			AppUtil.removeData(session, "REGISTERED_ACCOUNT");
			System.out.println("sono nella homepage servlet");
			AppUtil.removeData(session, "QUERY");
			// System.out.println(AppUtil.getData(session, "REGISTERED_ACCOUNT"));
			AdsDAO db = new AdsDAO();
			ArrayList<AdsBean> adList = new ArrayList<AdsBean>();
			try {
				adList = db.getAll();
				AppUtil.removeData(session, "ADLIST");
				AppUtil.storeData(session, "ADLIST", adList);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ServletContext sc = request.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
		else if(whatsend.equals("deleteaccount")) {
			AccountDAO dao = new AccountDAO();
			AccountBean account = (AccountBean) AppUtil.getLoginedUser(session);
			try {
				dao.Delete(account);
				System.out.println("Account eliminato");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			AppUtil.removeLoginedUser(session);
			ServletContext sc = request.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
		else {
			ServletContext sc = request.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
