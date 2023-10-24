package week_8.day_1;

import java.io.*;
import java.time.Year;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ExerciseOne {
    // Exercise 6
    public static int getBirthYear(int age) {
        int currentYear = Year.now().getValue();
        return currentYear - age;
    }

    // Exercise 8
    private static boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(email).matches();
    }

    // Exercise 3
    public static void displayInformation(String name, int age, String email, String
            phoneNumber, String bookTitle, String author, int publicationYear, String favoriteColor) {
        System.out.println("**Summary Report**");

        System.out.println("User Information: ");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);

        // Exercise 6
        System.out.println("Birth year: " + getBirthYear(age));

        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);

        // Exercise 7
        System.out.println("Favorite Book: " + bookTitle);
        System.out.println("Author: " + author);
        System.out.println("Publication Year: " + publicationYear);

        // Exercise 9
        System.out.println("Your favorite color is: " + favoriteColor);
    }

    // Exercise 4
    public static void writeInformationToFile(String name, int age, String email, String
            phoneNumber, String address, String bookTitle, String author,
                                              int publicationYear, String favoriteColor, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Summary report\n");
            writer.write("User Information:\n");
            writer.write("Name: " + name + "\n");
            writer.write("Age: " + age + "\n");
            writer.write("Birth Year: " + getBirthYear(age) + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Phone Number: " + phoneNumber + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Favourite Book:\n");
            writer.write("Book Title: " + bookTitle + "\n");
            writer.write("Author: " + author + "\n");
            writer.write("Publication Year: " + publicationYear + "\n");
            writer.write("Favorite Color: " + favoriteColor + "\n");
            System.out.println("User information has been written to userinfo.txt file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    // Day 2 Exercise 5
    public static void specifyPathToWriteFile(String name, int age, String email, String
            phoneNumber, String address, String bookTitle, String author,
                                              int publicationYear, String favoriteColor) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();

        try (FileWriter writer = new FileWriter("src/main/java/week_8/day_1/" + fileName)) {
            writer.write("Summary report\n");
            writer.write("User Information:\n");
            writer.write("Name: " + name + "\n");
            writer.write("Age: " + age + "\n");
            writer.write("Birth Year: " + getBirthYear(age) + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Phone Number: " + phoneNumber + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Favourite Book:\n");
            writer.write("Book Title: " + bookTitle + "\n");
            writer.write("Author: " + author + "\n");
            writer.write("Publication Year: " + publicationYear + "\n");
            writer.write("Favorite Color: " + favoriteColor + "\n");
            System.out.println("User information has been written to userinfo.txt file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Exercise 5
    public static void readFile(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String userFilePath = "src/main/java/week_8/day_1/userInfo.txt";

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name: ");
        String name = sc.nextLine();

        // Exercise 2:
        int age = 0;
        boolean isValidAge = false;
        while (!isValidAge) {
            try {
                System.out.print("Enter your age: ");
                age = Integer.parseInt(sc.nextLine());
                if (age > 0) {
                    isValidAge = true;
                } else {
                    System.out.println("Invalid age.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Age must be a positive integer. Please try again.");
            }
        }

        // Exercise 8
        String email;
        do {
            System.out.print("Enter your email address: ");
            email = sc.nextLine();
        } while (!isValidEmail(email));

        System.out.println("Enter your phone number: ");
        String phoneNumber = sc.nextLine();

        System.out.println("Enter your address: ");
        String address = sc.nextLine();

        // Exercise 7
        System.out.print("Enter your favorite book title: ");
        String bookTitle = sc.nextLine();

        System.out.print("Enter the author of the book: ");
        String author = sc.nextLine();

        int publicationYear = 0;
        boolean isValidYear = false;
        while (!isValidYear) {
            try {
                System.out.print("Enter the publication year of the book: ");
                publicationYear = Integer.parseInt(sc.nextLine());
                if (publicationYear > 0 && publicationYear <= Year.now().getValue()) {
                    isValidYear = true;
                } else {
                    System.out.println("Publication year must be valid. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Publication year must be a positive integer. Please try again.");
            }
        }

        // Exercise 9
        String favoriteColor = "";
        do {
            System.out.println("Choose your favorite color from the following options:");
            System.out.println("1. Red\n2. Blue\n3. Green\n4. Yellow\n5. Purple");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> favoriteColor = "Red";
                case 2 -> favoriteColor = "Blue";
                case 3 -> favoriteColor = "Green";
                case 4 -> favoriteColor = "Yellow";
                case 5 -> favoriteColor = "Purple";
                default -> System.out.println("Please choose another color");
            }
        } while(favoriteColor.isEmpty());


        displayInformation(name, age, email, phoneNumber, bookTitle, author, publicationYear, favoriteColor);

        writeInformationToFile(name, age, email, phoneNumber, address, bookTitle, author, publicationYear, favoriteColor, userFilePath);

        sc.close();

        readFile(userFilePath);

        // Day 2 Exercise 5
        specifyPathToWriteFile(name, age, email, phoneNumber, address, bookTitle, author, publicationYear, favoriteColor);
    }
}
