-- 2018-05-31 EJECUTAR EL SCRIPT COMPLETO
-- borramos la base si existe
DROP DATABASE IF EXISTS puntoventa;
-- crear db
CREATE DATABASE IF NOT EXISTS puntoventa;

USE puntoventa;

-- agregando catalogos

CREATE TABLE c_almacen(
cl_almacen integer unsigned not null AUTO_INCREMENT,
ds_descripcion VARCHAR(65) NOT NULL,
fg_estatus ENUM('1','0') NOT NULL DEFAULT '1',
PRIMARY KEY(cl_almacen)
)
ENGINE = InnoDB;

CREATE TABLE c_estatus_venta(
cl_estatus_venta integer unsigned not null AUTO_INCREMENT,
ds_descripcion VARCHAR(65) NOT NULL,
PRIMARY KEY(cl_estatus_venta)
)
ENGINE = InnoDB;

CREATE TABLE c_estatus_entrega(
cl_estatus_entrega integer unsigned not null AUTO_INCREMENT,
ds_descripcion VARCHAR(65) NOT NULL,
PRIMARY KEY(cl_estatus_entrega)
)
ENGINE = InnoDB;

-- 2018-05-25 GTL - TABLA COLORES
CREATE TABLE c_color(
cl_color integer unsigned not null AUTO_INCREMENT,
ds_descripcion VARCHAR(65) NOT NULL,
fg_estatus ENUM('1','0') NOT NULL DEFAULT '1',
PRIMARY KEY(cl_color)
)
ENGINE = InnoDB;

-- 2018-05-26 GTL - TABLA PUESTOS DEL TRABAJADOR
CREATE TABLE c_puesto(
cl_puesto integer unsigned not null AUTO_INCREMENT,
ds_descripcion VARCHAR(65) NOT NULL,
fg_estatus ENUM('1','0') NOT NULL DEFAULT '1',
PRIMARY KEY(cl_puesto)
)
ENGINE = InnoDB;

-- tabla clientes 

CREATE TABLE c_cliente(
  cl_cliente INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL DEFAULT '',
  ap_paterno VARCHAR(45),
  ap_materno VARCHAR(45),
  email VARCHAR(45),
  fe_alta TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  tel1 VARCHAR(25),
  tel2 VARCHAR(25),
  calle VARCHAR(30),
  delegacion VARCHAR(45),
  colonia VARCHAR(30),
  cp VARCHAR (5),
  municipio VARCHAR(30),
  estado VARCHAR(30),
  ciudad VARCHAR(45),
  direccion VARCHAR(255),
  status ENUM('1','0'),  
  PRIMARY KEY(cl_cliente)  
   
)
ENGINE = InnoDB;

-- tabla sucursales 
CREATE TABLE c_sucursal(
cl_sucursal INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
nombre VARCHAR (40) NOT NULL,
calle varchar(45),
CP VARCHAR (5),
colonia VARCHAR(50),
municipio VARCHAR(70),
estado VARCHAR(50),
t_fijo VARCHAR (12),
t_secundario VARCHAR (12),
PRIMARY KEY (cl_sucursal)
)
ENGINE = InnoDB;

-- tabla usuario 
CREATE TABLE c_usuario (
  cl_usuario INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  cl_puesto INTEGER UNSIGNED NOT NULL,
  cl_sucursal INTEGER UNSIGNED NOT NULL,
  nombre VARCHAR(45) NOT NULL DEFAULT '',
  ap_paterno VARCHAR(45),
  ap_materno VARCHAR(45),
  email VARCHAR(45),
  password VARCHAR(45),
  fe_alta TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fg_admin ENUM('1','0') NOT NULL DEFAULT '0',
  fg_activo ENUM('1','0') NOT NULL DEFAULT '1',
  PRIMARY KEY(cl_usuario),  
  CONSTRAINT fk_cl_puesto FOREIGN KEY fk_cl_puesto (cl_puesto) 
	REFERENCES c_puesto(cl_puesto)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
 CONSTRAINT fk_cl_sucursal FOREIGN KEY fk_cl_sucursal (cl_sucursal) 
	REFERENCES c_sucursal(cl_sucursal)
	ON DELETE CASCADE
    ON UPDATE CASCADE 
 )
ENGINE = InnoDB;



