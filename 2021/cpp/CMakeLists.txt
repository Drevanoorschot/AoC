cmake_minimum_required(VERSION 3.23)
project(advent_of_code)

set(CMAKE_CXX_STANDARD 20)

add_executable(advent_of_code main.cpp days/day.cpp days/day13.cpp)
