#!/bin/bash

# -----------------------------
# STUDENT CONFIGURATION
# -----------------------------
FIRST_NAME="Lucas"
LAST_NAME="Silva"
EMAIL="cantaruttimatheus94@gmail.com"
SSN="98765432100"
BIRTH_DATE="1998-05-15"
COURSE="COLLEGE_MASTER"
MODULE="module-2"
NEW_PASSWORD="MyNewPass123!"

API_URL="http://localhost:8080"

# -----------------------------
# 1️⃣ CREATE STUDENT
# -----------------------------
echo "=== 1️⃣ Creating student ==="
CREATE_RESPONSE=$(curl -s -X POST $API_URL/api/v1/students \
  -H "Content-Type: application/json" \
  -d "{
        \"firstName\": \"$FIRST_NAME\",
        \"familyName\": \"$LAST_NAME\",
        \"email\": \"$EMAIL\",
        \"socialSecurityNumber\": \"$SSN\",
        \"birthDate\": \"$BIRTH_DATE\",
        \"courseEnrolled\": \"$COURSE\",
        \"moduleNameId\": \"$MODULE\"
      }")

STUDENT_ID=$(echo $CREATE_RESPONSE | jq -r '.id')
echo "Student created! ID: $STUDENT_ID"

# -----------------------------
# 2️⃣ GENERATE REGISTRATION NUMBER AND DEFAULT PASSWORD
# -----------------------------
YEAR=$(date +%Y)
MONTH=$(date +%m)
LAST_DIGITS=${SSN: -4}

# Registration number calculation (same as backend)
REGISTRATION_NUMBER="${YEAR}${MONTH}${LAST_DIGITS}"
echo "Calculated registration number: $REGISTRATION_NUMBER"

# Map course to code for default password
if [ "$COURSE" == "COLLEGE_INTENSIVE" ]; then
  COURSE_CODE="TI"
elif [ "$COURSE" == "COLLEGE_MASTER" ]; then
  COURSE_CODE="TM"
elif [ "$COURSE" == "COLLEGE_COORP" ]; then
  COURSE_CODE="TC"
else
  echo "Course not supported!"
  exit 1
fi

TEMP_PASSWORD="${YEAR}${COURSE_CODE}${MONTH}${LAST_DIGITS}"
echo "Default temporary password: $TEMP_PASSWORD"

# -----------------------------
# 3️⃣ FIRST ACCESS PASSWORD CHANGE
# -----------------------------
echo "=== 3️⃣ Changing first access password ==="
FIRST_ACCESS_PAYLOAD=$(jq -n \
  --arg reg "$REGISTRATION_NUMBER" \
  --arg current "$TEMP_PASSWORD" \
  --arg new "$NEW_PASSWORD" \
  '{registrationNumber: $reg, currentPassword: $current, newPassword: $new}')

curl -s -X POST $API_URL/auth/first-access \
  -H "Content-Type: application/json" \
  -d "$FIRST_ACCESS_PAYLOAD"
echo "Password changed to: $NEW_PASSWORD"

# -----------------------------
# 4️⃣ LOGIN WITH NEW PASSWORD
# -----------------------------
echo "=== 4️⃣ Logging in ==="
LOGIN_PAYLOAD=$(jq -n \
  --arg reg "$REGISTRATION_NUMBER" \
  --arg pwd "$NEW_PASSWORD" \
  '{registrationNumber: $reg, studentPassword: $pwd}')

LOGIN_RESPONSE=$(curl -s -X POST $API_URL/login \
  -H "Content-Type: application/json" \
  -d "$LOGIN_PAYLOAD")

TOKEN=$(echo $LOGIN_RESPONSE | jq -r '.token')
echo "JWT received: $TOKEN"

# -----------------------------
# 5️⃣ ACCESS PROTECTED ENDPOINT
# -----------------------------
echo "=== 5️⃣ Listing students with token ==="
curl -s -X GET $API_URL/api/v1/students \
  -H "Authorization: Bearer $TOKEN" \
  | jq
