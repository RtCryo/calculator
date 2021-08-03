package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;
import service.CalculationService;

@WebServlet("/calc")
public class CalcController extends HttpServlet {

    CalculationService calculate = new CalculationService();

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, JSONException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            jsonEnt.put("result", calculate.calculate(request.getParameter("x1"),request.getParameter("x2"),request.getParameter("op")));
            out.print(jsonEnt.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/calc2.html").forward(req, resp);
    }

}
