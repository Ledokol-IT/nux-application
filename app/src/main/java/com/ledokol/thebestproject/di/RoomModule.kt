package com.ledokol.thebestproject.di

import android.content.Context
import androidx.room.Room
import com.ledokol.thebestproject.data.local.MyDatabase
import com.ledokol.thebestproject.data.local.contact.ContactsDao
import com.ledokol.thebestproject.data.local.game.GamesDao
import com.ledokol.thebestproject.data.local.notifications.NotificationsDao
import com.ledokol.thebestproject.data.local.profile.ProfileDao
import com.ledokol.thebestproject.data.local.user.UsersDao
import com.ledokol.thebestproject.data.remote.RetrofitServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): MyDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            MyDatabase::class.java,
            "my_database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()



    @Provides
    @Singleton
    fun provideProfileDao(myDatabase: MyDatabase): ProfileDao = myDatabase.profileDao()

    @Provides
    @Singleton
    fun provideUsersDao(myDatabase: MyDatabase): UsersDao = myDatabase.usersDao()

    @Provides
    @Singleton
    fun provideGamesDao(myDatabase: MyDatabase) : GamesDao = myDatabase.gamesDao()

    @Provides
    @Singleton
    fun provideContactsDao(myDatabase: MyDatabase) : ContactsDao = myDatabase.contactsDao()

    @Provides
    @Singleton
    fun provideNotificationsDao(myDatabase: MyDatabase) : NotificationsDao = myDatabase.notificationDao()

//    @Provides
//    @Singleton
//    fun provideGamesDao(myDatabase: MyDatabase) : StatusRepository = myDatabase.gamesDao()

//    @Provides
//    @Singleton
//    fun provideStatus(statusRepository: )


    @Provides
    @Singleton
    fun provideRetrofitServices(client: OkHttpClient): Retrofit {
//        val BASE_URL = "https://storage.yandexcloud.net/"
//        val BASE_URL = "http://192.168.126.142:8080/" // Для телефона
        val BASE_URL = "http://51.250.100.21/"
//        val BASE_URL = "http://10.0.2.2:8080/" // Для эмулятора

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create())
            .client(client)
            .build()

    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(15, TimeUnit.SECONDS) // connect timeout
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideRetrofitServices2(retrofit: Retrofit): RetrofitServices {
        return retrofit.create(RetrofitServices::class.java)
    }

}