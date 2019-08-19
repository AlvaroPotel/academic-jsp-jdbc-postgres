package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class LoginDao {
	
	private Connection connection;
	
	public LoginDao() {
		connection = SingleConnection.getConnection();
	}

	public boolean validatorLogin(String login, String password) throws Exception {
		
		String sql = "select * from users where login = '"+login+"' and password = '"+password + "'"; 
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			return true;
		}else {
			return false;
		}
		
	}
	
}
