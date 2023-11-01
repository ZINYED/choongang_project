package com.oracle.s202350101.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.s202350101.model.Meeting;
import com.oracle.s202350101.model.MeetingMember;
import com.oracle.s202350101.model.PrjInfo;
import com.oracle.s202350101.model.PrjMemList;
import com.oracle.s202350101.service.ljhSer.LjhService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LjhController {
	
	private final LjhService ljhs;
	
	// 프로젝트 캘린더
	@RequestMapping(value = "prj_calendar")
	public String prjCalendar(int project_id, Model model) {
		System.out.println("LjhController prjCalendar");
		
		PrjInfo prjInfo = new PrjInfo();
		prjInfo = ljhs.getProject(project_id);
		
		List<Meeting> meetingDateList = new ArrayList<Meeting>();
		meetingDateList = ljhs.getMeetingDate(project_id);
		
		model.addAttribute("prj", prjInfo);
		model.addAttribute("meetingDateList", meetingDateList);

		System.out.println(meetingDateList.get(0).getMeeting_status());
		
		return "project/prj_calendar";
	}
	
	// 회의록 캘린더
	@RequestMapping(value = "prj_meeting_calendar")
	public String meetingCalendar(int project_id, Model model) {
		System.out.println("LjhController meetingCalendar");
		
		List<PrjMemList> prjMemList = new ArrayList<PrjMemList>();
		prjMemList = ljhs.getPrjMember(project_id);
		
		List<Meeting> meetingDateList = new ArrayList<Meeting>();
		meetingDateList = ljhs.getMeetingDate(project_id);
		
		model.addAttribute("prjMemList", prjMemList);
		model.addAttribute("meetingDateList", meetingDateList);
		model.addAttribute("project_id", project_id);
		
		return "project/meeting/prj_meeting_calendar";
	}
	
	// 회의록 목록
	@RequestMapping(value = "prj_meeting_report_list")
	public String meetingList(int project_id, Model model) {
		System.out.println("LjhController meetingList");
		
		List<Meeting> meetingList = new ArrayList<Meeting>();
		meetingList = ljhs.getMeetingList(project_id);
		
		model.addAttribute("meetingList", meetingList);
		model.addAttribute("project_id", project_id);
		
		return "project/meeting/prj_meeting_report_list";
	}
	
	// 회의록 조회
	@RequestMapping(value = "prj_meeting_report_read")
	public String meetingRead(int meeting_id, int project_id, Model model) {
		System.out.println("LjhController meetingRead");
		
		List<Meeting> meetingRead = new ArrayList<Meeting>();
		meetingRead = ljhs.getMeetingRead(meeting_id);
		
		model.addAttribute("meeting", meetingRead);
		model.addAttribute("project_id", project_id);
		model.addAttribute("meeting_id", meeting_id);
		
		return "project/meeting/prj_meeting_report_read";
	}
	
	// 회의록 수정
	@RequestMapping(value = "prj_meeting_report_update")
	public String meetingUpdate(int meeting_id, int project_id, Model model) {
		System.out.println("LjhController meetingUpdate");
		 
		List<Meeting> meetingRead = new ArrayList<Meeting>();		//미팅멤버
		meetingRead = ljhs.getMeetingRead(meeting_id);
		
		List<PrjMemList> prjMemList = new ArrayList<PrjMemList>();		//	프로젝트전체멤버
		prjMemList = ljhs.getPrjMember(project_id);
		
		model.addAttribute("meeting", meetingRead);
		model.addAttribute("prjMemList", prjMemList);
		model.addAttribute("meeting_id", meeting_id);
		model.addAttribute("project_id", project_id);
		
		return "project/meeting/prj_meeting_report_update";
	}
	
	// 회의록 수정 2
	@Transactional
	@PostMapping(value = "prj_meeting_report_update_2")
	public String meetingReportUpdate(Meeting meeting, Model model, HttpServletRequest request, @RequestParam(value = "file1", required = false)MultipartFile file1) throws IOException {
		System.out.println("LjhController meetingReportUpdate");
		
		// 첨부파일 업로드
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");		// 저장 위치 주소 지정
		
		System.out.println("File Upload Post Start");
		
		log.info("originalName : " + file1.getOriginalFilename());		// 원본 파일명
		log.info("size : " + file1.getSize());							// 파일 사이즈
		log.info("contextType : " + file1.getContentType());			// 파일 타입
		log.info("uploadPath : " + uploadPath);							// 파일 저장되는 주소
		
		String savedName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);	// 저장되는 파일명
		log.info("Return savedName : " + savedName);
		meeting.setAttach_name(savedName);
		meeting.setAttach_path(uploadPath);
		
		log.info("MDMDMDMDM:   {}",meeting.getMeeting_date());
		
		
		int updateResult = ljhs.updateMeetingReport(meeting);		// 회의록 수정
		int deleteResult = ljhs.deleteMeetingMember(meeting);		// 회의 기존 참석자 삭제
		
		/*
		 * int insertResult = 0; String[] meetMems = meeting.getUser_id().split(","); //
		 * 체크된 참석자 배열로 저장 for (int i = 0; i<meetMems.length; i++) { Meeting mt = new
		 * Meeting(); mt.setUser_id(meetMems[i]);
		 * mt.setMeeting_id(meeting.getMeeting_id());
		 * mt.setProject_id(meeting.getProject_id());
		 * 
		 * insertResult += ljhs.insertMeetingMember(mt); // 회의 참석자 신규 등록 }
		 */
		
		System.out.println("LjhController meetingReportUpdate updateResult : " + updateResult);
		System.out.println("LjhController meetingReportUpdate deleteResult : " + deleteResult);
		// System.out.println("LjhController meetingReportUpdate insertResult : " + insertResult);
		
		if (updateResult > 0) return "redirect:prj_meeting_calendar";
		else {
			return "forward:prj_meeting_report_update";
		}
	}
	
	// 회의일정 등록
	@RequestMapping(value = "prj_meeting_date_write", method = RequestMethod.POST)
	public String prjMeetingDateWrite(Meeting meeting, Model model, HttpServletRequest request, MultipartFile file1) throws IOException {
		System.out.println("LjhController prjMeetingDateWrite Start");

		// 첨부파일 업로드
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");		// 저장 위치 주소 지정
		
		System.out.println("File Upload Post Start");
		
		log.info("originalName : " + file1.getOriginalFilename());		// 원본 파일명
		log.info("size : " + file1.getSize());							// 파일 사이즈
		log.info("contextType : " + file1.getContentType());			// 파일 타입
		log.info("uploadPath : " + uploadPath);							// 파일 저장되는 주소
		
		String savedName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);	// 저장되는 파일명
		log.info("Return savedName : " + savedName);
		
		
		// 회의일정 등록
		int insertResult = ljhs.insertMeetingDate(meeting);
		
		if (insertResult > 0) return "redirect:prj_meeting_calendar";
		else {
			return "forward:prj_meeting_date_write";
		}
	}

	private String uploadFile(String originalFilename, byte[] bytes, String uploadPath) throws IOException {
		// Universally Unique Identity (UUID)
		UUID uid = UUID.randomUUID();
		System.out.println("uploadPath : " + uploadPath);			// 파일 저장되는 주소

		// Directory 생성
		File fileDirectory = new File(uploadPath);
		if (!fileDirectory.exists()) {
			// 신규 폴더(Directory) 생성
			fileDirectory.mkdirs();
			System.out.println("업로드용 폴더 생성 : " + uploadPath);
		}
		
		String savedName = uid.toString() + "_" + originalFilename;	// 저장되는 파일명
		log.info("savedName : " + savedName);
		File target = new File(uploadPath, savedName);

		FileCopyUtils.copy(bytes, target);	// org.springframework.util.FileCopyUtils
		
		return savedName;
	}
	
	@RequestMapping(value = "prj_meeting_report_delete")
	public String prjMeetingReportDelete(int project_id, int meeting_id) {
		
		
		return null;
	}
	
	
}