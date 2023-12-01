module Day1 (solve) where

import Data.Text hiding (drop, foldl, head, length, reverse, take)
import Util

solve :: String -> (Answer, Answer)
solve input = (q1 input, q2 input)

q1 :: String -> Answer
q1 input = IntAnswer $ sum $ fmap parseLine $ fmap unpack (splitOn (pack "\n") (pack input))

q2 :: String -> Answer
q2 input = IntAnswer $ sum $ fmap parseLine2 $ fmap unpack (splitOn (pack "\n") (pack input))

parseLine :: String -> Int
parseLine str = read ((getDigit str) : (getDigit (reverse str)) : []) :: Int

getDigit :: String -> Char
getDigit (c : str)
  | isDigit c = c
  | otherwise = getDigit str

parseLine2 :: String -> Int
parseLine2 str = read ((getDigit2 str) : (getDigit2 (reverse str)) : []) :: Int

getDigit2 :: String -> Char
getDigit2 str
  | isDigit $ head str = head str
  | isWrittenOut str = parseWrittenOut str
  | otherwise = getDigit2 $ drop 1 str

isDigit :: Char -> Bool
isDigit c = c >= '0' && c <= '9'

data Digit = Digit String Char

digits =
  [ Digit "one" '1',
    Digit "two" '2',
    Digit "three" '3',
    Digit "four" '4',
    Digit "five" '5',
    Digit "six" '6',
    Digit "seven" '7',
    Digit "eight" '8',
    Digit "nine" '9'
  ]

isWrittenOut :: String -> Bool
isWrittenOut str = foldl (||) False $ fmap (\(Digit s c) -> take (length s) str == s || take (length s) str == reverse s) digits

parseWrittenOut :: String -> Char
parseWrittenOut str = tryAllDigits str digits

tryAllDigits :: String -> [Digit] -> Char
tryAllDigits str (Digit s c : ds)
  | take (length s) str == s = c
  | take (length s) str == reverse s = c
  | otherwise = tryAllDigits str ds