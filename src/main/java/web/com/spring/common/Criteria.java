package web.com.spring.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Criteria {
	// 특정 페이지 조회를 위한 클래스
	private int page; // 현재 페이지 번호
	private int perPageNum; // 페이지당 보여줄 게시글의 개수
	private String searchType;//검색할때 종류(작성자,내용....)
	private String keyword;//키워드

	public int getPageStart() {
		// 특정 페이지의 범위를 정하는 구간,현재 페이지의 게시글 시작 번호
		// 0~10,10~20이런식으로 보여주기
		return (this.page - 1) * perPageNum;
	}

	// 생성자
	public Criteria() {
		// 페이지 번호랑 보여줄 게시글의 개수 설정하기.
		this.page = 1;
		this.perPageNum = 5;
	}

	// page겟터
	public int getPage() {
		return page;
	}

	// page셋터
	public void setPage(int page) {
		// 페이지 번호가 있다면? 밑에 1부터 표기.
		if (page <= 0) {
			this.page = 1;

		} else {
			this.page = page;
		}
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		int cnt = this.perPageNum;

		if (perPageNum != cnt) {
			this.perPageNum = cnt;
		} else {
			this.perPageNum = perPageNum;
		}
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
}
