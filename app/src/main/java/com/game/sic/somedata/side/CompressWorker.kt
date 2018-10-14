package com.game.sic.somedata.side

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class CompressWorker(context: Context, params: WorkerParameters)
    : Worker(context, params) {

    override fun doWork(): Result {

        // Do the work here--in this case, compress the stored images.
        // In this example no parameters are passed; the task is
        // assumed to be "compress the whole library."

        println("work")
        return Result.SUCCESS


        // Indicate success or failure with your return value:

        // (Returning RETRY tells WorkManager to try this task again
        // later; FAILURE says not to try again.)

    }

}