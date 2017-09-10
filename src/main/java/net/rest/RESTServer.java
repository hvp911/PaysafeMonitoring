package net.rest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RESTServer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("---- initialize servlet context -----");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("---- destroying servlet context -----");

	}
}
