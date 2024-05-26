import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import enums.InviteStatus;
import enums.Side;
import exceptions.TableDoesntExistException;
import exceptions.TableExistsException;
import model.*;
import repository.*;

public class Service {

    private static Service instance;

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/wedding_planner";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD= "bia123";

    private static Scanner scanner;

    private static Connection connection;

    private static FileService fileService;

    private static IWeddingRepository weddingRepository;
    private static IPersonRepository<Person> personRepository;
    private static IPersonRepository<Guest> guestRepository;
    private static IPersonRepository<Vendor> vendorRepository;
    private static ITableRepository tableRepository;

    private Service() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner = new Scanner(System.in);
        weddingRepository = new WeddingRepository(connection);
        personRepository = new PersonRepository(connection);
        guestRepository = new GuestRepository(connection);
        vendorRepository = new VendorRepository(connection);
        tableRepository = new TableRepository(connection);
        fileService = FileService.getInstance();

    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public void clearScreen(){
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
        fileService.logAction("Main menu");
        clearScreen();
        System.out.println("Welcome to your wedding planner!\n\n");
        System.out.println("1. General wedding information");
        System.out.println("2. Guests");
        System.out.println("3. Tables");
        System.out.println("4. Vendors");
        System.out.println("5. Generate statistics");
        System.out.println("6. Exit");

        return userInput();
    }

    public int generalInformationMenu(){
        fileService.logAction("General information menu");
        clearScreen();
        System.out.println("General wedding information");

        Wedding wedding = weddingRepository.getWedding();

        System.out.println("Location: " + wedding.getLocation());
        System.out.println("Date: " + wedding.getDate());
        System.out.println("Bride: " + wedding.getBride());
        System.out.println("Groom: " + wedding.getGroom());
        System.out.println("Godmother: " + wedding.getGodmother());
        System.out.println("Godfather: " + wedding.getGodfather());

        System.out.println("\n\n1. Edit location");
        System.out.println("2. Edit date");
        System.out.println("3. Edit bride");
        System.out.println("4. Edit groom");
        System.out.println("5. Edit godmother");
        System.out.println("6. Edit godfather");
        System.out.println("7. Back to main menu");

        return userInput();
    }

    public void handleGeneralInformationMenu(int input){
        Wedding wedding = weddingRepository.getWedding();
        switch (input) {
            case 1:
                editLocation(wedding);
                break;
            case 2:
                editDate(wedding);
                break;
            case 3:
                editPerson(wedding.getBride());
                break;
            case 4:
                editPerson(wedding.getGroom());
                break;
            case 5:
                editPerson(wedding.getGodmother());
                break;
            case 6:
                editPerson(wedding.getGodfather());
                break;
            default:
                break;
        }
    }

    public int guestsMenu(){
        fileService.logAction("Guest menu");
        clearScreen();
        System.out.println("Guest menu");
        System.out.println("\n\n1. Show guests");
        System.out.println("2. Add guest");
        System.out.println("3. Remove guest");
        System.out.println("4. Edit guest");
        System.out.println("5. Find guest");
        System.out.println("6. Back to main menu");

        return userInput();
    }

    public int tableMenu(){
        fileService.logAction("Table menu");
        clearScreen();
        System.out.println("Table menu");
        System.out.println("\n\n1. Show tables");
        System.out.println("2. Add table");
        System.out.println("3. Remove table");
        System.out.println("4. Edit table");
        System.out.println("5. Back to main menu");

        return userInput();
    }

    public int vendorMenu(){
        fileService.logAction("Vendor menu");
        clearScreen();
        System.out.println("Vendor menu");
        System.out.println("\n\n1. Show vendors");
        System.out.println("2. Add vendor");
        System.out.println("3. Remove vendor");
        System.out.println("4. Edit vendor");
       //TODO: System.out.println("5. Find vendor");
        System.out.println("5. Back to main menu");

        return userInput();
    }

