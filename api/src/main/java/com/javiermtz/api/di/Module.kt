package com.javiermtz.api.di

import com.example.shared.MarvelRepository
import com.javiermtz.api.remote.MarvelRepositoryImpl
import com.javiermtz.api.remote.RemoteDataSourceImpl
import com.javiermtz.api.remote.RemoteDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Module {

  @Binds
  abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDatasource

  @Binds
  abstract fun bindMarvelRepository(repository: MarvelRepositoryImpl): MarvelRepository
}
