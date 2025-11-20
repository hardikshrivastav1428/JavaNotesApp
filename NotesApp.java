import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n===== NOTES APP =====");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Clear All Notes");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    addNote(sc);
                    break;

                case 2:
                    viewNotes();
                    break;

                case 3:
                    clearNotes();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void addNote(Scanner sc) {
        System.out.print("Enter your note: ");
        String note = sc.nextLine();

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) { // true → append mode
            fw.write(note + "\n");
            System.out.println("Note added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file!");
            e.printStackTrace();
        }
    }

    private static void viewNotes() {
        System.out.println("\n--- All Notes ---");

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean empty = true;

            while ((line = br.readLine()) != null) {
                empty = false;
                System.out.println("- " + line);
            }

            if (empty) {
                System.out.println("No notes found!");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No notes found! File does not exist.");
        } catch (IOException e) {
            System.out.println("Error reading file!");
            e.printStackTrace();
        }
    }

    private static void clearNotes() {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {  
            fw.write(""); // overwrite mode clears file
            System.out.println("All notes cleared!");
        } catch (IOException e) {
            System.out.println("Error clearing file!");
            e.printStackTrace();
        }
    }
}