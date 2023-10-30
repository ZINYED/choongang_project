package com.oracle.s202350101.dao.ljhDao;

import java.util.List;

import com.oracle.s202350101.model.Meeting;
import com.oracle.s202350101.model.PrjInfo;

public interface LjhDao {

	PrjInfo 		getProject(int project_id);
	List<Meeting> 	getMeetingDate(int project_id);
	List<Meeting> 	getMeetingRead(int meeting_id);
	List<Meeting> 	getMeetingList(int project_id);

}
