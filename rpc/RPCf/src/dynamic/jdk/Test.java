package dynamic.jdk;
/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��24�� ����11:06:31
 * ��˵��
 */
public class Test {
    public static void main(String[] args) {
    	Speak p  = new Person("GH");
    	ObjectInvocation invo = new ObjectInvocation(p);
    	Speak proxy= (Speak) invo.getProxy();
    	proxy.self();
	}
}
