package com.org;

import java.sql.*;
import java.time.LocalDate;
import java.util.Formatter;

public class TicketModel{
    private final Connection connect;
    private final Statement statement;
    private ResultSet resultSet;

    public TicketModel() throws Exception // constructor to initialize connection with db
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager
                .getConnection("jdbc:mysql://localhost/airlinesystem?"
                        + "user=root&password=");

        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
    }

    public int verifyLogin(String email, String password) throws SQLException // to check whether such account exists or not, and return user's id if exists
    {
        resultSet = statement.executeQuery("select acc_id from account where email = '" + email + "' and pass = '" + password + "'");
        if(resultSet.next())
        {
            return resultSet.getInt(1);
        }
        else return -1;
    }

    public boolean registerAccount(String email, String password, String fname, String mname, String lname, String dob, String mobile) throws SQLException // register a new account
    {
        String sql = " insert into account (email, pass, fname, mname, lname, dob, mobile_no, createdon)"
                + " values (?, ?, ?, ?, ?, ? , ? , ?)";
        PreparedStatement preparedStmt = connect.prepareStatement(sql);
        preparedStmt.setString (1, email);
        preparedStmt.setString (2, password);
        preparedStmt.setString   (3, fname);
        preparedStmt.setString(4, mname);
        preparedStmt.setString    (5, lname);
        preparedStmt.setString    (6, dob);
        preparedStmt.setString    (7, mobile);
        preparedStmt.setString(8, String.valueOf(LocalDate.now()));
        return preparedStmt.execute();
    }

    public void updateRecord(int acc_id, String column, String value) throws SQLException { // function to update one record only from account table.
        statement.executeUpdate("update account set " + column + " = '" + value + "' where acc_id = " + acc_id);
    }

    public String getAccountName(int acc_id) throws SQLException { // get full name of user.
        resultSet = statement.executeQuery("select fname, mname, lname from account where acc_id = " + acc_id);
        resultSet.next();
        return resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3);
    }

    public String getAccountEmail(int acc_id) throws SQLException { // get user's email.
        resultSet = statement.executeQuery("select email from account where acc_id = " + acc_id);
        resultSet.next();
        return resultSet.getString(1);
    }

    public String getAccountDOB(int acc_id) throws SQLException { // get user's dob.
        resultSet = statement.executeQuery("select dob from account where acc_id = " + acc_id);
        resultSet.next();
        return resultSet.getString(1);
    }

    public String getAccountMobileNo(int acc_id) throws SQLException { // get user's phone number.
        resultSet = statement.executeQuery("select Mobile_No from account where acc_id = " + acc_id);
        resultSet.next();
        return resultSet.getString(1);
    }

    public String getAccountCreatedOn(int acc_id) throws SQLException { // get creation date of one's account.
        resultSet = statement.executeQuery("select CreatedOn from account where acc_id = " + acc_id);
        resultSet.next();
        return resultSet.getString(1);
    }

    public String[] getAccountInventory(int acc_id) throws SQLException   // get inventory's info from db for one user.
    {
        String[] tickets = new String[10];
        resultSet = statement.executeQuery("SELECT Ticket_ID, fromwhere, towhere, departuredate,departuretime from ticket where Ticket_ID IN (SELECT Ticket_ID from inventory where Account_ID = "+ acc_id +")");
        int i = 0;
        while(resultSet.next())
        {
            tickets[i] = String.format("%15s %15s %15s %15s %15s", resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
            i++;
        }
        return tickets;
    }

    public String[] getTicketShop() throws SQLException // get shop's info from db.
    {
        String[] tickets = new String[10];
        resultSet = statement.executeQuery("select Ticket_ID, fromwhere, towhere, price, discountprice, departuredate, departuretime from ticket where isbought = 0" );
        int i = 0;
        while(resultSet.next())
        {
            tickets[i] = String.format( "%15s %15s %15s %15s %15s %15s %15s", resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)
                    , resultSet.getString(4) , resultSet.getString(5) , resultSet.getString(6)
                    , resultSet.getString(7));

            i++;
        }
        return tickets;
    }

    public String operateTicket(int tid, int acc_id, int choice) throws SQLException { // do operation on a ticket, to cancel or to purchase.
        if(choice == 1)
        {
            int success = statement.executeUpdate("update ticket set isbought = 0 where ticket_id = " + tid + " AND isbought = 1");
            if(success > 0)
            {
                statement.executeUpdate("delete from inventory where ticket_id = " + tid + " And account_id = " + acc_id);
                return "You have cancelled a ticket!";
            }
            else return "Ticket cancellation failed, check inserted TID.";
        }
        else if(choice == 2)
        {
            int success = statement.executeUpdate("update ticket set isbought = 1 where ticket_id = " + tid);
            if(success > 0)
            {
                statement.execute("insert into inventory(account_id, ticket_id, inventory_id) values(" + acc_id + "," + tid + "," + acc_id + ")");
                return "You have purchased a new ticket!";
            }
            else return "Ticket purchase failed, check inserted TID.";
        }
        return "Unknown Error.";
    }

    public void logout(int acc_id) throws SQLException { // log out by setting user's isActive flag to 0.
        statement.executeUpdate("update account set isActive = 0 where acc_id = " + acc_id);
    }
}
