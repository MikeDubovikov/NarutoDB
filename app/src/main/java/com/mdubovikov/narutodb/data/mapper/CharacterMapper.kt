package com.mdubovikov.narutodb.data.mapper

import com.mdubovikov.narutodb.data.network.dto.CharacterDto
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.domain.entity.CharacterDetails

fun CharacterDto.toCharacterDetails(): CharacterDetails = CharacterDetails(
    id = id,
    name = name,
    image = images?.lastOrNull(),
    birthdate = personal?.birthdate,
    sex = personal?.sex,
    status = personal?.status,
    age = if (!personal?.age?.blankPeriod.isNullOrEmpty()) {
        personal?.age?.blankPeriod
    } else if (!personal?.age?.partII.isNullOrEmpty()) {
        personal?.age?.partII
    } else {
        personal?.age?.partI
    },
    height = if (!personal?.height?.blankPeriod.isNullOrEmpty()) {
        personal?.height?.blankPeriod
    } else if (!personal?.height?.partII.isNullOrEmpty()) {
        personal?.height?.partII
    } else {
        personal?.height?.partI
    },
    weight = if (!personal?.weight?.blankPeriod.isNullOrEmpty()) {
        personal?.weight?.blankPeriod
    } else if (!personal?.weight?.partII.isNullOrEmpty()) {
        personal?.weight?.partII
    } else {
        personal?.weight?.partI
    },
    jutsu = jutsu,
    clan = personal?.clan,
    ninjaRank = if (!rank?.ninjaRank?.gaiden.isNullOrEmpty()) {
        rank?.ninjaRank?.gaiden
    } else if (!rank?.ninjaRank?.partII.isNullOrEmpty()) {
        rank?.ninjaRank?.partII
    } else {
        rank?.ninjaRank?.partI
    },
    mother = family?.mother,
    father = family?.father,
    sister = family?.sister,
    brother = family?.brother,
    daughter = family?.daughter,
    son = family?.son,
    wife = family?.wife,
    husband = family?.husband,
    grandmother = family?.grandmother,
    grandfather = family?.grandfather,
)

fun CharacterDto.toCharacter(): Character =
    Character(
        id = id,
        name = name,
        image = images?.lastOrNull(),
        isBookmarked = false
    )

fun List<CharacterDto>.toCharactersList() = map { it.toCharacter() }

fun CharacterDetails.toCharacter(): Character =
    Character(
        id = id,
        name = name,
        image = image,
        isBookmarked = false
    )