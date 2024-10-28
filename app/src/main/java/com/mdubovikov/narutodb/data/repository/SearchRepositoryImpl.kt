package com.mdubovikov.narutodb.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.mdubovikov.narutodb.data.mapper.toCharacterDetails
import com.mdubovikov.narutodb.data.network.api.ApiService
import com.mdubovikov.narutodb.domain.entity.CharacterDetails
import com.mdubovikov.narutodb.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
) : SearchRepository {
    private val recentQueriesKey = stringSetPreferencesKey("queries")

    override val recentQueries: Flow<List<String>> = dataStore.data
        .map { preferences ->
            preferences[recentQueriesKey]?.toList() ?: emptyList()
        }

    override suspend fun saveQuery(query: String) {
        dataStore.edit { preferences ->
            val queries = preferences[recentQueriesKey]?.toMutableSet() ?: mutableSetOf()
            queries.add(query)
            if (queries.size > 10) {
                queries.remove(queries.first())
            }
            preferences[recentQueriesKey] = queries
        }
    }

    override suspend fun deleteQuery(query: String) {
        dataStore.edit { preferences ->
            val queries = preferences[recentQueriesKey]?.toMutableSet() ?: mutableSetOf()
            queries.remove(query)
            preferences[recentQueriesKey] = queries
        }
    }

    override suspend fun searchCharacterByName(characterName: String): CharacterDetails {
        return apiService.getCharacterByName(characterName).toCharacterDetails()
    }

    override suspend fun searchCharacterById(characterId: Int): CharacterDetails {
        return apiService.getCharacterById(characterId).toCharacterDetails()
    }
}