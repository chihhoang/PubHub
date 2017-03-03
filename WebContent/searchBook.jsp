	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- Java imports -->
	<%@page import="examples.pubhub.model.Book"%>
	<%@page import="examples.pubhub.model.Tag"%>
	<%@page import="examples.pubhub.dao.BookDAO"%>
	<%@page import="examples.pubhub.dao.TagDAO"%>
	<%@page import="examples.pubhub.utilities.DAOUtilities"%>
	
	<%@page import="java.util.List"%>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
	  
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
		
		<hr class="book-primary">
				
		<table class="table table-striped table-hover table-responsive search-datatable">
			<thead>
				<tr>
					<td>ISBN-13:</td>
					<td>Title:</td>
					<td>Author:</td>
					<td>Publish Date:</td>
					<td>Price:</td>
					<td>Tags:</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
			
				<!-- Table -->
				<c:forEach var="book" items="${books}">
					<tr>
						<td><c:out value="${book.isbn13}" /></td>
						
						<td><c:out value="${book.title}" /></td>
						<td><c:out value="${book.author}" /></td>
						<td><c:out value="${book.publishDate}" /></td>
						
						<td><fmt:formatNumber value="${book.price}" type="CURRENCY"/></td>
						
						
						<td>
							<c:forEach var="tag" items="${book.tags}">
								<a href=""><c:out value="${tag.tag_name}  " /></a>
							</c:forEach>
						</td>
						
						<td><form action="DownloadBook" method="get">
							<input type="hidden" name="isbn13" value="${book.isbn13}">
							<button class="btn btn-success">Download</button>
						</form></td>
						<td><form action="ViewBookDetails?isbn=${book.isbn13}" method="get">
							<input type="hidden" name="isbn13" value="${book.isbn13}">
							<button class="btn btn-primary">Details</button>
						</form></td>
						<td><form action="ModifyTag?isbn=${book.isbn13}" method="get">
							<input type="hidden" name="isbn13" value="${book.isbn13}">
							<button class="btn btn-primary">Modify Tag</button>
						</form></td>
						
						<!-- <td><c:out value="${book.tags}" /></td> -->
					</tr>
				</c:forEach>
			</tbody>
		</table>

	  </div>
	</header>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />