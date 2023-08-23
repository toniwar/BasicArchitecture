package ru.otus.basicarchitecture.domain.entities

import ru.otus.basicarchitecture.domain.repositories.DataStoreManager

data class UserInfo(

    val info: Map<DataStoreManager.Keys.UserInfoKeys, String?> = mapOf()
)
