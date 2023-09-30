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


@WebServlet("/ShowMyListings")
public class ShowMyListings extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cs336project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "ENTER MYSQL PASSWORD HERE";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> inames = new ArrayList<>();   
        String username = request.getParameter("username"); // Get the selected category

        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish a database connection
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            PreparedStatement statement;
            ResultSet resultSet;
            
            	 // SQL query to retrieve the 'inames' column values based on the selected category
                String sql = "SELECT i.iname FROM item i, user u WHERE u.username = ? AND i.seller_id = u.id";
                
                statement = connection.prepareStatement(sql);
                statement.setString(1, username); // Set the selected category as a parameter
                resultSet = statement.executeQuery();
               
          
            // Populate the 'inames' list with data from the databases
            while (resultSet.next()) {
                String iname = resultSet.getString("iname");
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
        request.getRequestDispatcher("/MyListings.jsp").forward(request, response);
    }
}

