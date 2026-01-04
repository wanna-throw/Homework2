<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Catálogo de Modelos IA</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-light bg-light mb-4">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">AI Catalog</span>
        <div>
            <c:if test="${empty sessionScope.username}">
                <a href="${pageContext.request.contextPath}/mvc/login" class="btn btn-outline-primary">Login</a>
                <a href="${pageContext.request.contextPath}/mvc/SignUp" class="btn btn-primary">Registro</a>
            </c:if>
            <c:if test="${not empty sessionScope.username}">
                <span>Bienvenido, ${sessionScope.username}!</span>
                <a href="${pageContext.request.contextPath}/mvc/login/logout" class="btn btn-sm btn-danger">Logout</a>
            </c:if>
        </div>
    </div>
</nav>

<div class="container">
    <div id="models-container" class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
        </div>

    <div id="loading-spinner" class="text-center my-5" style="display: none;">
        <div class="spinner-border text-primary" role="status"></div>
    </div>
    
    <div id="end-message" class="text-center my-5 text-muted" style="display: none;">
        No hay más modelos.
    </div>
</div>

<script>
    let page = 1;
    let isLoading = false;
    let hasMore = true;
    const container = document.getElementById('models-container');
    const spinner = document.getElementById('loading-spinner');
    const endMessage = document.getElementById('end-message');

    // Función para obtener datos del controlador
    async function loadModels() {
        if (isLoading || !hasMore) return;
        
        isLoading = true;
        spinner.style.display = 'block';

        try {
            // Llamada al endpoint JSON creado en CatalogController
            const response = await fetch('${pageContext.request.contextPath}/mvc/catalog/data?page=' + page);
            
            if (!response.ok) throw new Error("Error en red");
            
            const models = await response.json();

            if (models.length === 0) {
                hasMore = false;
                endMessage.style.display = 'block';
            } else {
                models.forEach(model => {
                    const cardHtml = createCard(model);
                    container.insertAdjacentHTML('beforeend', cardHtml);
                });
                page++;
            }
        } catch (err) {
            console.error(err);
        } finally {
            isLoading = false;
            spinner.style.display = 'none';
        }
    }

    // Generador de HTML para cada carta
    function createCard(model) {
        // Lógica visual para candado
        const lockIcon = model.isPrivate ? '<span class="badge bg-warning text-dark">🔒 Privado</span>' : '';
        
        return `
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <div class="card-header d-flex justify-content-between align-items-center">
                        <small class="text-muted">\${model.provider}</small>
                        \${lockIcon}
                    </div>
                    <div class="card-body text-center">
                        <img src="\${model.logoUrl}" alt="Logo" class="img-fluid mb-3" style="max-height: 60px;">
                        <h5 class="card-title">\${model.name}</h5>
                        <p class="card-text small">\${model.summary}</p>
                        <span class="badge bg-info">\${model.mainCapability}</span>
                    </div>
                    <div class="card-footer bg-white border-top-0">
                        <a href="${pageContext.request.contextPath}/mvc/catalog/\${model.id}" class="btn btn-outline-primary w-100">Ver Detalles</a>
                    </div>
                </div>
            </div>
        `;
    }

    // Evento de Scroll Infinito
    window.addEventListener('scroll', () => {
        const { scrollTop, scrollHeight, clientHeight } = document.documentElement;
        
        // Si estamos cerca del final (a 100px)
        if (scrollTop + clientHeight >= scrollHeight - 100) {
            loadModels();
        }
    });

    // Carga inicial
    document.addEventListener('DOMContentLoaded', loadModels);
</script>

</body>
</html>