package goroh.stepan

import android.app.Application
import android.content.Context
import goroh.stepan.root.KoinSDK
import org.koin.dsl.module

class CourseMetersApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val platformModule = module {
            single<Context> { this@CourseMetersApplication }
        }
        KoinSDK.initKoin(platformModule)
    }
}