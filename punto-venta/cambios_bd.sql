CREATE TABLE c_usuario (
  cl_usuario INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL DEFAULT '',
  ap_paterno VARCHAR(45),
  ap_materno VARCHAR(45),
  email VARCHAR(45),
  password VARCHAR(45),
  fe_alta TIMESTAMP NULL,
  fg_admin ENUM('1','0') NOT NULL DEFAULT '0',
  PRIMARY KEY(cl_usuario),
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;

-- tabla de proveedores

CREATE TABLE c_proveedores (
  cl_usuario INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL DEFAULT '',
  ap_paterno VARCHAR(45),
  ap_materno VARCHAR(45),
  email VARCHAR(45),
  password VARCHAR(45),
  fe_alta TIMESTAMP NULL,
  fg_admin ENUM('1','0') NOT NULL DEFAULT '0',
  PRIMARY KEY(cl_usuario),
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;