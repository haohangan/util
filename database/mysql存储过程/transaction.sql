BEGIN
	declare i int default 0;
	declare name varchar(50) default '金阁寺';
	declare cc varchar(64) default '00006';
	
	declare transaction_error int default 0;
	declare continue handler for sqlexception set transaction_error = -1;
	
	start transaction;
	repeat
		set name = CONCAT('金阁寺',i);
		INSERT INTO `test`.`goods_info` (`goods_info_name`, `CC`) VALUES (name, cc);
		
		set i = i+1;
		until ((i>=5)or(transaction_error=-1))
	end repeat;
	if transaction_error = -1 then 
		rollback;
	else
		commit;
	end if; 
	select concat('事务结果：',transaction_error);
END
