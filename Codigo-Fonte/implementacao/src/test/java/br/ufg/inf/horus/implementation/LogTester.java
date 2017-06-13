/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.implementation.model.Log;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pedro
 */
public class LogTester implements Log{

    private List message;
    
    public LogTester(){
        this.message = new ArrayList<>();
    }
    
    public List<String> getMessages(){
        return this.message;
    }
    
    @Override
    public void info(String message) {
        this.message.add(message);
    }

    @Override
    public void erro(String message) {
        this.message.add(message);
    }
    
}
