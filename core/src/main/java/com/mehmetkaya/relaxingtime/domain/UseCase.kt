package com.mehmetkaya.relaxingtime.domain

interface UseCase<in Input, out Output> {
    suspend operator fun invoke(input: Input): Output
}

interface NoInputUseCase<out Output> {
    suspend operator fun invoke(): Output
}
