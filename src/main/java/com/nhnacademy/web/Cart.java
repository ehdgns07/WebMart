package com.nhnacademy.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cart extends HttpServlet {
    int numberOfOnion;
    int numberOfEgg;
    int numberOfGreenOnion;
    int numberOfApple;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        Map<String, Goods> goodsMap;
        goodsMap = (Map<String, Goods>) getServletContext().getAttribute("goodsList");

        String onionNumber = req.getParameter("onionnumber");
        String eggnumber = req.getParameter("eggnumber");
        String greenOniononnumber = req.getParameter("greenOniononnumber");
        String applenumber = req.getParameter("applenumber");

        numberOfOnion = Integer.parseInt(onionNumber);
        numberOfEgg = Integer.parseInt(eggnumber);
        numberOfGreenOnion = Integer.parseInt(greenOniononnumber);
        numberOfApple = Integer.parseInt(applenumber);

        if(goodsMap.get("onion").getNumber() < numberOfOnion){
            resp.sendRedirect("onionerr.html");
        }
        else if(goodsMap.get("egg").getNumber() < numberOfEgg){
            resp.sendRedirect("eggerr.html");
        }
        else if(goodsMap.get("greenOnion").getNumber() < numberOfGreenOnion){
            resp.sendRedirect("greenOnionerr.html");
        }
        else if(goodsMap.get("apple").getNumber() < numberOfApple){
            resp.sendRedirect("appleerr.html");
        }else {

            goodsMap.get("onion").setNumber(goodsMap.get("onion").getNumber() - numberOfOnion);
            goodsMap.get("egg").setNumber(goodsMap.get("egg").getNumber() - numberOfEgg);
            goodsMap.get("greenOnion").setNumber(goodsMap.get("greenOnion").getNumber() -
                numberOfGreenOnion);
            goodsMap.get("apple").setNumber(goodsMap.get("apple").getNumber() - numberOfApple);

            getServletContext().setAttribute("goodsList", goodsMap);

            resp.sendRedirect("/gotocart.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        
        int calculateOnionPrice = numberOfOnion*1000;
        int calculateEggPrice = numberOfEgg*2000;
        int calculateGreenOnion = numberOfGreenOnion*500;
        int calculateApple = numberOfApple*2000;
        int result = calculateOnionPrice + calculateEggPrice + calculateGreenOnion + calculateApple;

        try(PrintWriter out = resp.getWriter()){
            out.println("양파 : " + numberOfOnion + "개 " + calculateOnionPrice + "원");
            out.println("계란 : " + numberOfEgg + "개 " + calculateEggPrice + "원");
            out.println("파 : " + numberOfGreenOnion + "개 " +calculateGreenOnion + "원");
            out.println("사과 : " + numberOfApple + "개 " + calculateApple + "원");
            out.println("총 합계 : " + result + "원");

        }

    }
}
