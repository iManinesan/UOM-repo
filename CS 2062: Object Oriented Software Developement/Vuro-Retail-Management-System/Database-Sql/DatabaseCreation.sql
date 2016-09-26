CREATE DATABASE romafour;
-- USE `romafour` ;

-- -----------------------------------------------------
-- Table `romafour`.`customer`
-- -----------------------------------------------------

CREATE  TABLE `romafour`.`customers` (
  `Cust_Id` INT NOT NULL AUTO_INCREMENT  ,
  `Cust_Name` VARCHAR(20) NOT NULL ,
  `Cust_Address` VARCHAR(50) NULL DEFAULT NULL ,
  `Cust_Number` VARCHAR(10) NULL DEFAULT NULL ,
  `Cust_email` VARCHAR(30) NULL DEFAULT NULL ,
  `Total_Amount_Purchased` FLOAT NULL DEFAULT NULL ,
  `Discount_Points` FLOAT NULL DEFAULT NULL ,
  PRIMARY KEY (`Cust_Id`) 
  );


-- -----------------------------------------------------
-- Table `romafour`.`employee`
-- -----------------------------------------------------
CREATE  TABLE `romafour`.`employee` (
  `Emp_Id`INT NOT NULL AUTO_INCREMENT  ,
  `Emp_Name` VARCHAR(40) NOT NULL ,
  `Emp_Add` VARCHAR(60) NULL ,
  `Dept_Code` VARCHAR(20) NULL ,
  `Gender` VARCHAR(45) NOT NULL ,
  `Basic Salary` FLOAT NULL ,
  `employment_Status` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`Emp_Id`) 
  );


-- -----------------------------------------------------
-- Table `romafour`.`employee_attendance`
-- -----------------------------------------------------
CREATE  TABLE `romafour`.`employee_attendance` (
  `Emp_Id` INT NOT NULL ,
  `Atten_Date` DATE NOT NULL ,
  `Arrival_Time` TIME NOT NULL ,
  `Departure_Time` TIME NOT NULL ,
  `Overtime` FLOAT NULL DEFAULT NULL ,
  PRIMARY KEY (Emp_Id,Atten_Date)

 );


-- -----------------------------------------------------
-- Table `romafour`.`product_type`
-- -----------------------------------------------------
CREATE  TABLE `romafour`.`product_type` (
  `Product_Type_Code` VARCHAR(10) NOT NULL DEFAULT '' ,
  `Product_Type_Description` VARCHAR(30) NULL DEFAULT NULL ,
  PRIMARY KEY (`Product_Type_Code`)
  );


-- -----------------------------------------------------
-- Table `romafour`.`products`
-- -----------------------------------------------------
CREATE  TABLE `romafour`.`products` (
  `Product_Id` INT NOT NULL AUTO_INCREMENT  , 
  `Product_Name` VARCHAR(20) NOT NULL ,
  `Product_Type_Code` VARCHAR(10) NULL ,
  `Price` FLOAT NOT NULL ,
  `Reorder_Level` INT(11) NOT NULL ,
  `Total_Quantity` INT NOT NULL  DEFAULT 0,
  PRIMARY KEY (`Product_Id`) 
 
   );


-- -----------------------------------------------------
-- Table `romafour`.`inventory`
-- -----------------------------------------------------
CREATE  TABLE `romafour`.`inventory` (
  `Product_Id` INT NOT NULL  ,
  `Order_Type_Code` VARCHAR(10) NOT NULL ,
  `Order_Date` DATE NOT NULL ,
  `Quantity` INT(11) NOT NULL ,
  `Inventory_Level` INT(11) NOT NULL 
 
  );


-- -----------------------------------------------------
-- Table `romafour`.`sales`
-- -----------------------------------------------------
CREATE  TABLE `romafour`.`sales` (
  `Bill_No` INT NOT NULL AUTO_INCREMENT  ,
  `Order_Date` DATE NOT NULL ,
  `Cust_Id` INT NOT NULL ,
  `Cashier_Id`INT NOT NULL  ,
  `Total_Amount` FLOAT NOT NULL ,
  `Discount_Used` FLOAT NULL ,
  `Final_Amount` FLOAT NOT NULL ,
  PRIMARY KEY (`Bill_No`) 
  
  );


-- -----------------------------------------------------
-- Table `romafour`.`sales_items`
-- -----------------------------------------------------
CREATE  TABLE `romafour`.`sales_items` (
  `Bill_No` INT NOT NULL ,
  `Product_Id` INT NOT NULL ,
  `Price` FLOAT NOT NULL ,
  `Order_Quantity` INT(11) NOT NULL ,
  `Total_Amount` FLOAT NOT NULL ,
   PRIMARY KEY (Bill_No,Product_Id) 
  
  );



-- -----------------------------------------------------
-- Table `romafour`.`salary_history`
-- -----------------------------------------------------
CREATE  TABLE `romafour`.`salary_history` (
  `Emp_Id`INT NOT NULL ,
  `Period` VARCHAR(15) NOT NULL ,
  `Salary_Paid_Date` DATE NULL ,
  `Basic_Salary` FLOAT NOT NULL ,
  `OverTime_Payment` FLOAT NULL ,
  `Incentives` FLOAT NULL ,
  `Total_Amount` FLOAT NOT NULL ,
  PRIMARY KEY (Emp_Id,Period)
  
  );


-- -----------------------------------------------------
-- Table `romafour`.`supplies`
-- -----------------------------------------------------
CREATE  TABLE `romafour`.`supplies` (
  `Invoice_No`INT NOT NULL AUTO_INCREMENT  ,
  `Order_Date` DATE NOT NULL ,
  `Total_Amount` FLOAT NOT NULL ,
  `Cashier_Id` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`Invoice_No`)
  );


-- -----------------------------------------------------
-- Table `romafour`.`supplies_items`
-- -----------------------------------------------------
CREATE  TABLE `romafour`.`supplies_items` (
  `Invoice_No` INT NOT NULL  ,
  `Product_Id` INT NOT NULL  ,
  `Order_Quantity` INT(11) NOT NULL ,
  PRIMARY KEY (Invoice_No,Product_Id) 
  
  
  );


-- -----------------------------------------------------
-- Table `romafour`.`login`
-- -----------------------------------------------------
CREATE  TABLE `romafour`.`login` (
  `Emp_Id` INT NOT NULL ,
  `ppword` VARCHAR(5) NOT NULL ,
  `interface_Id` INT NOT NULL ,
  PRIMARY KEY (Emp_Id)
    
 );
 
-- Data for employee login
INSERT INTO `romafour`.`login` ( `Emp_Id`, `ppword`, `interface_Id` )
VALUES (1,"qwert",3);
INSERT INTO `romafour`.`login` ( `Emp_Id`, `ppword`, `interface_Id` )
VALUES (2,"qwert",2);
INSERT INTO `romafour`.`login` ( `Emp_Id`, `ppword`, `interface_Id` )
VALUES (3,"qwert",1);


