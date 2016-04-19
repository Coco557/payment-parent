<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Please Login</title>
</head>
<body>
	<c:url value="/login" var="loginUrl" />
	<form:form name="f" action="${loginUrl}" method="post">
		<fieldset>
			<legend>Please Login</legend>
			<c:if test="${param.error != null}">
				<div class="alert alert-error">Invalid username and password.
				</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div class="alert alert-success">You have been logged out.</div>
			</c:if>
			<c:if test="${param.timeout != null}">
				<div class="alert alert-success">You have been logged out.</div>
			</c:if>
			<label for="username">Username</label> <input type="text"
				id="username" name="username" value="${username}" /> <label
				for="password">Password</label> <input type="password" id="password"
				name="password" />
			<div class="form-actions">
				<button type="submit" class="btn">Log in</button>
			</div>
		</fieldset>
	</form:form>
</body>
</html>