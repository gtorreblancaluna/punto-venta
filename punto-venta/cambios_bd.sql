--tabla usuario 
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
ALTER TABLE c_usuario
ADD fg_activo ENUM('1','0') NOT NULL DEFAULT '1';

-- tabla de proveedores

CREATE TABLE c_proveedore {
cl_proveedor INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL DEFAULT '',
  ap_paterno VARCHAR(45),
  ap_materno VARCHAR(45),
  email VARCHAR(45),
  password VARCHAR(45),
  fe_alta TIMESTAMP NULL,
  T_Fijo INTEGER(12),
  T_Celular INTEGER(12),
  Calle VARCHAR(30),
  Colonia VARCHAR(30),
  cp INTEGER (5),
  municipio VARCHAR(30),
  estado VARCHAR(30)
)
ENGINE = InnoDB;
-------tabla clientes 

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
-- por si quieres agregar varios clientes aqui te dejo unos INSERTS jejeje
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

---tabla sucursales 
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
					
--2018.05.22 GTL Tabla Ventas 
CREATE TABLE c_venta(
cl_venta INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
fe_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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

--Tabla Pedidos
CREATE TABLE c_pedido(
cl_Pedido INTEGER UNSIGNED NOT NULL AUTOINCREMENT,
status varchar(15)not null,
fecha DATE,
cliente varchar(40),
Vendedor varchar(40),
Articulo varchar(80) not null,
Descripcion varchar(100),
Unidad varchar(50),
Cantidad double (7,2)not null,
Precio_U double (9,2) not null, 
importe double (9,2) not null
)
ENGINE = InnoDB;


--Tabla Inventarios
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

INSERT INTO c_articulo (cl_almacen,cl_color,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','sala_2','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','2','sala_3','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','sala_4','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','sala_5','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','sala_6','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','sala_7','pieza',600,22000,500,'1');
INSERT INTO c_articulo (cl_almacen,cl_color,descripcion,unidad_medida,cantidad_entrada,precio_venta,cantidad_existente,fg_estatus) VALUES ('2','1','sala_8','pieza',600,22000,500,'1');

--Table productos, 2018.05.17 ESTA PENDIENTE ESTA TABLA, NO SE CREA POR EL MOMENTO 
create table c_productos(
c_producto integer unsigned not null AUTO_INCREMENT,
status varchar(15)not null,
f_alta date,
cl_almacen INTEGER NOT NULL,--aqui se podria poner un id de almacen porque seran 7 sucursales 
nombre varchar(100) not null,
u_venta varchar(30),
u_compra varchar(30),
p_venta double(7,2),
)
ENGINE = InnoDB;

CREATE TABLE c_almacen(
cl_almacen integer unsigned not null AUTO_INCREMENT,
ds_descripcion VARCHAR(65) NOT NULL,
fg_estatus ENUM('1','0') NOT NULL DEFAULT '1',
PRIMARY KEY(cl_almacen)
)
ENGINE = InnoDB;

INSERT INTO c_almacen (ds_descripcion) VALUES ('almacen_1');
INSERT INTO c_almacen (ds_descripcion) VALUES ('almacen_2');
INSERT INTO c_almacen (ds_descripcion) VALUES ('almacen_3');
INSERT INTO c_almacen (ds_descripcion) VALUES ('almacen_4');


--2018-05-25 GTL - TABLA COLORES
CREATE TABLE c_color(
cl_color integer unsigned not null AUTO_INCREMENT,
ds_descripcion VARCHAR(65) NOT NULL,
fg_estatus ENUM('1','0') NOT NULL DEFAULT '1',
PRIMARY KEY(cl_color)
)
ENGINE = InnoDB;

INSERT INTO c_color (ds_descripcion) VALUES ('blanco');
INSERT INTO c_color (ds_descripcion) VALUES ('azul');
INSERT INTO c_color (ds_descripcion) VALUES ('amarillo');
INSERT INTO c_color (ds_descripcion) VALUES ('rosa');
INSERT INTO c_color (ds_descripcion) VALUES ('negro');
INSERT INTO c_color (ds_descripcion) VALUES ('rojo');
		

--2018-05-26 GTL - TABLA PUESTOS DEL TRABAJADOR
CREATE TABLE c_puesto(
cl_puesto integer unsigned not null AUTO_INCREMENT,
ds_descripcion VARCHAR(65) NOT NULL,
fg_estatus ENUM('1','0') NOT NULL DEFAULT '1',
PRIMARY KEY(cl_puesto)
)
ENGINE = InnoDB;

INSERT INTO c_puesto (ds_descripcion) VALUES ('administrador');
INSERT INTO c_puesto (ds_descripcion) VALUES ('vendedor');
INSERT INTO c_puesto (ds_descripcion) VALUES ('chofer');



					
					
					
					
					
					








