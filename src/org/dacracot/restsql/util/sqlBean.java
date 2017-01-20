package org.dacracot.restql.util;
//---------------------------------------------------
import java.io.*;
import java.util.*;
//----------------------------------------------------
import javax.xml.parsers.*;
//----------------------------------------------------
import org.xml.sax.*;
//----------------------------------------------------
public class sqlBean
	{
	//-----------------------------------------------
	private verbs verb;
	private String payload;
	private String outputType;
	private database db;
	//-----------------------------------------------
	public sqlBean(String verb, String payload, String outputType) throws requestException
		{
		this.verb = verbs.valueOf(verb);
		this.payload = payload;
		this.outputType = outputType;
		try
			{
			db = new database();
			}
		catch (Exception e)
			{
			debug.logger("org.dacracot.restql.util.sqlBean","error: constructor >> ",e);
			}
		}
	//-----------------------------------------------
	private enum verbs
		{
		GET,
		POST,
		PUT,
		DELETE
		}
	//-----------------------------------------------
	public String restql()
		{
		String result = "";
		try
			{
			db.getConn();
			switch (verb)
				{
				case GET:		// select (but any sql will work)
					{
					result = db.doSql(payload,outputType);
					break;
					}
				case POST:		// insert (but any sql will work)
					{
					result = db.doSql(payload,outputType);
					break;
					}
				case PUT:		// update (but any sql will work)
					{
					result = db.doSql(payload,outputType);
					break;
					}
				case DELETE:	// delete (but any sql will work)
					{
					result = db.doSql(payload,outputType);
					break;
					}
				}
			db.releaseConn();
			}
		catch (Exception e)
			{
			result = debug.logger("org.dacracot.restql.util.sqlBean","error: restql >> ",e);
			}
		return(result);
		}
	//-----------------------------------------------
	}
//---------------------------------------------------
