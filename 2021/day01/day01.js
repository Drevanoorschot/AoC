const fs = require('fs');

const input_data = fs.readFileSync("day01/input.txt", 'utf-8').split("\n").map(x => parseInt(x));

function part1(input) {
    let increases = 0;
    for (let i = 1; i <= input.length; i++) {
        if (input[i - 1] < input[i]) {
            increases++;
        }
    }
    return increases;
}

function part2(input) {
    let increases = 0;
    let curr = input[0] + input[1] + input[2];
    for (let i = 3; i <= input.length; i++) {
        let next = curr - input[i - 3] + input[i];
        if (next > curr) {
            increases++;
        }
        curr = next;
    }
    return increases;
}

console.log("answer part 1: " + part1(input_data))
console.log("answer part 2: " + part2(input_data))