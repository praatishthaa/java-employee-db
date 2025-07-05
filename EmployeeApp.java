import java.sql.*;
import java.util.Scanner;

public class EmployeeApp {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        EmployeeApp app = new EmployeeApp();

        while (true) {
            System.out.println("\n1. Add Employee\n2. View Employees\n3. Update Employee\n4. Delete Employee\n5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> app.addEmployee();
                case 2 -> app.viewEmployees();
                case 3 -> app.updateEmployee();
                case 4 -> app.deleteEmployee();
                case 5 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public void addEmployee() {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO employee (name, email, salary) VALUES (?, ?, ?)")) {

            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Salary: ");
            double salary = sc.nextDouble();

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setDouble(3, salary);
            ps.executeUpdate();

            System.out.println("Employee added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewEmployees() {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM employee")) {

            System.out.println("ID | Name | Email | Salary");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                                   rs.getString("name") + " | " +
                                   rs.getString("email") + " | " +
                                   rs.getDouble("salary"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee() {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE employee SET name=?, email=?, salary=? WHERE id=?")) {

            System.out.print("Enter ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("New name: ");
            String name = sc.nextLine();
            System.out.print("New email: ");
            String email = sc.nextLine();
            System.out.print("New salary: ");
            double salary = sc.nextDouble();

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setDouble(3, salary);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Employee updated.");
            else System.out.println("Employee not found.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee() {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM employee WHERE id=?")) {

            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) System.out.println("Employee deleted.");
            else System.out.println("No employee found with that ID.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
