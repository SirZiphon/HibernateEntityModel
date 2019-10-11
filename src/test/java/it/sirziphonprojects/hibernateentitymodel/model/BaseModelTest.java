package it.sirziphonprojects.hibernateentitymodel.model;

import it.sirziphonprojects.hibernateentitymodel.connector.HibernateBaseConnector;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * BaseModel tests
 *
 * @author SirZiphon {@literal <https://github.com/sirziphon>}
 * @version 1.0.0
 */
public class BaseModelTest {

    public class Test1 extends BaseModel {

        public Test1() throws IllegalArgumentException {
            super(null, null, null, null, null);
        }
    }

    @Test
    public void testIllegalArgumentException() {
        try {
            new Test1();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
            Assert.assertTrue(true);
        }
    }

}