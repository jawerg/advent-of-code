// Load
val fpath = "/home/jan/IdeaProjects/advent-of-code/src/main/D01/input.txt"
val source = scala.io.Source.fromFile(fpath)
val values = try source.getLines().toList.map(x => x.toInt) finally source.close()

for {
  i <- values
  j <- values
  if i <= j; if i + j == 2020
} yield i * j