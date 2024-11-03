package com.xxl.job.admin.core.thread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.locks.Lock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class JobScheduleHelperTest {

    private JobScheduleHelper jobScheduleHelper;
    private Lock distributedLock;

    @BeforeEach
    public void setUp() {
        jobScheduleHelper = JobScheduleHelper.getInstance();
        distributedLock = mock(Lock.class);
        jobScheduleHelper.setDistributedLock(distributedLock);
    }

    @Test
    public void testDistributedLockMechanism() {
        // Simulate acquiring the lock
        doNothing().when(distributedLock).lock();

        // Simulate releasing the lock
        doNothing().when(distributedLock).unlock();

        // Call the start method to test the distributed lock mechanism
        jobScheduleHelper.start();

        // Verify that the lock was acquired and released
        verify(distributedLock, times(1)).lock();
        verify(distributedLock, times(1)).unlock();
    }

    @Test
    public void testSingleAdminServerScheduling() {
        // Simulate acquiring the lock
        doNothing().when(distributedLock).lock();

        // Simulate releasing the lock
        doNothing().when(distributedLock).unlock();

        // Call the start method to test the distributed lock mechanism
        jobScheduleHelper.start();

        // Verify that the lock was acquired and released
        verify(distributedLock, times(1)).lock();
        verify(distributedLock, times(1)).unlock();

        // Ensure that only one admin server can schedule jobs at a time
        assertTrue(jobScheduleHelper.isSingleAdminServerScheduling());
    }
}
