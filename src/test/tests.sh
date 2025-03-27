#!/bin/bash

API_ENDPOINT="http://localhost:8080"

cd "$(dirname "$0")"

# Colores
COLOR_RESET="$(tput sgr0)"
COLOR_RED="$(tput setaf 1)"
COLOR_GREEN="$(tput setaf 2)"
COLOR_CYAN="$(tput setaf 6)"

TEST_NUMBER=1
OK_COUNT=0
ERROR_COUNT=0
TEST_COUNT=0

while IFS="|" read -r TEST_NAME INPUT EXPECTED; do
    if [[ -z "$TEST_NAME" || "$TEST_NAME" == \#* ]]; then
        continue
    fi

    OUTPUT=$(curl --silent \
        -H "Content-Type: application/json" \
        -X POST \
        -d "$INPUT" \
        "$API_ENDPOINT/radar"
    )
    ((TEST_COUNT++))

    EXPECTED_JSON=$(echo "$EXPECTED" | jq -S . 2>/dev/null)
    OUTPUT_JSON=$(echo "$OUTPUT" | jq -S . 2>/dev/null)
    STATUS=$(echo "$OUTPUT_JSON" | jq -r '.status' 2>/dev/null)


    if [[ "$EXPECTED" == "error" && "$STATUS" != "200" ]]; then
        echo "$TEST_NAME: ${COLOR_GREEN}[  OK  ]${COLOR_RESET} (Expected error, got status: $STATUS)"
        ((OK_COUNT++))
    elif [[ "$EXPECTED_JSON" == "$OUTPUT_JSON" ]]; then
        echo "$TEST_NAME: ${COLOR_GREEN}[  OK  ]${COLOR_RESET}"
        ((OK_COUNT++))
    else
        echo "$TEST_NAME: ${COLOR_RED}[ FAIL ]${COLOR_RESET}"
        echo " > ${COLOR_CYAN}INPUT${COLOR_RESET}:    $INPUT"
        echo " > ${COLOR_CYAN}EXPECTED${COLOR_RESET}: $EXPECTED"
        echo " > ${COLOR_CYAN}OUTPUT${COLOR_RESET}:   $OUTPUT"
        ((ERROR_COUNT++))
    fi


    ((TEST_NUMBER++))
done < test_cases.txt
echo "TOTAL: ${TEST_COUNT}"
echo "OK: $OK_COUNT"
echo "ERROR: $ERROR_COUNT"
