<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro - Catálogo IA</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0;
        }
        .signup-card {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            backdrop-filter: blur(10px);
            padding: 40px;
            max-width: 500px;
            width: 100%;
            animation: fadeInUp 0.6s ease-out;
        }
        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
        .signup-card h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #333;
            font-weight: 600;
        }
        .form-control {
            border-radius: 10px;
            border: 1px solid #ddd;
            padding: 12px 15px;
            margin-bottom: 20px;
            transition: all 0.3s ease;
        }
        .form-control:focus {
            border-color: #667eea;
            box-shadow: 0 0 0 0.2rem rgba(102, 126, 234, 0.25);
        }
        .btn-signup {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border: none;
            border-radius: 10px;
            padding: 12px;
            width: 100%;
            color: white;
            font-weight: 600;
            transition: all 0.3s ease;
        }
        .btn-signup:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        .alert {
            border-radius: 10px;
            margin-bottom: 20px;
        }
        .back-link {
            text-align: center;
            margin-top: 20px;
        }
        .back-link a {
            color: #667eea;
            text-decoration: none;
            transition: color 0.3s ease;
        }
        .back-link a:hover {
            color: #764ba2;
        }
    </style>
</head>
<body>
    <div class="signup-card">
        <h2>Registro</h2>
        <form action="${mvc.uri('sign-up')}" method="POST">
            <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}"/>
            <div class="form-group">
                <input type="text" name="firstName" value="${user.firstName}" class="form-control" placeholder="Nombre" required>
            </div>
            <div class="form-group">
                <input type="text" name="lastName" value="${user.lastName}" class="form-control" placeholder="Apellido" required>
            </div>
            <div class="form-group">
                <input type="email" name="email" value="${user.email}" class="form-control" placeholder="Correo electrónico" required>
            </div>
            <button type="submit" class="btn btn-signup">Registrarse</button>
        </form>
        <c:if test="${not empty message}">
            <div class="alert alert-danger" role="alert">
                ${message}
            </div>
        </c:if>
        <c:if test="${attempts.hasExceededMaxAttempts()}">
            <div id="too-many-signup-attempts" class="modal top fade" role="alert" tabindex="-1" data-mdb-backdrop="static" data-mdb-keyboard="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="modal-title" id="too-many-signup-attempts">Por favor, inténtalo de nuevo más tarde.</h2>
                        </div>
                        <div class="modal-body">
                            <div class="alert alert-danger" role="alert">
                                <img class="mb-4" src="<c:url value="/resources/img/Invalid.png" />" alt="" width="134" height="92" />
                                ¡Se ha excedido el número máximo de intentos de registro!
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script>
                $("#too-many-signup-attempts").modal('show');
            </script>
        </c:if>
        <jsp:include page="/WEB-INF/views/layout/alert.jsp" />
        <div class="back-link">
            <a href="${pageContext.request.contextPath}/">Volver al inicio</a>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>