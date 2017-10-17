package com.nerddash.aulago.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	
	public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
	
	public static EntityManager getEm() {
		return factory.createEntityManager();
	}

}
