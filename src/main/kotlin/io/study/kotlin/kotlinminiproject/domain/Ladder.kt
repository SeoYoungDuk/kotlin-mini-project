package io.study.kotlin.kotlinminiproject.domain

import kotlin.random.Random

class Ladder {
    private var ladder: List<Legs> = ArrayList();

    constructor(ladder: List<Legs>) {
        this.ladder = ladder
    }

    constructor(ladderCreation: LadderCreation) {
        val columCount = ladderCreation.participants.size() - 1
        val rowCount = ladderCreation.rung.count;
        (0 until rowCount).forEach { row ->
            var legs: List<Leg> = ArrayList()
            (0..columCount).forEach { colum ->
                when {
                    colum == columCount -> {
                        legs += (Leg(left = (legs[colum - 1].right), right = false))
                    }
                    colum == 0 || !legs[colum - 1].right -> {
                        legs += Leg(left = false, right = Random.nextBoolean())
                    }
                    legs[colum - 1].right -> {
                        legs += Leg(left = true, right = false)
                    }
                }
            }
            this.ladder += Legs(legs)
        }
    }

    fun getResults(ladderCreation: LadderCreation): List<Result> {
        val participants = ladderCreation.participants
        val destinations = ladderCreation.destinations
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