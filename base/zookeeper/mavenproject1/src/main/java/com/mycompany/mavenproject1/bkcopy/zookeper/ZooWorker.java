/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.bkcopy.zookeper;

import com.mycompany.mavenproject1.bkcopy.util.MathUtils;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.zookeeper.KeeperException;

/**
 *
 * @author Administrator
 */
public class ZooWorker {

    int attempts = 0;
    long startTimeMs;
    long elapsedTimeMs = 0L;//经过的时间
    final RetryPolicy retryPolicy;

    public ZooWorker(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
        this.startTimeMs = MathUtils.now();
    }

    public boolean allowRetry(int rc) {
        if (!ZooWorker.isRecoverableException(rc)) {//是否为可恢复的错误
            return false;
        }
        ++attempts;
        elapsedTimeMs = MathUtils.now() - startTimeMs;
        return retryPolicy.allowRetry(attempts, elapsedTimeMs);//允许恢复
    }

    public static boolean isRecoverableException(int rc) {
        return KeeperException.Code.CONNECTIONLOSS.intValue() == rc
                || KeeperException.Code.OPERATIONTIMEOUT.intValue() == rc
                || KeeperException.Code.SESSIONMOVED.intValue() == rc
                || KeeperException.Code.SESSIONEXPIRED.intValue() == rc;
    }

    public static boolean isRecoverableException(KeeperException exception) {
        return isRecoverableException(exception.code().intValue());
    }

    static interface ZooCallable<T> {

        T call() throws InterruptedException, KeeperException;
    }

    
    /**
     * 恢复操作的方法
     * @param <T>
     * @param client
     * @param proc
     * @param retryPolicy
     * @return
     * @throws InterruptedException
     * @throws KeeperException 
     */
    public static <T> T syncCallWithRetries(ZookeeperClient client, ZooCallable<T> proc, RetryPolicy retryPolicy) throws InterruptedException, KeeperException {
        T result = null;
        boolean isDone = false;
        int attempts = 0;
        long startTimeMs = MathUtils.now();
        while (!isDone) {//没有完成，则一直循环
           try{
                if (null != client) {
//                client
            }
            Logger.getGlobal().log(Level.INFO, "Execute {0} at {1} retry attempt.", new Object[]{proc, attempts});
            result = proc.call();
            isDone = true;
           }catch(KeeperException e){
               ++attempts;
               boolean rethrow = true;//是否再次抛出异常
               long elapsedTime = MathUtils.now() - startTimeMs;
               if (((null != client && isRecoverableException(e)) || null == client) &&
                        retryPolicy.allowRetry(attempts, elapsedTime)) {
                    rethrow = false;
                }
                if (rethrow) {
                     Logger.getGlobal().log(Level.INFO, "Stopped executing {0} after {1} attempts.", new Object[]{proc, attempts});
                    throw e;
                }
                TimeUnit.MILLISECONDS.sleep(retryPolicy.nextRetryWaitTime(attempts, elapsedTime));
           }
        }
        return result;
    }

    static <T> T syncCallWithRetries(ZooCallable<T> proc, RetryPolicy retryPolicy) throws InterruptedException, KeeperException {
        return syncCallWithRetries(null, proc, retryPolicy);
    }
}
