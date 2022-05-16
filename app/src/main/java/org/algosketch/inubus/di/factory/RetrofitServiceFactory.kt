package org.algosketch.inubus.di.factory

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import org.algosketch.inubus.di.OkHttpClientFactory
import org.algosketch.inubus.common.configs.ServerConfigs
import retrofit2.Retrofit

class RetrofitServiceFactory {
    companion object {
        inline fun <reified Service> create(): Service {
            return Retrofit.Builder()
                .baseUrl(ServerConfigs.baseUrl)
                .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
                .client(OkHttpClientFactory.create())
                .build()
                .create(Service::class.java)
        }
    }
}