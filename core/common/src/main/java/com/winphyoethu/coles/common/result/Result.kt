package com.winphyoethu.coles.common.result

/**
 * Base Result Class
 */
sealed class ColesResult<out T> {

    /**
     * Generic Success Result Class
     */
    data class Success<out T>(val data: T) : ColesResult<T>()

    /**
     * Generic Error Result Class
     */
    data class Error<out T>(val e: ErrorCode): ColesResult<T>()

}