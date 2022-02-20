package web.com.spring.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class pageinfo {
	private int totalCount; // 게시판 전체 데이터 개수
	private int displayPageNum = 5; // 게시판 화면에서 한번에 보여질 페이지 번호의 개수

	private int startPage; // 화면의 시작 번호
	private int endPage; // 화면의 끝 번호
	private boolean prev; // 페이징 이전 버튼 활성화 여부
	private boolean next; // 페이징 다음 버튼 활성화 여부

	// 검색을 위해 추가
	private String searchType;
	private String keyword;

	// Criteria클래스 사용
	private Criteria cri;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;

		pagingData();
	}

	// 중요...(코드로 배우는 스프링 프로젝트 참고하기)
	private void pagingData() {

		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		// endPage = (현재 페이지 번호 / 화면에 보여질 페이지 번호의 개수) * 화면에 보여질 페이지 번호의 개수
		startPage = (endPage - displayPageNum) + 1;
		// startPage = (끝 페이지 번호 - 화면에 보여질 페이지 번호의 개수) + 1

		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		// 마지막 페이지 번호 = 총 게시글 수 / 한 페이지당 보여줄 게시글의개수

		prev = startPage == 1 ? false : true;
		// 이전 버튼 생성 여부 = 시작 페이지 번호가 1과 같으면 false, 아니면 true
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
		// 다음 버튼 생성 여부 = 끝 페이지 번호 * 한 페이지당 보여줄 게시글의 개수가 총 게시글의 수보다
		// 크거나 같으면 false, 아니면 true
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
}
