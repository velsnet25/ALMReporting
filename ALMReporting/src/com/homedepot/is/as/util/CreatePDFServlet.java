package com.homedepot.is.as.util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreatePDFServlet
 */
public class CreatePDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePDFServlet() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException 
	{
		doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException 
	{
		int i = 0;
		int k = 0;
		int maxLength = req.getContentLength();
		byte[] bytes = new byte[maxLength];
		String method = req.getParameter("method");
		String name = req.getParameter("name");
		ServletInputStream si = req.getInputStream();
		while (true)
		{
			k = si.read(bytes,i,maxLength-1);
			i += k;
			if (k == -1)
				break;
		}
		if (bytes != null)
		{
			ServletOutputStream stream = resp.getOutputStream();
			resp.setContentType("application/pdf");
			resp.setContentLength(bytes.length);
			resp.setHeader("Content-Disposition",method + ";filename=" + name);
			stream.write(bytes);
			stream.flush();
			stream.close();
		}
		else
		{
			resp.setContentType("text");
			resp.getWriter().write("bytes is null");
		}
	}
}