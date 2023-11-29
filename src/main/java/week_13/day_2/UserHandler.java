package week_13.day_2;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

// Task 3
public class UserHandler implements HttpHandler {
    private final UserManager userManager = new UserManager();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();

        switch (requestMethod) {
            case "GET":
                handleGetRequest(exchange);
                break;
            case "POST":
                handlePostRequest(exchange);
                break;
            case "PUT":
                handlePutRequest(exchange);
                break;
            case "DELETE":
                handleDeleteRequest(exchange);
                break;
            default:
                sendResponse(exchange, 405, "Method Not Allowed");
        }

        /*
        // Task 1
        String response = "This is your REST server!";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
         */
    }

    private void handleGetRequest(HttpExchange exchange) throws IOException {
        List<User> users = userManager.getAllUsers();
        String jsonResponse = convertUsersToJson(users);
        sendResponse(exchange, 200, jsonResponse);
    }

    private void handlePostRequest(HttpExchange exchange) throws IOException {
        if (exchange.getRequestHeaders().getFirst("Content-Type").equals("application/json")) {
            String requestBody = new String(exchange.getRequestBody().readAllBytes());
            System.out.println("req body: "+ requestBody);
            User newUser = convertJsonToUser(requestBody);
            userManager.addUser(newUser);
            sendResponse(exchange, 201, "User created successfully");
        } else {
            sendResponse(exchange, 400, "Bad Request - JSON expected");
        }
    }

    private void handlePutRequest(HttpExchange exchange) throws IOException {
        if (exchange.getRequestHeaders().getFirst("Content-Type").equals("application/json")) {

            String requestBody = new String(exchange.getRequestBody().readAllBytes());
            User updatedUser = convertJsonToUser(requestBody);

            userManager.updateUser(updatedUser);
            sendResponse(exchange, 200, "User updated successfully");
        } else {
            sendResponse(exchange, 400, "Bad Request - JSON expected");
        }
    }

    private void handleDeleteRequest(HttpExchange exchange) throws IOException {
        String userId = exchange.getRequestURI().getPath().split("/")[2];
        userManager.deleteUser(Integer.parseInt(userId));
        sendResponse(exchange, 200, "User deleted successfully");
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    // You would need to implement these methods for converting between User objects and JSON
    private String convertUsersToJson(List<User> users) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(users);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    private User convertJsonToUser(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, User.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

