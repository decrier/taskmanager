import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Вы хотите добавить новое задание? Y / N");
            System.out.println("Или вывести список всех задач? P");
            String answer = scan.nextLine().toLowerCase();
            if (answer.equals("y")) {
                System.out.print("Введите ID: ");
                int id = scan.nextInt();
                scan.nextLine();
                System.out.print("Введите название: ");
                String title = scan.nextLine();
                System.out.print("Введите описание: ");
                String description = scan.nextLine();
                System.out.print("Введите дедлайн в формате YYYY-MM-DD: ");
                String date = scan.nextLine();
                LocalDate dueDate = LocalDate.parse(date);
                System.out.print("Введите приоритет задачи (HIGH/MEDIUM/LOW): ");
                Priority priority = Priority.valueOf(scan.nextLine().toUpperCase());
                Task task = new Task(id, title, description, dueDate, priority, false);
                tasks.add(task);
            } else if (answer.equals("p")) {
                System.out.println(tasks);
            } else {
                break;
            }
        }
    }
}
