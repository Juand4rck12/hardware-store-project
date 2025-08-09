// Responsividad del sidebar
document.addEventListener('DOMContentLoaded', function() {
    const sidebarToggle = document.getElementById('sidebarToggle');
    const sidebar = document.getElementById('sidebar');
    const overlay = document.getElementById('sidebarOverlay');

    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', function() {
            sidebar.classList.toggle('show');
            overlay.classList.toggle('show');
        });
    }

    if (overlay) {
        overlay.addEventListener('click', function() {
            sidebar.classList.remove('show');
            overlay.classList.remove('show');
        });
    }
});

// DATATABLE
document.addEventListener("DOMContentLoaded", function() {
    // Si la tabla existe
    if ($('#myTable').length) {
        // Inicializa DataTables en la tabla con id "myTable"
    $('#myTable').DataTable({
        // Opciones de idioma en español
        language: {
            url: "https://cdn.datatables.net/plug-ins/2.0.3/i18n/es-ES.json"
        }
        // Puedes agregar más opciones aquí, como orden, longitud de página, etc.
    });
    }
});