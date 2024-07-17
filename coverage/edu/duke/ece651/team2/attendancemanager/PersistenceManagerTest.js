var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 44,
        "id": 1218,
        "methods": [{"el": 28, "sc": 5, "sl": 16}, {"el": 43, "sc": 5, "sl": 30}],
        "name": "PersistenceManagerTest",
        "sl": 8
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_18": {
        "methods": [{"sl": 30}],
        "name": "testWriteRecordsToJSON",
        "pass": true,
        "statements": [{"sl": 32}, {"sl": 33}, {"sl": 34}, {"sl": 35}, {"sl": 36}, {"sl": 37}, {"sl": 38}, {"sl": 39}, {"sl": 40}, {"sl": 41}, {"sl": 42}]
    },
    "test_28": {
        "methods": [{"sl": 16}],
        "name": "testWriteRecordsToCSV",
        "pass": true,
        "statements": [{"sl": 18}, {"sl": 19}, {"sl": 20}, {"sl": 21}, {"sl": 22}, {"sl": 23}, {"sl": 24}, {"sl": 25}, {"sl": 26}, {"sl": 27}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [28], [], [28], [28], [28], [28], [28], [28], [28], [28], [28], [28], [], [], [18], [], [18], [18], [18], [18], [18], [18], [18], [18], [18], [18], [18], [], []]
