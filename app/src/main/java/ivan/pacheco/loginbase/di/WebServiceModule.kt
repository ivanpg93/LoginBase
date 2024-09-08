package ivan.pacheco.loginbase.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ivan.pacheco.loginbase.infrastructure.remote.LoginCredentialsWebService
import ivan.pacheco.loginbase.infrastructure.remote.LogoutWebService
import ivan.pacheco.loginbase.infrastructure.remote.RecoveryPasswordWebService
import ivan.pacheco.loginbase.infrastructure.remote.RegisterWebService
import ivan.pacheco.loginbase.infrastructure.remote.SendVerificationEmailWebService
import ivan.pacheco.loginbase.infrastructure.remote.webservice.LoginCredentialsWS
import ivan.pacheco.loginbase.infrastructure.remote.webservice.LogoutWS
import ivan.pacheco.loginbase.infrastructure.remote.webservice.RecoveryPasswordWS
import ivan.pacheco.loginbase.infrastructure.remote.webservice.RegisterWS
import ivan.pacheco.loginbase.infrastructure.remote.webservice.SendVerificationEmailWS

@Module
@InstallIn(SingletonComponent::class)
object WebServiceModule {

    @Provides
    fun providesRegisterWebservice(): RegisterWebService = RegisterWS()

    @Provides
    fun providesLoginCredentialsWebservice(): LoginCredentialsWebService = LoginCredentialsWS()

    @Provides
    fun providesRecoveryPasswordWebservice(): RecoveryPasswordWebService = RecoveryPasswordWS()

    @Provides
    fun providesSendVerificationEmailWebservice(): SendVerificationEmailWebService = SendVerificationEmailWS()

    @Provides
    fun providesLogoutWebservice(): LogoutWebService = LogoutWS()

}