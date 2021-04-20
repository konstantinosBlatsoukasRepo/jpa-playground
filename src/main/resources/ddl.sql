-- employees.passport definition

CREATE TABLE `passport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(100) NOT NULL,
  `employee_emp_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhooxgd2e6rb8cn2h4xwrpyilq` (`employee_emp_no`),
  CONSTRAINT `FKhooxgd2e6rb8cn2h4xwrpyilq` FOREIGN KEY (`employee_emp_no`) REFERENCES `employees` (`emp_no`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;