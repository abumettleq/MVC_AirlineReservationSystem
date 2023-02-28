package com.org;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;

public class TicketController {
    private final TicketModel model;
    private final TicketView view;
    private int account_id; // account id is only saved on the controller side. // needed for model's side operations.

    public TicketController(TicketModel m, TicketView v)
    {
        this.model = m;
        this.view = v;
    }

    public void setAccount_id(int account_id) { // controller uses this method to set account id.
        this.account_id = account_id;
    }

    public int getAccount_id() {
        return account_id;
    } // used to check whether account is logged in.

    public void inventory2view() throws SQLException {  // get inventory info from model, and send to view
        this.view.setHelloInventory(model.getAccountInventory(this.account_id));
    }

    public void shop2view() throws SQLException { // get shop info from model, and send to view
        this.view.setHelloShop(model.getTicketShop());
    }

    public void viewChoice2model() throws SQLException { // after choosing an operation on a ticket, send to model to operate.
        int ticket_id = getLocalChoice();
        int choice = this.view.getChoice();
        this.view.printMessage(model.operateTicket(ticket_id,this.account_id,choice)); // an operation will return a message, display it on view side.
    }

    public int getLocalChoice()
    {
        return view.getHelloChoice();
    } // get user's choice after logging in.

    public void getView2Model() throws SQLException { // get login/registration info from view, to model.
        if(view.getChoice() == 1)
        {
            setAccount_id(this.model.verifyLogin(this.view.getLogin_email(), this.view.getLogin_password()));
            if(this.account_id > 0)
            {
                this.view.printMessage("Login was successful!");
                this.model.updateRecord(this.account_id, "LastLogin", String.valueOf(LocalDate.now()));
                this.model.updateRecord(this.account_id, "IsActive", "1");
            }
            else this.view.printMessage("Login failed, check email or password.");
        }
        else if(view.getChoice() == 2)
        {
            try
            {
                boolean i = this.model.registerAccount(view.getReg_email(), view.getReg_password(), view.getReg_fname(), view.getReg_mname(), view.getReg_lname(), view.getReg_dob(), view.getReg_mobilenum());
                if(!i) this.view.printMessage("Registration was successful!");
            }
            catch (SQLIntegrityConstraintViolationException e)
            {
                this.view.printMessage("Registration failed!, Account with this email already exists.");
            }
        }
        else this.view.printMessage("User has chosen wrong operation in View, hence Model failed to call.\nExiting with failure.");
    }

    public void getAccountInfo2View() throws SQLException // send account's info from model to view after login is success.
    {
        view.setHello_email(model.getAccountEmail(this.account_id));
        view.setHello_name(model.getAccountName(this.account_id));
        view.setHello_dob(model.getAccountDOB(this.account_id));
        view.setHello_mobileno(model.getAccountMobileNo(this.account_id));
        view.setHello_createdon(model.getAccountCreatedOn(this.account_id));
    }

    public void sendLogoutSignal() throws SQLException { // log out from one's account.
        model.logout(this.account_id);
    }
}
