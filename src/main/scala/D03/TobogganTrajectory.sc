val lines = Importeuer(3, test = false).values
val streams = lines.map(x => LazyList.continually(x).flatten)

def tobo_trajector(right: Int, down: Int): BigInt = {
  val collector =
    for {
      i <- Seq.range(0, streams.length / down)
      if streams(i * down)(i * right) == '#'
    } yield 1
  collector.sum
}

// Part 1
tobo_trajector(3, 1)

// Part 2
List((1, 1), (3, 1), (5, 1), (7, 1), (1, 2))
  .map(x => tobo_trajector(x._1, x._2))
  .product
