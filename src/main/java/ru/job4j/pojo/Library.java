package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book firstBook = new Book("Mobi-Dick, or The Whale", 752);
        Book secondBook = new Book("Java: The Complete Reference", 1345);
        Book thirdBook = new Book("Grokking Algorithms", 256);
        Book fourthBook = new Book("Clean code", 464);
        Book[] books = new Book[4];
        books[0] = firstBook;
        books[1] = secondBook;
        books[2] = thirdBook;
        books[3] = fourthBook;
        for (int index = 0; index < books.length; index++) {
            Book book = books[index];
            System.out.println(book.getName() + " - " + book.getNumberOfPage());
        }
        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        System.out.println("Replaced the book with index 0 with the book with index 3.");
        for (int index = 0; index < books.length; index++) {
            Book book = books[index];
            System.out.println(book.getName() + " - " + book.getNumberOfPage());
        }
        System.out.println("Indexes of books with name Clean Code :");
        for (int index = 0; index < books.length; index++) {
            Book book = books[index];
            if ("Clean code".equals(book.getName())) {
                System.out.println(index);
            }
        }
    }
}
