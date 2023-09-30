import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PlacebidServlet")


public class PlacebidServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cs336project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Taylorswift1322!";
    
    private void placeAutomaticBids(Connection connection, String itemId, double currentBid, double incrementBy) throws SQLException {
        PreparedStatement automaticBidsStatement = connection.prepareStatement(
            "SELECT bidder_id FROM automaticbids WHERE item_id = ?");
        automaticBidsStatement.setString(1, itemId);
        ResultSet automaticBidsResultSet = automaticBidsStatement.executeQuery();

        while (automaticBidsResultSet.next()) {
            String automaticBidderId = automaticBidsResultSet.getString("bidder_id");

            // Calculate the higher bid for automatic bidder
            double automaticBidAmount = currentBid + incrementBy;

            // Insert the automatic bid into the bids table
            PreparedStatement insertAutomaticBidStatement = connection.prepareStatement(
                "INSERT INTO bids (item_id, max_bid, bidder_id) VALUES (?, ?, ?)");
            insertAutomaticBidStatement.setString(1, itemId);
            insertAutomaticBidStatement.setDouble(2, automaticBidAmount);
            insertAutomaticBidStatement.setString(3, automaticBidderId);
            insertAutomaticBidStatement.executeUpdate();

            // Update the highest bid in the item table
            PreparedStatement updateItemStatement = connection.prepareStatement(
                "UPDATE item SET highest_bid = ? WHERE id = ?");
            updateItemStatement.setDouble(1, automaticBidAmount);
            updateItemStatement.setString(2, itemId);
            updateItemStatement.executeUpdate();

            // Close resources
            insertAutomaticBidStatement.close();
            updateItemStatement.close();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the bid amount, item ID, and username from the form submission
        String bidAmountStr = request.getParameter("bidAmount");
        String itemId = request.getParameter("itemId");
        String username = request.getParameter("username");
        
        System.out.println("bidAmountStr: " + bidAmountStr);
        System.out.println("itemId: " + itemId);
        System.out.println("username: " + username);

        // Convert bid amount to a double
        double bidAmount = Double.parseDouble(bidAmountStr);
        System.out.println("bidAmount: " + bidAmount);

        // Connect to the database and perform the bid logic
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a database connection
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Retrieve item details to compare the bid amount
            PreparedStatement itemStatement = connection.prepareStatement("SELECT starting_price, highest_bid, increment_by FROM item WHERE id = ?");
            itemStatement.setString(1, itemId);
            ResultSet itemResultSet = itemStatement.executeQuery();

            if (itemResultSet.next()) {
                double startingPrice = itemResultSet.getDouble("starting_price");
                double highestBid = itemResultSet.getDouble("highest_bid");
                double incrementBy = itemResultSet.getDouble("increment_by");


                // Check if the bid amount is higher than the starting price or the current highest bid
                if ((highestBid == 0 && bidAmount >= startingPrice) || (highestBid > 0 && bidAmount > highestBid + incrementBy)){
                    // Insert the bid into the bids table
                    PreparedStatement insertStatement = connection.prepareStatement(
                        "INSERT INTO bids (item_id, max_bid, bidder_id) VALUES (?, ?, (SELECT id FROM user WHERE username = ?))");
                    insertStatement.setString(1, itemId);
                    insertStatement.setDouble(2, bidAmount);
                    insertStatement.setString(3, username);
                    insertStatement.executeUpdate();

                    // Update the highest bid in the item table
                    PreparedStatement updateStatement = connection.prepareStatement(
                        "UPDATE item SET highest_bid = ? WHERE id = ?");
                    updateStatement.setDouble(1, bidAmount);
                    updateStatement.setString(2, itemId);
                    updateStatement.executeUpdate();
                    
                 // Place automatic bids for registered automatic bidders
                    placeAutomaticBids(connection, itemId, bidAmount, incrementBy);

                    // Close resources
                    insertStatement.close();
                    updateStatement.close();

                    // Redirect to the success page
                    response.sendRedirect("BidSuccess.jsp");
                } else {
                    // Close resources
                    itemResultSet.close();
                    itemStatement.close();
                    connection.close();
                    
                    // Redirect to the error page
                    response.sendRedirect("BidError.jsp");
                }
            }

            // Close resources
            itemResultSet.close();
            itemStatement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
            // Redirect to the error page in case of exception
            response.sendRedirect("BidError.jsp");
        }
    }
}


