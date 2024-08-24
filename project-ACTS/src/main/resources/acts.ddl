
/* 시퀀스 삭제 */
DROP SEQUENCE USER_ID_SEQ;

CREATE SEQUENCE USER_ID_SEQ
	INCREMENT BY 1
	START WITH 1;
	
DROP SEQUENCE POST_ID_SEQ;

CREATE SEQUENCE POST_ID_SEQ
	INCREMENT BY 1
	START WITH 1;

/* 테이블 삭제 */
DROP TABLE PostReview CASCADE CONSTRAINTS PURGE;

DROP TABLE SearchKeyword CASCADE CONSTRAINTS PURGE;

DROP TABLE Favorite CASCADE CONSTRAINTS PURGE;

DROP TABLE Transaction CASCADE CONSTRAINTS PURGE;

DROP TABLE Post CASCADE CONSTRAINTS PURGE;

DROP TABLE Category CASCADE CONSTRAINTS PURGE;

DROP TABLE Message CASCADE CONSTRAINTS PURGE;

DROP TABLE MessageRoom CASCADE CONSTRAINTS PURGE;

DROP TABLE Account CASCADE CONSTRAINTS PURGE;





/* 사용자 계정 테이블 생성 */
CREATE TABLE Account
(
	userId               INT NOT NULL ,
	phoneNumber          VARCHAR2(15) NOT NULL ,
	emailAddress         VARCHAR2(100) NOT NULL ,
	userName             VARCHAR2(20) NOT NULL ,
	registrationNumber   VARCHAR2(14) NOT NULL ,
	password             VARCHAR2(30) NOT NULL ,
	joinDate             DATE DEFAULT  SYSDATE  NOT NULL ,
	accountId            VARCHAR2(30) NOT NULL ,
	nickName             VARCHAR2(32) NOT NULL 
);

CREATE UNIQUE INDEX IDXAccount ON Account (userId ASC);

ALTER TABLE Account ADD CONSTRAINT XPKAccount PRIMARY KEY (userId);





/* 검색 키워드 테이블 생성 */
CREATE TABLE SearchKeyword
(
	createdTime          DATE DEFAULT  SYSDATE  NOT NULL ,
	keyword              VARCHAR2(200) NOT NULL ,
	userId               INT NOT NULL 
);

CREATE UNIQUE INDEX IDXSearchKeyword ON SearchKeyword (userId ASC);

ALTER TABLE SearchKeyword ADD CONSTRAINT XPKSearchKeyword PRIMARY KEY (userId);





/* 카테고리 테이블 생성 */
CREATE TABLE Category
(
	categoryId           INT NOT NULL ,
	categoryName         VARCHAR2(100) NOT NULL 
);

CREATE UNIQUE INDEX IDXCategory ON Category (categoryId ASC);

ALTER TABLE Category ADD CONSTRAINT XPKCategory PRIMARY KEY (categoryId);

INSERT INTO Category (categoryId, categoryName) VALUES (1, '디자인');

INSERT INTO Category (categoryId, categoryName) VALUES (2, 'IT');

INSERT INTO Category (categoryId, categoryName) VALUES (3, '문서');

INSERT INTO Category (categoryId, categoryName) VALUES (4, '기타');





/* 메세지 룸 테이블 생성 */
CREATE TABLE MessageRoom
(
	roomId               INT NOT NULL ,
	senderId             INT NOT NULL ,
	receiverId           INT NOT NULL 
);

CREATE UNIQUE INDEX IDXMessageRoom ON MessageRoom (roomId ASC);

ALTER TABLE MessageRoom ADD CONSTRAINT XPKMessageRoom PRIMARY KEY (roomId);





/* 메세지 테이블 생성 */
CREATE TABLE Message
(
	messageId            INT NOT NULL ,
	messageContent       VARCHAR2(500) NOT NULL ,
	createdTime          DATE DEFAULT  SYSDATE  NOT NULL ,
	roomId               INT NOT NULL 
);

CREATE UNIQUE INDEX IDXMessage ON Message (messageId ASC);

ALTER TABLE Message
	ADD CONSTRAINT  XPKMessage PRIMARY KEY (messageId);

	
	
	

