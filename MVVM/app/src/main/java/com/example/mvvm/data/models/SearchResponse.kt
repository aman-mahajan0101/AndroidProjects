package com.example.mvvm.data.models

import com.example.mvvm.data.models.User

data class SearchResponse(
	val totalCount: Int? = null,
	val incompleteResults: Boolean? = null,
	val items: List<User>? = null
)


