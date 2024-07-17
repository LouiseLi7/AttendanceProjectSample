var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 126,
        "id": 297,
        "methods": [{"el": 23, "sc": 5, "sl": 21}, {"el": 32, "sc": 5, "sl": 30}, {
            "el": 58,
            "sc": 5,
            "sl": 45
        }, {"el": 77, "sc": 5, "sl": 69}, {"el": 89, "sc": 5, "sl": 86}, {"el": 103, "sc": 5, "sl": 95}, {
            "el": 117,
            "sc": 5,
            "sl": 109
        }, {"el": 125, "sc": 5, "sl": 123}],
        "name": "AttendanceSession",
        "sl": 12
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_1": {
        "methods": [{"sl": 21}, {"sl": 30}, {"sl": 45}, {"sl": 69}, {"sl": 123}],
        "name": "testEndLecture",
        "pass": true,
        "statements": [{"sl": 22}, {"sl": 31}, {"sl": 47}, {"sl": 48}, {"sl": 55}, {"sl": 56}, {"sl": 57}, {"sl": 70}, {"sl": 71}, {"sl": 72}, {"sl": 73}, {"sl": 124}]
    },
    "test_13": {
        "methods": [{"sl": 30}, {"sl": 45}],
        "name": "testRecordAttendanceNewRecord",
        "pass": true,
        "statements": [{"sl": 31}, {"sl": 47}, {"sl": 55}, {"sl": 56}, {"sl": 57}]
    },
    "test_16": {
        "methods": [{"sl": 30}, {"sl": 45}],
        "name": "testRecordAttendanceDuplicateRecord",
        "pass": true,
        "statements": [{"sl": 31}, {"sl": 47}, {"sl": 48}, {"sl": 49}, {"sl": 50}, {"sl": 55}, {"sl": 56}, {"sl": 57}]
    },
    "test_18": {
        "methods": [{"sl": 21}, {"sl": 30}, {"sl": 45}],
        "name": "testWriteRecordsToJSON",
        "pass": true,
        "statements": [{"sl": 22}, {"sl": 31}, {"sl": 47}, {"sl": 55}, {"sl": 56}, {"sl": 57}]
    },
    "test_26": {
        "methods": [{"sl": 86}],
        "name": "testRemoveNonexistentAttendanceRecord",
        "pass": true,
        "statements": [{"sl": 87}]
    },
    "test_27": {
        "methods": [{"sl": 21}, {"sl": 45}],
        "name": "testStartLecture",
        "pass": true,
        "statements": [{"sl": 22}, {"sl": 47}, {"sl": 48}, {"sl": 55}, {"sl": 56}, {"sl": 57}]
    },
    "test_28": {
        "methods": [{"sl": 21}, {"sl": 30}, {"sl": 45}],
        "name": "testWriteRecordsToCSV",
        "pass": true,
        "statements": [{"sl": 22}, {"sl": 31}, {"sl": 47}, {"sl": 55}, {"sl": 56}, {"sl": 57}]
    },
    "test_29": {
        "methods": [{"sl": 30}, {"sl": 45}, {"sl": 86}],
        "name": "testRemoveAttendanceRecord",
        "pass": true,
        "statements": [{"sl": 31}, {"sl": 47}, {"sl": 55}, {"sl": 56}, {"sl": 57}, {"sl": 87}]
    },
    "test_40": {
        "methods": [{"sl": 30}, {"sl": 45}, {"sl": 69}],
        "name": "testUpdateAttendanceRecord",
        "pass": true,
        "statements": [{"sl": 31}, {"sl": 47}, {"sl": 55}, {"sl": 56}, {"sl": 57}, {"sl": 70}, {"sl": 71}, {"sl": 72}, {"sl": 73}]
    },
    "test_7": {
        "methods": [{"sl": 69}],
        "name": "testUpdateNonexistentAttendanceRecord",
        "pass": true,
        "statements": [{"sl": 70}, {"sl": 76}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [1, 28, 18, 27], [1, 28, 18, 27], [], [], [], [], [], [], [], [16, 29, 13, 40, 1, 28, 18], [16, 29, 13, 40, 1, 28, 18], [], [], [], [], [], [], [], [], [], [], [], [], [], [16, 29, 13, 40, 1, 28, 18, 27], [], [16, 29, 13, 40, 1, 28, 18, 27], [16, 1, 27], [16], [16], [], [], [], [], [16, 29, 13, 40, 1, 28, 18, 27], [16, 29, 13, 40, 1, 28, 18, 27], [16, 29, 13, 40, 1, 28, 18, 27], [], [], [], [], [], [], [], [], [], [], [], [40, 1, 7], [40, 1, 7], [40, 1], [40, 1], [40, 1], [], [], [7], [], [], [], [], [], [], [], [], [], [29, 26], [29, 26], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [1], [1], [], []]
