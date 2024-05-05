package com.university.core.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: DispatcherKey)
enum class DispatcherKey { IO, COMPUTATION, MAIN }

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ContextQualifier(val contextKet: ContextKey)
enum class ContextKey { APP, ACTIVITY, FRAGMENT }