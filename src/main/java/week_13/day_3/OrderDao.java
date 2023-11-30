package week_13.day_3;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// To note: naming a table "Order" is bad practice as I
// discovered a little too late when working on the Order exercise
// However, this is how I would implement the OrderDao
public class OrderDao {
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Order")) {

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("id"),
                        rs.getInt("productId"),
                        rs.getInt("quantity"),
                        rs.getInt("customerDetails"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    // Date range wasn't listed as an attribute in Order...
    public List<Order> getOrdersByCustomerDetails(int customerDetails) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Order WHERE customerDetails = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerDetails);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order(
                            rs.getInt("id"),
                            rs.getInt("productId"),
                            rs.getInt("quantity"),
                            rs.getInt("customerDetails"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public Order getOrder(int id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Order WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Order(resultSet.getInt("id"),
                        resultSet.getInt("productId"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("customerDetails"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addOrder(Order order) {
        try (Connection connection = DBConnection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement psOrder = connection.prepareStatement("INSERT INTO Order (productId, quantity, customerDetails) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                psOrder.setInt(1, order.getProductId());
                psOrder.setInt(2, order.getQuantity());
                psOrder.setInt(3, order.getCustomerDetails());
                psOrder.executeUpdate();

                // Retrieve the generated order ID
                ResultSet generatedKeys = psOrder.getGeneratedKeys();
                int orderId = -1;
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                }

                // Check if the order was successfully inserted
                if (orderId != -1) {
                    // Update StockQuantity in Product table
                    try (PreparedStatement psProduct = connection.prepareStatement("UPDATE Product SET stockQuantity = stockQuantity - ? WHERE id = ?")) {
                        psProduct.setInt(1, order.getQuantity());
                        psProduct.setInt(2, order.getProductId());
                        psProduct.executeUpdate();
                    }

                    connection.commit();
                } else {
                    connection.rollback();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateOrder(Order order) {
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE Order SET productId = ?, quantity = ?, customerDetails = ? WHERE id = ?")) {
                ps.setInt(1, order.getProductId());
                ps.setInt(2, order.getQuantity());
                ps.setInt(3, order.getCustomerDetails());

                ps.setInt(4, order.getId());

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM Order WHERE id = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

