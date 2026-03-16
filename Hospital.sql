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
    Cantidad INT NOT NULL,
);

CREATE TABLE Provedor (ProvedorID INT PRIMARY KEY AUTO_INCREMENT, 
    Nombre VARCHAR(100) NOT NULL,
    Cedula INT NOT NULL UNIQUE, 
    Producto VARCHAR(100) NOT NULL,
    Precio DOUBLE NOT NULL,
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

