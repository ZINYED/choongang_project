<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style type="text/css">
	#notify {
	    position: absolute;
	    width: 300px;
	    height: 400px;
	    background-color: skyblue;
	    right: 200;
	    z-index: 999;
	}
</style>

<script type="text/javascript">
	// stomp 사용	
	let stompClient;
	
	
	$(document).ready(function () {
	    onSocket();
	});
	
	// 소켓 연결	
	function onSocket() {
	    let socket = new SockJS('/websocket');		// websocket = NotifyConfig에서 지정한 엔드포인트
	    console.log("1");
	    stompClient = Stomp.over(socket);
	    console.log("2");
	    
        const obj = {
			// userInfo : '${userInfo}'
        	project_id : '${userInfo.project_id}',
        	user_id : '${userInfo.user_id}'
		};
        
	    stompClient.connect({}, function (frame) {
			console.log('Connected: ' + frame);

	        console.log("3");
	        console.log(obj);

	        stompClient.subscribe("/noti/test", function(data){
	        	console.log("hi");
	        	console.log(data);

                var rtndata = JSON.parse(data.body);

                // 현재 날짜
                const date = new Date();
                const year = date.getFullYear();
                const month = ('0' + (date.getMonth() + 1)).slice(-2);
                const day = ('0' + (date.getDate())).slice(-2);
                let now = year + "-" + month + "-" + day;

                console.log("now date: " + now);

                let str = '';

                // 현재 날짜와 meeting_date가 일치하면 화면에 출력
                for (var i = 0; i < rtndata.length; i++) {
                    const meetingDate = rtndata[i].meeting_date;
                    console.log(meetingDate);

                    if (meetingDate == now) {
                        str += '<p>' + rtndata[i].meeting_date + ' ' + rtndata[i].meeting_title + '</p>';
                    }
                }
                let notify = $('#notify');
                notify.append(str);
	        });

	     	// userInfo 같이 보내기
	        stompClient.send('/queue/post', {}, JSON.stringify(obj));
	        console.log("4");
	        
	    });
	};
	
	// 알림버튼 클릭 시 작동
	function notifyClick() {
		
		var con = document.getElementById("notify");
		
		if (con.style.display == 'none') {
			con.style.display = 'block';
		} else {
			con.style.display = 'none';
		}
		
	};
</script>
    
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="/main">PMS</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav me-auto mb-2 mb-md-0">
			<!-- <li class="nav-item">
	            <a class="nav-link active" aria-current="page" href="#">Home</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link" href="#">Link</a>
	          </li>
	          <li class="nav-item">
	            <a class="nav-link disabled" aria-disabled="true">Disabled</a>
	          </li> -->
	        </ul>
			<ul class="nav nav-pills">
				<li class="nav-item">
					<a class="nav-link px-2 link-light" aria-current="page" href="#">${userInfo.user_name }</a>
				</li>
			</ul>
			<div class="dropdown text-end">
				<a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
					<img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle">
				</a>
				<ul class="dropdown-menu text-small" style="">
					<li><a class="dropdown-item" href="#">채팅창</a></li>
					<li><a class="dropdown-item" href="mypage_main">내 정보 설정</a></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item" href="user_logout">로그아웃</a></li>
				</ul>
			</div>
			<div>
				<button type="button" onclick="notifyClick()">알림</button>
			</div>
			<div class="d-flex" role="search" style="margin-left:10px">        
				<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-secondary" type="submit">Search</button>
			</div>
		</div>
	</div>
</nav>

<div id="notify" style="display: none;">
	<div id="notifyBox">
	
	</div>
</div>

</head>
</html>