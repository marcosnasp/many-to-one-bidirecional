package com.example.hibernate;

import java.util.Set;
import java.util.logging.Logger;

import org.hibernate.Session;

import com.example.hibernate.config.HibernateUtil;
import com.example.hibernate.model.Cart;
import com.example.hibernate.model.Item;
 
public class App 
{
    static Logger logger = Logger.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
 
        session.beginTransaction();

        Cart cart = new Cart();
        Set<Item> cartItems = cart.getItems();

        Item item1 = new Item();
        item1.setCart(cart);
 
        cart.setItems(cartItems);

        session.save(cart);
        session.getTransaction().commit();

        session.beginTransaction();
        cart = (Cart) session.get(Cart.class, 1L);

        logger.info(cart.toString());

        System.out.println("Cart " + cart.getId() + " was successfully stored in database ");
    }
}