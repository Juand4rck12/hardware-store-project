-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.40 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para hardwarestore_db
CREATE DATABASE IF NOT EXISTS `hardwarestore_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hardwarestore_db`;

-- Volcando estructura para tabla hardwarestore_db.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `id_customer` bigint NOT NULL AUTO_INCREMENT,
  `document` bigint NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT (now()),
  `updated_at` timestamp NOT NULL DEFAULT (now()),
  PRIMARY KEY (`id_customer`) USING BTREE,
  UNIQUE KEY `document` (`document`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla hardwarestore_db.customer: ~10 rows (aproximadamente)
INSERT INTO `customer` (`id_customer`, `document`, `name`, `phone`, `address`, `email`, `created_at`, `updated_at`) VALUES
	(1, 10000000, 'Juan Pérez García', '3101234567', 'Calle 12 # 34-56 Bogotá', 'juan.perez@example.com', '2025-08-02 14:20:19', '2025-08-02 14:20:19'),
	(2, 10000001, 'María López Rodríguez', '3119876543', 'Carrera 15 # 78-90 Medellín', 'maria.lopez@example.com', '2025-08-02 14:20:19', '2025-08-02 14:20:19'),
	(3, 10000002, 'Carlos Gómez Torres', '3125556677', 'Avenida 45 # 12-34 Cali', 'carlos.gomez@example.com', '2025-08-02 14:20:19', '2025-08-02 14:20:19'),
	(4, 10000003, 'Ana Martínez Castro', '3134445566', 'Diagonal 23 # 45-67 Barranquilla', 'ana.martinez@example.com', '2025-08-02 14:20:19', '2025-08-02 14:20:19'),
	(5, 10000004, 'Luis González Díaz', '3148889900', 'Transversal 7 # 89-01 Cartagena', 'luis.gonzalez@example.com', '2025-08-02 14:20:19', '2025-08-02 14:20:19'),
	(6, 10000005, 'Sofía Hernández Ríos', '3151112233', 'Circular 10 # 23-45 Bucaramanga', 'sofia.hernandez@example.com', '2025-08-02 14:20:19', '2025-08-02 14:20:19'),
	(7, 10000006, 'Diego Sánchez Mejía', '3167778899', 'Calle 56 # 78-90 Pereira', 'diego.sanchez@example.com', '2025-08-02 14:20:19', '2025-08-02 14:20:19'),
	(8, 10000007, 'Valentina Romero Guzmán', '3176665544', 'Avenida 3 # 45-67 Cúcuta', 'valentina.romero@example.com', '2025-08-02 14:20:19', '2025-08-02 14:20:19'),
	(9, 10000008, 'Javier Acosta Pinto', '3183334455', 'Carrera 25 # 67-89 Santa Marta', 'javier.acosta@example.com', '2025-08-02 14:20:19', '2025-08-02 14:20:19'),
	(10, 10000009, 'Camila Torres Salazar', '3192223344', 'Diagonal 14 # 56-78 Manizales', 'camila.torres@example.com', '2025-08-02 14:20:19', '2025-08-02 14:20:19');

-- Volcando estructura para tabla hardwarestore_db.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `id_employee` bigint NOT NULL AUTO_INCREMENT,
  `document` bigint NOT NULL,
  `name` varchar(100) NOT NULL,
  `position` varchar(50) NOT NULL,
  `salary` decimal(10,2) NOT NULL,
  `role` varchar(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT (now()),
  `updated_at` timestamp NOT NULL DEFAULT (now()),
  PRIMARY KEY (`id_employee`),
  UNIQUE KEY `document` (`document`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla hardwarestore_db.employee: ~10 rows (aproximadamente)
INSERT INTO `employee` (`id_employee`, `document`, `name`, `position`, `salary`, `role`, `created_at`, `updated_at`) VALUES
	(1, 20000000, 'Pedro Gutiérrez', 'Gerente', 3500000.00, 'admin', '2025-08-02 14:20:34', '2025-08-02 14:20:34'),
	(2, 20000001, 'Laura Ramírez', 'Vendedor', 1800000.00, 'employee', '2025-08-02 14:20:34', '2025-08-02 14:20:34'),
	(3, 20000002, 'Andrés Castro', 'Repositor', 1500000.00, 'employee', '2025-08-02 14:20:34', '2025-08-02 14:20:34'),
	(4, 20000003, 'Gabriela Páez', 'Cajero', 1600000.00, 'employee', '2025-08-02 14:20:34', '2025-08-02 14:20:34'),
	(5, 20000004, 'Mario López', 'Técnico', 2200000.00, 'employee', '2025-08-02 14:20:34', '2025-08-02 14:20:34'),
	(6, 20000005, 'Isabel Gómez', 'Supervisor', 2500000.00, 'admin', '2025-08-02 14:20:34', '2025-08-02 14:20:34'),
	(7, 20000006, 'Daniel Rodríguez', 'Almacenero', 1700000.00, 'employee', '2025-08-02 14:20:34', '2025-08-02 14:20:34'),
	(8, 20000007, 'Carolina Méndez', 'Asistente', 1900000.00, 'employee', '2025-08-02 14:20:34', '2025-08-02 14:20:34'),
	(9, 20000008, 'Felipe González', 'Mensajero', 1400000.00, 'employee', '2025-08-02 14:20:34', '2025-08-02 14:20:34'),
	(10, 20000009, 'Natalia Díaz', 'Contador', 2800000.00, 'admin', '2025-08-02 14:20:34', '2025-08-02 14:20:34');

-- Volcando estructura para tabla hardwarestore_db.order_detail
CREATE TABLE IF NOT EXISTS `order_detail` (
  `id_detail` bigint NOT NULL AUTO_INCREMENT,
  `id_order` bigint NOT NULL,
  `id_product` bigint NOT NULL,
  `quantity` int NOT NULL DEFAULT (0),
  `unit_price` decimal(10,2) NOT NULL DEFAULT (0),
  `subtotal` decimal(10,2) NOT NULL DEFAULT (0),
  PRIMARY KEY (`id_detail`),
  KEY `FK__purchase_order` (`id_order`),
  KEY `FK_product` (`id_product`),
  CONSTRAINT `FK__purchase_order` FOREIGN KEY (`id_order`) REFERENCES `purchase_order` (`id_order`),
  CONSTRAINT `FK_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla hardwarestore_db.order_detail: ~10 rows (aproximadamente)
INSERT INTO `order_detail` (`id_detail`, `id_order`, `id_product`, `quantity`, `unit_price`, `subtotal`) VALUES
	(1, 1, 1, 2, 250000.00, 500000.00),
	(2, 2, 2, 1, 85000.00, 85000.00),
	(3, 3, 3, 5, 25000.00, 125000.00),
	(4, 4, 4, 2, 45000.00, 90000.00),
	(5, 5, 5, 3, 35000.00, 105000.00),
	(6, 6, 6, 1, 15000.00, 15000.00),
	(7, 7, 7, 10, 5000.00, 50000.00),
	(8, 8, 8, 100, 8000.00, 800000.00),
	(9, 9, 9, 2, 32000.00, 64000.00),
	(10, 10, 10, 5, 12000.00, 60000.00);

-- Volcando estructura para tabla hardwarestore_db.product
CREATE TABLE IF NOT EXISTS `product` (
  `id_product` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `category` varchar(50) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock_quantity` int NOT NULL,
  `min_stock_level` int NOT NULL,
  `supplier_id` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT (now()),
  `updated_at` timestamp NOT NULL DEFAULT (now()),
  PRIMARY KEY (`id_product`),
  UNIQUE KEY `name` (`name`),
  KEY `FK__supplier` (`supplier_id`),
  CONSTRAINT `FK__supplier` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id_supplier`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla hardwarestore_db.product: ~10 rows (aproximadamente)
INSERT INTO `product` (`id_product`, `name`, `category`, `price`, `stock_quantity`, `min_stock_level`, `supplier_id`, `created_at`, `updated_at`) VALUES
	(1, 'Taladro Inalámbrico 12V', 'Herramientas Eléctricas', 250000.00, 25, 10, 1, '2025-08-02 14:21:23', '2025-08-02 14:21:23'),
	(2, 'Pintura Látex Blanca 4L', 'Pinturas', 85000.00, 50, 20, 4, '2025-08-02 14:21:23', '2025-08-02 14:21:23'),
	(3, 'Cinta Métrica 5m', 'Herramientas Manuales', 25000.00, 100, 30, 1, '2025-08-02 14:21:23', '2025-08-02 14:21:23'),
	(4, 'Kit de Destornilladores', 'Herramientas Manuales', 45000.00, 40, 20, 6, '2025-08-02 14:21:23', '2025-08-02 14:21:23'),
	(5, 'Cable Eléctrico 10 AWG', 'Electricidad', 35000.00, 200, 50, 3, '2025-08-02 14:21:23', '2025-08-02 14:21:23'),
	(6, 'Bombillo LED 12W', 'Iluminación', 15000.00, 150, 50, 3, '2025-08-02 14:21:23', '2025-08-02 14:21:23'),
	(7, 'Lija Grano 120', 'Ferretería General', 5000.00, 300, 100, 2, '2025-08-02 14:21:23', '2025-08-02 14:21:23'),
	(8, 'Clavos de Acero 2"', 'Ferretería General', 8000.00, 1000, 500, 6, '2025-08-02 14:21:23', '2025-08-02 14:21:23'),
	(9, 'Martillo Carpintero', 'Herramientas Manuales', 32000.00, 30, 15, 8, '2025-08-02 14:21:23', '2025-08-02 14:21:23'),
	(10, 'Tornillo Hexagonal 1/2"', 'Ferretería General', 12000.00, 500, 200, 6, '2025-08-02 14:21:23', '2025-08-02 14:21:23');

-- Volcando estructura para tabla hardwarestore_db.purchase_order
CREATE TABLE IF NOT EXISTS `purchase_order` (
  `id_order` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint NOT NULL,
  `employee_id` bigint NOT NULL,
  `total_amount` decimal(10,2) NOT NULL DEFAULT (0),
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `order_date` timestamp NOT NULL DEFAULT (now()),
  `created_at` timestamp NOT NULL DEFAULT (now()),
  PRIMARY KEY (`id_order`),
  KEY `FK_purchase_order_customer` (`customer_id`),
  KEY `FK_purchase_order_employee` (`employee_id`),
  CONSTRAINT `FK_purchase_order_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id_customer`),
  CONSTRAINT `FK_purchase_order_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla hardwarestore_db.purchase_order: ~10 rows (aproximadamente)
INSERT INTO `purchase_order` (`id_order`, `customer_id`, `employee_id`, `total_amount`, `status`, `order_date`, `created_at`) VALUES
	(1, 1, 1, 500000.00, 'PENDIENTE', '2025-08-02 14:21:39', '2025-08-02 14:21:39'),
	(2, 2, 2, 120000.00, 'COMPLETADO', '2025-08-02 14:21:39', '2025-08-02 14:21:39'),
	(3, 3, 3, 250000.00, 'EN_PROCESO', '2025-08-02 14:21:39', '2025-08-02 14:21:39'),
	(4, 4, 4, 85000.00, 'PENDIENTE', '2025-08-02 14:21:39', '2025-08-02 14:21:39'),
	(5, 5, 5, 45000.00, 'COMPLETADO', '2025-08-02 14:21:39', '2025-08-02 14:21:39'),
	(6, 6, 6, 35000.00, 'EN_PROCESO', '2025-08-02 14:21:39', '2025-08-02 14:21:39'),
	(7, 7, 7, 15000.00, 'PENDIENTE', '2025-08-02 14:21:39', '2025-08-02 14:21:39'),
	(8, 8, 8, 5000.00, 'COMPLETADO', '2025-08-02 14:21:39', '2025-08-02 14:21:39'),
	(9, 9, 9, 8000.00, 'EN_PROCESO', '2025-08-02 14:21:39', '2025-08-02 14:21:39'),
	(10, 10, 10, 12000.00, 'PENDIENTE', '2025-08-02 14:21:39', '2025-08-02 14:21:39');

-- Volcando estructura para tabla hardwarestore_db.sale
CREATE TABLE IF NOT EXISTS `sale` (
  `id_sale` bigint NOT NULL AUTO_INCREMENT,
  `id_customer` bigint NOT NULL,
  `id_employee` bigint NOT NULL,
  `total_amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `sale_date` timestamp NOT NULL DEFAULT (now()),
  `created_at` timestamp NOT NULL DEFAULT (now()),
  PRIMARY KEY (`id_sale`),
  KEY `FK__customer` (`id_customer`),
  KEY `FK__employee` (`id_employee`),
  CONSTRAINT `FK__customer` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id_customer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK__employee` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id_employee`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla hardwarestore_db.sale: ~10 rows (aproximadamente)
INSERT INTO `sale` (`id_sale`, `id_customer`, `id_employee`, `total_amount`, `sale_date`, `created_at`) VALUES
	(1, 1, 1, 250000.00, '2025-08-02 14:21:58', '2025-08-02 14:21:58'),
	(2, 2, 2, 170000.00, '2025-08-02 14:21:58', '2025-08-02 14:21:58'),
	(3, 3, 3, 75000.00, '2025-08-02 14:21:58', '2025-08-02 14:21:58'),
	(4, 4, 4, 45000.00, '2025-08-02 14:21:58', '2025-08-02 14:21:58'),
	(5, 5, 5, 105000.00, '2025-08-02 14:21:58', '2025-08-02 14:21:58'),
	(6, 6, 6, 35000.00, '2025-08-02 14:21:58', '2025-08-02 14:21:58'),
	(7, 7, 7, 15000.00, '2025-08-02 14:21:58', '2025-08-02 14:21:58'),
	(8, 8, 8, 5000.00, '2025-08-02 14:21:58', '2025-08-02 14:21:58'),
	(9, 9, 9, 8000.00, '2025-08-02 14:21:58', '2025-08-02 14:21:58'),
	(10, 10, 10, 12000.00, '2025-08-02 14:21:58', '2025-08-02 14:21:58');

-- Volcando estructura para tabla hardwarestore_db.sale_detail
CREATE TABLE IF NOT EXISTS `sale_detail` (
  `id_detail` bigint NOT NULL AUTO_INCREMENT,
  `id_sale` bigint NOT NULL,
  `id_product` bigint NOT NULL,
  `quantity` int NOT NULL,
  `unit_price` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_detail`),
  KEY `FK_detail_product` (`id_product`),
  KEY `FK__detail_sale` (`id_sale`),
  CONSTRAINT `FK__detail_sale` FOREIGN KEY (`id_sale`) REFERENCES `sale` (`id_sale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_detail_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla hardwarestore_db.sale_detail: ~10 rows (aproximadamente)
INSERT INTO `sale_detail` (`id_detail`, `id_sale`, `id_product`, `quantity`, `unit_price`, `subtotal`) VALUES
	(1, 1, 1, 1, 250000.00, 250000.00),
	(2, 2, 2, 2, 85000.00, 170000.00),
	(3, 3, 3, 3, 25000.00, 75000.00),
	(4, 4, 4, 1, 45000.00, 45000.00),
	(5, 5, 5, 3, 35000.00, 105000.00),
	(6, 6, 6, 1, 15000.00, 15000.00),
	(7, 7, 7, 10, 5000.00, 50000.00),
	(8, 8, 8, 100, 8000.00, 800000.00),
	(9, 9, 9, 2, 32000.00, 64000.00),
	(10, 10, 10, 5, 12000.00, 60000.00);

-- Volcando estructura para tabla hardwarestore_db.supplier
CREATE TABLE IF NOT EXISTS `supplier` (
  `id_supplier` bigint NOT NULL AUTO_INCREMENT,
  `document` bigint NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT (now()),
  `updated_at` timestamp NOT NULL DEFAULT (now()),
  PRIMARY KEY (`id_supplier`) USING BTREE,
  UNIQUE KEY `document` (`document`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla hardwarestore_db.supplier: ~10 rows (aproximadamente)
INSERT INTO `supplier` (`id_supplier`, `document`, `name`, `phone`, `email`, `created_at`, `updated_at`) VALUES
	(1, 30000000, 'Ferretería López', '3151234567', 'contacto@ferreterialopez.com', '2025-08-02 14:20:48', '2025-08-02 14:20:48'),
	(2, 30000001, 'ConstruMateriales S.A.', '3169876543', 'ventas@construmateriales.co', '2025-08-02 14:20:48', '2025-08-02 14:20:48'),
	(3, 30000002, 'Electricidad El Volta', '3175556677', 'info@elvolta.com', '2025-08-02 14:20:48', '2025-08-02 14:20:48'),
	(4, 30000003, 'Pinturas Andinas', '3184445566', 'servicio@pinturasandinas.co', '2025-08-02 14:20:48', '2025-08-02 14:20:48'),
	(5, 30000004, 'Herramientas El Torquímetro', '3198889900', 'ventas@eltorquimetro.com', '2025-08-02 14:20:48', '2025-08-02 14:20:48'),
	(6, 30000005, 'Suministros Industriales SAS', '3201112233', 'atencion@suministrosind.com', '2025-08-02 14:20:48', '2025-08-02 14:20:48'),
	(7, 30000006, 'Tornillería Universal', '3217778899', 'ventas@tornilleriauniversal.co', '2025-08-02 14:20:48', '2025-08-02 14:20:48'),
	(8, 30000007, 'Cerrajería La Llave', '3226665544', 'contactenos@cerrajerialallave.com', '2025-08-02 14:20:48', '2025-08-02 14:20:48'),
	(9, 30000008, 'Maderas del Pacífico', '3233334455', 'info@maderaspacifico.co', '2025-08-02 14:20:48', '2025-08-02 14:20:48'),
	(10, 30000009, 'Ferretería El Martillo', '3242223344', 'ventas@ferreteriaelmartillo.com', '2025-08-02 14:20:48', '2025-08-02 14:20:48');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