/* 게시글 테이블 생성 */
CREATE TABLE Post
(
	postId               INT NOT NULL ,
	title                VARCHAR2(50) NOT NULL ,
	description          VARCHAR2(2000) NOT NULL ,
	imageUrl             VARCHAR2(500) NULL ,
	createdTime          DATE DEFAULT  SYSDATE  NOT NULL ,
	views                INT DEFAULT  0  NOT NULL ,
	status               VARCHAR2(10) NOT NULL  CONSTRAINT  status_1697923632 CHECK (status IN ('available', 'ongoing', 'completed')),
	price                INT NOT NULL  CONSTRAINT  post_price CHECK (price >= 0),
	postType             CHAR(1) NOT NULL  CONSTRAINT  post_type CHECK (postType IN ('s', 'b')),
	writerId             INT NOT NULL ,
	categoryId           INT NOT NULL 
);

CREATE UNIQUE INDEX IDXPost ON Post (postId ASC);

ALTER TABLE Post ADD CONSTRAINT XPKPost PRIMARY KEY (postId);

	
	
	
	
/* 게시글 리뷰 테이블 생성 */
CREATE TABLE PostReview
(
	reviewId             INT NOT NULL ,
	createdTime          DATE DEFAULT  SYSDATE  NOT NULL ,
	reviewContent        VARCHAR2(800) NOT NULL ,
	score                INT NOT NULL  CONSTRAINT  score CHECK (score BETWEEN 1 AND 5),
	reviewerId           INT NOT NULL ,
	postId               INT NOT NULL 
);

CREATE UNIQUE INDEX IDXPostReview ON PostReview (reviewId ASC);

ALTER TABLE PostReview ADD CONSTRAINT  XPKPostReview PRIMARY KEY (reviewId);

	
	
	
	
/* 좋아요 테이블 생성 */
CREATE TABLE Favorite
(
	favorId              INT NOT NULL ,
	userId               INT NOT NULL ,
	postId               INT NOT NULL 
);

CREATE UNIQUE INDEX IDXFavorite ON Favorite (favorId ASC);

ALTER TABLE Favorite ADD CONSTRAINT XPKFavorite PRIMARY KEY (favorId);

	
	
	
	
/* 거래 테이블 생성 */
CREATE TABLE Transaction
(
	transId              INT NOT NULL ,
	transDate            DATE DEFAULT  SYSDATE  NOT NULL ,
	userId               INT NOT NULL ,
	postId               INT NOT NULL 
);

CREATE UNIQUE INDEX IDXTransaction ON Transaction (transId ASC);

ALTER TABLE Transaction ADD CONSTRAINT XPKTransaction PRIMARY KEY (transId);





/* 외래 키 제약 생성 */
ALTER TABLE SearchKeyword
	ADD (CONSTRAINT FK_USERID_SearchKeyword FOREIGN KEY (userId) REFERENCES Account (userId));

ALTER TABLE MessageRoom
	ADD (CONSTRAINT FK_SENDERID_MessageRoom FOREIGN KEY (senderId) REFERENCES Account (userId));

ALTER TABLE MessageRoom
	ADD (CONSTRAINT FK_RECEIVERID_MessageRoom FOREIGN KEY (receiverId) REFERENCES Account (userId));

ALTER TABLE Message
	ADD (CONSTRAINT FK_ROOMID_Message FOREIGN KEY (roomId) REFERENCES MessageRoom (roomId));

ALTER TABLE Post
	ADD (CONSTRAINT FK_WRITERID_Post FOREIGN KEY (writerId) REFERENCES Account (userId));

ALTER TABLE Post
	ADD (CONSTRAINT FK_CATEGORYID_Post FOREIGN KEY (categoryId) REFERENCES Category (categoryId));

ALTER TABLE PostReview
	ADD (CONSTRAINT FK_REVIEWERID_PostReview FOREIGN KEY (reviewerId) REFERENCES Account (userId));

ALTER TABLE PostReview
	ADD (CONSTRAINT FK_POSTID_PostReview FOREIGN KEY (postId) REFERENCES Post (postId));

ALTER TABLE Favorite
	ADD (CONSTRAINT FK_USERID_Favorite FOREIGN KEY (userId) REFERENCES Account (userId) ON DELETE SET NULL);

ALTER TABLE Favorite
	ADD (CONSTRAINT FK_POSTID_Favorite FOREIGN KEY (postId) REFERENCES Post (postId));

ALTER TABLE Transaction
	ADD (CONSTRAINT FK_USERID_Transaction FOREIGN KEY (userId) REFERENCES Account (userId));

ALTER TABLE Transaction
	ADD (CONSTRAINT FK_POSTID_Transaction FOREIGN KEY (postId) REFERENCES Post (postId));
