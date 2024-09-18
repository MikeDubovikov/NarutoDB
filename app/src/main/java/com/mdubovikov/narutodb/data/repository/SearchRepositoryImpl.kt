package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.data.mapper.toEntity
import com.mdubovikov.narutodb.data.network.api.ApiService
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.domain.entity.Clan
import com.mdubovikov.narutodb.domain.entity.Kekkeigenkai
import com.mdubovikov.narutodb.domain.entity.Team
import com.mdubovikov.narutodb.domain.entity.Village
import com.mdubovikov.narutodb.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SearchRepository {

    override suspend fun searchCharacterByName(characterName: String): Character {
        return apiService.getCharacterByName(characterName).toEntity()
    }

    override suspend fun searchCharacterById(characterId: Int): Character {
        return apiService.getCharacterById(characterId).toEntity()
    }

    override suspend fun searchAkatsukiByName(akatsukiName: String): Character {
        return apiService.getCharacterByName(akatsukiName).toEntity()
    }

    override suspend fun searchAkatsukiById(akatsukiId: Int): Character {
        return apiService.getCharacterById(akatsukiId).toEntity()
    }

    override suspend fun searchTailedBeastByName(tailedBeastName: String): Character {
        return apiService.getCharacterByName(tailedBeastName).toEntity()
    }

    override suspend fun searchTailedBeastById(tailedBeastId: Int): Character {
        return apiService.getCharacterById(tailedBeastId).toEntity()
    }

    override suspend fun searchKaraByName(karaName: String): Character {
        return apiService.getCharacterByName(karaName).toEntity()
    }

    override suspend fun searchKaraById(karaId: Int): Character {
        return apiService.getCharacterById(karaId).toEntity()
    }

    override suspend fun searchKekkeigenkaiByName(kekkeigenkaiName: String): Kekkeigenkai {
        return apiService.getKekkeigenkaiByName(kekkeigenkaiName).toEntity()
    }

    override suspend fun searchKekkeigenkaiById(kekkeigenkaiId: Int): Kekkeigenkai {
        return apiService.getKekkeigenkaiById(kekkeigenkaiId).toEntity()
    }

    override suspend fun searchClanByName(clanName: String): Clan {
        return apiService.getClanByName(clanName).toEntity()
    }

    override suspend fun searchClanById(clanId: Int): Clan {
        return apiService.getClanById(clanId).toEntity()
    }

    override suspend fun searchTeamByName(teamName: String): Team {
        return apiService.getTeamByName(teamName).toEntity()
    }

    override suspend fun searchTeamById(teamId: Int): Team {
        return apiService.getTeamById(teamId).toEntity()
    }

    override suspend fun searchVillageByName(villageName: String): Village {
        return apiService.getVillageByName(villageName).toEntity()
    }

    override suspend fun searchVillageById(villageId: Int): Village {
        return apiService.getVillageById(villageId).toEntity()
    }
}