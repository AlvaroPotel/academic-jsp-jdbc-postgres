package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author alopespo
 *
 */
public class SingleConnection {
	
	private static String url ="jdbc:postgresql://localhost:5432/academy-jsp?autoReconnect=true";
	private static String password = "123";
	private static String user = "postgres";
	private static Connection connection = null;
	
	static {
		connect();
	}

	public SingleConnection() {
		connect();
	}
	
	private static void connect() {
		try {

			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("DataBase OK");
			}

		} catch (Exception e) {
			throw new RuntimeException("Error connecting to database!");
		}
	}

	public static Connection getConnection() {
		return connection;
	}
	
}
