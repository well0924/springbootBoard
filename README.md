# springbootBoard

1.목적

이제까지 스프링을 배웠는데 배운것을 기억하기 위해서 복습 겸 해서 게시판을 만들어 보고자 한다.

1-1.사용기술 
- Java11
- SpringBoot
- Mybatis
- MariaDB
- SpringSecurity
- BootStrap
- JQuery
- thyemleaf

2.기능

- [x] 기본적인 crud
- [x] 페이징 및 검색 기능
- [x] 댓글 기능(삭제,수정)
- [x] 파일첨부기능
- [x] 로그인 기능
- [x] 회원가입 기능
- [x] 선택삭제
- [x] 회원 수정 및 삭제

3.작업 현황

- [x] crud
- [x] 페이징 및 검색 기능
- [x] 댓글 기능
- [x] 파일첨부기능(삭제,수정)
- [x] 선택삭제
- [x] 회원가입기능
- [x] 로그인

4.미완성 기능

- 로그인기능(시큐리티 기능이 제대로 작동이 안됨).
- 댓글 수정기능(스크립트에서 출력이 되지 않음)

5.테이블 
 <pre>
  <code>
  CREATE TABLE p_bbs(
	board_id INT AUTO_INCREMENT NOT NULL,
	board_title VARCHAR(400) NOT NULL,
	board_writer VARCHAR(400) NOT NULL,
	board_contents VARCHAR(2000) NOT NULL,
	HITCOUNT INT,
	reg_date DATE TIME DEFAULT NOW(),
	update_date DATE TIME DEFAULT NOW(),
	originfilename VARCHAR(200),
	storedfilename VARCHAR(200),
	filepath VARCHAR(200),
	PRIMARY KEY(board_id)
);
  </code>
 </pre>
 
 <pre>
 <code>
 CREATE TABLE re_bbs(
	rno INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	bno INT(11) NOT NULL,
	rno_writer VARCHAR(200) NOT NULL,
	rno_contents VARCHAR(400) NOT NULL,
	rno_date DATE TIME DEFAULT NOW(),
	rno_update DATE TIME DEFAULT NOW()
);
alter table re_bbs add constraint re_bbs_ibfk_1 foreign key(bno) references p_bbs(board_id);
 </code>
 </pre>
 
 <pre>
 <code>
 create table p_user(
 	userid varchar(100) not null primary key,
 	userpw varchar(200) not null,
 	username varchar(200) not null,
 	userphone varchar(200) not null,
 	useremail varchar(300) not null,
 	useraddr1 varchar(200) not null,
 	useraddr2 varchar(200) not null,
 	useraddr3 varchar(200) not null,
 	userlevel int(10) default '1',
 	userdate datetime default now(),
 	userupdate datetime default now()
);
 </code>
 </pre>
 
 <pre>
 <code>
 create table p_role(
 	roleid int not null,
 	rolename varchar(100) not null,
 	roledesc varchar(100) default '',
 	primary key(roleid)
);
 </code>
 </pre>
6.구현 화면

7.어려웠던 점

- 글 입력시 name에 vo클래스에 있는 변수명과 일치가 되어야 데이터가 전송이 된다는 점
- 비동기처리시에 데이터 값 가져오는것이 아직도 조금은 미숙했다. 다음번에는 Json.stringify보다는 form.serialize()로 해보기.
- 제이쿼리가 아직까지는 좀 미숙함./공식 메뉴엘을 참고해서 봐야겠다.
- 댓글 기능중에 댓글 수정버튼을 누르면 화면이 출력이 되지 않는다는점(스크립트단에서 작동이 안되는 이유를 모르겠다)
- 파일첨부처리에서 컨트롤러작성이 어려웠음./서비스단에 로직을 세우는 점이 조금은 힘들었다.
- 시큐리티 기능 좀더 파기.(로그인 부분)

8.공부해야될 점

- 제이쿼리에서 비동기 부분 외에도 공식 메뉴얼을 참고해서 공부를 해야겠다.
- 스프링 시큐리티공부(전반적으로 어떻게 흘러가는 것을 알고 작성을 했는데 컨트롤러에 로그를 찍은 결과 데이터 값은 나왔지만 설정부분에 문제가 있는것 같다.)
- 데이터 베이스공부를 좀더 해야겠다.(지금은 간단한 테이블을 가지고 만들었지만 좀더 많은 테이블과 어려운 관계를 이해하고 쿼리를 짜는 연습을 해야 될것 같다.)
- 다양한 라이브러리 사용해 보기(다음번에는 엑셀로 출력을 하는 apache poi, cdk edite,지도api)
- 시간이 되면 상기 프로젝트에 살을 계속 붙여볼 생각이다.
