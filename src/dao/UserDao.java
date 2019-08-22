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
			String sql = "insert into users(login, password, name, phone, zip, street, "
					+ "neighborhood, city, states, ibge, picbase64, contentType, pdfBase64, contenttypepdf, miniPicBase64, active, gender, perfil ) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
			statement.setString(15, user.getMiniPicBase64());
			statement.setBoolean(16, user.isActive());
			statement.setString(17, user.getGender());
			statement.setString(18, user.getPerfil());
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
	
	public List<UserBean> listByConsult(String consultDescri) throws Exception {
		String sql = "select * from users where login <> 'q' and name like '%"+consultDescri+"%' ";
		return list(sql);
	}
		
	public List<UserBean> listAll() throws Exception {
		String sql = "select * from users where login <> 'q'";
		return list(sql);
	}

	private List<UserBean> list(String sql) throws SQLException {
		List<UserBean> list = new ArrayList<UserBean>();
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
			beanJsp.setContentType(resultSet.getString("contentType"));
			beanJsp.setMiniPicBase64(resultSet.getString("minipicbase64"));
			beanJsp.setPdfBase64(resultSet.getString("pdfBase64"));
			beanJsp.setContentTypePdf(resultSet.getString("contenttypepdf"));
			beanJsp.setActive(resultSet.getBoolean("active"));
			beanJsp.setGender(resultSet.getString("gender"));
			beanJsp.setPerfil(resultSet.getString("perfil"));

			list.add(beanJsp);
		}
		return list;
	}

	public void delete(String id) {

		try {
			String sql = "delete from users where id = '" + id + "' and login <> 'q'";
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

		String sql = "select * from users where id = '" + id + "' and login <> 'q' ";

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
			beanJsp.setMiniPicBase64(resultSet.getString("minipicbase64"));
			beanJsp.setContentType(resultSet.getString("contentType"));
			beanJsp.setPdfBase64(resultSet.getString("pdfBase64"));
			beanJsp.setContentTypePdf(resultSet.getString("contenttypepdf"));
			beanJsp.setActive(resultSet.getBoolean("active"));
			beanJsp.setGender(resultSet.getString("gender"));
			beanJsp.setPerfil(resultSet.getString("perfil"));
			
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
			StringBuilder sql = new StringBuilder();

			sql.append(" update users set login = ?, password = ?, name = ?, phone = ?, zip = ? ");
			sql.append(", street = ?, neighborhood = ?, city = ?, states = ?, ibge = ?, active = ?, gender = ?, perfil = ? ");

			if (user.isUpdatePic()) {
				sql.append(", picbase64 = ?, contentType = ? ");
			}

			if (user.isUpdatePdf()) {
				sql.append(", pdfbase64 = ?, contenttypepdf = ? ");
			}

			if (user.isUpdatePic()) {
				sql.append(", minipicbase64 = ? ");
			}

			sql.append(" where id = " + user.getId());

			int position =1;
			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(position++, user.getLogin());
			statement.setString(position++, user.getPassword());
			statement.setString(position++, user.getName());
			statement.setString(position++, user.getPhone());
			statement.setString(position++, user.getZip());
			statement.setString(position++, user.getStreet());
			statement.setString(position++, user.getNeighborhood());
			statement.setString(position++, user.getCity());
			statement.setString(position++, user.getStates());
			statement.setString(position++, user.getIbge());
			statement.setBoolean(position++, user.isActive());
			statement.setString(position++, user.getGender());
			statement.setString(position++, user.getPerfil());

			if (user.isUpdatePic()) {
				statement.setString(position++, user.getPicBase64());
				statement.setString(position++, user.getContentType());
				statement.setString(position++, user.getMiniPicBase64());
			} 
			if (user.isUpdatePdf()) {
				statement.setString(position++, user.getPdfBase64());
				statement.setString(position++, user.getContentTypePdf());
			}
			
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
