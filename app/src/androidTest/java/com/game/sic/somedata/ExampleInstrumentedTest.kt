package com.game.sic.somedata

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.game.sic.somedata.repo.local.model.Post
import com.game.sic.somedata.repo.Repo
import kotlinx.coroutines.experimental.async
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        Repo.setup(context)
    }

    @Test
    fun useAppContext() {
        val signal = CountDownLatch(1)

        async {
            Repo.database.postDao().all().observeForever {
                println(it.toString())
            }

            Repo.database.postDao().insert(Post())

            val posts = Repo.service.posts().await()

            Repo.database.postDao().insert(posts)

            signal.countDown()

        }

        signal.await()
    }
}
