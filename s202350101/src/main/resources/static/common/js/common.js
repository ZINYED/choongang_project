/**
 * 
 */

function popup(url, w, h) {
	var pwidth = 800;
	var pheight = 800;
	if(w != null) pwidth = w;
	if(h != null) pheight = h;

	var left = Math.ceil((window.screen.width - pwidth)/2);
	var top  = Math.ceil((window.screen.height - pheight)/2);

	var options = "location=no, directories=no, resizable=yes, status=no, toolbar=no, menubar=no, scrollbars=auto"
	            + ", height="+pheight+", width="+pwidth+", left="+left+", top="+top;

	window.open(url, "_blank", options);
}

function goto(url) {
	location.href = url;
}

//게시판 페이지 이동
function gotoPage(currentPage) {
	//currentPage 경우의 수
	//1.list_mapping_name
	//2.list_mapping_name?currentPage=1
	//3.list_mapping_name?search=s_doc_body&keyword=test
	//4.list_mapping_name?search=s_doc_body&keyword=test&currentPage=1
	//5.list_mapping_name?doc_group=27&doc_group_lsit=y
	//6.list_mapping_name?doc_group=27&doc_group_lsit=y&currentPage=1
	
	var loc = location.href;
	
	if(loc.indexOf("?currentPage=") != -1) { //2
		loc = loc.substring(0, loc.indexOf("?currentPage=")+13);
		loc = loc + currentPage;
	}else if(loc.indexOf("&currentPage=") != -1) { //4, 6
		loc = loc.substring(0, loc.indexOf("&currentPage=")+13);
		loc = loc + currentPage;
	}else{
		if(loc.indexOf("?") != -1){ //3 5  맨뒤에 추가 
			loc = loc + "&currentPage=" + currentPage;
		}else{ //1 파라미터 없으면 첫번째 추가
			loc = loc + "?currentPage=" + currentPage;
		}
	}
	location.href = loc;
}


//d값
//변경전: 2023-11-09T01:44:25.000+00:00
//변경후: 2023-11-09 01:44:25
function formatDateTime(d) {
	d = d.substring(0, d.indexOf('.'));
	d = d.replace('T', ' ');
	return d; 
}

function setCookie(cookieName, cookieValue, days, cookiePath, cookieDomain, cookieSecure){
	var cookieExpire = new Date();
	cookieExpire.setDate(cookieExpire.getDate() + days); // 설정 일수만큼 현재시간에 만료값으로 지정
	var cookieText=escape(cookieName)+'='+escape(cookieValue);
	cookieText+=(cookieExpire ? '; EXPIRES='+cookieExpire.toUTCString() : '');
	cookieText+=(cookiePath ? '; PATH='+cookiePath : '');
	cookieText+=(cookieDomain ? '; DOMAIN='+cookieDomain : '');
	cookieText+=(cookieSecure ? '; SECURE' : '');
	document.cookie=cookieText;
}
 
function getCookie(cookieName){
	var cookieValue=null;
	if(document.cookie){
		var array=document.cookie.split((escape(cookieName)+'='));
		if(array.length >= 2){
			var arraySub=array[1].split(';');
			cookieValue=unescape(arraySub[0]);
 		}
	}
	return cookieValue;
}
 
function deleteCookie(cookieName){
	var temp=getCookie(cookieName);
	if(temp){
		setCookie(cookieName,temp,(new Date(1)));
	}
}


function deleteFlagAttach() {
	$('#idAttachDeleteFlag').val("D");
	$('#idAttachFile').hide();
	$('#idAttachInput').show();
}
