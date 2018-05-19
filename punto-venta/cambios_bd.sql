--tabla usuario 
CREATE TABLE c_usuario (
  cl_usuario INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL DEFAULT '',
  ap_paterno VARCHAR(45),
  ap_materno VARCHAR(45),
  email VARCHAR(45),
  password VARCHAR(45),
  fe_alta TIMESTAMP NULL,
  fg_admin ENUM('1','0') NOT NULL DEFAULT '0',
  fg_activo ENUM('1','0') NOT NULL DEFAULT '1',
  PRIMARY KEY(cl_usuario)
 )
ENGINE = InnoDB;

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

---tabla sucursales 
CREATE TABLE c_sucursales(
cl_sucursal INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
nombre VARCHAR (40) NOT NULL DEFAULT,
calle varchar(45),
CP Integer (5),
colonia varchar(50),
municipio varchar(70),
estado varchar(50),
t_fijo INTEGER (12),
t_secundario INTEGER (12));	
ENGINE = InnoDB;
					
--Tabla Ventas 
CREATE TABLE c_ventas(
cl_Venta INTEGER UNSIGNED NOT NULL AUTOINCREMENT,
status varchar(15)not null,
Fecha DATE,
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

--Tabla Pedidos
CREATE TABLE c_ventas(
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
create table c_inventario(
cl_inventario integer unsigned NOT NULL AUTO_INCREMENT,
fecha_alta TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
cl_almacen INTEGER NOT NULL,
descripcion VARCHAR(80) NOT NULL,
unidad_medida VARCHAR(20),
cantidad_entrada DECIMAL(9,2) NOT NULL,
cantidad_salida DECIMAL(9,2),
precio_venta DECIMAL(9,2),
cantidad_existente DECIMAL(9,2),
fg_estatus ENUM('1','0') NOT NULL DEFAULT '1',
PRIMARY KEY(cl_inventario)
)
ENGINE = InnoDB;

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
		



					
					
					
					
					
					








