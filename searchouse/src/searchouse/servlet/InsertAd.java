package searchouse.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import searchouse.bean.AccountBean;
import searchouse.bean.AdsBean;
import searchouse.bean.ErrorBean;
import searchouse.db.AdsDAO;
import searchouse.utils.AppUtil;

/**
 * Servlet implementation class InsertAd
 */
@WebServlet("/InsertAd")
public class InsertAd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertAd() {
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
		if (AppUtil.getLoginedUser(session) == null) {
			AppUtil.forward(request, response, "/logInForm.jsp");
		} else
			// response.getWriter().append("Served at: ").append(request.getContextPath());
			AppUtil.forward(request, response, "/insertAd.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		// Usare due jsp separate per il caricamento dei dati e per il caricamento delle
		// immagini
		// e questa sola servlet per l'inserimento dell'annuncio
		// dati e immagini hanno tabelle separate e distinte nel db
		// necessitano quindi due funzioni diverse nel CRUDE SaveMySql
		HttpSession session = request.getSession();
		// SaveMySQL db = new SaveMySQL();
		AdsDAO db = new AdsDAO();

		AdsBean ad = new AdsBean();
		UUID id = UUID.randomUUID();
		String id_account = AppUtil.getLoginedUser(session).getUsername();
		String title = "";
		String address = "";
		String provincia = "";
		String description = "";
		int dimension = 10;
		String type = "";
		int price = 1;
		float x = 0;
		float y = 0;
		ArrayList<String> pathImg = new ArrayList<String>();

		// FINALMENTE HO TROVATO IL MODO PER CARICARE LE IMMAGINI CONVERTENDO I FILEITEM
		// PROVENIENTI DAL FORM
		// IN BYTE CON IL METODO GET() E POI IL BYTE IN STRINGHE CON TOSTRING PER
		// POTERLI SALVARE NEL DB
		// COMMENTO PER SVILUPPARE CODICE ALTERNATIVO SUGGERITO DA OLIVA
		// CREARE ALTRA JSP PER CARICAMENTO DELLE FOTO
		// String directoryPath = "C:/Users/Lorenzo/Desktop/Git for
		// SIW/SIW/searchouse/searchouse/img";
		String directoryPath = getServletContext().getRealPath("/img");
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory())
						.parseRequest((new ServletRequestContext(request)));
				int cont = 1;
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						if (AppUtil.getExtension(item.getName()).equalsIgnoreCase(".png")
								|| AppUtil.getExtension(item.getName()).equalsIgnoreCase(".jpg")
								|| AppUtil.getExtension(item.getName()).equalsIgnoreCase(".jpeg")) {
							String name = id + "_" + cont + AppUtil.getExtension(item.getName());// new
																									// File(item.getName()).getName();
							item.write(new File(directoryPath + File.separator + name));
							// System.out.println("Percorso img "+directoryPath + File.separator + name);
							pathImg.add(name);
							cont++;
						}
					}
					if (item.isFormField()) {
						String inputName = (String) item.getFieldName();
						if (inputName.equalsIgnoreCase("title"))
							title = (String) item.getString().trim();
						if (inputName.equalsIgnoreCase("address"))
							address = (String) item.getString().trim();
						if (inputName.equalsIgnoreCase("provincia"))
							provincia = (String) item.getString().trim();
						if (inputName.equalsIgnoreCase("description"))
							description = (String) item.getString().trim();
						if (inputName.equalsIgnoreCase("dimension"))
							dimension = Integer.parseInt((String) item.getString().trim());
						if (inputName.equalsIgnoreCase("type"))
							type = (String) item.getString().trim();
						if (inputName.equalsIgnoreCase("price"))
							price = Integer.parseInt((String) item.getString().trim());
						if (inputName.equalsIgnoreCase("lan"))
							x = Float.parseFloat((String) item.getString().trim());
						if (inputName.equalsIgnoreCase("lng"))
							y = Float.parseFloat((String) item.getString().trim());

					}

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} else {
			System.out.println("Errore file");
		}

		ad.setId(id);
		ad.setId_account(id_account);
		ad.setTitle(title);
		ad.setAddress(address);
		ad.setProvincia(provincia);
		ad.setDescription(description);
		ad.setDimension(dimension);
		ad.setType(type);
		ad.setPrice(price);
		ad.setImagesName(pathImg);
		ad.setX(x);
		ad.setY(y);
		System.out.println("STAMPA X------->"+x);
		System.out.println("STAMPA Y------->"+y);

		ErrorBean err = new ErrorBean();
		AccountBean user = (AccountBean) AppUtil.getLoginedUser(session);

		if (ad.isNull()) {
			System.out.println("NULLI");
			err.setMessage("Campi non validi");
			err.setComeBack("/searchouse/newAd");
			AppUtil.storeData(session, "ERROR", err);
			AppUtil.forward(request, response, "/index.jsp");
		} else {
			try {
				db.Insert(ad);
				user.setAds(db.getAllFromUser(user.getUsername()));
				AppUtil.removeLoginedUser(session);
				AppUtil.storeLoginedUser(session, user);
				err.setMessage("Annuncio creato!");
				AppUtil.removeData(session, "ERROR");
				AppUtil.storeData(session, "ERROR", err);
				// AppUtil.removeData(session, "ADLIST");
				// AppUtil.storeData(session, "ADLIST", db.getAll());
				AppUtil.forward(request, response, "/index.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (String s : ad.getImagesName()) {
				try {
					System.out.println(s);
					db.insertFile(ad.getId(), s);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
