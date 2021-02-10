package com.decwujot.countires.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.decwujot.countires.core.repository.RemoteRepository
import com.nhaarman.mockitokotlin2.mock
import org.junit.Rule
import java.lang.RuntimeException

abstract class BaseUnitTest {

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val repository: RemoteRepository = mock()
    val exception = RuntimeException("Something went wrong")
}