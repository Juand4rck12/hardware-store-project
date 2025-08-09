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

// Script para validación de Bootstrap 5
(function() {
    'use strict';
    window.addEventListener('load', function() {
        // Obtener todos los formularios que necesitan validación
        var forms = document.getElementsByClassName('needs-validation');

        // Validar cada formulario
        Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();

// Script para formato automático del documento
document.getElementById('document').addEventListener('input', function(e) {
    // Remover cualquier caracter que no sea número
    this.value = this.value.replace(/[^0-9]/g, '');
});

// Script para formato del salario
document.getElementById('salary').addEventListener('input', function(e) {
    // Permitir solo números y punto decimal
    this.value = this.value.replace(/[^0-9.]/g, '');

    // Evitar múltiples puntos decimales
    const parts = this.value.split('.');
    if (parts.length > 2) {
        this.value = parts[0] + '.' + parts.slice(1).join('');
    }
});