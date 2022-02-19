package web.com.spring.board.vo;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class BoardVO {
	
	private Integer boardId;
	private String boardTitle;
	private String boardWriter;
	private String boardContents;
	private Integer count;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime regDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateDate;
	
//	create table p_bbs(
//	  board_id int auto_increment  not null,
//	  board_title varchar(400) not null,
//	  board_writer varchar(400) not null,
//	  board_contents varchar(2000) not null,
//	  count int,	
//	  regdate datetime default now(),
//	  updatedate datetime default now(),
//	  primary key(board_id)
//	);
	
}
