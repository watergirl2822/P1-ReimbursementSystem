package com.revature.P1.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.P1.dtos.request.NewUserRequest;
import com.revature.P1.models.User;
import com.revature.P1.services.TokenService;
import com.revature.P1.services.UserService;
import com.revature.P1.utils.custom_exceptions.InvalidRequestException;
import com.revature.P1.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final UserService userService;
    private final TokenService tokenService;

    public UserServlet(ObjectMapper objectMapper, UserService userService, TokenService tokenService) {
        this.mapper = objectMapper;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            //new user request from Postman
            //mapper obj convert JSON request and store into a NewUserRequest.class
            NewUserRequest request = mapper.readValue(req.getInputStream(), NewUserRequest.class);
            String[] path = req.getRequestURI().split("/");

            if (path[3].equals("signup")){
                User createduser = userService.register(request);

                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(createduser.getUser_id()));
            }

        }catch (InvalidRequestException e){
            resp.setStatus(404); //bad request
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        }catch (ResourceConflictException e){
            resp.setStatus(109);  //conflict
        }catch (Exception e) {
            resp.setStatus(404);
        }
    }
}
