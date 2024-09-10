
/* 시퀀스 삭제 */
DROP SEQUENCE POST_ID_SEQ;
DROP SEQUENCE MESSAGE_ID_SEQ;
DROP SEQUENCE USER_ACTION_ID_SEQ;
DROP SEQUENCE FAVORITE_ID_SEQ;





/* 시퀀스 생성 */
CREATE SEQUENCE POST_ID_SEQ
	INCREMENT BY 1
	START WITH 1
	NOCACHE;
CREATE SEQUENCE MESSAGE_ID_SEQ
	INCREMENT BY 1
	START WITH 26
	NOCACHE;
CREATE SEQUENCE USER_ACTION_ID_SEQ
	INCREMENT BY 1
	START WITH 26
	NOCACHE;
CREATE SEQUENCE FAVORITE_ID_SEQ
	INCREMENT BY 1
	START WITH 29
	NOCACHE;



/* 테이블 삭제 */
DROP TABLE PostReview CASCADE CONSTRAINTS PURGE;

DROP TABLE SearchKeyword CASCADE CONSTRAINTS PURGE;

DROP TABLE Favorite CASCADE CONSTRAINTS PURGE;

DROP TABLE Transaction CASCADE CONSTRAINTS PURGE;

DROP TABLE POST CASCADE CONSTRAINTS PURGE;

DROP TABLE MESSAGE CASCADE CONSTRAINTS PURGE;

DROP TABLE ACCOUNT CASCADE CONSTRAINTS PURGE;

DROP TABLE IMAGE CASCADE CONSTRAINTS PURGE;

DROP TABLE USER_ACTION CASCADE CONSTRAINTS PURGE;





/* 사용자 계정 테이블 생성 */
CREATE TABLE ACCOUNT
(
	ID               VARCHAR2(50) NOT NULL ,
	PASSWORD          VARCHAR2(100) NOT NULL ,
	EMAIL         VARCHAR2(100) NOT NULL
);

CREATE UNIQUE INDEX IDXACCOUNT ON ACCOUNT (ID ASC);

ALTER TABLE ACCOUNT ADD CONSTRAINT XPKACCOUNT PRIMARY KEY (ID);





/* 검색 키워드 테이블 생성 */
CREATE TABLE SearchKeyword
(
	createdTime          DATE DEFAULT  SYSDATE  NOT NULL ,
	keyword              VARCHAR2(200) NOT NULL ,
	userId               VARCHAR2(50) NOT NULL 
);

CREATE UNIQUE INDEX IDXSearchKeyword ON SearchKeyword (userId ASC);

ALTER TABLE SearchKeyword ADD CONSTRAINT XPKSearchKeyword PRIMARY KEY (userId);





/* 메세지 테이블 생성 */
CREATE TABLE MESSAGE 
(
    ID 								INT PRIMARY KEY,
    SENDER_ID 						VARCHAR2(50),
    RECEIVER_ID 					VARCHAR2(50),
    CONTENT 						CLOB NOT NULL,
    CREATED_AT 						TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (SENDER_ID) 		REFERENCES ACCOUNT(ID),  
    FOREIGN KEY (RECEIVER_ID) 		REFERENCES ACCOUNT(ID)
);


	
		

/* 게시글 테이블 생성 */
CREATE TABLE POST
(
	ID               		INT NOT NULL ,
	TITLE                	VARCHAR2(50) NOT NULL ,
	BODY          			VARCHAR2(2000) NOT NULL ,
	CREATED_AT          	DATE DEFAULT  SYSDATE  NOT NULL ,
	CATEGORY_ID           	INT NOT NULL ,
	VIEW_COUNT             	INT DEFAULT  0  NOT NULL ,
	STATUS               	VARCHAR2(10) NOT NULL  CONSTRAINT  status_1697923632 CHECK (status IN ('available', 'ongoing', 'completed')),
	PRICE                	INT NOT NULL  CONSTRAINT  POST_PRICE CHECK (PRICE >= 0),
	AUTHOR_ID             	VARCHAR2(50) NOT NULL
	
);

CREATE UNIQUE INDEX IDXPOST ON POST (ID ASC);

