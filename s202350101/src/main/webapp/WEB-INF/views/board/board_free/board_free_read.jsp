<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--CSS START -->
<!-- CSS END -->

<!-- JS START -->	
<script type="text/javascript">
	function closeDoc() {
	   if(opener) {
	      opener.location.reload();
	      window.close();
	   }else{
	      location.reload();
	   }
	}
</script>
<!-- JS END -->

<script type="text/javascript">
	$(function() {
		
		$.ajax({
			url			: '/main_header',
			dataType 	: 'html',
			success		: function(data) {
				$('#header').html(data);
			}
		});
		
		$.ajax({
			url			: '/main_menu',
			dataType 	: 'html',
			success		: function(data) {
				$('#menubar').html(data);
			}
		});
	
		$.ajax({
			url			: '/main_footer',
			dataType 	: 'html',
			success		: function(data) {
				$('#footer').html(data);
			}
		});
	});
</script>
</head>
<body>
<!------------------------------ //개발자 소스 입력 START ----------------------------->
			<h4 class ="pt-4">문서 조회</h4>
			
			<table width="100%" style="margin-top:10px;">
				<tr>
					<td style="text-align:right">
						<button type="button" class="btn btn-secondary btn-sm" onclick="closeDoc()">닫기</button>
					</td>
				</tr>
			</table>
			
			<table class="table table-hover">
				<tr> <th>글 번호</th>       <td>${freeContent.doc_no}</td> </tr>
				<tr> <th>이름</th>         <td>${freeContent.user_name}</td> </tr>
				<tr> <th>작성일</th>      <td>${freeContent.create_date}</td> </tr>
				<tr> <th>수정일</th>      <td>${freeContent.modify_date}</td> </tr>
				<tr> <th>게시종류</th>      <td>${freeContent.bd_category}</td> </tr>
				<tr> <th>제목</th>        <td>${freeContent.subject}</td> </tr>
				<tr> <th>본문</th>        <td>${freeContent.doc_body}</td> </tr>
				<tr> <th>조회수</th>       <td>${freeContent.bd_count}</td> </tr>
				<tr> <th>추천</th>        <td>${freeContent.good_count}</td> </tr>
				<tr> <th>첨부파일</th>     <td>${freeContent.attach_name}<img alt="" src="${pageContext.request.contextPath}/${freeContent.attach_path}/${freeContent.attach_name}"></td> </tr>	
			</table>
			
			<div>댓글</div>				
			<table class="table table-sm">
				<tr>
					<th>이름</th> <th>작성일</th> <th>내용</th>
				</tr>
				<c:forEach var="comt" items="${freeComtList}">
					<tr>
						<td>${comt.user_name}</td>
						<td>${comt.create_date}</td>
						<td>${comt.comment_context}</td>
					</tr>
				</c:forEach>
			</table>

<!------------------------------ //개발자 소스 입력 END ------------------------------->
</body>
</html>