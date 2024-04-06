# Project - ACTS (비전문가 외주 거래 서비스)

## 📌 프로젝트 정보

![image](https://github.com/BingBong1999/somature/assets/142529694/9b7bb2a9-d875-455d-9fc3-b420ad4e1921)
- **동덕여자대학교** 3학년 2학기 **데이터베이스프로그래밍** 과목에서 수행한 프로젝트입니다.
- 총 4 명의 팀원으로 구성하여 한 학기동안 진행하였습니다.
- 해당 README.md 파일은 **요약된 정보**만 기재되어 있으니 더 많은 정보를 확인하시려면 아래 첨부된 **pdf 파일 및 mp3 파일을 확인해주시면** 감사하겠습니다.

## 🚀 프로젝트 개발 배경 및 목적

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


## 🛴 요구사항 분석

![image](https://github.com/BingBong1999/somature/assets/142529694/58ac876b-0f11-42e7-86b8-ab5a21faf973)


## 🧭 시스템 구성도

![image](https://github.com/BingBong1999/somature/assets/142529694/8b97fd70-8833-43de-a4ce-0b9cfb958197)


## 🌊 데이터베이스 스키마

![image](https://github.com/BingBong1999/somature/assets/142529694/f839e95c-5e66-4a7b-ae46-20487447fa9d)


## 🚝 MVC 구조

![image](https://github.com/BingBong1999/somature/assets/142529694/297a2a7b-d28e-4bc2-8984-d83de8a41492)


## 🚔 Request URI 처리 흐름도

![image](https://github.com/BingBong1999/somature/assets/142529694/9b5b0932-21a7-4a78-86a8-c6b19248c0fc)

## 🌌 구현 결과 이미지 파일

![image](https://github.com/BingBong1999/somature/assets/142529694/691c07dd-e820-47e8-9ca9-373fe7c130a0)

![image](https://github.com/BingBong1999/somature/assets/142529694/b93e3047-3e5b-437e-b835-d0c76843bd0f)

![image](https://github.com/BingBong1999/somature/assets/142529694/eeca3ca1-c9ba-4e58-af68-379f14cd6a60)

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
