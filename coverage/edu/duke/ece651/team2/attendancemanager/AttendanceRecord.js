var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 138,
        "id": 26,
        "methods": [{"el": 55, "sc": 5, "sl": 48}, {"el": 64, "sc": 5, "sl": 62}, {
            "el": 73,
            "sc": 5,
            "sl": 71
        }, {"el": 82, "sc": 5, "sl": 80}, {"el": 91, "sc": 5, "sl": 89}, {"el": 100, "sc": 5, "sl": 98}, {
            "el": 109,
            "sc": 5,
            "sl": 107
        }, {"el": 118, "sc": 5, "sl": 116}, {"el": 127, "sc": 5, "sl": 125}, {"el": 136, "sc": 5, "sl": 134}],
        "name": "AttendanceRecord",
        "sl": 9
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_1": {
        "methods": [{"sl": 48}, {"sl": 80}, {"sl": 107}],
        "name": "testEndLecture",
        "pass": true,
        "statements": [{"sl": 50}, {"sl": 51}, {"sl": 52}, {"sl": 53}, {"sl": 54}, {"sl": 81}, {"sl": 108}]
    },
    "test_13": {
        "methods": [{"sl": 48}, {"sl": 80}, {"sl": 98}, {"sl": 116}, {"sl": 125}],
        "name": "testRecordAttendanceNewRecord",
        "pass": true,
        "statements": [{"sl": 50}, {"sl": 51}, {"sl": 52}, {"sl": 53}, {"sl": 54}, {"sl": 81}, {"sl": 99}, {"sl": 117}, {"sl": 126}]
    },
    "test_14": {
        "methods": [{"sl": 98}, {"sl": 107}],
        "name": "testSetStatus",
        "pass": true,
        "statements": [{"sl": 99}, {"sl": 108}]
    },
    "test_16": {
        "methods": [{"sl": 48}, {"sl": 80}, {"sl": 116}],
        "name": "testRecordAttendanceDuplicateRecord",
        "pass": true,
        "statements": [{"sl": 50}, {"sl": 51}, {"sl": 52}, {"sl": 53}, {"sl": 54}, {"sl": 81}, {"sl": 117}]
    },
    "test_18": {
        "methods": [{"sl": 48}],
        "name": "testWriteRecordsToJSON",
        "pass": true,
        "statements": [{"sl": 50}, {"sl": 51}, {"sl": 52}, {"sl": 53}, {"sl": 54}]
    },
    "test_27": {
        "methods": [{"sl": 48}, {"sl": 80}],
        "name": "testStartLecture",
        "pass": true,
        "statements": [{"sl": 50}, {"sl": 51}, {"sl": 52}, {"sl": 53}, {"sl": 54}, {"sl": 81}]
    },
    "test_28": {
        "methods": [{"sl": 48}],
        "name": "testWriteRecordsToCSV",
        "pass": true,
        "statements": [{"sl": 50}, {"sl": 51}, {"sl": 52}, {"sl": 53}, {"sl": 54}]
    },
    "test_29": {
        "methods": [{"sl": 48}, {"sl": 80}],
        "name": "testRemoveAttendanceRecord",
        "pass": true,
        "statements": [{"sl": 50}, {"sl": 51}, {"sl": 52}, {"sl": 53}, {"sl": 54}, {"sl": 81}]
    },
    "test_32": {"methods": [{"sl": 98}], "name": "testGetStatus", "pass": true, "statements": [{"sl": 99}]},
    "test_35": {
        "methods": [{"sl": 116}, {"sl": 134}],
        "name": "testSetLectureID",
        "pass": true,
        "statements": [{"sl": 117}, {"sl": 135}]
    },
    "test_37": {"methods": [{"sl": 80}], "name": "testGetStudentID", "pass": true, "statements": [{"sl": 81}]},
    "test_38": {
        "methods": [{"sl": 80}, {"sl": 89}],
        "name": "testSetStudentID",
        "pass": true,
        "statements": [{"sl": 81}, {"sl": 90}]
    },
    "test_40": {
        "methods": [{"sl": 48}, {"sl": 80}, {"sl": 98}, {"sl": 107}],
        "name": "testUpdateAttendanceRecord",
        "pass": true,
        "statements": [{"sl": 50}, {"sl": 51}, {"sl": 52}, {"sl": 53}, {"sl": 54}, {"sl": 81}, {"sl": 99}, {"sl": 108}]
    },
    "test_41": {"methods": [{"sl": 116}], "name": "testGetLectureID", "pass": true, "statements": [{"sl": 117}]},
    "test_42": {
        "methods": [{"sl": 62}, {"sl": 71}],
        "name": "testSetAttendanceDate",
        "pass": true,
        "statements": [{"sl": 63}, {"sl": 72}]
    },
    "test_6": {"methods": [{"sl": 62}], "name": "testGetAttendanceDate", "pass": true, "statements": [{"sl": 63}]}
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [29, 13, 40, 1, 28, 18, 16, 27], [], [29, 13, 40, 1, 28, 18, 16, 27], [29, 13, 40, 1, 28, 18, 16, 27], [29, 13, 40, 1, 28, 18, 16, 27], [29, 13, 40, 1, 28, 18, 16, 27], [29, 13, 40, 1, 28, 18, 16, 27], [], [], [], [], [], [], [], [6, 42], [6, 42], [], [], [], [], [], [], [], [42], [42], [], [], [], [], [], [], [], [29, 13, 40, 1, 16, 37, 38, 27], [29, 13, 40, 1, 16, 37, 38, 27], [], [], [], [], [], [], [], [38], [38], [], [], [], [], [], [], [], [13, 32, 40, 14], [13, 32, 40, 14], [], [], [], [], [], [], [], [40, 1, 14], [40, 1, 14], [], [], [], [], [], [], [], [13, 16, 41, 35], [13, 16, 41, 35], [], [], [], [], [], [], [], [13], [13], [], [], [], [], [], [], [], [35], [35], [], [], []]
