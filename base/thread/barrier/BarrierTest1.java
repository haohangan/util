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
public class BarrierTest1 {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(11, new Runnable(){
            @Override
            public void run() {
                System.out.println("go barrier");
            }
        });
        for(int i = 0;i<23;i++){
            Runnable t = new Runnable(){
                @Override
                public void run() {
                    try {
                        barrier.await();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BarrierTest1.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (BrokenBarrierException ex) {
                        Logger.getLogger(BarrierTest1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(Thread.currentThread().getName() + " show!!");
                }
            };
            new Thread(t).start();
        }
    }
}
