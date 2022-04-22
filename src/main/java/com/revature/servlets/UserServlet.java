package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.GlobalObjectStore;
import com.revature.dtos.AuthDto;
import com.revature.dtos.UserDto;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto)GlobalObjectStore.getObject(req.getHeader("username"));
        resp.setStatus(200);
        resp.getWriter().print(new ObjectMapper().writeValueAsString(userDto));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch(req.getHeader("mode")) {
            case "register":
                UserDto newUserDto = new ObjectMapper().readValue(req.getInputStream(), UserDto.class);
                GlobalObjectStore.addObject(newUserDto.getUsername(), newUserDto);
                resp.setStatus(201);
                resp.getWriter().print(new ObjectMapper().writeValueAsString(newUserDto));
                resp.setHeader("access-control-expose-headers", "authToken");
                resp.setHeader("authToken", newUserDto.getUsername());
                break;
            case "login":
                AuthDto authDto = new ObjectMapper().readValue(req.getInputStream(), AuthDto.class);
                UserDto checkUser = (UserDto)GlobalObjectStore.getObject(authDto.getUsername());
                if(checkUser != null && checkUser.getPassword().equals(authDto.getPassword())) {
                    resp.setStatus(200);
                    resp.getWriter().print(new ObjectMapper().writeValueAsString(checkUser));
                    resp.setHeader("access-control-expose-headers", "authToken");
                    resp.setHeader("authToken", checkUser.getUsername());
                } else {
                    resp.setStatus(401);
                }
                break;
            default:
                resp.setStatus(400);
                break;
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto updateUserDto = new ObjectMapper().readValue(req.getInputStream(), UserDto.class);
        GlobalObjectStore.removeObject(updateUserDto.getUsername());
        GlobalObjectStore.addObject(updateUserDto.getUsername(), updateUserDto);
        resp.setStatus(200);
        resp.getWriter().print(new ObjectMapper().writeValueAsString(updateUserDto));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GlobalObjectStore.removeObject(req.getHeader("username"));
        resp.setStatus(200);
    }
}
