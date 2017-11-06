<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="service.DB" %>
<%@ page import="service.api.Trip" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List</title>
</head>
<body>
  <h1>Trips:</h1>
 
  <ul>
  <%  for (Trip item : DB.getInstance().getTrips()) {  %>
      <li>
      <%=item.getId()%> <%=item.getName()%> 
      (<%=item.getBookings().size()%>/<%=item.getCapacity()%>)
      
     	<%  for (String name : item.getBookings()) {  %>
     		<%= name %>,
     	<%  }  %>
      </li>
  <%  }  %>
  </ul>
  
  <h1>Book a trip</h1>
    <form action="./Book">
	  Please enter name: <br>
	  <input type="text" name="name" size="20px"> <br>
	  <select name="tripId">
	  	  <%  for (Trip item : DB.getInstance().getTrips()) {  %>
      			<option value=<%=item.getId()%>><%=item.getName()%></option>
  <%  			}  %>
  
  <br>
	  </select>
	  <input type="submit" value="submit">
	</form>
  
  <h1>Create trip</h1>
  <form action="./Trips">
  Please trip name: <br>
  <input type="text" name="name" size="20px"> <br>
  Please capacity: <br>
  <input type="text" name="capacity" size="20px">
  <input type="submit" value="submit">
</form>
</body>
</html>