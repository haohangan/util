package concurrent;

public class MyResources {
    private String[] res;
    private int current;

    public MyResources(int number) {
        res = new String[number];
        current = -1;
    }

    public  void produce(String obj) throws InterruptedException {
        synchronized (this){
            while(current==res.length-1){
                System.out.println("资源池已满，等待消费者");
                this.wait();
            }
            current++;
            res[current] = obj;
            System.out.printf("生产了新资源：%s,current:%d\n",obj,current);
            this.notify();
        }
    }


    public void consume() throws InterruptedException {
        synchronized (this){
            while(current==-1){
                System.out.println("资源池已空，等待生产者");
                this.wait();
            }
            String obj = res[current];
            current--;
            System.out.printf("消费了新资源：%s,current:%d\n",obj,current);
            this.notify();
        }
    }


    public static void main(String[] args) {
        MyResources res = new MyResources(10);
        Thread produce = new Thread(()->{
            int i = 0;
            while(true){
                try {
                    Thread.sleep(1000);
                    res.produce(""+(i++));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread consumer = new Thread(()->{
            while(true){
                try {
                    Thread.sleep(500);
                    res.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        produce.start();
        consumer.start();
    }
}
