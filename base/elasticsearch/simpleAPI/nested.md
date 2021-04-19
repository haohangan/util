## 创建索引

put /index1

~~~json
{
	"mappings": {
		"properties": {
			"id": {
				"type": "keyword"
			},
			"case_code": {
				"type": "keyword"
			},
			"case_tags": {
				"type": "nested",
				"properties": {
					"tag_desc": {
						"type": "text",
						"fields": {
							"raw": {
								"type": "keyword"
							}
						},
						"analyzer": "standard"
					},
					"tag_key": {
						"type": "keyword"
					}
				}
			}
		}
	}
}
~~~

## 新增记录

POST /index1/_doc/_bulk

~~~json
{"index":{"_id":"1"}}
{"case_code":"xx01","case_tags":{"tag_desc":["A","B","C"],"tag_key":["001","002","003"]}}
{"index":{"_id": "2"}}
{"case_code":"xx02","case_tags":{"tag_desc":["A","B"],"tag_key":["001","002"]}}
{"index":{"_id":"3"}}
{"case_code":"xx01","case_tags":{"tag_desc":["B","C","D"],"tag_key":["002","003","004"]}}
{"index":{"_id": "4"}}
{"case_code":"xx02","case_tags":{"tag_desc":["D","E","F"],"tag_key":["004","005","006"]}}

~~~

## nested查询

query

~~~json
{
  "query": {
    "bool": {
      "must": [
        {
          "nested": {
            "path": "case_tags",
            "query": {
              "bool": {
                "must": [
                  {
                    "match": {
                      "case_tags.tag_desc": "A"
                    }
                  },
                  {
                    "match": {
                      "case_tags.tag_desc": "B"
                    }
                  }
                ]
              }
            }
          }
        }
      ]
    }
  }
}
~~~



filter

~~~json
{
  "query": {
    "bool": {
      "filter": [
        {
          "nested": {
            "path": "case_tags",
            "query": {
              "bool": {
                "filter": [
                  {
                    "match": {
                      "case_tags.tag_desc": "A"
                    }
                  },
                  {
                    "match": {
                      "case_tags.tag_desc": "B"
                    }
                  },
                  {
                    "match": {
                      "case_tags.tag_desc": "C"
                    }
                  }
                ]
              }
            }
          }
        }
      ]
    }
  }
}
~~~

