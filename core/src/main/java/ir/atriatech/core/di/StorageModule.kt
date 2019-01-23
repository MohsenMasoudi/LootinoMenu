package ir.atriatech.core.di

import android.content.Context
import android.content.SharedPreferences
import com.securepreferences.SecurePreferences
import dagger.Module
import dagger.Provides
import ir.atriatech.core.constants.Constants.SESSION_FILE_NAME
import ir.atriatech.core.constants.Constants.SESSION_PASSWORD
import javax.inject.Singleton

@Module
class StorageModule {
	@Provides
	@Singleton
	fun providesSharedPreferences(context: Context): SharedPreferences {
		return SecurePreferences(context, SESSION_PASSWORD, SESSION_FILE_NAME)
	}
}