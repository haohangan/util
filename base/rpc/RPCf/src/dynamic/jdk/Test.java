package dynamic.jdk;
/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月24日 上午11:06:31
 * 类说明
 */
public class Test {
    public static void main(String[] args) {
    	Speak p  = new Person("GH");
    	ObjectInvocation invo = new ObjectInvocation(p);
    	Speak proxy= (Speak) invo.getProxy();
    	proxy.self();
	}
}
