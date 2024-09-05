import java.sql.*;
import java.util.Scanner;

public class BloodBankSystem {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/BloodBankDB";
    private static final String USER = "root";
    private static final String PASS = "your_password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n--- Blood Bank Management System ---");
                System.out.println("1. View Donors");
                System.out.println("2. Add Donor");
                System.out.println("3. View Blood Inventory");
                System.out.println("4. Add Donation Record");
                System.out.println("5. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline character

                switch (choice) {
                    case 1:
                        viewDonors(conn);
                        break;
                    case 2:
                        addDonor(conn, scanner);
                        break;
                    case 3:
                        viewBloodInventory(conn);
                        break;
                    case 4:
                        addDonation(conn, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View all donors
    private static void viewDonors(Connection conn) throws SQLException {
        String query = "SELECT * FROM Donors";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n--- Donor List ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("donor_id")
                        + ", Name: " + rs.getString("name")
                        + ", Age: " + rs.getInt("age")
                        + ", Blood Type: " + rs.getString("blood_type"));
            }
        }
    }

    // Add a new donor
    private static void addDonor(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter donor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter donor age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume newline character
        System.out.print("Enter donor gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter blood type: ");
        String bloodType = scanner.nextLine();
        System.out.print("Enter contact number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        String query = "INSERT INTO Donors (name, age, gender, blood_type, contact_number, address) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, gender);
            pstmt.setString(4, bloodType);
            pstmt.setString(5, contact);
            pstmt.setString(6, address);
            pstmt.executeUpdate();
            System.out.println("Donor added successfully!");
        }
    }

    // View blood inventory
    private static void viewBloodInventory(Connection conn) throws SQLException {
        String query = "SELECT * FROM BloodInventory";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n--- Blood Inventory ---");
            while (rs.next()) {
                System.out.println("Blood ID: " + rs.getInt("blood_id")
                        + ", Blood Type: " + rs.getString("blood_type")
                        + ", Quantity: " + rs.getInt("quantity"));
            }
        }
    }

    // Add donation record
    private static void addDonation(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter donor ID: ");
        int donorId = scanner.nextInt();
        System.out.print("Enter blood ID: ");
        int bloodId = scanner.nextInt();
        scanner.nextLine();  // Consume newline character
        System.out.print("Enter donation date (YYYY-MM-DD): ");
        String donationDate = scanner.nextLine();

        String query = "INSERT INTO DonationRecords (donor_id, blood_id, date_of_donation) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, donorId);
            pstmt.setInt(2, bloodId);
            pstmt.setString(3, donationDate);
            pstmt.executeUpdate();
            System.out.println("Donation record added successfully!");
        }
    }
}
