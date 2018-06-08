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

-- insertar solo para pruebas
INSERT INTO c_almacen (ds_descripcion) VALUES ('almacen_1');
INSERT INTO c_almacen (ds_descripcion) VALUES ('almacen_2');
INSERT INTO c_almacen (ds_descripcion) VALUES ('almacen_3');
INSERT INTO c_almacen (ds_descripcion) VALUES ('almacen_4');

-- 2018-05-25 GTL - TABLA COLORES
CREATE TABLE c_color(
cl_color integer unsigned not null AUTO_INCREMENT,
ds_descripcion VARCHAR(65) NOT NULL,
fg_estatus ENUM('1','0') NOT NULL DEFAULT '1',
PRIMARY KEY(cl_color)
)
ENGINE = InnoDB;

-- insertar solo para pruebas
INSERT INTO c_color (ds_descripcion) VALUES ('blanco');
INSERT INTO c_color (ds_descripcion) VALUES ('azul');
INSERT INTO c_color (ds_descripcion) VALUES ('amarillo');
INSERT INTO c_color (ds_descripcion) VALUES ('rosa');
INSERT INTO c_color (ds_descripcion) VALUES ('negro');
INSERT INTO c_color (ds_descripcion) VALUES ('rojo');
    
-- 2018-05-26 GTL - TABLA PUESTOS DEL TRABAJADOR
CREATE TABLE c_puesto(
cl_puesto integer unsigned not null AUTO_INCREMENT,
ds_descripcion VARCHAR(65) NOT NULL,
fg_estatus ENUM('1','0') NOT NULL DEFAULT '1',
PRIMARY KEY(cl_puesto)
)
ENGINE = InnoDB;

-- insertar solo para pruebas
INSERT INTO c_puesto (ds_descripcion) VALUES ('administrador');
INSERT INTO c_puesto (ds_descripcion) VALUES ('vendedor');
INSERT INTO c_puesto (ds_descripcion) VALUES ('chofer');

-- tabla de proveedores

CREATE TABLE c_proveedor(
cl_proveedor INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
nombre VARCHAR(45) NOT NULL DEFAULT '',
ap_paterno VARCHAR(45),
ap_materno VARCHAR(45),
email VARCHAR(45),
password VARCHAR(45),
fe_alta TIMESTAMP NULL,
tel_fijo INTEGER(12),
t_celular INTEGER(12),
calle VARCHAR(30),
colonia VARCHAR(30),
cp INTEGER (5),
municipio VARCHAR(30),
estado VARCHAR(30),
PRIMARY KEY(cl_proveedor)  
)
ENGINE = InnoDB;

-- tabla clientes 

CREATE TABLE c_cliente(
  cl_cliente INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL DEFAULT '',
  ap_paterno VARCHAR(45),
  ap_materno VARCHAR(45),
  email VARCHAR(45),
  fe_alta TIMESTAMP NULL,
  tel1 INTEGER(12),
  tel2 INTEGER(12),
  calle VARCHAR(30),
  delegacion varchar(45),
  colonia VARCHAR(30),
  cp INTEGER (5),
  municipio VARCHAR(30),
  estado VARCHAR(30),
  ciudad varchar(45),
  status ENUM('1','0'),
   PRIMARY KEY(cl_cliente)  
   
)
ENGINE = InnoDB;

-- NOTA 2018.05.22 GTL
-- solo para pruebas
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

INSERT INTO c_sucursal (nombre,calle) VALUES ('sucursal_1','calle');
INSERT INTO c_sucursal (nombre,calle) VALUES ('sucursal_2','calle');
INSERT INTO c_sucursal (nombre,calle) VALUES ('sucursal_3','calle');
INSERT INTO c_sucursal (nombre,calle) VALUES ('sucursal_4','calle');
INSERT INTO c_sucursal (nombre,calle) VALUES ('sucursal_5','calle');
INSERT INTO c_sucursal (nombre,calle) VALUES ('sucursal_6','calle');
INSERT INTO c_sucursal (nombre,calle) VALUES ('sucursal_7','calle');

-- tabla usuario 
CREATE TABLE c_usuario (
  cl_usuario INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  cl_puesto INTEGER UNSIGNED NOT NULL,
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
    ON UPDATE CASCADE 
 )
ENGINE = InnoDB;