ALTER TABLE POST ADD CONSTRAINT XPKPOST PRIMARY KEY (ID);

	
	
	
	
/* 게시글 리뷰 테이블 생성 */
CREATE TABLE PostReview
(
	reviewId             INT NOT NULL ,
	createdTime          DATE DEFAULT  SYSDATE  NOT NULL ,
	reviewContent        VARCHAR2(800) NOT NULL ,
	score                INT NOT NULL  CONSTRAINT  score CHECK (score BETWEEN 1 AND 5),
	reviewerId           VARCHAR2(50) NOT NULL ,
	postId               INT NOT NULL 
);

CREATE UNIQUE INDEX IDXPostReview ON PostReview (reviewId ASC);

ALTER TABLE PostReview ADD CONSTRAINT  XPKPostReview PRIMARY KEY (reviewId);

	
	
	
	
/* 좋아요 테이블 생성 */
CREATE TABLE Favorite
(
	favorId              INT NOT NULL ,
	userId               VARCHAR2(50) NOT NULL ,
	postId               INT NOT NULL 
);

CREATE UNIQUE INDEX IDXFavorite ON Favorite (favorId ASC);

ALTER TABLE Favorite ADD CONSTRAINT XPKFavorite PRIMARY KEY (favorId);

	
	
	
	
/* 거래 테이블 생성 */
CREATE TABLE Transaction
(
	transId              INT NOT NULL ,
	transDate            DATE DEFAULT  SYSDATE  NOT NULL ,
	userId               VARCHAR2(50) NOT NULL ,
	postId               INT NOT NULL 
);

CREATE UNIQUE INDEX IDXTransaction ON Transaction (transId ASC);

ALTER TABLE Transaction ADD CONSTRAINT XPKTransaction PRIMARY KEY (transId);




/* 이미지 테이블 생성 */
CREATE TABLE IMAGE 
(
    ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,  
    POST_ID INT NOT NULL,                     
    IMAGE_URL VARCHAR2(255) NOT NULL,          
    CONSTRAINT FK_POST_ID FOREIGN KEY (POST_ID) REFERENCES POST(ID) ON DELETE CASCADE
);





-- 사용자 행동 테이블
CREATE TABLE USER_ACTION (
    ID 					NUMBER PRIMARY KEY,
    USER_ID 			VARCHAR2(50),
    POST_ID 			NUMBER,
    ACTION_TYPE 		VARCHAR2(50) CONSTRAINT USER_ACTION_ACTION_TYPE CHECK (ACTION_TYPE IN ('view_count', 'view_duration', 'liked')),
    ACTION_TIMESTAMP 	TIMESTAMP,
    DURATION 			NUMBER,
    CONSTRAINT FK_USER_ACTION_USER_ID FOREIGN KEY (USER_ID) REFERENCES ACCOUNT(ID) ON DELETE CASCADE,
    CONSTRAINT FK_USER_ACTION_POST_ID  FOREIGN KEY (POST_ID) REFERENCES POST(ID) ON DELETE CASCADE
);





/* 외래 키 제약 생성 */
ALTER TABLE SearchKeyword
	ADD (CONSTRAINT FK_USERID_SearchKeyword FOREIGN KEY (userId) REFERENCES ACCOUNT (ID));

ALTER TABLE POST
	ADD (CONSTRAINT FK_WRITERID_Post FOREIGN KEY (AUTHOR_ID) REFERENCES ACCOUNT (ID));

ALTER TABLE PostReview
	ADD (CONSTRAINT FK_REVIEWERID_PostReview FOREIGN KEY (reviewerId) REFERENCES ACCOUNT (ID));

ALTER TABLE PostReview
	ADD (CONSTRAINT FK_POSTID_PostReview FOREIGN KEY (postId) REFERENCES POST (ID));

ALTER TABLE Favorite
	ADD (CONSTRAINT FK_USERID_Favorite FOREIGN KEY (userId) REFERENCES ACCOUNT (ID) ON DELETE SET NULL);

ALTER TABLE Favorite
	ADD (CONSTRAINT FK_POSTID_Favorite FOREIGN KEY (postId) REFERENCES POST (ID));

ALTER TABLE Transaction
	ADD (CONSTRAINT FK_USERID_Transaction FOREIGN KEY (userId) REFERENCES ACCOUNT (ID));

