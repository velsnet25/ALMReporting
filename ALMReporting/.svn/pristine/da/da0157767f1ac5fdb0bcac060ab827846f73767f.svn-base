package com.homedepot.is.as.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class to generate report.
 * 
 * @author SXB8898
 *
 */
public class CreateReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateReportServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String reportId = request.getParameter("reportId");
		String stageId = request.getParameter("stageId");
		
		//stageId will be null for All Stages, in that case update
		//stageId to -1 for validation
		if(stageId == null)
			stageId = "-1";
		
		RequestDispatcher rd = null;
			if(reportId != null && reportId.length() > 0
					&& stageId != null && stageId.length() > 0){
				rd = getServletContext().getRequestDispatcher("/jsp/showReport.jsp?reportId="+reportId+"&stageId="+stageId);
				rd.forward(request, response);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
