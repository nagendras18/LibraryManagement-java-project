This is a Library Management System project implemented in Java using Object-Oriented Programming (OOP) concepts.
It allows managing books and students in a library with basic functionalities like adding books, registering students, borrowing and returning books, and searching.

Features :

Book Management:-
* Add new books to the library
* Upgrade quantity of existing books
* Search books by Serial Number
* Search books by Author Name
* Display all available books

Student Management:-
* Register new students
* View all registered students
* Borrow (Check Out) books
* Return (Check In) books

Borrowing Rules:-
* Each student can borrow a maximum of 3 books
* Book quantity decreases when checked out and increases when checked in

 Concepts Used:- Object-Oriented Programming (OOP), Arrays, Classes & Objects, Encapsulation, Inheritance (indirect use via multiple classes)


   LibraryManagementSystem/ :-
* │
* ├── Book.java         # Handles book details
* ├── Books.java        # Manages collection of books
* ├── Student.java      # Handles student details & borrowed books
* ├── Students.java     # Manages collection of students
* ├── library.java      # Main class with menu-driven program


Sample Menu :-

----------------------------------------------------------------------------------------------------------
* Press 1 to Add new Book.
* Press 0 to Exit Application.
* Press 2 to Upgrade Quantity of a Book.
* Press 3 to Search a Book by Serial Number.
* Press 4 to Search a Book by Author Name.
* Press 5 to Show All Books.
* Press 6 to Register Student.
* Press 7 to Show All Registered Students.
* Press 8 to Check Out Book.
* Press 9 to Check In Book.
----------------------------------------------------------------------------------------------------------

