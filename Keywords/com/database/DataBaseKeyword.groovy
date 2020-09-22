package com.database
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.Statement

import com.kms.katalon.core.annotation.Keyword
import org.postgresql.jdbc.PgConnection
import org.postgresql.jdbc.PgResultSet
import org.postgresql.jdbc.PgStatement

public class DataBaseKeyword {




	private static PgConnection connection = null;

	/**
	 * Open and return a connection to database
	 * @param dataFile absolute file path
	 * @return an instance of java.sql.Connection
	 */

	//Establishing a connection to the DataBase

	@Keyword

	def connectDB(String url, String dbname, String port, String username, String password){

		//Load driver class for your specific database type

		String conn = "jdbc:postgresql://" + url + ":" + port + "/" + dbname

		//Class.forName("org.sqlite.JDBC")

		//String connectionString = "jdbc:sqlite:" + dataFile

		if(connection != null && !connection.isClosed()){

			connection.close()

		}

		connection = DriverManager.getConnection(conn, username, password)

		return connection

	}

	/**
	 * execute a SQL query on database
	 * @param queryString SQL query string
	 * @return a reference to returned data collection, an instance of java.sql.ResultSet
	 */

	//Executing the constructed Query and Saving results in resultset

	@Keyword

	def executeQuery(String queryString) {

		Statement stm = connection.createStatement()

		ResultSet resultSet = stm.executeQuery(queryString)

		ResultSetMetaData metadata = resultSet.getMetaData()
		int columnCount = metadata.getColumnCount()
		List<List<String>> rowList = new LinkedList<List<String>>()

		while (resultSet.next()) {
			List<String> row = new LinkedList<>()

			for(int i = 1; i <=columnCount; i++) {
				Object value = resultSet.getObject(i)
				row.add(value)
			}

			rowList.add(row)
		}

		for(List<String> row: rowList) {
			for(String data: row) {
				System.out.print(data + " ")
			}
			System.out.println()
		}

		return rowList

	}

	//Closing the connection

	@Keyword

	def closeDatabaseConnection() {

		if(connection != null && !connection.isClosed()){

			connection.close()

		}

		connection = null

	}

	/**
	 * Execute non-query (usually INSERT/UPDATE/DELETE/COUNT/SUM...) on database
	 * @param queryString a SQL statement
	 * @return single value result of SQL statement
	 */

	@Keyword

	def execute(String queryString) {

		Statement stm = connection.createStatement()

		boolean result = stm.execute(queryString)

		return result

	}

}