ALTER TABLE Transaction
	ADD (CONSTRAINT FK_POSTID_Transaction FOREIGN KEY (postId) REFERENCES POST (ID));
	
	
/* 샘플 데이터 생성 */
	
INSERT INTO ACCOUNT (ID, PASSWORD, EMAIL) VALUES ('johnDoe', 'password123', 'john.doe@example.com');
INSERT INTO ACCOUNT (ID, PASSWORD, EMAIL) VALUES ('janeSmith', 'password456', 'jane.smith@example.com');
INSERT INTO ACCOUNT (ID, PASSWORD, EMAIL) VALUES ('mikeJones', 'password789', 'mike.jones@example.com');
INSERT INTO ACCOUNT (ID, PASSWORD, EMAIL) VALUES ('emilyClark', 'password101', 'emily.clark@example.com');
INSERT INTO ACCOUNT (ID, PASSWORD, EMAIL) VALUES ('davidBrown', 'password202', 'david.brown@example.com');
INSERT INTO ACCOUNT (ID, PASSWORD, EMAIL) VALUES ('kyungwon', '$2a$10$5wwPGrYPQxQi4pImMxv1beCrrEzOv/O6RTgKjBYgUHVuXlVuUgUkO', 'kyungwon@example.com');

	

Insert into C##KIMKYUNGWON.POST (ID,TITLE,BODY,CREATED_AT,CATEGORY_ID,VIEW_COUNT,STATUS,PRICE,AUTHOR_ID) values (14,
'맞춤형 수채화 작품 그리기','맞춤형 수채화 작품 그리기 서비스를 소개합니다!
<br>
<br>당신만의 감성을 담은 맞춤형 수채화, 이제 간편하게 주문하세요!
<br>
<br>특별한 날을 기념하거나 소중한 사람에게 마음을 전하고 싶을 때, 맞춤형 수채화는 그 자체로 감동을 선사하는 선물입니다. 사진 속 소중한 순간, 반려동물, 혹은 머릿속에만 그려두었던 아름다운 풍경을 수채화 작품으로 제작해 드립니다.
<br>
<br>- 개인 맞춤형 제작: 고객님의 요청에 맞춰 특별한 순간을 그대로 담아냅니다. 원하는 색감, 스타일, 크기 등을 세세하게 반영하여 세상에 하나뿐인 작품을 만들어 드립니다.
<br>
<br>- 전문가 수준의 퀄리티: 실력 있는 작가들이 정성을 다해 작업합니다. 고퀄리티의 수채화로 일상의 특별함을 표현합니다.
<br>
<br>- 편리한 주문 과정: 간단한 주문서 작성으로 원하는 작품의 스타일과 디테일을 지정하세요. 작품이 완성되면, 빠르고 안전하게 배송해 드립니다.
<br>
<br>- 맞춤형 선물 포장: 요청 시 선물 포장 서비스를 제공하여, 받는 분의 기쁨을 배가시킬 수 있습니다.
<br>지금 주문하시고 세상에 하나뿐인 수채화 작품을 만나보세요. 당신의 이야기가 물감으로 펼쳐지는 특별한 경험을 선사합니다!',
to_date('24/08/30','RR/MM/DD'),1,16,'available',50000,'johnDoe');


Insert into C##KIMKYUNGWON.POST (ID,TITLE,BODY,CREATED_AT,CATEGORY_ID,VIEW_COUNT,STATUS,PRICE,AUTHOR_ID) values (15,
'벽화 디자인','공간에 특별한 매력을 더해줄 벽화 디자인 서비스를 제공합니다. 
<br>
<br>집 안 거실이나 아이들 방, 카페, 레스토랑, 사무실 등 다양한 장소에 어울리는 맞춤형 벽화를 그려드립니다. 
<br>
<br>고객의 요청에 맞춰 특정 테마나 분위기, 색상을 반영하여 공간에 생동감과 개성을 불어넣습니다. 벽
<br>
<br>화는 단순한 그림을 넘어 공간의 이야기를 담는 예술 작품입니다.
<br>
<br>가격: 150,000원',
to_date('24/08/30','RR/MM/DD'),4,7,'available',150000,'janeSmith');


