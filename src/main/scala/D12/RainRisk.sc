// Define a Set of Directions to make the labels easier to read.
object Direction extends Enumeration {
  type Direction = Value

  val East, North, West, South = Value
}
import Direction._

// Interpret the given strings with a simple class
case class Instruction(action: Char, magnitude: Int)

// All navigation is incorporated in a moving Ship class.
case class Ship(north: Int, east: Int, kompass: LazyList[Direction]) {
  def move(instr: Instruction) = {
    instr match {
      case Instruction('N', m) => copy(north = north + m)
      case Instruction('S', m) => copy(north = north - m)
      case Instruction('E', m) => copy(east = east + m)
      case Instruction('W', m) => copy(east = east - m)
      case Instruction('L', m) => copy(kompass = kompass.drop(m / 90))
      case Instruction('R', m) => copy(kompass = kompass.drop(4 - m / 90))
      case Instruction('F', m) =>
        kompass.head match {
          case North => copy(north = north + m)
          case South => copy(north = north - m)
          case East => copy(east = east + m)
          case West => copy(east = east - m)
        }
    }
  }

  def ManhattanDistance: Int = north.abs + east.abs
}

// Preamble
val lines = Importeuer(12, test = false).values
val kompass = LazyList.continually(List(East, North, West, South)).flatten


// Example
Ship(0, 0, kompass)
  .move(Instruction('F', 10))
  .move(Instruction('N', 3))
  .move(Instruction('F', 7))
  .move(Instruction('R', 90))
  .move(Instruction('F', 11))
  .ManhattanDistance

// Part 1
lines
  .map(x => Instruction(x.head, x.tail.toInt))
  .foldLeft(Ship(0,0, kompass))((ship, instr) => ship.move(instr))
  .ManhattanDistance