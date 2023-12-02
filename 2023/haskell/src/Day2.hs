module Day2 (solve) where

import Util

data Game = Game Int [[Color]] deriving Show

data Color = Color String Int deriving Show

colors :: [String]
colors = ["blue", "red", "green"]

solve :: String -> (Answer, Answer)
solve input = (q1 input, q2 input)

q1 :: String -> Answer
q1 input = IntAnswer $ sum $ fmap (\(Game i _) -> i) $ filter isValidGame $ parseGames input

q2 :: String -> Answer
q2 input = IntAnswer $ sum $ gamePower <$> parseGames input

-- q1

isValidGame :: Game -> Bool
isValidGame (Game _ cs) = and (fmap validateColor (concat cs))

validateColor :: Color -> Bool
validateColor (Color c i)
    | c == "red" = i <= 12
    | c == "green" = i <= 13
    | c == "blue" = i <= 14
    | otherwise = error $ "no color named \"" ++ c ++ "\""

-- q2
gamePower :: Game -> Int
gamePower (Game _ css) = product $ fmap (colorPower css) colors

colorPower :: [[Color]] -> String -> Int
colorPower css colorName = maxColor $ filterColors (concat css) colorName

filterColors :: [Color] -> String -> [Color]
filterColors cs colorName = filter (\(Color s _) -> s == colorName) cs

maxColor :: [Color] -> Int
maxColor cs = foldl max 0 $ fmap (\(Color _ i) -> i) cs

-- parsing

parseGames :: String -> [Game]
parseGames str = parseGame <$> splitToList str "\n";

parseGame :: String -> Game
parseGame str = Game (getGameNr $ takeWhile (/= ':') str) (parseGrabs $ tail $ dropWhile (/= ':') str)

getGameNr :: String -> Int
getGameNr str = read (drop 5 str) :: Int

parseGrabs :: String -> [[Color]]
parseGrabs str = parseGrab <$> splitToList str ";"

parseGrab :: String -> [Color]
parseGrab str = fmap (parseColor colors) (splitToList str ",")

parseColor :: [String] -> String -> Color
parseColor (c:cs) str
    | take (length c) (reverse str) == reverse c = Color c (read (takeWhile (/= head c) str) :: Int)
    | otherwise = parseColor cs str
parseColor [] str = error $ "Cannot find a valid color in \"" ++ str ++ "\""
