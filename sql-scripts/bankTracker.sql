CREATE DATABASE  IF NOT EXISTS `skbank` ;
USE `skbank`;

--
-- Table structure for table `USER_DETAILS`
--

DROP TABLE IF EXISTS `TRANSACTION_DETAILS`;
DROP TABLE IF EXISTS `USER_DETAILS`;

CREATE TABLE `USER_DETAILS` (
  `ACCOUNT_NUMBER` bigint(16) NOT NULL,
  `ACCOUNT_TYPE` varchar(20) DEFAULT NULL,
  `ACCOUNT_HOLDER_NAME` varchar(20) DEFAULT NULL,
  `ACCOUNT_BALANCE` numeric(20) DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_NUMBER`)
);



--
-- Dumping data for table `USER_DETAILS`
--

LOCK TABLES `USER_DETAILS` WRITE;

INSERT INTO `USER_DETAILS` VALUES
(1234567890123456,'Saving','Suresh Kumar',10000),
(1234567890123457,'Salary','Mark Samules',20000),
(1234567890123458,'Salary','Smith John',30000),
(1234567890123459,'Saving','Mike Daulton',40000),
(1234567890123460,'Saving','Vickie Mollett',50000);


UNLOCK TABLES;

DROP TABLE IF EXISTS `TRANSACTION_DETAILS`;

CREATE TABLE `TRANSACTION_DETAILS` (
  `TRANSACTION_ID` bigint(10) NOT NULL AUTO_INCREMENT,
  `TRANSACTION_DESCRIPTION` varchar(20) DEFAULT NULL,
  `TRANSACTION_TYPE` varchar(20) DEFAULT NULL,
  `ACCOUNT_NUMBER` bigint(16) NOT NULL,
  KEY fk_accountnumber(ACCOUNT_NUMBER),
  CONSTRAINT fk_accountnumber
  FOREIGN KEY(ACCOUNT_NUMBER)
  REFERENCES USER_DETAILS(ACCOUNT_NUMBER)
  ON UPDATE CASCADE
  ON DELETE RESTRICT,
  `TRANSACTION_AMOUNT` numeric(20) DEFAULT NULL,
  PRIMARY KEY (`TRANSACTION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1234567890 DEFAULT CHARSET=latin1;




select * from USER_DETAILS


delimiter //
create   trigger validate_transaction  before insert on transaction_details
for each row 
begin
DECLARE accountType varchar(10);
DECLARE accountBalance int;
DECLARE message_text varchar(100);
	if New.TRANSACTION_TYPE = 'Withdrawal' then 		
		select ud.ACCOUNT_TYPE, ud.ACCOUNT_BALANCE into accountType, accountBalance from user ud
		where ud.ACCOUNT_NUMBER = NEW.ACCOUNT_NUMBER;
			if accountType = 'Saving' and (accountBalance - New.TRANSACTION_AMOUNT <= 5000) then
					SIGNAL SQLSTATE '45000' SET message_text = 'Remaining balance must be greater than the 5000 Rupees';
			end if; 
                select ud.ACCOUNT_TYPE, ud.ACCOUNT_BALANCE into accountType, accountBalance from user ud
				where ud.ACCOUNT_NUMBER = NEW.ACCOUNT_NUMBER;
				if accountType = 'Salary' and (accountBalance - New.TRANSACTION_AMOUNT <= 0) then
					SIGNAL SQLSTATE '45000' SET message_text = 'Enough balance is not available in user account for  Withdrawal';
				end if;				      
       	update user ud
		set ud.ACCOUNT_BALANCE = ud.ACCOUNT_BALANCE - New.TRANSACTION_AMOUNT
		where ud.ACCOUNT_NUMBER = NEW.ACCOUNT_NUMBER;		
     end if;
     if New.TRANSACTION_TYPE = 'Deposit' then
		update user ud
		set ud.ACCOUNT_BALANCE = ud.ACCOUNT_BALANCE + New.TRANSACTION_AMOUNT
		where ud.ACCOUNT_NUMBER = NEW.ACCOUNT_NUMBER;
	end if;    
END //validate_transaction
delimiter ;




