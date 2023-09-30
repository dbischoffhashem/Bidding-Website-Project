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


@WebServlet("/SpecificationsForListing")
public class SpecificationsForListing extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cs336project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "ENTER MYSQL PASSWORD HERE";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> inames = new ArrayList<>();    
        String selectedSubcategory = request.getParameter("subcategory"); // Get the selected category
        //String selectedCategory = request.getParameter("category"); // Get the selected category
        
        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish a database connection
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
      
           // SQL query to retrieve the 'inames' column values based on the selected category
           String sql = "SELECT spec_name FROM Specifications s, Category c WHERE c.subcategory = s.subcategory AND c.subcategory = ?";
           	
                
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1, selectedSubcategory); // Set the selected category as a parameter
           ResultSet resultSet = statement.executeQuery();
            
           System.out.println("Selected Subcategory: " + selectedSubcategory);
           System.out.println("SQL Query: " + sql);
            	
            // Populate the 'inames' list with data from the databases
            while (resultSet.next()) {
                String iname = resultSet.getString("spec_name");
                inames.add(iname);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("inames", inames);

        // Forward the request to your JSP page
        request.getRequestDispatcher("/CreateListing.jsp").forward(request, response);
    }
}
