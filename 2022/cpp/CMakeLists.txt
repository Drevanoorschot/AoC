cmake_minimum_required(VERSION 3.23)
project(cpp)

set(CMAKE_CXX_STANDARD 20)

add_executable(cpp main.cpp util/day.cpp util/util.cpp days/day01.cpp)
