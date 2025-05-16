import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {

    static LocalDate readDueDate(Scanner scan) {
        while (true) {
            System.out.print("Срок выполнения (YYYY-MM-DD): ");
            String line = scan.nextLine();
            try {
                LocalDate dueDate = LocalDate.parse(line);
                return dueDate;
            } catch (DateTimeException e) {
                System.out.println("Неверный формат даты. Повторите ввод.");
            }
        }
    }

    static Priority readPriority (Scanner scan) {
        while (true) {
            System.out.print("Приоритет (HIGH/MEDIUM/LOW): ");
            String line = scan.nextLine().trim().toUpperCase();
            try {
                return Priority.valueOf(line);
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный вид приоритета. Повторите ввод");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.println("\nКоманда:\n[A]dd, [L]ist, [R]emove, [C]omplete"
                    + "\n[U]ncompleted, [O]verdue, [S]earch"
                    + "\n[D]ueSort, [P]rioSort, [G]roup"
                    + "\n[SV] Save, [LD] Load, [Q]uit");
            String cmd = scan.nextLine().trim().toUpperCase();

            switch (cmd) {
                case "A":
                    System.out.print("Название: ");
                    String title = scan.nextLine();
                    System.out.print("Описание: ");
                    String desc = scan.nextLine();
                    LocalDate dueDate = readDueDate(scan);
                    Priority priority = readPriority(scan);
                    manager.addTask(new Task(title, desc, dueDate, priority));
                    System.out.println("Добавлено");
                    break;

                case "L":
                    System.out.println("Список текущих задач");
                    manager.getAllTasks().forEach(System.out::println);
                    break;

                case "R":
                    System.out.print("Введите ID задачи для удаления: ");
                    int remId = Integer.parseInt(scan.nextLine());
                    if (manager.removeTask(remId)) {
                        System.out.println("Задача удалена.");
                    } else {
                        System.out.println("Задача с таким ID не найдена");
                    }
                    break;

                case "C":
                    System.out.print("Пометить задачу как выполненную, ID=");
                    int compId = Integer.parseInt(scan.nextLine());
                    if (manager.markCompleted(compId)) {
                        System.out.println("Задача завершена.");
                    } else {
                        System.out.println("Задача с таким ID не найдена");
                    }
                    break;


                case "U":
                    System.out.println("Незавершенные задачи:\n");
                    manager.getUncompletedTasks().forEach(System.out::println);
                    break;

                case "O":
                    System.out.println("Просроченные задачи:\n");
                    manager.getAllTasks().stream()
                            .filter(t -> !t.isCompleted() && t.getDueDate().isBefore(LocalDate.now()))
                            .forEach(System.out::println);
                    break;

                case "S":
                    System.out.print("Введите ключевое слово для поиска: ");
                    String keyWord = scan.nextLine().trim().toLowerCase();
                    manager.searchTasks(keyWord).forEach(System.out::println);
                    break;

                case "DU":
                    System.out.println("Задачи, отсортированные по дате:");
                    manager.sortByDueDates().forEach(System.out::println);
                    break;

                case "PR":
                    System.out.println("Задачи, отсортированные по приоритету:");
                    manager.sortByPriority().forEach(System.out::println);
                    break;

                case "G":
                    System.out.println("Группировка по приоритету:");
                    manager.groupByPriority().forEach((prio, list) -> {
                        System.out.println("\n" + prio + ":");
                        list.forEach(System.out::println);
                    });
                    break;

                case "SV":
                    System.out.print("Сохранить как: ");
                    String saveFile = scan.nextLine();
                    try {
                        manager.saveToFile(saveFile);
                        System.out.println("Сохранено в " + saveFile);
                    } catch (IOException e) {
                        System.out.println("Ошибка при сохранении: " + e.getMessage());
                    }
                    break;

                case "LD":
                    System.out.print("Загрузить из файла: ");
                    String loadFile = scan.nextLine().trim();
                    try {
                        manager.loadFromFile(loadFile);
                        System.out.println("Загружено из файла: " + loadFile);
                    } catch (IOException e) {
                        System.out.println("Ошибка при загрузке: " + e.getMessage());
                    }
                    break;

                case "Q":
                    System.out.println("Ок, пока!");
                    return;

                default:
                    System.out.println("Неизвестная команда");

            }
        }
    }
}
