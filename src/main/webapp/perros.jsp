
<%@page import="com.ipartek.formacion.model.pojo.Perro"%>
<%@page import="java.util.ArrayList"%>

<%@include file="includes/header.jsp"%>
<%@include file="includes/navigation.jsp"%>


<h1>Perros</h1>


<%
	ArrayList<Perro> perros = (ArrayList<Perro>) request.getAttribute("perros");
	String mensaje = (String) request.getAttribute("mensaje");
%>

Listado :

<ul>
	<%
		for (Perro p : perros) {
	%>

	<li style= "display: flex; border-top:1px solid black; padding-top:5px;" ><%=p.getNombre()%> - <%=p.getId()%>  
													<div style= " width: 100px; margin: 25px;">
														<img src="<%=p.getFoto()%>" alt="imagen del perro" style="max-width: 100%; max-height: 100%;">
													</div>
													<a href="perros?id=<%=p.getId()%>&adoptar=s">- Adoptar -</a>
													<a href="perros?id=<%=p.getId()%>&editar=s">- Modificar -</a>
	</li>

	<%
		}
	%>
</ul>

<hr>

<%
	Perro perroEditar = (Perro)request.getAttribute("perroEditar");
	if ( perroEditar == null ){
		perroEditar = new Perro();
	}
%>
<p style="color: orange;"><%=mensaje %> </p>
<form action="perros" method="post">
	<fieldset>
		<legend>Formulario</legend>
		<input 	type="hidden"
				name="id"
				value="<%=perroEditar.getId()%>"
				readonly  
				required >
		<br>
		<label for="nombre">Nombre del perro :</label>
		<input 	type="text" 
				name="nombre" 
				placeholder="nombre del chucho" 
				value="<%=perroEditar.getNombre()%>" 
				required>	
	<br>
		<label for="foto">Imagen del chucho : </label>
        <input style="width: 257px;"
				type="text"
				name="foto"
				value="<%=perroEditar.getFoto()%>"
                required="required"
                placeholder="url de imagen, formato:  .jpg o .gif o.png "
                pattern="[A-Za-zñÑ0-9_/\-\.\:]{2,255}\.(jpg|jpeg|png|gif)">
		<br><br>
	<button class="botonenviar" type="submit" value="acceder">Añadir perro</button>
</form>
</fieldset>


<%@include file="includes/footer.jsp"%>