Insert into C##KIMKYUNGWON.POST (ID,TITLE,BODY,CREATED_AT,CATEGORY_ID,VIEW_COUNT,STATUS,PRICE,AUTHOR_ID) values (18,
'테마 음악 작곡','당신의 이야기를 음악으로 표현해드립니다. 
<br>
<br>결혼식의 감동을 더하는 웨딩송, 개인적인 프로젝트에 생기를 불어넣는 배경음악, 또는 브랜드를 대표할 수 있는 테마 음악 등 다양한 요구에 맞춰 작곡해 드립니다. 
<br>
<br>전문 작곡가가 세심하게 상담하여 고객님의 감성과 메시지를 음악에 담아냅니다. 특별한 순간에 어울리는 음악으로 그날을 더욱 빛나게 만들어 보세요.
<br>
<br>가격: 120,000원',
to_date('24/08/30','RR/MM/DD'),2,0,'available',120000,'janeSmith');


Insert into C##KIMKYUNGWON.POST (ID,TITLE,BODY,CREATED_AT,CATEGORY_ID,VIEW_COUNT,STATUS,PRICE,AUTHOR_ID) values (19,
'감동을 전하는 편지 대필 서비스','마음은 가득하지만 글로 표현하기 어려운 당신을 위해 편지를 대신 작성해드립니다. 
<br>
<br>사랑하는 연인에게 전할 로맨틱한 편지, 오랜 친구에게 보내는 감사의 메시지, 가족에게 전하는 진심 가득한 편지 등 다양한 상황에 맞춰 감동적인 글을 작성합니다. 
<br>
<br>고객님의 이야기를 듣고 진심을 담아, 받는 사람의 마음을 따뜻하게 해줄 편지를 만들어 드립니다.
<br>
<br>가격: 30,000원',to_date('24/08/30','RR/MM/DD'),3,2,'available',30000,'mikeJones');
Insert into C##KIMKYUNGWON.POST (ID,TITLE,BODY,CREATED_AT,CATEGORY_ID,VIEW_COUNT,STATUS,PRICE,AUTHOR_ID) values (20,'개인 브랜딩을 위한 로고 디자인','브랜드의 첫인상을 결정하는 로고 디자인, 이제 전문가에게 맡겨보세요. 
<br>
<br>스타트업, 개인 사업, 프리랜서 등 당신의 브랜드 아이덴티티를 돋보이게 할 독창적인 로고를 제작해 드립니다. 
<br>
<br>브랜드의 핵심 가치를 반영하고, 기억에 남는 디자인으로 고객과의 첫 만남을 특별하게 만들어 드립니다. 
<br>
<br>상담을 통해 브랜드의 스토리와 철학을 파악하고, 이를 시각적으로 구현하여 차별화된 로고를 완성합니다.
<br>
<br>가격: 80,000원',
to_date('24/08/30','RR/MM/DD'),4,0,'available',80000,'mikeJones');


Insert into C##KIMKYUNGWON.POST (ID,TITLE,BODY,CREATED_AT,CATEGORY_ID,VIEW_COUNT,STATUS,PRICE,AUTHOR_ID) values (21,
'웹사이트 제작','당신의 비즈니스에 완벽히 맞춘 웹사이트를 제작해 드립니다. 
<br>
<br>제품 판매를 위한 쇼핑몰, 포트폴리오 사이트, 블로그 등 다양한 목적에 맞는 웹사이트를 최신 기술과 트렌드를 반영하여 설계합니다. 
<br>
<br>사용자가 쉽게 탐색할 수 있는 UI/UX 디자인과 모바일 친화적인 반응형 웹사이트로 사용자 경험을 최적화합니다. 
<br>
<br>비즈니스의 성장을 돕는 웹사이트로 온라인에서의 존재감을 확립하세요.
<br>
<br>가격: 300,000원',
to_date('24/08/30','RR/MM/DD'),5,0,'available',300000,'mikeJones');


