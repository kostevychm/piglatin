package me.kostevych;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue()
    {
        assertEquals(PigLatinTranslator.convert("Hello"),"Ellohay");
        assertEquals(PigLatinTranslator.convert("apple"),"appleway");
        assertEquals(PigLatinTranslator.convert("stairway"),"stairway");
        assertEquals(PigLatinTranslator.convert("can't"),"antca'y");
        assertEquals(PigLatinTranslator.convert("end."),"endway.");
        assertEquals(PigLatinTranslator.convert("hop.b"),"opbha.y");
        assertEquals(PigLatinTranslator.convert("ho.p.b"),"opbh.a.y");
        assertEquals(PigLatinTranslator.convert("this-thing"),"histay-hingtay");
        assertEquals(PigLatinTranslator.convert("McCloud"),"CcLoudmay");
        assertEquals(PigLatinTranslator.convert("Beach"),"Eachbay");
    }
}
