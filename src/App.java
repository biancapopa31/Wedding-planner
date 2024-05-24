import java.time.LocalDate;

import model.Checklist;
import model.Guest;
import model.Person;
import model.Table;
import model.Task;
import model.Vendor;
import model.Wedding;

//TODO Debugging!!!

public class App {
    public static Wedding wedding = new Wedding();

    public static void main(String[] args) throws Exception {

        // //TODO adaugat date mai bune pentru teste
        // Person guest1 = new Guest("Doe", "John", "1234567890", InviteStatus.ATTENDING, Side.BRIDE, Role.MAID_OF_HONOR, Relationship.FAMILY);
        // Person guest2 = new Guest("Doe", "Jane", "1234567890", InviteStatus.NOT_ATTENDING, Side.BRIDE, Role.NONE, Relationship.FRIEND);
        // Person vendor1 = new Vendor("Doe", "Jane", "1234567890", "Email", 1234,"Catering", "Notes");
        
        // wedding.addGuest((Guest) guest1);
        // wedding.addGuest((Guest) guest2);

        // wedding.addVendor((Vendor) vendor1);
        
        // Table table1 = new Table(1, 10);
        // table1.addMember(guest1);

        // wedding.addTable(table1);

        // Task task1 = new Task("Task 1", "Description 1", LocalDate.now());
        // Task task2 = new Task("Task 2", "Description 2", LocalDate.now());
        // Task task3 = new Task("Task 3", "Description 3", LocalDate.now());
        // wedding.addTask(task1);
        // wedding.addTask(task2);
        // wedding.addTask(task3);


        // Checklist checklist1 = new Checklist("Checklist 1", "Description 1");
        

        Service service = new Service();
        // service.addTaskToChecklist(task3, checklist1);
        // wedding.addChecklist(checklist1);
        int mainInput = -1;
        while (mainInput != 7) {
            
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
                    while (input != 6 ) {
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
                    input = -1;
                    while (input != 6) {
                        input = service.tasksMenu();
                        switch (input) {
                            case 1:
                                service.clearScreen();
                                service.showAllTasks();
                                service.waitForAnyKey();
                                break;
                            case 2:
                                service.clearScreen();
                                service.showUnassignedTasks();
                                service.waitForAnyKey();
                                break;
                            case 3:
                                service.addTask();
                                break;
                            case 4:
                                service.clearScreen();
                                service.showAllTasks();
                                service.removeTask(service.userInput());
                                break;
                            case 5:
                                service.clearScreen();
                                service.showAllTasks();
                                service.editTask(service.userInput());
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case 6:
                    input = -1;
                    while (input != 5) {
                        input = service.checklistMenu();
                        switch (input) {
                            case 1:
                                service.clearScreen();
                                service.showChecklists();
                                service.waitForAnyKey();
                                break;
                            case 2:
                                service.addChecklist();
                                break;
                            case 3:
                                service.clearScreen();
                                service.showChecklists();
                                service.removeChecklist(service.userInput());
                                break;
                            case 4:
                                service.clearScreen();
                                service.showChecklists();
                                service.editChecklist(service.userInput());
                                break;
                            default:
                                break;
                        }
                    }
                    break;
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
