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
    if (typeof jQuery !== 'undefined' && $('#myTable').length) {
        $('#myTable').DataTable({
            language: {
                url: "https://cdn.datatables.net/plug-ins/2.0.3/i18n/es-ES.json"
            }
        });
    }
});

// Script para validación de Bootstrap 5
(function() {
    'use strict';
    window.addEventListener('load', function() {
        var forms = document.getElementsByClassName('needs-validation');
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

// Script para formato automático del documento (guardado por si no existe el input)
(function() {
    const docInput = document.getElementById('document');
    if (docInput) {
        docInput.addEventListener('input', function(e) {
            this.value = this.value.replace(/[^0-9]/g, '');
        });
    }

    const salaryInput = document.getElementById('salary');
    if (salaryInput) {
        salaryInput.addEventListener('input', function(e) {
            this.value = this.value.replace(/[^0-9.]/g, '');
            const parts = this.value.split('.');
            if (parts.length > 2) {
                this.value = parts[0] + '.' + parts.slice(1).join('');
            }
        });
    }
})();

// ################## SISTEMA DE NOTIFICACIONES Y CONFIRMACIONES ##################
document.addEventListener('DOMContentLoaded', function () {
    // Delegación para formularios con data-confirm
    document.addEventListener('submit', function (e) {
        const form = e.target;
        if (!(form instanceof HTMLFormElement)) return;
        if (!form.hasAttribute('data-confirm')) return;

        // Si ya se marcó como "confirmado" dejamos que se envíe normalmente
        if (form.dataset._confirmed === 'true') {
            // limpiar la marca para futuras acciones si quieres
            delete form.dataset._confirmed;
            return;
        }

        e.preventDefault();

        const title = form.dataset.confirmTitle || '¿Estás seguro?';
        const text = form.dataset.confirmText || '';
        const confirmText = form.dataset.confirmButton || 'Sí, eliminar';
        const cancelText = form.dataset.cancelButton || 'Cancelar';
        const icon = form.dataset.icon || 'warning';

        Swal.fire({
            title: title,
            text: text,
            icon: icon,
            showCancelButton: true,
            confirmButtonText: confirmText,
            cancelButtonText: cancelText,
            reverseButtons: true,
            customClass: {
                confirmButton: 'btn btn-danger',
                cancelButton: 'btn btn-secondary'
            }
        }).then(result => {
            if (result.isConfirmed) {
                // Evitar que el submit programático vuelva a interceptarse:
                // Opción A: marcar dataset y luego llamar submit
                form.dataset._confirmed = 'true';
                form.removeAttribute('data-confirm'); // doble seguro
                form.submit();
            }
        });
    });

    // Función para mostrar toasts desde elementos ocultos
    function showToastFromElement(el) {
        if (!el) return;
        const message = el.dataset.message;
        if (!message) return;
        const icon = el.dataset.icon || 'success';
        const title = el.dataset.title || '';

        Swal.fire({
            icon: icon,
            title: title,
            text: message,
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 2500,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer);
                toast.addEventListener('mouseleave', Swal.resumeTimer);
            }
        });
    }

    // Buscar los div ocultos generados por Thymeleaf
    showToastFromElement(document.getElementById('successToast'));
    showToastFromElement(document.getElementById('errorToast'));
    showToastFromElement(document.getElementById('warningToast'));
});

// ################## Graficos para home ##################
const salesCtx = document.getElementById('salesChart').getContext('2d');
new Chart(salesCtx, {
    type: 'line',
    data: {
        labels: ['Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb', 'Dom'],
        datasets: [{
            label: 'Esta Semana',
            data: [1200000, 1900000, 800000, 2100000, 1600000, 2400000, 1800000],
            borderColor: '#F97316',
            backgroundColor: 'rgba(249, 115, 22, 0.1)',
            tension: 0.4,
            fill: true
        }, {
            label: 'Semana Anterior',
            data: [1000000, 1700000, 900000, 1800000, 1400000, 2000000, 1500000],
            borderColor: '#CBD5E1',
            backgroundColor: 'transparent',
            tension: 0.4,
            fill: false
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                position: 'top',
            }
        },
        scales: {
            y: {
                beginAtZero: true,
                ticks: {
                    callback: function(value) {
                        return '$' + (value / 1000000).toFixed(1) + 'M';
                    }
                }
            }
        }
    }
});

// Gráfico de stock por categoría
const stockCtx = document.getElementById('stockChart').getContext('2d');
new Chart(stockCtx, {
    type: 'doughnut',
    data: {
        labels: ['Herramientas', 'Pinturas', 'Tornillería', 'Eléctricos', 'Plomería'],
        datasets: [{
            data: [35, 25, 20, 12, 8],
            backgroundColor: [
                '#F97316',
                '#5BBE83',
                '#F9AA33',
                '#3B82F6',
                '#8B5CF6'
            ],
            borderWidth: 0
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                position: 'bottom',
            }
        }
    }
});