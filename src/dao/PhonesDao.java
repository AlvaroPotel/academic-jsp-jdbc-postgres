package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.PhoneBean;
import connection.SingleConnection;

public class PhonesDao {

	private Connection connection;

	public PhonesDao() {
		connection = SingleConnection.getConnection();
	}

	public void save(PhoneBean phoneBean) {

		try {
			String sql = "insert into phones(numbers, typess, users) values (?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, phoneBean.getNumbers());
			statement.setString(2, phoneBean.getTypess());
			statement.setLong(3, phoneBean.getUsers());

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

	public List<PhoneBean> list(Long user) throws Exception {
		List<PhoneBean> list = new ArrayList<PhoneBean>();

		String sql = "select * from phones where users = " + user;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			PhoneBean phoneBean = new PhoneBean();

			phoneBean.setId(resultSet.getLong("id"));
			phoneBean.setNumbers(resultSet.getString("numbers"));
			phoneBean.setTypess(resultSet.getString("typess"));
			phoneBean.setUsers(resultSet.getLong("users"));

			list.add(phoneBean);

		}
		return list;
	}

	public void delete(String id) {

		try {
			String sql = "delete from phones where id = '" + id + "'";
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

}
