package com.oracle.s202350101.dao.ljhDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s202350101.model.Meeting;
import com.oracle.s202350101.model.PrjInfo;

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

}
