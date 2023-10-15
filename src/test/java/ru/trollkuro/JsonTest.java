package ru.trollkuro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.trollkuro.model.PersonalData;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonTest {
    ClassLoader cl = JsonTest.class.getClassLoader();
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void jsonTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("PersonalData.json");
             Reader reader = new InputStreamReader(stream)) {
            PersonalData personalData = mapper.readValue(reader, PersonalData.class);
            //check [cars] property size
            Assertions.assertEquals(3, personalData.getCars().size());
            //check [family.pet] property
            Assertions.assertEquals("cat", personalData.getFamily().getPet().toString());
            //check [siblings] property
            Assertions.assertTrue(personalData.getFamily().getHasSiblings());
        }
    }
}
