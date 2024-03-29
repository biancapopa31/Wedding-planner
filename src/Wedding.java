import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Wedding {

    private LocalDate date;
    private String location;

    private Person bride = new Person();
    private Person groom = new Person();
    private Person Godmother = new Person();
    private Person Godfather = new Person();
    // TODO: Add other persons?

    private List<Guest> guests = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();
    private List<Vendor> vendors = new ArrayList<>();
    private List<Checklist> checklists = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();

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
        return Godmother;
    }

    public void setGodmother(Person Godmother) {
        this.Godmother = Godmother;
    }

    public Person getGodfather() {
        return Godfather;
    }

    public void setGodfather(Person Godfather) {
        this.Godfather = Godfather;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    

}
