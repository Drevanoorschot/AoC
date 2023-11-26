module Main (main) where

import Day1
import System.Environment

main :: IO ()
main = do
  dayNumber <- parseInt <$> head <$> getArgs;
  input <- inputToString dayNumber;
  putStrLn $ formatAnswer $ solveDay (dayNumber, input);

inputToString :: Int -> IO String
inputToString dayNumber = readFile $ "inputs/day" ++ show dayNumber ++ ".txt"

parseInt :: String -> Int
parseInt str = read str :: Int

formatAnswer :: (Int, Int) -> String
formatAnswer (a1, a2) = "Answers:\nq1: " ++ show a1 ++ "\nq2: " ++ show a2

solveDay :: (Int, String) -> (Int, Int)
solveDay (n, input)
  | n == 1 = Day1.solve input
