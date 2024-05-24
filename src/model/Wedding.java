package model;
import java.time.LocalDate;
import java.util.*;

public class Wedding {

    private LocalDate date;
    private String location;

    private Person bride = new Person();
    private Person groom = new Person();
    private Person godmother = new Person();
    private Person godfather = new Person();
    // TODO: Add other persons?

    private List<Guest> guests = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();
    private List<Vendor> vendors = new ArrayList<>();
    private List<Checklist> checklists = new ArrayList<>();
    private Set<Task> tasks = new TreeSet<>();

    public Wedding() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Person getBride() {
        return bride;
    }

    public void setBride(Person bride) {
        this.bride = bride;
    }

    public Person getGroom() {
        return groom;
    }

    public void setGroom(Person groom) {
        this.groom = groom;
    }

    public Person getGodmother() {
        return godmother;
    }

    public void setGodmother(Person godmother) {
        this.godmother = godmother;
    }

    public Person getGodfather() {
        return godfather;
    }

    public void setGodfather(Person godfather) {
        this.godfather = godfather;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void addGuest(Guest guest) {
        guests.add(guest);
    }

    public void removeGuest(int index) {
        guests.remove(index);
    }

    public List<Table> getTables() {
        return tables;
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    public void removeTable(Table table) {
        tables.remove(table);
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void addVendor(Vendor vendor) {
        vendors.add(vendor);
    }

    public void removeVendor(int index) {
        vendors.remove(index);
    }

    public List<Checklist> getChecklists() {
        return checklists;
    }

    public void addChecklist(Checklist checklist) {
        checklists.add(checklist);
    }

    public void removeChecklist(int index) {
        checklists.remove(index);
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    

}