    public void showVendors(){
        fileService.logAction("Show vendors");
        System.out.println("Vendor list");
        List<Vendor> vendors = vendorRepository.getAll();
        int n = vendors.size();
        System.out.println("Number of vendors: " + n);


        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + ". " + vendors.get(i) + "\n");
        }
    }

    public void showTables(){
        fileService.logAction("Show tables");
        System.out.println("Table list");
        List<Table> tables = tableRepository.getAll();
        int n = tables.size();
        TreeSet<Table> sortedTables = new TreeSet<Table>(tables);

        System.out.println("Number of tables: " + n);


        for (Table table : sortedTables) {
            System.out.println(table+ "\n");
          }
          
    }

    public void showGuests(){
        fileService.logAction("Show guests");
        System.out.println("Guest list");
        List<Guest> guests = guestRepository.getAll();
        int n = guests.size();

        System.out.println("Number of guests: " + n);

        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + ". " + guests.get(i) + "\n");
        }
    }

    public void addVendor(){
        fileService.logAction("Add vendor");
        clearScreen();
        System.out.println("Add vendor");
        Person newPerson = createPersonFromUserInput();
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter service type: ");
        scanner.nextLine();
        String serviceType = scanner.nextLine();
        System.out.print("Enter notes: ");
        String notes = scanner.nextLine();

        Vendor newVendor = new Vendor(newPerson, email, price, serviceType, notes);

        vendorRepository.add(newVendor);
    }

    public void addTable(){
        fileService.logAction("Add table");
        clearScreen();
        System.out.println("Add table");
        System.out.print("Enter table number: ");
        int tableNumber = scanner.nextInt();
        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();

        Table newTable = new Table(tableNumber, capacity);

        try{
            tableRepository.add(newTable);
        }catch(TableExistsException e){
            System.out.println(e.getMessage());
            waitForAnyKey();
        }
    }

    public void addGuest(){
        fileService.logAction("Add guest");
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

        guestRepository.add(newGuest);
    }

    public Person createPersonFromUserInput(){
        fileService.logAction("Create person from user input");
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
        fileService.logAction("Remove vendor");
        clearScreen();
        Vendor vendor = vendorRepository.getAll().get(index - 1);
        vendorRepository.delete(vendor);
    }

    public void removeGuest(int index){
        fileService.logAction("Remove guest");
        clearScreen();
        Guest guest = guestRepository.getAll().get(index - 1);
        guestRepository.delete(guest);
    }


    public void removeTable(int index){
        fileService.logAction("Remove table");
        clearScreen();
        try{
            Table table = tableRepository.get(index);
            tableRepository.delete(table);
        }catch(TableDoesntExistException e){
            System.out.println(e.getMessage());
            waitForAnyKey();
        }
    }


    public void editVendor(int index){
        fileService.logAction("Edit vendor");
        clearScreen();
        Vendor vendor = vendorRepository.getAll().get(index - 1);

        int input = -1;
        while (input != 8) {
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
        vendorRepository.update(vendor);
    }

    public void editGuest(int index){
        fileService.logAction("Edit guest");
        clearScreen();
        Guest guest = guestRepository.getAll().get(index - 1);

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
        guestRepository.update(guest);
    }

    public void editTable(int index){
        fileService.logAction("Edit table");
        clearScreen();
        Table table = new Table();
        try{
            table = tableRepository.get(index);
        }catch(TableDoesntExistException e){
            System.out.println(e.getMessage());
            waitForAnyKey();
            return;
        }
        List<Person> members = table.getmembers();

        int input = -1;
        while (input != 5) {
            clearScreen();
            System.out.println("Edit table");
            System.out.println(table);

            System.out.println("\n\n1. Edit capacity");
            System.out.println("2. Add member");
            System.out.println("3. Remove member");
            System.out.println("4. Clear table");
            System.out.println("5. Back to main menu");
            input = userInput();

            // TODO: verify if the new capacity is greater than the number of members

            switch (input) {
                case 1:
                    System.out.print("Enter new capacity: ");
                    int newCapacity = scanner.nextInt();
                    table.setCapacity(newCapacity);
                    break;
                case 2:
                    if(table.isFull()){
                        System.out.println("Table is full");
                        waitForAnyKey();
                        break;
                    }
                    clearScreen();
                    System.out.println("Enter memeber type (1. Guest, 2. Vendor): ");
                    int memberType = scanner.nextInt();

                    if(memberType == 1)
                        showGuests();
                    else
                        showVendors();
                    System.out.print("Enter member index: ");
                    int newMemberIndex = scanner.nextInt();
                    Person newMember;
                    if(memberType == 1)
                        newMember = guestRepository.getAll().get(newMemberIndex - 1);
                    else
                        newMember = vendorRepository.getAll().get(newMemberIndex - 1);
                    if(newMember.getTableNumber() != 0){
                        System.out.println("This person is already assigned to table " + newMember.getTableNumber());
                        System.out.println("Do you want change the table? (y/n)");
                        String changeTable = scanner.next();
                        if(changeTable.equals("y")){
                            table.addMember(newMember);
                        } else {
                            break;
                        }
                    }else{
                        table.addMember(newMember);
                    }
                    break;
                case 3:
                    System.out.print("Enter member index: ");
                    table.removeMember(members.get(scanner.nextInt() - 1));
                    break;                    
                case 4:
                    table.clearTable();
                    break;
                default:
                    break;
            }
        }
        tableRepository.update(table);
    }

    public void findGuestFromName(){
        fileService.logAction("Find guest from name");
        clearScreen();
        System.out.println("Find guest");
        System.out.print("Enter last name or -: ");
        String lastName = scanner.next().toLowerCase();
        System.out.print("Enter first name or -: ");
        String firstName = scanner.next().toLowerCase();

        List<Guest> guests = guestRepository.getAll();
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
        fileService.logAction("Edit person");
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
        personRepository.update(person);
    }

    public void editLocation(Wedding wedding){
        fileService.logAction("Edit wedding location");
        clearScreen();
        System.out.println("Edit location");
        System.out.println("Current location: " + wedding.getLocation());
        System.out.print("Enter new location: ");
        scanner.nextLine();
        wedding.setLocation(scanner.nextLine());
        
        weddingRepository.updateWedding(wedding);
    }

    public void editDate(Wedding wedding){
        fileService.logAction("Edit wedding date");
        clearScreen();
        System.out.println("Edit date");
        System.out.println("Current date: " + wedding.getDate());
        System.out.println("Date format: YYYY-MM-DD");
        System.out.print("Enter new date: ");
        String date = scanner.next();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        wedding.setDate(LocalDate.parse(date, formatter));

        weddingRepository.updateWedding(wedding);
    }

    public void genWeddingReport() {
        String WeddingReportFile = "./files/weddingReport.txt";
        Wedding wedding = weddingRepository.getWedding();
        Person bride = wedding.getBride();
        Person groom = wedding.getGroom();
        Person godmother = wedding.getGodmother();
        Person godfather = wedding.getGodfather();
    
        List<Guest> guests = guestRepository.getAll();
        List<Vendor> vendors = vendorRepository.getAll();

        try (PrintWriter writer = new PrintWriter(WeddingReportFile)) {
            writer.println("\t\tWedding Report\n\n");
            writer.println("Date: " + wedding.getDate());
            writer.println("Location: " + wedding.getLocation());
            writer.println("\n\nBride: " + bride.getFirstName() + " " + bride.getLastName());
            writer.println("Groom: " + groom.getFirstName() + " " +groom.getLastName());
            writer.println("\nGodmother: " + godmother.getFirstName() + " " + godmother.getLastName());
            writer.println("Godfather: " + godfather.getFirstName() + " " + godfather.getLastName());

            writer.println("\n\nGuest statistics:");
            int totalNumberOfGuests = guests.size();
            List <Guest> attendingGuests = guests.stream().filter(guest -> guest.getInviteStatus() == InviteStatus.ATTENDING).collect(Collectors.toList());
            writer.println("Number of guests: " + totalNumberOfGuests);
            writer.println("Number of guests attending: " + attendingGuests.size());
            writer.println(attendingGuests.size()*100/totalNumberOfGuests + "% of guests are attending");
            List <Guest> notInvited = guests.stream().filter(guest -> guest.getInviteStatus() == InviteStatus.NOT_SENT).collect(Collectors.toList());
            writer.println("Number of unsent invitations: " + notInvited.size());
            List <Guest> notAttending = guests.stream().filter(guest -> guest.getInviteStatus() == InviteStatus.NOT_ATTENDING).collect(Collectors.toList());
            writer.println("Number of guests not attending: " + notAttending.size());


            List <Guest> guestsFromBridesSide = guests.stream().filter(guest -> guest.getSide() == Side.BRIDE).collect(Collectors.toList());
            writer.println("\nGuests from bride's side: " + guestsFromBridesSide.size());
            writer.println(guestsFromBridesSide.size()*100/totalNumberOfGuests + "% of guests are from bride's side");
            writer.println("Guests from groom's side: " + (totalNumberOfGuests - guestsFromBridesSide.size()));
            writer.println((totalNumberOfGuests - guestsFromBridesSide.size())*100/totalNumberOfGuests + "% of guests are from groom's side");

            writer.println("\n\nVendor statistics:");
            int totalNumberOfVendors = vendors.size();
            writer.println("Number of vendors: " + totalNumberOfVendors);
            int weddingCost = vendors.stream().mapToInt(vendor -> (int) vendor.getPrice()).sum();
            writer.println("Total cost of wedding: " + weddingCost);
            Vendor mostExpensiveVendor = null;
            double max = 0;
            for (Vendor vendor : vendors) {
                if (vendor.getPrice() > max) {
                    max = vendor.getPrice();
                    mostExpensiveVendor = vendor;
                }
                
            }
            writer.println("Most expensive vendor: " + mostExpensiveVendor.getFirstName() + " " + mostExpensiveVendor.getLastName() + "," + 
                            mostExpensiveVendor.getServiceType() + " - " + mostExpensiveVendor.getPrice());

        } catch (FileNotFoundException e) {
            fileService.logAction("Error: Wedding report file not found");
            e.printStackTrace();  
        }
    }
    

}

