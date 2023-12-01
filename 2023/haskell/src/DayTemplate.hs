module DayTemplate (solve) where

import Data.Text hiding (drop, foldl, head, length, reverse, take)
import Util

solve :: String -> (Answer, Answer)
solve input = (q1 input, q2 input)

q1 :: String -> Answer
q1 input = IntAnswer 42

q2 :: String -> Answer
q2 input = IntAnswer 42