package oop_1.ra.entity;

import oop_1.ra.run.BookImp;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Book {
    //1. Fields/Atributes/Properties
    private String bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private String author;
    private float interest;
    private int year;
    //2. Constructors

    public Book() {
    }

    public Book(String bookId, String bookName, float importPrice, float exportPrice, String author, float interest, int year) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.author = author;
        this.interest = interest;
        this.year = year;
    }

    //3. Methods

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void inputData(Scanner scanner) {
        //Nhập thông tin sách (trừ interest)
        this.bookId = inputBookId(scanner);
        this.bookName = inputBookName(scanner);
        this.importPrice = inputImportPrice(scanner);
        this.exportPrice = inputExportPrice(scanner);
        this.author = inputAuthor(scanner);
        this.year = inputYear(scanner);
    }

    //Phương thức nhập mã sách - validate duy nhất không trùng lặp, gồm 4 ký tự, bắt đầu là ký tự B, 3 ký tự sau là số
    public String inputBookId(Scanner scanner) {
        String bookIdRegex = "B\\d{3}";
        System.out.println("Nhập vào mã sách:");
        do {
            String bookId = scanner.nextLine();
            //Kiểm tra duy nhất
            boolean isExist = false;//chưa tồn tại mã sách
            for (Book book : BookImp.listBook) {
                if (book.getBookId().equals(bookId)) {
                    isExist = true;//đã tồn tại mã sách trong danh sách
                    break;
                }
            }
            if (isExist) {
                System.err.println("Mã sách đã tồn tại, vui lòng nhập lại");
            } else {
                //4 ký tự, bắt đầu là B, sau là 3 số
                if (Pattern.matches(bookIdRegex, bookId)) {
                    return bookId;
                }
                System.err.println("Định dạng mã sách không đúng, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputBookName(Scanner scanner) {
        System.out.println("Nhập vào tên sách:");
        do {
            String bookName = scanner.nextLine();
            boolean isExist = false;//Tên sách chưa tồn tại
            for (Book book : BookImp.listBook) {
                if (book.bookName.equals(bookName)) {
                    isExist = true;//Tên sách đã tồn tại
                    break;
                }
            }
            if (isExist) {
                System.err.println("Tên sách đã tồn tại, vui lòng nhập lại");
            } else {
                return bookName;
            }
        } while (true);
    }

    public float inputImportPrice(Scanner scanner) {
        System.out.println("Nhập vào giá nhập của sách:");
        do {
            float importPrice = Float.parseFloat(scanner.nextLine());
            if (importPrice > 0) {
                return importPrice;
            }
            System.err.println("Giá sách có giá trị lớn hơn 0, vui lòng nhập lại");
        } while (true);
    }

    public float inputExportPrice(Scanner scanner) {
        System.out.println("Nhập vào giá xuất của sách:");
        do {
            float exportPrice = Float.parseFloat(scanner.nextLine());
            if (exportPrice >= this.importPrice * 1.3F) {
                return exportPrice;
            }
            System.err.println("Giá xuất có giá trị lớn hơn ít nhất 30% so với giá nhập, vui lòng nhập lại");
        } while (true);
    }

    public String inputAuthor(Scanner scanner) {
        System.out.println("Nhập vào tên tác giả:");
        do {
            String author = scanner.nextLine();
            if (author.length() >= 6 && author.length() <= 50) {
                return author;
            }
            System.err.println("Tên tác giả có từ 6-50 ký tự, vui lòng nhập lại");
        } while (true);
    }

    public int inputYear(Scanner scanner) {
        System.out.println("Nhập vào năm xuất bản của sách:");
        do {
            int year = Integer.parseInt(scanner.nextLine());
            if (year >= 2000) {
                return year;
            }
            System.err.println("Năm xuất bản phải sau năm 2000, vui lòng nhập lại");
        } while (true);
    }

    public void calInterest() {
        this.interest = this.exportPrice - this.importPrice;
    }

    public void displayData() {
        System.out.printf("Mã sách: %s - Tên sách: %s - Tác giả: %s - NXB: %d\n"
                , this.bookId, this.bookName, this.author, this.year);
        System.out.printf("Giá nhập: %.1f - Giá xuất: %.1f - Lợi nhuận: %.1f\n"
                , this.importPrice, this.exportPrice, this.interest);
    }
}
