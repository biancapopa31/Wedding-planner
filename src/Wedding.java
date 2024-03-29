import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Wedding {

    private LocalDate date;
    private String location;

    private Person bride;
    private Person groom;
    private Person Godmother;
    private Person Godfather;
    // TODO: Add other persons?

    private List<Guest> guests = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();
    private List<Vendor> vendors = new ArrayList<>();
    private List<Checklist> checklists = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();

    

}
