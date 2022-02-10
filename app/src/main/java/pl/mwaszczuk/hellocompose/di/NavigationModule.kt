package pl.mwaszczuk.hellocompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import pl.mwaszczuk.hellocompose.mock.AnalyticsLogger
import pl.mwaszczuk.hellocompose.navigation.NavigationManager
import pl.mwaszczuk.hellocompose.navigation.NavigationManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun provideNavigationManager(): NavigationManager {
        return NavigationManagerImpl()
    }

    @Singleton
    @Provides
    fun provideAnalyticsLoggerMock(): AnalyticsLogger {
        return AnalyticsLogger()
    }
}
