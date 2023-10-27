package com.oracle.s202350101.service.ljhSer;

import org.springframework.stereotype.Service;

import com.oracle.s202350101.dao.ljhDao.LjhDao;
import com.oracle.s202350101.model.PrjInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LjhServiceImpl implements LjhService {
	
	private final LjhDao	ljhd;

	@Override
	public PrjInfo getProject(int project_id) {
		System.out.println("LjhServiceImpl getProject Start");
		
		PrjInfo prjInfo = ljhd.getProject(project_id);
		
		return prjInfo;
	}

}
