package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.UserBean;
import dao.UserDao;

@WebServlet("/saveUser")
@MultipartConfig
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao = new UserDao();

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String action = request.getParameter("action");
			String user = request.getParameter("user");

			if (action != null && action.equalsIgnoreCase("delete") && user != null) {
				userDao.delete(user);
				RequestDispatcher view = request.getRequestDispatcher("/userregistration.jsp");
				request.setAttribute("users", userDao.listAll());
				view.forward(request, response);

			} else if (action != null && action.equalsIgnoreCase("edit")) {

				UserBean beanJsp = userDao.consult(user);

				RequestDispatcher view = request.getRequestDispatcher("/userregistration.jsp");
				request.setAttribute("user", beanJsp);
				view.forward(request, response);

			} else if (action != null && action.equalsIgnoreCase("listAll")) {
				
				RequestDispatcher view = request.getRequestDispatcher("/userregistration.jsp");
				request.setAttribute("users", userDao.listAll());
				view.forward(request, response);
			
			} else if(action != null && action.equalsIgnoreCase("download")) {
				
				UserBean userbean = userDao.consult(user);
				if(userbean != null) {
					
					String contenType = "";
					byte[] fileBytes = null;
					String types = request.getParameter("types");
					
					if(types.equalsIgnoreCase("img")) {
						contenType = userbean.getContentType();
						fileBytes = new Base64().decodeBase64(userbean.getPicBase64());
					
					}else if(types.equalsIgnoreCase("pdf")) {
						contenType = userbean.getContentTypePdf();
						fileBytes = new Base64().decodeBase64(userbean.getPdfBase64());
					}
					
					response.setHeader("Content-Disposition", "attachment;filename=file." + contenType.split("\\/")[1]);
				
					InputStream is = new ByteArrayInputStream(fileBytes);
					
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
					
					while((read = is.read(bytes)) != -1 ) {
						os.write(bytes,0, read);
					}
					os.flush();
					os.close();
				}
				
				
			} else {
				RequestDispatcher view = request.getRequestDispatcher("/userregistration.jsp");
				request.setAttribute("users", userDao.listAll());
				view.forward(request, response);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);

		String action = request.getParameter("action");

		if (action != null && action.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/userregistration.jsp");
				request.setAttribute("users", userDao.listAll());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String zip = request.getParameter("zip");
			String street = request.getParameter("street");
			String neighborhood = request.getParameter("neighborhood");
			String city = request.getParameter("city");
			String states = request.getParameter("states");
			String ibge = request.getParameter("ibge");
			String active = request.getParameter("active");
			String gender = request.getParameter("gender");
			String perfil = request.getParameter("perfil");
			
			UserBean user = new UserBean();
			user.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			user.setLogin(login);
			user.setPassword(password);
			user.setName(name);
			user.setPhone(phone);
			user.setZip(zip);
			user.setStreet(street);
			user.setNeighborhood(neighborhood);
			user.setCity(city);
			user.setStates(states);
			user.setIbge(ibge);
			user.setGender(gender);
			user.setPerfil(perfil);
			
			if(request.getParameter("active") != null && request.getParameter("active").equalsIgnoreCase("on"))  {
				user.setActive(true);
			}else {
				user.setActive(false);
			}

			try {
				
				if(ServletFileUpload.isMultipartContent(request)) {
					
					Part imgPic = request.getPart("pic");
					
					if(imgPic != null && imgPic.getInputStream().available() > 0) {
					
						String picBase64 = new Base64().encodeBase64String(convertStreamToByte(imgPic.getInputStream()));
						
						user.setPicBase64(picBase64);
						user.setContentType(imgPic.getContentType());
						
						//miniPic
						byte[] imageByteDecode = new Base64().decodeBase64(picBase64);
						BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
						
						int type = bufferedImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
						
						BufferedImage resizedImage = new BufferedImage(100, 100, type);
						Graphics2D g = resizedImage.createGraphics();
						g.drawImage(bufferedImage, 0, 0, 100, 100, null);
						g.dispose();
						
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(resizedImage, "png", baos);
						
						String picBase64Mini = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
						
						user.setMiniPicBase64(picBase64Mini);
						
					}else {
						user.setUpdatePic(false);
					}
					
					Part pdfBase = request.getPart("pdf");
					
					if(pdfBase != null && pdfBase.getInputStream().available() > 0) {
						String pdfBase64 = new Base64().encodeBase64String(convertStreamToByte(pdfBase.getInputStream()));
						
						user.setPdfBase64(pdfBase64);
						user.setContentTypePdf(pdfBase.getContentType());
						
					} else {
						user.setUpdatePdf(false);
					}
				}

				String msg = null;
				boolean canInsert = true;

				if (login == null || login.isEmpty()) {
					msg = "Invalid Login";
					canInsert = false;
				
				} else if (password == null || password.isEmpty()) {
					msg = "Invalid Password";
					canInsert = false;

				} else if (name == null || name.isEmpty()) {
					msg = "Invalid Name";
					canInsert = false;

				} else if (zip == null || zip.isEmpty()) {
					msg = "Invalid zip";
					canInsert = false;
				
				} else if (id == null || id.isEmpty() && !userDao.validationLogin(login)) {
					msg = "Login already exist";
					canInsert = false;

				}

				if (msg != null) {
					request.setAttribute("msg", msg);
					
				} else if (id == null || id.isEmpty() && userDao.validationLogin(login) && canInsert) {
					userDao.saveUser(user);

				} 
				
				if (id != null && !id.isEmpty() && canInsert) {
					userDao.update(user);
				}

				if (!canInsert) {
					request.setAttribute("user", user);
				}

				RequestDispatcher view = request.getRequestDispatcher("/userregistration.jsp");
				request.setAttribute("users", userDao.listAll());
				request.setAttribute("msg", "Save successful");
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private byte[] convertStreamToByte(InputStream img) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = img.read();
		while (reads != -1) {
			baos.write(reads);
			reads = img.read();
		}
		
		return baos.toByteArray(); 
	}
	
}
