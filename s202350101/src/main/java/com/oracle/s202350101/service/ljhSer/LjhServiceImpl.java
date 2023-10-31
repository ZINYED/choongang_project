package com.oracle.s202350101.service.ljhSer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.s202350101.dao.ljhDao.LjhDao;
import com.oracle.s202350101.model.Meeting;
import com.oracle.s202350101.model.PrjInfo;
import com.oracle.s202350101.model.PrjMemList;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LjhServiceImpl implements LjhService {
	
	private final LjhDao	ljhd;
	
	// 프로젝트 기간 조회
	@Override
	public PrjInfo getProject(int project_id) {
		System.out.println("LjhServiceImpl getProject Start");
		
		PrjInfo prjInfo = ljhd.getProject(project_id);
		
		return prjInfo;
	}

	// 회의 일정 조회 (meeting_status = 1 or 3)
	@Override
	public List<Meeting> getMeetingDate(int project_id) {
		System.out.println("LjhServiceImpl getMeeting Start");

		List<Meeting> meetingDateList = ljhd.getMeetingDate(project_id);
		
		return meetingDateList;
	}
	
	// 회의록 상세
	@Override
	public List<Meeting> getMeetingRead(int meeting_id) {
		System.out.println("LjhServiceImpl getMeetingRead Start");
		
		List<Meeting> meetingRead = ljhd.getMeetingRead(meeting_id);
		
		return meetingRead;
	}

	// 회의록 리스트 (meeting_status = 2 or 3)
	@Override
	public List<Meeting> getMeetingList(int project_id) {
		System.out.println("LjhServiceImpl getMeetingRead Start");
		
		List<Meeting> meetingList = ljhd.getMeetingList(project_id);
		
		return meetingList;
	}

	@Override
	public List<PrjMemList> getPrjMember(int project_id) {
		System.out.println("LjhServiceImpl getPrjMember Start");
		
		List<PrjMemList> prjMemList = ljhd.getPrjMember(project_id);
		
		return prjMemList;
	}

	@Override
	public int insertMeetingDate(Meeting meeting) {
		System.out.println("LjhServiceImpl insertMeetingDate Start");
		int insertResult = 0;
		
		insertResult = ljhd.insertMeetingDate(meeting);
		
		return insertResult;
	}

	@Override
	public int updateMeetingReport(Meeting meeting) {
		System.out.println("LjhServiceImpl updateMeetingReport Start");
		
		int updateResult = 0;
		
		updateResult = ljhd.updateMeetingReport(meeting);
		
		return updateResult;
	}

}
