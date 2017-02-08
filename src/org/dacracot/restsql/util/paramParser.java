package org.dacracot.storql.util;
//---------------------------------------------------
import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
//---------------------------------------------------
public class paramParser
	{
	//-----------------------------------------------
	private String findParameterValue(String key, String payload) throws Exception
		{
		String marker = key+"=";
		String[] pairs = payload.split("&");
		for (String pair : pairs)
			{
			int idx = pair.indexOf(marker);
			if (idx == -1)
				continue;
			else
				return(URLDecoder.decode(pair.substring(marker.length()), "UTF-8"));
			}
		throw(new Exception(key+" not found in query"));
		}
	//-----------------------------------------------
	public String getPayload(HttpServletRequest req, String name)
		{
		String result = "";
		String[] value = req.getParameterValues(name);
		if (value != null)
			result = value[0];  // only ever sending a single value
		else
			{
			StringBuilder buffer = new StringBuilder(512);
			try
				{
				BufferedReader postReader = req.getReader();
				String line = null;
				//---------------------------------------
				while((line = postReader.readLine()) != null)
					{
					buffer.append(line);
					}
				result = findParameterValue(name,buffer.toString());
				//---------------------------------------
				}
			catch(Exception e)
				{
				result = debug.logger("org.dacracot.storql.sqlServlet","error: getPayload>> ",e);
				return(result);
				}
			}
		return(result);
		}
	//-----------------------------------------------
	}
//---------------------------------------------------