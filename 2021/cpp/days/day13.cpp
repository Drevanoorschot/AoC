#include <vector>
#include <iostream>
#include "../util/day.cpp"
#include "unordered_set"
#include "../util/util.cpp"
class Point {
public:
    int x;
    int y;

    Point(int x, int y) {
        this->x = x;
        this->y = y;
    }

    bool operator==(const Point &p) const {
        return x == p.x && y == p.y;
    }
};

class PointHash {
public:
    size_t operator()(const Point &p) const {
        // cantor pairing
        return util::cantor_pair(p.x, p.y);
    }
};

enum Along {
    x = 'x', y = 'y'
};

class FoldInstruction {
public:
    Along along;
    int value;

    FoldInstruction(Along along, int value) {
        this->along = along;
        this->value = value;
    }
};

class Day13 : public Day {
private:
    std::unordered_set<Point, PointHash> points;
    std::vector<FoldInstruction> foldInstructions;

    static std::unordered_set<Point, PointHash>
    fold(const std::unordered_set<Point, PointHash> &pointSet, FoldInstruction fold) {
        auto newPoints = std::unordered_set<Point, PointHash>();
        for (Point point: pointSet) {
            if (fold.along == 'x' && point.x > fold.value) {
                point.x = point.x - (point.x - fold.value) * 2;
            }
            if (fold.along == 'y' && point.y > fold.value) {
                point.y = point.y - (point.y - fold.value) * 2;
            }
            newPoints.insert(point);
        }
        return newPoints;
    }

public:
    explicit Day13(const std::string &input) : Day(input) {
        points = std::unordered_set<Point, PointHash>();
        foldInstructions = std::vector<FoldInstruction>();
        std::string pointInput = input.substr(0, input.find("\n\n") + 1);
        std::string foldInput = input.substr(input.find("\n\n") + 2, std::string::npos);
        for(const auto& token : util::split_string(pointInput, "\n")) {
            int x = std::stoi(token.substr(0, token.find(',')));
            int y = std::stoi(token.substr(token.find(',') + 1, std::string::npos));
            points.insert(Point(x, y));
        }
        for(const auto& token : util::split_string(foldInput, "\n")) {
            auto along = Along(token.c_str()[token.find('=') - 1]);
            int val = std::stoi(token.substr(token.find('=') + 1, std::string::npos));
            foldInstructions.emplace_back(along, val);
        }
    }


    long q1() override {
        FoldInstruction foldInstruction = foldInstructions.front();
        return (long) fold(points, foldInstruction).size();
    }

    long q2() override {
        auto newPoints = points;
        for (FoldInstruction foldInstruction: foldInstructions) {
            newPoints = fold(newPoints, foldInstruction);
        }
        int max_x = 0;
        int max_y = 0;
        for(Point point : newPoints) {
            max_x = max_x > point.x ? max_x : point.x;
            max_y = max_y > point.y ? max_y : point.y;
        }
        for (int y = 0; y <= max_y; y++) {
            for (int x = 0; x <= max_x; x++) {
                std::cout << (newPoints.contains(Point(x, y)) ? "█" : " ");
            }
            std::cout << '\n';
        }
        return (long) newPoints.size();
    }
};
