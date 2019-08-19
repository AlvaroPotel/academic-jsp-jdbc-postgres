package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.UserBean;
import connection.SingleConnection;

/**
 * @author alopespo
 *
 */
public class UserDao {

	private Connection connection;

	public UserDao() {
		connection = SingleConnection.getConnection();
	}

	public void saveUser(UserBean user) {

		try {
			String sql = "insert into users(login, password, name, phone, zip, street, neighborhood, city, states, ibge, picbase64, contentType, pdfBase64, contenttypepdf ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getZip());
			statement.setString(6, user.getStreet());
			statement.setString(7, user.getNeighborhood());
			statement.setString(8, user.getCity());
			statement.setString(9, user.getStates());
			statement.setString(10, user.getIbge());
			statement.setString(11, user.getPicBase64());
			statement.setString(12, user.getContentType());
			statement.setString(13, user.getPdfBase64());
			statement.setString(14, user.getContentTypePdf());
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

	public List<UserBean> list() throws Exception {
		List<UserBean> list = new ArrayList<UserBean>();

		String sql = "select * from users";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			UserBean beanJsp = new UserBean();

			beanJsp.setId(resultSet.getLong("id"));
			beanJsp.setLogin(resultSet.getString("login"));
			beanJsp.setPassword(resultSet.getString("password"));
			beanJsp.setName(resultSet.getString("name"));
			beanJsp.setPhone(resultSet.getString("phone"));
			beanJsp.setZip(resultSet.getString("zip"));
			beanJsp.setStreet(resultSet.getString("street"));
			beanJsp.setNeighborhood(resultSet.getString("neighborhood"));
			beanJsp.setCity(resultSet.getString("city"));
			beanJsp.setStates(resultSet.getString("states"));
			beanJsp.setIbge(resultSet.getString("ibge"));
			beanJsp.setPicBase64(resultSet.getString("picbase64"));
			beanJsp.setContentType(resultSet.getString("contentType"));
			beanJsp.setPdfBase64(resultSet.getString("pdfBase64"));
			beanJsp.setContentTypePdf(resultSet.getString("contenttypepdf"));

			list.add(beanJsp);

		}
		return list;
	}

	public void delete(String id) {

		try {
			String sql = "delete from users where id = '" + id + "'";
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

	public UserBean consult(String id) throws Exception {

		String sql = "select * from users where id = '" + id + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			UserBean beanJsp = new UserBean();

			beanJsp.setId(resultSet.getLong("id"));
			beanJsp.setLogin(resultSet.getString("login"));
			beanJsp.setPassword(resultSet.getString("password"));
			beanJsp.setName(resultSet.getString("name"));
			beanJsp.setPhone(resultSet.getString("phone"));
			beanJsp.setZip(resultSet.getString("zip"));
			beanJsp.setStreet(resultSet.getString("street"));
			beanJsp.setNeighborhood(resultSet.getString("neighborhood"));
			beanJsp.setCity(resultSet.getString("city"));
			beanJsp.setStates(resultSet.getString("states"));
			beanJsp.setIbge(resultSet.getString("ibge"));
			beanJsp.setPicBase64(resultSet.getString("picbase64"));
			beanJsp.setContentType(resultSet.getString("contentType"));
			beanJsp.setPdfBase64(resultSet.getString("pdfBase64"));
			beanJsp.setContentTypePdf(resultSet.getString("contenttypepdf"));

			return beanJsp;
		}

		return null;
	}

	public boolean validationLogin(String login) throws Exception {

		String sql = "select count(1) as qtd from users where login ='" + login + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public boolean validationPassword(String password) throws Exception {
		String sql = "select count(1) as qtd from users where password='" + password + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/* Return true */
		}
		return false;
	}

	public boolean validationUpdate(String login, String id) throws Exception {

		String sql = "select count(1) as qtd from users where login ='" + login + "' and id <> " + id;

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	/**
	 * @param user
	 */
	public void update(UserBean user) {

		try {
			String sql = "update users set login = ?, password = ?, name = ?, phone = ?, zip = ?, "
					+ "street = ?, neighborhood = ?, city = ?, states = ?, ibge = ? , picbase64 = ?, "
					+ "contentType = ?, pdfBase64 = ?, contenttypepdf = ? where id = "
					+ user.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getZip());
			statement.setString(6, user.getStreet());
			statement.setString(7, user.getNeighborhood());
			statement.setString(8, user.getCity());	
			statement.setString(9, user.getStates());
			statement.setString(10, user.getIbge());
			statement.setString(11, user.getPicBase64());
			statement.setString(12, user.getContentType());
			statement.setString(13, user.getPdfBase64());
			statement.setString(14, user.getContentTypePdf());
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
