import java.util.Scanner;

class Book {
    public int sNo;
    public String bookName;
    public String authorName;
    public int bookQty;
    public int bookQtyCopy;

    Scanner input = new Scanner(System.in);

    public Book() {
        System.out.println("Enter Serial No of Book:");
        this.sNo = input.nextInt();
        input.nextLine();

        System.out.println("Enter Book Name:");
        this.bookName = input.nextLine();

        System.out.println("Enter Author Name:");
        this.authorName = input.nextLine();

        System.out.println("Enter Quantity of Books:");
        this.bookQty = input.nextInt();
        this.bookQtyCopy = this.bookQty;
    }
}

class Books {
    Book theBooks[] = new Book[50];
    public static int count = 0;
    Scanner input = new Scanner(System.in);

    public int compareBookObjects(Book b1, Book b2) {
        if (b1.bookName.equalsIgnoreCase(b2.bookName)) {
            System.out.println("Book of this Name Already Exists.");
            return 0;
        }
        if (b1.sNo == b2.sNo) {
            System.out.println("Book of this Serial No Already Exists.");
            return 0;
        }
        return 1;
    }

    public void addBook(Book b) {
        for (int i = 0; i < count; i++) {
            if (this.compareBookObjects(b, this.theBooks[i]) == 0)
                return;
        }
        if (count < 50) {
            theBooks[count] = b;
            count++;
        } else {
            System.out.println("No Space to Add More Books.");
        }
    }

    public void searchBySno() {
        System.out.println("\t\t\t\tSEARCH BY SERIAL NUMBER\n");
        int sNo;
        System.out.println("Enter Serial No of Book:");
        sNo = input.nextInt();

        int flag = 0;
        System.out.println("S.No\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");
        for (int i = 0; i < count; i++) {
            if (sNo == theBooks[i].sNo) {
                System.out.println(theBooks[i].sNo + "\t\t"
                    + theBooks[i].bookName + "\t\t"
                    + theBooks[i].authorName + "\t\t"
                    + theBooks[i].bookQtyCopy + "\t\t"
                    + theBooks[i].bookQty);
                flag++;
                return;
            }
        }
        if (flag == 0)
            System.out.println("No Book for Serial No " + sNo + " Found.");
    }

    public void searchByAuthorName() {
        System.out.println("\t\t\t\tSEARCH BY AUTHOR'S NAME");
        input.nextLine();

        System.out.println("Enter Author Name:");
        String authorName = input.nextLine();

        int flag = 0;
        System.out.println("S.No\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");
        for (int i = 0; i < count; i++) {
            if (authorName.equalsIgnoreCase(theBooks[i].authorName)) {
                System.out.println(theBooks[i].sNo + "\t\t"
                    + theBooks[i].bookName + "\t\t"
                    + theBooks[i].authorName + "\t\t"
                    + theBooks[i].bookQtyCopy + "\t\t"
                    + theBooks[i].bookQty);
                flag++;
            }
        }
        if (flag == 0)
            System.out.println("No Books of " + authorName + " Found.");
    }

    public void showAllBooks() {
        System.out.println("\t\t\t\tSHOWING ALL BOOKS\n");
        System.out.println("S.No\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");
        for (int i = 0; i < count; i++) {
            System.out.println(theBooks[i].sNo + "\t\t"
                + theBooks[i].bookName + "\t\t"
                + theBooks[i].authorName + "\t\t"
                + theBooks[i].bookQtyCopy + "\t\t"
                + theBooks[i].bookQty);
        }
    }

