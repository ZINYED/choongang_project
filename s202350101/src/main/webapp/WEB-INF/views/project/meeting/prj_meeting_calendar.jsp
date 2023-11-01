<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--CSS START -->
<style type="text/css">
	div #calendar {
		width: 80%;
		margin-top: 30px;
	}
	
</style>
<!-- CSS END -->

<!-- JS START -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.9/index.global.min.js'></script>
<!-- JS END -->

<script type="text/javascript">
	$(function() {
		
		$.ajax({
			url			: '/main_header',
			dataType 	: 'text',
			success		: function(data) {
				$('#header').html(data);
			}
		});
		
		$.ajax({
			url			: '/main_menu',
			dataType 	: 'text',
			success		: function(data) {
				$('#menubar').html(data);
			}
		});
	
		$.ajax({
			url			: '/main_footer',
			dataType 	: 'text',
			success		: function(data) {
				$('#footer').html(data);
			}
		});
	});
	
 	// fullcalendar
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');
		
		var events = [];

		// Iterate over the meetings using JSTL				meetingDateList
		<c:forEach var="meeting" items="${meetingDateList}">
			events.push({
				title : '${meeting.meeting_title}',
				start : '${meeting.meeting_date}',
				end : '${meeting.meeting_date}',
				color : '#F2CB61'
			});
		</c:forEach>
		
		var calendar = new FullCalendar.Calendar(calendarEl, {
			initialView : 'dayGridMonth',
			selectable : true,
			events : events,
			
			dateClick : function(info) {
				console.log("clicked date : " + info.dateStr);
			},
			
			headerToolbar: {
                left: 'addEventButton meetingListButton',	// headerToolbar 왼쪽에 커스텀 버튼 추가
                center: 'title'
            },
			
			customButtons: {
				addEventButton: {
					text : '회의일정 등록',
					click : function() {					// 버튼 클릭 시 이벤트 추가
                        $("#calendarModal").modal("show");	// modal 나타내기
					}
				},
				
				meetingListButton: {
					text : '회의록',
					click : function() {
						location.href = 'prj_meeting_report_list?project_id=${project_id}';
					}
				}
			},
			
			eventClick: function(event) {
				console.log(event);
			}
			
		});

		calendar.render();
	});
	
	// form 입력값 체크
	function chk() {

		// meeting_member : 참석자 
		const query = 'input[name="meeting_member"]:checked';			// 	체크된 참석자 	
		const selectedElements = document.querySelectorAll(query);		//	모든 체크된 참석자 

		// 선택된 목록의 갯수 세기
		const selectedElementsCnt = selectedElements.length;			//	체크된 참석자 수
		console.log(selectedElementsCnt);								//	검증.

		if (!frm.meeting_title.value) {									//	제목 없으면 발생.
			alert("제목을 입력하세요.");
			frm.meeting_title.focus();
			return false;
		}

		if (!frm.meeting_date.value) {									//	날짜 지정 안하면 발생.
			alert("일정을 선택하세요.");
			return false;
		}

		if (selectedElementsCnt < 1) {									//	체크된 참석자 없으면 발생.
			alert("참석자를 선택하세요.");
			return false;
		}
	}
</script>
</head>
<body>

<!-- HEADER -->
<header id="header"></header>

