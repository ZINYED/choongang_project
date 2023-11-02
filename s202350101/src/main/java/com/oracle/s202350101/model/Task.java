package com.oracle.s202350101.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Date 
@Getter
@Setter
@ToString
public class Task {
		private int		task_id;
		private int		project_id;
		private int		project_step_seq;
		private String	user_id;
		private String	task_subject;
		private String	task_content;
		private Date	task_start_time;
		private Date	task_end_time;
		private String	task_priority;
		private String	task_status;
		private int		garbage;
		
		// 조회용
		private int		status_count;
		private String	user_name;
		private String	project_s_name;
}
