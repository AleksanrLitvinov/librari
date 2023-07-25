package ru.maxima.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.maxima.model.Book;
import ru.maxima.model.Human;

import java.util.List;
import java.util.Objects;

@Component
public class BookRepository {

    private SessionFactory sessionFactory;

    public BookRepository() {
        Configuration configuration = new Configuration().addAnnotatedClass(Book.class).addAnnotatedClass(Human.class);
        sessionFactory = configuration.buildSessionFactory();
    }


    public List<Book> allBooks() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Book> res = session.createQuery("from Book ").getResultList();
        session.getTransaction().commit();
        return res;
    }

    public void mergeMovie(Book book) {
        if (Objects.isNull(book.getHuman())){
            Book b = findById(book.getId());
            book.setHuman(b.getHuman());
        }
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(book);
        session.getTransaction().commit();
    }

    public Book findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Book book = (Book) session.createQuery("from Book where id = " + id).getSingleResult();
        session.getTransaction().commit();
        return book;
    }

    public void deleteHuman(Long id) {
        Book b = findById(id);
        b.setHuman(null);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(b);
        session.getTransaction().commit();
    }

    public void deleteById(Long id) {
        Book b = findById(id);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(b);
        session.getTransaction().commit();
    }
}
