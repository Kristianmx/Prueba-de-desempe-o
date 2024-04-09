USE b9vcqk173yhmvlp7pkrj;

--  Creacion de Tablas con sus conrrespondientes columnas.

CREATE TABLE coder (
	id_coder INT(11) PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    apellidos VARCHAR(45) NOT NULL,
    documento VARCHAR(45) NOT NULL,
    cohorte INT(11) NOT NULL,
    CV TEXT NOT NULL
);

CREATE TABLE empresa(
	id_empresa INT(11) PRIMARY KEY AUTO_INCREMENT,
    nombre_empresa VARCHAR(45) NOT NULL,
    sector VARCHAR(60) NOT NULL,
    ubicacion VARCHAR(80) NOT NULL,
    contacto VARCHAR(50) NOT NULL
);

CREATE TABLE vacante(
	id_vacante INT(11) PRIMARY KEY AUTO_INCREMENT,
    empresa_id_fk INT(11) NOT NULL,
    titulo VARCHAR(70) NOT NULL,
    descripcion TEXT NOT NULL,
    duracion VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    CONSTRAINT fk_id_empresa FOREIGN KEY (empresa_id_fk) REFERENCES empresa(id_empresa) ON DELETE CASCADE 
);

CREATE TABLE contratacion(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
    vacante_id_fk INT(11) NOT NULL,
    coder_id_fk int(11) NOT NULL,
    fecha_aplicacion DATE NOT NULL,
    estado VARCHAR(50) NOT NULL,
    salario DECIMAL(10,2),
	CONSTRAINT fk_id_vacante FOREIGN KEY (vacante_id_fk) REFERENCES vacante(id_vacante) ON DELETE CASCADE,
    CONSTRAINT fk_id_coder FOREIGN KEY (coder_id_fk) REFERENCES coder(id_coder)
);

-- Alteración de tablas

ALTER TABLE vacante
ADD COLUMN (tecnologia VARCHAR(60) NOT NULL);

ALTER TABLE coder
ADD COLUMN (clan VARCHAR(60) NOT NULL)




