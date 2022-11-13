package io.study.kotlin.kotlinminiproject.service

import io.study.kotlin.kotlinminiproject.domain.Ladder
import io.study.kotlin.kotlinminiproject.domain.LadderCreation
import io.study.kotlin.kotlinminiproject.domain.Result
import org.springframework.stereotype.Service

@Service
class LadderGameService {
    fun run(ladderCreation: LadderCreation): List<Result> {
        val ladder = Ladder.create(ladderCreation)
        return ladder.getResults(ladderCreation)
    }
}