# springbootBoard

1.목적

이제까지 스프링을 배웠는데 배운것을 기억하기 위해서 복습 겸 해서 게시판을 만들어 보고자 한다.

1-1.기간:2022.02.19~2022.02.24

1-2.사용기술 
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

4.테이블 
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
