<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<input type="hidden" name="p"value="${currentPage }">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board?p=1" method="post">
					<input type="text" id="kwd" name="kwd" value="${keyword }">
					<input type="submit" value="찾기">
					
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
					<c:forEach var="vo" items="${list}" varStatus="status">
					    <tr>
					        <td>${vo.no }</td>
					        <c:choose>
					            <c:when test="${vo.depth == 0}">
					                <td style="text-align:Left; padding-left:${20 * vo.depth}px">
					                    <a href="${pageContext.request.contextPath}/board/view/${vo.no}?p=${page}&kwd=${keyword}">${vo.title}</a>
					                </td>
					            </c:when>
					            <c:otherwise>
					                <td style="text-align:Left; padding-left:${20 * vo.depth}px">
					                    <img src='${pageContext.servletContext.contextPath}/assets/images/reply.png'>
					                    <a href="${pageContext.request.contextPath}/board/view/${vo.no}?p=${page}&kwd=${keyword}">${vo.title}</a>
					                </td>
					            </c:otherwise>
					        </c:choose>
					        <td>${fn:replace(fn:replace(vo.userName, ">", "&gt;"), "<", "&lt;")}</td>
					        <td>${vo.hit}</td>
					        <td>${vo.reg_date}</td>
					        <c:if test="${sessionScope.authUser.no eq vo.userNo}">
					        	<td><a href="${pageContext.request.contextPath}/board/delete?no=${vo.no}" class="del">삭제</a></td>
					        </c:if>	
					    </tr>
					</c:forEach>
				</table>
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:choose>
					    	<c:when test="${1 eq param.p }">
					    		<li>◀◀</li> <!-- Link to next page -->
					    		<li>◀</li> <!-- Link to next page -->	
					    	</c:when>
					    	<c:otherwise>
					    		<li><a href="${pageContext.request.contextPath}/board?p=${1 }&kwd=${keyword }">◀◀</a></li> <!-- Link to previous page -->
								<li><a href="${pageContext.request.contextPath}/board?p=${Math.max(1, param.p - 1) }&kwd=${keyword }">◀</a></li> <!-- Link to previous page -->
					    	</c:otherwise>
					    </c:choose> 
						
					        <c:forEach var="item" items="${entry }">
					            <c:choose>
					            	<c:when test="${param.p == null} ">
					                    <li class="selected">${1 }</li> <!-- Current page -->
					            	</c:when>
					                <c:when test="${item.pageNo == param.p}">
					                    <li class="selected">${item.pageNo}</li> <!-- Current page -->
					                </c:when>
					                <c:when test="${item.active eq true }">
					                	<li><a href="${pageContext.request.contextPath}/board?p=${item.pageNo}&kwd=${keyword }">${item.pageNo}</a></li>
					                </c:when>
					                <c:otherwise>
										<li>${item.pageNo }</li>					                    
					                </c:otherwise>
					            </c:choose>
					        </c:forEach>
					    <c:choose>
					    	<c:when test="${totalPage eq param.p }">
					    		<li>▶</li> <!-- Link to next page -->
					    		<li>▶▶</li> <!-- Link to next page -->	
					    	</c:when>
					    	<c:otherwise>
					    		<li><a href="${pageContext.request.contextPath}/board?p=${Math.min(totalPage, param.p + 1) }&kwd=${keyword }">▶</a></li> <!-- Link to next page -->
					    		<li><a href="${pageContext.request.contextPath}/board?p=${totalPage }&kwd=${keyword }">▶▶</a></li> <!-- Link to next page -->
					    	</c:otherwise>
					    </c:choose> 
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<c:if test="${not empty sessionScope.authUser }">
						<a href="${pageContext.servletContext.contextPath }/board/add" id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>