package com.mdubovikov.narutodb.data.network.dto

import com.mdubovikov.narutodb.data.network.dto.common.TransformJsonReturnEmptyObjectPersonal
import com.mdubovikov.narutodb.data.network.dto.common.TransformJsonReturnEmptyObjectRank
import com.mdubovikov.narutodb.data.network.dto.common.TransformJsonStringOrListSerializer
import com.mdubovikov.narutodb.data.network.dto.common.TransformJsonStringToListSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String? = null,
    val images: List<String>? = emptyList(),
    val family: FamilyDto? = null,
    val jutsu: List<String>? = null,
    @Serializable(TransformJsonReturnEmptyObjectPersonal::class)
    val personal: PersonalDto? = null,
    @Serializable(TransformJsonReturnEmptyObjectRank::class)
    val rank: RankDto? = null,
    val tools: List<String>? = null
)

@Serializable
data class PersonalDto(
    val birthdate: String? = null,
    val sex: String? = null,
    val status: String? = null,
    val age: AgeDto? = null,
    val height: HeightDto? = null,
    val weight: WeightDto? = null,
    val bloodType: String? = null,
    @Serializable(TransformJsonStringToListSerializer::class)
    val classification: List<String>? = null,
    @Serializable(TransformJsonStringToListSerializer::class)
    val affiliation: List<String>? = null,
    @Serializable(TransformJsonStringOrListSerializer::class)
    val occupation: List<String>? = null,
    @Serializable(TransformJsonStringOrListSerializer::class)
    val partner: List<String>? = null,
    @Serializable(TransformJsonStringToListSerializer::class)
    val team: List<String>? = null,
    @Serializable(TransformJsonStringToListSerializer::class)
    val clan: List<String>? = null
)

@Serializable
data class AgeDto(
    @SerialName("Part I")
    val partI: String? = null,
    @SerialName("Part II")
    val partII: String? = null
)

@Serializable
data class HeightDto(
    @SerialName("Part I")
    val partI: String? = null,
    @SerialName("Part II")
    val partII: String? = null
)

@Serializable
data class WeightDto(
    @SerialName("Part I")
    val partI: String? = null,
    @SerialName("Part II")
    val partII: String? = null
)

@Serializable
data class RankDto(
    val ninjaRank: NinjaRankDto? = null,
    val ninjaRegistration: String? = null
)

@Serializable
data class NinjaRankDto(
    @SerialName("Part I")
    val partI: String? = null,
    @SerialName("Part II")
    val partII: String? = null
)

@Serializable
data class FamilyDto(
    val mother: String? = null,
    val father: String? = null,
    val sister: String? = null,
    val brother: String? = null,
    val daughter: String? = null,
    val son: String? = null,
    val wife: String? = null,
    val husband: String? = null,
    val granddaughter: String? = null,
    val grandfather: String? = null,
    val spouse: String? = null,
    @SerialName("depowered form")
    val depoweredform: String? = null,
    @SerialName("incarnation with the god tree")
    val incarnationWithTheGodtree: String? = null
)