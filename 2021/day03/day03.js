const fs = require('fs');

const input = fs.readFileSync("input.txt", 'utf-8').split("\n");


function part1() {
    let gamma = "";
    for (let i = 0; i < input[0].length; i++) {
        let zeroes = 0;
        let ones = 0;
        for (let r = 0; r < input.length; r++) {
            input[r].charAt(i) === '0' ? zeroes++ : ones++;
        }
        zeroes > ones ? gamma += "0" : gamma += "1";
    }
    let epsilon = "";
    for (let i = 0; i < gamma.length; i++) {
        gamma.charAt(i) === '0' ? epsilon += "1" : epsilon += "0";
    }
    return parseInt(gamma, 2) * parseInt(epsilon, 2);
}

function oxygen() {
    let possible_ox = "";
    let hits = input.slice();
    while (true) {
        for (let i = 0; i < hits[0].length; i++) {
            let zeroes = 0;
            let ones = 0;
            for (let r = 0; r < hits.length; r++) {
                hits[r].charAt(i) === '0' ? zeroes++ : ones++;
            }
            zeroes > ones ? possible_ox += "0" : possible_ox += "1";
            let new_hits = [];
            for (let j = 0; j < hits.length; j++) {
                if (hits[j].startsWith(possible_ox)) {
                    new_hits.push(hits[j]);
                }
            }
            if (new_hits.length === 1) {
                return parseInt(new_hits[0], 2);
            } else {
                hits = new_hits;
            }
        }
    }
}

function co2() {
    let possible_co2 = "";
    let hits = input.slice();
    while (true) {
        for (let i = 0; i < hits[0].length; i++) {
            let zeroes = 0;
            let ones = 0;
            for (let r = 0; r < hits.length; r++) {
                hits[r].charAt(i) === '0' ? zeroes++ : ones++;
            }
            zeroes > ones ? possible_co2 += "1" : possible_co2 += "0";
            let new_hits = [];
            for (let j = 0; j < hits.length; j++) {
                if (hits[j].startsWith(possible_co2)) {
                    new_hits.push(hits[j]);
                }
            }
            if (new_hits.length === 1) {
                return parseInt(new_hits[0], 2);
            } else {
                hits = new_hits;
            }
        }
    }
}

function part2() {
    return oxygen() * co2()
}

console.log("answer part 1: " + part1())
console.log("answer part 2: " + part2())