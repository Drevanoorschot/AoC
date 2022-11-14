#include <cstring>
#include <fstream>
#include <iostream>
#include <sstream>
#include "days/day01.cpp"

#include "string"

const char *EXAMPLE_PATH = "../inputs/example/%s.txt";
const char *PUZZLE_PATH = "../inputs/puzzle/%s.txt";


int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("USAGE main <day number> [-e]");
        return EXIT_FAILURE;
    }
    int dayNumber = std::stoi(argv[1]);
    bool exampleMode = argc >= 3 && std::strcmp(argv[2], "-e") == 0;
    printf("[INFO] running day %d in %s mode...\n", dayNumber, exampleMode ? "example" : "puzzle");

    // prep input data
    char path[exampleMode ? strlen(EXAMPLE_PATH) : strlen(PUZZLE_PATH)];
    sprintf(path, exampleMode ? EXAMPLE_PATH : PUZZLE_PATH, argv[1]);
    std::ifstream inputStream(path);
    std::stringstream inputBuffer;
    inputBuffer << inputStream.rdbuf();
    std::string input = inputBuffer.str();
    inputStream.close();

    switch (dayNumber) {
        case 1: {
            std::string str = std::string(input);
            Day01(str).run();
            return EXIT_SUCCESS;
        }
        default: {
            std::cerr << "[ERROR] Day " << dayNumber << " not supported\n";
            return EXIT_FAILURE;
        }
    }
}
