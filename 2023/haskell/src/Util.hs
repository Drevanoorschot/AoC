module Util (Answer (IntAnswer, StrAnswer), splitToList) where

import Data.Text(unpack, pack, splitOn)

data Answer = IntAnswer Int | StrAnswer String

splitToList :: String -> String -> [String]
splitToList str substr = fmap unpack (splitOn (pack substr) (pack str))