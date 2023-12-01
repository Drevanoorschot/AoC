module Main (main) where

import System.Environment

import Util

import Day1

main :: IO ()
main = do
  dayNumber <- parseInt <$> head <$> getArgs
  test <- (==) "test" <$> head <$> drop 1 <$> getArgs
  input <- inputToString dayNumber test
  putStrLn $ formatAnswer $ solveDay (dayNumber, input)

inputToString :: Int -> Bool -> IO String
inputToString dayNumber test
  | not test = readFile $ "inputs/day" ++ show dayNumber ++ "/input.txt"
  | test = readFile $ "inputs/day" ++ show dayNumber ++ "/test.txt"

parseInt :: String -> Int
parseInt str = read str :: Int

formatAnswer :: (Answer, Answer) -> String
formatAnswer (IntAnswer a1, IntAnswer a2) = 
    "Answers:\nq1: " ++ show a1 ++ "\nq2: " ++ show a2
formatAnswer (StrAnswer a1, StrAnswer a2) =
    "Answers:\nq1: " ++ a1 ++ "\nq2: " ++ a2

solveDay :: (Int, String) -> (Answer, Answer)
solveDay (n, input)
  | n == 1 = Day1.solve input
