<%-- 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    Document   : add
    Created on : Aug 19, 2020, 1:12:03 PM
    Author     : Veljko

--%> 
<%@page import="fon.silab.fifawebservlet.action.services.SortSelectionsByName"%>
<%@page import="java.util.Collections"%>
<%@page import="fon.silab.fifawebservlet.model.Selection"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">

    <%
        SortSelectionsByName sortSelectionsByName = new SortSelectionsByName();
        List<Selection> selections = (List<Selection>) application.getAttribute("selections");
        Collections.sort(selections, sortSelectionsByName);
    %>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@include file="../template/header.jsp" %>
    <%@include file="../template/menu.jsp" %>


    <body class="pozadina">

        <form  autocomplete="off" class="forma" action="/fifaWebServlet/application/match/save" method="post">
            <div class="row">
                <div class="form-group  col-md-6">
                    <label translate>Host</label>
                    <select name="host" id="host">                                   
                        <c:forEach var="selection" items="${selections}">
                            <option>${selection.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-md-6">
                    <label>Hostgoals</label>
                    <input name="hostGoals" class="form-control" required>
                </div>
            </div>
            <div class="row">
                <div class="form-group  col-md-6">
                    <label translate>Away</label>
                    <select name="away" id="away">                                   
                        <c:forEach var="selection" items="${selections}">
                            <option>${selection.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-md-6">
                    <label>Awaygoals</label>
                    <input name="awayGoals" class="form-control" required>
                </div>
            </div>
            <div class="form-group">
                <label translate>MatchType</label>
                <select name="matchType" id="matchType">                                   
                    <c:forEach var="matchType" items="${applicationScope.matchTypes}">
                        <option>${matchType.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="row">
                <div class="form-group  col-md-4">
                    <label translate>Day</label>
                    <select name="day" id="away">                                   
                        <c:forEach var="counter" begin="1" end="31">
                            <option>${counter}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group  col-md-4">
                    <label translate>Month</label>
                    <select name="month" id="away">                                   
                        <c:forEach var="counter" begin="1" end="12">
                            <option>${counter}</option>
                        </c:forEach>
                    </select>

                </div>

                <div class="form-group  col-md-4">
                    <label translate>Year</label>
                    <select name="year" id="away">                                   
                        <c:forEach var="counter" begin="${applicationScope.year-4}" end="${applicationScope.year}">
                            <option>${counter}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="message" class="validation-error">${message}</label>             
                </div>    

            </div>
            <div class="form-group">
                <button type="submit"  class="btn btn-lg btn-block dugme">Add Match</button>
            </div>
        </form>


        <%@include file="../template/footer.jsp" %>

        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.0.0/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.4.1/js/bootstrap.js"></script>

        <style>
            .forma {
                background-color: white;
                /* border: 0.5px solid black; */
                border-radius: 10px;
                padding: 30px;
                margin: 50px;
                -webkit-box-shadow: 0px 0px 22px -5px rgba(0, 0, 0, 0.75);
                -moz-box-shadow: 0px 0px 22px -5px rgba(0, 0, 0, 0.75);
                box-shadow: 0px 0px 22px -5px rgba(0, 0, 0, 0.75);
            }

            .validation-error {
                margin-left: 20px;
                font-style: italic;
                color: rgb(169, 10, 10);
            }

            .dugme {
                background-color: green;
                color: white;
                margin: 50px auto;
            }

            .cancelBtn {
                background-color: red;
                color: white;
                margin: 50px auto;
            }

            .footerBtns {
                margin: 50px;
            }

            .pozadina {
                background-color: whitesmoke;
            }
        </style>



    </body>


</html> 