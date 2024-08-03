package com.core.solution.utils;


import static org.junit.Assert.assertEquals;

import java.util.Date;
import org.junit.jupiter.api.Test;

class UtilsTests {

    @Test
    void formatDateTest() {
    	Date date = new Date();
    	String dateString  = Utils.formatDate(date);
        assertEquals(dateString, Utils.formatDate(date));
    }
    
}
