var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 105,
        "id": 1241,
        "methods": [{"el": 28, "sc": 5, "sl": 21}, {"el": 47, "sc": 5, "sl": 30}, {
            "el": 64,
            "sc": 5,
            "sl": 49
        }, {"el": 80, "sc": 5, "sl": 66}, {"el": 92, "sc": 5, "sl": 82}, {"el": 98, "sc": 5, "sl": 94}, {
            "el": 104,
            "sc": 5,
            "sl": 100
        }],
        "name": "AttendanceSessionTest",
        "sl": 10
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_13": {
        "methods": [{"sl": 30}],
        "name": "testRecordAttendanceNewRecord",
        "pass": true,
        "statements": [{"sl": 32}, {"sl": 33}, {"sl": 34}, {"sl": 35}, {"sl": 38}, {"sl": 41}, {"sl": 42}, {"sl": 43}, {"sl": 44}, {"sl": 45}, {"sl": 46}]
    },
    "test_16": {
        "methods": [{"sl": 49}],
        "name": "testRecordAttendanceDuplicateRecord",
        "pass": true,
        "statements": [{"sl": 51}, {"sl": 52}, {"sl": 53}, {"sl": 54}, {"sl": 57}, {"sl": 60}, {"sl": 63}]
    },
    "test_26": {
        "methods": [{"sl": 100}],
        "name": "testRemoveNonexistentAttendanceRecord",
        "pass": true,
        "statements": [{"sl": 103}]
    },
    "test_29": {
        "methods": [{"sl": 82}],
        "name": "testRemoveAttendanceRecord",
        "pass": true,
        "statements": [{"sl": 85}, {"sl": 86}, {"sl": 89}, {"sl": 91}]
    },
    "test_40": {
        "methods": [{"sl": 66}],
        "name": "testUpdateAttendanceRecord",
        "pass": true,
        "statements": [{"sl": 70}, {"sl": 71}, {"sl": 74}, {"sl": 78}, {"sl": 79}]
    },
    "test_7": {
        "methods": [{"sl": 94}],
        "name": "testUpdateNonexistentAttendanceRecord",
        "pass": true,
        "statements": [{"sl": 97}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [13], [], [13], [13], [13], [13], [], [], [13], [], [], [13], [13], [13], [13], [13], [13], [], [], [16], [], [16], [16], [16], [16], [], [], [16], [], [], [16], [], [], [16], [], [], [40], [], [], [], [40], [40], [], [], [40], [], [], [], [40], [40], [], [], [29], [], [], [29], [29], [], [], [29], [], [29], [], [], [7], [], [], [7], [], [], [26], [], [], [26], [], []]
