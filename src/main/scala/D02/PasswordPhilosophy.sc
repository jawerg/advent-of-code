val lines = Importeuer(2, test = false).values


case class Interval(description: String) {
  val splitter = description.split("-")

  val lb: Int = splitter(0).toInt

  val ub: Int = splitter(1).toInt

  def contains(x: Int): Boolean = lb <= x && x <= ub

  def is_valid(pw: String, key: Char): Boolean = {
    val i = lb - 1
    val j = ub - 1
    pw(i) == key ^ pw(j) == key
  }
}


// General
val policies = lines.map(x => x.split(" "))

// Part 1
policies
  .map { case Array(constraints, letter, pw) =>
    Interval(constraints).contains(pw.count(x => x == letter.head))
  }
  .count(x => x)

// Part 2
policies
  .map { case Array(constraints, letter, pw) =>
    Interval(constraints).is_valid(pw, letter.head)
  }
  .count(x => x)