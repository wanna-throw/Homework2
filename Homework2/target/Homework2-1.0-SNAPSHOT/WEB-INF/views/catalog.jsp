<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Catálogo IA</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .model-card img { height: 80px; object-fit: contain; }
        .description-text { font-size: 0.9em; color: #555; display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical; overflow: hidden; }
    </style>
</head>
<body>

<nav class="navbar navbar-light bg-light mb-4 shadow-sm">
    <div class="container">
        <span class="navbar-brand mb-0 h1">Homework 2</span>
        <div>
            <c:if test="${empty sessionScope.username}">
                <a href="${pageContext.request.contextPath}/mvc/login" class="btn btn-outline-primary btn-sm">Iniciar Sesión</a>
            </c:if>
            <c:if test="${not empty sessionScope.username}">
                <span class="me-2">Hola, ${sessionScope.username}</span>
                <a href="${pageContext.request.contextPath}/mvc/logout" class="btn btn-outline-danger btn-sm">Salir</a>
            </c:if>
        </div>
    </div>
</nav>

<div class="container">
    <div id="models-container" class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
        </div>
    
    <div id="loading-spinner" class="text-center my-4" style="display:none;">
        <div class="spinner-border text-primary" role="status"></div>
    </div>
</div>

<script>
    let page = 1;
    let isLoading = false;
    let hasMore = true;
    
    // Función para formatear fechas (si viene en milisegundos o string ISO)
    function formatDate(dateString) {
        if (!dateString) return 'N/A';
        const date = new Date(dateString);
        return date.toLocaleDateString(); // Formato local (ej: 12/05/2025)
    }

    async function loadModels() {
        if (isLoading || !hasMore) return;
        isLoading = true;
        document.getElementById('loading-spinner').style.display = 'block';

        try {
            // Petición al endpoint JSON
            console.log("GET lista de modelos");
            const response = await fetch('${pageContext.request.contextPath}/mvc/catalog/data?page=' + page);
            console.log("Lista de modelos recibida");
            const models = await response.json();
            console.log("Lista de modelos leida");

            if (models.length === 0) {
                hasMore = false;
            } else {
                const container = document.getElementById('models-container');
                models.forEach(model => {
                    // Mapeo seguro de campos (manejando nulos)
                    const name = model.name || 'Sin nombre';
                    const provider = model.provider || 'Desconocido';
                    const logo = model.logoUrl || 'https://via.placeholder.com/80?text=No+Image';
                    const desc = model.description || 'Sin descripción disponible.';
                    const ver = model.version || 'v1.0';
                    const date = formatDate(model.releaseDate);
                    
                    // Lógica para candado (campo private)
                    // Intentamos leer "private" (JSON puro) o "isPrivate" (mapeado)
                    const isPriv = (model.private === true) || (model.isPrivate === true);
                    const lockHtml = isPriv ? '<span class="badge bg-warning text-dark float-end">🔒 Privado</span>' : '';

                    // HTML de la tarjeta con los campos solicitados
                    const card = `
                        <div class="col">
                            <div class="card h-100 shadow-sm model-card">
                                <div class="card-body">
                                    <div class="d-flex justify-content-between mb-3">
                                        <img src="\${logo}" alt="\${provider}">
                                        <div>\${lockHtml}</div>
                                    </div>
                                    <h5 class="card-title">\${name}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">\${provider}</h6>
                                    
                                    <p class="description-text my-3">\${desc}</p>
                                    
                                    <ul class="list-group list-group-flush small mb-3">
                                        <li class="list-group-item d-flex justify-content-between px-0">
                                            <span>Versión:</span> <strong>\${ver}</strong>
                                        </li>
                                        <li class="list-group-item d-flex justify-content-between px-0">
                                            <span>Lanzamiento:</span> <strong>\${date}</strong>
                                        </li>
                                    </ul>

                                    <a href="${pageContext.request.contextPath}/mvc/catalog/\${model.id}" class="btn btn-primary w-100">Ver Detalles</a>
                                </div>
                            </div>
                        </div>
                    `;
                    container.insertAdjacentHTML('beforeend', card);
                });
                page++;
            }
        } catch (e) {
            console.error("Error cargando modelos:", e);
        } finally {
            isLoading = false;
            document.getElementById('loading-spinner').style.display = 'none';
        }
    }

    // Scroll Infinito
    window.addEventListener('scroll', () => {
        if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight - 100) {
            loadModels();
        }
    });

    // Carga inicial
    document.addEventListener('DOMContentLoaded', loadModels);
</script>

</body>
</html>