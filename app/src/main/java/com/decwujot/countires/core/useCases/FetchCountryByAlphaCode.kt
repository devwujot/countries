package com.decwujot.countires.core.useCases

import com.decwujot.countires.core.repository.RemoteRepository

class FetchCountryByAlphaCode(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke(alphaCode: String) =
        remoteRepository.fetchCountryByAlphaCode(alphaCode)
}