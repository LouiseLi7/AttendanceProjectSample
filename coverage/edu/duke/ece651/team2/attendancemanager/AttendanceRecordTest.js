var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 102,
        "id": 1166,
        "methods": [{"el": 31, "sc": 5, "sl": 23}, {"el": 42, "sc": 5, "sl": 33}, {
            "el": 55,
            "sc": 5,
            "sl": 44
        }, {"el": 61, "sc": 5, "sl": 57}, {"el": 69, "sc": 5, "sl": 63}, {"el": 75, "sc": 5, "sl": 71}, {
            "el": 87,
            "sc": 5,
            "sl": 77
        }, {"el": 93, "sc": 5, "sl": 89}, {"el": 101, "sc": 5, "sl": 95}],
        "name": "AttendanceRecordTest",
        "sl": 14
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_14": {
        "methods": [{"sl": 77}],
        "name": "testSetStatus",
        "pass": true,
        "statements": [{"sl": 79}, {"sl": 80}, {"sl": 81}, {"sl": 82}, {"sl": 83}, {"sl": 84}, {"sl": 85}, {"sl": 86}]
    },
    "test_32": {"methods": [{"sl": 71}], "name": "testGetStatus", "pass": true, "statements": [{"sl": 73}, {"sl": 74}]},
    "test_35": {
        "methods": [{"sl": 95}],
        "name": "testSetLectureID",
        "pass": true,
        "statements": [{"sl": 97}, {"sl": 98}, {"sl": 99}, {"sl": 100}]
    },
    "test_37": {
        "methods": [{"sl": 57}],
        "name": "testGetStudentID",
        "pass": true,
        "statements": [{"sl": 59}, {"sl": 60}]
    },
    "test_38": {
        "methods": [{"sl": 63}],
        "name": "testSetStudentID",
        "pass": true,
        "statements": [{"sl": 65}, {"sl": 66}, {"sl": 67}, {"sl": 68}]
    },
    "test_41": {
        "methods": [{"sl": 89}],
        "name": "testGetLectureID",
        "pass": true,
        "statements": [{"sl": 91}, {"sl": 92}]
    },
    "test_42": {
        "methods": [{"sl": 44}],
        "name": "testSetAttendanceDate",
        "pass": true,
        "statements": [{"sl": 46}, {"sl": 47}, {"sl": 48}, {"sl": 49}, {"sl": 50}, {"sl": 52}, {"sl": 53}, {"sl": 54}]
    },
    "test_6": {
        "methods": [{"sl": 33}],
        "name": "testGetAttendanceDate",
        "pass": true,
        "statements": [{"sl": 35}, {"sl": 36}, {"sl": 37}, {"sl": 38}, {"sl": 39}, {"sl": 40}, {"sl": 41}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [6], [], [6], [6], [6], [6], [6], [6], [6], [], [], [42], [], [42], [42], [42], [42], [42], [], [42], [42], [42], [], [], [37], [], [37], [37], [], [], [38], [], [38], [38], [38], [38], [], [], [32], [], [32], [32], [], [], [14], [], [14], [14], [14], [14], [14], [14], [14], [14], [], [], [41], [], [41], [41], [], [], [35], [], [35], [35], [35], [35], [], []]
