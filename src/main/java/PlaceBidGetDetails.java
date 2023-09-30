import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PlaceBidGetDetails")

public class PlaceBidGetDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cs336project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Taylorswift1322!";
    
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	 List<List<String>> resultData = new ArrayList<>();

    //String iname = request.getParameter("iname");
    String id = request.getParameter("item_id");
    String username = request.getParameter("username");

    //String startingPrice = request.getParameter("starting_price");
    //String highestBid = request.getParameter("highest_bid");
    //String selectedSubcategory = request.getParameter("subcategory");



    try {   	
    	// Load the MySQL JDBC driver
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	// Establish a database connection
    	Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
    	PreparedStatement statement;
    	ResultSet resultSet;
    	
    	// SQL query to retrieve the 'inames' column values based on the selected category
    	String sql = "SELECT i.iname, i.id, i.starting_price, i.highest_bid, i.subcategory FROM item i WHERE i.id = ?";
    	statement = connection.prepareStatement(sql);
    	statement.setString(1, id); // Set the selected category as a parameter
    	resultSet = statement.executeQuery();
    	
    	System.out.println("id: " + id);
    	
    	// Populate the 'inames' list with data from the databases
        while (resultSet.next()) {
            String column1Value = resultSet.getString("iname");
        	String column2Value = resultSet.getString("id");
            String column3Value = resultSet.getString("starting_price");
            String column4Value = resultSet.getString("highest_bid");
            String column5Value = resultSet.getString("subcategory");

        	System.out.println("iname: " + column1Value);
        	System.out.println("iname: " + column2Value);
        	System.out.println("iname: " + column3Value);
        	System.out.println("iname: " + column4Value);
        	System.out.println("iname: " + column5Value);

            // Create a list to store the two values and add it to the resultData
            List<String> row = new ArrayList<>();
            row.add(column1Value);
            row.add(column2Value);
            row.add(column3Value);
            row.add(column4Value);
            row.add(column5Value);

            resultData.add(row);
            System.out.println (column1Value);
            System.out.println (column2Value);

        }
    	
    	// Close resources
    	resultSet.close();
    	statement.close();
    	connection.close();

    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }

    // Set attributes and forward to JSP
    request.setAttribute("resultData", resultData);


    request.getRequestDispatcher("/Placebid.jsp?username="+ username).forward(request, response);
    }
}
