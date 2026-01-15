package com.example.foodapp;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/api/foods")
public class FoodServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write("""
            [
              { "name": "Chicken Breast", "protein": 31 },
              { "name": "Brown Rice", "protein": 2.6 }
            ]
        """);
    }
}
