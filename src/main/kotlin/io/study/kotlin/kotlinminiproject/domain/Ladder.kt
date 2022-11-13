package io.study.kotlin.kotlinminiproject.domain

import kotlin.random.Random

class Ladder private constructor(val ladder: List<Legs>) {

//    private var ladder: List<Legs> = ArrayList();

    companion object {
        fun from(ladder: List<Legs>): Ladder {
            return Ladder(ladder)
        }

        fun create(ladderCreation: LadderCreation): Ladder {
            val columCount = ladderCreation.participants.size() - 1
            val rowCount = ladderCreation.rung.count

            var ladder: List<Legs> = ArrayList();

            (0 until rowCount).forEach { row ->
                var legs: List<Leg> = ArrayList()

                val firstLeg = Leg(left = false, right = Random.nextBoolean());
                legs += firstLeg

                (1 until columCount).forEach { colum ->
                    val previousLeg = legs[colum - 1]
                    legs += when (previousLeg.right) {
                        true -> {
                            Leg(left = true, right = false)
                        }

                        false -> {
                            Leg(left = false, right = Random.nextBoolean())
                        }
                    }
                }

                var lastLeg = (Leg(left = (legs[columCount - 1].right), right = false))
                legs += lastLeg
                ladder += Legs(legs)
            }
            return Ladder(ladder)
        }
    }
    //companion object 활용한 생성자 메서드로 정리
    /**
     *   for( int i = 0; i < list.size(); i++ ) {
     *   if( i === 0 ) {}
     *   else if ( i === list.size() - 1 ) {          }
     *   else {}
     *   }

    =>

    var list = ...
    list.add(First)
    for( 중간에 있는 요소들 순회 ) {
    list.add(작업)
    }
    list.add(Last)

     **/

    fun getResults(ladderCreation: LadderCreation): List<Result> {
        val (participants, destinations) = ladderCreation
        var results = ArrayList<Result>()

        for (order in 1..participants.size()) {
            val routes = getRoutes(order)
            val destinationOrder = routes[routes.size - 1]
            results += Result(order, participants.get(order), destinations.get(destinationOrder), routes)
        }

        return results
    }

    fun getRoutes(order: Int): List<Int> {
        var routes: List<Int> = ArrayList()
        if (this.ladder.isNotEmpty()) {
            routes += order
        }
        for (i in 0 until this.ladder.size) {
            routes += this.ladder[i].getMovedPosition(routes.last())
        }

        return routes
    }

    fun getLegs(): List<Legs> {
        return this.ladder
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ladder

        if (ladder != other.ladder) return false

        return true
    }

    override fun hashCode(): Int {
        return ladder.hashCode()
    }

}