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

@WebServlet("/SummaryServlet")
public class SummaryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cs336project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "ENTER MYSQL PASSWORD HERE";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> reportData = new ArrayList<>();

        String reportType = request.getParameter("reportType");

        try {   
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a database connection
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            
            if (reportType.equals("Total Earnings")) {
                String sql = "SELECT SUM(price) AS total_earnings FROM sales";
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                
                if (resultSet.next()) {
                    double totalEarnings = resultSet.getDouble("total_earnings");
                    reportData.add("Total Earnings: $" + totalEarnings);
                }
            } else if (reportType.equals("Earnings Per Item")) {
                String sql = "SELECT item_id, SUM(price) AS total_earnings FROM sales GROUP BY item_id";
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                
                while (resultSet.next()) {
                    int itemId = resultSet.getInt("item_id");
                    double earnings = resultSet.getDouble("total_earnings");
                    reportData.add("Earnings for Item ID " + itemId + ": $" + earnings);
                }
            } else if (reportType.equals("Earnings Per Item Type")) {
                String sql = "SELECT item_type, SUM(price) AS total_earnings FROM sales GROUP BY item_type";
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                
                while (resultSet.next()) {
                    String itemType = resultSet.getString("item_type");
                    double earnings = resultSet.getDouble("total_earnings");
                    reportData.add("Earnings for Item Type " + itemType + ": $" + earnings);
                }
            }
            
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // Set attributes and forward to JSP
        request.setAttribute("reportData", reportData);

        request.getRequestDispatcher("/report.jsp").forward(request, response);
    }
}

