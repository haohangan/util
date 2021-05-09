import asyncio
from datetime import datetime
from elasticsearch import AsyncElasticsearch


class CaseInfoEs():
    def __init__(self):
        self.es = AsyncElasticsearch(
            ['http://elastic:password@ip:port/'], sniffer_timeout=60*1000*1000)

    def printes(self):
        print(self.es)

    def insert(self):
        doc = {
            'author': 'jakc',
            'text': 'Elasticsearch: cool. bonsai cool.',
            'timestamp': datetime.now(),
        }
        res = self.es.index(index="test-index", id=2, body=doc)
        print(res['result'])

    # 测试更新操作
    def update(self):
        # match_all = {
        #     "script": {
        #         "source": "ctx._source.text='WAIJBO'",
        #         "lang": "painless"
        #     },
        #     "query": {
        #         "match_all": {}
        #     }
        # }
        body = {
            "script": {
                "lang": "painless",
                "source": """
                ctx._source.text = 'aaa';
                ctx._source.author = 'aaa';
                """
            },
            "query": {
                "match_all": {}
            }
        }

        self.es.update_by_query(
            index="test-index", body=body)

    # 更新es
    async def upadte_case(self):
        params = {
            "type_num": 1,
            "types": ['type1'],
            "tag_num": 1,
            "tags": ['tag1'],
            "pro_num": 1,
            "pro_tags": ['tag1'],
            "pro_handle_num": 1
        }
        body = {
            "script": {
                "source": """
                ctx._source.type_num=params.type_num;
                ctx._source.types.add(params.types);
                ctx._source.tag_num=params.tag_num;
                ctx._source.tags.add(params.tags);
                ctx._source.pro_num=params.pro_num;
                ctx._source.pro_tags.add(params.pro_tags);
                ctx._source.pro_handle_num=params.pro_handle_num;
                """,
                "lang": "painless"
            },
            "query": {
                "match_all": {
                }
            }
        }
        body['script']['params'] = params
        resp = await self.es.update_by_query(
            index="test-index", body=body)
        print(resp)


def main():
    client = CaseInfoEs()
    loop = asyncio.get_event_loop()
    loop.run_until_complete(client.upadte_case())


if __name__ == '__main__':
    main()
