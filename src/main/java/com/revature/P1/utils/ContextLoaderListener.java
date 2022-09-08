package com.revature.P1.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.P1.daos.UserDAO;
import com.revature.P1.services.TokenService;
import com.revature.P1.services.UserService;
import com.revature.P1.servlets.TestServlet;
import com.revature.P1.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //object mapper provides functionality for reading and writing JSON, either to and from basics POJOS (plain old java objects)
        ObjectMapper mapper = new ObjectMapper();

        UserServlet userServlet = new UserServlet(mapper, new UserService(new UserDAO()), new TokenService(new JwtConfig()));
        TestServlet testServlet = new TestServlet();

        ServletContext context = sce.getServletContext();
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("TestServlet", testServlet).addMapping("/test");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Shutting down P1 web application");
    }
}
