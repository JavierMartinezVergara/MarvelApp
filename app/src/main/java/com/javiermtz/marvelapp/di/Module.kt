package com.javiermtz.marvelapp.di

import com.javiermtz.marvelapp.data.repository.MarvelRepository
import com.javiermtz.marvelapp.data.repository.MarvelRepositoryImpl
import com.javiermtz.marvelapp.data.repository.RemoteDataSourceImpl
import com.javiermtz.marvelapp.data.repository.RemoteDatasource
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
