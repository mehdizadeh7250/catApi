package com.mehdizadeh.catfeed.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<in Params, Output> {

    /**
     * Executes the use case with the given parameters and returns a Flow of Result.
     */
    operator fun invoke(params: Params): Flow<Result<Output>> = flow {
        emit(Result.Loading) // Emit loading state
        // Collect the result from the execute function
        execute(params).collect { result ->
            emit(result) // Emit each result from the Flow returned by execute
        }
    }.catch { e ->
        emit(Result.Error("An error occurred", e)) // Catch exceptions and emit as Result.Error
    }

    /**
     * Implement this function in subclasses to perform the specific task.
     * This function is suspendable and should return a Flow of Result<Output>.
     */
    protected abstract suspend fun execute(params: Params): Flow<Result<Output>>
}