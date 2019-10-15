package it.sirziphonprojects.hibernateentitymodel;

import it.sirziphonprojects.hibernateentitymodel.customidkey.BaseModel;
import org.junit.Assert;
import org.junit.Test;

/**
 * BaseModel tests
 *
 * @author SirZiphon {@literal <https://github.com/sirziphon>}
 * @version 1.0.0
 */
public class BaseModelTest {

    public class Test1 extends it.sirziphonprojects.hibernateentitymodel.customidkey.BaseModel {

        public Test1() throws IllegalArgumentException {
            super(null, null, null, null, null);
        }
    }

    public class Test2 extends it.sirziphonprojects.hibernateentitymodel.singleidkey.BaseModel {

        public Test2() throws IllegalArgumentException {
            super(null, null, null, null, null);
        }
    }

    @Test
    public void test1IllegalArgumentException() {
        try {
            new Test1();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
            Assert.assertTrue(true);
        }
    }

    @Test
    public void test2IllegalArgumentException() {
        try {
            new Test2();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
            Assert.assertTrue(true);
        }
    }
}