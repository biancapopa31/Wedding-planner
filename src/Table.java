import java.util.ArrayList;
import java.util.List;

public class Table {
    private int tableNumber;
    private int capacity;
    private List<Guest> members = new ArrayList<>();

    public Table(int tableNumber, int capacity, List<Guest> members) {
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

    public int getTableNumber() {
        return tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Guest> getmembers() {
        return members;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setmembers(List<Guest> members) {
        this.members = members;
    }

    public void addGuest(Guest guest) {
        members.add(guest);
    }

    public void removeGuest(Guest guest) {
        members.remove(guest);
    }

    public void clearTable() {
        members.clear();
    }

    public boolean isFull() {
        return members.size() == capacity;
    }

    @Override
    public String toString() {
        String str = "Table: " + tableNumber + "Capacity " + capacity + "\n";

        for (Guest guest : members) {
            str += guest + "\n";
        }

        return str;

    }
}
