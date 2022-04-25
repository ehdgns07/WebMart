package com.nhnacademy.web;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        Map<String, Goods> goodsMap;
        goodsMap = (Map<String, Goods>) getServletContext().getAttribute("goodsList");

        String onionNumber = req.getParameter("onionnumber");
        String eggnumber = req.getParameter("eggnumber");
        String greenOniononnumber = req.getParameter("greenOniononnumber");
        String applenumber = req.getParameter("applenumber");

        int onion = Integer.parseInt(onionNumber);
        int egg = Integer.parseInt(eggnumber);
        int greenOnion = Integer.parseInt(greenOniononnumber);
        int apple = Integer.parseInt(applenumber);

        if(goodsMap.get("onion").getNumber() < onion){

        }
        if(goodsMap.get("egg").getNumber() < egg){

        }
        if(goodsMap.get("greenOnion").getNumber() < greenOnion){

        }
        if(goodsMap.get("apple").getNumber() < apple){

        }

        goodsMap.get("onion").setNumber(goodsMap.get("onion").getNumber() - onion);
        goodsMap.get("egg").setNumber(goodsMap.get("egg").getNumber() - egg);
        goodsMap.get("greenOnion").setNumber(goodsMap.get("greenOnion").getNumber() - greenOnion);
        goodsMap.get("apple").setNumber(goodsMap.get("apple").getNumber() - apple);
        getServletContext().setAttribute("goodsList", goodsMap);
    }
}
