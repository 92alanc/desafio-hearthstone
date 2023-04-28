package com.alancamargo.hearthstone.cards.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alancamargo.hearthstone.core.data.local.DbCard

@Dao
internal interface CardsDao {

    @Query("SELECT * FROM CARDS WHERE faction = :faction")
    suspend fun getCardsByFaction(faction: String): List<DbCard>?

    @Query("SELECT * FROM CARDS WHERE quality = :quality")
    suspend fun getCardsByQuality(quality: String): List<DbCard>?

    @Query("SELECT * FROM CARDS WHERE race = :race")
    suspend fun getCardsByRace(race: String): List<DbCard>?

    @Query("SELECT * FROM CARDS WHERE type = :type")
    suspend fun getCardsByType(type: String): List<DbCard>?

    @Query("SELECT * FROM CARDS WHERE playerClass = :playerClass")
    suspend fun getCardsByPlayerClass(playerClass: String): List<DbCard>?

    @Insert(DbCard::class)
    suspend fun insertCard(card: DbCard)

    @Update(DbCard::class)
    suspend fun updateCard(card: DbCard)

    @Query("DELETE FROM CARDS")
    suspend fun deleteCards()

    @Query("SELECT COUNT() FROM CARDS WHERE name = :name")
    suspend fun getCardsCount(name: String): Int
}
