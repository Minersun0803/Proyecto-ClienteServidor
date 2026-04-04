CREATE DATABASE Hospital;

USE Hospital;

CREATE TABLE Persona (
    PersonaID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Cedula INT NOT NULL UNIQUE,
    AñoNacimiento INT NOT NULL,
    genero VARCHAR(10) NOT NULL, 
    Telefono VARCHAR(15),
    Correo VARCHAR(100),
    Ubicacion VARCHAR(255),
    Contraseña VARCHAR(255) NOT NULL
);

CREATE TABLE Medico (
    MedicoID INT PRIMARY KEY AUTO_INCREMENT,
    PersonaID INT,
    Especialidad VARCHAR(100),
    FOREIGN KEY (PersonaID) REFERENCES Persona(PersonaID)

);

CREATE TABLE Paciente (
    PacienteID INT PRIMARY KEY AUTO_INCREMENT,
    PersonaID INT,

    FOREIGN KEY (PersonaID) REFERENCES Persona(PersonaID)
  
);


CREATE TABLE Inventario (ProductoID INT PRIMARY KEY AUTO_INCREMENT, 
    Nombre VARCHAR(100) NOT NULL, 
    Descripcion VARCHAR(255) NOT NULL, 
    Cantidad INT NOT NULL
);

CREATE TABLE Receta (RecetaID INT PRIMARY KEY AUTO_INCREMENT, 
    PacienteID INT, 
    MedicoID INT, 
    Fecha DATE NOT NULL, 
    Detalles VARCHAR(1000) NOT NULL,
    FOREIGN KEY (PacienteID) REFERENCES Paciente(PacienteID),
    FOREIGN KEY (MedicoID) REFERENCES Medico(MedicoID)
);

ALTER TABLE Receta
ADD COLUMN Estado VARCHAR(20) NOT NULL DEFAULT 'Pendiente';

CREATE TABLE Provedor (ProvedorID INT PRIMARY KEY AUTO_INCREMENT, 
    Nombre VARCHAR(100) NOT NULL,
    Cedula INT NOT NULL UNIQUE, 
    Producto VARCHAR(100) NOT NULL,
    Cantidad INT NOT NULL,
    ProductoID INT,
    FOREIGN KEY (ProductoID) REFERENCES Inventario(ProductoID)
);

CREATE TABLE Cita (
    CitaID INT PRIMARY KEY AUTO_INCREMENT,
    PacienteID INT,
    MedicoID INT,
    Fecha varchar(10) NOT NULL,
    Hora varchar(5) NOT NULL,
    Direccion VARCHAR(255) NOT NULL,
    FOREIGN KEY (PacienteID) REFERENCES Paciente(PacienteID),
    FOREIGN KEY (MedicoID) REFERENCES Medico(MedicoID)
);

CREATE TABLE Provincia (
    ProvinciaID INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Canton (
    CantonID INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(100) NOT NULL,
    ProvinciaID INT,
    FOREIGN KEY (ProvinciaID) REFERENCES Provincia(ProvinciaID)
);

CREATE TABLE Distrito (
    DistritoID INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(100) NOT NULL,
    CantonID INT,
    FOREIGN KEY (CantonID) REFERENCES Canton(CantonID)
);


CREATE TABLE Historial (
    PacienteID INT,
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL,
    Edad INT NOT NULL,
    Genero VARCHAR(10) NOT NULL,
    Antecedentes VARCHAR(1000),
    Tratamiento VARCHAR(1000),
    Habitos VARCHAR(1000),
    Notas Varchar (500),
    FOREIGN KEY (PacienteID) REFERENCES Paciente(PacienteID)
);

INSERT INTO Persona (FirstName, LastName, Cedula, AñoNacimiento, genero, Telefono, Correo, Ubicacion, Contraseña) VALUES
('Carlos', 'Ramirez', 87654323, 1975, 'Masculino', '555-1111', 'carlos.ramirez@email.com', '789 Pine Rd', 'medico123'),
('Laura', 'Fernandez', 87654324, 1985, 'Femenino', '555-2222', 'laura.fernandez@email.com', '321 Maple St', 'medico456'),
('Pedro', 'Sanchez', 87654325, 1995, 'Masculino', '555-3333', 'pedro.sanchez@email.com', '654 Elm St', 'paciente789');


-- Médicos
INSERT INTO Medico (PersonaID, Especialidad) VALUES
(1, 'Cardiología'),
(2, 'Pediatría');

-- Paciente (Pedro)
INSERT INTO Paciente (PersonaID) VALUES
(3);

INSERT INTO Historial (PacienteID, Nombre, Apellido, Edad, Genero, Antecedentes, Tratamiento, Habitos, Notas) VALUES
(1, 'Pedro', 'Sanchez', 31, 'Masculino',
 'Antecedentes familiares de hipertensión. No alergias conocidas.',
 'Tratamiento actual: Paracetamol 500mg cada 8 horas por 5 días.',
 'No fuma, consume alcohol ocasionalmente, realiza ejercicio moderado 3 veces por semana.',
 'Paciente presenta dolor de cabeza recurrente, se recomienda seguimiento en consulta de control.');

-- Inventario
INSERT INTO Inventario (Nombre, Descripcion, Cantidad) VALUES
('Aspirina', 'Medicamento para el dolor y la inflamación', 100);

-- Proveedor
INSERT INTO Provedor (Nombre, Cedula, Producto, Cantidad, ProductoID) VALUES
('Proveedor A', 123456789, 'Aspirina', 1000, 1);

INSERT INTO Cita (PacienteID, MedicoID, Fecha, Hora, Direccion) VALUES
(1, 1, '2026-07-01', '10:00', 'Hospital Calderon Guardia');

INSERT INTO Receta (PacienteID, MedicoID, Fecha, Detalles, Estado)
VALUES (1, 1, CURDATE(), 'Paracetamol 500mg cada 8 horas por 5 días', 'Pendiente');





