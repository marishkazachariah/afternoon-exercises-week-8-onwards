package week_13.day_3;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/orders/*")
public class OrderServlet extends HttpServlet {
    private final OrderDao orderDao = new OrderDao();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestUrl = request.getRequestURI();
        String contextPath = request.getContextPath();
        String pathInfo = requestUrl.substring(contextPath.length() + "/products/".length());

        if (pathInfo.isEmpty()) {
            // No specific path, return all products
            List<Order> orders = orderDao.getAllOrders();
            JSONArray jsonArray = new JSONArray();

            for (Order order : orders) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", order.getId());
                jsonObject.put("productId", order.getProductId());
                jsonObject.put("quantity", order.getQuantity());
                jsonObject.put("customerDetails", order.getCustomerDetails());
                jsonArray.put(jsonObject);
            }

            response.getOutputStream().println(jsonArray.toString());
        } else if (pathInfo.startsWith("customerDetails/")) {
            String[] parts = pathInfo.split("/");
            if (parts.length == 2) {
                String customerDetails = parts[1];

                List<Order> orders = orderDao.getOrdersByCustomerDetails(Integer.parseInt(customerDetails));
                JSONArray jsonArray = new JSONArray();

                for (Order order : orders) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", order.getId());
                    jsonObject.put("productId", order.getProductId());
                    jsonObject.put("quantity", order.getQuantity());
                    jsonObject.put("customerDetails", order.getCustomerDetails());
                    jsonArray.put(jsonObject);
                }

                response.getOutputStream().println(jsonArray.toString());
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid path format for category and price range filter");
            }
        } else if (pathInfo.startsWith("id/")) {
            // Path matches product by id
            String[] parts = pathInfo.split("/");
            if (parts.length == 2) {
                int orderId = Integer.parseInt(parts[1]);
                Order order = orderDao.getOrder(orderId);

                if (order != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", order.getId());
                    jsonObject.put("productId", order.getProductId());
                    jsonObject.put("quantity", order.getQuantity());
                    jsonObject.put("customerDetails", order.getCustomerDetails());
                    response.getOutputStream().println(jsonObject.toString());
                } else {
                    response.getOutputStream().println("{}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid path format for product by id");
            }
        } else {
            // Path does not match any known pattern
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid path");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Read the raw JSON data from the request body
            StringBuilder requestBody = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }

            // Parse the JSON data
            JSONObject jsonData = new JSONObject(requestBody.toString());

            // Extract values from the JSON object
            int productId = jsonData.getInt("productId");
            int quantity = jsonData.getInt("quantity");
            int customerDetails = jsonData.getInt("customerDetails");

            Order order = new Order(productId, quantity, customerDetails);
            orderDao.addOrder(order);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Product created successfully");
        } catch (JSONException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid JSON format");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Read the raw JSON data from the request body
            StringBuilder requestBody = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }

            // Parse the JSON data
            JSONObject jsonData = new JSONObject(requestBody.toString());

            // Extract values from the JSON object
            int productId = jsonData.getInt("productId");
            int quantity = jsonData.getInt("quantity");
            int customerDetails = jsonData.getInt("customerDetails");

            Order order = new Order(productId, quantity, customerDetails);
            orderDao.updateOrder(order);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Person edited successfully");
        } catch (JSONException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid JSON format");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error");
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestUrl = request.getRequestURI();
        String pathInfo = requestUrl.substring("/orders/".length());

        if (pathInfo.isEmpty()) {
            response.getWriter().println("No product specified to delete");
        } else {
            orderDao.deleteOrder(Integer.parseInt(pathInfo));
        }
    }
}