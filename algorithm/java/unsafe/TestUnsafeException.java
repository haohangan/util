package current.mb;

public class TestUnsafeException {

/**
 * 抛出检查异常，无声明
还有一些其他有趣的方法可以在Unsafe中找到。
 你有没有想要在一个较低的层中抛出一个特殊的异常，但是你的高层接口类型没有声明这个被检查的异常？
  Unsafe#throwException允许这样做：	
 * @param args
 */
	public static void main(String[] args) {
		throwChecked();
	}
	
	 public static void throwChecked(){
		 TestUnSafe.getUnsafe().throwException(new Exception("啥"));
	} 
}
