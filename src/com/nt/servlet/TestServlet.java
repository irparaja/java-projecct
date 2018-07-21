package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      //general settings
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");

		
		ServletContext sc=getServletContext();
		pw.println("<br>dbuser context param value"+sc.getInitParameter("dbuser"));
		
		ServletConfig cg=getServletConfig();
		pw.println("<br>dbuser init param value"+cg.getInitParameter("dbuser"));
		
		//Gathering various details using ServletContext obj
		pw.println("<br> Context path name/web application name "+sc.getContextPath());
		pw.println("<br> underlying Server info"+sc.getServerInfo());
		pw.println("<br> Servlet api version supported by the Server"+sc.getMajorVersion()+"."+sc.getMinorVersion());
		pw.println("<br> path of context path directory"+sc.getRealPath("/"));
		pw.println("<br> path of input.html"+sc.getRealPath("/input.html"));
		pw.println("<br> MIME type of input.html"+sc.getMimeType("input.html"));
		
		//writing Msg to log file
		sc.log("Today's Date and time"+new Date());

		//close stream
		pw.close();
	}//doGet(-,-)
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   doGet(req,res);
	}//doPost(-,-)
}//class
