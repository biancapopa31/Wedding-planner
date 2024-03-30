import java.time.LocalDate;

public class Task implements Comparable<Task>{

    private String name;
    private String description;
    private Boolean isComplete;
    private LocalDate dueDate;
    private int checklistId = -1;


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

    public int getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(int checklistId) {
        this.checklistId = checklistId;
    }

    @Override
    public String toString() {
        return name + "\nCompleted: " + isComplete + "\nDue date: " + dueDate + "\nDescription: " + description;
    }

    @Override
    public int compareTo(Task task) {
        int comp = this.isComplete.compareTo(task.isComplete);
        if(comp == 0){
            comp = this.dueDate.compareTo(task.dueDate);
            if(comp == 0){
                comp = this.name.compareTo(task.name);
            }

        }        
        return comp;
    }
}
