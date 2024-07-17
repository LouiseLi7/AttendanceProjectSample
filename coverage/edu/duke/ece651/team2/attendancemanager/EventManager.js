var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 41,
        "id": 242,
        "methods": [{"el": 22, "sc": 5, "sl": 20}, {"el": 31, "sc": 5, "sl": 29}, {"el": 40, "sc": 5, "sl": 36}],
        "name": "EventManager",
        "sl": 9
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_30": {
        "methods": [{"sl": 20}, {"sl": 29}, {"sl": 36}],
        "name": "testNotifyAttendanceChanged",
        "pass": true,
        "statements": [{"sl": 21}, {"sl": 30}, {"sl": 37}, {"sl": 38}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [30], [30], [], [], [], [], [], [], [], [30], [30], [], [], [], [], [], [30], [30], [30], [], [], []]