-- Tabla Inventarios
CREATE TABLE c_articulo(
cl_articulo INTEGER unsigned NOT NULL AUTO_INCREMENT,
fecha_alta TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
cl_almacen INTEGER UNSIGNED NOT NULL,
cl_color INTEGER UNSIGNED NOT NULL,
cl_sucursal INTEGER UNSIGNED NOT NULL,
descripcion VARCHAR(80) NOT NULL,
unidad_medida VARCHAR(20),
cantidad_entrada DECIMAL(9,2) NOT NULL,
cantidad_salida DECIMAL(9,2),
precio_venta DECIMAL(9,2),
cantidad_existente DECIMAL(9,2),
fg_estatus ENUM('1','0') NOT NULL DEFAULT '1',
PRIMARY KEY(cl_articulo),
CONSTRAINT fk_articulo_cl_sucursal FOREIGN KEY fk_articulo_cl_sucursal (cl_sucursal) 
	REFERENCES c_sucursal(cl_sucursal)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
CONSTRAINT fk1_cl_almacen FOREIGN KEY fk1_cl_almacen (cl_almacen) 
  REFERENCES c_almacen(cl_almacen)
  ON DELETE CASCADE
    ON UPDATE CASCADE,    
CONSTRAINT fk1_cl_color FOREIGN KEY fk1_cl_color (cl_color) 
  REFERENCES c_color(cl_color)
  ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

				
-- 2018.05.22 GTL Tabla Ventas 
CREATE TABLE c_venta(
cl_venta INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
fe_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
fe_entrega TIMESTAMP NULL,
fe_vencimiento_credito TIMESTAMP NULL,
cl_cliente INTEGER UNSIGNED NOT NULL,
cl_usuario INTEGER UNSIGNED NOT NULL,
cl_sucursal INTEGER UNSIGNED NOT NULL,
cl_estatus_venta INTEGER UNSIGNED NOT NULL,
ds_descripcion VARCHAR(100),
fg_credito ENUM('1','0') NOT NULL DEFAULT '0',
PRIMARY KEY (cl_venta),
CONSTRAINT fk_cl_cliente FOREIGN KEY fk_cl_cliente (cl_cliente) 
	REFERENCES c_cliente(cl_cliente)
	ON DELETE CASCADE
    ON UPDATE CASCADE,    
CONSTRAINT fk_venta_cl_sucursal FOREIGN KEY fk_venta_cl_sucursal (cl_sucursal) 
	REFERENCES c_sucursal(cl_sucursal)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
CONSTRAINT fk_cl_usuario FOREIGN KEY fk_cl_usuario (cl_usuario) 
	REFERENCES c_usuario(cl_usuario)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
CONSTRAINT fk_cl_estatus_venta FOREIGN KEY fk_cl_estatus_venta (cl_estatus_venta) 
	REFERENCES c_estatus_venta(cl_estatus_venta)
	ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

-- 2018.05.22 GTL DETALLE DE VENTA
CREATE TABLE k_detalle_venta(
cl_detalle_venta INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
cl_venta INTEGER UNSIGNED NOT NULL,
cl_articulo INTEGER UNSIGNED NOT NULL,
cl_color INTEGER UNSIGNED NOT NULL,
cantidad INTEGER NOT NULL,
precio_articulo DECIMAL(9,2),
PRIMARY KEY (cl_detalle_venta),
CONSTRAINT fk_cl_venta FOREIGN KEY fk_cl_venta (cl_venta) 
	REFERENCES c_venta(cl_venta)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
CONSTRAINT fk_cl_articulo FOREIGN KEY fk_cl_articulo (cl_articulo) 
	REFERENCES c_articulo(cl_articulo)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
CONSTRAINT fk_cl_color FOREIGN KEY fk_cl_color (cl_color) 
	REFERENCES c_color(cl_color)
	ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;


-- 2018.06.14 GTL - tabla entregas, almacenara la informacion para manejar las entregas por parte de un proveedor
CREATE TABLE c_entrega(
cl_entrega INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
fe_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
fe_entrega TIMESTAMP NULL,
cl_usuario INTEGER UNSIGNED NOT NULL,
cl_sucursal INTEGER UNSIGNED NOT NULL,
cl_estatus_entrega INTEGER UNSIGNED NOT NULL,
ds_descripcion VARCHAR(100),
PRIMARY KEY (cl_entrega),
CONSTRAINT fk_entrega_cl_sucursal FOREIGN KEY fk_entrega_cl_sucursal (cl_sucursal) 
	REFERENCES c_sucursal(cl_sucursal)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
CONSTRAINT fk_entrega_cl_usuario FOREIGN KEY fk_entrega_cl_usuario (cl_usuario) 
	REFERENCES c_usuario(cl_usuario)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
CONSTRAINT fk_cl_estatus_entrega FOREIGN KEY fk_cl_estatus_entrega (cl_estatus_entrega) 
	REFERENCES c_estatus_entrega(cl_estatus_entrega)
	ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

-- 2018.06.14 GTL DETALLE DE ENTREGAS
CREATE TABLE k_detalle_entrega(
cl_detalle_entrega INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
cl_entrega INTEGER UNSIGNED NOT NULL,
cl_articulo INTEGER UNSIGNED NOT NULL,
cl_color INTEGER UNSIGNED NOT NULL,
cantidad INTEGER NOT NULL,
PRIMARY KEY (cl_detalle_entrega),
CONSTRAINT fk_cl_detalle_entrega FOREIGN KEY fk_cl_detalle_entrega (cl_entrega) 
	REFERENCES c_entrega(cl_entrega)
	ON DELETE CASCADE
    ON UPDATE CASCADE,    
CONSTRAINT fk_detalle_entrega_cl_articulo FOREIGN KEY fk_detalle_entrega_cl_articulo (cl_articulo) 
	REFERENCES c_articulo(cl_articulo)
	ON DELETE CASCADE
    ON UPDATE CASCADE,    
CONSTRAINT fk_detalle_entrega_cl_color FOREIGN KEY fk_detalle_entrega_cl_color (cl_color) 
	REFERENCES c_color(cl_color)
	ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

-- 2018.08.28 tabla tipo de abonos
CREATE TABLE c_tipo_abono(
cl_tipo_abono INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
ds_descripcion VARCHAR(65) NOT NULL,
fg_estatus ENUM('1','0') NOT NULL DEFAULT '1',
PRIMARY KEY(cl_tipo_abono)
)
ENGINE = InnoDB;

-- 2018.08.28 tabla abonos
CREATE TABLE c_abono(
cl_abono INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
cl_venta INTEGER UNSIGNED NOT NULL,
cl_tipo_abono INTEGER UNSIGNED NOT NULL,
cl_usuario INTEGER UNSIGNED NOT NULL,
fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
cantidad_abono DECIMAL(9,2),
ds_descripcion VARCHAR(65) NULL,
fg_estatus ENUM('1','0') NOT NULL DEFAULT '1',
PRIMARY KEY(cl_abono),
CONSTRAINT fk_abono_cl_usuario FOREIGN KEY fk_abono_cl_usuario (cl_usuario) 
	REFERENCES c_usuario(cl_usuario)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
CONSTRAINT fk_abono_cl_venta FOREIGN KEY fk_abono_cl_venta (cl_venta) 
	REFERENCES c_venta(cl_venta)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
CONSTRAINT fk_abono_cl_tipo_abono FOREIGN KEY fk_abono_cl_tipo_abono (cl_tipo_abono) 
	REFERENCES c_tipo_abono(cl_tipo_abono)
	ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

-- FIN DEL SCRIPT PARA BD

-- Inicia la inserccion de registros solo para pruebas

-- primero insertamos la info para catalogos
INSERT INTO c_tipo_abono (ds_descripcion) VALUES ('Efectivo');
INSERT INTO c_tipo_abono (ds_descripcion) VALUES ('Cheque');
INSERT INTO c_tipo_abono (ds_descripcion) VALUES ('Transferencia');
INSERT INTO c_tipo_abono (ds_descripcion) VALUES ('Tarjeta credito');
INSERT INTO c_tipo_abono (ds_descripcion) VALUES ('Tarjeta debito');

INSERT INTO c_estatus_venta (ds_descripcion) VALUES ('Registrado');
INSERT INTO c_estatus_venta (ds_descripcion) VALUES ('Cancelado');
INSERT INTO c_estatus_venta (ds_descripcion) VALUES ('Autorizado');
INSERT INTO c_estatus_venta (ds_descripcion) VALUES ('Archivado');
INSERT INTO c_estatus_venta (ds_descripcion) VALUES ('Entregado');

INSERT INTO c_estatus_entrega (ds_descripcion) VALUES ('Registrado');
INSERT INTO c_estatus_entrega (ds_descripcion) VALUES ('Cancelado');
INSERT INTO c_estatus_entrega (ds_descripcion) VALUES ('Autorizado');
INSERT INTO c_estatus_entrega (ds_descripcion) VALUES ('Archivado');
INSERT INTO c_estatus_entrega (ds_descripcion) VALUES ('Entregado');

INSERT INTO c_almacen (ds_descripcion) VALUES ('almacen_1');
INSERT INTO c_almacen (ds_descripcion) VALUES ('almacen_2');
INSERT INTO c_almacen (ds_descripcion) VALUES ('almacen_3');
INSERT INTO c_almacen (ds_descripcion) VALUES ('almacen_4');

INSERT INTO c_color (ds_descripcion) VALUES ('blanco');
INSERT INTO c_color (ds_descripcion) VALUES ('azul');
INSERT INTO c_color (ds_descripcion) VALUES ('amarillo');
INSERT INTO c_color (ds_descripcion) VALUES ('rosa');
INSERT INTO c_color (ds_descripcion) VALUES ('negro');
INSERT INTO c_color (ds_descripcion) VALUES ('rojo');

INSERT INTO c_puesto (ds_descripcion) VALUES ('administrador');
INSERT INTO c_puesto (ds_descripcion) VALUES ('vendedor');
INSERT INTO c_puesto (ds_descripcion) VALUES ('chofer');
INSERT INTO c_puesto (ds_descripcion) VALUES ('proveedor');

INSERT INTO c_sucursal (cl_sucursal, nombre, calle, t_fijo) VALUES ('1', 'TOLUCA 1', 'PINOSUAREZ', '7222426467');
INSERT INTO c_sucursal (cl_sucursal, nombre, calle, t_fijo) VALUES ('2', 'TOLUCA 2', 'ADOLFO LOPEZ MATEOS', '7223186761');
INSERT INTO c_sucursal (cl_sucursal, nombre, calle, colonia, municipio, estado, t_fijo) VALUES ('3', 'TOLUCA 3', 'PASEO TOLLOCAN', 'UNIVERSIDAD', 'TOLUCA', 'TOLUCA', '7222121844');

INSERT INTO c_usuario (cl_puesto,cl_sucursal,nombre,ap_paterno,ap_materno,email,password,fg_admin,fg_activo) VALUES ('1','1','Gerardo','Torreblanca','Luna','gtorre@email.com','123456','1','1');
INSERT INTO c_usuario (cl_puesto,cl_sucursal,nombre,ap_paterno,ap_materno,email,password,fg_admin,fg_activo) VALUES ('2','1','Armando','Gonzales','Borja','armando@email.com','123456','1','1');
-- agregamos usuario ROL PROVEEDOR
INSERT INTO c_usuario (cl_puesto,cl_sucursal,nombre,ap_paterno,ap_materno,email,password,fg_admin,fg_activo) VALUES ('4','1','luis','proveedor 1','ap_paterno','proveedor@email.com','123456','1','1');

INSERT INTO c_cliente (nombre,ap_paterno,ap_materno,email,status) VALUES ('cliente1','ap_paterno','ap_materno','cliente1@email.com','1');
INSERT INTO c_cliente (nombre,ap_paterno,ap_materno,email,status) VALUES ('cliente2','ap_paterno','ap_materno','cliente2@email.com','1');
INSERT INTO c_cliente (nombre,ap_paterno,ap_materno,email,status) VALUES ('cliente3','ap_paterno','ap_materno','cliente3@email.com','1');
INSERT INTO c_cliente (nombre,ap_paterno,ap_materno,email,status) VALUES ('cliente4','ap_paterno','ap_materno','cliente4@email.com','1');
INSERT INTO c_cliente (nombre,ap_paterno,ap_materno,email,status) VALUES ('cliente5','ap_paterno','ap_materno','cliente5@email.com','1');
INSERT INTO c_cliente (nombre,ap_paterno,ap_materno,email,status) VALUES ('cliente6','ap_paterno','ap_materno','cliente6@email.com','1');
INSERT INTO c_cliente (nombre,ap_paterno,ap_materno,email,status) VALUES ('cliente7','ap_paterno','ap_materno','cliente7@email.com','1');
INSERT INTO c_cliente (nombre,ap_paterno,ap_materno,email,status) VALUES ('cliente8','ap_paterno','ap_materno','cliente8@email.com','1');
INSERT INTO c_cliente (nombre,ap_paterno,ap_materno,email,status) VALUES ('cliente9','ap_paterno','ap_materno','cliente9@email.com','1');
INSERT INTO c_cliente (nombre,ap_paterno,ap_materno,email,status) VALUES ('cliente10','ap_paterno','ap_materno','cliente10@email.com','1');
INSERT INTO c_cliente (nombre,ap_paterno,ap_materno,email,status) VALUES ('cliente11','ap_paterno','ap_materno','cliente11@email.com','1');

INSERT INTO c_articulo (cl_almacen,cl_color,cl_sucursal,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','1','sala_2','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,cl_sucursal,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','2','1','sala_3','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,cl_sucursal,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','1','sala_4','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,cl_sucursal,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','1','sala_5','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,cl_sucursal,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','1','sala_6','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,cl_sucursal,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','1','sala_7','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,cl_sucursal,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','1','sala_8','pieza',600,22000,500,'1');
	



