var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 57,
        "id": 1099,
        "methods": [{"el": 26, "sc": 3, "sl": 17}, {"el": 39, "sc": 3, "sl": 28}, {
            "el": 44,
            "sc": 3,
            "sl": 41
        }, {"el": 51, "sc": 3, "sl": 47}, {"el": 56, "sc": 3, "sl": 53}],
        "name": "LectureTest",
        "sl": 15
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_24": {"methods": [{"sl": 53}], "name": "testEndLecture", "pass": true, "statements": []},
    "test_3": {
        "methods": [{"sl": 17}, {"sl": 47}],
        "name": "testAttendanceRecord",
        "pass": true,
        "statements": [{"sl": 18}, {"sl": 20}, {"sl": 22}, {"sl": 23}, {"sl": 24}, {"sl": 25}, {"sl": 49}, {"sl": 50}]
    },
    "test_33": {
        "methods": [{"sl": 17}, {"sl": 28}],
        "name": "test_LectureGetterSetter",
        "pass": true,
        "statements": [{"sl": 18}, {"sl": 20}, {"sl": 22}, {"sl": 23}, {"sl": 24}, {"sl": 25}, {"sl": 30}, {"sl": 31}, {"sl": 32}, {"sl": 33}, {"sl": 34}, {"sl": 35}, {"sl": 36}, {"sl": 37}, {"sl": 38}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [33, 3], [33, 3], [], [33, 3], [], [33, 3], [33, 3], [33, 3], [33, 3], [], [], [33], [], [33], [33], [33], [33], [33], [33], [33], [33], [33], [], [], [], [], [], [], [], [], [3], [], [3], [3], [], [], [24], [], [], [], []]
