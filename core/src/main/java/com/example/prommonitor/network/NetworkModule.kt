package com.example.prommonitor.network

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

object NetworkModule {
    fun createClient(
        baseUrl: String,
        certificatePinner: CertificatePinner? = null,
        sslSocketFactory: SSLSocketFactory? = null,
        trustManager: X509TrustManager? = null
    ): PrometheusApi {
        val builder = OkHttpClient.Builder()
        certificatePinner?.let { builder.certificatePinner(it) }
        if (sslSocketFactory != null && trustManager != null) {
            builder.sslSocketFactory(sslSocketFactory, trustManager)
        }
        val client = builder.build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PrometheusApi::class.java)
    }
}
