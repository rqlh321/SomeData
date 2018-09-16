package com.game.sic.somedata

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.game.sic.somedata.repo.Repo
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        Repo.setup(context)
        Repo.postDao.all().observeForever { println(it.toString()) }
    }

    @Test
    fun useAppContext() {
        runBlocking {
            val posts = Repo.service.posts().await()
            Repo.postDao.insert(posts)
        }
    }

}
