1：wsdl文件解释：http://w3school.com.cn/wsdl/wsdl_documents.asp
   definitions:顶级
   types:数据类型
   message:消息
   portType:操作
   bingding:协议

2：注解使用
   service.GetNameService 接口中如果存在同名方法，则需要在@WebMethod(operationName = "getName"),加上operationName属性

3：使用jdk自带工具wsimport生成客户端
   wsimport生成客户端代码：win7的cmd中
   D:\nws\WebServiceClientDemo>"%JAVA_HOME%\bin\wsimport" -keep -d .\src -p test.client http://localhost:9000/count?wsdl

4：wsdl文件：wsdl.xml