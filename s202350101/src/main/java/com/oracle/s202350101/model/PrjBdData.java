package com.oracle.s202350101.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Date 
@Getter
@Setter
@ToString
public class PrjBdData {
	private int 	doc_no;				private int 	project_id;
	private int 	app_id;				private String 	user_id;
	private Date 	create_date;		private Date 	modify_date;
	private String 	subject;			private String 	notify_flag;
	private String 	bd_category;		private String 	doc_body;
	private String 	attach_name;		private String 	attach_path;
	private int 	bd_count;			private int 	good_count;
	private int 	doc_group;			private int 	doc_step;
	private int 	doc_indent;			private String 	alarm_flag;
	private String 	parent_doc_user_id;	private String 	parent_doc_no;
	
	// 조회용 추가
	private String	app_name;
	private int		qna_doc_no;					// BD_QNA 테이블 doc_no 
	private String	qna_user_id;				// BD_QNA 테이블 user_id
	private int		qna_parent_doc_no;			// BD_QNA 테이블 parent_doc_no
	private String	qna_parent_doc_user_id;		// BD_QNA 테이블 parent_doc_user_id
	private String	qna_alarm_flag;				// BD_QNA 테이블 alarm_flag
	
}
