package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import dao.UserDao;

@WebServlet("/servletSearch")
public class ServletSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao = new UserDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String descriptionSearch = request.getParameter("consultDescri");
		if(descriptionSearch != null) {
			try {
				List<UserBean> listSearch = userDao.listByConsult(descriptionSearch);
				
				RequestDispatcher view = request.getRequestDispatcher("/userregistration.jsp");
				request.setAttribute("users", listSearch);
				view.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