INSERT INTO c_usuario (cl_puesto,nombre,ap_paterno,ap_materno,email,password,fg_admin,fg_activo) VALUES ('1','Gerardo','Torreblanca','Luna','gtorre@email.com','123456','1','1');
INSERT INTO c_usuario (cl_puesto,nombre,ap_paterno,ap_materno,email,password,fg_admin,fg_activo) VALUES ('2','Armando','Gonzales','Borja','armando@email.com','123456','1','1');

-- 2018.05.17 solo si existe la tabla c_usuario agregar lo siguiente
-- ALTER TABLE c_usuario
-- ADD fg_activo ENUM('1','0') NOT NULL DEFAULT '1';

-- Tabla Inventarios
CREATE TABLE c_articulo(
cl_articulo INTEGER unsigned NOT NULL AUTO_INCREMENT,
fecha_alta TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
cl_almacen INTEGER UNSIGNED NOT NULL,
cl_color INTEGER UNSIGNED NOT NULL,
descripcion VARCHAR(80) NOT NULL,
unidad_medida VARCHAR(20),
cantidad_entrada DECIMAL(9,2) NOT NULL,
cantidad_salida DECIMAL(9,2),
precio_venta DECIMAL(9,2),
cantidad_existente DECIMAL(9,2),
fg_estatus ENUM('1','0') NOT NULL DEFAULT '1',
PRIMARY KEY(cl_articulo),
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
cl_cliente INTEGER UNSIGNED NOT NULL,
cl_usuario INTEGER UNSIGNED NOT NULL,
cl_sucursal INTEGER UNSIGNED NOT NULL,
ds_descripcion VARCHAR(100),
fg_status ENUM('1','0') NOT NULL DEFAULT '1',
PRIMARY KEY (cl_venta),
CONSTRAINT fk_cl_cliente FOREIGN KEY fk_cl_cliente (cl_cliente) 
	REFERENCES c_cliente(cl_cliente)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
    
CONSTRAINT fk_cl_sucursal FOREIGN KEY fk_cl_sucursal (cl_sucursal) 
	REFERENCES c_sucursal(cl_sucursal)
	ON DELETE CASCADE
    ON UPDATE CASCADE,

CONSTRAINT fk_cl_usuario FOREIGN KEY fk_cl_usuario (cl_usuario) 
	REFERENCES c_usuario(cl_usuario)
	ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

-- 2018.05.22 GTL DETALLE DE VENTA
CREATE TABLE k_detalle_venta(
cl_detalle_venta INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
cl_venta INTEGER UNSIGNED NOT NULL,
cl_articulo INTEGER UNSIGNED NOT NULL,
cantidad INTEGER NOT NULL,
PRIMARY KEY (cl_detalle_venta),
CONSTRAINT fk_cl_venta FOREIGN KEY fk_cl_venta (cl_venta) 
	REFERENCES c_venta(cl_venta)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
CONSTRAINT fk_cl_articulo FOREIGN KEY fk_cl_articulo (cl_articulo) 
	REFERENCES c_articulo(cl_articulo)
	ON DELETE CASCADE
  ON UPDATE CASCADE
)
ENGINE = InnoDB;

INSERT INTO c_articulo (cl_almacen,cl_color,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','sala_2','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','2','sala_3','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','sala_4','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','sala_5','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','sala_6','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','sala_7','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','sala_8','pieza',600,22000,500,'1');

--urgente
-- cambios en tabla sucursales 6 junio 2018

DELETE FROM `puntoventa`.`c_sucursal` WHERE `cl_sucursal`='7';
DELETE FROM `puntoventa`.`c_sucursal` WHERE `cl_sucursal`='1';
DELETE FROM `puntoventa`.`c_sucursal` WHERE `cl_sucursal`='2';
DELETE FROM `puntoventa`.`c_sucursal` WHERE `cl_sucursal`='3';
DELETE FROM `puntoventa`.`c_sucursal` WHERE `cl_sucursal`='4';
DELETE FROM `puntoventa`.`c_sucursal` WHERE `cl_sucursal`='5';
DELETE FROM `puntoventa`.`c_sucursal` WHERE `cl_sucursal`='6';

INSERT INTO `puntoventa`.`c_sucursal` (`cl_sucursal`, `nombre`, `calle`, `t_fijo`) VALUES ('1', 'TOLUCA 1', 'PINOSUAREZ', '7222426467');
INSERT INTO `puntoventa`.`c_sucursal` (`cl_sucursal`, `nombre`, `calle`, `t_fijo`) VALUES ('2', 'TOLUCA 2', 'ADOLFO LOPEZ MATEOS', '7223186761');
INSERT INTO `puntoventa`.`c_sucursal` (`cl_sucursal`, `nombre`, `calle`, `colonia`, `municipio`, `estado`, `t_fijo`) VALUES ('3', 'TOLUCA 3', 'PASEO TOLLOCAN', 'UNIVERSIDAD', 'TOLUCA', 'TOLUCA', '7222121844');

-- cambios en la tabla c_usuario 
-- hay que asignarle de donde es cada Usuario de que sucursal 
ALTER TABLE `puntoventa`.`c_usuario` 
ADD COLUMN `cl_sucursal` INT(10) UNSIGNED NOT NULL AFTER `fg_activo`;
-- ADD PROVEDORES

INSERT INTO `puntoventa`.`c_proveedor` (`cl_proveedor`, `nombre`, `password`, `t_celular`) VALUES ('1', 'JONATHAN', '', '5557633029');

--PRODUCTOS

UPDATE `puntoventa`.`c_articulo` SET `descripcion`='' WHERE `cl_articulo`='1';
DELETE FROM `puntoventa`.`c_articulo` WHERE `cl_articulo`='7';
DELETE FROM `puntoventa`.`c_articulo` WHERE `cl_articulo`='2';
DELETE FROM `puntoventa`.`c_articulo` WHERE `cl_articulo`='3';
DELETE FROM `puntoventa`.`c_articulo` WHERE `cl_articulo`='4';
DELETE FROM `puntoventa`.`c_articulo` WHERE `cl_articulo`='5';
DELETE FROM `puntoventa`.`c_articulo` WHERE `cl_articulo`='6';

--INSERT PRODUCTOS

insert into c_articulo values(2,now(),1,1,'DIANA C/DIVAN','PAQUETE',0,0,0,0,1);
insert into c_articulo values(3,now(),1,1,'ANAHI','PAQUETE',0,0,0,0,1);
insert into c_articulo values(4,now(),1,1,'SALA CASA 3,2,1','PAQUETE',0,0,0,0,1);
insert into c_articulo values(5,now(),1,1,'JANA 3,2,1','PAQUETE',0,0,0,0,1);
insert into c_articulo values(6,now(),1,1,'BELICE MODULAR C/RECLINABLE VASO Y ADUIO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(7,now(),1,1,'CASA CALIFORNIA 3,2,1 5 RECLINABLES 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(8,now(),1,1,'CALIFORNIA 3,2,1 1 RECLINABLE	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(9,now(),1,1,'CONVERTI CAMA 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(10,now(),1,1,'SALA LUCY  2 DIVAN 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(11,now(),1,1,'SALA EMA MODULAR  C/RECINABLE , PORTAVASO Y AUDIO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(12,now(),1,1,'SALA MELANY 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(13,now(),1,1,'SALA IMPERIO 2 ESQUINAS 2 DIAVAN C/RECLINABLE,PORTAVASO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(14,now(),1,1,'SALA VERA 3,2,1 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(15,now(),1,1,'SALA CORDOBA	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(16,now(),1,1,'SALA ESTEFANY 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(17,now(),1,1,'SALA BELGICA 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(18,now(),1,1,'SALA MIA 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(19,now(),1,1,'SALA BRITANY 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(20,now(),1,1,'SALA MANCHESTER 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(21,now(),1,1,'SALA ITALIA 3,2,1 C/RECLINABLE Y PORTAVASO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(22,now(),1,1,'SALA BRITANNY DE CHEZ 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(23,now(),1,1,'SALA DUQUE 3,2,1 C/RECLINABLE ,PORTAVASO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(24,now(),1,1,'SOFACAMA AMY 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(25,now(),1,1,'SOFACAMA PORTAVASO 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(26,now(),1,1,'SALA REYNA 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(27,now(),1,1,'SALA VIANEY MODULAR 2 ESQUINA 2 DIVAN C/RECLINABLE,PORTAVASO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(28,now(),1,1,'DIANA COSTURA 3,2,1 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(29,now(),1,1,'EMA 2 ESQUINAS 2 DIVAN C/RECLINABLE,PORTAVASO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(30,now(),1,1,'BRENDA 3,2,1 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(31,now(),1,1,'FRANCIA	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(32,now(),1,1,'DEVANE 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(33,now(),1,1,'ABANICO 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(34,now(),1,1,'TOLLOCAN 2 ESQUINA 2 DIAVN C/RECLINABLE,PORTAVASO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(35,now(),1,1,'JUNIOR CON BRAZO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(36,now(),1,1,'DIANA ECO 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(37,now(),1,1,'ONTARIO 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(38,now(),1,1,'SALA JUNIOR ESCUADRA	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(39,now(),1,1,'BRENDA 2 ESQUINAS 2 DIAVAN	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(40,now(),1,1,'DIANA DE COSTURA MODULAR	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(41,now(),1,1,'PLUS 3,2,1 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(42,now(),1,1,'GINA 2 ESQUINA 2 DIVAN 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(43,now(),1,1,'GINA 3,2,1 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(44,now(),1,1,'GONDOLA 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(45,now(),1,1,'VALENCIA 3,2,1 C/RECLINABLE,PORTAVASO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(46,now(),1,1,'CARTIER 3,2,1 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(47,now(),1,1,'VALENCIA 2 ESQUINA 2 DIAVN C/RECLINABLE,PORTAVASO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(48,now(),1,1,'EVES 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(49,now(),1,1,'LUIS XXV 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(50,now(),1,1,'VENECIA 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(51,now(),1,1,'EUROPA 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(52,now(),1,1,'VOGUE 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(53,now(),1,1,'DIANA MOLDURA 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(54,now(),1,1,'CHAT MODULAR 2 ESQUINA 2 DIAVN C/RECLINABLE,PORTAVASO,AUDIO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(55,now(),1,1,'BRASILIA 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(56,now(),1,1,'AMERICA 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(57,now(),1,1,'CHESTER 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(58,now(),1,1,'ERIKA 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(59,now(),1,1,'VERA 2 ESQUINA DOS	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(60,now(),1,1,'SILLON REDONDO 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(61,now(),1,1,'SALA IMPERIO  2 ESQUINA 2 DIVAN 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(62,now(),1,1,'SALA IMPERIO 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(63,now(),1,1,'REPOSET CALIFORNIA	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(64,now(),1,1,'REPOSET EMA	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(65,now(),1,1,'REPOSET VALENCIA	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(66,now(),1,1,'SILLONES OREJONES	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(67,now(),1,1,'SILLON OREJON MINI 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(68,now(),1,1,'MESA DE TUBOS CUADRADA	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(69,now(),1,1,'MESA DE TUBOS RECTANGULAR	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(70,now(),1,1,'MESA DE TUBOS CUADRADA 90 * 90	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(71,now(),1,1,'COMEDOR DE 6 SILLAS	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(72,now(),1,1,'COMEDOR DE 4 SILLAS	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(73,now(),1,1,'COMEDOR DE 8 SILLAS	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(74,now(),1,1,'MESA CON TABURETE	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(75,now(),1,1,'JUEGOS DE COJINES 6PZAS	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(76,now(),1,1,'COJIN 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(77,now(),1,1,'MUEBLE PARA COCINA 	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(78,now(),1,1,'RECAMARA SALVADOR	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(79,now(),1,1,'RECAMARA	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(80,now(),1,1,'FLOTTER	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(81,now(),1,1,'SWEDE	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(82,now(),1,1,'VELVERT	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(83,now(),1,1,'LINO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(84,now(),1,1,'IBIZA	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(85,now(),1,1,'TELA DECORADA	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(86,now(),1,1,'GRAPA CASCO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(87,now(),1,1,'GRAPA TAPIZ	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(88,now(),1,1,'CAJA PATA FRANCIA	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(89,now(),1,1,'COSCAL PATA BOLA PLASTICO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(90,now(),1,1,'TIRATACHUELA C/FORRO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(91,now(),1,1,'TIRACHUELA S/FORRO	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(92,now(),1,1,'CAJA HERRAJES	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(93,now(),1,1,'FLORIDA 3,2,1	','PAQUETE',0,0,0,0,1);
insert into c_articulo values(94,now(),1,1,'ROMA 3,2,1 RECLINABLE 8PORTAVASOS	','PAQUETE',0,0,0,0,1);


-- FIN DEL SCRIPT