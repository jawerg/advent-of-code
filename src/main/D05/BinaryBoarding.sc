// Load
val fpath = "/home/jan/IdeaProjects/advent-of-code/src/main/D05/input.txt"
val source = scala.io.Source.fromFile(fpath)
val lines = try source.getLines().toList finally source.close()


case class Encoder(desc: String) {
  val rowCode =
    desc
      .filter(x => x == 'F' || x == 'B')
      .map(x => x == 'B')
      .toList

  val seatCode =
    desc
      .filter(x => x == 'R' || x == 'L')
      .map(x => x == 'R')
      .toList

  type Binaries = List[Boolean]

  def computer(binaries: Binaries): Int = {
    val maxi = math.pow(2, binaries.length).toInt

    def finder(binaries: Binaries, mini: Int, maxi: Int): Int = {
      if (binaries.isEmpty) mini
      else if (binaries.head) finder(binaries.tail, ((maxi + mini) / 2).ceil.toInt, maxi)
      else finder(binaries.tail, mini, ((maxi + mini) / 2).floor.toInt)
    }

    finder(binaries, 0, maxi)
  }

  def seat: Int = computer(rowCode) * 8 + computer(seatCode)

  override def toString: String = rowCode + " | " + seatCode
}

// Part 1
lines.map(x => Encoder(x).seat).max

// Part 2
val BoardingList = lines.map(x => Encoder(x).seat).toList
Seq.range(BoardingList.min, BoardingList.max).toSet.diff(BoardingList.toSet)
