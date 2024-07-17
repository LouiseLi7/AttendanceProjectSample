var clover = {};

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {
    "classes": [{
        "el": 119,
        "id": 218,
        "methods": [{"el": 45, "sc": 5, "sl": 39}, {"el": 54, "sc": 5, "sl": 52}, {
            "el": 63,
            "sc": 5,
            "sl": 61
        }, {"el": 72, "sc": 5, "sl": 70}, {"el": 81, "sc": 5, "sl": 79}, {"el": 91, "sc": 5, "sl": 89}, {
            "el": 95,
            "sc": 5,
            "sl": 93
        }, {"el": 99, "sc": 5, "sl": 97}, {"el": 108, "sc": 5, "sl": 106}, {"el": 118, "sc": 5, "sl": 116}],
        "name": "Professor",
        "sl": 9
    }]
}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
    "test_0": {"methods": [{"sl": 52}], "name": "testGetName", "pass": true, "statements": [{"sl": 53}]},
    "test_19": {"methods": [{"sl": 61}], "name": "testGetEmail", "pass": true, "statements": [{"sl": 62}]},
    "test_2": {
        "methods": [{"sl": 39}, {"sl": 106}],
        "name": "testPrintStudents",
        "pass": true,
        "statements": [{"sl": 40}, {"sl": 41}, {"sl": 42}, {"sl": 43}, {"sl": 44}, {"sl": 107}]
    },
    "test_22": {
        "methods": [{"sl": 39}, {"sl": 52}],
        "name": "testReadNewCourse",
        "pass": true,
        "statements": [{"sl": 40}, {"sl": 41}, {"sl": 42}, {"sl": 43}, {"sl": 44}, {"sl": 53}]
    },
    "test_25": {
        "methods": [{"sl": 39}, {"sl": 106}],
        "name": "testPrintLectures",
        "pass": true,
        "statements": [{"sl": 40}, {"sl": 41}, {"sl": 42}, {"sl": 43}, {"sl": 44}, {"sl": 107}]
    },
    "test_34": {
        "methods": [{"sl": 79}, {"sl": 106}, {"sl": 116}],
        "name": "testCourses",
        "pass": true,
        "statements": [{"sl": 80}, {"sl": 107}, {"sl": 117}]
    },
    "test_36": {"methods": [{"sl": 52}], "name": "testConstructors", "pass": true, "statements": [{"sl": 53}]},
    "test_39": {"methods": [{"sl": 70}], "name": "testGetProfessorID", "pass": true, "statements": [{"sl": 71}]},
    "test_4": {
        "methods": [{"sl": 39}, {"sl": 52}, {"sl": 79}, {"sl": 106}],
        "name": "testPrintCourse",
        "pass": true,
        "statements": [{"sl": 40}, {"sl": 41}, {"sl": 42}, {"sl": 43}, {"sl": 44}, {"sl": 53}, {"sl": 80}, {"sl": 107}]
    },
    "test_43": {
        "methods": [{"sl": 39}, {"sl": 52}, {"sl": 61}, {"sl": 70}],
        "name": "testReadNewProfessor",
        "pass": true,
        "statements": [{"sl": 40}, {"sl": 41}, {"sl": 42}, {"sl": 43}, {"sl": 44}, {"sl": 53}, {"sl": 62}, {"sl": 71}]
    },
    "test_9": {
        "methods": [{"sl": 39}],
        "name": "testPrintStudentStatuses",
        "pass": true,
        "statements": [{"sl": 40}, {"sl": 41}, {"sl": 42}, {"sl": 43}, {"sl": 44}]
    }
}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [22, 9, 25, 2, 43, 4], [22, 9, 25, 2, 43, 4], [22, 9, 25, 2, 43, 4], [22, 9, 25, 2, 43, 4], [22, 9, 25, 2, 43, 4], [22, 9, 25, 2, 43, 4], [], [], [], [], [], [], [], [22, 36, 43, 0, 4], [22, 36, 43, 0, 4], [], [], [], [], [], [], [], [19, 43], [19, 43], [], [], [], [], [], [], [], [39, 43], [39, 43], [], [], [], [], [], [], [], [34, 4], [34, 4], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [34, 25, 2, 4], [34, 25, 2, 4], [], [], [], [], [], [], [], [], [34], [34], [], []]
