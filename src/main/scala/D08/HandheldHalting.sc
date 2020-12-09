// Define some helpers to match the defintion of the problem in the puzzle.
type Operation = String
type Argument = Int


case class Instruction(op: Operation, arg: Argument) {
  def goto: Int = {
    if (op == "nop") 1
    else if (op == "acc") 1
    else if (op == "jmp") arg
    else throw UninitializedFieldError("Unintended")
  }

  def compute: Int = {
    if (op == "acc") arg
    else 0
  }
}


def str2Instruction(instr: String): Instruction = {
  Instruction(instr.take(3), instr.drop(4).toInt)
}

// Setup
val lines = Importeuer(8, test = false).values.toArray

val instructions = lines.map(x => str2Instruction(x))

// define a recursive function to loop through the instruction set.
def computer(instr: Array[Instruction], accu: Int, idx: Int, history: Set[Int]): Int = {
  val todo = instr(idx)
  val next = idx + todo.goto
  if (history.contains(next)) accu
  else computer(instr, accu + todo.compute, next, history.union(Set(next)))
}

// Part 1
computer(instructions, 0, 0, Set())

