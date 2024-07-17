var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 21,
        "id": 1124,
        "methods": [{"el": 20, "sc": 3, "sl": 8}],
        "name": "StudentTest",
        "sl": 7
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_10": {
        "methods": [{"sl": 8}],
        "name": "test_setter_getter",
        "pass": true,
        "statements": [{"sl": 10}, {"sl": 11}, {"sl": 12}, {"sl": 13}, {"sl": 14}, {"sl": 15}, {"sl": 16}, {"sl": 17}, {"sl": 18}, {"sl": 19}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [10], [], [10], [10], [10], [10], [10], [10], [10], [10], [10], [10], [], []]
