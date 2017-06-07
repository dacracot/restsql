package org.dacracot.storql.util;
//---------------------------------------------------
import java.sql.*;
//----------------------------------------------------
import javax.naming.*;
import javax.sql.*;
//----------------------------------------------------
import org.json.*;
//----------------------------------------------------
public class database
	{
	private Connection conn;
	private Statement stmt;
	private ResultSet rset;
	private final String NULL = "";
	//-----------------------------------------------
	public database()
		{
		conn = null;
		}
	//-----------------------------------------------
	public void getConn() throws Exception
		{
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/storql");
		conn = ds.getConnection();
		doSqlWithoutResult ("PRAGMA foreign_keys = ON");
		}
	//-----------------------------------------------
	public void releaseConn() throws Exception
		{
		conn.close();
		}
	//-----------------------------------------------
	private enum outputs
		{
		JSON,
		XML,
		CSV
		}
	//------------------------------------------------
	private String doSqlWithResult (String call, outputs output)
		{
		if (debug.DEBUG) debug.logger("org.dacracot.storql.util.database","doSqlWithResult(call)>> "+call);
		JSONArray jsonResult = new JSONArray();
		String result = "";
		try
			{
			stmt = conn.createStatement();
			stmt.setQueryTimeout(30);
			//----------------------------------------
			try
				{
				rset = stmt.executeQuery(call);
				ResultSetMetaData meta = rset.getMetaData();
				int colCnt = meta.getColumnCount();
				//------------------------------------
				if (!rset.next())
					result = "no records found";
				else
					{
					boolean hasNext = false;
					do
						{
						JSONObject jsonRow = new JSONObject();
						for (int i=1; i<=colCnt; i++)
							{
							Object tmp = rset.getObject(i);
							if (rset.wasNull())
								{
								jsonRow.put(meta.getColumnName(i),NULL);
								}
							else
								{
								jsonRow.put(meta.getColumnName(i),tmp.toString());
								}
							}
						jsonResult.put(jsonRow);
						} while (rset.next());
					switch (output)
						{
						case JSON:
							{
							result = jsonResult.toString();
							break;
							}
						case XML:
							{
							result = "<result>"+XML.toString(jsonResult,"row")+"</result>";
							break;
							}
						case CSV:
							{
							result = CDL.toString(jsonResult);
							break;
							}
						}
					}
				//------------------------------------
				}
			catch(Exception e)
				{
				result = debug.logger("org.dacracot.storql.util.database","error: doSqlWithResult:execute>> " + call,e);
				}
			finally
				{
				try
					{
					rset.close();
					stmt.close();
					}
				catch(Exception e)
					{
					debug.logger("org.dacracot.storql.util.database","error: doSqlWithResult:finally>> ",e);
					}
				}
			}
		catch(Exception e)
			{
			result = debug.logger("org.dacracot.storql.util.database","error: doSqlWithResult:prepareCall>> " + call,e);
			}
		if (debug.DEBUG) debug.logger("org.dacracot.storql.util.database","doSqlWithResult(output)>> "+result);
		return(result);
		}
	//-----------------------------------------------
	private String doSqlWithoutResult (String call)
		{
		if (debug.DEBUG) debug.logger("org.dacracot.storql.util.database","doSqlWithoutResult(call)>> "+call);
		String result = "";
		try
			{
			stmt = conn.createStatement();
			stmt.setQueryTimeout(30);
			//----------------------------------------
			try
				{
				stmt.executeUpdate(call);
				}
			catch(Exception e)
				{
				result = debug.logger("org.dacracot.storql.util.database","error: doSqlWithoutResult:execute>> " + call,e);
				}
			finally
				{
				try
					{
					stmt.close();
					}
				catch(Exception e)
					{
					debug.logger("org.dacracot.storql.util.database","error: doSqlWithoutResult:finally>> ",e);
					}
				}
			}
		catch(Exception e)
			{
			result = debug.logger("org.dacracot.storql.util.database","error: doSqlWithoutResult:prepareCall>> " + call,e);
			}
		if (debug.DEBUG) debug.logger("org.dacracot.storql.util.database","doSqlWithoutResult(output)>> "+result);
		return(result);
		}
	//-----------------------------------------------
	public String doSql (String call, String outputType)
		{
		if (debug.DEBUG) debug.logger("org.dacracot.storql.util.database","doSql(call,outputType)>> "+call+","+outputType);
		String result = "";
		//-------------------------------------------
		if ((call.toUpperCase().startsWith("SELECT")) || (call.toUpperCase().startsWith("WITH RECURSIVE")))
			{
			outputs output = null;
			try
				{
				output = outputs.valueOf(outputType.toUpperCase());
				}
			catch (IllegalArgumentException e)
				{
				return("No such output type >> "+outputType);
				}
			result = doSqlWithResult(call,output);
			}
		else
			{
			result = doSqlWithoutResult(call);
			}
		//-------------------------------------------
		if (debug.DEBUG) debug.logger("org.dacracot.storql.util.database","doSql(output)>> "+result);
		return(result);
		}
	//-----------------------------------------------
	}
//----------------------------------------------------