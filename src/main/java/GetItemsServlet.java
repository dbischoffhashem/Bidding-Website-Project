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

@WebServlet("/GetItemsServlet")

public class GetItemsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cs336project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "ENTER MYSQL PASSWORD HERE";
    
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	 List<List<String>> resultData = new ArrayList<>();

    String selectedSubcategory = request.getParameter("subcategory");
    String selectedCategory = request.getParameter("category");

    try {   	
    	// Load the MySQL JDBC driver
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	// Establish a database connection
    	Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
    	PreparedStatement statement;
    	ResultSet resultSet;
    	
    	if (selectedSubcategory.equals("all")) {
    		 // SQL query to retrieve the 'inames' column values based on the selected category
    	String sql = "SELECT i.id, i.iname FROM item i INNER JOIN category c ON i.subcategory = c.subcategory WHERE c.cname = ?";
    	statement = connection.prepareStatement(sql);
    	statement.setString(1, selectedCategory); // Set the selected category as a parameter
    	resultSet = statement.executeQuery();
    	}
    		
    		
    		
    		
    	else {	
    		// SQL query to retrieve the 'inames' column values based on the selected category
    		String sql = "SELECT id, iname FROM item WHERE subcategory = ?";
    	statement = connection.prepareStatement(sql);
    	statement.setString(1, selectedSubcategory); // Set the selected category as a parameter
    	resultSet = statement.executeQuery();
    	System.out.println("Selected Subcategory: " + selectedSubcategory);
    	System.out.println("SQL Query: " + sql);
    	}

    	// Populate the 'inames' list with data from the databases
        while (resultSet.next()) {
        	String column1Value = resultSet.getString("id");
            String column2Value = resultSet.getString("iname");
            
            // Create a list to store the two values and add it to the resultData
            List<String> row = new ArrayList<>();
            row.add(column1Value);
            row.add(column2Value);
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

    request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}

