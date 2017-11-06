<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cvut.fit.service.DAOSingleton" %>
<%@ page import="cvut.fit.service.Record" %>
<html>
<head>
    <title>HW-04-jdbc</title>
</head>
<body>
<h1>Data</h1>

<table>
    <thead>
        <tr>
            <td>id</td>
            <td>type</td>
            <td>location</td>
            <td>capacity</td>
            <td>occupied</td>
            <td>trip</td>
            <td>person</td>
        </tr>
    </thead>
    <% for (Record r : DAOSingleton.getRecordsDAO().getAll() ) { %>
        <tr>
            <td><%=r.getId()%></td>
            <td><%=r.getType()%></td>
            <td><%=r.getLocation()%></td>
            <td><%=r.getCapacity()%></td>
            <td><%=r.getOccupied()%></td>
            <td><%=r.getTrip()%></td>
            <td><%=r.getPerson()%></td>
        </tr>
    <% } %>
</table>

</body>
</html>
