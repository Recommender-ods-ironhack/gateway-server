# Gateway

## Descripción

El `API Gateway` actúa como punto de entrada único para todos los microservicios del sistema, enruta dinámicamente las solicitudes entrantes hacia los servicios correspondientes. Utiliza Spring Cloud Gateway y se integra con Eureka para el descubrimiento de servicios, asegurando escalabilidad, desacoplamiento y facilidad de mantenimiento.

## Características

- **Ruteo basado en path y método HTTP:** Enruta las solicitudes según el endpoint solicitado y el método HTTP utilizado.
- **Integración con Eureka:** Descubre automáticamente los servicios registrados mediante balanceo de carga (`lb://`).
- **Centralización de tráfico:** Maneja todas las solicitudes externas hacia los microservicios internos, manteniendo una arquitectura limpia y segura.
- **Modularidad:** Rutas organizadas por microservicio (`user`, `clothing-item-service` y `recommender-service`).

## Tecnologías Utilizadas

- [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Eureka (Service Discovery)](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html)
- [Maven](https://maven.apache.org/)

## Configuración de Rutas

La clase `GatewayConfig` define todas las rutas necesarias mediante programación Java (no YAML), agrupadas por microservicio.

### Rutas del Microservicio `user`

| Método | Path                | URI destino     |
|--------|---------------------|-----------------|
| DELETE | /api/user/**        | `lb://user`     |
| POST   | /api/user           | `lb://user`     |
| PATCH  | /api/user/**        | `lb://user`     |

### Rutas del Microservicio `clothing-item-service`

| Método | Path                       | URI destino                |
|--------|----------------------------|----------------------------|
| GET    | /api/clothing-item/**      | `lb://clothing-item-service` |
| DELETE | /api/clothing-item/**      | `lb://clothing-item-service` |
| POST   | /api/clothing-item         | `lb://clothing-item-service` |
| PATCH  | /api/clothing-item/**      | `lb://clothing-item-service` |

### Rutas del Microservicio `recommender-service`

| Método | Path                           | URI destino              |
|--------|--------------------------------|---------------------------|
| GET    | /api/items/discount            | `lb://recommender-service` |
| GET    | /api/item/**                   | `lb://recommender-service` |
| GET    | /api/user/**                   | `lb://recommender-service` |
| GET    | /api/items/filtered/**         | `lb://recommender-service` |

> **Nota:** Todas las rutas están registradas con descubrimiento de servicio a través de Eureka (`lb://...`).

## Configuración

El archivo `application.properties` ya viene incluido en el repositorio y configurado correctamente, por lo que no se requiere configuración adicional manual.

## Ejecución del Gateway

1. Asegúrate de que **Eureka** esté activo.
2. Verifica que los microservicios `user`, `clothing-item-service` y `recommender-service` estén registrados en Eureka.
3. Ejecuta la clase principal `GatewayApplication.java` desde tu IDE o con Maven:

```bash
mvn spring-boot:run
