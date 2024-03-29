public class App {
    public static Wedding wedding = new Wedding();

    public static void main(String[] args) throws Exception {

        Guest guest1 = new Guest("Doe", "John", "1234567890", InviteStatus.ATTENDING, Side.BRIDE, Role.MAID_OF_HONOR, Relationship.FAMILY);
        Guest guest2 = new Guest("Doe", "Jane", "1234567890", InviteStatus.NOT_ATTENDING, Side.BRIDE, Role.NONE, Relationship.FRIEND);
        wedding.addGuest(guest1);
        wedding.addGuest(guest2);
        
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
                                service.removeGuest(service.getGuestIdFromUser());
                                break;
                            case 4:
                                service.editGuest(service.getGuestIdFromUser());
                                break;
                            case 5:
                                service.findGuest();
                                service.waitForAnyKey();
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
