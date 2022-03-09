package web.com.spring.board.vo;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardDto {
	
	@Getter
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	@AllArgsConstructor
	public static class Request{
		private String boardTitle;
		private String boardContents;
		private String boardWriter;
		private MultipartFile file;
		private Integer HITCOUNT;
		@Setter
		private String originfilename;
		@Setter
		private String storedfilename;
		@Setter
		private String filepath;
	}
	
	@Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
 	@AllArgsConstructor
	public static class UpdateRequest {
		
		private Integer boardId;
		private String boardTitle;
		private String boardContents;
		private Integer HITCOUNT;
		private MultipartFile  file;
		@Setter
		private String originFileName;
		@Setter
		private String storedFileName;
		@Setter
		private String filePath;
		
	}
	
	
	@Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
 	@AllArgsConstructor
	public static class Result {
		
		private Integer boardId;
		private String boardTitle;
		private String boardContents;
		private String boardWriter;
		@Setter
		private String originfilename;
		@Setter
		private String storedfilename;
		@Setter
		private String filePath;
		private Integer HITCOUNT;
		@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
		private LocalDateTime  regDate;
		@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
		private LocalDateTime  updateDate;
		
	}
}
