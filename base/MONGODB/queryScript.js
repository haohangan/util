db.getCollection('messages').find({'_id':'0','details':{'$elemMatch':{'status':'1'}}},{'_id':0,'details.$':4});

db.getCollection('messages').find({'_id':'0','details':{'$elemMatch':{'status':'1'}}});

db.getCollection('messages').find({'_id':'0'});

db.version();
##,'details.status':'1'

db.getCollection('messages').aggregate(
{$match:{'_id':'0'}},
{$project:{
    status:{$filter:{
                   input:'$details',
                   as:'detail',
                   cond:{$eq:['$$detail.status','1']}
           }},
           _id:0
}}
);

#################ok

db.test.aggregate(
  // Start with a $match pipeline which can take advantage of an index and limit documents processed
  { $match : {
     "shapes.color": "red"
  }},
  { $unwind : "$shapes" },
  { $match : {
     "shapes.color": "red"
  }}
);
  
  db.getCollection('messages').aggregate(
  {$match:{'_id':'0'}},
   {$project:{'_id':0,"details": 1}},
   {$unwind:{path:'$details'}},
   {$match:{'details.status':'1'}}
  );

################
   db.getCollection('messages').find({ _id: '0' })
  .forEach(function (doc) {
    doc.details.forEach(function (event) {

        event.status='0';

    });
    db.getCollection('messages').save(doc);
  });