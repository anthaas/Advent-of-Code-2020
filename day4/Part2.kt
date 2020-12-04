import java.io.File

fun main(args: Array<String>) {
    val passports = File("input.txt").bufferedReader().use { it.readText() }.split("\r\n\r\n")
    val requiredFields = listOf(
        Pair("byr", { x: String -> x.toIntOrNull() in 1920..2002 }),
        Pair("iyr", { x: String -> x.toIntOrNull() in 2010..2020 }),
        Pair("eyr", { x: String -> x.toIntOrNull() in 2020..2030 }),
        Pair("hgt", ::testHgt),
        Pair("hcl", { x: String -> x.matches(Regex("#[0-9,a-f]{6}")) }),
        Pair("ecl", { x: String -> x in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") }),
        Pair("pid", { x: String -> x.matches(Regex("[0-9]{9}")) })
    )

    val valid = passports.map { passport ->
        passport.split("\r\n", " ").associate { it.split(":").let { (key, value) -> key to value } }
    }.map {
        requiredFields.map { (value, validityCondition) -> it[value] != null && validityCondition(it[value]!!) }.all { it }
    }.count { it }

    println(valid)
}

fun testHgt(x: String): Boolean {
    if ("cm" in x) {
        return x.replace("cm", "").toIntOrNull() in 150..193
    } else if ("in" in x) {
        return x.replace("in", "").toIntOrNull() in 59..76
    }

    return false
}