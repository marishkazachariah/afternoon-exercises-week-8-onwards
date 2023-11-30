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

@WebServlet(urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {
    private final ProductDao productDao = new ProductDao();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestUrl = request.getRequestURI();
        String contextPath = request.getContextPath();
        String pathInfo = requestUrl.substring(contextPath.length() + "/products/".length());

        if (pathInfo.isEmpty()) {
            List<Product> products = productDao.getAllProducts();
            JSONArray jsonArray = new JSONArray();

            for (Product product : products) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", product.getName());
                jsonObject.put("name", product.getName());
                jsonObject.put("category", product.getCategory());
                jsonObject.put("price", product.getPrice());
                jsonObject.put("StockQuantity", product.getStockQuantity());
                jsonArray.put(jsonObject);
            }

            response.getOutputStream().println(jsonArray.toString());
        } else {
            Product product = productDao.getProduct(Integer.parseInt(pathInfo));

            if (product != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", product.getName());
                jsonObject.put("name", product.getName());
                jsonObject.put("category", product.getCategory());
                jsonObject.put("price", product.getPrice());
                jsonObject.put("StockQuantity", product.getStockQuantity());
                response.getOutputStream().println(jsonObject.toString());
            } else {
                response.getOutputStream().println("{}");
            }
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
            String name = jsonData.getString("name");
            String category = jsonData.getString("category");
            Double price = jsonData.getDouble("price");
            int stockQuantity = jsonData.getInt("StockQuantity");

            Product product = new Product(name, category, price, stockQuantity);
            productDao.addProduct(product);

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
            String name = jsonData.getString("name");
            String category = jsonData.getString("category");
            Double price = jsonData.getDouble("price");
            int stockQuantity = jsonData.getInt("StockQuantity");

            Product product = new Product(name, category, price, stockQuantity);
            productDao.updateProduct(product);

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
        String pathInfo = requestUrl.substring("/products/".length());

        if (pathInfo.isEmpty()) {
            response.getWriter().println("No person specified to delete");
        } else {
            productDao.deleteProduct(Integer.parseInt(pathInfo));
        }
    }
}
