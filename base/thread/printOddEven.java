package concurrent;

public class JOSJTDY {

    int current;

    public JOSJTDY(int current) {
        this.current = current;
    }

    /**
     * 打印奇数
     * @throws InterruptedException
     */
    public void printOdd() throws InterruptedException {
        synchronized (this){
            if(current%2==0){
                this.wait();
            }
            System.out.println("odd:"+current);
            current++;
            this.notify();
        }
    }

    public void printEven() throws InterruptedException {
        synchronized (this){
            if(current%2!=0){
                this.wait();
            }
            System.out.println("even:"+current);
            current++;
            this.notify();
        }
    }

    public static void main(String[] args) {
        JOSJTDY j = new JOSJTDY(0);

        Thread odd = new Thread(()->{
            try {
                while(true){
                    Thread.sleep(1000);
                    j.printOdd();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread even = new Thread(()->{
            try {
                while(true){
                    Thread.sleep(1000);
                    j.printEven();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        odd.start();
        even.start();
    }
}
