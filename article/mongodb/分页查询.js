db.getCollection('messages').find({});

//ȥ��ĳЩ�ֶ�
db.getCollection('messages').find(
{'bussId':'XMMXMXMXMX'},
{'details':0}
);

//��ҳ
db.getCollection('messages').find(
{'bussId':'XMMXMXMXMX'},
{'details':{'$slice':[0,50]}}
);

Query query = new Query();
query.addCriteria(Criteria.where("chatId").is("1"));
query.fields().slice("details", 0, 5);
DBMessage d = template.findOne(query, DBMessage.class);
System.out.println(d.getDetails().size());


