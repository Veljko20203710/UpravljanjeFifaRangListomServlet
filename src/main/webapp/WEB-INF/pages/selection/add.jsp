<%-- 

    Document   : add
    Created on : Aug 19, 2020, 1:12:03 PM
    Author     : Veljko

--%>
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
    <div class="container">
        <form  autocomplete="off"  action="/fifaWebServlet/application/selection/save" method="post" class="forma">
            <div class="row">
                <div class="form-group  col-md-6">
                    <label translate>name</label>
                    <input name="name" class="form-control"
                           required>
                    <div class="validation-error">${message}</div>
                </div>

                <div class="form-group  col-md-6">
                    <label translate>confederation</label>
                    <select name="confederations" id="confederation" class="form-control">                                   
                        <c:forEach var="confederation" items="${applicationScope.confederations}">
                            <option>${confederation.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>



            <div class="form-group">
                <button type="submit"
                        class="btn btn-lg btn-block dugme"translate>Add Selection</button>
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
