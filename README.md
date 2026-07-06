# Ms_Categoria

Microservicio encargado de administrar categorias de juegos.

## Responsabilidad

- Crear categorias.
- Consultar categorias.
- Actualizar categorias.
- Eliminar categorias.
- Exponer enlaces HATEOAS hacia juegos relacionados.

## Datos tecnicos

| Item | Valor |
| --- | --- |
| Puerto | `8081` |
| Base de datos | `categorias_Juegos_db` |
| Ruta base | `/api/v2/categorias` |
| HATEOAS | `/api/v2/hateoas/categorias` |
| Swagger | `http://localhost:8081/doc/swagger-ui.html` |
| Eureka name | `ms-categoria` |

## Endpoints principales

- `GET /api/v2/categorias`
- `GET /api/v2/categorias/{id}`
- `POST /api/v2/categorias`
- `PUT /api/v2/categorias/{id}`
- `DELETE /api/v2/categorias/{id}`

## Datos demo

Incluye `DataInitializer` para cargar categorias base.

## Ejecucion local

```bash
./mvnw spring-boot:run
```

## Ejecucion con Docker

Desde la repo `Infraestructura`:

```bash
docker compose up -d --build ms-categoria
```

