import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Task {
    private int id;
    private String title;
    private boolean completed;

    public Task(int id, String title) {
        this.id = id;
        this.title = title;
        this.completed = false;
    }

    
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    
    public void setTitle(String title) {
        this.title = title;
    }
}

class TaskManager {
    private List<Task> tasks;
    private int nextId;

    public TaskManager() {
        tasks = new ArrayList<>();
        nextId = 1;
    }

    public void addTask(String title) {
        Task task = new Task(nextId++, title);
        tasks.add(task);
    }

    public void updateTask(int id, String title, boolean completed) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setTitle(title);
                task.setCompleted(completed);
                break;
            }
        }
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Task Manager ===");
            System.out.println("1. List Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    List<Task> tasks = taskManager.getAllTasks();
                    System.out.println("=== Task List ===");
                    for (Task task : tasks) {
                        System.out.println("ID: " + task.getId());
                        System.out.println("Title: " + task.getTitle());
                        System.out.println("Completed: " + (task.isCompleted() ? "Yes" : "No"));
                        System.out.println();
                    }
                    break;
                case 2:
                    System.out.print("Enter task title: ");
                    String newTitle = scanner.nextLine();
                    taskManager.addTask(newTitle);
                    System.out.println("Task added successfully.");
                    break;
                case 3:
                    System.out.print("Enter task ID to update: ");
                    int taskId = -1; 
                    try {
                        taskId = scanner.nextInt();
                        scanner.nextLine(); 
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid task ID. Please enter a valid integer ID.");
                        scanner.nextLine(); 
                        continue; 
                    }
                    
                    System.out.print("Enter new title: ");
                    String updatedTitle = scanner.nextLine();
                    System.out.print("Is it completed? (true/false): ");
                    boolean isCompleted = scanner.nextBoolean();
                    taskManager.updateTask(taskId, updatedTitle, isCompleted);
                    System.out.println("Task updated successfully.");
                    break;
                case 4:
                    System.out.print("Enter task ID to delete: ");
                    int deleteId = -1;
                    try {
                        deleteId = scanner.nextInt();
                        scanner.nextLine(); 
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid task ID. Please enter a valid integer ID.");
                        scanner.nextLine(); 
                        continue; 
                    }
                    
                    taskManager.deleteTask(deleteId);
                    System.out.println("Task deleted successfully.");
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}