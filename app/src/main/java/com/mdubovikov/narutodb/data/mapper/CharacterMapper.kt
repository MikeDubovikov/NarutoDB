package com.mdubovikov.narutodb.data.mapper

import com.mdubovikov.narutodb.data.network.dto.CharacterDto
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.domain.entity.CharacterDetails

fun CharacterDto.toCharacterDetails(): CharacterDetails = CharacterDetails(
    id = id,
    name = name,
    images = images,
    birthdate = personal?.birthdate,
    sex = personal?.sex,
    status = personal?.status,
    agePartI = personal?.age?.partI,
    agePartII = personal?.age?.partII,
    heightI = personal?.height?.partI,
    heightII = personal?.height?.partII,
    weightI = personal?.weight?.partI,
    weightII = personal?.weight?.partII,
    bloodType = personal?.bloodType,
    jutsu = jutsu,
    classification = personal?.classification,
    affiliation = personal?.affiliation,
    occupation = personal?.occupation,
    partner = personal?.partner,
    clan = personal?.clan,
    team = personal?.team,
    ninjaRankI = rank?.ninjaRank?.partI,
    ninjaRankII = rank?.ninjaRank?.partII,
    ninjaRegistration = rank?.ninjaRegistration,
    tools = tools,
    mother = family?.mother,
    father = family?.father,
    sister = family?.sister,
    brother = family?.brother,
    daughter = family?.daughter,
    son = family?.son,
    wife = family?.wife,
    husband = family?.husband,
    granddaughter = family?.granddaughter,
    grandfather = family?.grandfather,
    spouse = family?.spouse,
    depoweredform = family?.depoweredform,
    incarnationWithTheGodtree = family?.incarnationWithTheGodtree
)

fun CharacterDto.toCharacter(): Character =
    Character(
        id = id,
        name = name ?: "",
        image = images?.first() ?: "",
        isBookmarked = false
    )

fun List<CharacterDto>.toCharactersList() = map { it.toCharacter() }

fun CharacterDetails.toCharacter(): Character =
    Character(
        id = id,
        name = name ?: "",
        image = images?.first() ?: "",
        isBookmarked = false
    )