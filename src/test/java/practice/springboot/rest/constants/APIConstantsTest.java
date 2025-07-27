package practice.springboot.rest.constants;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

public class APIConstantsTest {

    @Test
    void testPrivateConstructor() throws Exception {
        Constructor<APIConstants> constructor = APIConstants.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

}
