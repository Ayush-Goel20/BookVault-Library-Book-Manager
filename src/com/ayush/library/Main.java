package com.ayush.library;

import com.ayush.library.manager.LibraryManager;

import java.util.Scanner;

/**
 * Entry point — shows the menu and reads user input.
 * All logic is delegated to LibraryManager.
 */
public class Main {

    private static final String FILE_PATH = "books.csv";
    private static final Scanner scanner  = new Scanner(System.in);
    private static final LibraryManager library = new LibraryManager(FILE_PATH);

    public static void main(String[] args) {
        System.out.println("\n========================================");
        System.out.println("      LIBRARY BOOK MANAGER v1.0         ");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addBook();
                case "2" -> library.showAllBooks();
                case "3" -> searchBook();
                case "4" -> borrowBook();
                case "5" -> returnBook();
                case "6" -> library.showAvailable();
                case "7" -> library.showBorrowed();
                case "8" -> deleteBook();
                case "0" -> {
                    System.out.println("\n Goodbye! Happy reading!");
                    running = false;
                }
                default  -> System.out.println("\n Invalid choice. Please enter 0-8.");
            }
        }
        scanner.close();
    }

    // ── Menu ──────────────────────────────────────────────────────────────
    private static void printMenu() {
        System.out.println("\n----------------------------------------");
        System.out.println(" 1. Add a book");
        System.out.println(" 2. View all books");
        System.out.println(" 3. Search books");
        System.out.println(" 4. Borrow a book");
        System.out.println(" 5. Return a book");
        System.out.println(" 6. Show available books");
        System.out.println(" 7. Show borrowed books");
        System.out.println(" 8. Delete a book");
        System.out.println(" 0. Exit");
        System.out.println("----------------------------------------");
        System.out.print(" Enter choice: ");
    }

    // ── Input Handlers ────────────────────────────────────────────────────
    private static void addBook() {
        System.out.println("\n--- Add New Book ---");
        System.out.print("Title  : "); String title  = scanner.nextLine().trim();
        System.out.print("Author : "); String author = scanner.nextLine().trim();
        System.out.print("Genre  : "); String genre  = scanner.nextLine().trim();
        System.out.print("Year   : ");
        int year = 2024;
        try { year = Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Invalid year, using 2024."); }
        library.addBook(title, author, genre, year);
    }

    private static void searchBook() {
        System.out.print("\n Enter title or author to search: ");
        String keyword = scanner.nextLine().trim();
        library.search(keyword);
    }

    private static void borrowBook() {
        System.out.print("\n Enter book ID to borrow: ");
        library.borrowBook(readId());
    }

    private static void returnBook() {
        System.out.print("\n Enter book ID to return: ");
        library.returnBook(readId());
    }

    private static void deleteBook() {
        System.out.print("\n Enter book ID to delete: ");
        library.deleteBook(readId());
    }

    private static int readId() {
        try { return Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Invalid ID."); return -1; }
    }
}
