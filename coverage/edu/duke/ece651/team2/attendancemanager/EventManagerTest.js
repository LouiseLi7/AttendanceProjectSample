var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 43,
        "id": 763,
        "methods": [{"el": 42, "sc": 5, "sl": 17}],
        "name": "EventManagerTest",
        "sl": 15
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_30": {
        "methods": [{"sl": 17}],
        "name": "testNotifyAttendanceChanged",
        "pass": true,
        "statements": [{"sl": 20}, {"sl": 21}, {"sl": 22}, {"sl": 24}, {"sl": 25}, {"sl": 26}, {"sl": 28}, {"sl": 29}, {"sl": 30}, {"sl": 32}, {"sl": 33}, {"sl": 34}, {"sl": 36}, {"sl": 39}, {"sl": 40}, {"sl": 41}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [30], [], [], [30], [30], [30], [], [30], [30], [30], [], [30], [30], [30], [], [30], [30], [30], [], [30], [], [], [30], [30], [30], [], []]
