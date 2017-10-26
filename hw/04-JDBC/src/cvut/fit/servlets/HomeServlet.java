package cvut.fit.servlets;

import cvut.fit.service.DAOSingleton;
import cvut.fit.service.Record;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Record> all;

        all = DAOSingleton.getRecordsDAO().getAll();

        System.out.println(all);
    }
}
