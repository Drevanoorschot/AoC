module D1 where
import D1input


makeComb :: Int -> Int -> [Int]
makeComb x y = [x, y]

combs2 :: [Int] -> [[Int]]
combs2 [] = []
combs2 (x:xs) = map (makeComb x) xs ++ combs2 xs

sumEquals :: Int -> [Int] -> Bool
sumEquals s xs = sum xs == s

validCombSums :: [[Int]]
validCombSums = filter (sumEquals 2020) (combs2 input)

ex1 :: Int
ex1 = product (validCombSums !! 0)

makeComb3 :: Int -> Int -> Int -> [Int]
makeComb3 x y z = [x,y,z]

-- maybe wrong
combs3 :: [Int] -> [[Int]]
combs3 [] = []
combs3 (x:xs) = map (++[x]) (combs2 (x:xs)) ++ combs3 xs

validComb3Sums :: [[Int]]
validComb3Sums = filter (sumEquals 2020) (combs3 input)

ex2 :: Int
ex2 = product (validComb3Sums !! 0)