package ivan.pacheco.loginbase.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ivan.pacheco.loginbase.domain.service.LoginCredentialsService
import ivan.pacheco.loginbase.domain.service.LogoutService
import ivan.pacheco.loginbase.domain.service.RecoveryPasswordService
import ivan.pacheco.loginbase.domain.service.RegisterService
import ivan.pacheco.loginbase.domain.service.SendVerificationEmailService
import ivan.pacheco.loginbase.infrastructure.remote.LoginCredentialsWebService
import ivan.pacheco.loginbase.infrastructure.remote.LogoutWebService
import ivan.pacheco.loginbase.infrastructure.remote.RecoveryPasswordWebService
import ivan.pacheco.loginbase.infrastructure.remote.RegisterWebService
import ivan.pacheco.loginbase.infrastructure.remote.SendVerificationEmailWebService
import ivan.pacheco.loginbase.infrastructure.service.LoginCredentialsDataService
import ivan.pacheco.loginbase.infrastructure.service.LogoutDataService
import ivan.pacheco.loginbase.infrastructure.service.RecoveryPasswordDataService
import ivan.pacheco.loginbase.infrastructure.service.RegisterDataService
import ivan.pacheco.loginbase.infrastructure.service.SendVerificationEmailDataService

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun providesRegisterService(ws: RegisterWebService): RegisterService = RegisterDataService(ws)

    @Provides
    fun providesLoginCredentialsService(ws: LoginCredentialsWebService): LoginCredentialsService =
        LoginCredentialsDataService(ws)

    @Provides
    fun providesRecoveryPasswordService(ws: RecoveryPasswordWebService): RecoveryPasswordService =
        RecoveryPasswordDataService(ws)

    @Provides
    fun providesSendVerificationEmailService(ws: SendVerificationEmailWebService): SendVerificationEmailService =
        SendVerificationEmailDataService(ws)

    @Provides
    fun providesLogoutService(ws: LogoutWebService): LogoutService =
        LogoutDataService(ws)

}