package com.example.hibernate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.hibernate.config.HibernateUtil;
import com.example.hibernate.model.Cart;
import com.example.hibernate.model.Item;

public class HibernateOneToManyAnnotationMainIntegrationTest {

	private static SessionFactory sessionFactory;

	private Session session;

	@BeforeClass
	public static void beforeTests() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Before
	public void setUp() {
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	@Test
	public void givenSession_checkIfDatabaseIsEmpty() {
		Cart cart = (Cart) session.get(Cart.class, 1);
		assertNull(cart);
	}

	@Test
	public void givenSession_checkIfDatabaseIsPopulated_afterCommit() {
		Cart cart = new Cart();
		Set<Item> cartItems = cart.getItems();

		assertNull(cartItems);

		Item item1 = new Item();
		item1.setCart(cart);

		assertNotNull(item1);

		Set<Item> itemSet = new HashSet<Item>();
		itemSet.add(item1);

		assertNotNull(itemSet);
		cart.setItems(itemSet);

		assertNotNull(cart);

		session.persist(cart);
		session.getTransaction().commit();
		session.close();

		session = sessionFactory.openSession();
		session.beginTransaction();
		cart = (Cart) session.get(Cart.class, 1L);

		assertNotNull(cart);
	}

	@After
	public void tearDown() {
		session.getTransaction().commit();
		session.close();
	}

	@AfterClass
	public static void afterTests() {
		sessionFactory.close();
	}

}
