package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{

		  //general settings
	    PrintWriter pw=res.getWriter();
	    res.setContentType("text/html");
	    //header logic
	    pw.println("<h1><marquee> H C  L Technologies </marquee></h1>");
	    pw.println("<br><br><br>");
	    //do not close stream
	 
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}//doPost(-,-)
}
