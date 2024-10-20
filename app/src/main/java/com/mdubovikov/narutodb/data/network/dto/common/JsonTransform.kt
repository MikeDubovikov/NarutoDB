package com.mdubovikov.narutodb.data.network.dto.common

import com.mdubovikov.narutodb.data.network.dto.PersonalDto
import com.mdubovikov.narutodb.data.network.dto.RankDto
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonTransformingSerializer
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.serializer

object TransformJsonStringToListSerializer :
    JsonTransformingSerializer<List<String>>(serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement =
        if (element !is JsonArray) JsonArray(listOf(element)) else element
}

object TransformJsonReturnEmptyObjectRank :
    JsonTransformingSerializer<RankDto>(serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        if (element is JsonObject) return element
        return buildJsonObject {}
    }
}

object TransformJsonReturnEmptyObjectPersonal :
    JsonTransformingSerializer<PersonalDto>(serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        if (element is JsonObject) return element
        return buildJsonObject {}
    }
}