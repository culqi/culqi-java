package com.culqi;

import com.culqi.model.*;


/**
 * Created by culqi on 12/23/16.
 */
public class Culqi {

    public static String public_key;

    public static String secret_key;

    // Resources
    public Token token = new Token();

    public Order order = new Order();

    public Charge charge = new Charge();

    public Customer customer = new Customer();

    public Card card = new Card();

    public Event event = new Event();

    public Iin iin = new Iin();

    public Plan plan = new Plan();

    public Refund refund = new Refund();

    public Subscription subscription = new Subscription();

    public Transfer transfer = new Transfer();

    public Culqi() {}

    public static void main(String[] args) {
    }

}
