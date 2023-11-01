package com.oracle.s202350101.dao.ljhDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s202350101.model.Meeting;
import com.oracle.s202350101.model.PrjInfo;
import com.oracle.s202350101.model.PrjMemList;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LjhDaoImpl implements LjhDao {

	private final SqlSession session;
	
	@Override
	public PrjInfo getProject(int project_id) {
		PrjInfo prjInfo = new PrjInfo();
		System.out.println("LjhDaoImpl getProject Start");
		
		try {
			prjInfo = session.selectOne("ljhPrjInfoSelOne", project_id);
			System.out.println("LjhDaoImpl getProject prjInfo.getProject_name() : " + prjInfo.getProject_name());
			System.out.println("LjhDaoImpl getProject prjInfo.getProject_startdate() : " + prjInfo.getProject_startdate());
			System.out.println("LjhDaoImpl getProject prjInfo.getProject_enddate() : " + prjInfo.getProject_enddate());
		} catch (Exception e) {
			System.out.println("LjhDaoImpl getProject Exception : " + e.getMessage());
		}
		
		return prjInfo;
	}

	@Override
	public List<Meeting> getMeetingDate(int project_id) {
		List<Meeting> meetingDateList = null;
		System.out.println("LjhDaoImpl getMeeting Start");
		
		try {
			meetingDateList = session.selectList("ljhMeetingDateList", project_id);
			System.out.println("LjhDaoImpl getMeeting meetingDateList.size() :" + meetingDateList.size());
		} catch (Exception e) {
			System.out.println("LjhDaoImpl getMeeting Exception : " + e.getMessage());
		}
		
		return meetingDateList;
	}

	@Override
	public List<Meeting> getMeetingRead(int meeting_id) {
		List<Meeting> meetingRead = null;
		System.out.println("LjhDaoImpl getMeetingRead Start");
		
		try {
			meetingRead = session.selectList("ljhMeetingRead", meeting_id);
			System.out.println("LjhDaoImpl getMeetingRead meetingRead.size() -> " + meetingRead.size());
		} catch (Exception e) {
			System.out.println("LjhDaoImpl getMeetingRead Exception : " + e.getMessage());
		}
		
		return meetingRead;
	}

	@Override
	public List<Meeting> getMeetingList(int project_id) {
		List<Meeting> meetingList = null;
		System.out.println("LjhDaoImpl getMeetingList Start");
		
		try {
			meetingList = session.selectList("ljhMeetingList", project_id);
			System.out.println("LjhDaoImpl getMeetingList meetingList.size() -> " + meetingList.size());
		} catch (Exception e) {
			System.out.println("LjhDaoImpl getMeetingList Exception : " + e.getMessage());
		}
		
		return meetingList;
	}

	@Override
	public List<PrjMemList> getPrjMember(int project_id) {
		List<PrjMemList> prjMemList = null;
		System.out.println("LjhDaoImpl getPrjMember Start");
		
		try {
			prjMemList = session.selectList("ljhPrjMemList", project_id);
			System.out.println("LjhDaoImpl getPrjMember prjMemList.size() -> " + prjMemList.size());
		} catch (Exception e) {
			System.out.println("LjhDaoImpl getPrjMember Exception : " + e.getMessage());
		}
		
		return prjMemList;
	}

	@Override
	public int insertMeetingDate(Meeting meeting) {
		int insertResult = 0;
		System.out.println("LjhDaoImpl insertMeetingDate Start");
		
		try {
			insertResult = session.insert("ljhMeetingInsert", meeting);
			System.out.println("LjhDaoImpl insertMeetingDate insertResult -> " + insertResult);
		} catch (Exception e) {
			System.out.println("LjhDaoImpl insertMeetingDate Exception : " + e.getMessage());
		}
		
		return insertResult;
	}

	@Override
	public int updateMeetingReport(Meeting meeting) {
		int updateResult = 0;
		System.out.println("LjhDaoImpl updateMeetingReport Start");
		
		try {
			updateResult = session.update("ljhMeetingReportUpdate", meeting);
			System.out.println("LjhDaoImpl updateMeetingReport updateResult -> " + updateResult);
		} catch (Exception e) {
			System.out.println("LjhDaoImpl updateMeetingReport Exception : " + e.getMessage());
		}
		
		return updateResult;
	}

	@Override
	public int deleteMeetingMember(Meeting meeting) {
		int deleteResult = 0;
		System.out.println("LjhDaoImpl deleteMeetingMember Start");
		
		try {
			deleteResult = session.delete("ljhDeleteMeetingMember", meeting);
			System.out.println("LjhDaoImpl deleteMeetingMember deleteResult -> " + deleteResult);
		} catch (Exception e) {
			System.out.println("LjhDaoImpl deleteMeetingMember Exception : " + e.getMessage());
		}
		
		return deleteResult;
	}

	@Override
	public int insertMeetingMember(Meeting meeting) {
		int insertResult = 0;
		System.out.println("LjhDaoImpl insertMeetingMember Start");
		
		try {
			insertResult = session.insert("ljhInsertMeetingMember", meeting);
			System.out.println("LjhDaoImpl insertMeetingMember insertResult -> " + insertResult);
		} catch (Exception e) {
			System.out.println("LjhDaoImpl insertMeetingMember Exception : " + e.getMessage());
		}
		
		return insertResult;
	}

}
