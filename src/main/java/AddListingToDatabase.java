import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddListingToDatabase")
public class AddListingToDatabase extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cs336project";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "ENTER MYSQL PASSWORD HERE";
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
// Retrieve form data
String username = request.getParameter("username");
String subcategory = request.getParameter("subcategory");
String iname = request.getParameter("iname");
String incrementBy = request.getParameter("increment_by");
//print statements
System.out.println("username " + username);
System.out.println("subcategory " + subcategory);
System.out.println("iname " + iname);
System.out.println("incrementBy " + incrementBy);
//System.out.println("endDateTime " + endDateTime);
// Connect to the database
try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
// Insert into item table
	 String insertItemSql = "INSERT INTO item (seller_id, iname, subcategory, increment_by, end_date_time) VALUES (?, ?, ?, ?, ?)";
	 PreparedStatement itemStatement = connection.prepareStatement(insertItemSql);
	 itemStatement.setInt(1, getUserIdByUsername(connection, username));
	 itemStatement.setString(2, iname);
	 itemStatement.setString(3, subcategory);
	 itemStatement.setBigDecimal(4, new BigDecimal(incrementBy)); // Assuming increment_by is decimal(10,2)
	
	 String endDateTime = request.getParameter("end_date_time");
String formattedEndDateTime = endDateTime.replace("T", " ") + ":00"; // Add seconds
itemStatement.setTimestamp(5, Timestamp.valueOf(formattedEndDateTime));
	 //itemStatement.setTimestamp(5, Timestamp.valueOf(endDateTime.replace("T", " "))); // Convert datetime-local to SQL timestamp
	 itemStatement.executeUpdate();
	 itemStatement.close();
//print statement
System.out.println("insertItemSql" + insertItemSql);
// Get the generated item_id
int itemId = getGeneratedItemId(connection);
// Insert into itemSpecifications table
Enumeration<String> parameterNames = request.getParameterNames();
while (parameterNames.hasMoreElements()) {
String paramName = parameterNames.nextElement();
if (!paramName.equals("username") && !paramName.equals("subcategory") && !paramName.equals("iname") && !paramName.equals("increment_by") && !paramName.equals("end_date_time")) {
	
	System.out.println("paramName" + paramName);

	
	int specId = getSpecIdBySpecName(connection, subcategory, paramName);
	String value = request.getParameter(paramName);
	System.out.println("itemId " + itemId);
	System.out.println("specId " + specId);
	String insertItemSpecSql = "INSERT INTO ItemSpecifications (item_id, spec_id, value) VALUES (?, ?, ?)";
	PreparedStatement itemSpecStatement = connection.prepareStatement(insertItemSpecSql);
	itemSpecStatement.setInt(1, itemId);
	itemSpecStatement.setInt(2, specId);
	itemSpecStatement.setString(3, value);
	itemSpecStatement.executeUpdate();
	itemSpecStatement.close();
	}
}
// Redirect to a success page or perform further actions
response.sendRedirect("ListingSuccessPage.jsp");
} catch (SQLException e) {
e.printStackTrace();
// Handle database errors or display an error message
response.sendRedirect("ListingErrorPage.jsp");
}
}
private int getUserIdByUsername(Connection connection, String username) throws SQLException {
String query = "SELECT id FROM User WHERE username = ?";
try (PreparedStatement statement = connection.prepareStatement(query)) {
statement.setString(1, username);
try (ResultSet resultSet = statement.executeQuery()) {
if (resultSet.next()) {
return resultSet.getInt("id");
}
}
}
return -1; // Return an appropriate default value or handle the case where no user is found
}
private int getGeneratedItemId(Connection connection) throws SQLException {
String query = "SELECT LAST_INSERT_ID() AS last_id";
try (PreparedStatement statement = connection.prepareStatement(query);
ResultSet resultSet = statement.executeQuery()) {
if (resultSet.next()) {
return resultSet.getInt("last_id");
}
}
return -1; // Return an appropriate default value or handle the case where no ID is found
}
private int getSpecIdBySpecName(Connection connection, String subcategory, String specName) throws SQLException {
String query = "SELECT spec_id FROM Specifications WHERE subcategory = ? AND spec_name = ?";
try (PreparedStatement statement = connection.prepareStatement(query)) {
statement.setString(1, subcategory);
statement.setString(2, specName);
try (ResultSet resultSet = statement.executeQuery()) {
if (resultSet.next()) {
return resultSet.getInt("spec_id");
}
}
}
return -1; // Return an appropriate default value or handle the case where no spec_id is found
}
}



