package com.decwujot.countires.core.useCases

import com.decwujot.countires.core.repository.RemoteRepository

class FetchAllCountries(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke() = remoteRepository.fetchAllCountries()
}