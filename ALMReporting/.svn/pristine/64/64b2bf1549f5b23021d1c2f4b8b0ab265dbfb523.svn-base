<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.homedepot.is.as.dto.ReadQCTestReportDetailByInputListDTO;"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quality Center v10 Report</title>
</head>
<body>
<%
	ReadQCTestReportDetailByInputListDTO report = (ReadQCTestReportDetailByInputListDTO) request
			.getAttribute("requestReport");
	
%>
<h3 align="center">Quality Center v10 Report</h3>
<table border="1" width="100%" align="center">
	<tr>
		<th width="45%">Test Type</th>
		<th width="15%">ARB Flag</th>
		<th width="50%">Comments</th>
	</tr>
	<% for(int i=0;i<report.getQcTestReportDetailList().size();i++){ %>
	<%  if(i % 2 == 0) { %>
	<tr bgcolor="#FFF8C6" >
		<td><%= report.getQcTestReportDetailList().get(i).getTestTypeName() %></td>
		<td align="center"><%= report.getQcTestReportDetailList().get(i).getArbRequiredFlag() %></td>
		<td><%= report.getQcTestReportDetailList().get(i).getCommentText() %></td>
	</tr>
	<% } else { %>
		<tr>
		<td><%= report.getQcTestReportDetailList().get(i).getTestTypeName() %></td>
		<td align="center"><%= report.getQcTestReportDetailList().get(i).getArbRequiredFlag() %></td>
		<td><%= report.getQcTestReportDetailList().get(i).getCommentText() %></td>
	</tr>
	
	<% } } %>
</table>
</body>
</html>