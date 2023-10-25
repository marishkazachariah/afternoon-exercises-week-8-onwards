package week_8.day_2;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class DayTwoExercises {
    // Exercise 1
    public static void displayUsersPhoneNumberAndAddress(String fileInput) {
        try (FileInputStream fileInputStream = new FileInputStream(fileInput)) {
            InputStreamReader reader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String phoneNumber;
            String address;
            while ((phoneNumber = bufferedReader.readLine()) != null &&
                    (address = bufferedReader.readLine()) != null) {
                if (phoneNumber.contains("Phone Number")) {
                    System.out.println(phoneNumber);
                }
                if (address.contains("Address")) {
                    System.out.println(address);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist or file is empty" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file" + e.getMessage());
        }
    }

    // Exercise 2 and Exercise 7
    // for this one I couldn't figure out how to update it directly, so I created a new file
    public static void updateUserAddressAndPhoneNumber(String filePath) {
        Scanner scanner = new Scanner(System.in);
        try (FileInputStream fileInput = new FileInputStream(filePath);
             InputStreamReader reader = new InputStreamReader(fileInput);
             BufferedReader bufferedReader = new BufferedReader(reader);
             FileWriter fileWriter = new FileWriter("src/main/java/week_8/day_2/updatedUserInfo.txt")
        ) {
            String phoneNumber;
            String address;
            while ((phoneNumber = bufferedReader.readLine()) != null &&
                    (address = bufferedReader.readLine()) != null) {
                if (phoneNumber.contains("Phone Number")) {
                    System.out.println("Current Phone Number:" + phoneNumber.substring(phoneNumber.lastIndexOf(":") + 1));
                }
                if (address.contains("Address")) {
                    System.out.println("Current Address:" + address.substring(address.lastIndexOf(":") + 1));
                }
            }

            System.out.println("Enter new phone number: ");
            String newPhoneNumber = scanner.nextLine();
            System.out.println("Enter new address: ");
            String newAddress = scanner.nextLine();

            System.out.println("Are you sure you want to update your data?");
            String answer = scanner.nextLine().toLowerCase();

            if (answer.equals("yes")) {
                fileWriter.write("Updated User Information:\n" + "Phone number: " + newPhoneNumber + "\n" + "Address: " + newAddress);
                System.out.println("User information updated successfully.");
                System.out.println("Updated Phone Number: " + newPhoneNumber);
                System.out.println("Updated Address: " + newAddress);
            } else {
                System.out.println("User information was not updated.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist or file is empty" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading and/or writing to file: " + e.getMessage());
        }
        scanner.close();
    }

    // Exercise 3 and Exercise 7
    public static void deleteUserData(String fileInput) {
        File fileToDelete = new File(fileInput);
        if(fileToDelete.exists()) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Do you want to delete your information? (yes/no): ");
            String answer = scanner.nextLine().toLowerCase();
            if (answer.equals("yes")) {
                fileToDelete.delete();
                if(fileToDelete.delete()) System.out.println("User information deleted successfully.");
            } else {
                System.out.println("User information was not deleted.");
            }
            scanner.close();
        } else {
            System.out.println("User Information File not found.");
        }
    }

    // Exercise 4
    public static void createLogFile() {
        try (FileWriter logWriter = new FileWriter("src/main/java/week_8/day_2/logInfo.txt", true);) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.now();

            String logMessage = "User interaction at " + dateFormat.format(new Date()) + " at time: " + dtf.format(localTime);

            logWriter.write(logMessage + "\n");
            System.out.println("Created log file:\n" + logMessage);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    // I implemented Day 2 Exercise 5 in day_1/ExerciseOne

    // Exercise 6
    public static void displayUsersFavoriteColor(String fileInput) {
        try (FileInputStream fileInputStream = new FileInputStream(fileInput)) {
            InputStreamReader reader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String favoriteColor;
            while ((favoriteColor = bufferedReader.readLine()) != null) {
                if (favoriteColor.contains("Favorite Color")) {
                    System.out.println(favoriteColor);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist or file is empty" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file" + e.getMessage());
        }
    }

    // Exercise 8
    public static void determineAgeRange(String fileInput) {
        try (FileInputStream fileInputStream = new FileInputStream(fileInput)) {
            InputStreamReader reader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String age;
            while ((age = bufferedReader.readLine()) != null) {
                if (age.contains("Age")) {
                    int ageToDetermine = Integer.parseInt(age.replaceAll("\\D", ""));

                    if(ageToDetermine < 13) {
                        System.out.println("User is a child");
                    } else if (ageToDetermine < 18) {
                        System.out.println("User is a teenager");
                    } else if (ageToDetermine > 54) {
                        System.out.println("User is a senior");
                    } else {
                        System.out.println("User is an adult");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist or file is empty" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file" + e.getMessage());
        }
    }

    // Exercise 9
    public static int amountOfTimesProgramIsRun() {
        String logPath = "src/main/java/week_8/day_2/logInfo.txt";
        int numberOfInteractions = 0;

        try (FileInputStream fileInputStream = new FileInputStream(logPath)) {
            InputStreamReader reader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            while (bufferedReader.readLine() != null) {
                numberOfInteractions++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist or file is empty" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file" + e.getMessage());
        }

        return numberOfInteractions;
    }

    public static void main(String[] args) {
        String userFilePath = "src/main/java/week_8/day_1/userInfo.txt";
        displayUsersPhoneNumberAndAddress(userFilePath);
        updateUserAddressAndPhoneNumber(userFilePath);
        deleteUserData(userFilePath);
        createLogFile();
        displayUsersFavoriteColor(userFilePath);
        determineAgeRange(userFilePath);
        System.out.println("Amount of times program is run: " + amountOfTimesProgramIsRun());
    }
}
