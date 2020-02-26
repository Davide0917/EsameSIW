package searchouse.servlet;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
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
import searchouse.bean.ErrorBean;
import searchouse.db.AccountDAO;
import searchouse.db.SaveMySQL;
import searchouse.utils.AppUtil;

/**
 * Servlet implementation class SignInUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
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
		RequestDispatcher rd = sc.getRequestDispatcher("/signUpForm.jsp");
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

		String name = request.getParameter("name").trim();
		String surname = request.getParameter("surname").trim();
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		String phone = request.getParameter("phone").trim();
		String email = request.getParameter("email").trim();

		System.out.println("Name->" + name);
		System.out.println("Surname->" + surname);
		System.out.println("Username->" + username);
		System.out.println("Password->******");
		System.out.println("Phone->" + phone);
		System.out.println("Email->" + email);

		AccountBean account = new AccountBean();
		//SaveMySQL saveAccount = new SaveMySQL();
		AccountDAO db = new AccountDAO();
		ErrorBean error = new ErrorBean();

		account.setName(name);
		account.setSurname(surname);
		account.setUsername(username);
		account.setPassword(password);
		account.setPhone(phone);
		account.setEmail(email);

		AppUtil.storeData(session, "REGISTERED_ACCOUNT", account);

		ServletContext sc = request.getServletContext();
		if (account.isNull()) {
			error.setMessage("Campi vuoti o non validi");
			error.setComeBack("/searchouse/signUp");
			AppUtil.storeData(session, "ERROR", error);
			RequestDispatcher rd = sc.getRequestDispatcher("/signUpForm.jsp");
			rd.forward(request, response);
		} else {
			try {
				if (!db.isPresent(account.getUsername())) {
					db.Insert(account);
					System.out.println("Account creato");
					// request.getSession().removeAttribute("ACCOUNT");
					ErrorBean err = new ErrorBean();
					err.setMessage("Account creato!");
					AppUtil.removeData(session, "ERROR");
					AppUtil.storeData(session, "ERROR", err);
					AppUtil.removeData(session, "REGISTERED_ACCOUNT");
					RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
					rd.forward(request, response);
				} else {
					error.setMessage("Username già in uso");
					error.setComeBack("/searchouse/signUp");
					// request.getSession().removeAttribute("ACCOUNT");
					AppUtil.storeData(session, "ERROR", error);
					RequestDispatcher rd = sc.getRequestDispatcher("/signUpForm.jsp");
					rd.forward(request, response);
				}

			} catch (SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode() + ":" + e.getMessage());
				e.printStackTrace();

			}
		}

	}
}
