<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ACTS - Header</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
        .navbar {
            padding-left: 400px; 
            padding-right: 400px;
            background-color: transparent;
            border-bottom: 1px solid #dcdcdc;
        }
        .navbar-brand {
            font-weight: 700;
            font-size: 2rem;
            display: flex;
            align-items: center;
            margin-right: auto;
        }
        .navbar-brand .bi {
            font-size: 2.5rem;
            margin-right: 10px;
        }
        .navbar-nav {
            display: flex;
            align-items: center;
            justify-content: center;
            flex: 1;
        }
        .navbar-nav .nav-item {
            margin: 0 3px; /* 기본 항목 사이의 간격 */
        }
        .navbar-nav .nav-link {
            font-weight: 600; /* 글자 두께 감소 */
            font-size: 0.9rem; /* 글자 크기 감소 */
        }
        .navbar-nav .nav-link i {
            font-size: 2.0rem; /* 아이콘 크기 감소 */
            margin-right: 5px;
        }
        .nav-item-left {
            margin-right: auto;
        }
        .nav-item-right {
            margin-left: auto;
        }
        .nav-item-spacing {
            margin-left: 30px; /* 필요에 따라 조정 */
        }
        /* 로그인과 마이페이지 아이콘 크기 조정 */
        .nav-link-large i {
            font-size: 1.5rem; /* 원래 크기 */
        }
        /* 검색 버튼 크기 고정 */
        .btn-search {
            width: 50px; /* 고정된 버튼 크기 */
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light">
        <a class="navbar-brand" href="<c:url value='/comm/main' />">
            <i class="bi bi-house"></i> ACTS
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav nav-item-spacing">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/comm/buyerPage' />">사고싶어요</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/comm/search/list' />">팔고싶어요</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='' />">공지사항</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='' />">인기글</a>
                </li>
                
                <ul class="navbar-nav mx-auto">
                    <li class="nav-item">
                        <form class="form-inline my-2 my-lg-0">
                            <input class="form-control mr-sm-2" type="search" placeholder="찾고싶은 상품이 있나요?" aria-label="Search">
                            <button class="btn btn-primary my-2 my-sm-0 btn-search" type="submit">
                                <i class="bi bi-search"></i>
                            </button>
                        </form>
                    </li>
                </ul>
                <c:if test="${accountId eq null}">
                    <li class="nav-item">
	                    <a class="nav-link" href="<c:url value='/user/login' />">로그인</a>
	                </li>
                </c:if>
                <c:if test="${accountId ne null}">
                    <li class="nav-item">
	                    <a class="nav-link" href="<c:url value='/user/logout' />">로그아웃</a>
	                </li>
                </c:if>
                <li class="nav-item nav-item-right">
                    <a class="nav-link nav-link-large" href="<c:url value='/user/myPage' />"><i class="bi bi-person"></i></a>
                </li>
            </ul>
        </div>
    </nav>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
