#!/bin/bash

set -e

API_URL="http://localhost:8080"

echo "=============================="
echo "0️⃣ ADMIN LOGIN"
echo "=============================="

ADMIN_LOGIN=$(curl -s -X POST $API_URL/login \
  -H "Content-Type: application/json" \
  -d '{
    "registrationNumber": "admin",
    "studentPassword": "Admin@123"
  }')

ADMIN_TOKEN=$(echo "$ADMIN_LOGIN" | jq -r '.token')

if [ "$ADMIN_TOKEN" == "null" ] || [ -z "$ADMIN_TOKEN" ]; then
  echo "❌ Admin login failed"
  echo "$ADMIN_LOGIN"
  exit 1
fi

echo "✅ Admin token obtained"

# -----------------------------
# STUDENT DATA
# -----------------------------
FIRST_NAME="Matheus"
LAST_NAME="de Almeida Cantarutti"
EMAIL="cantaruttimatheus94@gmail.com"
SSN="98765432100"
BIRTH_DATE="1998-05-15"
COURSE="COLLEGE_MASTER"
MODULE="module-2"
NEW_PASSWORD="MyNewPass123!"

echo
echo "=============================="
echo "1️⃣ CREATE STUDENT (ADMIN)"
echo "=============================="

CREATE_RESPONSE=$(curl -s -X POST $API_URL/api/v1/students/create-student \
  -H "Authorization: Bearer $ADMIN_TOKEN" \
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

echo "CREATE RESPONSE:"
echo "$CREATE_RESPONSE"

STUDENT_ID=$(echo "$CREATE_RESPONSE" | jq -r '.id')

if [ "$STUDENT_ID" == "null" ] || [ -z "$STUDENT_ID" ]; then
  echo "❌ Student creation failed"
  exit 1
fi

echo "✅ Student created with ID: $STUDENT_ID"

# -----------------------------
# REGISTRATION + TEMP PASSWORD
# -----------------------------
YEAR=$(date +%Y)
MONTH=$(date +%m)
LAST_DIGITS=${SSN: -4}

REGISTRATION_NUMBER="${YEAR}${MONTH}${LAST_DIGITS}"

if [ "$COURSE" == "COLLEGE_INTENSIVE" ]; then
  COURSE_CODE="TI"
elif [ "$COURSE" == "COLLEGE_MASTER" ]; then
  COURSE_CODE="TM"
elif [ "$COURSE" == "COLLEGE_COORP" ]; then
  COURSE_CODE="TC"
else
  echo "❌ Invalid course"
  exit 1
fi

TEMP_PASSWORD="${YEAR}${COURSE_CODE}${MONTH}${LAST_DIGITS}"

echo
echo "=============================="
echo "2️⃣ FIRST ACCESS – CHANGE PASSWORD"
echo "=============================="
echo "Registration: $REGISTRATION_NUMBER"
echo "Temp password: $TEMP_PASSWORD"

FIRST_ACCESS_RESPONSE=$(curl -s -X POST $API_URL/auth/first-access \
  -H "Content-Type: application/json" \
  -d "{
    \"registrationNumber\": \"$REGISTRATION_NUMBER\",
    \"oldPassword\": \"$TEMP_PASSWORD\",
    \"newPassword\": \"$NEW_PASSWORD\"
  }")

echo "$FIRST_ACCESS_RESPONSE"

echo "✅ Password changed"

# -----------------------------
# LOGIN AS STUDENT
# -----------------------------
echo
echo "=============================="
echo "3️⃣ STUDENT LOGIN"
echo "=============================="

LOGIN_RESPONSE=$(curl -s -X POST $API_URL/login \
  -H "Content-Type: application/json" \
  -d "{
    \"registrationNumber\": \"$REGISTRATION_NUMBER\",
    \"studentPassword\": \"$NEW_PASSWORD\"
  }")

STUDENT_TOKEN=$(echo "$LOGIN_RESPONSE" | jq -r '.token')

if [ "$STUDENT_TOKEN" == "null" ] || [ -z "$STUDENT_TOKEN" ]; then
  echo "❌ Student login failed"
  echo "$LOGIN_RESPONSE"
  exit 1
fi

echo "✅ Student JWT obtained"

# -----------------------------
# DEBUG ROLES & PERMISSIONS
# -----------------------------
echo
echo "=============================="
echo "4️⃣ DEBUG /me"
echo "=============================="

curl -s -X GET $API_URL/api/v1/debug/me \
  -H "Authorization: Bearer $STUDENT_TOKEN" \
  | jq

# -----------------------------
# AUTHORIZATION TEST
# -----------------------------
echo
echo "=============================="
echo "5️⃣ AUTH TEST – UPDATE SELF"
echo "=============================="

curl -s -X PUT $API_URL/api/v1/debug/students/$REGISTRATION_NUMBER \
  -H "Authorization: Bearer $STUDENT_TOKEN" \
  | jq
