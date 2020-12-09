import scala.annotation.tailrec

// Load
val fpath = "/home/jan/IdeaProjects/advent-of-code/src/main/D09/input.txt"
val source = scala.io.Source.fromFile(fpath)
val lines = try source.getLines().toList.map(x => BigInt(x)) finally source.close()

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

@tailrec
def summer(target: BigInt)(inList: List[BigInt], N: Int): BigInt = {
  if (inList.take(N).sum == target) inList.take(N).min + inList.take(N).max
  else if (inList.take(N + 1).sum > target) summer(target)(inList.tail, 0)
  else summer(target)(inList, N + 1)
}

// Set list of slices.
val splitter = 25

// get value of the first number that breaks the rule.
val idx =
  lines
    .sliding(splitter + 1)
    .toList
    .map(x => slider(x))
    .indexOf(false)

// Part 1
val problemkind = lines(splitter + idx)

// Part 2
val shortList = lines.take(idx)
summer(problemkind)(shortList, 1)