package revagenda.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import revagenda.models.AbstractUser;
import revagenda.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private UserService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new UserService();
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getHeader("howTORead"));
        if (req.getHeader("howToRead").equals("users_id")) {
            AbstractUser model = service.read(Integer.parseInt(req.getHeader("users_id")));
            String json = mapper.writeValueAsString(model);
            resp.setContentType("application/json");
            resp.getWriter().print(json);
            resp.setStatus(200);
        } else if (req.getHeader("howToRead").equals("username")) {
            AbstractUser model = service.readbyu(req.getHeader("username"));
            String json = mapper.writeValueAsString(model);
            resp.setContentType("application/json");
            resp.getWriter().print(json);
            resp.setStatus(200);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AbstractUser model = mapper.readValue(req.getInputStream(), AbstractUser.class);
        service.create(model);
        String json = mapper.writeValueAsString(model);
        resp.setStatus(201);
        resp.getWriter().print(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AbstractUser model = mapper.readValue(req.getInputStream(), AbstractUser.class);
        service.update(model);
        String json = mapper.writeValueAsString(model);
        resp.setStatus(201);
        resp.getWriter().print(json);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            service.delete(Integer.parseInt(req.getHeader("users_id")));
            resp.setStatus(200);
    }
    }
