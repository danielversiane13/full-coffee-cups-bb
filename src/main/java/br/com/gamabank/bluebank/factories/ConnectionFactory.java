package br.com.gamabank.bluebank.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("bank");

	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:h2:mem:bluebank", "sa", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
