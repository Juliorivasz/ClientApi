# API de Gestión de Usuarios

Esta API permite gestionar usuarios, incluyendo el registro, el inicio de sesión y la obtención de la lista de usuarios existentes. Se utilizan JWT (JSON Web Tokens) para la autenticación.

## Endpoints

### 1. **Registrar un nuevo usuario**

**Método:** `POST`  
**Ruta:** `/api/client/register`  
**Descripción:** Este endpoint permite registrar un nuevo usuario en el sistema. El cuerpo de la solicitud debe contener un objeto `UserDTO` con la información del usuario.

**Cuerpo de la solicitud (JSON):**

```json
{
  "email": "usuario@example.com",
  "password": "password123",
  "name": "Juan",
  "lastName": "Pérez"
}
```
#### Respuesta exitosa (200):
```json
{
  "message": "Usuario registrado exitosamente"
}
```

#### Respuesta en caso de conflicto (409):
```json
{
  "message": "Usuario registrado exitosamente"
}
```

### 2. **Iniciar Sesion**

**Método:** `POST`  
**Ruta:** `/api/client/login`  
**Descripción:** Este endpoint permite que un usuario inicie sesión utilizando su email y contraseña. Si las credenciales son correctas, se devuelve un token JWT.

**Cuerpo de la solicitud (JSON):**

```json
{
  "email": "usuario@example.com",
  "password": "password123"
}
```
### Respuesta exitosa (200):
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c3VhcmlvQGV4YW1wbGUuY29tIiwiaWF0IjoxNjIzNzc3MjM5LCJleHBpcnkiOjE2MjM3ODk4Mzl9.9LZfPYWqxQKPbbXldbdLVGgd6bHqRbSoUlqDkJmzrD8"
}
```

### Respuesta en caso de credenciales inválidas (401):
```json
{
  "message": "Credenciales inválidas"
}
```

### 3. **Obtener todos los usuarios**

**Método:** `GET`  
**Ruta:** `/api/client/`  
**Descripción:** Este endpoint devuelve una lista de todos los usuarios registrados, incluyendo su id y email. Este endpoint no requiere autenticación (token JWT).

### Respuesta exitosa (200):
```json
[
  {
    "id": 1,
    "email": "usuario1@example.com"
  },
  {
    "id": 2,
    "email": "usuario2@example.com"
  }
]

```

### Respuesta en caso de no haber usuarios registrados (404):
```json
{
  "message": "No hay usuarios registrados."
}
```

## Informativo 
### **Autenticación**
Para acceder a los endpoints protegidos (hasta los momentos no hay), es necesario enviar un token JWT en los encabezados de la solicitud. Este token se obtiene al iniciar sesión con las credenciales correctas.

### Ejemplo de encabezado con token JWT (http): 
```json
{
  "Authorization": "Bearer <your-jwt-token>"
}
```