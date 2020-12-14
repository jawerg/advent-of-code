case class Importeuer(day: Int, test: Boolean) {
  val fname: String = if (test) "test" else "input"

  val values: List[String] = {
    val fpath =
      "/home/jan/IdeaProjects/advent-of-code/src/main/" +
        "D" + "%02d".format(day) + "/" + fname + ".txt"
    val source = scala.io.Source.fromFile(fpath)
    try source.getLines().toList finally source.close()
  }
}