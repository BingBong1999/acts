# Project - ACTS (비전문가 외주 거래 서비스)

## 🌞 목차

### **[1. 🌑 프로젝트 정보](#-프로젝트-정보)**

### **[2. 🌒 개발 배경 및 목적](#-개발-배경-및-목적)**

### **[3. 🌓 SKILL](#-SKILL)**

### **[4. 🌔 주요 기능](#-주요-기능)**

### **[5. 🌕 개발 과정](#-개발-과정)**

### **[6. 🌖 기능 구현](#-기능-구현)**

### **[7. 🌗 프로젝트 설정 및 실행 방법](#-프로젝트-설정-및-실행-방법)**

## 🌑 프로젝트 정보

![image](https://github.com/BingBong1999/somature/assets/142529694/9b7bb2a9-d875-455d-9fc3-b420ad4e1921)

- 동덕여자대학교 3학년 2학기 데이터베이스 프로그래밍 과목에서 수행한 프로젝트입니다.

- 총 4 명의 팀원으로 구성하여 한 학기동안 진행하였습니다.

- 해당 프로젝트에 대한 전반적인 정보는 본 README 파일에 기재되어 있지만,

  **요구사항 분석**, **시스템 구성도**, **데이터베이스 스키마**, **MVC 구조**, **Request 처리 흐름**, **구현 결과** 등이 포함된 **이미지 파일**은 

  아래의 첨부된 PDF 파일과 프로젝트 시연 영상 링크를 통해 확인해 주시기 바랍니다.

- PDF 파일 및 프로젝트 시연 영상 링크:

- [acts pdf 파일 링크](https://drive.google.com/file/d/1N9nd2-Lqpyxd5r1XS69SP_j-skS4rHRb/view?usp=sharing)

- [acts 프로젝트 시연 영상 링크](https://drive.google.com/file/d/1CxR71SBd_9CaYRMW5As9XstDMpkDjGSd/view?usp=sharing)


## 🌒 개발 배경 및 목적

- 타 플랫폼에서 전문가에게 외주를 맡기기엔 가격이 고민되는 **소비자**들과

- 다양한 분야의 전문가가 되기 위해 발돋움 중인 **아마추어**를 위해

- 서로의 니즈를 충족시킬 수 있는 **재능기부 커뮤니티 웹 서비스**입니다.

## 🌓 SKILL

- **Language**: ```Java```, ```HTML```, ```CSS```, ```Java Script```, ```SQL```

- **Database**: ```Oracle```

- **Framework**: ```Bootstrap```

- **Design Pattern**: ```MVC Pattern```, ```Singleton Pattern```

## 🌔 주요 기능

### 1. 게시글 등록

- 사용자는 게시글을 작성하고 읽고 수정하고 삭제할 수 있습니다.

- 사용자는 마음에 드는 게시글에 ‘좋아요’ 버튼을 누르고, 마이페이지에서 확인할 수 있습니다.

- 사용자는 게시글에 댓글을 작성할 수 있습니다.

### 2. 사용자 회원가입 및 로그인

- 사용자는 회원가입을 통해 계정을 생성할 수 있습니다.

- 사용자는 마이페이지에서 개인 정보를 조회하거나 수정하고, 계정 탈퇴 작업을 수행할 수 있습니다.

- 사용자 아이디와 비밀번호를 입력하여 로그인하고, 버튼을 눌러 로그아웃할 수 있습니다.

### 3. 거래

- 사용자는 원하는 게시글에 대한 거래를 원할 때, 해당 게시글에서 거래를 요청하는 주문서를 작성할 수 있습니다.

- 사용자는 마이페이지에서 거래 내역을 확인할 수 있습니다.

### 4. 검색

- 사용자는 게시글 유형에 따라 게시글 목록을 분류하여 확인할 수 있습니다.

- 사용자는 특정 검색어가 포함된 게시글 제목으로 게시글을 검색할 수 있습니다.

## 🌕 개발 과정

### 1. 요구사항 분석과 기획

- **시장 조사 및 사례 분석**

	- 현재 시중에 있는 커뮤니티 플랫폼을 조사하여 우리 프로젝트 주제와 관련된 사례를 분석하여 벤치마킹하였습니다. 이를 통해 UI와 주요 기능, 사용자 경험을 파악하여 우리 프로젝트에 적용할 수 있도록 인사이트를 도출하였습니다.

- **요구사항 명세 작성**

	- 초기 요구사항을 목록화하여 표로 요구사항 명세서를 작성하였습니다. 각 요구사항에는 고유한 ID를 부여하고, 요구사항의 내용과 유형, 우선순위에 따라 분류하였습니다.
    
	- **요구사항의 유형**
        
		- Functional (기능적 요구사항)
        
		- Products (제품 요구사항)
        
		- User Interface (사용자 인터페이스 요구사항)
        
		- Orders (주문 요구사항)
        
		- Nonfunctional (비기능적 요구사항)
    
	- **요구사항의 우선순위**
        
		- Must-Have (필수 요구사항)
        
		- Should-Have (권장 요구사항)
        
		- Could-Have  (선택 가능 요구사항)

- **Use-case 명세 작성**
    
	- 사용자와 시스템 간의 상호작용을 시각적으로 표현한 **Use-case Diagram**을 작성하였습니다.

	- **Use-case 상세 명세**를 작성하여 각 Use-case의 ‘이름’, ‘설명’, ‘사전 조건’, ‘수행 절차’, ‘사후 조건’ 등을 포함하여 상세하게 기술하였습니다.

- **추진 일정 계획 및 공유**
 
	- 프로젝트 추진 일정을 상세히 계획하고, 모든 팀원이 일정과 역할을 명확히 확인할 수 있도록 **Notion** 이라는 툴을 활용하였습니다.

   	- 팀 구성 양식을 작성하여 팀원들의 이름과 전화번호 등의 개인정보와 팀의 규칙 및 문제 해결 전략 등을 기술하였습니다.

	- 팀원들과 일주일에 한 번씩 **Zoom**을 사용한 온라인 회의 혹은 오프라인 회의를 주기적으로 실시하였습니다.

### 2. UI/UX 디자인

- **UI 화면 프로토타입**을 작성하여 사용자의 서비스 사용 흐름을 시각적으로 표현하고, 이를 바탕으로 UI 화면 프로토타입 설계 문서와 UI 화면 정의서를 작성하였습니다.
    
    - UI 화면 정의서에는 각 화면의 구성 요소에 대한 상호작용 방식과 UX 요소들을 상세하게 기술하였습니다.

- 사용자에게 편안한 색감을 주고 아마추어의 이미지를 강조하기 위해 ‘새싹’을 서비스의 아이콘으로 결정하였습니다.

### 3. 시스템 설계

- **데이터베이스 설계**

	- **데이터베이스 스키마**를 작성하여 주요 테이블의 PK(Primary Key)를 결정하고, **논리적 모델링**과 **물리적 모델링** 단계를 거쳐 데이터베이스 구조를 설계하였습니다.

	- 설계된 데이터베이스 구조를 바탕으로 **DDL(Data Definition Language) 스크립트 파일**을 작성하여 데이터베이스 생성 및 초기 설정을 자동화하였습니다.

- **Request 처리 흐름 설계**

	- 웹 서비스의 **사용자 요청(Request)에 대한 시스템 처리 흐름도**를 Google Sheets를 활용하여 상세히 작성하여, MVC(Model-View-Controller) 패턴을 기반으로 한 시스템 아키텍처를 설계하였습니다.

  	- 각 요청에 대해 **‘입력 화면’**, **‘request URI’**, **‘request Parameters’**, **‘Controller’**, **‘Manager’**, **‘DAO’**, **‘요청 처리 결과’**, **‘출력 화면’**, **‘출력 결과 처리 방식’** 을 사용자 요청이 처리되는 과정을 정의하였습니다.

- **도메인 클래스 설계**

	- 시스템의 주요 도메인 클래스와 클래스들 간 관계를 시각적으로 표현한 **도메인 클래스 다이어그램**을 작성하였습니다.

	- 각 클래스의 속성, 메서드를 명시하고 클래스 간의 연관 관계를 상세하게 정의하였습니다.

### 4. 기술 스택 선정

- **Front-End**

	- 반응형 웹 디자인과 프론트엔드 개발을 빠르고 쉽게 하기 위해 CSS 프레임워크인 **Bootstrap**을 사용하였습니다.

- **Back-End**

	- **Model**(데이터와 비즈니스 로직), **View**(사용자에게 정보 표시 및 입력 수신), **Controller**(사용자 입력을 모델에 전달하고 모델의 결과를 뷰로 전달)로 역할을 분리하여 각 부분들을 독립적으로 개발하고 유지보수하는 것이 편리하기 때문에 **MVC 패턴**을 적용하였습니다.
    
	- **SingleTon Pattern**을 적용하여 여러 모듈에서 공통적으로 사용하는 객체를 전역적으로 접근할 수 있고, 한 번 생성된 인스턴스를 재사용함으로써 메모리를 절약하였습니다.
  
	- **Database**

		- 테이블 형식으로 데이터를 저장하고 테이블 간의 관계를 이용하여 데이터를 관리하기 위해 **Oracle RDBMS**를 사용하였습니다.

   		- Oracle RDBMS와 Java로 작성된 프로그램을 연결하기 위해 Java API인 **Oracle JDBC**를 사용하였습니다.

		- 서비스의 성능을 향상시키기 위해 데이터베이스 연결 풀링을 구현하는 **DBCP**를 사용하여 데이터베이스의 연결을 관리하였습니다.

- **etc.**

  	- 서비스에서 사용하는 외부 라이브러리를 다운로드하고 프로젝트에 추가하기 위해 **Apache** **Maven**을 사용하였습니다.

### 5. 개발 환경 설정

- erwin은 데이터 모델링을 통해 데이터베이스를 설계하고, 복잡한 데이터베이스 구조를 시각적으로 확인할 수 있습니다. 또한, ER(엔티티-관계) 다이어그램으로 데이터베이스의 구조를 관리하기 위해 erwin을 사용하였습니다.

### 6. 기능 구현

- 이 사항에 대한 설명은 하단에 따로 기재하였습니다.

### 7. 테스트 및 디버깅

- **SLF4J**의 `org.slf4j.Logger` 클래스와 `org.slf4j.LoggerFactory`의 `getLogger()` 메서드를 사용하여 `Logger` 객체를 생성하고, `Logger.debug()` 메서드로 프로그램 실행 중 발생하는 다양한 이벤트와 상태 정보를 로그 메세지로 기록해 시스템이 정상적으로 동작하는지 확인하였습니다.

- **Eclipse**의 **Debug mode**를 사용하여 **breakpoint**를 설정하여 디버깅함으로써, 코드 실행 흐름과 변수 상태를 추적하여 데이터의 흐름을 파악하였습니다.

- **데이터베이스와 관련된 에러메세지**를 통해, 테이블 구조와 삽입된 데이터의 필드 유형이 일치하지 않을 경우 발생하는 문제를 식별하고 수정하였습니다.

- 서비스의 여러 동작을 실행하며, **Oracle SQL Developer**를 통해 데이터베이스의 데이터가 예상대로 생성, 수정, 삭제되는지 확인하였습니다.

### 8. VCS

- Git과 GitHub을 활용하여 각 기능과 버그 수정 사항에 대한 Project issue를 생성하고, 각 issue에 대해 브랜치를 만들어 협업하였습니다.

- Commit 메세지를 작성하여 브랜치 사용자와 변경사항을 기록하였고, 이를 바탕으로 팀원들과 코드 리뷰를 진행하여 코드를 개선하였습니다.

## 🌖 기능 구현

- **도메인 객체**의 데이터 **생성(C)**, **검색(R)**, **갱신(U)**, **삭제(D) 요청은 Controller**에서 접수합니다.
  
    - 사용자의 요청이 들어오면 **Controller**는 해당 요청을 받아서 **Manager** 클래스를 이용하여 데이터 검증 및 후속 작업을 수행합니다.
    
    - **Manager** 클래스는 **DAO** 클래스를 통해 데이터베이스에 접근하여 요청된 작업을 수행하고 필요한 경우, 데이터를 반환합니다.
    
    - 이 때, **DAO** 클래스는 `JDBCUtil`의 `setSqlAndParameters` 메서드를 사용하여 SQL 쿼리와 파라미터를 설정하고 **Database**에 해당 작업을 요청합니다.
    
    - 작업이 완료되면 **Controller**는 사용자를 다음 페이지로 `redirect` 하거나 `forward` 합니다.

### 1. 사용자가 게시글 갱신 버튼을 클릭할 경우

- **Controller**에서 해당 게시글의 작성자와 현재 로그인한 사용자의 계정을 대조합니다.
    
- 동일한 계정일 경우, 게시글 갱신 양식 페이지로 화면을 전환합니다.
    
- 동일한 계정이 아닐 경우, 게시글 상세 보기 페이지로 화면을 전환하고 사용자에게 게시글 작성자가 아니므로 게시글 갱신을 진행할 수 없다고 알립니다.

### 2. 특정 카테고리로 게시글을 검색할 경우

- 카테고리 종류에 따라 게시글 목록을 조회하는 **SQL** 쿼리를 작성하였습니다.
        
	- `JOIN` 절을 사용하여 `POST`와 `CATEGORY` 테이블을 연결하고, `ORDER BY` 절을 사용하여 조회된 결과를 게시글의 ID를 기준으로 정렬하였습니다.
    
- `PreparedStatement`를 사용하여 쿼리의 파라미터를 설정하고 실행합니다.

- `ResultSet`을 사용하여 검색된 게시글을 Post 도메인 객체에 매핑하고, 이를 `List<Post>`에 추가하여 반환합니다.

### 3. 사용자가 특정 게시글을 조회할 경우

- **PostInfoController**에서 **PostManager** 클래스를 호출합니다.

- **PostManager** 클래스는 **PostDAO** 클래스를 통해 쿼리를 실행시켜 데이터베이스에 접근하고 해당 게시글의 조회수를 증가시킵니다.

### 4. 사용자가 게시글을 생성/수정하면서 게시글의 대표 이미지를 설정할 경우

- **Java Script**에서 `setThumbnail` 함수를 정의하고, `FileReader` 객체를 생성하여 `onload` **Event Handler**를 지정하였습니다.

- `onload` **Event Handler**에서는 `FileReader` 객체가 읽은 파일의 데이터 URL을 img 변수의 src 속성에 설정하여 이미지를 페이지에 표시합니다.

- 이미지 파일을 읽고 데이터 URL로 변환하기 위해 `FileReader` 객체의 `readAsDataURL` 메서드를 사용하였습니다.

### 5. 메인 화면에서 게시글의 대표 이미지를 사용자에게 보여주는 경우

- `<c:if test=*"*${empty ****post.getImgUrl()}*"*>` **JSTL** 코드를 사용하여 이미지 주소가 비어있는 경우를 식별하고, 
`<img src=*"*<c:url value='/images/noImage.png'/>*"* class=*"*listImg*"*>` 코드를 사용하여 기본 이미지로 게시글 대표 이미지를 지정합니다.

- `<c:if test=*"*${not empty ****post.getImgUrl()}*"*>` **JSTL** 코드를 사용하여 이미지 주소가 비어있지 않은 경우를 식별하고, 
`<img src=*"*${pageContext.request.contextPath}*/*upload*/*${post.getImgUrl()}*"* class=*"*listImg*"*>` 코드를 사용하여 해당 이미지를 게시글의 대표 이미지로 지정합니다.

### 6. 사용자가 게시글을 보고 좋아요 기능을 활용하고자 할 경우

- `<c:if test=*"*${empty ****user.userId}*"*>` **JSTL** 코드를 사용하여 **현재 사용자가 로그인하지 않은 사용자**임을 식별하고 ‘좋아요’ 버튼을 표시합니다. 사용자가 버튼을 클릭하면, **로그인 양식 페이지**로 화면을 전환합니다.

- `<c:if test=*"*${fOrNot eq ****0 and not empty ****user.userId}*"*>` **JSTL** 코드를 사용하여 **현재 로그인한 사용자가 게시글에 ‘좋아요’ 버튼을 누르지 않은 경우**를 식별하고 ‘좋아요’ 버튼을 표시합니다. 사용자가 버튼을 클릭하면, 관련 데이터를 생성하도록 **Controller**에 **POST** 요청을 보냅니다.

- `<c:if test=*"*${fOrNot eq ****1}*"*>` **JSTL** 코드를 사용하여 **현재 로그인한 사용자가 게시글에 ‘좋아요’ 버튼을 이미 누른 경우**를 식별하고 ‘좋아요 해제’ 버튼을 표시합니다. 사용자가 버튼을 클릭하면, 관련 데이터를 삭제하도록 **Controller**에 **POST** 요청을 보냅니다.

## 🌗 프로젝트 설정 및 실행 방법

1. Remote acts Repository 를 본인의 Eclipse Local Repository에 Clone 합니다.

2. Local Repository의 acts 프로젝트를 Eclipse WorkSpace로 import하고, Java EE View로 확인합니다.

3. acts 프로젝트의 Java Build Path에 Apache Tomcat을 설정합니다.

4. acts 프로젝트의 Dynamic Web Module과 Tomcat 버전을 호환되도록 맞춥니다.

	- [Dynamic Web Module과 Tomcat 버전 호환하는 방법](https://dark-cloud-covered-sunshine.tistory.com/27)

5. acts 프로젝트를 Tomcat에 적재합니다.

6. 한글 Encoding을 다음과 같은 순서로 설정합니다.

	- Window -> Preferences -> Editors -> Text Editors -> Spelling -> Encoding -> Other: UTF-8 체크 -> Apply

	- Window -> Preferences -> General -> Workspace -> Text file encoding -> Other: UTF-8 체크 -> Apply

	- Window -> Preferences -> General -> Content Types -> Java Class File, Text -> Default encoding: UTF-8 입력 -> Update -> Apply and Close

	- Window -> Preferences -> Web -> CSS Files, HTML Files, JSP Files -> Encoding: UTF-8 입력 -> Apply and Close

  	- Window -> Preferences -> XML -> XML Files -> Encoding: UTF-8 입력 -> Apply and Close

  	- acts 프로젝트 우클릭 -> Properties -> Resource -> Text file encoding -> Other: UTF-8 체크 -> Apply

7. 프로젝트 전체실행 버튼을 클릭합니다.

## 🌘 참고한 Web Page 및 실행 오류 해결 방법

- **Error: The import org.apache.commons cannot be resolved**
	- https://k-gline.tistory.com/3

- **Error: java.lang.NullPointerException: null
	at controller.DispatcherServlet.service(DispatcherServlet.java:)**
	- <c:url ~> 사용했을 경우, 상단에 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 작성했는지 확인

- **[JSTL] session에 접근하는 다양한 방법**
	- https://mytory.net/archives/323

- **jsp에서 parameter가 안 넘어갈 경우**
	- https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=kimsu0813&logNo=140201820687
