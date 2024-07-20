package presentation.app

import di.dataModule
import di.presentationModule
import di.useCaseModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(koinApp: ((KoinApplication)->Unit)?= null){
    startKoin {
        koinApp?.invoke(this)
        modules(dataModule, useCaseModule, presentationModule)
    }
}