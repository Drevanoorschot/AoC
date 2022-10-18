const fs = require('fs');

const input_data = fs.readFileSync("input.txt", 'utf-8').split("\n").map(x => parseInt(x));

function part1() {
    let increases = 0;
    for (let i = 1; i <= input_data.length; i++) {
        if (input_data[i - 1] < input_data[i]) {
            increases++;
        }
    }
    return increases;
}

function part2() {
    let increases = 0;
    let curr = input_data[0] + input_data[1] + input_data[2];
    for (let i = 3; i <= input_data.length; i++) {
        let next = curr - input_data[i - 3] + input_data[i];
        if (next > curr) {
            increases++;
        }
        curr = next;
    }
    return increases;
}

console.log("answer part 1: " + part1())
console.log("answer part 2: " + part2())