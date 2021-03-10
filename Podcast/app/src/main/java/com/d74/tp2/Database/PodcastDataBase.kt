package com.d74.tp2.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.d74.tp2.Dao.EpisodeDao
import com.d74.tp2.Dao.PodcastDao
import com.d74.tp2.Models.Episode
import com.d74.tp2.Models.Podcast


@Database(entities = [Podcast::class, Episode::class], version = 5, exportSchema = false)
abstract class PodcastDataBase : RoomDatabase() {

    abstract fun PodcastDao(): PodcastDao
    abstract fun EpisodeDao(): EpisodeDao

    companion object {
        @Volatile
        private var singletonDb: PodcastDataBase? = null

        fun getDatabase(context: Context): PodcastDataBase {
            return singletonDb ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, PodcastDataBase::class.java, "Podcasts_database"
                ).fallbackToDestructiveMigration().addCallback(PodcastDataBaseCallback()).build()
                singletonDb = instance
                instance
            }
        }

        private class PodcastDataBaseCallback() : RoomDatabase.Callback() {
            override fun onOpen(dbParam: SupportSQLiteDatabase) {
                super.onOpen(dbParam)
                singletonDb?.let { database ->
                    populateDatabase(database.PodcastDao())
                }
            }
        }

        fun populateDatabase(PodcastDao: PodcastDao) {
        }
    }
}