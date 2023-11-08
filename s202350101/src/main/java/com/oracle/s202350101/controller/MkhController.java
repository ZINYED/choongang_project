package com.oracle.s202350101.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.s202350101.model.BdFree;
import com.oracle.s202350101.model.BdQna;
import com.oracle.s202350101.model.ClassRoom;
import com.oracle.s202350101.model.PrjBdData;
import com.oracle.s202350101.model.PrjBdRep;
import com.oracle.s202350101.model.UserInfo;
import com.oracle.s202350101.service.mkhser.MkhService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MkhController {
	
	private final MkhService mkhService;
	
	/* 로그인  */
	// 로그인 화면 (Validation 걸어줘야함)
	@RequestMapping(value = "user_login")
	public String userLogin() {
		System.out.println("MkhController userLogin Start..");
	
		return "user/user_login";
	}
	
////	 단순 로그인 체크
//	@RequestMapping(value = "user_login_check")
//	public String userLoginCheck(UserInfo userInfoDTO, HttpSession session) {
//		System.out.println("MkhController userLoginCheck Start..");
//		System.out.println("userInfo.getUser_id()->"+userInfoDTO.getUser_id());
//		System.out.println("userInfo.getUser_pw()->"+userInfoDTO.getUser_pw());
//		
//		UserInfo userInfo = mkhService.userLoginCheck(userInfoDTO);
//		if(userInfo != null) {
//			session.setAttribute("userInfo", userInfo);
//			return "main";	// 로그인 성공시 메인화면
//		} else {
//			return "redirect:/user_login"; // 로그인 실패시 로그인 화면
//		}
//	}
	
	// 로그인 인터셉터(origianl)
	// 2번째 실행
//	@RequestMapping(value = "user_login_check", method = RequestMethod.POST)
//	public String interCeptor(UserInfo userInfoDTO, Model model) {
//		System.out.println("MkhController userLoginCheck Start..");
//		System.out.println("userInfo.getUser_id()->"+userInfoDTO.getUser_id());
//		System.out.println("userInfo.getUser_pw()->"+userInfoDTO.getUser_pw());
//		
//		UserInfo userInfo = mkhService.userLoginCheck(userInfoDTO);
//		
//		model.addAttribute("userInfo", userInfo);
////		model.addAttribute("urlGo", "main");   미완성
//
//		System.out.println("user_login_check End");
//		// 형식적으로 만들어줌
//		return "main";
//	}
	
//	// 로그인 인터셉터(second)
//	// 2번째 실행
//	@RequestMapping(value = "user_login_check", method = RequestMethod.POST)
//	public String interCeptor(UserInfo userInfoDTO, Model model, HttpServletRequest request) {
//		System.out.println("MkhController userLoginCheck Start..");
//		System.out.println("userInfo.getUser_id()->"+userInfoDTO.getUser_id());
//		System.out.println("userInfo.getUser_pw()->"+userInfoDTO.getUser_pw());
//		
//		UserInfo userInfo = mkhService.userLoginCheck(userInfoDTO);
//		// 뷰로 보내줌
//		if(userInfo == null) {
//			return "user_login";
//		}
//		// 기존 세션 폐기
//		request.getSession().invalidate();
//		// 세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
//		HttpSession session = request.getSession(true);
//		session.setAttribute("userId", userInfo.getUser_id());
//		// 세션 30분동안 유지
//		session.setMaxInactiveInterval(1800);
//		
//		System.out.println("user_login_check End");
//		// 형식적으로 만들어줌
//		return "main";
//	}
	
//	 로그인 인터셉터(third)
//		 2번째 실행
	@RequestMapping(value = "user_login_check", method = RequestMethod.POST)
	public String interCeptor(HttpServletRequest  request, UserInfo userInfoDTO, 
							  				Model model,   HttpSession session) {
		System.out.println("MkhController userLoginCheck Start..");
		System.out.println("userInfo.getUser_id()->"+userInfoDTO.getUser_id());
		System.out.println("userInfo.getUser_pw()->"+userInfoDTO.getUser_pw());
		
		// 검증
		UserInfo userInfo = mkhService.userLoginCheck(userInfoDTO);
		
		if(userInfo != null) {
			System.out.println("userInfo exists");
			session.setAttribute("userInfo", userInfo);
			System.out.println("session.getAttribute(userInfo)->"+session.getAttribute("userInfo"));
			return "redirect:/main";	// redirect의 의미
		} else {
			System.out.println("userInfo is not exist");
			return "redirect:/user_login";
		}
	}
	
	// 로그아웃
	@RequestMapping(value = "user_logout")
	public String userLogout(HttpSession session) {
		// 세션 정보 삭제
		session.invalidate();
		return "redirect:/user_login";
	}
	
	/* 회원가입 */
	// 회원가입 동의 페이지로 이동
	@RequestMapping(value = "user_join_agree")
	public String userJoinAgree() {
		System.out.println("MkhController userJoinAgree Start..");
		return "user/user_join_agree";
	}
	
	// 회원가입 페이지로 이동
	@RequestMapping(value = "user_join_write")
	public String userJoinWrite(Model model) {
		System.out.println("MkhController userJoinWrite Start..");
		// class_id GET
		List<ClassRoom> classList = mkhService.createdClass();
		System.out.println("MkhController user_join_write classList.size->"+classList.size());
		model.addAttribute("classList", classList);
		
		return "user/user_join_write";
	}
	
//	 회원가입 정보 insert
	@PostMapping(value = "writeUserInfo")
	public String writeUserInfo(UserInfo userInfo, Model model) {
		System.out.println("MkhController writeUserInfo Start...");
	
		int result = mkhService.insertUserInfo(userInfo);
		if(result > 0) return "user/user_login";
		else return "redirect:user_join_write";
	}
	
	
	/* 마이페이지 */
	// 마이페이지 수정으로 이동
	@RequestMapping(value = "mypage_main")
	public String mypageMain(HttpServletRequest request) {
		System.out.println("MkhController mypageMain Start..");
		System.out.println("session.userInfo->"+request.getSession().getAttribute("userInfo"));

		return "mypage/mypage_main";
	}
	
	// 개인정보 수정용 비밀번호 확인 페이지
	@RequestMapping(value = "mypage_check_pw")
	public String mypageCheckPw(HttpServletRequest request) {
		System.out.println("MkhController mypageCheckPw Start..");
		System.out.println("session.userInfo->"+request.getSession().getAttribute("userInfo"));

		return "mypage/mypage_check_pw";
	}
	
	// 개인정보 수정 페이지 인터셉터 검증
	@RequestMapping(value = "mypage_update")
	public String mypageUpdate(HttpServletRequest request, Model model) {
		System.out.println("2.MkhController mypageUpdate Start..");
		System.out.println("session.userInfo->"+request.getSession().getAttribute("userInfo"));

	//	이미 loginCheck할 때 세션이 올바르면 userInfo 객체가 잡혀있음
		model.addAttribute("urlGo", "mypage_update_view");
		return "mypage/mypage_update";
	}
	
	// 개인정보 수정 페이지로 이동
	@RequestMapping(value = "mypage_update_view")
	public String mypage_update_view(HttpServletRequest request,Model model) {
		System.out.println("2.MkhController mypage_update_view Start..");

		return "mypage/mypage_update";
	}
	
	/* MYPOST */
	
	// 내가 쓴 게시글
	@RequestMapping(value = "mypost_board_list")
	public String mypostBoardList(HttpServletRequest request,  BdQna bdQna, 
								  			UserInfo userInfo, Model model) {
		System.out.println("MkhController mypostBoardList Start..");
		System.out.println("userinfo.getUser_id()->"+userInfo.getUser_id());
		
		// userInfo가 있으면 보여주고 아니면 권한없음처리
		
		// 내가 쓴 게시글 Count
		// Q&A 게시판 Count
		int totalBdQna = mkhService.totalQna(userInfo);
		System.out.println("totalBdQnaCount->"+totalBdQna);
		model.addAttribute("totalBdQna", totalBdQna);
		
		// 공용게시판 Count
//		int totalBdFree = mkhService.totalFree();
//		System.out.println("totalBdQnaCount->"+totalBdFree);
//		
//		// 내가 쓴 게시글 Count
//		int totalBdPrj = mkhService.totalPrj();
//		System.out.println("totalBdQnaCount->"+totalBdPrj);
//		
//		int totalBdQna = mkhService.totalQna();
//		System.out.println("totalBdQnaCount->"+totalBdQna);

		/* 내가 쓴 게시글 출력 */
		// Q&A 게시판
		List<BdQna> qnaList = mkhService.bdQnaList(userInfo);
		System.out.println("MkhController mypostBoardList qnaList.size->"+qnaList.size());
		model.addAttribute("qnaList", qnaList);
		// 공용 게시판
		List<BdFree> freeList = mkhService.bdFreeList(userInfo);
		System.out.println("MkhController mypostBoardList freeList.size->"+freeList.size());
		model.addAttribute("freeList", freeList);
		// 프로젝트 & 공지자료 게시판
		List<PrjBdData> dataPrjList = mkhService.PrjDataList(userInfo);
		System.out.println("MkhController mypostBoardList prjList.size->"+dataPrjList.size());
		model.addAttribute("dataPrjList", dataPrjList);
		// 업무보고 게시판
		List<PrjBdRep> RepPrjList = mkhService.PrjRepList(userInfo);
		System.out.println("MkhController mypostBoardList RepPrjList.size->"+RepPrjList.size());
		model.addAttribute("RepPrjList", RepPrjList);
		
		/* 내가 추천한 게시글 출력 */
		
		/* 내가 쓴 댓글 출력 */

		
		return "mypost/mypost_board_list";
	}
	
	// 내가 쓴 댓글
	@RequestMapping(value = "mypost_comment_list")
	public String mypostCommentList(HttpServletRequest request) {
		System.out.println("MkhController mypostCommentList Start..");
		return "mypost/mypost_comment_list";
	}
	
	// 내가 추천한 게시글
	@RequestMapping(value = "mypost_good_list")
	public String mypostGoodList(HttpServletRequest request) {
		System.out.println("MkhController mypostGoodList Start..");
		return "mypost/mypost_good_list";
	}

}
