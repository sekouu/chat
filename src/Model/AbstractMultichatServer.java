/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.net.InetAddress;

/**
 *
 * @author Sekou
 */
public abstract class AbstractMultichatServer {
    private InetAddress bindAddress;
    private int port;
    
    
    public AbstractMultichatServer(InetAddress bindAddress, int port)
    {
        this.bindAddress = bindAddress;
        this.port = port;
    }
    
    
    
}
