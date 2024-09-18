package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.domain.entity.Clan
import com.mdubovikov.narutodb.domain.entity.Kekkeigenkai
import com.mdubovikov.narutodb.domain.entity.Team
import com.mdubovikov.narutodb.domain.entity.Village

interface SearchRepository {

    suspend fun searchCharacterByName(characterName: String): Character

    suspend fun searchCharacterById(characterId: Int): Character

    suspend fun searchAkatsukiByName(akatsukiName: String): Character

    suspend fun searchAkatsukiById(akatsukiId: Int): Character

    suspend fun searchTailedBeastByName(tailedBeastName: String): Character

    suspend fun searchTailedBeastById(tailedBeastId: Int): Character

    suspend fun searchKaraByName(karaName: String): Character

    suspend fun searchKaraById(karaId: Int): Character

    suspend fun searchKekkeigenkaiByName(kekkeigenkaiName: String): Kekkeigenkai

    suspend fun searchKekkeigenkaiById(kekkeigenkaiId: Int): Kekkeigenkai

    suspend fun searchClanByName(clanName: String): Clan

    suspend fun searchClanById(clanId: Int): Clan

    suspend fun searchTeamByName(teamName: String): Team

    suspend fun searchTeamById(teamId: Int): Team

    suspend fun searchVillageByName(villageName: String): Village

    suspend fun searchVillageById(villageId: Int): Village
}