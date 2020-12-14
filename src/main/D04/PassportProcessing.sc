import scala.annotation.tailrec

val lines = Importeuer(4, test = false).values

// prepare a list of values to check in part 1
val checklist =
  Set(
    "byr",
    "iyr",
    "eyr",
    "hgt",
    "hcl",
    "ecl",
    "pid"
  )


case class Passport(desc: String) {
  // derive attribute Map.
  val aMap =
    desc.split(" ")
      .map(str => str.split(":"))
      .map { case Array(k, v) => (k, v) }
      .toMap

  val looks_valid: Boolean = checklist.diff(aMap.keys.toSet).isEmpty
}


// prepare data
@tailrec
def splitter(accu: List[String], inList: List[String], splitChar: String): List[String] = {
  if (inList.isEmpty) accu
  else {
    val (keep, rest) = inList.splitAt(inList.indexOf(""))
    splitter(accu ::: List(keep.mkString(" ")), rest.tail, splitChar)
  }
}

val data = splitter(List.empty[String], rawData ::: List(""), "")

// Part 1
data.count(x => Passport(x).looks_valid)