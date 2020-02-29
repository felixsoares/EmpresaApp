package com.felix.empresa.data.module

import com.felix.empresa.data.connection.RestConfig
import com.felix.empresa.data.database.LocalStorage
import com.felix.empresa.data.database.LocalStorageImpl
import com.felix.empresa.data.repository.EnterpriseRepository
import com.felix.empresa.data.repository.EnterpriseRepositoryImpl
import com.felix.empresa.data.repository.LoginRepository
import com.felix.empresa.data.repository.LoginRepositoryImpl
import com.felix.empresa.ui.login.business.LoginContract
import com.felix.empresa.ui.login.model.LoginModel
import com.felix.empresa.ui.login.presenter.LoginPresenter
import com.felix.empresa.ui.login.view.LoginActivity
import com.felix.empresa.ui.main.business.MainContract
import com.felix.empresa.ui.main.model.MainModel
import com.felix.empresa.ui.main.presenter.MainPresenter
import com.felix.empresa.ui.main.view.MainActivity
import com.felix.empresa.util.ItemViewClick
import com.felix.empresa.widget.EnterpriseAdapter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val baseModule = module {
    single { LoginModel() }
    single { MainModel() }

    single<LocalStorage> { LocalStorageImpl(get()) }
    factory { RestConfig.get(get()) }
    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }
    single<EnterpriseRepository> { EnterpriseRepositoryImpl(get(), get()) }

    scope(named<LoginActivity>()) {
        factory<LoginContract.Presenter> { (view: LoginContract.View) ->
            LoginPresenter(view, get(), get())
        }
    }

    scope(named<MainActivity>()) {
        factory { (click: ItemViewClick) ->
            EnterpriseAdapter(get(), click)
        }
        factory<MainContract.Presenter> { (view: MainContract.View) ->
            MainPresenter(view, get(), get())
        }
    }
}