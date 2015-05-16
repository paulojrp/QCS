<%--
  Created by IntelliJ IDEA.
  User: jpedrom
  Date: 11/05/15
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <jsp:useBean id="back" class="diabetes.PersonalSensitivityToInsulin" scope="application"/>
    <jsp:setProperty name="back" property="*"/>

    <title>Simple JSP Calculator (Results)</title>
</head>
<body>

<div class="alert alert-success" role="alert" style="margin:20px;">

    <h3 style="margin-left: 10px">Number of insulin units: </h3>

    <h2 style="margin-left: 10px"><%= back.getResult()%></h2>

</div>

<br>
<br>
<div class="container">

    <div class="col-sm-4">
        <button class="btn btn-primary btn-lg" id="button" style="margin-left: 10px">Show technical information</button>
    </div>

    <div class="col-sm-4">
        <button id="myButton" class="btn btn-primary btn-lg" type="submit">Back to Calculator</button>
    </div>
    <script type="text/javascript">
        document.getElementById("myButton").onclick = function () {
            location.href = "index.html";
        };
    </script>

</div>


<div class="panel panel-info" id="details" style="display:none; margin:20px;">
    <!-- Default panel contents -->
    <div class="panel-heading">Technical information</div>
    <div class="panel-body">
        <p>3 Web Services used in calculation</p>
    </div>

    <!-- Table -->
    <table class="table">
        <tr>
            <th>Web Service</th>
            <th>Result</th>
        </tr>
        <tr>
            <td><%=back.getWSName(0)%></td>
            <td><%=back.getWSResult(0)%></td>
        </tr>
        <tr>
            <td><%=back.getWSName(1)%></td>
            <td><%=back.getWSResult(1)%></td>
        </tr>
        <tr>
            <td><%=back.getWSName(2)%></td>
            <td><%=back.getWSResult(2)%></td>
        </tr>
    </table>
</div>

<script>

    $("#button").click(function(){
        $("#details").toggle();
    });

</script>


</body>
</html>
