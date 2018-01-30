#-*- coding: UTF-8 -*-
import hashlib
import json
from time import time
from uuid import uuid4

class BlockChain(object):
    def __init__(self):
        self.chain = []
        self.current_transactions = []
        # Create the genesis block
        self.new_block(proof=1,previous_hash=1)

    def new_block(self,proof,previous_hash=None):
        block={
            'index':len(self.chain)+1,
            'timestamp':time(),
            'transactions':self.current_transactions,
            'proof':proof,
            'previous_hash':previous_hash
        }
        # Reset the current list of transactions
        self.current_transactions = []
        self.chain.append(block)
        return block

    def new_transaction(self,sender,recipent,amount):
        self.current_transactions.append({
            'sender':sender,
            'recipent':recipent,
            'amount':amount
        })

        return self.last_block['index'] + 1

    @staticmethod
    def hash(block):
        block_str = json.dumps(block,sort_keys=True).encode()
        return hashlib.sha256(block_str).hexdigest()
    
    #特殊规则的hash值
    @staticmethod
    def valid_proof(last_proof,proof):
        guess = '%s%s'%(last_proof,proof)
        guess_hash = hashlib.sha256(guess).hexdigest()
        return guess_hash[:4] == "0000"


    @property
    def last_block(self):
        return self.chain[-1]

    def proof_of_work(self,last_proof):
        proof = 0
        while self.valid_proof(last_proof,proof) is False:
            proof += 1
        
        return proof

bc = BlockChain()
print bc
last_proof = bc.last_block['proof']
print last_proof

proof = bc.proof_of_work(last_proof)
print proof