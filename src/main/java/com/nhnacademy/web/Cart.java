package com.nhnacademy.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="cart", urlPatterns = "/cart")
public class Cart extends HttpServlet {
    int numberOfOnion;
    int numberOfEgg;
    int numberOfGreenOnion;
    int numberOfApple;
    Map<String, Goods> goodsMap;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

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
        
        int calculateOnionPrice = numberOfOnion*goodsMap.get("onion").getPrice();
        int calculateEggPrice = numberOfEgg*goodsMap.get("egg").getPrice();
        int calculateGreenOnion = numberOfGreenOnion*goodsMap.get("greenOnion").getPrice();
        int calculateApple = numberOfApple*goodsMap.get("apple").getPrice();
        int result = calculateOnionPrice + calculateEggPrice + calculateGreenOnion + calculateApple;

        try(PrintWriter out = resp.getWriter()){
            out.println("장바구니");
            out.println("양파 : " + numberOfOnion + "개 " + calculateOnionPrice + "원");
            out.println("계란 : " + numberOfEgg + "개 " + calculateEggPrice + "원");
            out.println("파 : " + numberOfGreenOnion + "개 " +calculateGreenOnion + "원");
            out.println("사과 : " + numberOfApple + "개 " + calculateApple + "원");
            out.println("총 합계 : " + result + "원");

        }

    }
}
