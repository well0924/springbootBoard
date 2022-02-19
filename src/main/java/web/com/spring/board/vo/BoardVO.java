package web.com.spring.board.vo;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class BoardVO {
	
	private Integer boardId;
	private String boardTitle;
	private String boardWriter;
	private String boardContents;
	@JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
	private LocalDateTime regDate;
	@JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
	private LocalDateTime updateDate;
	
//	create table p_bbs(
//	  board_id int auto_increment  not null,
//	  board_title varchar(400) not null,
//	  board_writer varchar(400) not null,
//	  board_contents varchar(2000) not null,
//	  regdate datetime default now(),
//	  updatedate datetime default now(),
//	  primary key(board_id)
//	);
	
}
