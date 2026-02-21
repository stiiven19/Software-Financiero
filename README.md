# Sistema Bancario - Software Financiero AP

![https://img.shields.io/badge/Java-8%2B-orange](https://img.shields.io/badge/Java-8%2B-orange)
![https://img.shields.io/badge/PostgreSQL-12%2B-blue](https://img.shields.io/badge/PostgreSQL-12%2B-blue)
![https://img.shields.io/badge/NetBeans-IDE-green](https://img.shields.io/badge/NetBeans-IDE-green)

Una aplicación bancaria completa desarrollada en Java con conexión a base de datos PostgreSQL para la gestión de préstamos, inversiones y clientes.

## 📋 Descripción General

El Software Financiero AP es un sistema integral de gestión bancaria que permite a los administradores bancarios gestionar clientes, préstamos, inversiones y garantías. El sistema cuenta con un menú interactivo y control de acceso mediante autenticación de usuarios.

## 🗄️ Base de Datos

### Configuración de PostgreSQL
- **Motor:** PostgreSQL
- **Base de datos:** `InversionesPrestamos`
- **Puerto:** `5432`
- **Usuario:** `postgres`
- **Contraseña:** `123`

### Requisitos
- PostgreSQL instalado y corriendo
- Base de datos `InversionesPrestamos` creada
- Driver JDBC de PostgreSQL

## 🔐 Autenticación y Seguridad

### Sistema de Login
- Solo usuarios autorizados (empleados del banco) pueden acceder
- Autenticación mediante credenciales almacenadas en la base de datos
- Control de sesiones para administradores

## 👥 Gestión de Clientes

### Registro de Clientes
- El administrador registra nuevos clientes en el sistema
- Información requerida:
  - Cédula de identidad
  - Nombres completos
  - Apellidos
  - Dirección
  - Teléfono

### Gestión de Cuentas Bancarias
- Cada cliente puede tener cuentas bancarias asociadas
- Las cuentas son necesarias para realizar inversiones

## 💰 Gestión de Préstamos

### Características de los Préstamos
- **Estados:** Aprobado, Rechazado, Pendiente
- **Requisitos obligatorios:**
  - Garantía O fiador (no ambos simultáneamente)
- **Tipos de garantía:**
  - Automóvil
  - Vivienda

### Proceso de Aprobación
1. Cliente solicita préstamo
2. Administrador revisa documentación
3. Se aprueba, rechaza o mantiene pendiente
4. Generación automática de cuotas de pago

### Sistema de Cuotas
- Cada préstamo genera cuotas mensuales
- Las cuotas incluyen capital e intereses
- Sistema de seguimiento de pagos

## 📈 Gestión de Inversiones

### Características de las Inversiones
- Los clientes pueden invertir dinero a plazo fijo
- Requieren cuenta bancaria asociada
- Generan intereses según tasa definida
- Sistema de cuotas para rendimientos

### Proceso de Inversión
1. Cliente con cuenta bancaria activa
2. Definición de monto y plazo
3. Cálculo automático de intereses
4. Generación de cuotas de rendimiento

## 🏠 Sistema de Garantías

### Tipos de Garantía
- **Automóvil:** Vehículos como garantía de préstamo
- **Vivienda:** Propiedades inmobiliarias como garantía

### Información de Garantía
- Código único de garantía
- Tipo (automóvil/vivienda)
- Valor comercial
- Ubicación/dirección

## 🤝 Sistema de Fiadores

### Requisitos del Fiador
- Debe ser un cliente registrado en el sistema
- Asume responsabilidad en caso de incumplimiento
- Verificación de capacidad crediticia

## 🖥️ Interfaz Gráfica

### Menú Principal Interactivo
- Acceso centralizado a todas las funcionalidades
- Navegación intuitiva entre módulos
- Diseño amigable para el administrador

### Módulos Principales
- Gestión de Clientes
- Gestión de Préstamos
- Gestión de Inversiones
- Consultas y Reportes
- Pagos de Cuotas

## 🚀 Instalación y Ejecución

### **Prerrequisitos**
- **JDK 8+** instalado
- **PostgreSQL 12+** corriendo
- **Apache Ant** (para compilación)
- **NetBeans IDE** (recomendado)

### **Pasos de Instalación**

1. **Configurar Base de Datos**
   - Instalar PostgreSQL
   - Crear base de datos: `CREATE DATABASE InversionesPrestamos;`
   - Ejecutar script: `database/creaciontablas.sql`
   - (Opcional) Importar datos CSV desde `database/data/`

2. **Configurar Driver PostgreSQL**
   - Descargar: https://jdbc.postgresql.org/download/
   - En NetBeans: Properties → Libraries → Add JAR/Folder
   - Agregar `postgresql-XX.X.X.jar`

3. **Login Inicial**
   - Usuario: `admin`
   - Contraseña: `admin123`

### **Configuración de Conexión**
- **Base de datos:** `InversionesPrestamos`
- **Puerto:** `5432`
- **Usuario:** `postgres`
- **Contraseña:** `123`

Para más detalles, ver: `database/README.md`

## 📋 Funcionalidades Completas

### ✅ Módulo de Administradores
- Login seguro
- Gestión de sesiones
- Control de accesos

### ✅ Módulo de Clientes
- Registro completo
- Modificación de datos
- Consulta de información
- Gestión de cuentas bancarias

### ✅ Módulo de Préstamos
- Solicitud de préstamos
- Aprobación/rechazo
- Gestión de garantías
- Gestión de fiadores
- Generación de cuotas
- Seguimiento de pagos

### ✅ Módulo de Inversiones
- Creación de inversiones
- Cálculo de intereses
- Generación de rendimientos
- Consulta de estados

### ✅ Módulo de Reportes
- Listado de préstamos
- Listado de inversiones
- Estados de cuentas
- Historial de transacciones

## 🖼️ **Capturas de Pantalla de la Aplicación**

### **Interfaz Principal**
- ![Menú Principal](images/screenshots/Inicio%20y%20Menu.webp) - inicio de sesión y Menú principal del sistema

### **Gestión de Clientes**
- ![Registro de Clientes](images/screenshots/Ver%20y%20Registrar%20Cliente.webp) - Formulario de registro y consulta de cliente

### **Gestión de Préstamos**
- ![Registrar Préstamo](images/screenshots/Registrar%20Prestamo.webp) - Formulario de registro de préstamos
- ![Ver Préstamos](images/screenshots/Ver%20Prestamo.webp) - Consulta y aprobación de préstamos

### **Gestión de Inversiones**
- ![Registrar Inversión](images/screenshots/Registrar%20Inversion.webp) - Formulario de registro de inversiones

### **Sistema de Cuotas**
- ![Cuotas de Pago](images/screenshots/Cuotas%20de%20Pago.webp) - Gestión de cuotas de préstamos e inversiones

## 🔧 Tecnologías Utilizadas

- **Lenguaje:** Java 8+
- **IDE:** NetBeans
- **Base de Datos:** PostgreSQL
- **Driver:** PostgreSQL JDBC
- **GUI:** Java Swing

## 📝 Notas Adicionales

- El sistema está diseñado para uso interno de instituciones bancarias
- Todas las transacciones quedan registradas en la base de datos
- El sistema incluye validaciones para evitar inconsistencias
- Las contraseñas y datos sensibles deben ser gestionados con seguridad adicional en producción

## 👨‍💻 Desarrollado por

Steven Guerrero - Ingeniero de Sistemas  
