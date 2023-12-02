module Main (main) where

import System.Environment

import Util

import Day1
import Day2

main :: IO ()
main = do
  dayNumber <- parseInt . head <$> getArgs
  fileName <- (!! 1) <$> getArgs
  input <- inputToString dayNumber fileName
  putStrLn $ formatAnswer $ solveDay (dayNumber, input)

inputToString :: Int -> String -> IO String
inputToString dayNumber fileName = readFile $ "inputs/day" ++ show dayNumber ++ "/"++ fileName ++ ".txt"

parseInt :: String -> Int
parseInt str = read str :: Int

formatAnswer :: (Answer, Answer) -> String
formatAnswer (IntAnswer a1, IntAnswer a2) = 
    "Answers:\nq1: " ++ show a1 ++ "\nq2: " ++ show a2
formatAnswer (StrAnswer a1, StrAnswer a2) =
    "Answers:\nq1: " ++ a1 ++ "\nq2: " ++ a2
formatAnswer (_, _) = error "Both answers should be the same type"

solveDay :: (Int, String) -> (Answer, Answer)
solveDay (n, input)
  | n == 1 = Day1.solve input
  | n == 2 = Day2.solve input
  | otherwise = error $ "Day " ++ show n ++ " not yet implemented"
