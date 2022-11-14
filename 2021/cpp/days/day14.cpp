#include <list>
#include <map>
#include <unordered_map>
#include <climits>
#include "../util/util.cpp"

#include "../util/day.cpp"


class Day14 : public Day {
private:
    std::unordered_map<char, std::unordered_map<char, long>> pairs;
    std::unordered_map<char, std::unordered_map<char, char>> rules;
    char last_char;

public:
    explicit Day14(const std::string &input) : Day(input) {
        auto sequence = input.substr(0, input.find("\n\n"));
        for (int i = 0; i < sequence.length() - 1; i++) {
            pairs[sequence[i]][sequence[i + 1]] += 1;
        }
        last_char = sequence[sequence.length() - 1];
        std::string str_rules = input.substr(input.find("\n\n") + 2);
        for (auto token: util::split_string(str_rules, "\n")) {
            char first = token.at(0);
            char last = token.at(1);
            char map_to = token.at(6);
            rules[first][last] = map_to;
        }
    }

    long q1() override {
        for (int _ = 0; _ < 10; _++) {
            extend_polymer();
        }
        return high_low_diff();
    }

    long q2() override {
        // 30 more times
        for (int _ = 0; _ < 30; _++) {
            extend_polymer();
        }
        return high_low_diff();
    }


    void extend_polymer() {
        std::unordered_map<char, std::unordered_map<char, long>> new_pairs;
        for (const auto &p1: pairs) {
            for (auto p2: p1.second) {
                if (rules[p1.first].contains(p2.first)) { // there is a rule
                    // add lhs of pair triplet
                    new_pairs[p1.first][rules[p1.first][p2.first]] += p2.second;
                    // add rhs of pair triplet
                    new_pairs[rules[p1.first][p2.first]][p2.first] += p2.second;
                } else { // there is no rule
                    new_pairs[p1.first][p2.first] = p2.second; // copy old value
                }
            }
        }
        pairs = new_pairs;
    }

    long high_low_diff() {
        long low = LONG_MAX;
        long high = 0;
        for (const auto &char_pairs: pairs) {
            long count = char_pairs.first == last_char ? 1 : 0;
            for (auto pair: char_pairs.second) {
                count += pair.second;
            }
            low = count > low ? low : count;
            high = count < high ? high : count;
        }
        return high - low;
    }
};

