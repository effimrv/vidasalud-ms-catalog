# VidaSalud MS Catalog

Microservicio de VidaSalud responsable de administrar el catálogo de prestaciones o servicios clínicos y la información institucional del centro. Utiliza Spring Boot, Spring Data JPA y PostgreSQL.

Matriz de funcionalidades y documentación del proyecto: [vidasalud-docs](https://github.com/effimrv/vidasalud-docs).

## Responsabilidades

- Listar prestaciones disponibles.
- Crear prestaciones.
- Actualizar prestaciones existentes.
- Mantener nombre, precio, box y cupos disponibles.
- Entregar y actualizar la información institucional del centro (descripción, teléfono, correo, horario y dirección) que muestra la página Nosotros.
- Persistir información en PostgreSQL/Supabase.

## Tecnologías

- Java 17.
- Spring Boot 3.2.5.
- Spring Web.
- Spring Data JPA e Hibernate.
- PostgreSQL JDBC Driver.
- Maven.

## Puerto y endpoints

El servicio utiliza el puerto `8082`.

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/catalog/services` | Lista las prestaciones. |
| POST | `/api/catalog/services` | Crea una prestación. |
| PUT | `/api/catalog/services/{id}` | Actualiza una prestación. |
| GET | `/api/info` | Información institucional del centro (se crea con valores iniciales si no existe). |
| PUT | `/api/info` | Actualiza la información institucional. |

## Ejecución local

Requisitos: Java 17, Maven y PostgreSQL accesible.

```bash
DB_PASSWORD='…' mvn spring-boot:run
mvn clean package
java -jar target/catalog-1.0.0.jar
```

`mvn clean package` también ejecuta las pruebas del controlador del catálogo.

## Base de datos

La conexión se configura en `src/main/resources/application.yml`. El servicio utiliza el pooler de Supabase mediante PostgreSQL y `prepareThreshold=0`.

**Contraseña de la base de datos:** no está en el repositorio. Se entrega con la variable de entorno `DB_PASSWORD` (en la EC2, desde el `.env` de `vidasalud-infra`). Para correrlo en local: `DB_PASSWORD='…' mvn spring-boot:run`. También se pueden cambiar `DB_URL` y `DB_USERNAME`.

## Docker

```bash
docker build -t catalog .
docker run -d --name catalog -p 8082:8082 -e DB_PASSWORD='…' catalog
```

## Verificación rápida

```bash
curl -i http://localhost:8082/api/catalog/services
```
