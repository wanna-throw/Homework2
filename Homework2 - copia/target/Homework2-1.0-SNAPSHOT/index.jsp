<%@ page contentType="text/html; charset=UTF-8" %>
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
        .hero {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            text-align: center;
            position: relative;
            overflow: hidden;
        }
        .hero::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"><circle cx="50" cy="50" r="2" fill="rgba(255,255,255,0.1)"/></svg>') repeat;
            opacity: 0.1;
        }
        .hero h1 {
            font-size: 4rem;
            font-weight: 700;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
            animation: fadeInUp 1s ease-out;
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
        .hero .container {
            position: relative;
            z-index: 1;
        }
        .btn-hero {
            background: rgba(255, 255, 255, 0.2);
            border: 2px solid white;
            color: white;
            padding: 15px 30px;
            font-size: 1.2rem;
            font-weight: 600;
            border-radius: 30px;
            text-decoration: none;
            transition: all 0.3s ease;
            margin-top: 20px;
            display: inline-block;
        }
        .btn-hero:hover {
            background: white;
            color: #667eea;
            transform: translateY(-3px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
        }
    </style>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Catálogo IA</a>
                <div class="d-flex">
                    <a href="${pageContext.request.contextPath}/mvc/login" class="btn btn-outline-light me-2">Login</a>
                    <a href="${pageContext.request.contextPath}/mvc/SignUp" class="btn btn-light">Signup</a>
                </div>
            </div>
        </nav>
    </header>

    <main>
        <div class="hero bg-primary text-white text-center py-5">
            <div class="container">
                <h1 class="display-4">Bienvenido al catálogo de modelos de IA</h1>
                <a href="${pageContext.request.contextPath}/mvc/catalog" class="btn-hero">Explorar Catálogo</a>
            </div>
        </div>
    </main>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>