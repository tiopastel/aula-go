package tech.nerddash.aulago.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import tech.nerddash.aulago.model.AbstractEntityClass;

public abstract class AbstractRepositoryTest {

	protected static EntityManagerFactory emFactory;
	protected static EntityManager em;
	protected AbstractEntityClass entityObject;

	protected enum DbLanguage {
		MYSQL, H2
	};

	protected DbLanguage dbLanguage = DbLanguage.MYSQL;

	/**
	 * Abrindo conexão com o Database, criando a EntityManager e abrindo a
	 * transação.
	 */

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emFactory = Persistence.createEntityManagerFactory("TEST");
		em = emFactory.createEntityManager();
		em.getTransaction().begin();
	}

	/**
	 * Comita a transação, encerra a EntityManager e a factory.
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		em.getTransaction().commit();
		em.close();
		emFactory.close();
	}

	@After
	public void tearDown() throws Exception {
		em.clear();
		resetTable(this.entityObject);

	}

	protected void resetTable(AbstractEntityClass entityClass2) {
		String COLUMN_NAME = getColumnName(entityClass2);
		Query query = em.createNativeQuery("DELETE FROM " + COLUMN_NAME + " WHERE id > 0;");
		query.executeUpdate();

		switch (dbLanguage) {
		case MYSQL:
			/*
			 * Resetar o increment no MySQL
			 */
			query = em.createNativeQuery("ALTER TABLE " + COLUMN_NAME + " AUTO_INCREMENT = 1;");
			query.executeUpdate();
			break;

		case H2:
			/*
			 * Resetar o increment no H2
			 */
			query = em.createNativeQuery("ALTER TABLE " + COLUMN_NAME + " ALTER COLUMN id RESTART WITH 1;");
			query.executeUpdate();
			break;
		}

	}

	protected String getColumnName(AbstractEntityClass entityClass) {
		return ((Table) entityClass.getClass().getAnnotation(Table.class)).name();
	}

}
