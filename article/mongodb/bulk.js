db.getCollection("XX").find({"opTime":null},{opTime:1}).count();

db.getCollection("XX").find(
{ 
    opTime : { $exists: false } 
}
).count();

1:
var cursor = db.getCollection("XX").find({ opTime : { $exists: false } })._addSpecial( "$snapshot", true );

var bulkUpdateOps = [];
cursor.forEach(function(doc){
	var date = new Date();
	bulkUpdateOps.push({
		"updateOne":{
			"filter":{"_id":doc._id},
			"update":{"$set":{"opTime":date}}
		}
	});
	
	if(bulkUpdateOps.length===1000){
	    db.getCollection("LEADS_RESULT").bulkWrite(bulkUpdateOps);
	    bulkUpdateOps = [];
	}    
});

2:
var bulk = db.getCollection("LEADS_RESULT").initializeUnorderedBulkOp(),
counter = 0; // counter to keep track of the batch update size
db.getCollection("LEADS_RESULT").find({"opTime":null}).snapshot().forEach(function(doc){
	var date = new Date();
	bulk.find({"_id":doc._id}).updateOne({
		"$set":{"opTime":date}
	});
	counter++;
	if (counter % 1000 == 0){
		bulk.execute();
	}
	bulk = db.getCollection("LEADS_RESULT").initializeUnorderedBulkOp();
});
