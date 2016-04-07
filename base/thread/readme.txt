代码中:
product.java 和 producer.java
Consume.java 和 Consumer.java
 互相类似，Test类也几乎一样，
不同的地方在于：ResourceCache.java 和 Storage.java
前者使用了 java Lock接口提供的同步工具
后者使用了 wait()/notify()
可以做一下设计，做一个通用的资源的同步队列