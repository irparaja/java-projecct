//DBServlet.java
package com.nt.servlet;

import java.sql.*; //jdbc api
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class DBServlet extends HttpServlet
{
	  private Connection con;
	  private PreparedStatement ps;
	  private ResultSet rs;
	  public void init(){
		  try{
	       //Access servlet Context obj
			   ServletContext sc=getServletContext();
			//read init param values
			   String driver=sc.getInitParameter("driver");
			   String url=sc.getInitParameter("url");
			   String dbuser=sc.getInitParameter("dbuser");
			   String dbpwd=sc.getInitParameter("dbpwd");
			   
  		  //register driver
		  Class.forName(driver);
		  //Establish the connection
		  con=DriverManager.getConnection(url,dbuser,dbpwd);
		  //create PreparedStatement obj
		  ps=con.prepareStatement("SELECT EMPNO,ENAME,SAL,JOB FROM EMP WHERE EMPNO=?");
          }//try
		  catch(SQLException se){
			  se.printStackTrace();
		  }
		  catch(ClassNotFoundException cnf){
			  cnf.printStackTrace();
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
	  }//init()

  public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
	   //General settings
	     PrintWriter pw=res.getWriter();
		 res.setContentType("text/html");
		 //include header
		 RequestDispatcher rd1=req.getRequestDispatcher("/headerurl");
		 rd1.include(req,res);
		 try{
		 //read form data
		 int no=Integer.parseInt(req.getParameter("teno"));
		 //write jdbc code to get emp details
		     //set value query param
			 ps.setInt(1,no);
			 //execute the query
			 rs=ps.executeQuery();
			 //process the ResultSet
			 if(rs.next()){
				 pw.println("<h1>"+no+" Emp Details are </h1>");
				 pw.println("<br>Emp no:"+rs.getInt(1));
				 pw.println("<br>Emp Name:"+rs.getString(2));
				 pw.println("<br>Emp Salary:"+rs.getInt(3));
				 pw.println("<br> Emp Job :"+rs.getString(4));
			 }//if
			 else{
               pw.println("<h1>"+no+" Emp Details are not found </h1>");
			 }
			 //add hyperlink
			 pw.println("<br><a href='input.html'>home </a><br><br><br>");
			 //include footer
			 RequestDispatcher rd2=req.getRequestDispatcher("/footer.html");
			 pw.close();
			 rd2.include(req,res);
			 //close stream
			 pw.close();
		 }//try
		 catch(Exception e){
			 RequestDispatcher rd=req.getRequestDispatcher("/errurl");
			 rd.forward(req,res);
		 }
	 }//doGet(-,-)

  public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
	  doGet(req,res);
	}

	public void destroy(){
      //close jdbc objs
	    try{
			if(rs!=null)
				rs.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}

	    try{
			if(ps!=null)
				ps.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}

	    try{
			if(con!=null)
				con.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	}//destroy()
}//class
//>javac    -d    .     DBServlet.java
