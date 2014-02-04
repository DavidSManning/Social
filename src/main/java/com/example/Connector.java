package com.example;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Entry point to the application
 */
public class Connector {

  /**
   * @param args
   */
  public static void main(String[] args) throws InterruptedException {

    MessageService service = new MessageService(getSessionFactory());

    service.post("Dave", "Test 2");

    service.viewWall("Dave");

  }

  private static SessionFactory getSessionFactory() {
    Configuration configuration = new Configuration()
      .addAnnotatedClass(Post.class)
      .addProperties(getHibernateProperties());

    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
    return configuration.buildSessionFactory(serviceRegistry);
  }

  private static Properties getHibernateProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", "update");
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    properties.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
    properties.setProperty("hibernate.connection.url", "jdbc:h2:~/test");
    properties.setProperty("hibernate.current_session_context_class", "thread");
//    properties.setProperty("hibernate.connection.username", "sa");
//    properties.setProperty("hibernate.connection.password", "password");
    return properties;
  }
}
