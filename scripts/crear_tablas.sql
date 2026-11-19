-- Script de creacion de tablas - Clinica
-- Base de datos: H2 (la misma del proyecto)

CREATE TABLE paciente (
    id_paciente      BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre           VARCHAR(255),
    domicilio        VARCHAR(255),
    celular          VARCHAR(255),
    fecha_nacimiento DATE
);

CREATE TABLE prestacion (
    id_prestacion BIGINT AUTO_INCREMENT PRIMARY KEY,
    descripcion   VARCHAR(255),
    categoria     VARCHAR(255),
    stock         INTEGER NOT NULL
);

CREATE TABLE lista_precio (
    id_lista_precio BIGINT AUTO_INCREMENT PRIMARY KEY,
    descripcion     VARCHAR(255),
    vigencia        DATE
);

CREATE TABLE detalle_lista_precio (
    id_detalle_lista_precio     BIGINT AUTO_INCREMENT PRIMARY KEY,
    precio                      DOUBLE PRECISION NOT NULL,
    lista_precio_id_lista_precio BIGINT,
    prestacion_id_prestacion    BIGINT,
    FOREIGN KEY (lista_precio_id_lista_precio) REFERENCES lista_precio (id_lista_precio),
    FOREIGN KEY (prestacion_id_prestacion) REFERENCES prestacion (id_prestacion)
);

CREATE TABLE comprobante (
    id_comprobante       BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha                TIMESTAMP,
    total                DOUBLE PRECISION NOT NULL,
    cantidad_productos   INTEGER NOT NULL,
    paciente_id_paciente BIGINT,
    FOREIGN KEY (paciente_id_paciente) REFERENCES paciente (id_paciente)
);

CREATE TABLE linea_comprobante (
    id_linea_comprobante   BIGINT AUTO_INCREMENT PRIMARY KEY,
    cantidad               INTEGER NOT NULL,
    precio_unitario        DOUBLE PRECISION NOT NULL,
    prestacion_id_prestacion BIGINT,
    id_comprobante         BIGINT,
    FOREIGN KEY (prestacion_id_prestacion) REFERENCES prestacion (id_prestacion),
    FOREIGN KEY (id_comprobante) REFERENCES comprobante (id_comprobante)
);
