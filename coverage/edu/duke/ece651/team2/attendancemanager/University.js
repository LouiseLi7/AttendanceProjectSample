var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 20,
        "id": 50,
        "methods": [{"el": 11, "sc": 5, "sl": 8}, {"el": 15, "sc": 5, "sl": 13}, {"el": 19, "sc": 5, "sl": 17}],
        "name": "University",
        "sl": 3
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_2": {
        "methods": [{"sl": 8}],
        "name": "testPrintStudents",
        "pass": true,
        "statements": [{"sl": 9}, {"sl": 10}]
    },
    "test_22": {
        "methods": [{"sl": 8}],
        "name": "testReadNewCourse",
        "pass": true,
        "statements": [{"sl": 9}, {"sl": 10}]
    },
    "test_25": {
        "methods": [{"sl": 8}],
        "name": "testPrintLectures",
        "pass": true,
        "statements": [{"sl": 9}, {"sl": 10}]
    },
    "test_4": {"methods": [{"sl": 8}], "name": "testPrintCourse", "pass": true, "statements": [{"sl": 9}, {"sl": 10}]},
    "test_43": {
        "methods": [{"sl": 8}, {"sl": 13}],
        "name": "testReadNewProfessor",
        "pass": true,
        "statements": [{"sl": 9}, {"sl": 10}, {"sl": 14}]
    },
    "test_9": {
        "methods": [{"sl": 8}],
        "name": "testPrintStudentStatuses",
        "pass": true,
        "statements": [{"sl": 9}, {"sl": 10}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [22, 9, 25, 2, 43, 4], [22, 9, 25, 2, 43, 4], [22, 9, 25, 2, 43, 4], [], [], [43], [43], [], [], [], [], [], []]
