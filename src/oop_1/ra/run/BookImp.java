package oop_1.ra.run;

import oop_1.ra.entity.Book;

import java.util.*;

public class BookImp {
    //Tổ chức lưu trữ danh sách sách - List Interface - ArrayList
    public static List<Book> listBook = new ArrayList<Book>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("****************MENU******************");
            System.out.println("1. Nhập thông tin các sách");
            System.out.println("2. Tính lợi nhuận các sách");
            System.out.println("3. Hiển thị thông tin sách");
            System.out.println("4. Sắp xếp sách theo giá bán tăng dần");
            System.out.println("5. Sắp xếp sách theo lợi nhuận giảm dần");
            System.out.println("6. Tìm sách theo tên sách");
            System.out.println("7. Thống kê số lượng sách theo năm xuất bản");
            System.out.println("8. Thống kê số lượng sách theo tác giả");
            System.out.println("9. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    inputListBooks(scanner);
                    break;
                case 2:
                    calInterestOfBooks();
                    break;
                case 3:
                    displayListBook();
                    break;
                case 4:
                    sortBookByExportPriceASC();
                    break;
                case 5:
                    sortBookByInterestDESC();
                    break;
                case 6:
                    searchBookByName(scanner);
                    break;
                case 7:
                    staticticBookByYear();
                    break;
                case 8:
                    staticticBookByAuthor();
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-9");
            }
        } while (true);
    }

    public static void inputListBooks(Scanner scanner) {
        System.out.println("Nhập số lượng sách cần nhập thông tin:");
        int numberOfBooks = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfBooks; i++) {
            Book book = new Book();
            book.inputData(scanner);
            listBook.add(book);
        }
    }

    public static void calInterestOfBooks() {
        for (Book book : listBook) {
            book.calInterest();
        }
        System.out.println("Đã tính xong tất cả lơi nhuận cho các sách!!!!");
    }

    public static void displayListBook() {
        for (Book book : listBook) {
            book.displayData();
        }
    }

    public static void sortBookByExportPriceASC() {
        Collections.sort(listBook, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if (o1.getExportPrice() > o2.getExportPrice()) {
                    return 1;
                } else if (o1.getExportPrice() == o2.getExportPrice()) {
                    return 0;
                } else {
                    return -1;
                }

            }
        });
    }

    public static void sortBookByInterestDESC() {
        Collections.sort(listBook, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if (o1.getInterest() > o2.getInterest()) {
                    return -1;
                } else if (o1.getInterest() == o2.getInterest()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

    public static void searchBookByName(Scanner scanner) {
        System.out.println("Nhập vào tên sách cần tìm:");
        String bookNameSearch = scanner.nextLine();
        System.out.println("Các sách có tên như vậy là:");
        int cntBook = 0;
        for (Book book : listBook) {
            if (book.getBookName().toLowerCase().contains(bookNameSearch.toLowerCase())) {
                book.displayData();
                cntBook++;
            }
        }
        System.out.printf("Tìm thấy %d quyển sách thỏa mãn điều kiện\n", cntBook);
    }

    public static void staticticBookByYear() {
        //1. Khởi tạo 1 danh sách chứa các năm xuất bản
        List<Integer> listYears = new ArrayList<>();
        //2. Khởi tạo 1 danh sách chứa số lượng các sách của các năm xuất bản
        List<Integer> listCntBooks = new ArrayList<>();
        //3. Lưu các năm xuất bản có trong listBooks vào listYears
        for (Book book : listBook) {
            boolean isExistYear = false;
            for (int year : listYears) {
                if (book.getYear() == year) {
                    isExistYear = true;
                    break;
                }
            }
            if (!isExistYear) {
                listYears.add(book.getYear());
            }
        }
        //4. Thống kê số lượng sách trong từng năm xuất bản
        for (int year : listYears) {
            int cntBook = 0;
            for (Book book : listBook) {
                if (book.getYear() == year) {
                    cntBook++;
                }
            }
            listCntBooks.add(cntBook);
        }
        //5. Hiển thị thống kê sách
        for (int i = 0; i < listYears.size(); i++) {
            System.out.printf("Năm xuất bản: %d - Số lượng sách: %d\n",
                    listYears.get(i), listCntBooks.get(i));
        }
    }

    public static void staticticBookByAuthor() {
        //1. Khởi tạo 1 danh sách chứa các tác giả
        List<String> listAuthor = new ArrayList<>();
        //2. Khởi tạo 1 danh sách chứa số lượng các sách của các tác giả
        List<Integer> listCntBooks = new ArrayList<>();
        //3. Lưu các tác giả có trong listBooks vào listAuthor
        for (Book book : listBook) {
            boolean isExistAuthor = false;
            for (String author : listAuthor) {
                if (book.getAuthor().equals(author)) {
                    isExistAuthor = true;
                    break;
                }
            }
            if (!isExistAuthor) {
                listAuthor.add(book.getAuthor());
            }
        }
        //4. Thống kê số lượng sách của từng tác giả
        for (String author : listAuthor) {
            int cntBook = 0;
            for (Book book : listBook) {
                if (book.getAuthor().equals(author)) {
                    cntBook++;
                }
            }
            listCntBooks.add(cntBook);
        }
        //5. Hiển thị thống kê sách
        for (int i = 0; i < listAuthor.size(); i++) {
            System.out.printf("Tác giả: %s - Số lượng sách: %d\n",
                    listAuthor.get(i), listCntBooks.get(i));
        }
    }
}
