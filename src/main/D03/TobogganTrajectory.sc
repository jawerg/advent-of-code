// Load
val fpath = "/home/jan/IdeaProjects/advent-of-code/src/main/D03/input.txt"
val source = scala.io.Source.fromFile(fpath)
val lines = try source.getLines().toList finally source.close()
val streams = lines.map(x => LazyList.continually(x).flatten)

(
  for {
    i <- Seq.range(0, streams.length)
    if streams(i)(i * 3) == '#'
  } yield 1
  ).sum