Insert into C##KIMKYUNGWON.POST (ID,TITLE,BODY,CREATED_AT,CATEGORY_ID,VIEW_COUNT,STATUS,PRICE,AUTHOR_ID) values (25,
'포스터 디자인','이벤트, 공연, 캠페인 등 다양한 용도의 포스터 디자인을 제공합니다. 
<br>
<br>고객의 요청에 따라 테마와 메시지를 시각적으로 강렬하게 전달할 수 있는 포스터를 제작합니다. 
<br>
<br>브랜딩과 일관성을 유지하면서도 눈길을 사로잡는 디자인으로, 대중의 관심을 끌어낼 수 있습니다. 
<br>
<br>디지털 파일로 제공되며, 필요 시 인쇄 옵션도 지원합니다.
<br>
<br>가격: 70,000원',
to_date('24/08/30','RR/MM/DD'),4,0,'available',70000,'emilyClark');


Insert into C##KIMKYUNGWON.POST (ID,TITLE,BODY,CREATED_AT,CATEGORY_ID,VIEW_COUNT,STATUS,PRICE,AUTHOR_ID) values (23,
'초상화 제작','가족 사진, 연인 사진, 또는 나만의 특별한 모습을 담은 맞춤형 초상화를 그려드립니다. 
<br>
<br>사진만 보내주시면, 고퀄리티의 초상화로 제작하여 특별한 선물이나 인테리어 소품으로 활용할 수 있습니다. 
<br>
<br>아크릴, 유화, 수채화 등 다양한 기법을 선택할 수 있으며, 고객의 요청에 따라 특정 스타일을 반영하여 제작합니다. 
<br>
<br>이 초상화는 시간과 공간을 초월하여 특별한 순간을 영원히 간직할 수 있는 예술 작품입니다.
<br>
<br>가격: 100,000원',
to_date('24/08/30','RR/MM/DD'),1,0,'available',100000,'davidBrown');


Insert into C##KIMKYUNGWON.POST (ID,TITLE,BODY,CREATED_AT,CATEGORY_ID,VIEW_COUNT,STATUS,PRICE,AUTHOR_ID) values (24,
'소셜 미디어 콘텐츠 제작','인스타그램, 페이스북, 유튜브 등 다양한 소셜 미디어 플랫폼에서 활용할 수 있는 맞춤형 콘텐츠를 제작해 드립니다. 
<br>
<br>비즈니스의 홍보, 개인 브랜드의 성장, 이벤트 알림 등 목적에 맞는 콘텐츠로 팔로워의 관심을 끌고, 브랜드 가치를 극대화하세요. 
<br>
<br>텍스트, 이미지, 동영상 등 다양한 포맷을 지원하며, 트렌드에 맞춘 디자인과 전략으로 소셜 미디어에서의 성공을 돕습니다.
<br>
<br>가격: 50,000원',
to_date('24/08/30','RR/MM/DD'),2,0,'available',50000,'davidBrown');


Insert into C##KIMKYUNGWON.POST (ID,TITLE,BODY,CREATED_AT,CATEGORY_ID,VIEW_COUNT,STATUS,PRICE,AUTHOR_ID) values (26,
'유튜브 채널 인트로 음악 작곡',
'유튜브 채널의 인트로 음악을 맞춤 제작해드립니다! 전문 작곡가와 함께 채널의 메시지와 테마에 맞춘 배경음악을 만들어보세요.
<br>
<br>맞춤형 음악 작곡: 채널의 스타일에 맞는 독창적인 테마 음악 제작
<br>
<br>전문 작곡가와 협업: 경험 많은 작곡가와 함께하는 맞춤 작업
<br>
<br>가격:
<br>
<br>기본 패키지: 50000원 (30초 이내 인트로)
<br>
<br>맞춤 패키지: 100000원 (1분 이상)
<br>
<br>채널에 딱 맞는 인트로 음악으로 첫인상을 장식해보세요! 문의 환영합니다.',
to_date('24/08/30','RR/MM/DD'),6,0,'available',50000,'davidBrown');


