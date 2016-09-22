/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.bkcopy.zookeper;

/**
 * Interface of the policy to use when retrying operations.
 *
 * @author gh
 */
public interface RetryPolicy {

    boolean allowRetry(int retryCount, long elapsedRetryTime);

    long nextRetryWaitTime(int retryCount, long elapsedRetryTime);
}
