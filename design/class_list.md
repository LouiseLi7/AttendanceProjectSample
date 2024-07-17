# Class design list
## Server:
- User
- Course
- Student
- AttendanceRecord
- WeeklyAttendanceRecord (extends from AttendanceRecord)
- LoginRequest
- Notifier (interface)
 - EmailNotifier (implements Notifier)
- PersistenceManager (functions: saveData(), loadData())
- SecurityManager (functions: logAccessCheck(),encryptData()...)

## Client:
### Model:
*Some classes can be shared with server. DRY*
- Course
- Student
- AttendanceRecord
- WeeklyAttendanceRecord (extends from AttendanceRecord)

### View:
- ClassView
  - ClassTextView *We may have to create a GUI in the future, so better to be extensible now*

### Controller:
- User
 - TextUser
- AttendanceController
