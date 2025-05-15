import java.time.LocalDate;

public class Task {
    private static int counter = 0;

    private final int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private boolean isCompleted;

    public Task(int id, String title, String description, LocalDate dueDate, Priority priority, boolean isCompleted) {
        this.id = ++counter;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return  String.format("ID: %d | %s | Due: %s | Priority: %s | %s",
                id, title, dueDate, priority, isCompleted ? "Completed" : "Pending");
    }
}
