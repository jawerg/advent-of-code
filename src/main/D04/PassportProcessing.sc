import scala.annotation.tailrec

// Load
val fpath = "/home/jan/IdeaProjects/advent-of-code/src/main/D04/input.txt"
val source = scala.io.Source.fromFile(fpath)
val rawData = try source.getLines().toList finally source.close()

// Given input of keys to check.
val checkList =
  List(
    "byr",
    "iyr",
    "eyr",
    "hgt",
    "hcl",
    "ecl",
    "pid",
    //"cid",
  )

@tailrec
def splitter(accu: List[String], inList: List[String], splitChar: String): List[String] = {
  if (inList.isEmpty) accu
  else {
    val (keep, rest) = inList.splitAt(inList.indexOf(""))
    splitter(accu ::: List(keep.mkString(" ")), rest.tail, splitChar)
  }
}
def mapper(inString: String): Map[String, String] = {
  inString
    .split(" ")
    .map(str => str.split(":"))
    .map { case Array(k, v) => (k, v) }
    .toMap
}

def validate(inMap: Map[String, String], checklist: List[String]): Boolean = {
  checkList.forall(x => inMap.contains(x))
}

val numberOfValidPassports =
  splitter(List.empty[String], rawData ::: List(""), "")
    .map(x => mapper(x))
    .map(m => validate(m, checkList))
    .map(t => if (t) 1 else 0)
    .sum
