package com.oracle.s202350101.dao.ljhDao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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

}
