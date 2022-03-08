package org.algosketch.inubus.di.factory

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import org.algosketch.inubus.di.OkHttpClientFactory
import org.algosketch.inubus.global.configs.ServerConfigs
import retrofit2.Retrofit

class RetrofitServiceFactory {
    companion object {
        fun <Service> create(service: Class<Service>): Service {
            return Retrofit.Builder()
                .baseUrl(ServerConfigs.baseUrl)
                .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
                .client(OkHttpClientFactory.create())
                .build()
                .create(service)
        }
    }
}