CREATE DATABASE Hospital;

USE Hospital;

CREATE TABLE Person (
    PersonID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Cedula INT NOT NULL UNIQUE,
    Telefono VARCHAR(15),
    Correo VARCHAR(100),
    Direccion VARCHAR(255),
    Contraseña VARCHAR(255) NOT NULL,
);


CREATE TABLE Inventario (ProductoID INT PRIMARY KEY AUTO_INCREMENT, 
    Nombre VARCHAR(100) NOT NULL, 
    Descripcion TEXT, 
    Cantidad INT NOT NULL
);

CREATE TABLE Provedor (ProvedorID INT PRIMARY KEY AUTO_INCREMENT, 
    Nombre VARCHAR(100) NOT NULL, 
    Telefono VARCHAR(15), 
    Correo VARCHAR(100), 
    Direccion VARCHAR(255)
);

CREATE TABLE Cita (
    CitaID INT PRIMARY KEY AUTO_INCREMENT,
    PacienteID INT,
    MedicoID INT,
    Fecha DATETIME NOT NULL,
    Motivo TEXT,
    FOREIGN KEY (PacienteID) REFERENCES Person(PersonID),
    FOREIGN KEY (MedicoID) REFERENCES Person(PersonID)
);

