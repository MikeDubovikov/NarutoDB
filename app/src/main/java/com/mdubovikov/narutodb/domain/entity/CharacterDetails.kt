package com.mdubovikov.narutodb.domain.entity

data class CharacterDetails(
    val id: Int,
    val name: String,
    val image: String?,
    val birthdate: String?,
    val sex: String?,
    val status: String?,
    val age: String?,
    val height: String?,
    val weight: String?,
    val jutsu: List<String>?,
    val clan: List<String>?,
    val ninjaRank: String?,
    val mother: String?,
    val father: String?,
    val sister: String?,
    val brother: String?,
    val daughter: String?,
    val son: String?,
    val wife: String?,
    val husband: String?,
    val grandmother: String?,
    val grandfather: String?
)