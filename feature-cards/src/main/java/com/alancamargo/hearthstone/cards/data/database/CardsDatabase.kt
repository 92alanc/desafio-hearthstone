package com.alancamargo.hearthstone.cards.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alancamargo.hearthstone.core.data.local.DbCard

@Database(
    entities = [DbCard::class],
    version = 2,
    exportSchema = false
)
internal abstract class CardsDatabase : RoomDatabase() {

    abstract fun getCardsDao(): CardsDao
}
