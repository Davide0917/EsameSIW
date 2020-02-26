package searchouse.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.print.attribute.ResolutionSyntax;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import searchouse.bean.AccountBean;
import searchouse.bean.Bean;
import searchouse.bean.ErrorBean;
import searchouse.db.AccountDAO;
import searchouse.db.SaveMySQL;
import searchouse.utils.AppUtil;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogIn() {
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

		ServletContext sc = request.getServletContext();
		System.out.println("sono nel get del login");
		// Controllo se in session c'� un account loggato
		if (request.getSession().getAttribute("ACCOUNT") != null) {
			System.out.println("Gia loggato");
			RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = sc.getRequestDispatcher("/logInForm.jsp");
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
		
		HttpSession session = request.getSession();
		ServletContext sc = request.getServletContext();
		// Controllo per verificare che in session non vi � nessun account loggato
		// altrimenti lo elimina in session e prosegue
		if (request.getSession().getAttribute("LOGINED_USER") != null) {
			AppUtil.removeLoginedUser(session);
		}
		
		AccountBean account = new AccountBean();
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		System.out.println(password);
		ErrorBean error = new ErrorBean();

		if (username != null && password != null) {
			account.setUsername(username);
			account.setPassword(password);

			//SaveMySQL db = new SaveMySQL();
			AccountDAO db = new AccountDAO();

			try {
				account.setLogged(db.isCorrect(username, password));
				if (account.isLogged()) {
					account = (AccountBean) db.GetOne(account);
					account.setLogged(true);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (account.isLogged()) {
//				// Qui solo se password e username sono validi settiamo account in session
				System.out.println("BENVENUTO");
				AppUtil.removeLoginedUser(session);
				AppUtil.storeLoginedUser(session, account);
				response.sendRedirect("/searchouse/index");
			} else {
				error.setMessage("Username o password non validi");
				error.setComeBack("/searchouse/login");
				AppUtil.storeData(session, "ERROR", error);
				RequestDispatcher rd = sc.getRequestDispatcher("/logInForm.jsp");
				rd.forward(request, response);
				System.out.println("ERRORE LOGIN");
			}

		} else {
			error.setMessage("Username o password non validi");
			error.setComeBack("/searchouse/index");
			AppUtil.storeData(session, "ERROR", error);
			RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
		
	}

}
