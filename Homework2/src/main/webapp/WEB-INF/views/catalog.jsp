<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catálogo de Modelos de IA</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }
        header {
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1000;
        }
        .navbar {
            background: rgba(0, 0, 0, 0.8);
            backdrop-filter: blur(10px);
            border-bottom: 1px solid rgba(255, 255, 255, 0.1);
        }
        .navbar-brand {
            font-weight: bold;
            color: #fff !important;
        }
        .btn {
            transition: all 0.3s ease;
            border-radius: 25px;
            padding: 10px 20px;
        }
        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .welcome-text {
            color: #fff;
            margin-right: 15px;
        }
        .logout-btn {
            background: #dc3545;
            border: none;
            color: white;
        }
        .logout-btn:hover {
            background: #c82333;
        }
        main {
            margin-top: 80px; /* Adjust for fixed header */
            flex: 1;
        }
        .card {
            transition: transform 0.3s ease;
        }
        .card:hover {
            transform: translateY(-5px);
        }
    </style>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/">Catálogo IA</a>
                <div class="d-flex">
                    <c:choose>
                        <c:when test="${not empty sessionScope.username}">
                            <span class="welcome-text">Bienvenido ${sessionScope.username}</span>
                            <a href="${pageContext.request.contextPath}/logout" class="btn logout-btn">Logout</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-light me-2">Login</a>
                            <a href="${pageContext.request.contextPath}/signup" class="btn btn-light">Signup</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </nav>
    </header>

    <main>
        <div class="container mt-4">
            <h2>Catálogo de Modelos</h2>

            <div class="row">
                <c:forEach var="model" items="${modelsList}">
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <img src="${model.logoUrl}" alt="logo" class="card-img-top" style="width: 50px; margin: 10px auto;">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <a href="${pageContext.request.contextPath}/catalog/${model.id}">${model.name}</a>
                                </h5>
                                <p class="card-text">${model.summary}</p>
                                <span class="badge bg-primary">${model.mainCapability}</span>

                                <c:if test="${model.isPrivate}">
                                    <span class="text-danger">&#128274; (Privado)</span>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </main>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>