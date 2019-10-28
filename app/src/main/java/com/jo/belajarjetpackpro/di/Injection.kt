package com.jo.belajarjetpackpro.di

import android.app.Application
import com.jo.belajarjetpackpro.data.source.AcademyRepository
import com.jo.belajarjetpackpro.data.source.remote.RemoteRepository
import com.jo.belajarjetpackpro.utils.JsonHelper

class Injection {
    companion object {
        fun provideRepository(application: Application): AcademyRepository {
            val remoteRepository = RemoteRepository.getInstance(JsonHelper(application))
            return AcademyRepository.getInstance(remoteRepository)
        }
    }
}