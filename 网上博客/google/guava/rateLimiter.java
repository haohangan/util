package guava;

import com.google.common.util.concurrent.RateLimiter;

public class GuavaTest {

    RateLimiter limiter;

    public GuavaTest() {
        this.limiter = RateLimiter.create(5.0);
    }

    public String limitService(){
        if(limiter.tryAcquire()){
            return "success";
        }else{
             return "error";
        }
    }

    public static void main(String[] args) throws InterruptedException {
        GuavaTest test = new GuavaTest();//测试令牌桶算法
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            System.out.println(test.limitService());
//            Thread.sleep(100);
        }
    }
}
