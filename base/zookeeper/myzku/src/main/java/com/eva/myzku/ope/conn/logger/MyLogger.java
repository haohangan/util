/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eva.myzku.ope.conn.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 97617
 */
public class MyLogger {
    static Logger LOG = Logger.getGlobal();
    static{
        LOG.setLevel(Level.INFO);
    }
    
    public static void info(String msg){
        LOG.info(msg);
    }
}
