var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 28,
        "id": 620,
        "methods": [{"el": 19, "sc": 5, "sl": 17}, {"el": 27, "sc": 5, "sl": 24}],
        "name": "EmailAlertsListener",
        "sl": 6
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_30": {
        "methods": [{"sl": 17}, {"sl": 24}],
        "name": "testNotifyAttendanceChanged",
        "pass": true,
        "statements": [{"sl": 18}, {"sl": 26}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [30], [30], [], [], [], [], [], [30], [], [30], [], []]
