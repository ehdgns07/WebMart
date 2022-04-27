package com.nhnacademy.web;

import static java.lang.Thread.sleep;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="foodsStand", urlPatterns = "/foods")
public class FoodStand extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.setContentType("text/plain");

        RequestDispatcher rd =req.getRequestDispatcher("/init");
        rd.include(req,resp);

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

            resp.setContentType("text/html");

            out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<form method =\"post\" action = \"/cart\">\n" +
                "<br>구매할 갯수를 입력하세요</br>\n" +
                "    <input type=\"text\" name=\"onionnumber\"/>양파<br/>\n" +
                "    <input type=\"text\" name=\"eggnumber\"/>계란<br/>\n" +
                "    <input type=\"text\" name=\"greenOniononnumber\"/>파<br/>\n" +
                "    <input type=\"text\" name=\"applenumber\"/>사과<br/>\n" +
                "    <input type=\"submit\"/>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
        }


    }

}
