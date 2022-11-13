package io.study.kotlin.kotlinminiproject.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class LadderTest {
    @Test
    @DisplayName("Ladder 생성 테스트")
    fun create() {
        val legs1 = Legs(listOf(Leg(left = false, right = true), Leg(left = true, right = false), Leg(left = false, right = true), Leg(left = true, right = false)))
        val ladder1 = Ladder.from(listOf(legs1))

        val legs2 = Legs(listOf(Leg(left = false, right = true), Leg(left = true, right = false), Leg(left = false, right = true), Leg(left = true, right = false)))
        val ladder2 = Ladder.from(listOf(legs2))

        assertEquals(ladder1, ladder2)
    }

    @Test
    @DisplayName("Secondary 생성자로 LadderCreation 으로 Ladder 생성 테스트 (최초 left, 최종 right 는 이동 할 수 없으므로, 항상 false 이어야 함)")
    fun create2() {
        val ladderCreation1 =
                LadderCreation(listOf("서영덕", "서동성", "선용주", "홍석민"), listOf("화분에 물 주기", "커피 머신 청소", "화장실 비품 채우기", "쓰레기통 비우기"), 3)

        val ladder = Ladder.create(ladderCreation1)
        val legsList: List<Legs> = ladder.getLegs()
        for (legs in legsList) {
            assertEquals(false, legs.get(0).left)
            assertEquals(false, legs.get(legs.size() - 1).right)
        }
    }

    @Test
    @DisplayName("Secondary 생성자로 LadderCreation 으로 Ladder 생성 테스트 (왼쪽, 오른쪽 은 둘다 true 일 수 없다)")
    fun create3() {
        val ladderCreation1 =
                LadderCreation(listOf("서영덕", "서동성", "선용주", "홍석민"), listOf("화분에 물 주기", "커피 머신 청소", "화장실 비품 채우기", "쓰레기통 비우기"), 3)

        val ladder = Ladder.create(ladderCreation1)
        val legsList: List<Legs> = ladder.getLegs()
        for (legs in legsList) {
            for (i in 0 until legs.size()) {
                assertNotEquals(Leg(left = true, right = true), legs.get(i))
            }
        }
        //3. 참가자 수만큼 leg가 생성 되어야 한다.
    }

    @Test
    @DisplayName("Secondary 생성자로 LadderCreation 으로 Ladder 생성 테스트 (참가자 수만큼 leg가 생성 되어야 한다.)")
    fun create4() {
        val ladderCreation1 =
                LadderCreation(listOf("서영덕", "서동성", "선용주", "홍석민"), listOf("화분에 물 주기", "커피 머신 청소", "화장실 비품 채우기", "쓰레기통 비우기"), 3)

        val ladder = Ladder.create(ladderCreation1)
        val legsList: List<Legs> = ladder.getLegs()
        for (legs in legsList) {
            assertEquals(ladderCreation1.participants.size(), legs.size())
        }
    }

    @Test
    @DisplayName("Secondary 생성자로 LadderCreation 으로 Ladder 생성 테스트 (rung 만큼 legs가 생성 되어야 한다.)")
    fun create5() {
        val ladderCreation1 =
                LadderCreation(listOf("서영덕", "서동성", "선용주", "홍석민"), listOf("화분에 물 주기", "커피 머신 청소", "화장실 비품 채우기", "쓰레기통 비우기"), 3)

        val ladder = Ladder.create(ladderCreation1)
        val legsList: List<Legs> = ladder.getLegs()

        assertEquals(ladderCreation1.rung.count, legsList.size)
    }

    @Test
    @DisplayName("경로 가져오기 테스트")
    fun getRoutes() {
        val legs1 = Legs(listOf(Leg(left = false, right = true), Leg(left = true, right = false), Leg(left = false, right = false), Leg(left = false, right = false)))
        val legs2 = Legs(listOf(Leg(left = false, right = false), Leg(left = false, right = true), Leg(left = true, right = false), Leg(left = false, right = false)))
        val legs3 = Legs(listOf(Leg(left = false, right = false), Leg(left = false, right = false), Leg(left = false, right = true), Leg(left = true, right = false)))

        val ladder = Ladder.from(listOf(legs1, legs2, legs3))
        assertEquals(listOf(1, 2, 3, 4), ladder.getRoutes(1))
        assertEquals(listOf(2, 1, 1, 1), ladder.getRoutes(2))
        assertEquals(listOf(3, 3, 2, 2), ladder.getRoutes(3))
        assertEquals(listOf(4, 4, 4, 3), ladder.getRoutes(4))
    }

    @Test
    @DisplayName("결과 가져오기 테스트")
    fun getResults() {
        val legs1 = Legs(listOf(Leg(left = false, right = true), Leg(left = true, right = false), Leg(left = false, right = false), Leg(left = false, right = false)))
        val legs2 = Legs(listOf(Leg(left = false, right = false), Leg(left = false, right = true), Leg(left = true, right = false), Leg(left = false, right = false)))
        val legs3 = Legs(listOf(Leg(left = false, right = false), Leg(left = false, right = false), Leg(left = false, right = true), Leg(left = true, right = false)))

        val ladderCreation =
                LadderCreation(listOf("서영덕", "서동성", "선용주", "홍석민"), listOf("화분에 물 주기", "커피 머신 청소", "화장실 비품 채우기", "쓰레기통 비우기"), 3)

        val ladder = Ladder.from(listOf(legs1, legs2, legs3))
        assertEquals(listOf(1, 2, 3, 4), ladder.getRoutes(1))
        assertEquals(listOf(2, 1, 1, 1), ladder.getRoutes(2))
        assertEquals(listOf(3, 3, 2, 2), ladder.getRoutes(3))
        assertEquals(listOf(4, 4, 4, 3), ladder.getRoutes(4))

        assertEquals(listOf(Result(1, "서영덕", "쓰레기통 비우기", listOf(1, 2, 3, 4)),
                Result(2, "서동성", "화분에 물 주기", listOf(2, 1, 1, 1)),
                Result(3, "선용주", "커피 머신 청소", listOf(3, 3, 2, 2)),
                Result(4, "홍석민", "화장실 비품 채우기", listOf(4, 4, 4, 3))),
                ladder.getResults(ladderCreation))
    }
}