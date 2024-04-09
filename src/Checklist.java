import java.util.TreeSet;

public class Checklist {
    private static int nextId = 0;
    private String name;
    private String description;
    private TreeSet<Task> tasks;
    private int id;

    public Checklist(String name, String description, TreeSet<Task> tasks) {
        this.id = ++nextId;
        this.name = name;
        this.description = description;
        this.tasks = tasks;
    }

    public Checklist(String name, String description) {
        this.name = name;
        this.description = description;
        this.tasks = new TreeSet<>();
    }

    public Checklist(String name, TreeSet<Task> tasks) {
        this.name = name;
        this.tasks = new TreeSet<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TreeSet<Task> getTasks() {
        return tasks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void addTask(Task task) {
        task.setChecklistId(id);
        tasks.add(task);
    }

    public void removeTask(Task task) {
        task.setChecklistId(-1);
        tasks.remove(task);
    }

    public void clearChecklist() {
        for (Task task : tasks) {
            task.setChecklistId(-1);
        }
        tasks.clear();
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        String str = name + ": " + description + "\n";

        for (Task task : tasks) {
            str += task.toString() + "\n";
        }

        return str;
    }


}
