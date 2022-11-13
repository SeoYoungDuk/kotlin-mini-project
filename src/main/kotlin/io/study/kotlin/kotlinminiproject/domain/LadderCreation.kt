package io.study.kotlin.kotlinminiproject.domain

import java.lang.Math.abs

class LadderCreation(participants: List<String>, destinations: List<String>, rung: Int?) {
    var participants: Participants
    var destinations: Destinations
    var rung: Rung

    init {
        this.participants = Participants(participants)
        this.destinations = Destinations(destinations)
        this.rung = Rung(rung)

        val difference = participants.size - destinations.size
        if (difference < 0) {
            this.participants.addGhost(abs(difference))
        }

        if (difference > 0) {
            this.destinations.addNone(difference)
        }

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LadderCreation

        if (participants != other.participants) return false
        if (destinations != other.destinations) return false
        if (rung != other.rung) return false

        return true
    }

    override fun hashCode(): Int {
        var result = participants.hashCode()
        result = 31 * result + destinations.hashCode()
        result = 31 * result + rung.hashCode()
        return result
    }

    operator fun component1(): Participants {
        return this.participants
    }

    operator fun component2(): Destinations {
        return this.destinations
    }
}