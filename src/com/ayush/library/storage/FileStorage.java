package com.ayush.library.storage;

import com.ayush.library.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all reading and writing of books to a CSV file.
 * Each line in books.csv represents one book.
 */
public class FileStorage {

    private final String filePath;

    public FileStorage(String filePath) {
        this.filePath = filePath;
        createFileIfNotExists();
    }

    // ── Load all books from file ──────────────────────────────────────────
    public List<Book> loadAll() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    books.add(Book.fromCsv(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return books;
    }

    // ── Save all books back to file (overwrites completely) ───────────────
    public void saveAll(List<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                writer.write(book.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // ── Create the file if it doesn't exist yet ───────────────────────────
    private void createFileIfNotExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Created new library file: " + filePath);
            } catch (IOException e) {
                System.out.println("Could not create file: " + e.getMessage());
            }
        }
    }
}
