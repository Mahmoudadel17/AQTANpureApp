package com.example.aqtan.data.repository

import com.example.aqtan.data.remote.ApiServices
import com.example.aqtan.domain.repository.ApiServicesRepository
import javax.inject.Inject

class ApiServicesRepositoryImpl  @Inject constructor(
    private val api: ApiServices
) : ApiServicesRepository {
}