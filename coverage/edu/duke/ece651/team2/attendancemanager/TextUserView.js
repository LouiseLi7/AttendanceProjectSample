var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 101,
        "id": 721,
        "methods": [{"el": 20, "sc": 3, "sl": 18}, {"el": 30, "sc": 3, "sl": 26}, {
            "el": 44,
            "sc": 3,
            "sl": 36
        }, {"el": 58, "sc": 3, "sl": 50}, {"el": 73, "sc": 3, "sl": 64}, {"el": 85, "sc": 3, "sl": 79}, {
            "el": 100,
            "sc": 3,
            "sl": 91
        }],
        "name": "TextUserView",
        "sl": 8
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_2": {
        "methods": [{"sl": 18}, {"sl": 26}, {"sl": 36}],
        "name": "testPrintStudents",
        "pass": true,
        "statements": [{"sl": 19}, {"sl": 27}, {"sl": 28}, {"sl": 29}, {"sl": 37}, {"sl": 38}, {"sl": 39}, {"sl": 40}, {"sl": 41}, {"sl": 42}]
    },
    "test_25": {
        "methods": [{"sl": 18}, {"sl": 26}, {"sl": 50}],
        "name": "testPrintLectures",
        "pass": true,
        "statements": [{"sl": 19}, {"sl": 27}, {"sl": 28}, {"sl": 29}, {"sl": 51}, {"sl": 52}, {"sl": 53}, {"sl": 54}, {"sl": 55}, {"sl": 56}]
    },
    "test_4": {
        "methods": [{"sl": 18}, {"sl": 26}, {"sl": 64}],
        "name": "testPrintCourse",
        "pass": true,
        "statements": [{"sl": 19}, {"sl": 27}, {"sl": 28}, {"sl": 29}, {"sl": 65}, {"sl": 66}, {"sl": 67}, {"sl": 71}]
    },
    "test_9": {"methods": [{"sl": 18}], "name": "testPrintStudentStatuses", "pass": true, "statements": [{"sl": 19}]}
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [9, 25, 2, 4], [9, 25, 2, 4], [], [], [], [], [], [], [25, 2, 4], [25, 2, 4], [25, 2, 4], [25, 2, 4], [], [], [], [], [], [], [2], [2], [2], [2], [2], [2], [2], [], [], [], [], [], [], [], [25], [25], [25], [25], [25], [25], [25], [], [], [], [], [], [], [], [4], [4], [4], [4], [], [], [], [4], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], []]
