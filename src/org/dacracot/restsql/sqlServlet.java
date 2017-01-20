package org.dacracot.restql;
//---------------------------------------------------
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
//---------------------------------------------------
import org.dacracot.restql.util.*;
//----------------------------------------------------
public class sqlServlet extends HttpServlet
	{
	//-----------------------------------------------
	private static final long serialVersionUID = 1961071705050000001L;
	private paramParser pp = null;
	//-----------------------------------------------
	public void init(ServletConfig config) throws ServletException
		{
		//-------------------------------------------
		try
			{
			debug.init(config);
			debug.logger("org.dacracot.restql.sqlServlet","initialized restql SQL version: "+sqlServlet.class.getPackage().getImplementationVersion()+" with "+config.getInitParameter("debugLevel")+" logging");
			pp = new paramParser();
			}
		catch (Exception e)
			{
			throw(new ServletException(e));
			}
		}
	//-----------------------------------------------
	private void doVerb(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
		{
		String verb = req.getMethod();
		try
			{
			sqlBean bb = new sqlBean(verb,pp.getPayload(req,"sql"),pp.getPayload(req,"outputType"));
			res.setContentType("text/plain");
			PrintWriter out = res.getWriter();
			//-------------------------------------------
			out.println(bb.restql());
			out.close();
			}
		catch(requestException re)
			{
			res.sendError(HttpServletResponse.SC_FORBIDDEN, re.getMessage());
			}
		}
	//-----------------------------------------------
	private void notImplemented(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
		{
		String verb = req.getMethod();
		res.setContentType("text/plain");
		PrintWriter out = res.getWriter();
		//-------------------------------------------
		out.println(verb+" has not been implemented.");
		out.close();
		}
	//-----------------------------------------------
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
		{
		doVerb(req,res);
		}
	//-----------------------------------------------
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
		{
		doVerb(req,res);
		}
	//-----------------------------------------------
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
		{
		notImplemented(req,res);
		}
	//-----------------------------------------------
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
		{
		notImplemented(req,res);
		}
	//-----------------------------------------------
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
		{
		notImplemented(req,res);
		}
	//-----------------------------------------------
	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
		{
		notImplemented(req,res);
		}
	//-----------------------------------------------
	}
//---------------------------------------------------
