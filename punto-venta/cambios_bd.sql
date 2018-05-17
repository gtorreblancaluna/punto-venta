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
  PRIMARY KEY(cl_usuario)
 )
ENGINE = InnoDB;

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
create table c_Inventarios(
cl_Inventario integer unsigned not null autoincrement,
status varchar (15) not null,
fecha date,
almacen varchar(40),
Articulo varchar(80) not null,
unidad varchar(20),
entrada double (7,2)not null,
salida double(7,2),
Costo double(9,2),
)
ENGINE = InnoDB;

--Table productos 
create table c_productos(
c_producto integer unsigned not null autoincrement,
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
cl_almacnen integer unsigned not null autoincrement,
ds_descripcion VARCHAR(65) NOT NULL,
status varchar(15)not null
)
ENGINE = InnoDB;
		



					
					
					
					
					
					








