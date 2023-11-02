package com.oracle.s202350101.service.ljhSer;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.oracle.s202350101.dao.ljhDao.LjhDao;
import com.oracle.s202350101.model.Meeting;
import com.oracle.s202350101.model.PrjInfo;
import com.oracle.s202350101.model.PrjMemList;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LjhServiceImpl implements LjhService {

	private final PlatformTransactionManager transactionManager;
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

	// 1
	@Override
	public int updateMeetingReport(Meeting meeting) {
		System.out.println("LjhServiceImpl updateMeetingReport Start");
		
		int updateResult = 0;
		
		updateResult = ljhd.updateMeetingReport(meeting);
		
		return updateResult;
	}

	// 2
	@Override
	public int deleteMeetingMember(Meeting meeting) {
		System.out.println("LjhServiceImpl deleteMeetingMember Start");
		
		int deleteResult = 0;
		
		deleteResult = ljhd.deleteMeetingMember(meeting);
		
		return deleteResult;
	}

	// 3
	@Override
	public int insertMeetingMember(Meeting meeting) {
		System.out.println("LjhServiceImpl insertMeetingMember Start");
		
		int insertResult = 0;
		
		insertResult = ljhd.insertMeetingMember(meeting);
		
		return insertResult;
	}

	@Override
	public int deleteMeetingReport(int meeting_id) {
		System.out.println("LjhServiceImpl deleteMeetingReport Start");
		
		int deleteResult = 0;
		
		deleteResult = ljhd.deleteMeetingReport(meeting_id);
		
		return deleteResult;
	}
	
	public int meetingReportUpdate(Meeting meeting) {
		
		String[] meetMems = meeting.getUser_id().split(",");	// 체크된 참석자 배열로 저장 

		int insertResult = 0;
		int updateResult = 0;
		int deleteResult = 0;

		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition()); 
		
		try {

			updateResult = ljhd.updateMeetingReport(meeting);
			deleteResult = ljhd.deleteMeetingMember(meeting);
			for (int i = 0; i<meetMems.length; i++) {
				Meeting mt = new Meeting(); 
				mt.setMeetuser_id(meetMems[i]);
				mt.setMeeting_id(meeting.getMeeting_id());
				mt.setProject_id(meeting.getProject_id());
				
				insertResult += ljhd.insertMeetingMember(mt);		// 회의 참석자 신규 등록
			}

			transactionManager.commit(txStatus);
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			System.out.println("LjhServiceImpl meetingReportUpdate Exception -> " + e.getMessage());
			
		}
		
		int totalResult = updateResult+deleteResult+insertResult;
		
		return totalResult;
		
	}
	
	
	

}
