package current.mb;

public class TestUnsafeException {

/**
 * �׳�����쳣��������
����һЩ������Ȥ�ķ���������Unsafe���ҵ���
 ����û����Ҫ��һ���ϵ͵Ĳ����׳�һ��������쳣��������ĸ߲�ӿ�����û����������������쳣��
  Unsafe#throwException������������	
 * @param args
 */
	public static void main(String[] args) {
		throwChecked();
	}
	
	 public static void throwChecked(){
		 TestUnSafe.getUnsafe().throwException(new Exception("ɶ"));
	} 
}
