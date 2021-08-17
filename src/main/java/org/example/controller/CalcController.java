package org.example.controller;

import org.example.service.CalculationService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class CalcController {

    CalculationService calculate;

    public CalcController(CalculationService calculate) {
        this.calculate = calculate;
    }

    @GetMapping("/")
    public String indexGet(){
        return "index";
    }

    @GetMapping("/calc")
    public String calcGet(){
        return "calc2";
    }

    @PostMapping("/calc")
    public void calcPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

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
}
