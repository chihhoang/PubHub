	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- Java imports -->
	<%@page import="examples.pubhub.model.Book"%>
	<%@page import="examples.pubhub.model.Tag"%>
	<%@page import="examples.pubhub.dao.BookDAO"%>
	<%@page import="examples.pubhub.dao.TagDAO"%>
	<%@page import="examples.pubhub.utilities.DAOUtilities"%>
	
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
	
		<h1>PUBHUB <small>Modify Tag(s) of - "${book.title}"</small></h1>
		<hr class="book-primary">
		  
		  <input type="hidden" class="form-control" id="isbn13" name="isbn13" required="required" value="${book.isbn13 }" readonly />
		  
		  <div class="form-group">
		    <label for="title" class="col-sm-4 control-label">Title</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="title" name="title" placeholder="Title" required="required" value="${book.title }" readonly />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="author" class="col-sm-4 control-label">Author</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="author" name="author" placeholder="Author" required="required" value="${book.author }" readonly />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="price" class="col-sm-4 control-label">Price</label>
		    <div class="col-sm-5">
		      <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Price" required="required" value="${book.price}" readonly />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="price" class="col-sm-4 control-label">Tags</label>
		    <div class="col-sm-5">
		    	<%-- <form action="RemoveTag?isbn=${book.isbn13}&remove_tag=${tag.tag_name}" method="get"> --%>
				    <div class="form-control" id="tags">
			      	<c:forEach var="tag" items="${book.tags}">
					  		<c:out value="  ${tag.tag_name}" />
					  		<a href="RemoveTag?isbn13=${book.isbn13}&remove_tag=${tag.tag_name}">    (remove)    </a>
							</c:forEach>
				    </div>
			    <!-- </form> -->
		    </div>
		  </div>
		  <form action="AddTag?isbn=${book.isbn13}&new_tag=new_tag" method="get">
			  <div class="form-group">
			  	<label for="new_tag" class="col-sm-4 control-label">New Tag</label>
			  	<div class="col-sm-4">
			  		<input type="text" class="form-control" id="new_tag" name="new_tag" placeholder="Enter a new tag" required="required" />
			  	</div>
			    <div class="col-4 col-sm-1">			      
			      	<input type="hidden" name="isbn13" value="${book.isbn13}">
			      	<button class="btn btn-primary">Add</button>
			    </div>
			  </div>
		  </form>

	  </div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
