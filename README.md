# Project - ACTS (비전문가 외주 거래 서비스)

## 🌞 목차

### **[1. 🌑 프로젝트 정보](#🌑-프로젝트-정보)**

### **[2. 🌒 개발 배경 및 목적](#🌒-개발-배경-및-목적)**

### **[3. 🌓 Skill](#🌓-Skill)**

### **[4. 🌔 주요 기능](#🌔-주요-기능)**

### **[5. 🌕 시스템 설계 과정](#🌕-시스템-설계-과정)**

### **[6. 🌖 기능 구현](#🌖-기능-구현)**

### **[7. 🌗 프로젝트 설정 및 실행 방법](#🌗-프로젝트-설정-및-실행-방법)**

## 🌑 프로젝트 정보

![image](https://github.com/BingBong1999/somature/assets/142529694/9b7bb2a9-d875-455d-9fc3-b420ad4e1921)

- 동덕여자대학교 3학년 2학기 데이터베이스 프로그래밍 과목에서 수행한 프로젝트입니다.

- 총 4 명의 팀원으로 구성하여 한 학기동안 진행하였습니다.

- 해당 프로젝트에 대한 전반적인 정보는 본 README 파일에 기재되어 있지만,

  **요구사항 분석**, **시스템 구성도**, **데이터베이스 설계**, **MVC 설계**, **Request URI 처리 설계**, **프로그램 동작 화면**이 포함된 **이미지 파일**은 

  아래의 첨부된 PDF 파일과 프로젝트 시연 영상 링크를 통해 확인해 주시기 바랍니다.

- PDF 파일 및 프로젝트 시연 영상 링크:

- [acts pdf 파일 링크](https://drive.google.com/file/d/1N9nd2-Lqpyxd5r1XS69SP_j-skS4rHRb/view?usp=sharing)

- [acts 프로젝트 시연 영상 링크](https://drive.google.com/file/d/1CxR71SBd_9CaYRMW5As9XstDMpkDjGSd/view?usp=sharing)


## 🌒 개발 배경 및 목적

- 타 플랫폼에서 전문가에게 외주를 맡기기에 가격이 고민되는 **소비자**들과
- 다양한 분야의 전문가가 되기 위해 발돋움 중인 **주니어**들을 위해
- 서로의 니즈를 충족시킬 수 있는 **커뮤니티 웹 서비스**를 제공하고자 제작된 프로젝트입니다.


## 🛸 주요 기능 및 특징

### 1. 게시글 등록

- 게시글 작성/읽기/수정/삭제 기능
- 게시글 좋아요 버튼 활성화 - 좋아요 버튼을 누른 게시글은 마이페이지에서 확인 가능
- 게시글에 댓글 작성 기능

### 2. 사용자 회원가입 및 로그인

- 사용자 생성, 사용자 정보 조회, 사용자 정보 수정, 사용자 정보 삭제 기능
- 사용자 정보 조회 및 정보 수정 기능은 마이페이지에서 작업 가능

### 3. 거래

- 각 게시글 마다 거래를 요청하는 주문서 작성 가능
- 해당 거래내역은 마이페이지에서 확인 가능

### 4. 검색

- 게시글 유형에 따른 카테고리를 나누어, 카테고리 별 게시글 리스트 검색 가능.
- 특정 검색어가 포함된 게시글 제목 리스트 검색 가능.

## 🦉 도움이 되었던 Web Link 모음

- **Error: The import org.apache.commons cannot be resolved**
	- https://k-gline.tistory.com/3

- **Error: java.lang.NullPointerException: null
	at controller.DispatcherServlet.service(DispatcherServlet.java:)**
	- <c:url ~> 사용했을 경우, 상단에 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 작성했는지 확인

- **[JSTL] session에 접근하는 다양한 방법**
	- https://mytory.net/archives/323

- **jsp에서 parameter가 안 넘어갈 경우**
	- https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=kimsu0813&logNo=140201820687
