# TallerSpringBoot_Posgres_UPTC

Este proyecto es una aplicación de ejemplo desarrollada con Spring Boot que utiliza una base de datos PostgreSQL alojada en supabase. La aplicación gestiona información sobre escuelas, estudiantes y materias.

## Requisitos

- Java 8 o superior
- Maven
- PostgreSQL

## Ejecución de la aplicación

1. Clona este repositorio
2. Navega hasta el directorio del proyecto: `cd TallerSpringBoot_Posgres_UPTC`
3. Ejecuta la aplicación: `mvn spring-boot:run`
4. Ruta en local sera: `http://localhost:3000/`
5. Ruta de la aplicacion en la nube: `https://tallerspringboot-posgres-uptc.onrender.com/docs`

<span style="text-decoration: underline;">_Nota:_ En el despliegue en Render, al utilizar el plan gratuito, surge el inconveniente de que el servidor se apaga después de un período de inactividad. Por lo tanto, puede ser necesario esperar unos momentos después de acceder al enlace para que el servidor se reinicie.</span>


## Base de datos
La base de datos utilizada en este proyecto es PostgreSQL. La configuración de la base de datos se encuentra en el archivo `application.properties`. La base de datos se encuentra alojada en supabase.

### Diagrama entidad-relación sin normalizar
[![Diagrama-Data-Base-Sin-Normalizar.png](https://i.postimg.cc/NM5GcgH4/Diagrama-Data-Base-Sin-Normalizar.png)](https://postimg.cc/jwYY6Yvw)

### Diagrama entidad-relación normalizado
[![Diagrama-Data-Base-Normalizado.png](https://i.postimg.cc/0jZqMFDQ/Diagrama-Data-Base-Normalizado.png)](https://postimg.cc/sGBLbnnd)

## Diagrama de clases
[![Diagrama-de-clases.png](https://i.postimg.cc/CK0JX193/Diagrama-de-clases.png)](https://postimg.cc/qNm8CkJw)

## Diagrama de componentes
[![Diagrama-Componentes.png](https://i.postimg.cc/Zqf7NmDX/Diagrama-Componentes.png)](https://postimg.cc/Y4Wzc57x)

## Diagrama de despliegue
[![Diagrama-Despliegue.png](https://i.postimg.cc/L6dyLNN4/Diagrama-Despliegue.png)](https://postimg.cc/t75tHdK8)

## Documentación de los Endpoints

### SchoolsController

#### GET /schools

Obtiene la lista de todas las escuelas.

#### GET /schools/{id}

Obtiene una escuela por su ID.

Ejemplo de solicitud:
GET /schools/1


#### POST /schools

Crea una nueva escuela.

Ejemplo de solicitud:
POST /schools
```json
{
  "name": "string",
  "address": "string",
  "email": "string",
  "description": "string"
}
```

#### PUT /schools/{id}

Actualiza una escuela existente por su ID.

Ejemplo de solicitud:
PUT /schools/1
```json
{
  "name": "string",
  "address": "string",
  "email": "string",
  "description": "string"
}
```

#### DELETE /schools/{id}

Elimina una escuela por su ID.

Ejemplo de solicitud:
DELETE /schools/1

<br>

### StudentController

#### GET /students

Obtiene la lista de todos los estudiantes.

#### GET /students/{id}

Obtiene un estudiante por su ID.

Ejemplo de solicitud: GET /students/1


#### POST /students

Crea un nuevo estudiante.

Ejemplo de solicitud:
POST /students
```json
{
  "name": "string",
  "lastName": "string",
  "birthDate": "2024-02-25",
  "email": "string",
  "phone": "string"
}
```

#### PUT /students/{id}

Actualiza un estudiante existente por su ID.

Ejemplo de solicitud: PUT /students/1
```json
{
  "name": "string",
  "lastName": "string",
  "birthDate": "2024-02-25",
  "email": "string",
  "phone": "string"
}
```

#### DELETE /students/{id}

Elimina un estudiante por su ID.

Ejemplo de solicitud:
DELETE /students/1

#### GET /students/{id}/subjects/school/
Optiene las materias de un estudiante por su ID la escuela a la que pertenecen.

Ejemplo de solicitud: GET /students/1/subjects/school


#### POST /students/{id}/subjects/{idSubject}
Agrega una materia a un estudiante por su ID y la materia por su ID.
Ejemplo de solicitud: POST /students/1/subjects/1

#### DELETE /students/{id}/subjects/{idSubject}
Elimina una materia de un estudiante por su ID y la materia por su ID.
Ejemplo de solicitud: DELETE /students/1/subjects/1

<br>

### SubjectController

#### GET /subjects
Obtiene la lista de todas las materias.
Ejemplo de solicitud: GET /subjects

#### GET /subjects/{id}
Obtiene una materia por su ID.
Ejemplo de solicitud: GET /subjects/1

#### POST /subjects/{idSchool}
Crea una nueva materia, se debe especificar la escuela a la que pertenece.
Ejemplo de solicitud: POST /subjects
```json
{
  "name": "String",
  "description": "String",
  "credits": 0
}
```

#### PUT /subjects/{id}
Actualiza una materia existente por su ID.
Ejemplo de solicitud: PUT /subjects/1
```json
{
  "name": "Fisica",
  "description": "Escuela de fisica",
  "credits": 3
}
```

#### DELETE /subjects/{id}
Elimina una materia por su ID.
Ejemplo de solicitud: DELETE /subjects/1

#### GET /subjects/{id}/students
Obtiene los estudiantes que estan matriculados en una materia por su ID.
Ejemplo de solicitud: GET /subjects/1/students

#### GET /subjects/{schoolId}/school
Obtiene las materias de una escuela por su ID.
Ejemplo de solicitud: GET /subjects/1/school

<br>

### School-directorController

#### GET /school-director
Obtiene la lista del directore de escuela.
Ejemplo de solicitud: GET /school-director

#### GET /school-director/{id}
Obtiene un director de escuela por su ID y muestra la escuela a la que pertenece.
Ejemplo de solicitud: GET /school-director/1

#### POST /school-director/{idSchool}
Crea un nuevo director de escuela.
Ejemplo de solicitud: POST /school-director/1
```json
{
  "name": "string",
  "lastName": "string",
  "birthDate": "2024-02-25"
}
```

#### PUT /school-director/{id}
Actualiza un director de escuela existente por su ID.
Ejemplo de solicitud: PUT /school-director/1
```json
{
  "name": "string",
  "lastName": "string",
  "birthDate": "2024-02-25"
}
```

#### DELETE /school-director/{id}
Elimina un director de escuela por su ID.
Ejemplo de solicitud: DELETE /school-director/1



