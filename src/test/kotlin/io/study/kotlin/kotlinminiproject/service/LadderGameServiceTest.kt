package io.study.kotlin.kotlinminiproject.service

import io.study.kotlin.kotlinminiproject.domain.LadderCreation
import org.junit.jupiter.api.Test

internal class LadderGameServiceTest {
    @Test
    fun run() {
        val gameService = LadderGameService()
        val ladderCreation =
            LadderCreation(
                listOf("서영덕", "서동성", "선용주", "홍석민", "이도훈"),
                listOf("화분에 물 주기", "커피 머신 청소", "화장실 비품 채우기", "쓰레기통 비우기"),
                3
            )
        println(gameService.run(ladderCreation))
    }

}