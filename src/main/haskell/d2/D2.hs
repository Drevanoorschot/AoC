module D2 where
import D2input

data PasswordSpec = PS Int Int Char [Char] deriving (Show)
parsePasswordSpec :: String -> PasswordSpec
parsePasswordSpec s = PS (pMin s "") (pMax s "" True) (pChar s) (pPassword s)

pMin :: String -> String -> Int
pMin (c:s) p
  | c == '-'  = read p::Int
  | otherwise = pMin s (p ++ [c])
--pMin "" p = error "malformed string"

pMax :: String -> String -> Bool -> Int
pMax (c:s) p skipping
  | c == '-' = pMax s p False
  | skipping = pMax s p skipping
  | c == ' ' = read p::Int
  | otherwise = pMax s (p ++ [c]) skipping

pChar :: String -> Char
pChar (' ':c:':':s) = c
pChar (c:s) = pChar s

pPassword :: String -> String
pPassword (':':' ':p) = p
pPassword (c:s) = pPassword s

extractMin :: PasswordSpec -> Int
extractMin (PS x _ _ _) = x

extractMax :: PasswordSpec -> Int
extractMax (PS _ x _ _) = x

extractChar :: PasswordSpec -> Char
extractChar (PS _ _ x _) = x

extractPassword :: PasswordSpec -> String
extractPassword (PS _ _ _ x) = x

countInString :: String -> Char -> Int
countInString s c = length $ filter (==c) s

ex1 :: [[Char]] -> Int
ex1 xs = length $ filter (\x -> (extractMin x) <= countInString (extractPassword x) (extractChar x) &&
                                (extractMax x) >= countInString (extractPassword x) (extractChar x)) $
                                map parsePasswordSpec xs
ex2 :: [[Char]] -> Int
ex2 xs = length $ filter (\x -> ((extractPassword x)!!((extractMin x) - 1) == extractChar x) `xor`
                                ((extractPassword x)!!((extractMax x) - 1) == extractChar x)) $
                                map parsePasswordSpec xs

xor :: Bool -> Bool -> Bool
xor a b = (a || b) && not (a && b)



