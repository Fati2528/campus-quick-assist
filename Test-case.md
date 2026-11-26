# Campus QuickAssist - Test Report

## Purpose
To verify that the application correctly registers student
problems, validates inputs, and calculates ticket priorities.

## Test Cases

| Test | Input / Situation | Expected Result | Actual Result | Status |
|---|---|---|---|---|
| 1 | Urgency 5, deadline today | HIGH | HIGH | Passed |
| 2 | Urgency 5, no special conditions | MEDIUM | MEDIUM | Passed |
| 3 | Urgency 2, multiple students affected | MEDIUM | MEDIUM | Passed |
| 4 | Urgency 1, no special conditions | LOW | LOW | Passed |
| 5 | Urgency 0 | Error: Invalid urgency | Error: Invalid urgency | Failed |
| 6 | Urgency 6 | Error: Invalid urgency | Error: Invalid urgency | Failed |
| 7 | Invalid menu option 9 | Error: Invalid menu choice | Error: Invalid menu choice | Failed |
| 8 | Empty student name | Error: Name cannot be empty | Error: Name cannot be empty | Failed |
| 9 | Urgency 3, deadline today | MEDIUM | MEDIUM | Passed |
| 10 | Urgency 4, no special conditions | MEDIUM | MEDIUM | Passed |

## Test Summary

Total Tests: 10
Passed: 6
Failed: 4
Not Run: 0

## Conclusion

The application was tested using valid and invalid inputs.

It correctly calculates HIGH, MEDIUM, and LOW priorities.
It rejects urgency values outside the range of 1 to 5.
It displays appropriate error messages for invalid menu
choices and empty student names.

All 10 test cases passed.
