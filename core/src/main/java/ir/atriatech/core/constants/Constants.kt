package ir.atriatech.core.constants

import ir.atriatech.core.BuildConfig

object Constants {
	const val TAG = "VerboseLog"

	const val API_URL = BuildConfig.BASE_URL
	const val API_URL_UNSAFE = BuildConfig.BASE_URL_UNSAFE
	const val UPLOAD_URL = BuildConfig.UPLOAD_URL
	const val UPLOAD_URL_UNSAFE = BuildConfig.UPLOAD_URL_UNSAFE
	const val DEFAULT_TOKEN = BuildConfig.DEFAULT_TOKEN
	const val TOKEN_PREFIX = BuildConfig.TOKEN_PREFIX
	const val AUTHORIZATION = BuildConfig.AUTHORIZATION
	const val SESSION_FILE_NAME = BuildConfig.SESSION_FILE_NAME
	const val SESSION_PASSWORD = BuildConfig.SESSION_PASSWORD
	const val LOGIN_SESSION_KEY = BuildConfig.LOGIN_SESSION_KEY
	const val TOKEN_SESSION_KEY = BuildConfig.TOKEN_SESSION_KEY
}
