<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<h1>Perros JSTL</h1>
<p> Mismo ejemplo pero usando Taglibs/Expresion leguage(EL)</p>

<c:if test="${not empty mensaje}">
	<p style="color:orange; font-size:2em;">${mensaje}</p>
</c:if>

<c:if test="${empty mensaje}">
	<p>No hay mensajes</p>
</c:if>

<h2>Hay ${fn:length(perros)} perros para adoptar</h2>

<ul>
	<c:forEach items="${perros}" var="p">
		<li>
			${p.id} ${p.nombre}
			<img src="${p.foto}" style="width:100px; height: 100px;" alt="foto alterntiva del perro">
			<a href="perros2?id=${p.id}&editar=s">- Modificar -</a>
			<a href="perros2?id=${p.id}&eliminar=s">- Adoptar -</a>
		</li>
	</c:forEach>
</ul>


<c:if test="${empty perroEditar}">
	No hay perros para modificar
</c:if>

${perroEditar}