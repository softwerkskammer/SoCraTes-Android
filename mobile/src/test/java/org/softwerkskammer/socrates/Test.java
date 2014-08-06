package org.softwerkskammer.socrates;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class Test {

    @org.junit.Test
    public void failingTest() throws Exception {

        assertThat("SoCraTes", is(equalTo("SoCraTes")));
    }
}
