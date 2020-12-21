import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map {
        it.split(" (contains ").let { (ingredients, allergens) ->
            ingredients.split(" ") to allergens.replace(")", "").split(", ")
        }
    }

    val translator = input.map { it.second.toSet() }.reduce { acc, set -> acc.union(set) }.map { allergen ->
        allergen to input.filter { it.second.contains(allergen) }.map { it.first.toSet() }
            .reduce { acc, set -> acc.intersect(set) }.toMutableSet()
    }

    while (!translator.map { it.second.size == 1 }.all { it }) {
        val toRemove = translator.filter { it.second.size == 1 }.map { it.second }.flatten()
        translator.forEach { if (it.second.size != 1) it.second.removeAll(toRemove) }
    }

    println(translator.sortedBy { it.first }.joinToString(",") { it.second.first() })
}