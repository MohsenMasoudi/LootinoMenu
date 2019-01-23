package ir.atriatech.core.di

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import ir.atriatech.core.BuildConfig
import ir.atriatech.core.constants.Constants
import ir.atriatech.core.constants.Constants.TAG
import ir.atriatech.core.networking.MyServiceInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module(includes = [StorageModule::class])
class NetworkModule {
	@Provides
	@Singleton
	fun providesRetrofit(
			gsonConverterFactory: GsonConverterFactory,
			rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
			okHttpClient: OkHttpClient
	): Retrofit {
		Log.d(TAG, "retrofit Created")
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT_WATCH) {
			return Retrofit.Builder().baseUrl(Constants.API_URL_UNSAFE)
					.addConverterFactory(gsonConverterFactory)
					.addCallAdapterFactory(rxJava2CallAdapterFactory)
					.client(okHttpClient)
					.build()
		}
		return Retrofit.Builder()
				.baseUrl(Constants.API_URL)
				.addConverterFactory(gsonConverterFactory)
				.addCallAdapterFactory(rxJava2CallAdapterFactory)
				.client(okHttpClient)
				.build()
	}

	@Provides
	@Singleton
	fun providesOkHttpClient(cache: Cache, interceptor: MyServiceInterceptor): OkHttpClient {
		val client = OkHttpClient.Builder()
				.connectTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(30, TimeUnit.SECONDS)
				.readTimeout(10, TimeUnit.SECONDS)
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH && Constants.API_URL.startsWith("https://")) {
			try {
				val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
					@SuppressLint("TrustAllX509TrustManager")
					@Throws(CertificateException::class)
					override fun checkClientTrusted(chain: Array<X509Certificate>,
					                                authType: String) {
					}

					@SuppressLint("TrustAllX509TrustManager")
					@Throws(CertificateException::class)
					override fun checkServerTrusted(chain: Array<X509Certificate>,
					                                authType: String) {
					}

					override fun getAcceptedIssuers(): Array<X509Certificate?> {
						return arrayOfNulls(0)
					}
				})
				val sslContext = SSLContext.getInstance("SSL")
				sslContext.init(null, trustAllCerts, java.security.SecureRandom())
				val sslSocketFactory = sslContext.socketFactory

				client.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
						.hostnameVerifier { _, _ -> true }

			} catch (e: Exception) {
				throw RuntimeException(e)
			}
		}

		if (BuildConfig.DEBUG)
			client.addNetworkInterceptor(StethoInterceptor())

		if (BuildConfig.DEBUG)
			client.cache(cache)

		client.addInterceptor(interceptor)

		return client.build()
	}

	@Provides
	@Singleton
	fun providesOkhttpCache(context: Context): Cache {
		val cacheSize = 10 * 1024 * 1024 // 10 MB
		return Cache(context.cacheDir, cacheSize.toLong())
	}

	@Provides
	@Singleton
	fun providesGson(): Gson {
		return Gson()
	}

	@Provides
	@Singleton
	fun providesGsonConverterFactory(): GsonConverterFactory {
		return GsonConverterFactory.create()
	}

	@Provides
	@Singleton
	fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
		return RxJava2CallAdapterFactory.create()
	}
}