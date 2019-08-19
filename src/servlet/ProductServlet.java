package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ProductBean;
import dao.ProductDao;

@WebServlet("/saveProducts")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao productDao = new ProductDao();

	public ProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			String action = request.getParameter("action");
			String product = request.getParameter("product");

			if (action != null && action.equalsIgnoreCase("delete")) {
				productDao.delete(product);
				RequestDispatcher view = request.getRequestDispatcher("/productregistration.jsp");
				request.setAttribute("products", productDao.list());
				view.forward(request, response);

			} else if (action != null && action.equalsIgnoreCase("edit")) {

				ProductBean productBean = productDao.consult(product);

				RequestDispatcher view = request.getRequestDispatcher("/productregistration.jsp");
				request.setAttribute("product", productBean);
				view.forward(request, response);

			} else if (action != null && action.equalsIgnoreCase("listAll")) {
				RequestDispatcher view = request.getRequestDispatcher("/productregistration.jsp");
				request.setAttribute("products", productDao.list());
				view.forward(request, response);
						
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action != null && action.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/productregistration.jsp");
				request.setAttribute("products", productDao.list());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String quant = request.getParameter("quant");
			String price = request.getParameter("price");

			try {

				String msg = null;
				boolean canInsert = true;
								
				if (price == null || price.isEmpty()) {
					msg = "Invalid price";
					canInsert = false;

				} else if (quant == null || quant.isEmpty()) {
					msg = "Invalid quant";
					canInsert = false;

				} else if (name == null || name.isEmpty()) {
					msg = "Invalid name";
					canInsert = false;

				} else if (id == null || id.isEmpty() && !productDao.validationName(name)) {
					msg = "Name already exist";
					canInsert = false;
					
				}
				
				ProductBean productBean = new ProductBean();
				productBean.setName(name);
				productBean.setId(!id.isEmpty() ? Long.parseLong(id) : null);

				if (quant != null && !quant.isEmpty()) {
					productBean.setQuant(Double.parseDouble(quant));
				}

				if (price != null && !price.isEmpty()) {
					
					String valueParse = price.replaceAll("\\.", "");
					valueParse = valueParse.replaceAll("\\,", ".");
					
					productBean.setPrice(Double.parseDouble(valueParse));
				}
				
				if (msg != null) {
					request.setAttribute("msg", msg);
					
				}else if (id == null || id.isEmpty() && productDao.validationName(name) && canInsert) {
					productDao.save(productBean);

				}else if (id != null && !id.isEmpty() && canInsert) {
					productDao.update(productBean);
				}

				if (!canInsert) {
					request.setAttribute("product", productBean);
				}

				RequestDispatcher view = request.getRequestDispatcher("/productregistration.jsp");
				request.setAttribute("products", productDao.list());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
