var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 15,
        "id": 1123,
        "methods": [{"el": 14, "sc": 5, "sl": 10}],
        "name": "AppTest",
        "sl": 9
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_8": {"methods": [{"sl": 10}], "name": "appHasAGreeting", "pass": true, "statements": []}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [8], [], [], [], [], []]
