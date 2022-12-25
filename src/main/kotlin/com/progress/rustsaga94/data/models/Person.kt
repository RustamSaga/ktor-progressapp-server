package com.progress.rustsaga94.data.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Person(
    @BsonId
    val id: ObjectId = ObjectId(),
    val userId: ObjectId? = null,
    val userName: String,
    val name: String,
    val joined_data: String,
    val targets: List<Int> = mutableListOf()
)
