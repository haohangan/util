
select *
from CW_MONTH_CLOSE 
where CMC_CINEMA_CODE = '65900402'
and CMC_MONTH = '2015-10'
and CMC_BUSS_TYPE = 'T';

MERGE INTO mytable d
USING (SELECT 1 id, 'x' name from dual) s
ON (d.id = s.id)
WHEN MATCHED THEN UPDATE SET d.name = s.name
WHEN NOT MATCHED THEN INSERT (id, name) VALUES (s.id, s.name);

MERGE INTO CW_MONTH_CLOSE CMC
USING (SELECT '65900402' CMC_CINEMA_CODE, '2015-10' CMC_MONTH,'S' CMC_BUSS_TYPE,'N' CMC_MGR_FLAG from dual) S
ON (CMC.CMC_CINEMA_CODE = S.CMC_CINEMA_CODE and CMC.CMC_MONTH = S.CMC_MONTH and CMC.CMC_BUSS_TYPE=S.CMC_BUSS_TYPE)
WHEN MATCHED THEN UPDATE SET CMC.CMC_MGR_FLAG = S.CMC_MGR_FLAG
WHEN NOT MATCHED THEN INSERT (CMC_CINEMA_CODE, CMC_MONTH,CMC_BUSS_TYPE,CMC_MGR_FLAG) VALUES (S.CMC_CINEMA_CODE,S.CMC_MONTH,S.CMC_BUSS_TYPE,S.CMC_MGR_FLAG);
