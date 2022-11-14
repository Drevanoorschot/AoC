cmake_minimum_required(VERSION 3.23)
project(advent_of_code)

set(CMAKE_CXX_STANDARD 20)

add_executable(advent_of_code main.cpp util/day.cpp days/day13.cpp days/day14.cpp util/util.cpp)
