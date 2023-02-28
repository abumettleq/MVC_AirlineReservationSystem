package com.org;

import static java.lang.System.exit;

public class Main {

    /**
     * The main function contains three objects: view, model
     * and controller. Controller works as the mediator between view and
     * model. Order of operations: User interacts with view ->
     * Controller gets user info from view -> Controller asks the model
     * to fetch the info the user has requested -> Model sends the info
     * to the controller -> Controller sends the user requested info to
     * the view -> Client sees what he wanted to see.
     * ->
    **/
    public static void main(String[] args) throws Exception {
        TicketView view = new TicketView();
        TicketModel model = new TicketModel();
        TicketController controller = new TicketController(model, view);

        view.Start(); // start view, user should now interact with it.
        controller.getView2Model(); // get data from view and pass it to the model in 1 call.
        if(controller.getAccount_id() > 0) // if login is successful.
        {
            controller.getAccountInfo2View(); // If model succeeds to fetch data from db, then controller will pass them to view.
            while(true)
            {
                view.HelloView(); // the after login

                switch(view.getChoice()) // get user's choice from view.
                {
                    case 1 -> {
                        controller.inventory2view(); // get inventory data from model, to view.
                        view.getHelloInventory(); // from view, show inventory.
                        switch (controller.getLocalChoice()) // get user's choice from view.
                        {
                            case 0 -> {} // go back to the after login.
                            default -> controller.viewChoice2model(); // send ticket id the user chose to the model, model will send back the reply.
                        }
                    }
                    case 2 -> {
                        controller.shop2view(); //  get shop info from model, to view.
                        view.getHelloShop(); // from view, show shop.
                        switch (controller.getLocalChoice()) // get user's choice from view.
                        {
                            case 0 -> {} // go back to the after login.
                            default -> controller.viewChoice2model(); // send ticket id the user chose to the model, model will send back the reply.
                        }
                    }
                    case 3 -> {
                        controller.sendLogoutSignal(); // logout from client.
                        exit(0);
                    }
                    default -> System.out.println("Please, choose a valid operation.");
                }
            }
        }
    }
}
