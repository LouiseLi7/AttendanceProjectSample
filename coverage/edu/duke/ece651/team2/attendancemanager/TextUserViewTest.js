var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 112,
        "id": 954,
        "methods": [{"el": 34, "sc": 5, "sl": 11}, {"el": 61, "sc": 3, "sl": 36}, {
            "el": 82,
            "sc": 5,
            "sl": 63
        }, {"el": 110, "sc": 3, "sl": 84}],
        "name": "TextUserViewTest",
        "sl": 10
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_2": {
        "methods": [{"sl": 36}],
        "name": "testPrintStudents",
        "pass": true,
        "statements": [{"sl": 38}, {"sl": 39}, {"sl": 40}, {"sl": 42}, {"sl": 43}, {"sl": 44}, {"sl": 45}, {"sl": 46}, {"sl": 47}, {"sl": 49}, {"sl": 51}, {"sl": 52}, {"sl": 54}, {"sl": 59}, {"sl": 60}]
    },
    "test_25": {
        "methods": [{"sl": 84}],
        "name": "testPrintLectures",
        "pass": true,
        "statements": [{"sl": 86}, {"sl": 87}, {"sl": 88}, {"sl": 90}, {"sl": 91}, {"sl": 92}, {"sl": 93}, {"sl": 94}, {"sl": 95}, {"sl": 96}, {"sl": 97}, {"sl": 99}, {"sl": 101}, {"sl": 103}, {"sl": 108}, {"sl": 109}]
    },
    "test_4": {
        "methods": [{"sl": 11}],
        "name": "testPrintCourse",
        "pass": true,
        "statements": [{"sl": 13}, {"sl": 14}, {"sl": 15}, {"sl": 17}, {"sl": 19}, {"sl": 20}, {"sl": 21}, {"sl": 22}, {"sl": 24}, {"sl": 25}, {"sl": 27}, {"sl": 32}, {"sl": 33}]
    },
    "test_9": {
        "methods": [{"sl": 63}],
        "name": "testPrintStudentStatuses",
        "pass": true,
        "statements": [{"sl": 65}, {"sl": 66}, {"sl": 67}, {"sl": 69}, {"sl": 71}, {"sl": 72}, {"sl": 73}, {"sl": 74}, {"sl": 75}, {"sl": 76}, {"sl": 77}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [4], [], [4], [4], [4], [], [4], [], [4], [4], [4], [4], [], [4], [4], [], [4], [], [], [], [], [4], [4], [], [], [2], [], [2], [2], [2], [], [2], [2], [2], [2], [2], [2], [], [2], [], [2], [2], [], [2], [], [], [], [], [2], [2], [], [], [9], [], [9], [9], [9], [], [9], [], [9], [9], [9], [9], [9], [9], [9], [], [], [], [], [], [], [25], [], [25], [25], [25], [], [25], [25], [25], [25], [25], [25], [25], [25], [], [25], [], [25], [], [25], [], [], [], [], [25], [25], [], [], []]
