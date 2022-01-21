package hibernate.util;

import hibernate.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Owner.class);
                configuration.addAnnotatedClass(Car.class);
                configuration.addAnnotatedClass(Sedan.class);
                configuration.addAnnotatedClass(SportCar.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
              throw new RuntimeException(e);
        }
        return sessionFactory;
    }
}
