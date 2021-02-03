<%-- 
    Document   : all
    Created on : Aug 19, 2020, 2:15:22 PM
    Author     : Veljko
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All selections</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.15/css/dataTables.jqueryui.min.css"/> 
    </head>
    <body>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.0.0/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.4.1/js/bootstrap.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.15/js/dataTables.jqueryui.min.js"></script>
        <%@include file="../template/header.jsp" %>
        <%@include file="../template/menu.jsp" %>

        <h1>This is page for all matches!</h1> 
        
        <table id="example" class="table table-striped table-bordered" style="width:100%">
            <thead>
                <tr>
                    <th>Host</th>
                    <th>Host Goals</th>
                    <th>Away Goals</th>
                    <th>Away</th>
                    <th>Date</th>
                    <th>Delete</th>
                </tr>

            </thead>
            <tbody>
                <c:forEach var="match" items="${requestScope.matchesBySelection}">
                    <tr>
                        <td>${match.host.name}</td>
                        <td>${match.hostGoals}</td>
                        <td>${match.awayGoals}</td>
                        <td>${match.away.name}</td>
                        <td>
                         <fmt:formatDate type = "date"  value="${match.date}" />
                        </td>
                        <td>
                            <a href="/fifaWebServlet/application/match/delete/${match.id}">Delete</a>
                        </td>
                    </c:forEach>
                </tr>
            </tbody>
        </table>
        
        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>
        
        <%@include file="../template/footer.jsp" %>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    </body>
</html>
