package eu.epfc.a5900.contactapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {
    public static final String CONNECTION_STRING = "jdbc:h2:./contact";

    static void initialize() {
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {
            String sql = "CREATE TABLE IF NOT EXISTS Contact (" +
                    "Id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "FirstName VARCHAR(64) NOT NULL," +
                    "LastName VARCHAR(64) NOT NULL," +
                    "Email VARCHAR(128)," +
                    "Phone VARCHAR(20)" +
                    ")";
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void add(Contact contact) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {
            String sql = "INSERT INTO Contact (FirstName, LastName, Email, Phone) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, contact.firstName);
            preparedStatement.setString(2, contact.lastName);
            preparedStatement.setString(3, contact.email);
            preparedStatement.setString(4, contact.phone);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static List<Contact> fetch() {
        List<Contact> contacts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {
            String sql = "SELECT Id, FirstName, LastName, Email, Phone FROM Contact";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.id = resultSet.getInt("Id");
                contact.firstName = resultSet.getString("FirstName");
                contact.lastName = resultSet.getString("LastName");
                contact.email = resultSet.getString("Email");
                contact.phone = resultSet.getString("Phone");
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }
}
