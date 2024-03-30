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
        System.out.println("Guest menu");
        System.out.println("Number of guests: " + App.wedding.getGuests().size());
        System.out.println("\n\n1. Show guests");
        System.out.println("2. Add guest");
        System.out.println("3. Remove guest");
        System.out.println("4. Edit guest");
        System.out.println("5. Find guest");
        System.out.println("6. Back to main menu");

        return userInput();
    }

    public int tableMenu(){
        clearScreen();
        System.out.println("Table menu");
        System.out.println("Number of tables: " + App.wedding.getTables().size());
        System.out.println("\n\n1. Show tables");
        System.out.println("2. Add table");
        System.out.println("3. Remove table");
        System.out.println("4. Edit table");
        System.out.println("5. Find table");
        System.out.println("6. Back to main menu");

        return userInput();
    }

    public int vendorMenu(){
        clearScreen();
        System.out.println("Vendor menu");
        System.out.println("Number of vendors: " + App.wedding.getVendors().size());
        System.out.println("\n\n1. Show vendors");
        System.out.println("2. Add vendor");
        System.out.println("3. Remove vendor");
        System.out.println("4. Edit vendor");
       // System.out.println("5. Find vendor");
        System.out.println("5. Back to main menu");

        return userInput();
    }

    public void showVendors(){
        clearScreen();
        System.out.println("Vendor list");
        List<Vendor> vendors = App.wedding.getVendors();
        int n = vendors.size();

        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + ". " + vendors.get(i) + "\n");
        }
    }

    public void showTables(){
        clearScreen();
        System.out.println("Table list");
        List<Table> tables = App.wedding.getTables();
        int n = tables.size();

        for (int i = 0; i < n; i++) {
            System.out.println(tables.get(i)+"\n");
        }
    }

    public void showGuests(){
        clearScreen();
        System.out.println("Guest list");
        List<Guest> guests = App.wedding.getGuests();
        int n = guests.size();

        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + ". " + guests.get(i) + "\n");
        }
    }

    public void addVendor(){
        clearScreen();
        System.out.println("Add vendor");
        Person newPerson = createPersonFromUserInput();
        System.out.println("Enter email: ");
        String email = scanner.next();
        System.out.println("Enter price: ");
        double price = scanner.nextDouble();
        System.out.println("Enter service type: ");
        String serviceType = scanner.next();
        System.out.println("Enter notes: ");
        String notes = scanner.next();

        Vendor newVendor = new Vendor(newPerson, email, price, serviceType, notes);

        App.wedding.addVendor(newVendor);
    }

    public void addTable(){
        clearScreen();
        System.out.println("Add table");
        System.out.print("Enter table number: ");
        int tableNumber = scanner.nextInt();
        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();

        Table newTable = new Table(tableNumber, capacity);

        App.wedding.addTable(newTable);
    }

    public void addGuest(){
        clearScreen();
        System.out.println("Add guest");
        Person newPerson = createPersonFromUserInput();
        System.out.print("Enter invite status (1.ATTENDING, 2.NOT_ATTENDING, 3.SENT, 4.NOT_SENT): ");
        int inviteStatus = scanner.nextInt()-1;
        System.out.print("Enter side (1.BRIDE, 2.GROOM): ");
        int side = scanner.nextInt()-1;
        System.out.print("Enter role (1.MAID_OF_HONOR, 2.BEST_MAN, 3.NONE): ");
        int role = scanner.nextInt()-1;
        System.out.print("Enter relationship (1.FAMILY, 2.FRIEND, 3.OTHER): ");
        int relationship = scanner.nextInt()-1;

        Guest newGuest = new Guest(newPerson, inviteStatus, side, role, relationship);

        App.wedding.addGuest(newGuest);
    }

    public Person createPersonFromUserInput(){
        System.out.print("Enter last name: ");
        String lastName = scanner.next();
        System.out.print("Enter first name: ");
        String firstName = scanner.next();
        System.out.print("Enter telephone: ");
        String telephone = scanner.next();

        Person newPerson = new Person(lastName, firstName, telephone);

        return newPerson;
    }

    public void removeVendor(int index){
        clearScreen();
        App.wedding.removeVendor(index - 1);
    }

    public void removeGuest(int index){
        clearScreen();
        App.wedding.removeGuest(index - 1);
    }


    public void removeTable(int index){
        clearScreen();

        Table table = findTable(index);

        table.clearTable();
        
        App.wedding.removeTable(table);

    }

    public void editVendor(int index){
        clearScreen();
        Vendor vendor = App.wedding.getVendors().get(index - 1);

        int input = -1;
        while (input != 5) {
            clearScreen();
            System.out.println("Edit vendor");
            System.out.println(vendor);

            System.out.println("\n\n1. Edit last name");
            System.out.println("2. Edit first name");
            System.out.println("3. Edit telephone");
            System.out.println("4. Edit email");
            System.out.println("5. Edit price");
            System.out.println("6. Edit service type");
            System.out.println("7. Edit notes");
            System.out.println("8. Back");
            input = userInput();

            switch (input) {
                case 1:
                    System.out.print("Enter new last name: ");
                    vendor.setLastName(scanner.next());
                    break;
                case 2:
                    System.out.print("Enter new first name: ");
                    vendor.setFirstName(scanner.next());
                    break;
                case 3:
                    System.out.print("Enter new telephone: ");
                    vendor.setTelephone(scanner.next());
                    break;
                case 4:
                    System.out.print("Enter new email: ");
                    vendor.setEmail(scanner.next());
                    break;
                case 5:
                    System.out.print("Enter new price: ");
                    vendor.setPrice(scanner.nextDouble());
                    break;
                case 6:
                    System.out.print("Enter new service type: ");
                    scanner.nextLine();
                    vendor.setServiceType(scanner.nextLine());
                    break;
                case 7:
                    System.out.print("Enter new notes: ");
                    scanner.nextLine();
                    vendor.setNotes(scanner.nextLine());
                    break;
                default:
                    break;
            }
        }
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
            System.out.println("8. Back");
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

    public void editTable(int index){
        clearScreen();
        Table table = findTable(index);
        List<Person> members = table.getmembers();

        int input = -1;
        while (input != 3) {
            clearScreen();
            System.out.println("Edit table");
            System.out.println(table);

            System.out.println("\n\n1. Edit table number");
            System.out.println("2. Edit capacity");
            System.out.println("3. Add member");
            System.out.println("4. Remove member");
            System.out.println("5. Clear table");
            System.out.println("6. Back to main menu");
            input = userInput();

            switch (input) {
                case 1:
                    System.out.print("Enter new table number: ");
                    int newTableNumber = scanner.nextInt();
                    if (findTable(newTableNumber) != null) {
                        System.out.println("Table number already exists");
                        break;
                    }
                    table.setTableNumber(newTableNumber);
                    break;
                case 2:
                    System.out.print("Enter new capacity: ");
                    int newCapacity = scanner.nextInt();
                    if(newCapacity < members.size()){
                        System.out.println("New capacity is less than the number of members in the table");
                        break;
                    }
                    table.setCapacity(newCapacity);
                    break;
                case 3:
                    if(table.isFull()){
                        System.out.println("Table is full");
                        break;
                    }
                    //TODO: Add vendor
                    showGuests();
                    System.out.print("Enter member index: ");
                    int newMemberIndex = scanner.nextInt();
                    Person newMember = App.wedding.getGuests().get(newMemberIndex - 1);
                    if(newMember.getTableNumber() != -1){
                        System.out.println("Guest is already assigned to table " + newMember.getTableNumber());
                        System.out.println("Do you want change the table? (y/n)");
                        String changeTable = scanner.next();
                        if(changeTable.equals("y")){
                            findTable(newMember.getTableNumber()).removeMember(newMember);
                            table.addMember(newMember);
                        } else {
                            break;
                        }
                    }else{
                        table.addMember(newMember);
                    }
                   
                    break;
                case 4:
                    System.out.print("Enter member index: ");
                    table.removeMember(members.get(scanner.nextInt() - 1));
                    break;                    
                case 5:
                    table.clearTable();
                    break;
                default:
                    break;
            }
        }
    }

    public Table findTable(int index){
        List<Table> tables = App.wedding.getTables();
        int n = tables.size();
        for(int i = 0; i < n; i++){
            if(tables.get(i).getTableNumber() == index){
                return tables.get(i);
            }
        }
        return null;
    }

    public void findGuestFromName(){
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

