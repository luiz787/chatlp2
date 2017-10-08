/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import controller.ChatController;

/**
 *
 * @author Luiz
 */
public abstract class Observer {
    protected ChatController subject;
    public abstract void update();
}
