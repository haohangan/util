1��wsdl�ļ����ͣ�http://w3school.com.cn/wsdl/wsdl_documents.asp
   definitions:����
   types:��������
   message:��Ϣ
   portType:����
   bingding:Э��

2��ע��ʹ��
   service.GetNameService �ӿ����������ͬ������������Ҫ��@WebMethod(operationName = "getName"),����operationName����

3��ʹ��jdk�Դ�����wsimport���ɿͻ���
   wsimport���ɿͻ��˴��룺win7��cmd��
   D:\nws\WebServiceClientDemo>"%JAVA_HOME%\bin\wsimport" -keep -d .\src -p test.client http://localhost:9000/count?wsdl

4��wsdl�ļ���wsdl.xml