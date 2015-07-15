/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securemetrix;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author test
 */
public class StringApplicationTest {    
    @Test
    public void testReplaceLine() {
        StringApplication app = new StringApplication();
        String inputString;
        String expResult;
        String result;

        inputString = "abcdaabcdeabaaacbfaaaabcab";
        expResult = "#bcda$bcde#ba$$cbfa$$$bc#b";
        result = app.replaceLine(inputString);
        assertEquals(expResult, result);

        inputString = "123aabcaabca35aa";
        expResult = "123a$bca$bc#35a$";
        result = app.replaceLine(inputString);
        assertEquals(expResult, result);

    }

}
