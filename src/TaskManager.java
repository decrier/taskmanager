import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean removeTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }

    public boolean markCompleted(int id) {
        for (Task task: tasks) {
            if (task.getId() == id) {
                task.setCompleted(true);
                return true;
            }
        }
        return false;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public List<Task> getCompletedTasks() {
        return tasks.stream()
                .filter(Task::isCompleted)
                .collect(Collectors.toList());
    }

    public List<Task> getUncompletedTasks() {
        return tasks.stream()
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList());
    }

    public List<Task> searchTasks(String keyword) {
        return tasks.stream()
                .filter(task ->
                        task.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        || task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Task> sortByDueDates() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
    }

    public List<Task> sortByPriority() {
        return tasks.stream()
                .sorted(Comparator.comparingInt(task -> -task.getPriority().getLevel()))
                .collect(Collectors.toList());
    }

    // Группировка задач по приоритету
    public Map<Priority,List<Task>> groupByPriority(){
        return tasks.stream()
                .collect(Collectors.groupingBy(Task::getPriority));
    }
}
