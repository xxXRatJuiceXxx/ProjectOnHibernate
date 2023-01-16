package com.javarush;

import com.javarush.dao.*;
import com.javarush.demain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.cfg.SecondPass;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Main {
    private static SessionFactory sessionFactory;
    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDao customerDao;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final  LanguageDAO LanguageDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;

    public Main() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MYSQL8Dialect");
        properties.put(Environment.DRIVER, "org.p6spy.engine.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/movle");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "2005Evgexa_opit28");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "validate");


        sessionFactory = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .addProperties(properties)
                .buildSessionFactory();
        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDao = new CustomerDao(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        LanguageDAO = new LanguageDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);

    }

    public static void main(String[] args) {
        Main main = new Main();
        Customer customer = main.createCustomer();
        main.customerRentInventory(customer);
        main.newFilmWasMade();
    }

    private void newFilmWasMade() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Language language = LanguageDAO.getItems(0,34).stream().unordered().findAny().get();
            List<Category> categories = categoryDAO.getItems(0,6);
            List<Actor> actors = actorDAO.getItems(0,15);

            Film film = new Film();
            film.setActors(new HashSet<>(actors));
            film.setRating(Rating.PG13);
            film.setSpecialFeatures(Set.of(Feature.TRAILERS, Feature.BEHINDTHESCENES));
            film.setLenght((short)1238);
            film.setReplacementCost(BigDecimal.TEN);
            film.setRentalRate(BigDecimal.ZERO);
            film.setLanguage(language);
            film.setDescription("love javaRush");
            film.setTitle("Love all mentors!!!");
            film.setRentalDuration((byte) 33);
            film.setOriginalLanguage(language);
            film.setCategories(new HashSet<>(categories));
            film.setYear(Year.now());
            filmDAO.save(film);

            FilmText filmText = new FilmText();
            filmText.setId(filmText.getId());
            filmText.setFilm(film);
            filmText.setDescription("Good gob!");
            filmText.setTitle("Directed to by...");
            filmTextDAO.save(filmText);

            session.getTransaction().commit();
        }
    }

    private void customerRentInventory(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();

           Film film = filmDAO.getFirstAvailableFilmForRent();
           Store store = (Store) storeDAO.getItems(0,1).get(0);

           Inventory inventory = new Inventory();
           inventory.setFilm(film);
           inventory.setStore(store);
           inventoryDAO.save(inventory);

           Staff staff = store.getStaff();

           Rental rental = new Rental();
           rental.setCustomer(customer);
           rental.setInventory(inventory);
           rental.setDate(LocalDateTime.now());
           rental.setStaff(staff);
           rentalDAO.save(rental);

           Payment payment=  new Payment();
           payment.setCustomer(customer);
           payment.setStaff(staff);
           payment.setAmount(BigDecimal.valueOf(444,45));
           payment.setPaymentDate(LocalDateTime.now());
           payment.setRental(rental);
           paymentDAO.save(payment);

            session.getTransaction().commit();
        }
    }

    private void customerReturnInventoryToStore(){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Rental rental = rentalDAO.getReturnRental();
            rental.setRaturnDate(LocalDateTime.now());

            rentalDAO.save(rental);

            session.getTransaction().commit();
        }
    }

    private Customer createCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Store store = (Store) storeDAO.getItems(0,1).get(0);

            City city =  cityDAO.getByName("Kragujevac");

            Address address = new Address();
            address.setAddress("Indep str, 45");
            address.setPhone("999-111-666");
            address.setCity(city);
            address.setDistrict("sadwadwadwadawdaw");
            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setAddress(address);
            customer.setActive(true);
            customer.setEmail("testi@gmail.com");
            customer.setFirstName("Geniy");
            customer.setLastName("Black");
            customerDao.save(customer);



            session.getTransaction().commit();
            return customer;


        }
    }
}