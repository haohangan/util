

select 
sum(case when age=25 and salary > 5000 then 1 else 0 end) as age25,
sum(case when age=35 and salary > 5000 then 1 else 0 end) as age35,
sum(case when age=45 and salary > 5000 then 1 else 0 end) as age45,
count(id)-sum(case when age=25 and salary > 5000 then 1 else 0 end)-sum(case when age=35 and salary > 5000 then 1 else 0 end)-sum(case when age=45 and salary > 5000 then 1 else 0 end) as ageelse

from bigtable.minemployees;

select count(id) from  bigtable.minemployees
where age = 25 and salary > 5000