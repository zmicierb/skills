package com.barysevich.project.controller;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by BarysevichD on 2017-06-15.
 */
@Ignore
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
