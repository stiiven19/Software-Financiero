![https://img.shields.io/badge/PostgreSQL-12%2B-blue](https://img.shields.io/badge/PostgreSQL-12%2B-blue)

# Base de Datos - Software Financiero AP

## 📁 **Archivos SQL**

### **creaciontablas.sql**
- **Propósito:** Creación completa de la estructura de la base de datos
- **Contenido:** 
  - Creación de todas las tablas
  - Definición de restricciones y relaciones
  - Configuración de CHECK constraints
  - Inserción de usuarios iniciales
  - Configuración de estilo de fecha

### **Operaciones.sql**
- **Propósito:** Operaciones específicas y consultas SQL
- **Contenido:**
  - Operaciones CRUD específicas
  - Consultas personalizadas
  - Procedimientos de prueba

## **Datos de Prueba (CSV)**

### **data/** - Archivos CSV con datos iniciales
- **Clientes.csv** - Datos de clientes de prueba
- **Prestamos.csv** - Préstamos existentes
- **PrestamosEnProceso.csv** - Préstamos pendientes
- **Garantias.csv** - Garantías registradas
- **Cuotas.csv** - Cuotas de préstamos
- **CuentasBancarias.csv** - Cuentas bancarias de clientes
- **Inversiones.csv** - Inversiones activas
- **CuotasInversion.csv** - Cuotas de inversiones

### **Comandos COPY para importar:**
```sql
-- Importar datos desde CSV
COPY clientes FROM 'database/data/Clientes.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
COPY garantias FROM 'database/data/Garantias.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
COPY prestamos FROM 'database/data/Prestamos.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
COPY prestamos(ceduprestatario,codprestamo,fechsolicitudpre,fechaprobacionpre,fechiniciopre,interesprestamo,montoprestamo,estadoprestamo,fiador,numerocuotas) FROM 'database/data/PrestamosEnProceso.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
COPY cuotaprestamo FROM 'database/data/Cuotas.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
COPY cuentasbancarias FROM 'database/data/CuentasBancarias.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
COPY inversiones FROM 'database/data/Inversiones.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
COPY cuotainversion FROM 'database/data/CuotasInversion.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
```

### **Configuración de Conexión:**
- **Motor:** PostgreSQL
- **Puerto:** 5432
- **Usuario:** postgres
- **Contraseña:** 123
- **URL:** jdbc:postgresql://localhost:5432/InversionesPrestamos

## 🚀 **Instrucciones de Instalación Completa**

### **1. Prerrequisitos**
- **PostgreSQL 12+** instalado y corriendo
- **Driver JDBC de PostgreSQL** (postgresql-XX.X.X.jar)
- **NetBeans IDE** (recomendado para desarrollo)

### **2. Configurar Driver PostgreSQL**
1. **Descargar driver:** https://jdbc.postgresql.org/download/
2. **En NetBeans:** Right-click proyecto → Properties → Libraries → Add JAR/Folder
3. **Seleccionar:** `postgresql-XX.X.X.jar`

### **3. Crear Base de Datos**
```sql
-- Conectarse a PostgreSQL y ejecutar:
CREATE DATABASE InversionesPrestamos;
```

### **4. Ejecutar Script de Creación**
```sql
-- Conectarse a la base de datos InversionesPrestamos
-- Ejecutar el archivo completo: database/creaciontablas.sql
```

### **5. Importar Datos de Prueba (Opcional)**
```sql
-- Importar datos desde CSV (ejecutar en orden):
COPY clientes FROM 'database/data/Clientes.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
COPY garantias FROM 'database/data/Garantias.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
COPY prestamos FROM 'database/data/Prestamos.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
COPY prestamos(ceduprestatario,codprestamo,fechsolicitudpre,fechaprobacionpre,fechiniciopre,interesprestamo,montoprestamo,estadoprestamo,fiador,numerocuotas) FROM 'database/data/PrestamosEnProceso.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
COPY cuotaprestamo FROM 'database/data/Cuotas.csv' WITH (delimiter '|', encoding 'UTF-8', format 'csv');
```

### **6. Verificar Instalación**
```sql
-- Listar todas las tablas creadas:
\dt

-- Ver usuarios iniciales:
SELECT * FROM usuarios;

-- Ver datos de clientes:
SELECT * FROM clientes LIMIT 5;
```

### **7. Ejecutar Aplicación**
1. **Abrir proyecto en NetBeans**
2. **Clean and Build Project**
3. **Run Project** (F6)
4. **Login inicial:**
   - Usuario: `admin`
   - Contraseña: `admin123`

## 📋 **Estructura de Tablas**

- **clientes** - Información de clientes
- **usuarios** - Usuarios del sistema (login)
- **garantias** - Garantías de préstamos
- **prestamos** - Préstamos y estados
- **cuentasbancarias** - Cuentas bancarias
- **inversiones** - Inversiones de clientes
- **cuotaprestamo** - Cuotas de préstamos
- **cuotainversion** - Cuotas de inversiones

## 🔐 **Usuarios Iniciales**

| Usuario | Contraseña | Cargo |
|---------|------------|-------|
| admin | admin123 | Administrador |
| cajero | cajero123 | Cajero Bancario |
| supervisor | super123 | Supervisor |
| gerente | gerente123 | Gerente |

## 👨‍💻 Desarrollado por

Steven Guerrero - Ingeniero de Sistemas  
