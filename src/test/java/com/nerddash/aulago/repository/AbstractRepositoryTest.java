package com.nerddash.aulago.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class AbstractRepositoryTest {

	protected static EntityManagerFactory emFactory;
	protected static EntityManager em;
	protected Class<?> entityClass;
	
	/**
	 * Abrindo conexão com o Database, criando a EntityManager e abrindo a
	 * transação.
	 */
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		emFactory = Persistence.createEntityManagerFactory("seu-ejbPU-test");
		em = emFactory.createEntityManager();
		em.getTransaction().begin();
	}

	/**
	 * Comita a transação, encerra a EntityManager e a factory.
	 */
	@AfterClass
	public static void afterClass() throws Exception {
		em.getTransaction().commit();
        em.close();
        emFactory.close();
	}
	
	@After
	public void tearDown() throws Exception {
		em.clear();
		resetTable(this.entityClass);

	}
	
	protected void resetTable(Class<?>  entityClass) {
		String COLUMN_NAME = getColumnName(entityClass);
		Query query = em.createNativeQuery("DELETE FROM "+COLUMN_NAME+"; ALTER TABLE "+COLUMN_NAME+" ALTER COLUMN id RESTART WITH 1;");
		query.executeUpdate();
	}
	

	protected String getColumnName(Class<?> entityClass) {
		return ((Table) entityClass.getAnnotation(Table.class)).name();
	}

}
