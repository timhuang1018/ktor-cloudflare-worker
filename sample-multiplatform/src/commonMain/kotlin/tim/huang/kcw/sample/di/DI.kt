package tim.huang.kcw.sample.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import tim.huang.kcw.sample.viewmodel.FetchViewModel
import tim.huang.kcw.sample.viewmodel.UploadViewModel

val shareModule = module {
    singleOf(::UploadViewModel)
    singleOf(::FetchViewModel)
    single { MainScope() }.bind<CoroutineScope>()
}
