-- 2018.07.26 GTL agregar un campo a clientes para la direccion completa
ALTER TABLE c_cliente
ADD COLUMN direccion VARCHAR(255) AFTER ciudad;

ALTER TABLE c_cliente
DROP COLUMN tel1;

ALTER TABLE c_cliente
DROP COLUMN tel2;

ALTER TABLE c_cliente
ADD COLUMN tel1 VARCHAR(25) AFTER fe_alta;

ALTER TABLE c_cliente
ADD COLUMN tel2 VARCHAR(25) AFTER fe_alta;