    public void upgradeBookQty() {
        System.out.println("\t\t\t\tUPGRADE QUANTITY OF A BOOK\n");
        System.out.println("Enter Serial No of Book");
        int sNo = input.nextInt();
        for (int i = 0; i < count; i++) {
            if (sNo == theBooks[i].sNo) {
                System.out.println("Enter No of Books to be Added:");
                int addingQty = input.nextInt();
                theBooks[i].bookQty += addingQty;
                theBooks[i].bookQtyCopy += addingQty;
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public int isAvailable(int sNo) {
        for (int i = 0; i < count; i++) {
            if (sNo == theBooks[i].sNo) {
                if (theBooks[i].bookQtyCopy > 0) {
                    System.out.println("Book is Available.");
                    return i;
                }
                System.out.println("Book is Unavailable");
                return -1;
            }
        }
        System.out.println("No Book of Serial Number Available in Library.");
        return -1;
    }

    public Book checkOutBook() {
        System.out.println("Enter Serial No of Book to be Checked Out.");
        int sNo = input.nextInt();

        int bookIndex = isAvailable(sNo);
        if (bookIndex != -1) {
            theBooks[bookIndex].bookQtyCopy--;
            return theBooks[bookIndex];
        }
        return null;
    }

    public void checkInBook(Book b) {
        for (int i = 0; i < count; i++) {
            if (b.equals(theBooks[i])) {
                theBooks[i].bookQtyCopy++;
                return;
            }
        }
    }
}

class Student {
    String studentName;
    String regNum;

    Book borrowedBooks[] = new Book[3];
    public int booksCount = 0;

    Scanner input = new Scanner(System.in);

    public Student() {
        System.out.println("Enter Student Name:");
        this.studentName = input.nextLine();

        System.out.println("Enter Registration Number:");
        this.regNum = input.nextLine();
    }

    public void checkOutBook(Books book) {
        if (booksCount == 3) {
            System.out.println("You have already borrowed 3 books.");
            return;
        }
        book.showAllBooks();
        Book b = book.checkOutBook();
        if (b != null) {
            borrowedBooks[booksCount] = b;
            booksCount++;
            System.out.println("Book Checked Out Successfully.");
        } else {
            System.out.println("Book could not be checked out.");
        }
    }

    public void checkInBook(Books book) {
        System.out.println("Enter Serial No of Book to Check In:");
        int sNo = input.nextInt();
        input.nextLine();
        for (int i = 0; i < booksCount; i++) {
            if (borrowedBooks[i] != null && borrowedBooks[i].sNo == sNo) {
                book.checkInBook(borrowedBooks[i]);
                borrowedBooks[i] = null;
                System.out.println("Book Checked In Successfully.");
                // Shift books left
                for (int j = i; j < booksCount - 1; j++) {
                    borrowedBooks[j] = borrowedBooks[j + 1];
                }
                borrowedBooks[booksCount - 1] = null;
                booksCount--;
                return;
            }
        }
        System.out.println("You did not borrow this book.");
    }
}

class Students {
    Scanner input = new Scanner(System.in);
    Student theStudents[] = new Student[50];
    public static int count = 0;

    public void addStudent(Student s) {
        for (int i = 0; i < count; i++) {
            if (s.regNum.equalsIgnoreCase(theStudents[i].regNum)) {
                System.out.println("Student of Reg Num " + s.regNum + " is Already Registered.");
                return;
            }
        }
        if (count < 50) {
            theStudents[count] = s;
            count++;
        } else {
            System.out.println("No Space to Register More Students.");
        }
    }

    public void showAllStudents() {
        System.out.println("Student Name\t\tReg Number");
        for (int i = 0; i < count; i++) {
            System.out.println(theStudents[i].studentName + "\t\t" + theStudents[i].regNum);
        }
    }

    public int isStudent() {
        System.out.println("Enter Reg Number:");
        String regNum = input.nextLine();

        for (int i = 0; i < count; i++) {
            if (theStudents[i].regNum.equalsIgnoreCase(regNum)) {
                return i;
            }
        }
        System.out.println("Student is not Registered.");
        System.out.println("Get Registered First.");
        return -1;
    }

    public void checkOutBook(Books book) {
        int studentIndex = this.isStudent();
        if (studentIndex != -1) {
            theStudents[studentIndex].checkOutBook(book);
        }
    }

    public void checkInBook(Books book) {
        int studentIndex = this.isStudent();
        if (studentIndex != -1) {
            theStudents[studentIndex].checkInBook(book);
        }
    }
}

public class library {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Books books = new Books();
        Students students = new Students();

        int choice;
        do {
            System.out.println("----------------------------------------------------------------------------------------------------------");
            System.out.println("Press 1 to Add new Book.");
            System.out.println("Press 0 to Exit Application.");
            System.out.println("Press 2 to Upgrade Quantity of a Book.");
            System.out.println("Press 3 to Search a Book by Serial Number.");
            System.out.println("Press 4 to Search a Book by Author Name.");
            System.out.println("Press 5 to Show All Books.");
            System.out.println("Press 6 to Register Student.");
            System.out.println("Press 7 to Show All Registered Students.");
            System.out.println("Press 8 to Check Out Book.");
            System.out.println("Press 9 to Check In Book.");
            System.out.println("-------------------------------------------------------------------------------------------------------");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    books.addBook(new Book());
                    break;
                case 2:
                    books.upgradeBookQty();
                    break;
                case 3:
                    books.searchBySno();
                    break;
                case 4:
                    books.searchByAuthorName();
                    break;
                case 5:
                    books.showAllBooks();
                    break;
                case 6:
                    students.addStudent(new Student());
                    break;
                case 7:
                    students.showAllStudents();
                    break;
                case 8:
                    students.checkOutBook(books);
                    break;
                case 9:
                    students.checkInBook(books);
                    break;
                case 0:
                    System.out.println("Exiting Application.");
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        } while (choice != 0);
    }
}