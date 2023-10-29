import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class indi {

    public static void showRoutes(Connection con) throws SQLException {
        Statement st = con.createStatement();
        String query = "SELECT * FROM routes";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            int routeId = rs.getInt("route_id");
            String routeName = rs.getString("route_name");
            double distance = rs.getDouble("distance");
            double fare = rs.getDouble("fare");

            System.out.println("Route ID: " + routeId + ", Route Name: " + routeName
                    + ", Distance: " + distance + " km, Fare: ₹" + fare);
        }

        rs.close();
        st.close();
    }

    public static void bookTicket(Connection con) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        String phoneNumber;
        while (true) {
            System.out.print("Enter Phone Number (10 digits): ");
            phoneNumber = scanner.nextLine();

            if (phoneNumber.length() == 10 && phoneNumber.matches("\\d+")) {
                break;
            } else {
                System.out.println("Invalid phone number. Please enter exactly 10 digits.");
            }
        }

        System.out.print("Enter Route ID: ");
        int routeId = scanner.nextInt();

        Random random = new Random();
        int vehicleId = random.nextInt(50) + 1;

        int bookingId;
        Statement st = con.createStatement();
        ResultSet rs;

        while (true) {
            bookingId = random.nextInt(101) + 100;

            rs = st.executeQuery("SELECT booking_id FROM bookings WHERE booking_id = " + bookingId);
            if (!rs.next()) {
                break;
            }
        }

        String query = "INSERT INTO bookings (booking_id, route_id, first_name, last_name, phonenumber, vehicle_id) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, bookingId);
        ps.setInt(2, routeId);
        ps.setString(3, firstName);
        ps.setString(4, lastName);
        ps.setString(5, phoneNumber);
        ps.setInt(6, vehicleId);

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Ticket booked successfully!");
            System.out.println("Your Booking ID is: " + bookingId);
        } else {
            System.out.println("Failed to book ticket.");
        }

        ps.close();
        st.close();
    }

    public static void getBookingDetails(Connection con) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Booking ID: ");
        int bookingId = scanner.nextInt();

        String query = "SELECT b.booking_id, r.route_name, r.distance, r.fare, b.first_name, b.last_name, b.phonenumber, b.vehicle_id " +
                "FROM bookings b " +
                "JOIN routes r ON b.route_id = r.route_id " +
                "WHERE b.booking_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, bookingId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String routeName = rs.getString("route_name");
            double distance = rs.getDouble("distance");
            double fare = rs.getDouble("fare");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String phoneNumber = rs.getString("phonenumber");
            int vehicleId = rs.getInt("vehicle_id");

            System.out.println("Route Name: " + routeName);
            System.out.println("Distance: " + distance + " km");
            System.out.println("Fare: ₹" + fare);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Phone Number: " + phoneNumber);
            System.out.println("Vehicle Number: " + vehicleId);
        } else {
            System.out.println("Booking ID not found.");
        }

        ps.close();
    }

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/java-individual";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);

            if (con != null) {
                System.out.println("Connected to the database!");

                Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.println("Menu:");
                    System.out.println("1. Show Routes");
                    System.out.println("2. Book a Ticket");
                    System.out.println("3. Get Booking Details");
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            showRoutes(con);
                            break;
                        case 2:
                            bookTicket(con);
                            break;
                        case 3:
                            try {
                                getBookingDetails(con);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 4:
                            try {
                                con.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            scanner.close();
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
