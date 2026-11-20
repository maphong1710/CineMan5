CREATE DATABASE IF NOT EXISTS cineman5 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cineman5;

CREATE TABLE tblUser5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    fullName VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    email VARCHAR(100),
    dob DATE
);

CREATE TABLE tblCustomer5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    customerCode VARCHAR(10) NOT NULL UNIQUE,
    tblUser5id INT(10) NOT NULL,
    FOREIGN KEY (tblUser5id) REFERENCES tblUser5(id)
);

CREATE TABLE tblStaff5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    staffCode VARCHAR(10) NOT NULL UNIQUE,
    position VARCHAR(50),
    tblUser5id INT(10) NOT NULL,
    FOREIGN KEY (tblUser5id) REFERENCES tblUser5(id)
);

CREATE TABLE tblManager5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    tblStaff5id INT(10) NOT NULL,
    FOREIGN KEY (tblStaff5id) REFERENCES tblStaff5(id)
);

CREATE TABLE tblSalesStaff5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    tblStaff5id INT(10) NOT NULL,
    FOREIGN KEY (tblStaff5id) REFERENCES tblStaff5(id)
);

CREATE TABLE tblMembershipTier5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    tierName VARCHAR(50) NOT NULL UNIQUE,
    minPoint INT(10) NOT NULL,
    discountPercent DECIMAL(5,2) NOT NULL,
    maxPoint INT(10)
);

CREATE TABLE tblMemberCard5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    cardCode VARCHAR(20) NOT NULL UNIQUE,
    issueDate DATE NOT NULL,
    loyaltyPoints INT(10) NOT NULL DEFAULT 0,
    tblCustomer5id INT(10) NOT NULL,
    tblMembershipTier5id INT(10),
    FOREIGN KEY (tblCustomer5id) REFERENCES tblCustomer5(id),
    FOREIGN KEY (tblMembershipTier5id) REFERENCES tblMembershipTier5(id)
);

CREATE TABLE tblMovie5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    movieName VARCHAR(200) NOT NULL,
    duration INT(10) NOT NULL,
    director VARCHAR(100),
    actors VARCHAR(500),
    description TEXT,
    poster VARCHAR(255),
    trailer VARCHAR(255),
    releaseDate DATE,
    language VARCHAR(50),
    ageRating VARCHAR(10)
);

CREATE TABLE tblGenre5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    genreName VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE tblMovieGenre5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    tblGenre5id INT(10) NOT NULL,
    tblMovie5id INT(10) NOT NULL,
    FOREIGN KEY (tblGenre5id) REFERENCES tblGenre5(id),
    FOREIGN KEY (tblMovie5id) REFERENCES tblMovie5(id)
);

CREATE TABLE tblRoom5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    roomName VARCHAR(50) NOT NULL UNIQUE,
    capacity INT(10) NOT NULL,
    description VARCHAR(255),
    bufferMinutes INT(10) NOT NULL DEFAULT 15
);

CREATE TABLE tblSeat5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    seatCode VARCHAR(5) NOT NULL,
    seatType VARCHAR(20) NOT NULL,
    tblRoom5id INT(10) NOT NULL,
    FOREIGN KEY (tblRoom5id) REFERENCES tblRoom5(id)
);

CREATE TABLE tblPromotion5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    programName VARCHAR(100) NOT NULL,
    discountValue DECIMAL(10,2) NOT NULL,
    `condition` VARCHAR(255)
);

CREATE TABLE tblShowtime5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    endTimeExpected DATETIME NOT NULL,
    startTime DATETIME NOT NULL,
    ticketPrice DECIMAL(10,0) NOT NULL,
    format VARCHAR(20) NOT NULL,
    tblMovie5id INT(10) NOT NULL,
    tblRoom5id INT(10) NOT NULL,
    tblPromotion5id INT(10),
    FOREIGN KEY (tblMovie5id) REFERENCES tblMovie5(id),
    FOREIGN KEY (tblRoom5id) REFERENCES tblRoom5(id),
    FOREIGN KEY (tblPromotion5id) REFERENCES tblPromotion5(id)
);

CREATE TABLE tblInvoice5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    transactionTime DATETIME NOT NULL,
    paymentMethod VARCHAR(30) NOT NULL,
    totalAmount DECIMAL(10,0) NOT NULL,
    discountApplied DECIMAL(5,2) NOT NULL DEFAULT 0,
    guestName VARCHAR(100),
    guestPhone VARCHAR(15),
    tblCustomer5id INT(10) NULL,
    tblSalesStaff5id INT(10) NULL,
    FOREIGN KEY (tblCustomer5id) REFERENCES tblCustomer5(id),
    FOREIGN KEY (tblSalesStaff5id) REFERENCES tblSalesStaff5(id)
);

CREATE TABLE tblTicket5 (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    actualPrice DECIMAL(10,0) NOT NULL,
    tblInvoice5id INT(10) NOT NULL,
    tblShowtime5id INT(10) NOT NULL,
    tblSeat5id INT(10) NOT NULL,
    FOREIGN KEY (tblInvoice5id) REFERENCES tblInvoice5(id),
    FOREIGN KEY (tblShowtime5id) REFERENCES tblShowtime5(id),
    FOREIGN KEY (tblSeat5id) REFERENCES tblSeat5(id)
);