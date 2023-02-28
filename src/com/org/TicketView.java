package com.org;

import java.util.Scanner;
import java.util.Formatter;

import static java.lang.System.exit;

public class TicketView {
    private String login_email, login_password; // login
    private String reg_email, reg_password, reg_fname, reg_lname, reg_mname, reg_dob, reg_mobilenum; // registration
    private String hello_email, hello_name, hello_dob, hello_mobileno, hello_createdon; // afterr login
    private String[] helloInventory; // user's inventory
    private String[] helloShop; // server's shop
    private int choice, helloChoice; // choices before and after login.

    public void setLogin_email(String login_email) {
        this.login_email = login_email;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public void setReg_email(String reg_email) {
        this.reg_email = reg_email;
    }

    public void setReg_password(String reg_password) {
        this.reg_password = reg_password;
    }

    public void setReg_fname(String reg_fname) {
        this.reg_fname = reg_fname;
    }

    public void setReg_mname(String reg_mname) {
        this.reg_mname = reg_mname;
    }

    public void setReg_lname(String reg_lname) {
        this.reg_lname = reg_lname;
    }

    public void setReg_dob(String reg_dob) {
        this.reg_dob = reg_dob;
    }

    public void setReg_mobilenum(String reg_mobilenum) {
        this.reg_mobilenum = reg_mobilenum;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public void setHello_createdon(String hello_createdon) {
        this.hello_createdon = hello_createdon;
    }

    public void setHello_dob(String hello_dob) {
        this.hello_dob = hello_dob;
    }

    public void setHello_email(String hello_email) {
        this.hello_email = hello_email;
    }

    public void setHello_mobileno(String hello_mobileno) {
        this.hello_mobileno = hello_mobileno;
    }

    public void setHello_name(String hello_name) {
        this.hello_name = hello_name;
    }

    public void setHelloInventory(String[] arr)
    {
        this.helloInventory = arr;
    }

    public void setHelloShop(String[] arr)
    {
        this.helloShop = arr;
    }

    public String getLogin_email() {
        return login_email;
    }

    public String getLogin_password() {
        return login_password;
    }

    public String getReg_dob() {
        return reg_dob;
    }

    public String getReg_email() {
        return reg_email;
    }

    public String getReg_fname() {
        return reg_fname;
    }

    public String getReg_lname() {
        return reg_lname;
    }

    public String getReg_mname() {
        return reg_mname;
    }

    public String getReg_mobilenum() {
        return reg_mobilenum;
    }

    public String getReg_password() {
        return reg_password;
    }

    public int getChoice() {
        return choice;
    }

    public String getHello_createdon() {
        return hello_createdon;
    }

    public String getHello_dob() {
        return hello_dob;
    }

    public String getHello_email() {
        return hello_email;
    }

    public String getHello_mobileno() {
        return hello_mobileno;
    }

    public String getHello_name() {
        return hello_name;
    }

    public int getHelloChoice() {
        return helloChoice;
    }

    public void getHelloInventory() // show user's inventory in table-view.
    {
        String table = String.format("%15s %15s %15s %15s %15s %15s", "#", "TID", "From", "To", "Departure_Date", "Departure_Time");
        System.out.println(table);
        for(int i = 0; i < this.helloInventory.length; i++)
        {
            if(this.helloInventory[i] == null) break;
            table = String.format("%15s %15s", (i+1) , this.helloInventory[i]);
            System.out.println(table);
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Ticket's ID to cancel, or enter '0' to go back: ");
        helloChoice = scan.nextInt();
    }

    public void getHelloShop() // show server's shop to user in table-view.
    {
        String table = String.format("%15s %15s %15s %15s %15s %15s %15s %15s", "#", "TID", "From", "To", "Price", "Discount_Price", "Departure_Date", "Departure_Time");
        System.out.println(table);
        for(int i = 0; i < this.helloShop.length; i++)
        {
            if(this.helloShop[i] == null) break;
            table = String.format("%15s %15s",(i+1), this.helloShop[i]);
            System.out.println(table);
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Ticket's ID to buy, or enter '0' to go back: ");
        helloChoice = scan.nextInt();
    }

    public void printMessage(String msg)
    {
        System.out.println(msg);
    } // view message to user from server.

    public void Start() // start client's application.
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Choose an operation?\n1.Login\n2.Register\n3.Exit\nChoice: ");
        setChoice(scan.nextInt());
        switch (this.choice) {
            case 1 -> this.LoginView();
            case 2 -> this.RegisterView();
            case 3 -> exit(0);
            default -> {
                System.out.println("Please, choose a valid operation.");
                this.Start();
            }
        }
    }

    public void LoginView() // login page.
    {
        System.out.println(">>> VIEW-LOGIN <<<");
        Scanner scan =  new Scanner(System.in);
        System.out.print("Email: "); setLogin_email(scan.nextLine());
        System.out.print("Password: "); setLogin_password(scan.nextLine());
    }

    public void RegisterView() // registration page.
    {
        System.out.println(">>> VIEW-REGISTER <<<");
        Scanner scan =  new Scanner(System.in);
        System.out.print("Email: "); setReg_email(scan.nextLine());
        System.out.print("Password: "); setReg_password(scan.nextLine());
        System.out.print("First Name: "); setReg_fname(scan.nextLine());
        System.out.print("Middle Name (OPTIONAL): "); setReg_mname(scan.nextLine());
        System.out.print("Last Name: "); setReg_lname(scan.nextLine());
        System.out.print("Date Of Birth (YYYY-MM-DD): "); setReg_dob(scan.nextLine());
        System.out.print("Mobile Number: "); setReg_mobilenum(scan.nextLine());
    }

    public void HelloView() // the after login page.
    {
        System.out.println("\n\n>>> VIEW-HELLO <<<\nHello, " + getHello_email() + "\nHere are your info:");
        System.out.println("Name: " + getHello_name());
        System.out.println("Date Of Birth: " + getHello_dob());
        System.out.println("Mobile Number: " + getHello_mobileno());
        System.out.println("Member since " + getHello_createdon());

        Scanner scan = new Scanner(System.in);
        System.out.print("\nChoose an operation?\n1.Show My Inventory\n2.Buy A Ticket\n3.Logout\nChoice: ");
        this.choice = scan.nextInt();
    }

}
