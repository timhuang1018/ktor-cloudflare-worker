package io.keeppro.kcw.sample.di

import io.keeppro.kcw.sample.viewmodel.FetchViewModel
import io.keeppro.kcw.sample.viewmodel.UploadViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val shareModule = module {
    singleOf(::UploadViewModel)
    singleOf(::FetchViewModel)
    single { MainScope() }.bind<CoroutineScope>()
}