Insert into C##KIMKYUNGWON.POST (ID,TITLE,BODY,CREATED_AT,CATEGORY_ID,VIEW_COUNT,STATUS,PRICE,AUTHOR_ID) values (27,
'테마 음악 작곡 서비스',
'특별한 메시지를 전달하는 테마 음악을 작곡해드립니다. 전문 작곡가와 함께하는 맞춤형 배경음악 제작!
<br>
<br>맞춤형 음악 작곡: 브랜드 메시지에 맞춘 독창적인 테마 음악 제작
<br>
<br>전문 작곡가와의 협업: 풍부한 경험을 가진 작곡가와 함께하는 작업
<br>
<br>다양한 파일 형식 제공: 추가 편집 가능한 형식으로 음악 파일 제공
<br>
<br>빠른 작업 시간: 신속한 피드백 반영과 수정
<br>
<br>가격: 기본 패키지 50000 원 (30초 이내), 맞춤 패키지 100000 원 (1분 이상)
<br>
<br>음악으로 여러분의 이야기를 더 특별하게 만드세요!',
to_date('24/08/30','RR/MM/DD'),6,0,'available',50000,'davidBrown');


Insert into C##KIMKYUNGWON.POST (ID,TITLE,BODY,CREATED_AT,CATEGORY_ID,VIEW_COUNT,STATUS,PRICE,AUTHOR_ID) values (28,
'맞춤형 배경음악 제작',
'개인의 메시지와 테마를 반영한 배경음악을 작곡합니다. 전문 작곡가와 함께하는 맞춤형 음악입니다.
<br>
<br>테마 음악 맞춤 제작: 고객의 요구에 맞는 고유한 배경음악 작곡
<br>
<br>전문 작곡가와 협업: 다양한 스타일에 능통한 작곡가와 함께하는 작업
<br>
<br>빠른 작업 속도: 신속한 피드백 반영 및 수정
<br>
<br>다양한 파일 제공: 추가 편집 가능한 포맷으로 음악 파일 제공
<br>
<br>가격: 기본 패키지 50000 원 (30초 이내), 맞춤 패키지 100000 원 (1분 이상)
<br>
<br>테마에 딱 맞는 음악으로 더 큰 감동을 전하세요!',
to_date('24/08/30','RR/MM/DD'),6,0,'available',50000,'davidBrown');


INSERT INTO IMAGE (POST_ID, IMAGE_URL) VALUES (14, 'paint.PNG');
INSERT INTO IMAGE (POST_ID, IMAGE_URL) VALUES (15, '벽화 디자인.PNG');
INSERT INTO IMAGE (POST_ID, IMAGE_URL) VALUES (18, '테마 음악 작곡.PNG');
INSERT INTO IMAGE (POST_ID, IMAGE_URL) VALUES (19, '감동을 전하는 편지 대필 서비스.PNG');
INSERT INTO IMAGE (POST_ID, IMAGE_URL) VALUES (20, '개인 브랜딩을 위한 로고 디자인.PNG');
INSERT INTO IMAGE (POST_ID, IMAGE_URL) VALUES (21, '웹사이트 제작.PNG');
INSERT INTO IMAGE (POST_ID, IMAGE_URL) VALUES (25, '포스터 디자인.PNG');
INSERT INTO IMAGE (POST_ID, IMAGE_URL) VALUES (23, '초상화 제작.PNG');
INSERT INTO IMAGE (POST_ID, IMAGE_URL) VALUES (24, '소셜 미디어 콘텐츠 제작.PNG');
INSERT INTO IMAGE (POST_ID, IMAGE_URL) VALUES (26, '유튜브 채널 인트로 음악 작곡.PNG');
INSERT INTO IMAGE (POST_ID, IMAGE_URL) VALUES (27, '테마 음악 작곡 서비스.PNG');
INSERT INTO IMAGE (POST_ID, IMAGE_URL) VALUES (28, '맞춤형 배경음악 제작.PNG');


INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (1, 'johnDoe', 'kyungwon', '안녕하세요, 맞춤형 수채화 작품에 관심 있으신가요?', to_timestamp('24/08/30 09:00:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (2, 'kyungwon', 'johnDoe', '네, 맞춤형 수채화 작품에 대해 알고 싶어요. 가격이 어떻게 되나요?', to_timestamp('24/08/30 09:05:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (3, 'johnDoe', 'kyungwon', '맞춤형 수채화 작품은 50,000원입니다.', to_timestamp('24/08/30 09:10:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (4, 'kyungwon', 'johnDoe', '제작 기간은 얼마나 걸리나요?', to_timestamp('24/08/30 09:15:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (5, 'johnDoe', 'kyungwon', '제작 기간은 약 7일 정도 소요됩니다.', to_timestamp('24/08/30 09:20:00', 'RR/MM/DD HH24:MI:SS'));


INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (6, 'janeSmith', 'kyungwon', '벽화 디자인에 대해 궁금한 점이 있으신가요?', to_timestamp('24/08/30 10:00:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (7, 'kyungwon', 'janeSmith', '네, 벽화 디자인은 어떤 스타일이 가능한가요?', to_timestamp('24/08/30 10:05:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (8, 'janeSmith', 'kyungwon', '현대적, 고전적, 자연풍경 등 다양한 스타일이 가능합니다.', to_timestamp('24/08/30 10:10:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (9, 'kyungwon', 'janeSmith', '가격은 어떻게 되나요?', to_timestamp('24/08/30 10:15:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (10, 'janeSmith', 'kyungwon', '벽화 디자인의 가격은 150,000원부터 시작합니다.', to_timestamp('24/08/30 10:20:00', 'RR/MM/DD HH24:MI:SS'));


INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (11, 'mikeJones', 'kyungwon', '테마 음악 작곡 서비스에 관심 있으신가요?', to_timestamp('24/08/30 11:00:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (12, 'kyungwon', 'mikeJones', '네, 맞춤형 음악 작곡에 대해 알고 싶어요.', to_timestamp('24/08/30 11:05:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (13, 'mikeJones', 'kyungwon', '원하시는 스타일과 테마에 맞춰 작곡이 가능합니다.', to_timestamp('24/08/30 11:10:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (14, 'kyungwon', 'mikeJones', '가격은 얼마인가요?', to_timestamp('24/08/30 11:15:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (15, 'mikeJones', 'kyungwon', '테마 음악 작곡의 가격은 120,000원입니다.', to_timestamp('24/08/30 11:20:00', 'RR/MM/DD HH24:MI:SS'));


INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (16, 'emilyClark', 'kyungwon', '편지 대필 서비스에 관심 있으신가요?', to_timestamp('24/08/30 12:00:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (17, 'kyungwon', 'emilyClark', '네, 대필 서비스에 대해 알고 싶어요.', to_timestamp('24/08/30 12:05:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (18, 'emilyClark', 'kyungwon', '받는 분의 마음을 감동시킬 수 있도록 편지를 작성해 드립니다.', to_timestamp('24/08/30 12:10:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (19, 'kyungwon', 'emilyClark', '서비스 가격은 어떻게 되나요?', to_timestamp('24/08/30 12:15:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (20, 'emilyClark', 'kyungwon', '편지 대필 서비스의 가격은 30,000원입니다.', to_timestamp('24/08/30 12:20:00', 'RR/MM/DD HH24:MI:SS'));


INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (21, 'davidBrown', 'kyungwon', '개인 브랜딩을 위한 로고 디자인에 관심 있으신가요?', to_timestamp('24/08/30 13:00:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (22, 'kyungwon', 'davidBrown', '네, 로고 디자인에 대해 알고 싶어요.', to_timestamp('24/08/30 13:05:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (23, 'davidBrown', 'kyungwon', '당신의 브랜드에 맞는 독창적인 로고를 제작해 드립니다.', to_timestamp('24/08/30 13:10:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (24, 'kyungwon', 'davidBrown', '가격은 얼마인가요?', to_timestamp('24/08/30 13:15:00', 'RR/MM/DD HH24:MI:SS'));
INSERT INTO MESSAGE (ID, SENDER_ID, RECEIVER_ID, CONTENT, CREATED_AT) 
VALUES (25, 'davidBrown', 'kyungwon', '로고 디자인의 가격은 80,000원입니다.', to_timestamp('24/08/30 13:20:00', 'RR/MM/DD HH24:MI:SS'));


-- 사용자 "admin"이 게시글 14번을 조회한 경우
INSERT INTO USER_ACTION (ID, USER_ID, POST_ID, ACTION_TYPE, ACTION_TIMESTAMP, DURATION)
VALUES (1, 'kyungwon', 14, 'view_count', TO_TIMESTAMP('2024-09-06 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), NULL);

-- 사용자 "admin"이 게시글 15번을 120초 동안 본 경우
INSERT INTO USER_ACTION (ID, USER_ID, POST_ID, ACTION_TYPE, ACTION_TIMESTAMP, DURATION)
VALUES (2, 'kyungwon', 15, 'view_duration', TO_TIMESTAMP('2024-09-06 10:02:00', 'YYYY-MM-DD HH24:MI:SS'), 120);

-- 사용자 "admin"이 게시글 18번에 좋아요를 누른 경우
INSERT INTO USER_ACTION (ID, USER_ID, POST_ID, ACTION_TYPE, ACTION_TIMESTAMP, DURATION)
VALUES (3, 'kyungwon', 18, 'liked', TO_TIMESTAMP('2024-09-06 10:03:00', 'YYYY-MM-DD HH24:MI:SS'), NULL);

-- 사용자 "admin"이 게시글 18번에 120초 동안 본 경우
INSERT INTO USER_ACTION (ID, USER_ID, POST_ID, ACTION_TYPE, ACTION_TIMESTAMP, DURATION)
VALUES (10, 'kyungwon', 18, 'view_duration', TO_TIMESTAMP('2024-09-06 10:03:01', 'YYYY-MM-DD HH24:MI:SS'), 120);

-- 사용자 "admin"이 게시글 18번을 조회한 경우
INSERT INTO USER_ACTION (ID, USER_ID, POST_ID, ACTION_TYPE, ACTION_TIMESTAMP, DURATION)
VALUES (11, 'kyungwon', 18, 'view_count', TO_TIMESTAMP('2024-09-06 10:03:02', 'YYYY-MM-DD HH24:MI:SS'), NULL);

-- 사용자 "admin"이 게시글 19번을 90초 동안 본 경우
INSERT INTO USER_ACTION (ID, USER_ID, POST_ID, ACTION_TYPE, ACTION_TIMESTAMP, DURATION)
VALUES (4, 'kyungwon', 19, 'view_duration', TO_TIMESTAMP('2024-09-06 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 90);

-- 사용자 "admin"이 게시글 20번에 좋아요를 누른 경우
INSERT INTO USER_ACTION (ID, USER_ID, POST_ID, ACTION_TYPE, ACTION_TIMESTAMP, DURATION)
VALUES (5, 'kyungwon', 20, 'liked', TO_TIMESTAMP('2024-09-06 11:02:00', 'YYYY-MM-DD HH24:MI:SS'), NULL);

-- 사용자 "admin"이 게시글 21번을 조회한 경우
INSERT INTO USER_ACTION (ID, USER_ID, POST_ID, ACTION_TYPE, ACTION_TIMESTAMP, DURATION)
VALUES (6, 'kyungwon', 21, 'view_count', TO_TIMESTAMP('2024-09-06 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), NULL);

-- 사용자 "admin"이 게시글 25번을 45초 동안 본 경우
INSERT INTO USER_ACTION (ID, USER_ID, POST_ID, ACTION_TYPE, ACTION_TIMESTAMP, DURATION)
VALUES (7, 'kyungwon', 25, 'view_duration', TO_TIMESTAMP('2024-09-06 12:01:00', 'YYYY-MM-DD HH24:MI:SS'), 45);

-- 사용자 "admin"이 게시글 23번에 좋아요를 누른 경우
INSERT INTO USER_ACTION (ID, USER_ID, POST_ID, ACTION_TYPE, ACTION_TIMESTAMP, DURATION)
VALUES (8, 'kyungwon', 23, 'liked', TO_TIMESTAMP('2024-09-06 12:05:00', 'YYYY-MM-DD HH24:MI:SS'), NULL);

-- 사용자 "admin"이 게시글 24번을 조회한 경우
INSERT INTO USER_ACTION (ID, USER_ID, POST_ID, ACTION_TYPE, ACTION_TIMESTAMP, DURATION)
VALUES (9, 'kyungwon', 24, 'view_count', TO_TIMESTAMP('2024-09-06 12:10:00', 'YYYY-MM-DD HH24:MI:SS'), NULL);

