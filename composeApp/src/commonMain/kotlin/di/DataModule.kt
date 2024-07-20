package di

import data.repo.MainRepositoryImpl
import domain.repo.MainRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import org.chachadev.cmpprecompose.AppDatabase
import org.koin.dsl.module

val dataModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation){
                json()
            }
        }
    }

    factory <MainRepository>{ MainRepositoryImpl(get<HttpClient>(),get<AppDatabase>())}
}