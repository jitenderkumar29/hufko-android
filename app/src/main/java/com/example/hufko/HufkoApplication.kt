package com.example.hufko

import android.app.Application
import android.util.Log
import coil.Coil
import coil.ImageLoader
import coil.decode.ImageDecoderDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class HufkoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Configure OkHttpClient to handle HTTP connections properly
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .followRedirects(true)
            .followSslRedirects(true)
            .build()

        // Configure Coil ImageLoader
        val imageLoader = ImageLoader.Builder(this)
            .okHttpClient(okHttpClient)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("image_cache"))
                    .maxSizeBytes(100 * 1024 * 1024) // 100MB
                    .build()
            }
            .crossfade(true)
            .allowRgb565(true)
            .logger(DebugLogger())
            .components {
                add(ImageDecoderDecoder.Factory())
            }
            .build()

        Coil.setImageLoader(imageLoader)

        Log.d("HufkoApplication", "Coil ImageLoader configured")
    }
}