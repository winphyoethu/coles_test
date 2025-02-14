import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: ColesDispatchers)

enum class ColesDispatchers {
    IO,
    DEFAULT
}