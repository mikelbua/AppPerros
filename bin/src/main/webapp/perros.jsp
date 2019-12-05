
<%@page import="com.ipartek.formacion.model.pojo.Perro"%>
<%@page import="java.util.ArrayList"%>

<%@include file="includes/header.jsp" %>
<%@include file="includes/navigation.jsp" %>


<h1>Perros</h1>


<%
	
	ArrayList<Perro> perros = (ArrayList<Perro>)request.getAttribute("perros");

%>

listado

<ol>
	<% for ( Perro p :  perros ){ %>
	
		<li><%=p.getNombre()%> - <%=p.getId()%></li>
		
	<% } %>
</ol>

<hr>

formulario

<form action="perros" method="post">
	
	<input name="nombre" placeholder="nombre del bicho" required>	
	
	<input type="submit" value="que vaaaaaaaaa">
</form>


<%@include file="includes/footer.jsp" %>