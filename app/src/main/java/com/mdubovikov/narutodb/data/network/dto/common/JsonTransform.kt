package com.mdubovikov.narutodb.data.network.dto.common

import com.mdubovikov.narutodb.data.network.dto.PersonalDto
import com.mdubovikov.narutodb.data.network.dto.RankDto
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
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

object TransformJsonOccupationSerializer :
    JsonTransformingSerializer<List<String>>(ListSerializer(String.serializer())) {
    override fun transformDeserialize(element: JsonElement) =
        when (element) {
            is JsonArray -> element
            is JsonObject -> JsonArray(
                listOf(
                    element.getValue("occupation")
                )
            )

            is JsonPrimitive -> {
                JsonArray(
                    listOf(
                        element
                    )
                )
            }

            else -> throw IllegalArgumentException(
                "Invalid JSON format for occupation"
            )
        }
}

object TransformJsonPartnerSerializer :
    JsonTransformingSerializer<List<String>>(ListSerializer(String.serializer())) {
    override fun transformDeserialize(element: JsonElement) =
        when (element) {
            is JsonArray -> element
            is JsonObject -> JsonArray(
                listOf(
                    element.getValue("partner")
                )
            )

            is JsonPrimitive -> {
                JsonArray(
                    listOf(
                        element
                    )
                )
            }

            else -> throw IllegalArgumentException(
                "Invalid JSON format for partner"
            )
        }
}