<!-- CONTENT -->
<div class="container-fluid">
	<div class="row">
 		
 		<!-- 메뉴 -->
		<div id="menubar" class="menubar border-right col-md-3 col-lg-2 p-0 bg-body-tertiary">
		</div>
		
		<!-- 본문 -->
		<main id="center" class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
			<!------------------------------ //개발자 소스 입력 START ------------------------------->
			<!-- fullcalendar 추가 -->
			<div id='calendar'></div>

			<!-- modal 추가 -->
		    <div class="modal fade" id="calendarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		        aria-hidden="true">
		        <div class="modal-dialog" role="document">
		        	<form action="prj_meeting_date_write" name="frm" onsubmit="return chk()">
			            <div class="modal-content">
			                <div class="modal-header">
			                    <h5 class="modal-title" id="exampleModalLabel">회의 일정 등록</h5>
			                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" data-bs-dismiss="modal">
			                        <span aria-hidden="true">&times;</span>
			                    </button>
			                </div>
			                <div class="modal-body">
			                    <div class="form-group">
			                        <label for="taskId" class="col-form-label">회의 제목</label>
			                        <input type="text" class="form-control" id="meeting_title" name="meeting_title">
			                        <label for="taskId" class="col-form-label">회의 일정</label>
			                        <input type="date" class="form-control" id="meeting_date" name="meeting_date">
			                        <label for="taskId" class="col-form-label">회의 장소</label>
			                        <input type="text" class="form-control" id="meeting_place" name="meeting_place">
			                        <label for="taskId" class="col-form-label">참석자</label><p>
			                        
			                        <c:forEach var="prjMem" items="${prjMemList }">
			                        	<input type="checkbox" id="meeting_member" name="meeting_member" value="${prjMem.user_id }"> ${prjMem.user_name }<br>
			                        </c:forEach><br>
			                        
			                        <label for="taskId" class="col-form-label">회의 유형</label><br>
			                        <select id="meeting_category" name="meeting_category">
			                        	<option value="킥오프미팅">킥오프미팅</option>
			                        	<option value="주간 업무보고">주간 업무보고</option>
			                        	<option value="월간 회의">월간 회의</option>
			                        	<option value="내부 회의">내부 회의</option>
			                        	<option value="회의">회의</option>
			                        </select><br>
			                        
			                        <label for="taskId" class="col-form-label">첨부파일</label>
			                        <input type="file" class="form-control">
			                        <label for="taskId" class="col-form-label">회의 내용</label>
			                        <input type="text" class="form-control" id="meeting_content" name="meeting_content">
			                    </div>
			                </div>
			                <div class="modal-footer">
			                	<input type="submit" class="btn btn-warning" value="추가">
			                    <button type="button" class="btn btn-secondary" data-dismiss="modal"
			                        id="sprintSettingModalClose" data-bs-dismiss="modal">취소</button>
			                </div>
			            </div>
		            </form>
		        </div>
		    </div>

	  		<!------------------------------ //개발자 소스 입력 END ------------------------------->
		</main>		
		
		<div id="right_side" style="width:30%" >
			<div style="background-color: blue">
			1
			</div>
		</div>
	</div>
</div>

<!-- FOOTER -->
<footer class="footer py-2">
  <div id="footer" class="container">
  </div>
</footer>

<!-- color-modes -->
    <svg xmlns="http://www.w3.org/2000/svg" class="d-none">
      <symbol id="check2" viewBox="0 0 16 16">
        <path d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
      </symbol>
      <symbol id="circle-half" viewBox="0 0 16 16">
        <path d="M8 15A7 7 0 1 0 8 1v14zm0 1A8 8 0 1 1 8 0a8 8 0 0 1 0 16z"/>
      </symbol>
      <symbol id="moon-stars-fill" viewBox="0 0 16 16">
        <path d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278z"/>
        <path d="M10.794 3.148a.217.217 0 0 1 .412 0l.387 1.162c.173.518.579.924 1.097 1.097l1.162.387a.217.217 0 0 1 0 .412l-1.162.387a1.734 1.734 0 0 0-1.097 1.097l-.387 1.162a.217.217 0 0 1-.412 0l-.387-1.162A1.734 1.734 0 0 0 9.31 6.593l-1.162-.387a.217.217 0 0 1 0-.412l1.162-.387a1.734 1.734 0 0 0 1.097-1.097l.387-1.162zM13.863.099a.145.145 0 0 1 .274 0l.258.774c.115.346.386.617.732.732l.774.258a.145.145 0 0 1 0 .274l-.774.258a1.156 1.156 0 0 0-.732.732l-.258.774a.145.145 0 0 1-.274 0l-.258-.774a1.156 1.156 0 0 0-.732-.732l-.774-.258a.145.145 0 0 1 0-.274l.774-.258c.346-.115.617-.386.732-.732L13.863.1z"/>
      </symbol>
      <symbol id="sun-fill" viewBox="0 0 16 16">
        <path d="M8 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z"/>
      </symbol>
    </svg>

    <div class="dropdown position-fixed bottom-0 end-0 mb-3 me-3 bd-mode-toggle">
      <button class="btn btn-bd-primary py-2 dropdown-toggle d-flex align-items-center"
              id="bd-theme"
              type="button"
              aria-expanded="false"
              data-bs-toggle="dropdown"
              aria-label="Toggle theme (auto)">
        <svg class="bi my-1 theme-icon-active" width="1em" height="1em"><use href="#circle-half"></use></svg>
        <span class="visually-hidden" id="bd-theme-text">Toggle theme</span>
      </button>
      <ul class="dropdown-menu dropdown-menu-end shadow" aria-labelledby="bd-theme-text">
        <li>
          <button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="light" aria-pressed="false">
            <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em"><use href="#sun-fill"></use></svg>
            Light
            <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
          </button>
        </li>
        <li>
          <button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="dark" aria-pressed="false">
            <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em"><use href="#moon-stars-fill"></use></svg>
            Dark
            <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
          </button>
        </li>
        <li>
          <button type="button" class="dropdown-item d-flex align-items-center active" data-bs-theme-value="auto" aria-pressed="true">
            <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em"><use href="#circle-half"></use></svg>
            Auto
            <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
          </button>
        </li>
      </ul>
    </div>
    
</body>
</html>