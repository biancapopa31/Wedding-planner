import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Service {

    Scanner scanner = new Scanner(System.in);

    private void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public int userInput(){
        System.out.print("\n\nPlease enter your choice: ");
        int userInput = scanner.nextInt();
        return userInput;
    }

    public void exit(){
        System.exit(0);
    }

    public void waitForAnyKey(){
        System.out.println("\n\nPress any key to continue...");
        scanner.next();
    }

    public int MainMenu(){
        clearScreen();
        System.out.println("Welcome to your wedding planner!\n\n");
        System.out.println("1. General wedding information");
        System.out.println("2. Guest list");
        System.out.println("3. Table list");
        System.out.println("4. Vendor list");
        System.out.println("5. Checklists");
        System.out.println("6. Task list");
        System.out.println("7. Exit");

        
        return userInput();
        
    }


    public int generalInformationMenu(){
            clearScreen();
            System.out.println("General wedding information");

            System.out.println("Location: " + App.wedding.getLocation());
            System.out.println("Date: " + App.wedding.getDate());
            System.out.println("Bride: " + App.wedding.getBride());
            System.out.println("Groom: " + App.wedding.getGroom());
            System.out.println("Godmother: " + App.wedding.getGodmother());
            System.out.println("Godfather: " + App.wedding.getGodfather());

            System.out.println("\n\n1. Edit location");
            System.out.println("2. Edit date");
            System.out.println("3. Edit bride");
            System.out.println("4. Edit groom");
            System.out.println("5. Edit godmother");
            System.out.println("6. Edit godfather");
            System.out.println("7. Back to main menu");

        return userInput();
    }

    public int guestsMenu(){
        clearScreen();
        System.out.println("Guest list");
        System.out.println("Number of guests: " + App.wedding.getGuests().size());
        System.out.println("\n\n1. Show guests");
        System.out.println("2. Add guest");
        System.out.println("3. Remove guest");
        System.out.println("4. Edit guest");
        System.out.println("5. Find guest");
        System.out.println("6. Back to main menu");

        return userInput();
    }

    public void showGuests(){
        clearScreen();
        System.out.println("Guest list");
        List<Guest> guests = App.wedding.getGuests();
        int n = guests.size();

        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + ". " + guests.get(i));
        }
    }

    public void addGuest(){
        clearScreen();
        System.out.println("Add guest");
        System.out.print("Enter last name: ");
        String lastName = scanner.next();
        System.out.print("Enter first name: ");
        String firstName = scanner.next();
        System.out.print("Enter telephone: ");
        String telephone = scanner.next();
        System.out.print("Enter invite status (1.ATTENDING, 2.NOT_ATTENDING, 3.SENT, 4.NOT_SENT): ");
        int inviteStatus = scanner.nextInt()-1;
        System.out.print("Enter side (1.BRIDE, 2.GROOM): ");
        int side = scanner.nextInt()-1;
        System.out.print("Enter role (1.MAID_OF_HONOR, 2.BEST_MAN, 3.NONE): ");
        int role = scanner.nextInt()-1;
        System.out.print("Enter relationship (1.FAMILY, 2.FRIEND, 3.OTHER): ");
        int relationship = scanner.nextInt()-1;

        Guest newGuest = new Guest(lastName, firstName, telephone, inviteStatus, side, role, relationship);

        App.wedding.addGuest(newGuest);
    }

    public int getGuestIdFromUser(){
        showGuests();
        return userInput();

    }

    public void removeGuest(int index){
        clearScreen();
        App.wedding.getGuests().remove(index - 1);
    }

    public void editGuest(int index){
        clearScreen();
        Guest guest = App.wedding.getGuests().get(index - 1);

        int input = -1;
        while (input != 8) {
            clearScreen();
            System.out.println("Edit guest");
            System.out.println(guest);

            System.out.println("\n\n1. Edit last name");
            System.out.println("2. Edit first name");
            System.out.println("3. Edit telephone");
            System.out.println("4. Edit invite status");
            System.out.println("5. Edit side");
            System.out.println("6. Edit role");
            System.out.println("7. Edit relationship");
            System.out.println("8. Back to main menu");
            input = userInput();

            switch (input) {
                case 1:
                    System.out.print("Enter new last name: ");
                    guest.setLastName(scanner.next());
                    break;
                case 2:
                    System.out.print("Enter new first name: ");
                    guest.setFirstName(scanner.next());
                    break;
                case 3:
                    System.out.print("Enter new telephone: ");
                    guest.setTelephone(scanner.next());
                    break;
                case 4:
                    System.out.print("Enter new invite status (1.ATTENDING, 2.NOT_ATTENDING, 3.SENT, 4.NOT_SENT): ");
                    guest.setInviteStatus(scanner.nextInt());
                    break;
                case 5:
                    System.out.print("Enter new side (1.BRIDE, 2.GROOM): ");
                    guest.setSide(scanner.nextInt());
                    break;
                case 6:
                    System.out.print("Enter new role (1.MAID_OF_HONOR, 2.BEST_MAN, 3.NONE): ");
                    guest.setRole(scanner.nextInt());
                    break;
                case 7:
                    System.out.print("Enter new relationship (1.FAMILY, 2.FRIEND, 3.OTHER): ");
                    guest.setRelationship(scanner.nextInt());
                    break;
                default:
                    break;
            }
        }
    }

    public void findGuest(){
        clearScreen();
        System.out.println("Find guest");
        System.out.print("Enter last name or -: ");
        String lastName = scanner.next().toLowerCase();
        System.out.print("Enter first name or -: ");
        String firstName = scanner.next().toLowerCase();

        List<Guest> guests = App.wedding.getGuests();
        int n = guests.size();
        boolean found = false;

        for (int i = 0; i < n; i++) {
            Guest guest = guests.get(i);
            if (lastName.equals("-") && guest.getFirstName().toLowerCase().equals(firstName)) {
                System.out.println(guests.get(i));
                found = true;
            } else if (firstName.equals("-") && guest.getLastName().toLowerCase().equals(lastName)) {
                System.out.println(guests.get(i));
                found = true;
            } else if (guest.getLastName().toLowerCase().equals(lastName) && guest.getFirstName().toLowerCase().equals(firstName)) {
                System.out.println(guests.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No guests found");
        }
    }

    public void editPerson(Person person){
        int input = -1;
        while (input != 4) {
            clearScreen();
            System.out.println("Edit person");
            System.out.println(person);

            System.out.println("\n\n1. Edit last name");
            System.out.println("2. Edit first name");
            System.out.println("3. Edit telephone");
            System.out.println("4. Back to main menu");
            input = userInput();

            switch (input) {
                case 1:
                    System.out.print("Enter new last name: ");
                    person.setLastName(scanner.next());
                    break;
                case 2:
                    System.out.print("Enter new first name: ");
                    person.setFirstName(scanner.next());
                    break;
                case 3:
                    System.out.print("Enter new telephone: ");
                    person.setTelephone(scanner.next());
                    break;
                default:
                    break;
            }
        }
    }

    public void editLocation(){
        clearScreen();
        System.out.println("Edit location");
        System.out.println("Current location: " + App.wedding.getLocation());
        System.out.print("Enter new location: ");
        App.wedding.setLocation(scanner.next());
    }

    public void editDate(){
        clearScreen();
        System.out.println("Edit date");
        System.out.println("Current date: " + App.wedding.getDate());
        System.out.println("Date format: YYYY-MM-DD");
        System.out.print("Enter new date: ");
        String date = scanner.next();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        App.wedding.setDate(LocalDate.parse(date, formatter));

    }
}

