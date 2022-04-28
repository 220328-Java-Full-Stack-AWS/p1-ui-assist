package revagenda.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import revagenda.models.AbstractReimbursement;
import revagenda.models.Employee;
import revagenda.services.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {
private ReimbursementService service;
private ObjectMapper mapper;


    @Override
    public void init() throws ServletException {
        this.service = new ReimbursementService();
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getHeader("howToRead").equals("id")) {
            AbstractReimbursement model = service.read(Integer.parseInt(req.getHeader("id")));
            String json = mapper.writeValueAsString(model);
            resp.setContentType("application/json");
            resp.getWriter().print(json);
            resp.setStatus(200);
        } else if (req.getHeader("howToRead").equals("readAllByOneUser")) {
            List<AbstractReimbursement> list;

            list = service.readAllByOneUser(req.getIntHeader("author"));
            System.out.println(list);
            String json = mapper.writeValueAsString(list);
            System.out.println(json);
            resp.setContentType("application/json");
            resp.getWriter().print(json);
            resp.setStatus(200);
        } else if (req.getHeader("howToRead").equals("readAllPendingByOneUser")) {
            List<AbstractReimbursement> list;

            list = service.readAllPendingByOneUser(req.getIntHeader("author"));
            System.out.println(list);
            String json = mapper.writeValueAsString(list);
            System.out.println(json);
            resp.setContentType("application/json");
            resp.getWriter().print(json);
            resp.setStatus(200);
        } else if (req.getHeader("howToRead").equals("readAllApprovedByOneUser")) {
            List<AbstractReimbursement> list;

            list = service.readAllApproveddByOneUser(req.getIntHeader("author"));
            System.out.println(list);
            String json = mapper.writeValueAsString(list);
            System.out.println(json);
            resp.setContentType("application/json");
            resp.getWriter().print(json);
            resp.setStatus(200);
        } else if (req.getHeader("howToRead").equals("readAllDeniedByOneUser")) {
            List<AbstractReimbursement> list;

            list = service.readAllDeniedByOneUser(req.getIntHeader("author"));
            System.out.println(list);
            String json = mapper.writeValueAsString(list);
            System.out.println(json);
            resp.setContentType("application/json");
            resp.getWriter().print(json);
            resp.setStatus(200);
        }
    }
        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println(req.getHeader("howTOCreate"));
                if(req.getHeader("howToCreate").equals("complete")){
                AbstractReimbursement model = mapper.readValue(req.getInputStream(), AbstractReimbursement.class);
                model = service.create(model);
                String json = mapper.writeValueAsString(model);
                resp.setStatus(201);
                resp.getWriter().print(json);
            }
            else if(req.getHeader("howToCreate").equals("byEmployee")){
                AbstractReimbursement model = mapper.readValue(req.getInputStream(), AbstractReimbursement.class);
                model = service.createbyemployee(model);
                String json = mapper.writeValueAsString(model);
                resp.setStatus(201);
                resp.getWriter().print(json);
            }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getHeader("howToUpdate"));
        if(req.getHeader("howToUpdate").equals("complete")){
        AbstractReimbursement model = mapper.readValue(req.getInputStream(), AbstractReimbursement.class);
        service.update(model);
        String json = mapper.writeValueAsString(model);
        resp.setStatus(201);
        resp.getWriter().print(json);
        }
        else if(req.getHeader("howToUpdate").equals("byEmployee")){
            AbstractReimbursement model = mapper.readValue(req.getInputStream(), AbstractReimbursement.class);
            System.out.println(model);
            service.updateByEmployee(model);
            String json = mapper.writeValueAsString(model);
            resp.setStatus(201);
            resp.getWriter().print(json);
        }
    else if(req.getHeader("howToUpdate").equals("byadmin")){
            AbstractReimbursement model = mapper.readValue(req.getInputStream(), AbstractReimbursement.class);
            service.updateByAdmin(model);
            String json = mapper.writeValueAsString(model);
            resp.setStatus(201);
            resp.getWriter().print(json);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.delete(Integer.parseInt(req.getHeader("id")));
        resp.setStatus(200);
    }
}
