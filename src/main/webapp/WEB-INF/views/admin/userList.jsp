<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container form-style">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>유저 아이디</th>
				<th>유저 이메일</th>
				<th>가입날짜</th>

			</tr>
		</thead>
		<tbody>

			<c:forEach var="user" items="${userList.content}">
				<tr>
					<td id="id">${user.id}</td>
					<td>${user.username}</td>
					<td>${user.email}</td>
					<td>${user.createDate}</td>

				</tr>
			</c:forEach>

		</tbody>
	</table>

	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${userList.first}">
				<li class="page-item disabled"><a class="page-link" href="?page=${userList.number-1}">이전</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${userList.number-1}">이전</a></li>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${userList.last}">
				<li class="page-item disabled"><a class="page-link" href="?page=${userList.number+1}">다음</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${userList.number+1}">다음</a></li>
			</c:otherwise>
		</c:choose>


	</ul>
</div>

<script src="/js/admin.js"></script>
<%@ include file="../layout/footer.jsp"%>
