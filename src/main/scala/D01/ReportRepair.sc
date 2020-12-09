val values = Importeuer(1, test = false).values.map(x => x.toInt)

// Part 1
for {
  i <- values
  j <- values
  if i <= j; if i + j == 2020
} yield i * j

// Part 2
for {
  i <- values
  j <- values
  k <- values
  if i <= j; if j <= k; if i + j + k == 2020
} yield i * j * k