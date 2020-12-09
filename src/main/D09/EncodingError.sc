// Load
val fpath = "/home/jan/IdeaProjects/advent-of-code/src/main/D09/input.txt"
val source = scala.io.Source.fromFile(fpath)
val lines = try source.getLines().toList finally source.close()

// A function to check if the last element of a list is the sum of two former elements.
def slider(slide: List[BigInt]): Boolean = {
  val numbers = slide.take(slide.length - 1)
  val target = slide.last
  val combos = {
    for {
      i <- numbers
      j <- numbers
      if i != j & i + j == target
    } yield (i, j)
  }
  combos.nonEmpty
}

// Set list of slices.
val splitter = 25

// get value of the first number that breaks the rule.
lines(
  splitter +
    lines
      .map(x => BigInt(x))
      .sliding(splitter + 1)
      .toList
      .map(x => slider(x))
      .indexOf(false)
)