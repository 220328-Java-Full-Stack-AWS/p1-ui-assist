package revagenda.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import revagenda.models.AbstractUser;
import revagenda.services.AuthService;
import revagenda.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

private AuthService authService;
private UserService userService;
private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.authService = new AuthService();
        this.userService = new UserService();
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AbstractUser model = mapper.readValue(req.getInputStream(), AbstractUser.class);
        AbstractUser outputModel;
        switch (req.getHeader("mode")){
            case "register":

                outputModel = authService.register(model);
                String json = mapper.writeValueAsString(outputModel);
                resp.getWriter().print(json);
                resp.setHeader("access-control-expose-headers", "authToken");
                resp.setHeader("authToken", outputModel.getUsername());
                resp.setStatus(201);
                break;

            case "login":

                outputModel = authService.login(model);
                String outPassWord = outputModel.getPassword();
                String inPassWord = model.getPassword();
                System.out.println(outPassWord);
                if(model.getUsername() != null & outPassWord.equals(inPassWord)) {
                    json = mapper.writeValueAsString(outputModel);
                    resp.setStatus(200);
                    resp.getWriter().print(json);
                    resp.setHeader("access-control-expose-headers", "authToken");
                    resp.setHeader("authToken", model.getUsername());
                }else {
                    resp.setStatus(401);
                }
                break;

            default:
                resp.setStatus(404);
                break;

        }
    }






}
