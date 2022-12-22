package com.example.demo.entity;

// Board와 Member에 대하여 
// 연관관계를 설정하고
// 게시물목록에서 작성자아이디 누르면
// 그 id가 작성한 모든 게시물을 출력하도록 해 봅니다.
// 완성하면 "3"

import java.util.Date;



import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "board")
public class Board {
	@Id
	 private int no;
	
	 private String title;

	 @ManyToOne
	 @JoinColumn(name = "id", insertable = true, updatable = true)
	 private Member member;
	 
	 private String pwd;
	 private String content;
	 private Date regdate;
	 private int hit;
	 private String ip;
	 
	 private int b_ref;
	 private int b_step;
	 private int b_level;
	 
	 @Transient
	 private MultipartFile uploadFile;
	 private String fname;
}










