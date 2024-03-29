import java.time.LocalDate;

public class Task {

    private String name;
    private String description;
    private Boolean isComplete;
    private LocalDate dueDate;

    public Task(String name, String description, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isComplete = false;
    }

    public Task(String name) {
        this.name = name;
        this.isComplete = false;
    }

    public Task(String name, LocalDate dueDate) {
        this.name = name;
        this.dueDate = dueDate;
        this.isComplete = false;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.isComplete = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return name + "Completed: " + isComplete + " Due date: " + dueDate + "\nDescription: " + description;
    }

    //@Override
    public int compareTo(Task task) {
        return this.dueDate.compareTo(task.dueDate) == 0 ? -this.isComplete.compareTo(task.isComplete) : this.dueDate.compareTo(task.dueDate);
    }

}
