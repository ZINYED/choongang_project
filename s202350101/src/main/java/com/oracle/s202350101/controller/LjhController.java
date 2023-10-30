package com.oracle.s202350101.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.s202350101.model.Meeting;
import com.oracle.s202350101.model.PrjInfo;
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

			System.out.println(meetingDateList);
			
			return "project/prj_calendar";
		}
	
	// 회의록 캘린더
	@RequestMapping(value = "prj_meeting_calendar")
	public String meetingCalendar() {
		System.out.println("LjhController meetingCalendar");
		log.info("call");
		
		
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
	
	@RequestMapping(value = "prj_meeting_report_update")
	public String meetingUpdate(int meeting_id, int project_id, Model model) {
		System.out.println("LjhController meetingUpdate");
		 
		List<Meeting> meetingRead = new ArrayList<Meeting>();
		meetingRead = ljhs.getMeetingRead(meeting_id);
		
		model.addAttribute("meeting", meetingRead);
		model.addAttribute("meeting_id", meeting_id);
		model.addAttribute("project_id", project_id);
		
		return "project/meeting/prj_meeting_report_update";
	}
	
	@RequestMapping(value = "prj_meeting_report_update_2")
	public String meetingReportUpdate(int meeting_id, int project_id, Model model) {
		System.out.println("LjhController meetingReportUpdate");
		
		
		return "project/meeting/prj_meeting_report_read";
	}
	
	
	
}