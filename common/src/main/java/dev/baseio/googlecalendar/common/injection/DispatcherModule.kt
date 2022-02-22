package dev.baseio.googlecalendar.common.injection

import dev.baseio.googlecalendar.common.injection.dispatcher.CoroutineDispatcherProvider
import dev.baseio.googlecalendar.common.injection.dispatcher.RealCoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DispatcherModule {
    @Provides
    @Singleton
    fun providesCoroutineDispatcher(): CoroutineDispatcherProvider {
        return RealCoroutineDispatcherProvider()
    }
}
