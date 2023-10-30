package com.oracle.s202350101.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageService {
	private int currentPage = 1;
	private int pageBlock   = 10;
	private int start;
	private int startPage;
	private int total;
	
	private int row = 10;
	private int end;
	private int endPage;
	private int totalPage;
	
	public PageService (int total, String currentPage1) {
		this.total = total;
		
		if (currentPage1 != null) {
			this.currentPage = Integer.parseInt(currentPage1);
		}
		
		// currentPage1 == null
		start = (currentPage - 1) * row + 1;
		end   = start + row - 1;
		
		totalPage = (int)Math.ceil((double)total/row);
		
		startPage = currentPage - (currentPage - 1) % pageBlock;
		endPage   = startPage + pageBlock - 1;
		
		// 공갈페이지 방지 
		if (endPage > totalPage) {
			endPage = totalPage;
		}
	}
	

}
