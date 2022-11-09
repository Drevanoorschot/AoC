#include <cstdio>
#include <string>
#include <utility>

class Day {
public:
    virtual long q1() = 0;
    virtual long q2() = 0;

    explicit Day(std::string input) {
        this->input = std::move(input);
    }

    void run() {
        printf("Answer q1: %ld\n", q1());
        printf("Answer q2: %ld\n", q2());
    }
protected:
    std::string input;
};