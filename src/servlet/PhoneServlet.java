package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.PhoneBean;
import beans.UserBean;
import dao.PhonesDao;
import dao.UserDao;

@WebServlet("/savePhones")
public class PhoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao = new UserDao();
	private PhonesDao phonesDao = new PhonesDao();

	public PhoneServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String action = request.getParameter("action");
			String user = request.getParameter("user");
			UserBean userobj = userDao.consult(user);

			if (action.equalsIgnoreCase("addPhones")) {

				request.getSession().setAttribute("thisUser", userobj);
				request.setAttribute("thisUser", userobj);

				RequestDispatcher view = request.getRequestDispatcher("/phoneregistration.jsp");
				request.setAttribute("phones", phonesDao.list(userobj.getId()));
				view.forward(request, response);

			} else if (action.equalsIgnoreCase("deletePhone")) {

				String phoneId = request.getParameter("phoneId");
				phonesDao.delete(phoneId);

				UserBean userBean = (UserBean) request.getSession().getAttribute("thisUser");

				RequestDispatcher view = request.getRequestDispatcher("/phoneregistration.jsp");
				request.setAttribute("phones", phonesDao.list(userBean.getId()));
				request.setAttribute("msg", "Deleted ");
				view.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			UserBean userBean = (UserBean) request.getSession().getAttribute("thisUser");

			String number = request.getParameter("number");
			String typess = request.getParameter("type");

			String action = request.getParameter("action");

			if (action == null || (action != null && !action.equalsIgnoreCase("back"))) {

				if (number != null && !number.isEmpty()) {

					PhoneBean phoneBean = new PhoneBean();
					phoneBean.setNumbers(number);
					phoneBean.setTypess(typess);
					phoneBean.setUsers(userBean.getId());

					phonesDao.save(phoneBean);

					request.getSession().setAttribute("thisUser", userBean);
					request.setAttribute("thisUser", userBean);

					RequestDispatcher view = request.getRequestDispatcher("/phoneregistration.jsp");
					request.setAttribute("phones", phonesDao.list(userBean.getId()));
					request.setAttribute("msg", "Saved");
					view.forward(request, response);

				} else {
					RequestDispatcher view = request.getRequestDispatcher("/phoneregistration.jsp");
					request.setAttribute("phones", phonesDao.list(userBean.getId()));
					request.setAttribute("msg", "Invalid number");
					view.forward(request, response);
				}
				
			} else {
				
				RequestDispatcher view = request.getRequestDispatcher("/userregistration.jsp");
				request.setAttribute("users", userDao.list());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
