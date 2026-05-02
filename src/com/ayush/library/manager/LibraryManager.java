package com.ayush.library.manager;

import com.ayush.library.model.Book;
import com.ayush.library.storage.FileStorage;

import java.util.List;
import java.util.stream.Collectors;

public class LibraryManager {

    private final FileStorage storage;
    private List<Book> books;

    public LibraryManager(String filePath) {
        this.storage = new FileStorage(filePath);
        this.books   = storage.loadAll();
    }

    public void addBook(String title, String author, String genre, int year) {
        int newId = books.isEmpty() ? 1 : books.get(books.size() - 1).getId() + 1;
        Book book = new Book(newId, title, author, genre, year, true);
        books.add(book);
        save();
        System.out.println("\n Book added! ID: " + newId);
    }

    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("\n No books in library yet.");
            return;
        }
        printHeader();
        books.forEach(System.out::println);
    }

    public void search(String keyword) {
        List<Book> results = books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase())
                          || b.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        if (results.isEmpty()) {
            System.out.println("\n No books found for: " + keyword);
        } else {
            System.out.println("\n Found " + results.size() + " result(s):");
            printHeader();
            results.forEach(System.out::println);
        }
    }

    public void borrowBook(int id) {
        Book book = findById(id);
        if (book == null) { System.out.println("\n No book found with ID: " + id); return; }
        if (!book.isAvailable()) {
            System.out.println("\n Sorry, " + book.getTitle() + " is already borrowed.");
            return;
        }
        book.setAvailable(false);
        save();
        System.out.println("\n You borrowed: " + book.getTitle() + ". Enjoy reading!");
    }

    public void returnBook(int id) {
        Book book = findById(id);
        if (book == null) { System.out.println("\n No book found with ID: " + id); return; }
        if (book.isAvailable()) {
            System.out.println("\n " + book.getTitle() + " is already in the library.");
            return;
        }
        book.setAvailable(true);
        save();
        System.out.println("\n Returned: " + book.getTitle() + ". Thank you!");
    }

    public void deleteBook(int id) {
        Book book = findById(id);
        if (book == null) { System.out.println("\n No book found with ID: " + id); return; }
        books.remove(book);
        save();
        System.out.println("\n Deleted: " + book.getTitle());
    }

    public void showAvailable() {
        List<Book> available = books.stream().filter(Book::isAvailable).collect(Collectors.toList());
        if (available.isEmpty()) { System.out.println("\n No books currently available."); return; }
        System.out.println("\n Available (" + available.size() + "):");
        printHeader();
        available.forEach(System.out::println);
    }

    public void showBorrowed() {
        List<Book> borrowed = books.stream().filter(b -> !b.isAvailable()).collect(Collectors.toList());
        if (borrowed.isEmpty()) { System.out.println("\n No books currently borrowed."); return; }
        System.out.println("\n Borrowed (" + borrowed.size() + "):");
        printHeader();
        borrowed.forEach(System.out::println);
    }

    private Book findById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    private void save() { storage.saveAll(books); }

    private void printHeader() {
        System.out.println("\n" + "-".repeat(95));
        System.out.printf("[ID] %-35s | %-20s | %-15s | %-4s | %s%n",
                "Title", "Author", "Genre", "Year", "Status");
        System.out.println("-".repeat(95));
    }
}
