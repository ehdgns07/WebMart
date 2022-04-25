package com.nhnacademy.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Foodstable extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Goods> goodsMap;
        goodsMap = (Map<String, Goods>) getServletContext().getAttribute("goodsList");

        try(PrintWriter out = resp.getWriter()) {
            out.println("이름 : 양파 갯수 : " + goodsMap.get("onion").getNumber() + " 가격 : " +
                goodsMap.get("onion").getPrice());
            out.println("이름 : 계란 갯수 : " + goodsMap.get("egg").getNumber() + " 가격 : " +
                goodsMap.get("egg").getPrice());
            out.println("이름 : 파 갯수 : " + goodsMap.get("greenOnion").getNumber() + " 가격 : " +
                goodsMap.get("greenOnion").getPrice());
            out.println("이름 : 사과 갯수 : " + goodsMap.get("apple").getNumber() + " 가격 : " +
                goodsMap.get("apple").getPrice());
        }
    }
}
