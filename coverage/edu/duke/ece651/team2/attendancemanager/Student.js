var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 103,
        "id": 0,
        "methods": [{"el": 32, "sc": 3, "sl": 27}, {"el": 46, "sc": 3, "sl": 41}, {
            "el": 54,
            "sc": 3,
            "sl": 52
        }, {"el": 60, "sc": 3, "sl": 60}, {"el": 68, "sc": 3, "sl": 66}, {"el": 74, "sc": 3, "sl": 74}, {
            "el": 82,
            "sc": 3,
            "sl": 80
        }, {"el": 88, "sc": 3, "sl": 88}, {"el": 96, "sc": 3, "sl": 94}, {"el": 102, "sc": 3, "sl": 102}],
        "name": "Student",
        "sl": 6
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_1": {
        "methods": [{"sl": 74}, {"sl": 102}],
        "name": "testEndLecture",
        "pass": true,
        "statements": [{"sl": 74}, {"sl": 102}]
    },
    "test_10": {
        "methods": [{"sl": 27}, {"sl": 52}, {"sl": 60}, {"sl": 66}, {"sl": 74}, {"sl": 80}, {"sl": 88}, {"sl": 94}, {"sl": 102}],
        "name": "test_setter_getter",
        "pass": true,
        "statements": [{"sl": 28}, {"sl": 29}, {"sl": 30}, {"sl": 31}, {"sl": 53}, {"sl": 60}, {"sl": 67}, {"sl": 74}, {"sl": 81}, {"sl": 88}, {"sl": 95}, {"sl": 102}]
    },
    "test_12": {
        "methods": [{"sl": 41}, {"sl": 102}],
        "name": "testReadStudentStatus",
        "pass": true,
        "statements": [{"sl": 42}, {"sl": 43}, {"sl": 44}, {"sl": 45}, {"sl": 102}]
    },
    "test_2": {
        "methods": [{"sl": 41}, {"sl": 60}],
        "name": "testPrintStudents",
        "pass": true,
        "statements": [{"sl": 42}, {"sl": 43}, {"sl": 44}, {"sl": 45}, {"sl": 60}]
    },
    "test_22": {
        "methods": [{"sl": 41}],
        "name": "testReadNewCourse",
        "pass": true,
        "statements": [{"sl": 42}, {"sl": 43}, {"sl": 44}, {"sl": 45}]
    },
    "test_27": {
        "methods": [{"sl": 74}, {"sl": 102}],
        "name": "testStartLecture",
        "pass": true,
        "statements": [{"sl": 74}, {"sl": 102}]
    },
    "test_3": {
        "methods": [{"sl": 41}],
        "name": "testAttendanceRecord",
        "pass": true,
        "statements": [{"sl": 42}, {"sl": 43}, {"sl": 44}, {"sl": 45}]
    },
    "test_31": {
        "methods": [{"sl": 41}, {"sl": 60}, {"sl": 74}, {"sl": 88}, {"sl": 102}],
        "name": "testReadNewStudents",
        "pass": true,
        "statements": [{"sl": 42}, {"sl": 43}, {"sl": 44}, {"sl": 45}, {"sl": 60}, {"sl": 74}, {"sl": 88}, {"sl": 102}]
    },
    "test_33": {
        "methods": [{"sl": 41}],
        "name": "test_LectureGetterSetter",
        "pass": true,
        "statements": [{"sl": 42}, {"sl": 43}, {"sl": 44}, {"sl": 45}]
    },
    "test_9": {
        "methods": [{"sl": 41}],
        "name": "testPrintStudentStatuses",
        "pass": true,
        "statements": [{"sl": 42}, {"sl": 43}, {"sl": 44}, {"sl": 45}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [10], [10], [10], [10], [10], [], [], [], [], [], [], [], [], [], [22, 31, 9, 2, 33, 3, 12], [22, 31, 9, 2, 33, 3, 12], [22, 31, 9, 2, 33, 3, 12], [22, 31, 9, 2, 33, 3, 12], [22, 31, 9, 2, 33, 3, 12], [], [], [], [], [], [], [10], [10], [], [], [], [], [], [], [31, 10, 2], [], [], [], [], [], [10], [10], [], [], [], [], [], [], [31, 10, 1, 27], [], [], [], [], [], [10], [10], [], [], [], [], [], [], [31, 10], [], [], [], [], [], [10], [10], [], [], [], [], [], [], [31, 10, 1, 12, 27], []]
