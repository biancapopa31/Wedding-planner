//TODO Debugging!!!

public class App {

    public static void main(String[] args) throws Exception {

        Service service = Service.getInstance();
        int mainInput = -1;
        FileService fileService = FileService.getInstance();
        fileService.logAction("Application started");
        while (mainInput != 6) {
            
            mainInput = service.MainMenu();
            switch(mainInput){
                case 1:
                    int input = -1;
                    while (input != 7) {
                        input = service.generalInformationMenu();
                        service.handleGeneralInformationMenu(input);
                    }
                    break;
                case 2:
                    input = -1;
                    while (input != 6) {
                        input = service.guestsMenu();
                        switch (input) {
                            case 1:
                                service.clearScreen();
                                service.showGuests();
                                service.waitForAnyKey();
                                break;
                            case 2:
                                service.addGuest();
                                break;
                            case 3:
                                service.clearScreen();
                                service.showGuests();
                                service.removeGuest(service.userInput());
                                break;
                            case 4:
                                service.clearScreen();
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
                    while (input != 7 ) {

                        input = service.tableMenu();
                        switch (input) {
                            case 1:
                                service.clearScreen();
                                service.showTables();
                                service.waitForAnyKey();
                                break;
                             case 2:
                                 service.addTable();
                                 break;
                            case 3:
                                service.clearScreen();                        
                                service.showTables();
                                service.removeTable(service.userInput());
                                break;
                            case 4:
                                service.clearScreen();
                                service.showTables();
                                service.editTable(service.userInput());
                                break;
                            case 5:
                                service.clearScreen();
                                service.clearAllTables();
                                break;  
                            case 6:
                                service.clearScreen();
                                service.genSeatingPlan();
                                break;
                            default:
                                break;
                        }
                        
                    }
                    break;
                case 4:
                    input = -1;
                    while (input != 5) {
                        input = service.vendorMenu();
                        switch (input) {
                            case 1:
                                service.clearScreen();
                                service.showVendors();
                                service.waitForAnyKey();
                                break;
                            case 2:
                                service.addVendor();
                                break;
                            case 3:
                                service.clearScreen();
                                service.showVendors();
                                service.removeVendor(service.userInput());
                                break;
                            case 4:
                                service.clearScreen();
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
                    break;
                case 5:
                    service.clearScreen();
                    System.out.println("Generating report...");
                    service.genWeddingReport();
                    service.waitForAnyKey();
                    break;
                case 6:
                    service.exit();
                    break;

                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }
    }
}
