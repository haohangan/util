/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 97617
 */
public class BarrierTest2 {
    static class Party extends Thread{
        private int duration;
        private CyclicBarrier barrier;

        public Party(int duration, CyclicBarrier barrier,String name) {
            super(name);
            this.duration = duration;
            this.barrier = barrier;
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(duration);
                System.out.println("thread "+Thread.currentThread().getName() +" is waiting");
                barrier.await();
                
                System.out.println("thread "+Thread.currentThread().getName() +" is running again");
                
            } catch (InterruptedException ex) {
                Logger.getLogger(BarrierTest2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(BarrierTest2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N,() ->{
            System.out.println("hahah");
    });
        for(int i = 0;i<N;i++){
            Party p = new Party(i*1000, barrier, ("barrier"+i));
            new Thread(p).start();
        }
        System.out.println("over");
    }
}


//jieguo
run:
over
thread Thread-0 is waiting
thread Thread-1 is waiting
thread Thread-2 is waiting
thread Thread-3 is waiting
hahah
thread Thread-3 is running again
thread Thread-0 is running again
thread Thread-2 is running again
thread Thread-1 is running again
成功构建 (总时间: 3 秒)
