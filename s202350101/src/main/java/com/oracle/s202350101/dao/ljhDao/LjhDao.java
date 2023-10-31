package com.oracle.s202350101.dao.ljhDao;

import java.util.List;

import com.oracle.s202350101.model.Meeting;
import com.oracle.s202350101.model.PrjInfo;
import com.oracle.s202350101.model.PrjMemList;

public interface LjhDao {

	PrjInfo 			getProject(int project_id);
	List<Meeting> 		getMeetingDate(int project_id);
	List<Meeting> 		getMeetingRead(int meeting_id);
	List<Meeting> 		getMeetingList(int project_id);
	List<PrjMemList> 	getPrjMember(int project_id);
	int 				insertMeetingDate(Meeting meeting);
	int 				updateMeetingReport(Meeting meeting);

}
