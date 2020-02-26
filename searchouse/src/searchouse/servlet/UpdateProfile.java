package searchouse.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import searchouse.bean.AccountBean;
import searchouse.bean.ErrorBean;
import searchouse.db.AccountDAO;
import searchouse.db.SaveMySQL;
import searchouse.utils.AppUtil;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProfile() {
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
		ServletContext sc = request.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/myProfile.jsp");
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
		ErrorBean err = new ErrorBean();

		if (request.getSession() != null && request.getSession().getAttribute("LOGINED_USER") != null) {
			String whatsend = request.getParameter("whatsend");
			if (whatsend.equals("changeData")) {
				String email = request.getParameter("email").trim();
				String phone = request.getParameter("phone").trim();

				AccountBean account = new AccountBean();
				account = AppUtil.getLoginedUser(session);
				account.setEmail(email);
				account.setPhone(phone);
				ServletContext sc = request.getServletContext();

				//SaveMySQL db = new SaveMySQL();
				AccountDAO db = new AccountDAO();

				try {
					boolean updated = db.Update(account);
					if (updated) {
						System.out.println("Modifiche avvenute con successo");
						err.setMessage("Modifiche avvenute con successo!");
						AppUtil.removeData(session, "ERROR");
						AppUtil.storeData(session, "ERROR", err);
						AppUtil.removeLoginedUser(session);
						AppUtil.storeLoginedUser(session, account);
					} else {
						System.out.println("Errore modifiche");
						err.setMessage("Errore!");
						AppUtil.removeData(session, "ERROR");
						AppUtil.storeData(session, "ERROR", err);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher rd = sc.getRequestDispatcher("/myProfile.jsp");
				rd.forward(request, response);
			}
			if(whatsend.equals("change")) {
				ServletContext sc = request.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/myProfile.jsp");
				rd.forward(request, response);
			}
			if (whatsend.equals("changePssw")) {
				String password = request.getParameter("password").trim();
				String newPassword = request.getParameter("newpassword").trim();
				ServletContext sc = request.getServletContext();

				AccountBean account = new AccountBean();
				account = (AccountBean) AppUtil.getLoginedUser(session);
				if (account.getPassword().equals(password)) {
					account.setPassword(newPassword);
					//SaveMySQL db = new SaveMySQL();
					AccountDAO db = new AccountDAO();

					try {
						boolean updated = db.Update(account);
						if (updated) {
							err.setMessage("Modifiche avvenute con successo!");
							AppUtil.removeData(session, "ERROR");
							AppUtil.storeData(session, "ERROR", err);
							System.out.println("Modifiche avvenute con successo");
							AppUtil.removeLoginedUser(session);
							AppUtil.storeLoginedUser(session, account);
						} else {
							System.out.println("Errore modifiche");
							err.setMessage("Errore!");
							AppUtil.removeData(session, "ERROR");
							AppUtil.storeData(session, "ERROR", err);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("Password attuale errata");
					err.setMessage("Password attuale errata!");
					AppUtil.removeData(session, "ERROR");
					AppUtil.storeData(session, "ERROR", err);
				}
				RequestDispatcher rd = sc.getRequestDispatcher("/myProfile.jsp");
				rd.forward(request, response);

			}
		} else
			response.sendRedirect("/searchouse/index");
	}

}
