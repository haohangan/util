select * from DC.CW_CLOSE_ACCOUNT

select * from DCEX.CW_CHECK_ACCOUNT;

select * from DCEX.CW_CHECK_MONTH;

update DCEX.CW_CHECK_MONTH T2
			set CCM_LCHG_USER_ID = 'admin',
			CCM_LCHG_DATE = sysdate,
			CCM_MGR_FLAG = 'N'
			where exists ( 

select * from DCEX.CW_CHECK_MONTH T1 where CCM_CINEMA_CODE ='32091201' and CCM_MONTH ='2015-10'
)

update DCEX.CW_CHECK_MONTH T2
			set CCM_LCHG_USER_ID = 'admin',
			CCM_LCHG_DATE = sysdate,
			CCM_MGR_FLAG = 'Y'
			where exists ( 

select * from DCEX.CW_CHECK_MONTH T1 where CCM_CINEMA_CODE ='32091201' and CCM_MONTH ='2015-10' and T1.CCM_CINEMA_CODE = T2.CCM_CINEMA_CODE
)

select * from DCEX.CW_CHECK_MONTH 

http://www.cnblogs.com/CareySon/archive/2011/07/18/2109406.html