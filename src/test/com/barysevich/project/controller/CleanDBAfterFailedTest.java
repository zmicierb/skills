package com.barysevich.project.controller;

import org.junit.Test;

/**
 * Created by BarysevichD on 2017-06-15.
 */
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
