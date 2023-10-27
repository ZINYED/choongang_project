package com.oracle.s202350101.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
			
			DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date prjStartDate = prjInfo.getProject_startdate();
			Date prjEndDate = prjInfo.getProject_enddate();
			String prjStart = sdFormat.format(prjStartDate);
			String prjEnd = sdFormat.format(prjEndDate);
			
			model.addAttribute("prj", prjInfo);
			model.addAttribute("prjStart", prjStart);
			model.addAttribute("prjEnd", prjEnd);
			
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
	public String meetingList() {
		System.out.println("LjhController meetingList");
		
		return "project/meeting/prj_meeting_report_list";
	}
	
	
	
	
}