package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorServlet extends HttpServlet {
	
	@Override
	public  void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("ErrorServlet:doGet(-,-)");
		//general settings
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		//Display error message
		pw.println("<h1 style='color:red'>Internal Problem Try agin </h1>");
		//add hyperlink
		pw.println("<a href='input.html'>home</a>");
		//close stream
		pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
	

}
