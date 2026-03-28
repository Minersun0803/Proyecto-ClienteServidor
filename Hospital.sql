CREATE DATABASE Hospital;

USE Hospital;

CREATE TABLE Person (
    PersonID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Cedula INT NOT NULL UNIQUE,
    AñoNacimiento INT NOT NULL, -- Poner una rango entre 1 año a 80 años
    Telefono VARCHAR(15),
    Correo VARCHAR(100),
    Direccion VARCHAR(255),
    Contraseña VARCHAR(255) NOT NULL
);

CREATE TABLE Medico (
    MedicoID INT PRIMARY KEY AUTO_INCREMENT,
    PersonID INT,
    Especialidad VARCHAR(100),
    FOREIGN KEY (PersonID) REFERENCES Person(PersonID)
);

CREATE TABLE Paciente (
    PacienteID INT PRIMARY KEY AUTO_INCREMENT,
    PersonID INT,
    FOREIGN KEY (PersonID) REFERENCES Person(PersonID)
);


CREATE TABLE Inventario (ProductoID INT PRIMARY KEY AUTO_INCREMENT, 
    Nombre VARCHAR(100) NOT NULL, 
    Descripcion VARCHAR(255) NOT NULL, 
    Cantidad INT NOT NULL
);

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
    Fecha DATETIME NOT NULL,
    Motivo TEXT,
    FOREIGN KEY (PacienteID) REFERENCES Paciente(PacienteID),
    FOREIGN KEY (MedicoID) REFERENCES Medico(MedicoID)
);

CREATE TABLE Distrito (
    DistritoID INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(100) NOT NULL
);
CREATE TABLE Canton (
    CantonID INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(100) NOT NULL,
    DistritoID INT,
    FOREIGN KEY (DistritoID) REFERENCES Distrito(DistritoID)
);
CREATE TABLE Provincia (
    ProvinciaID INT PRIMARY KEY AUTO_INCREMENT,
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

INSERT INTO Person (FirstName, LastName, Cedula, AñoNacimiento, Telefono, Correo, Direccion, Contraseña) VALUES
('Juan', 'Perez', 12345678, 1980, '555-1234', 'juan.perez@email.com', '123 Main St', 'password123');

INSERT INTO Person (FirstName, LastName, Cedula, AñoNacimiento, Telefono, Correo, Direccion, Contraseña) VALUES
('Maria', 'Gomez', 87654321, 1990, '555-5678', 'maria.gomez@email.com', '456 Oak Ave', 'password456');

INSERT INTO Medico (PersonID, Especialidad) VALUES
(1, 'Cardiología');
INSERT INTO Paciente (PersonID) VALUES
(2);

INSERT INTO Inventario (Nombre, Descripcion, Cantidad) VALUES
('Aspirina', 'Medicamento para el dolor y la inflamación', 100);    
INSERT INTO Provedor (Nombre, Cedula, Producto, Precio, Cantidad, ProductoID) VALUES
('Proveedor A', 123456789, 'Aspirina', 0.10, 1000, 1);

INSERT INTO Cita (PacienteID, MedicoID, Fecha, Motivo) VALUES
(1, 1, '2024-07-01 10:00:00', 'Consulta de rutina');
INSERT INTO Distrito (Nombre) VALUES
('San José');
INSERT INTO Canton (Nombre, DistritoID) VALUES
('Central', 1);
INSERT INTO Provincia (Nombre, CantonID) VALUES
('San José', 1);
INSERT INTO Historial (PacienteID, Nombre, Apellido, Edad, Genero, Antecedentes, Tratamiento, Habitos, Notas, Consultas) VALUES
(2, 'Maria', 'Gomez', 34, 'Femenino', 'Diabetes', 'Insulina', 'No fuma, ejercicio regular', 'Paciente estable', 'Consulta de rutina el 2024-07-01');







