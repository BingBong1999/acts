
DROP SEQUENCE id_seq;

CREATE SEQUENCE id_seq
	INCREMENT BY 1
	START WITH 1;

DROP TABLE PostReview CASCADE CONSTRAINTS PURGE;

DROP TABLE SearchKeyword CASCADE CONSTRAINTS PURGE;

DROP TABLE Favorite CASCADE CONSTRAINTS PURGE;

DROP TABLE Transaction CASCADE CONSTRAINTS PURGE;

DROP TABLE Post CASCADE CONSTRAINTS PURGE;

DROP TABLE Category CASCADE CONSTRAINTS PURGE;

DROP TABLE Message CASCADE CONSTRAINTS PURGE;

DROP TABLE MessageRoom CASCADE CONSTRAINTS PURGE;

DROP TABLE Account CASCADE CONSTRAINTS PURGE;

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

CREATE UNIQUE INDEX XPKAccount ON Account
(userId   ASC);

ALTER TABLE Account
	ADD CONSTRAINT  XPKAccount PRIMARY KEY (userId);

CREATE TABLE SearchKeyword
(
	createdTime          DATE DEFAULT  SYSDATE  NOT NULL ,
	keyword              VARCHAR2(200) NOT NULL ,
	userId               INT NOT NULL 
);

CREATE UNIQUE INDEX XPKSearchKeyword ON SearchKeyword
(userId   ASC);

ALTER TABLE SearchKeyword
	ADD CONSTRAINT  XPKSearchKeyword PRIMARY KEY (userId);

CREATE TABLE Category
(
	categoryId           INT NOT NULL ,
	categoryName         VARCHAR2(100) NOT NULL 
);

CREATE UNIQUE INDEX XPKCategory ON Category
(categoryId   ASC);

ALTER TABLE Category
	ADD CONSTRAINT  XPKCategory PRIMARY KEY (categoryId);

CREATE TABLE MessageRoom
(
	roomId               INT NOT NULL ,
	senderId             INT NOT NULL ,
	receiverId           INT NOT NULL 
);

CREATE UNIQUE INDEX XPKMessageRoom ON MessageRoom
(roomId   ASC);

ALTER TABLE MessageRoom
	ADD CONSTRAINT  XPKMessageRoom PRIMARY KEY (roomId);

CREATE TABLE Message
(
	messageId            INT NOT NULL ,
	messageContent       VARCHAR2(500) NOT NULL ,
	createdTime          DATE DEFAULT  SYSDATE  NOT NULL ,
	roomId               INT NOT NULL 
);

CREATE UNIQUE INDEX XPKMessage ON Message
(messageId   ASC);

ALTER TABLE Message
	ADD CONSTRAINT  XPKMessage PRIMARY KEY (messageId);

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

CREATE UNIQUE INDEX XPKPost ON Post
(postId   ASC);

ALTER TABLE Post
	ADD CONSTRAINT  XPKPost PRIMARY KEY (postId);

CREATE TABLE PostReview
(
	reviewId             INT NOT NULL ,
	createdTime          DATE DEFAULT  SYSDATE  NOT NULL ,
	reviewContent        VARCHAR2(800) NOT NULL ,
	score                INT NOT NULL  CONSTRAINT  score CHECK (score BETWEEN 1 AND 5),
	reviewerId           INT NOT NULL ,
	postId               INT NOT NULL 
);

CREATE UNIQUE INDEX XPKPostReview ON PostReview
(reviewId   ASC);

ALTER TABLE PostReview
	ADD CONSTRAINT  XPKPostReview PRIMARY KEY (reviewId);

CREATE TABLE Favorite
(
	favorId              INT NOT NULL ,
	userId               INT NOT NULL ,
	postId               INT NOT NULL 
);

CREATE UNIQUE INDEX XPKFavorite ON Favorite
(favorId   ASC);

ALTER TABLE Favorite
	ADD CONSTRAINT  XPKFavorite PRIMARY KEY (favorId);

CREATE TABLE Transaction
(
	transId              INT NOT NULL ,
	transDate            DATE DEFAULT  SYSDATE  NOT NULL ,
	userId               INT NOT NULL ,
	postId               INT NOT NULL 
);

CREATE UNIQUE INDEX XPKTransaction ON Transaction
(transId   ASC);

ALTER TABLE Transaction
	ADD CONSTRAINT  XPKTransaction PRIMARY KEY (transId);

ALTER TABLE SearchKeyword
	ADD (CONSTRAINT 검색 FOREIGN KEY (userId) REFERENCES Account (userId));

ALTER TABLE MessageRoom
	ADD (CONSTRAINT R_129 FOREIGN KEY (senderId) REFERENCES Account (userId));

ALTER TABLE MessageRoom
	ADD (CONSTRAINT R_130 FOREIGN KEY (receiverId) REFERENCES Account (userId));

ALTER TABLE Message
	ADD (CONSTRAINT R_131 FOREIGN KEY (roomId) REFERENCES MessageRoom (roomId));

ALTER TABLE Post
	ADD (CONSTRAINT R_123 FOREIGN KEY (writerId) REFERENCES Account (userId));

ALTER TABLE Post
	ADD (CONSTRAINT R_126 FOREIGN KEY (categoryId) REFERENCES Category (categoryId));

ALTER TABLE PostReview
	ADD (CONSTRAINT 후기_작성 FOREIGN KEY (reviewerId) REFERENCES Account (userId));

ALTER TABLE PostReview
	ADD (CONSTRAINT R_127 FOREIGN KEY (postId) REFERENCES Post (postId));

ALTER TABLE Favorite
	ADD (CONSTRAINT 관심글_설정 FOREIGN KEY (userId) REFERENCES Account (userId) ON DELETE SET NULL);

ALTER TABLE Favorite
	ADD (CONSTRAINT R_124 FOREIGN KEY (postId) REFERENCES Post (postId));

ALTER TABLE Transaction
	ADD (CONSTRAINT 거래_요청 FOREIGN KEY (userId) REFERENCES Account (userId));

ALTER TABLE Transaction
	ADD (CONSTRAINT R_125 FOREIGN KEY (postId) REFERENCES Post (postId));
