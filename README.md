# VidaSalud MS Catalog

Microservicio de VidaSalud responsable de administrar el catálogo de prestaciones o servicios clínicos. Utiliza Spring Boot, Spring Data JPA y PostgreSQL.

## Responsabilidades

- Listar prestaciones disponibles.
- Crear prestaciones.
- Actualizar prestaciones existentes.
- Mantener nombre, precio, box y cupos disponibles.
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

## Ejecución local

Requisitos: Java 17, Maven y PostgreSQL accesible.

```bash
mvn spring-boot:run
mvn clean package -DskipTests
java -jar target/catalog-1.0.0.jar
```

## Base de datos

La conexión se configura en `src/main/resources/application.yml`. El servicio utiliza el pooler de Supabase mediante PostgreSQL y `prepareThreshold=0`.

No se deben publicar contraseñas ni credenciales reales. En producción se deben utilizar variables de entorno o un gestor de secretos.

## Docker

```bash
docker build -t catalog .
docker run -d --name catalog -p 8082:8082 catalog
```

## Verificación rápida

```bash
curl -i http://localhost:8082/api/catalog/services
```
