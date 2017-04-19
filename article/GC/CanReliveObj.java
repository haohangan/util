package wrench.zz;

public class CanReliveObj {

	static CanReliveObj obj;

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("CanReliveObj finalize obj");
		obj=this;//看看是否会出现循环引用
	}
	
	public static void main(String[] args) throws InterruptedException {
		obj = new CanReliveObj();
		
		obj=null;
		System.gc();
		
		Thread.sleep(1000);
		
		if(obj==null){
			System.out.println("obj is null");
		}else{
			System.out.println("obj 可用");
		}
		
		System.out.println("第二次gc");
		obj = null;
		System.gc();
		
		Thread.sleep(1000);
		if(obj==null){
			System.out.println("obj is null");
		}else{
			System.out.println("obj 可用");
		}
	}
	
}
