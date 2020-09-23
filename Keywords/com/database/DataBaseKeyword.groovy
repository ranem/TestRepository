package com.database
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.SQLException
import java.sql.Statement

import com.kms.katalon.core.annotation.Keyword
import com.sun.jmx.snmp.Timestamp

import internal.GlobalVariable

import org.apache.poi.ss.usermodel.*

import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.postgresql.jdbc.PgConnection
import org.postgresql.jdbc.PgResultSet
import org.postgresql.jdbc.PgStatement


public class DataBaseKeyword {


	@Keyword
	def void ExportDataFromDB(String sqlrequest,String sheetname) {
		String jdbcURL = GlobalVariable.DBConnector+":"+GlobalVariable.DBSql+"://"+GlobalVariable.host+":"+GlobalVariable.port+"/"+GlobalVariable.dbname;
		String username = GlobalVariable.username;
		String password = GlobalVariable.password;

		String excelFilePath = GlobalVariable.ExcelPath;

		try {
			PgConnection connection = DriverManager.getConnection(jdbcURL, username, password)
			String request = sqlrequest;

			Statement statement = connection.createStatement();

			ResultSet result = statement.executeQuery(sqlrequest);

			XSSFWorkbook workbook = new XSSFWorkbook();

			XSSFSheet sheet = workbook.createSheet(sheetname);


			writeHeaderLine(sheet);

			writeDataLines(result, workbook, sheet);

			FileOutputStream outputStream = new FileOutputStream(excelFilePath);
			workbook.write(outputStream);
			workbook.close();

			statement.close();
		}

		catch (SQLException e) {
			System.out.println("Datababse error:");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("File IO error:");
			e.printStackTrace();
		}
	}

	private void writeHeaderLine(XSSFSheet sheet) {

		Row headerRow = sheet.createRow(0);

		Cell headerCell = headerRow.createCell(0);
		headerCell.setCellValue("TabName");

		headerCell = headerRow.createCell(1);
		headerCell.setCellValue("UserId");

		headerCell = headerRow.createCell(2);
		headerCell.setCellValue("UserFirstName");

		headerCell = headerRow.createCell(3);
		headerCell.setCellValue("UsrSecondName");
	}

	private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
			XSSFSheet sheet) throws SQLException {
		int rowCount = 1;

		while (result.next()) {
			String userId = result.getInt("UserId");
			String userFirstName = result.getString("UserFirstName");
			String userSecondName = result.getString("UserSecondName");

			Row row = sheet.createRow(rowCount++);

			int columnCount = 1;

			Cell cell = row.createCell(columnCount++);
			cell.setCellValue(userId);

			cell = row.createCell(columnCount++);
			cell.setCellValue(userFirstName);

			cell = row.createCell(columnCount++);


			cell = row.createCell(columnCount++);
			cell.setCellValue(userSecondName);
		}
	}
}


