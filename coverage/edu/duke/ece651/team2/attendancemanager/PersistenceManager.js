var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 109,
        "id": 249,
        "methods": [{"el": 48, "sc": 5, "sl": 30}, {"el": 67, "sc": 5, "sl": 50}, {"el": 108, "sc": 5, "sl": 77}],
        "name": "PersistenceManager",
        "sl": 10
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_1": {
        "methods": [{"sl": 30}, {"sl": 77}],
        "name": "testEndLecture",
        "pass": true,
        "statements": [{"sl": 31}, {"sl": 32}, {"sl": 33}, {"sl": 45}, {"sl": 78}, {"sl": 79}, {"sl": 81}, {"sl": 105}]
    },
    "test_18": {
        "methods": [{"sl": 30}, {"sl": 77}],
        "name": "testWriteRecordsToJSON",
        "pass": true,
        "statements": [{"sl": 31}, {"sl": 32}, {"sl": 33}, {"sl": 45}, {"sl": 78}, {"sl": 79}, {"sl": 81}, {"sl": 105}]
    },
    "test_28": {
        "methods": [{"sl": 30}],
        "name": "testWriteRecordsToCSV",
        "pass": true,
        "statements": [{"sl": 31}, {"sl": 32}, {"sl": 33}, {"sl": 45}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [1, 28, 18], [1, 28, 18], [1, 28, 18], [1, 28, 18], [], [], [], [], [], [], [], [], [], [], [], [1, 28, 18], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [1, 18], [1, 18], [1, 18], [], [1, 18], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [1, 18], [], [], [], []]
