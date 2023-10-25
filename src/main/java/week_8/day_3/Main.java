package week_8.day_3;

import java.io.*;
import java.util.*;

public class Main {
    public static final String CSV_FILE_PATH = "src/main/java/week_8/day_3/users.csv";

    // Exercise 1
    public static void writeUsersToCsvFile() {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User("Tom", 23, "tom@mail.com", "3847373733", "34 Wood St."),
                new User("Mary", 12, "mary@test.com", "533343233", "106 Test Str."),
                new User("Joseph", 3, "jo@ether.de", "634344534", "3 Fifth Ave."),
                new User("Sam", 66, "sam@read.com", "9572994454", "1233 Pool St.")
        ));
        String filePath = CSV_FILE_PATH;

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.append("Name, Age, Email, Phone Number, Address\n");
            for (User user : users) {
                fileWriter.append(user.getName())
                        .append(", ")
                        .append(String.valueOf(user.getAge()))
                        .append(", ")
                        .append(user.getEmail())
                        .append(", ")
                        .append(user.getPhoneNumber())
                        .append(", ")
                        .append(user.getAddress())
                        .append("\n");
            }
            System.out.println("printed CSV file of user data");
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    // Exercise 2
    public static void readUserCsvFile() {
        try (FileReader fileReader = new FileReader(CSV_FILE_PATH)) {
            StringBuilder csvStringBuilder = new StringBuilder();
            int character;
            while ((character = fileReader.read()) != -1) {
                csvStringBuilder.append((char) character);
            }
            System.out.println("User Data\n" + csvStringBuilder);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Exercise 3
    public static void appendUserCsvFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add new user's name");
        String name = scanner.nextLine();
        System.out.println("Add new user's age");
        String age = scanner.nextLine();
        System.out.println("Add new user's email");
        String email = scanner.nextLine();
        System.out.println("Add new user's phone number");
        String phoneNumber = scanner.nextLine();
        System.out.println("Add new user's address");
        String address = scanner.nextLine();

        String newUserInput = "\n" + name + ", " + age + ", " + email + ", " + phoneNumber + ", " + address;

        try (FileWriter writer = new FileWriter(CSV_FILE_PATH, true)) {
            writer.write(newUserInput);
            System.out.println("Appended new user to users.csv successfully!");
        } catch (IOException e) {
            System.err.println("Error adding new user: " + e.getMessage());
        }

        readUserCsvFile();
        scanner.close();
    }

    // Exercise 4
    public static void searchUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter search query (either name, email, or phone number): ");
        String query = scanner.nextLine().toLowerCase();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            String header = bufferedReader.readLine();
            String[] headers = header.split(",");

            System.out.println("Search results:");

            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0].toLowerCase();
                String email = fields[1].toLowerCase();
                String phoneNumber = fields[2].toLowerCase();
                if (name.contains(query) || email.contains(query) || phoneNumber.contains(query)) {
                    System.out.println(headers[0] + ": " + fields[0]);
                    System.out.println(headers[1] + ": " + fields[1]);
                    System.out.println(headers[3] + ": " + fields[3]);
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading and/or searching file: " + e.getMessage());
        }
        scanner.close();
    }


    // Exercise 5
    public static void sortCsvByAgeOrName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose from the following options:");
        System.out.println("1. sort by age");
        System.out.println("2. sort by name");
        int query = scanner.nextInt();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            String header = bufferedReader.readLine();
            String[] headers = header.split(",");
            List<User> users = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0].toLowerCase().trim();
                String age = fields[1].toLowerCase().trim();
                String email = fields[2].toLowerCase().trim();
                String phoneNumber = fields[3].toLowerCase().trim();
                String address = fields[4].toLowerCase().trim();

                users.add(new User(name, Integer.parseInt(age), email, phoneNumber, address));
            }
            if (query == 1) {
                users.sort(Comparator.comparing(User::getAge));
                updateCSVFile(users);
            } else if (query == 2) {
                users.sort(Comparator.comparing(User::getName));
                updateCSVFile(users);
            } else {
                System.out.println("Invalid input.");
            }

            System.out.println("Sorted User Data:");
            for (User user : users) {
                System.out.println(headers[0] + ": " + user.getName());
                System.out.println(headers[1] + ": " + user.getAge());
                System.out.println(headers[2] + ": " + user.getEmail());
                System.out.println(headers[3] + ": " + user.getPhoneNumber());
                System.out.println(headers[4] + ": " + user.getAddress());
                System.out.println("********");
            }
        } catch (IOException e) {
            System.err.println("Error reading and/or searching file: " + e.getMessage());
        }
        scanner.close();
    }

    // Exercise 6
    public static void updateUsersAddress() {
        List<User> users = readUserData();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the user whose address you want to edit: ");
        String query = scanner.nextLine().toLowerCase();

        User userToUpdate = null;
        for (User user : users) {
            if (user.getName().toLowerCase().contains(query)) {
                userToUpdate = user;
                break;
            }
        }
        if (userToUpdate != null) {
            System.out.println("Enter the new address for " + userToUpdate.getName() + ":");
            String newAddress = scanner.nextLine();
            userToUpdate.setAddress(newAddress);
            updateCSVFile(users);
            System.out.println("Address updated successfully!");
        } else {
            System.out.println("User not found.");
        }
        displayUserData(users);
    }

    // Copied to follow editing solution
    private static List<User> readUserData() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            reader.readLine(); // Skip header line
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String name = userData[0].trim();
                int age = Integer.parseInt(userData[1].trim());
                String email = userData[2].trim();
                String phone = userData[3].trim();
                String address = userData[4].trim();
                users.add(new User(name, age, email, phone, address));
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading the CSV file: " + e.getMessage());
        }
        return users;
    }

    // This is basically Exercise 10
    private static void updateCSVFile(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            writer.write("Name, Age, Email, Phone, Address");
            writer.newLine();
            for (User user : users) {
                writer.write(user.getName() + ", " + user.getAge() + ", " +
                        user.getEmail() + ", " + user.getPhoneNumber() + ", " + user.getAddress());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error occurred while updating the CSV file: " + e.getMessage());
        }
    }

    private static void displayUserData(List<User> users) {
        System.out.println("User Data from CSV:");
        for (User user : users) {
            System.out.println("Name: " + user.getName());
            System.out.println("Age: " + user.getAge());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Phone: " + user.getPhoneNumber());
            System.out.println("Address: " + user.getAddress());
            System.out.println("----------------------");
        }
    }

    // my former solution that did not override the CSV file
    public static void updateUserAddress() {
        Scanner scanner = new Scanner(System.in);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            System.out.println("Enter the name of the user whose address you want to edit");
            String query = scanner.nextLine().toLowerCase();

            String line;
            String header = bufferedReader.readLine();
            String[] headers = header.split(",");
            List<User> users = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0].toLowerCase().trim();
                String age = fields[1].toLowerCase().trim();
                String email = fields[2].toLowerCase().trim();
                String phoneNumber = fields[3].toLowerCase().trim();
                String address = fields[4].toLowerCase().trim();

                if (name.contains(query)) {
                    System.out.println("User to edit: ");
                    System.out.println(headers[0] + ": " + name);
                    System.out.println(headers[1] + ": " + age);
                    System.out.println(headers[2] + ": " + email);
                    System.out.println(headers[3] + ": " + phoneNumber);
                    System.out.println(headers[4] + ": " + address);

                    System.out.println("Enter the new address");
                    address = scanner.nextLine();

                    System.out.println("Edited Address");
                    System.out.println(headers[0] + ": " + name);
                    System.out.println(headers[1] + ": " + age);
                    System.out.println(headers[2] + ": " + email);
                    System.out.println(headers[3] + ": " + phoneNumber);
                    System.out.println(headers[4] + ": " + address);

                    users.add(new User(name, Integer.parseInt(age), email, phoneNumber, address));
                }
                users.add(new User(name, Integer.parseInt(age), email, phoneNumber, address));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        scanner.close();
    }

    // Exercise 7
    public static void deleteUserData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name or email of the user you want to delete: ");
        String query = scanner.nextLine().toLowerCase();
        List<User> users = readUserData();

        boolean removed = false;

        for (User user : users) {
            if (user.getName().equalsIgnoreCase(query) || user.getEmail().equalsIgnoreCase(query)) {
                users.remove(user);
                removed = true;
            }
        }

        if (removed) {
            System.out.println("User data removed successfully!");
            updateCSVFile(users);
        } else {
            System.out.println("User not found.");
        }
        displayUserData(users);
        scanner.close();
    }

    // Exercise 8
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose from the number of options");
        System.out.println("1. add new user");
        System.out.println("2. sort user by age or name");
        System.out.println("3. edit user's address");
        System.out.println("4. delete user info");
        System.out.println("5. search user");

        int input = scanner.nextInt();
        switch (input) {
            case 1 -> appendUserCsvFile();
            case 2 -> sortCsvByAgeOrName();
            case 3 -> updateUsersAddress();
            case 4 -> deleteUserData();
            case 5 -> searchUser();
            default -> System.out.println("Invalid input/option");
        }
        scanner.close();
    }

    // Exercise 9
    public static int totalNumberOfUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            String header = bufferedReader.readLine();
            String[] headers = header.split(",");

            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0].toLowerCase().trim();
                String age = fields[1].toLowerCase().trim();
                String email = fields[2].toLowerCase().trim();
                String phoneNumber = fields[3].toLowerCase().trim();
                String address = fields[4].toLowerCase().trim();

                users.add(new User(name, Integer.parseInt(age), email, phoneNumber, address));
            }
        } catch (IOException e) {
            System.err.println("Error reading and/or searching file: " + e.getMessage());
        }
        return users.size();
    }


    public static void main(String[] args) {
        writeUsersToCsvFile();
        readUserCsvFile();
        menu();
    }
}
