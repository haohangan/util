#blick.py
#-*- coding: UTF-8 -*-
import hashlib
import uuid

class Block(object):
    def __init__(self,data=None,previous_hash=None):
        self.identifier = uuid.uuid4().get_hex() #唯一标识
        self.nonce = None #工作量证明
        self.data = data
        self.previous_hash = previous_hash #前一个节点的hash值

    #找到符合规则的哈希数，nonce是工作量
    def hash(self,nonce=None):
        '''
        计算hash值
        '''
        message = hashlib.sha256()
        message.update(self.identifier)
        message.update(str(nonce).encode('utf-8'))
        message.update(str(self.data).encode('utf-8'))
        message.update(str(self.previous_hash).encode('utf-8'))
        return message.hexdigest()
    
    #校验规则
    def vaildHash(self,hash_val):
        '''
        校验hash
        '''
        return hash_val.startswith('0000')
    
    #输出自己
    def __repr__(self):
        return "Block<Hash:{},Nonce:{}>".format(self.hash(),self.nonce)

    #工作函数，工作后产生工作量证明
    def mine(self):
        cur_nonce = 0
        while True:
            the_hash = bc.hash(nonce=cur_nonce)
            if(bc.vaildHash(the_hash)):
                self.nonce = cur_nonce
                break
            else:
                cur_nonce+=1

bc = Block('hello World')
bc.mine()
print bc