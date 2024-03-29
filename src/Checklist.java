import java.util.TreeSet;

public class Checklist {
    private String name;
    private String description;
    private TreeSet<Task> tasks;

    public Checklist(String name, String description, TreeSet<Task> tasks) {
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

    public void setTasks(TreeSet<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void clearTasks() {
        tasks.clear();
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
