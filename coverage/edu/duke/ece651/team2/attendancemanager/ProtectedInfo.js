var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 18,
        "id": 713,
        "methods": [{"el": 10, "sc": 5, "sl": 8}, {"el": 17, "sc": 5, "sl": 12}],
        "name": "ProtectedInfo",
        "sl": 5
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_43": {
        "methods": [{"sl": 8}],
        "name": "testReadNewProfessor",
        "pass": true,
        "statements": [{"sl": 9}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [43], [43], [], [], [], [], [], [], [], [], []]
