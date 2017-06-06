/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.interfaceh;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aluno
 */
public class BsusFactorytest {
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void createBarramentoCorretoTest() throws Exception {
        String className = "br.ufg.inf.horus.implementation.Bsus";
        assertNotNull(BsusFactory.createBarramento(className, null));
    }
    
    @Test(expected = java.lang.ClassNotFoundException.class)
    public void createBarramentoFalhoTest() throws Exception {
        String className = "br.ufg.inf.horus.implementation.FalsoBsus";
        assertNotNull(BsusFactory.createBarramento(className, null));
    }
}
