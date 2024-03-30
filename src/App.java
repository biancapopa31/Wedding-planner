public class App {
    public static Wedding wedding = new Wedding();

    public static void main(String[] args) throws Exception {

        Person guest1 = new Guest("Doe", "John", "1234567890", InviteStatus.ATTENDING, Side.BRIDE, Role.MAID_OF_HONOR, Relationship.FAMILY);
        Person guest2 = new Guest("Doe", "Jane", "1234567890", InviteStatus.NOT_ATTENDING, Side.BRIDE, Role.NONE, Relationship.FRIEND);
        Person vendor1 = new Vendor("Doe", "Jane", "1234567890", "Email", 1234,"Catering", "Notes");
        
        wedding.addGuest((Guest) guest1);
        wedding.addGuest((Guest) guest2);

        wedding.addVendor((Vendor) vendor1);
        
        Table table1 = new Table(1, 10);
        table1.addMember(guest1);

        wedding.addTable(table1);

        Service service = new Service();
        int input = -1;
        while (input != 7) {
            
            input = service.MainMenu();
            switch(input){
                case 1:
                    input = -1;
                    while (input != 7) {
                        input = service.generalInformationMenu();
                        switch (input) {
                            case 1:
                                service.editLocation();
                                break;
                            case 2:
                                service.editDate();
                                break;
                            case 3:
                                service.editPerson(wedding.getBride());
                                break;
                            case 4:
                                service.editPerson(wedding.getGroom());
                                break;
                            case 5:
                                service.editPerson(wedding.getGodmother());
                                break;
                            case 6:
                                service.editPerson(wedding.getGodfather());
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case 2:
                    input = -1;
                    while (input != 6) {
                        input = service.guestsMenu();
                        switch (input) {
                            case 1:
                                service.showGuests();
                                service.waitForAnyKey();
                                break;
                            case 2:
                                service.addGuest();
                                break;
                            case 3:
                                service.showGuests();
                                service.removeGuest(service.userInput());
                                break;
                            case 4:
                                service.showGuests();
                                service.editGuest(service.userInput());
                                break;
                            case 5:
                                service.findGuestFromName();
                                service.waitForAnyKey();
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case 3:
                    input = -1;
                    while (input != 6 ) {
                        input = service.tableMenu();
                        switch (input) {
                            case 1:
                                service.showTables();
                                service.waitForAnyKey();
                                break;
                             case 2:
                                 service.addTable();
                                 break;
                            case 3:
                                service.showTables();
                                service.removeTable(service.userInput());
                                break;
                            case 4:
                                service.showTables();
                                service.editTable(service.userInput());
                                break;
                            default:
                                break;
                        }
                        
                    }
                case 4:
                    input = -1;
                    while (input != 5) {
                        input = service.vendorMenu();
                        switch (input) {
                            case 1:
                                service.showVendors();
                                service.waitForAnyKey();
                                break;
                            case 2:
                                service.addVendor();
                                break;
                            case 3:
                                service.showVendors();
                                service.removeVendor(service.userInput());
                                break;
                            case 4:
                                service.showVendors();
                                service.editVendor(service.userInput());
                                break;

                            //TODO: Implement findVendor maybe?
                            // case 5:
                            //     service.findVendor();
                            //     service.waitForAnyKey();
                            //     break;
                            default:
                                break;
                        }
                    }
                case 5:
                    input = -1;
                    // while (input != 6) {
                    //     input = service.checklistMenu();
                    //     switch (input) {
                    //         case 1:
                    //             service.showChecklist();
                    //             service.waitForAnyKey();
                    //             break;
                    //         case 2:
                    //             service.addTask();
                    //             break;
                    //         case 3:
                    //             service.removeTask(service.getTaskIdFromUser());
                    //             break;
                    //         case 4:
                    //             service.editTask(service.getTaskIdFromUser());
                    //             break;
                    //         case 5:
                    //             service.findTask();
                    //             service.waitForAnyKey();
                    //             break;
                    //         default:
                    //             break;
                    //     }
                    // }
                case 7:
                    service.exit();
                    break;

                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }
    }
}
