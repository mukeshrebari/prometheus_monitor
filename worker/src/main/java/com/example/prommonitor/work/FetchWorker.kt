package com.example.prommonitor.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.prommonitor.storage.dao.EndpointDao
import com.example.prommonitor.network.PrometheusApi

class FetchWorker(
    ctx: Context,
    params: WorkerParameters,
    private val endpointDao: EndpointDao,
    private val api: PrometheusApi
) : CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val endpoints = endpointDao.getAllOnce()
        endpoints.forEach { /* fetch and store data */ }
        Result.success()
    }
}
