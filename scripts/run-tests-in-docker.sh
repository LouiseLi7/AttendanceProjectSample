#!/bin/bash
mkdir coverage
chmod 777 coverage
docker run --network=host --rm -v `pwd`/coverage:/coverage-out  citest scripts/test.sh
