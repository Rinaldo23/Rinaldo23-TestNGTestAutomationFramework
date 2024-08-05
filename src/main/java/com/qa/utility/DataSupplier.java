package com.qa.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataSupplier {

    /**
     * Provides valid login test data.
     *
     * @return Object[][] containing valid login credentials
     */
    @DataProvider(name = "ValidTestData")
    public Object[][] loginData() {
        return new Object[][] {
            { "test12345@gmail.com", "Test@12345" },
            { "test12345@gmail.com", "Test@12345" }
        };
    }

    /**
     * Provides invalid login test data.
     *
     * @return Iterator<String[]> containing invalid login credentials
     */
    @DataProvider(name = "InvalidTestData", indices = { 1, 2 })
    public Iterator<String[]> loginData1() {
        List<String[]> data = new ArrayList<>();
        data.add(new String[] { "test12345@gmail.com", "Test@12345" });
		data.add(new String[] { "test@gmail.com", "Test@12345" });
		data.add(new String[] { "test@gmail.com", "Test@12345" });
		data.add(new String[] { "test12345@gmail.com", "Test@12345" });

        return data.iterator();
    }

    /**
     * Provides parallel test data.
     *
     * @return Iterator<String[]> containing login credentials for parallel execution
     */
    @DataProvider(name = "ParallelTestData", parallel = true)
    public Iterator<String[]> loginData2() {
        List<String[]> data = new ArrayList<>();
        data.add(new String[] { "test12345@gmail.com", "Test@12345" });
		data.add(new String[] { "test12345@gmail.com", "Test@12345" });
		data.add(new String[] { "test12345@gmail.com", "Test@12345" });
		data.add(new String[] { "test12345@gmail.com", "Test@12345" });
		
        return data.iterator();
    }
}