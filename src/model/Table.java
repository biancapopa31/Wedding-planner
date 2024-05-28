package model;
import java.util.ArrayList;
import java.util.List;

public class Table implements Comparable<Table> {

    private int tableNumber;
    private int capacity;
    private List<Person> members = new ArrayList<>();

    public Table(int tableNumber, int capacity, List<Person> members) {
        if (capacity > members.size())
            throw new IllegalArgumentException("Too many members at the table.");

        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.members = members;
    }

    public Table(int tableNumber, int capacity) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
    }

    public Table(){

    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Person> getmembers() {
        return members;
    }

    public void setTableNumber(int tableNumber) {
        for (Person guest : members) {
            guest.setTableNumber(tableNumber);
        }
        this.tableNumber = tableNumber;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    public void addMember(Person guest) {
        guest.setTableNumber(tableNumber);
        members.add(guest);
    }

    public void removeMember(Person guest) {
        guest.setTableNumber(0);
    }

    public void clearTable() {
        for (Person guest : members) {
            guest.setTableNumber(0);
        }
    }

    public boolean isFull() {
        return members.size() >= capacity;
    }

    @Override
    public String toString() {
        String str = "Table: " + tableNumber + " Capacity: " + capacity + "\n";
        int i = 1;

        for (Person guest : members) {
            str += i + ". " + (guest instanceof Guest ? "Guest\n" : "Vendor\n") + guest + "\n\n";
            i++;
        }

        return str;

    }

    @Override
    public int compareTo(Table o) {
        return o.members.size() - members.size() == 0 ? o.capacity - capacity : o.members.size() - members.size();
    }
}