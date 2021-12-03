const fs = require('fs');

class Instruction {
    constructor(direction, distance) {
        this.direction = direction;
        this.distance = distance;
    }
}

const input_data = fs.readFileSync("day02/input.txt", 'utf-8').split("\n")
    .map(x => new Instruction(
        x.split(" ")[0],
        parseInt(x.split(" ")[1])
    ));

function part1() {
    let depth = 0;
    let hor_pos = 0;
    for (let instruction of input_data) {
        switch (instruction.direction) {
            case "up":
                depth -= instruction.distance;
                continue;
            case "down":
                depth += instruction.distance;
                continue;
            case "forward":
                hor_pos += instruction.distance;
        }
    }
    return depth * hor_pos;
}

function part2() {
    let depth = 0;
    let hor_pos = 0;
    let aim = 0;
    for (let instruction of input_data) {
        switch (instruction.direction) {
            case "up":
                aim -= instruction.distance;
                continue;
            case "down":
                aim += instruction.distance;
                continue;
            case "forward":
                hor_pos += instruction.distance;
                depth += instruction.distance * aim;
        }
    }
    return hor_pos * depth;
}

console.log("answer part 1: " + part1(input_data));
console.log("answer part 2: " + part2(input_data));