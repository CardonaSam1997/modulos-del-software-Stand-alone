## Descripción del proyecto
Se creo el modulo de usuarios ya que la actividad 
pide 1 modulo del proyecto, no se crean mas ya que se planteo 
el uso de otra tecnologia para el desarrollo del proyecto

## Tecnologías

1. Java + Spring Boot
2. Spring Data JPA
3. Base de datos (MySQL)
4. Maven

## Arquitectura
El proyecto sigue una arquitectura por capas:
1. **controller:** expone endpoints REST
2. **service:** lógica de negocio
3. **repository:** acceso a datos
4. **entity:** modelos persistentes
5. **dto:** transferencia de datos
6. **exception:** manejo de errores personalizado

## Funcionalidades principales

1. CRUD
2. Validaciones
3. Manejo de excepciones

## Librerias

- Spring Web
- Spring Data JPA
- MySQL Driver
- Lombok
- Validation
- Devtools

## Ejecución del proyecto

1. Tener MySQL instalado y en ejecución en el puerto 3306

**Crear la base de datos:**

```
CREATE DATABASE bussines_task;
```

2. Descomprimir el proyecto y abrirlo en el IDE.
3. Esperar a que Maven descargue las dependencias.
4. Configurar las credenciales de la base de datos en application.properties.
5. Ejecutar la clase principal del proyecto.
6. La tabla users se crea automáticamente al iniciar la aplicación.

## Endpoints

```
POST   /api/usuarios        -> Crear usuario
GET    /api/usuarios        -> Listar usuarios
GET    /api/usuarios/{id}   -> Obtener usuario por ID
GET    /api/usuarios/buscar -> Buscar usuario por email o username
PUT    /api/usuarios/{id}   -> Actualizar usuario
DELETE /api/usuarios/{id}   -> Eliminar usuario
```