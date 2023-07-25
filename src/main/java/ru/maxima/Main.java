package ru.maxima;

import ru.maxima.dao.BookRepository;

public class Main {
    public static void main(String[] args) {

        BookRepository l = new BookRepository();
        System.out.println(l.allBooks());
        System.out.println(l.findById(1L));
        l.deleteById(1L);
        System.out.println(l.allBooks());

    }
}
