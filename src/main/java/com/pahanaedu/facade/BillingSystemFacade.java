package com.pahanaedu.facade;

import com.pahanaedu.dao.DatabaseManager;
import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Item;
import com.pahanaedu.model.Bill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillingSystemFacade {
    private DatabaseManager dbManager = DatabaseManager.getInstance();

    public boolean authenticate(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        ResultSet rs = dbManager.executeQuery(query, username, password);
        return rs.next();
    }

    public void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO customers (account_number, name, address, telephone, units_consumed) VALUES (?, ?, ?, ?, ?)";
        dbManager.executeUpdate(query, customer.getAccountNumber(), customer.getName(), customer.getAddress(), customer.getTelephone(), customer.getUnitsConsumed());
    }

    public void editCustomer(Customer customer) throws SQLException {
        String query = "UPDATE customers SET name = ?, address = ?, telephone = ?, units_consumed = ? WHERE account_number = ?";
        dbManager.executeUpdate(query, customer.getName(), customer.getAddress(), customer.getTelephone(), customer.getUnitsConsumed(), customer.getAccountNumber());
    }

    public void deleteCustomer(String accountNumber) throws SQLException {
        String query = "DELETE FROM customers WHERE account_number = ?";
        dbManager.executeUpdate(query, accountNumber);
    }

    public Customer getCustomer(String accountNumber) throws SQLException {
        String query = "SELECT * FROM customers WHERE account_number = ?";
        ResultSet rs = dbManager.executeQuery(query, accountNumber);
        if (rs.next()) {
            return new Customer(
                rs.getString("account_number"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("telephone"),
                rs.getInt("units_consumed")
            );
        }
        return null;
    }

    public void addItem(Item item) throws SQLException {
        String query = "INSERT INTO items (item_id, item_name, unit_price) VALUES (?, ?, ?)";
        dbManager.executeUpdate(query, item.getItemId(), item.getItemName(), item.getUnitPrice());
    }

    public void editItem(Item item) throws SQLException {
        String query = "UPDATE items SET item_name = ?, unit_price = ? WHERE item_id = ?";
        dbManager.executeUpdate(query, item.getItemName(), item.getUnitPrice(), item.getItemId());
    }

    public void deleteItem(String itemId) throws SQLException {
        String query = "DELETE FROM items WHERE item_id = ?";
        dbManager.executeUpdate(query, itemId);
    }

    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM items";
        ResultSet rs = dbManager.executeQuery(query);
        while (rs.next()) {
            items.add(new Item(
                rs.getString("item_id"),
                rs.getString("item_name"),
                rs.getDouble("unit_price")
            ));
        }
        return items;
    }

    public double calculateBill(String accountNumber, String itemId, int units) throws SQLException {
        Customer customer = getCustomer(accountNumber);
        String query = "SELECT unit_price FROM items WHERE item_id = ?";
        ResultSet rs = dbManager.executeQuery(query, itemId);
        if (rs.next() && customer != null) {
            double unitPrice = rs.getDouble("unit_price");
            double total = units * unitPrice;
            String insertBill = "INSERT INTO bills (account_number, total_amount, bill_date) VALUES (?, ?, ?)";
            dbManager.executeUpdate(insertBill, accountNumber, total, new Date());
            return total;
        }
        return 0.0;
    }

    public List<Bill> getBills(String accountNumber) throws SQLException {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM bills WHERE account_number = ?";
        ResultSet rs = dbManager.executeQuery(query, accountNumber);
        while (rs.next()) {
            bills.add(new Bill(
                rs.getInt("bill_id"),
                rs.getString("account_number"),
                rs.getDouble("total_amount"),
                rs.getDate("bill_date")
            ));
        }
        return bills;
    }

    public String getHelpContent() {
        return "Pahana Edu Billing System Help\n" +
               "1. Login: Use your username and password.\n" +
               "2. Add Customer: Enter customer details to register.\n" +
               "3. Manage Items: Add, edit, or delete book items.\n" +
               "4. Calculate Bill: Select customer and item, enter units.\n" +
               "5. View Details: Check customer or bill information.\n" +
               "6. Logout: Exit the system securely.";
    }
}