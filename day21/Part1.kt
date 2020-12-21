import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map {
        it.split(" (contains ").let { (ingredients, allergens) ->
            ingredients.split(" ") to allergens.replace(")", "").split(", ")
        }
    }

    val allergens = input.map { it.second.toSet() }.reduce { acc, set -> acc.union(set) }
    val ingredients = input.map { it.first.toSet() }.reduce { acc, set -> acc.union(set) }.toMutableSet()

    val translator = allergens.map { allergen ->
        allergen to input.filter { it.second.contains(allergen) }.map { it.first.toSet() }
            .reduce { acc, set -> acc.intersect(set) }
    }

    translator.forEach { it.second.forEach { ingredients.remove(it) } }

    val result =
        ingredients.map { ingredient -> input.map { it.first.filter { it == ingredient }.count() }.sum() }.sum()

    println(result)
}