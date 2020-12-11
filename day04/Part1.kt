import java.io.File

fun main(args: Array<String>) {
    val passports = File("input.txt").bufferedReader().use { it.readText() }.split("\r\n\r\n")
    val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    var valid = 0

    for (passport in passports) {
        val fields = passport.split("\r\n", " ").associate { it.split(":").let { (key, value) -> key to value } }
        val validAttrs = requiredFields.map { fields[it] != null }.all { it }
        if (validAttrs) {
            valid++
        }
    }

    println(valid)

}

