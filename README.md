# Clínica - Sistema de ventas (Entrega final)

Proyecto final del curso de Java de Coderhouse. Es una API REST hecha con Spring Boot para administrar las ventas de una clínica.

La consigna pedía un comercio con clientes, productos y ventas. Yo elegí una clínica, así que:

|Consigna|Mi proyecto|
|-|-|
|Cliente|Paciente|
|Producto|Prestación|
|Stock|Cantidad disponible de la prestación|
|Venta|Comprobante|

## Tecnologías

* Java 17
* Spring Boot 4.1.1 (Web MVC y Spring Data JPA)
* Base de datos H2 en memoria
* Maven
* Postman para las pruebas

## Cómo ejecutarlo

Hace falta tener Java 17 o superior.

Con el jar que está en la carpeta `entrega`:

```
java -jar entrega/FacturacionEntregaProyectoFinalCascallana.jar
```

Si se quiere generar el jar de nuevo:

```
mvn clean package
```

(o `./mvnw clean package`). El jar queda en la carpeta `target`.

La aplicación queda corriendo en `http://localhost:8080`.

La base H2 es en memoria, así que cada vez que se cierra la aplicación se pierden los datos. Las tablas se crean solas al arrancar. Para ver las tablas se puede entrar a `http://localhost:8080/h2-console` con estos datos:

* JDBC URL: `jdbc:h2:mem:clinicadb`
* Usuario: `sa`
* Contraseña: (vacía)

## Estructura

```
src/main/java/com/clinica
├── model        entidades (Paciente, Prestacion, ListaPrecio, DetalleListaPrecio, Comprobante, LineaComprobante)
├── repository   interfaces JpaRepository
├── service      lógica y validaciones
└── controller   endpoints REST
entrega/         jar ejecutable
postman/         colección de Postman
scripts/         script de creación de tablas (crear\_tablas.sql)
```

## Endpoints

|Ruta|POST|GET (lista)|GET por id|PUT|DELETE|
|-|-|-|-|-|-|
|`/paciente`|Sí|Sí|Sí|Sí|Sí|
|`/prestacion`|Sí|Sí|Sí|Sí|Sí|
|`/listaprecio`|Sí|Sí|Sí|Sí|Sí|
|`/detallelistaprecio`|Sí|Sí|Sí|Sí|Sí|
|`/comprobante`|Sí|Sí|Sí|No|No|

Para actualizar o eliminar se usa el id en la ruta, por ejemplo `PUT /paciente/1` o `DELETE /paciente/1`.

A los comprobantes no se les puede modificar ni borrar porque son registros de ventas ya hechas.

### Ejemplos de body

Paciente:

```json
{
  "nombre": "Juan Perez",
  "domicilio": "Calle 123",
  "celular": "2235551234",
  "fechaNacimiento": "1990-05-10"
}
```

Prestación (el `stock` es la cantidad disponible):

```json
{
  "descripcion": "Consulta clinica",
  "categoria": "Consultas",
  "stock": 10
}
```

Lista de precios:

```json
{
  "descripcion": "Lista 2026",
  "vigencia": "2026-01-01"
}
```

Detalle de lista de precios (el precio de una prestación):

```json
{
  "listaPrecio": { "id": 1 },
  "prestacion": { "id": 1 },
  "precio": 1000.0
}
```

Comprobante:

```json
{
  "paciente": { "id": 1 },
  "lineas": \[
    {
      "cantidad": 2,
      "prestacion": { "id": 1 }
    }
  ]
}
```

## Qué hace al crear un comprobante

1. Verifica que el paciente exista.
2. Verifica que cada prestación exista y que la cantidad sea mayor a 0.
3. Verifica que la cantidad pedida no supere el stock de la prestación.
4. Si todo está bien, descuenta el stock.
5. Calcula el precio total y la cantidad de productos.
6. Guarda la fecha. Se pide a `http://worldclockapi.com/api/json/utc/now` y, si ese servicio falla, se usa la fecha de la computadora con la clase `Date`.

Si alguna validación falla, la respuesta es un error 400 con un mensaje que explica el motivo, y no se descuenta nada del stock.

### Precio histórico

Cada línea del comprobante guarda el precio que tenía la prestación en el momento de la venta (`precioUnitario`). Por eso, si después se carga un precio nuevo, los comprobantes anteriores no cambian.

El precio actual de una prestación es el último detalle de lista de precios que se cargó para ella.

## Cómo probarlo

1. Importar en Postman el archivo de la carpeta `postman`.
2. Iniciar la aplicación (así la base está vacía y los ids empiezan en 1).
3. Ejecutar las requests en orden, de la 01 a la 23.

Las primeras 12 prueban el flujo del comprobante: alta de datos, venta correcta, errores de validación y cambio de precio. Las demás prueban listar, actualizar y eliminar.

## Script de tablas

Está en `scripts/crear\_tablas.sql`. Es el mismo esquema que genera la aplicación al arrancar. 

