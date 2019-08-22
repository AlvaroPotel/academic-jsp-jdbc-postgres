package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CategoryBean;
import beans.ProductBean;
import connection.SingleConnection;

public class ProductDao {

	private Connection connection;

	public ProductDao() {
		connection = SingleConnection.getConnection();
	}

	public void save(ProductBean product) {

		try {
			String sql = "insert into products(name, quant, price, category_id) values (?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, product.getName());
			statement.setDouble(2, product.getQuant());
			statement.setDouble(3, product.getPrice());
			statement.setLong(4, product.getCategory_id());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public List<ProductBean> list() throws Exception {
		List<ProductBean> list = new ArrayList<ProductBean>();

		String sql = "select * from products";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			ProductBean productBean = new ProductBean();

			productBean.setId(resultSet.getLong("id"));
			productBean.setName(resultSet.getString("name"));
			productBean.setQuant(resultSet.getDouble("quant"));
			productBean.setPrice(resultSet.getDouble("price"));
			productBean.setCategory_id(resultSet.getLong("category_id"));

			list.add(productBean);

		}
		return list;
	}
	
	public List<CategoryBean> listCategory() throws Exception {
		List<CategoryBean> list = new ArrayList<CategoryBean>();
	
		String sql = "select * from category";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			CategoryBean categoryBean = new CategoryBean();

			categoryBean.setId(resultSet.getLong("id"));
			categoryBean.setName(resultSet.getString("name"));

			list.add(categoryBean);
		}
		
		return list;
	}

	public void delete(String id) {

		try {
			String sql = "delete from products where id = '" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public ProductBean consult(String id) throws Exception {

		String sql = "select * from products where id = '" + id + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			ProductBean productBean = new ProductBean();

			productBean.setId(resultSet.getLong("id"));
			productBean.setName(resultSet.getString("name"));
			productBean.setQuant(resultSet.getDouble("quant"));
			productBean.setPrice(resultSet.getDouble("price"));
			productBean.setCategory_id(resultSet.getLong("category_id"));

			return productBean;
		}

		return null;
	}

	public boolean validationName(String name) throws Exception {

		String sql = "select count(1) as qtd from products where name ='" + name + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public boolean validationUpdate(String login, String id) throws Exception {

		String sql = "select count(1) as qtd from products where login ='" + login + "' and id <> " + id;

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public void update(ProductBean productBean) {

		try {
			String sql = "update products set name = ?, quant = ?, price = ?, category_id = ? where id ='" + productBean.getId() + "'";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, productBean.getName());
			statement.setDouble(2, productBean.getQuant());
			statement.setDouble(3, productBean.getPrice());
			statement.setLong(4, productBean.getCategory_id());
			statement.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
