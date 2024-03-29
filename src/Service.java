import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


    public int generalInformationMeniu(){
        int input = -1;

        while (input != 7) {
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
            input = userInput();
            switch (input) {
                case 1:
                    editLocation();
                    break;
                case 2:
                    editDate();
                //TODO: Implement edit date
                    break;
                case 3:
                    editPerson(App.wedding.getBride());
                    break;
                case 4:
                    editPerson(App.wedding.getGroom());
                    break;
                case 5:
                    editPerson(App.wedding.getGodmother());
                    break;
                case 6:
                    editPerson(App.wedding.getGodfather());
                    break;
                default:
                    break;
            }
        }

        return input;
    }

    public void editPerson(Person person){
        int input = -1;
        while (input != 4) {
            clearScreen();
            System.out.println("Edit person");
            System.out.println("Last name: " + person.getLastName());
            System.out.println("First name: " + person.getFirstName());
            System.out.println("Telephone: " + person.getTelephone());

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

