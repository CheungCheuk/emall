package com.cheung.emall.util;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

import javax.swing.JOptionPane;

/**
 * PortUtil：判断某个端口是否启动。主要用于检查 Redis 是否启动
 */
public class PortUtil {
    /**
     * 
     */
    public static boolean testPort(int port) {
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.close();
            return true;
        }catch(BindException e){
            return true;
        }catch(IOException e){
            return false;
        }
    }

    /**
     * 判断端口是否启动
     */
    public static void checkPort(int port, String server, boolean shutdown) {
        if ( testPort(port) ) {
            if ( shutdown ){
                String message = String.format("在端口 %d 未检查到 %s 启动 %n", port,server);
                JOptionPane.showMessageDialog(null, message);
                System.exit(1);
            }else{
                String message = String.format("在端口 %d 未检查得到 %s 启动 %n，是否继续", port,server);
                if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(null, message)) {
                    System.exit(1);
                }
            }
        }
    }
    
}