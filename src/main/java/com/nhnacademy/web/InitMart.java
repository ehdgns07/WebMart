package com.nhnacademy.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitMart extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        Map<String, Goods> goodsMap = new HashMap<>();
        String[] priceAndNumber;

        String goods = getServletContext().getInitParameter("onion");
        priceAndNumber = goods.split(",");
        Goods onion = new Goods("onion", Integer.parseInt(priceAndNumber[0]), Integer.parseInt(priceAndNumber[1]));
        goodsMap.put("onion", onion);

        goods = getServletContext().getInitParameter("egg");
        priceAndNumber = goods.split(",");
        Goods egg = new Goods("egg", Integer.parseInt(priceAndNumber[0]), Integer.parseInt(priceAndNumber[1]));
        goodsMap.put("egg", egg);

        goods = getServletContext().getInitParameter("greenOnion");
        priceAndNumber = goods.split(",");
        Goods greenOnion = new Goods("greenOnion", Integer.parseInt(priceAndNumber[0]), Integer.parseInt(priceAndNumber[1]));
        goodsMap.put("greenOnion", greenOnion);

        goods = getServletContext().getInitParameter("apple");
        priceAndNumber = goods.split(",");
        Goods apple = new Goods("apple", Integer.parseInt(priceAndNumber[0]), Integer.parseInt(priceAndNumber[1]));
        goodsMap.put("apple", apple);

        getServletContext().setAttribute("goodsList", goodsMap);

        resp.sendRedirect("/init.html");

        try (PrintWriter out = resp.getWriter()) {
            out.println(getServletContext().getAttribute("goodsList").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



