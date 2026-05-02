# Library Book Manager

A simple Java console app to manage library books. Data is saved to a `books.csv` file — no database needed.

## Project Structure

```
library-manager/
├── src/com/ayush/library/
│   ├── Main.java                   → Menu & user input
│   ├── model/Book.java             → Book data + CSV read/write
│   ├── storage/FileStorage.java    → Reads and writes books.csv
│   └── manager/LibraryManager.java → All business logic
├── out/                            → Compiled .class files (after build)
└── books.csv                       → Auto-created on first run
```

## How to Run (Windows)

### Step 1 — Install Java 17
Download from: https://adoptium.net/temurin/releases/
Run the .msi installer. Then verify:
```cmd
java -version
```

### Step 2 — Open the folder in Command Prompt
```cmd
cd C:\path\to\library-manager
```

### Step 3 — Compile
```cmd
mkdir out
javac -encoding UTF-8 -d out src\com\ayush\library\model\Book.java src\com\ayush\library\storage\FileStorage.java src\com\ayush\library\manager\LibraryManager.java src\com\ayush\library\Main.java
```

### Step 4 — Run
```cmd
java -cp out com.ayush.library.Main
```

## Features

| Option | What it does                        |
|--------|-------------------------------------|
| 1      | Add a new book                      |
| 2      | View all books                      |
| 3      | Search by title or author           |
| 4      | Borrow a book (marks as borrowed)   |
| 5      | Return a book (marks as available)  |
| 6      | Show only available books           |
| 7      | Show only borrowed books            |
| 8      | Delete a book permanently           |
| 0      | Exit                                |

## How data is stored

Every book is saved as one line in `books.csv`:
```
1,Clean Code,Robert Martin,Technology,2008,true
2,The Pragmatic Programmer,David Thomas,Technology,1999,false
```

No database. No internet. Just a plain text file.

## OOP Concepts Used

- **Encapsulation** — Book fields are private, accessed via getters/setters
- **Separation of concerns** — Model / Storage / Business Logic / UI are all separate classes
- **File I/O** — BufferedReader and BufferedWriter for efficient file handling
- **Streams** — Java Stream API for searching and filtering books
