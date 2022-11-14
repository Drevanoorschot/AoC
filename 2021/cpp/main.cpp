#include <cstring>
#include <fstream>
#include <iostream>
#include <sstream>

#include "string"

#include "days/day13.cpp"
#include "days/day14.cpp"

const char* EXAMPLE_PATH = "../inputs/example/%d.txt";
const char* PUZZLE_PATH = "../inputs/puzzle/%d.txt";


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
    sprintf(path, exampleMode ? EXAMPLE_PATH : PUZZLE_PATH, dayNumber);
    std::ifstream inputStream(path);
    std::stringstream inputBuffer;
    inputBuffer << inputStream.rdbuf();
    std::string input = inputBuffer.str();
    inputStream.close();

    switch (dayNumber) {
        case 13: {
            std::string str = std::string(input);
            Day13(str).run();
            return EXIT_SUCCESS;
        }
        case 14: {
            std::string str = std::string(input);
            Day14(str).run();
            return EXIT_SUCCESS;
        }
        default: {
            std::cerr << "[ERROR] Day " << dayNumber << " not supported\n";
            return EXIT_FAILURE;
        }
    }
}
