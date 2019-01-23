package ir.atriatech.core.networking

import android.content.SharedPreferences
import ir.atriatech.core.constants.Constants.AUTHORIZATION
import ir.atriatech.core.constants.Constants.DEFAULT_TOKEN
import ir.atriatech.core.constants.Constants.TOKEN_PREFIX
import ir.atriatech.core.constants.Constants.TOKEN_SESSION_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MyServiceInterceptor @Inject constructor(private val sharedPreferences: SharedPreferences) : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()
		val builder = request.newBuilder()

		val authorization = sharedPreferences.getString(TOKEN_SESSION_KEY, DEFAULT_TOKEN)!!

		builder.header("Cache-Control", "no-cache")
		builder.header("User-Agent", "Baban-Android")
		builder.header(AUTHORIZATION, TOKEN_PREFIX + authorization)

		return chain.proceed(builder.build())
	}
}