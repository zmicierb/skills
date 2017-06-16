package com.barysevich.project.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by BarysevichD on 2017-06-15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CleanDBAfterFailedTest extends PopulateDBTest {

    @Override
    public void populateDB() {
        //just clean, without insert
    }

    @Test
    public void launch() throws Exception {
        //empty method for running Before and After methods
    }
}
